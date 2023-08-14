package com.boxi.transport.service.impl;

import com.boxi.core.errors.BusinessException;
import com.boxi.core.errors.EntityType;
import com.boxi.core.response.SelectResponse;
import com.boxi.hub.entity.Hub;
import com.boxi.hub.payload.dto.HubPermissionDto;
import com.boxi.transport.entity.Connection;
import com.boxi.transport.entity.Route;
import com.boxi.transport.payload.converter.ConnectionConverter;
import com.boxi.transport.payload.dto.ConnectionDelDto;
import com.boxi.transport.payload.dto.ConnectionDto;
import com.boxi.transport.payload.dto.ConnectionFilter;
import com.boxi.transport.repo.ConnectionRepository;
import com.boxi.transport.repo.RouteRepository;
import com.boxi.transport.service.ConnectionService;
import com.boxi.utils.DateUtil;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
@Slf4j
public class ConnectionServiceImpl implements ConnectionService {

    private final ConnectionRepository connectionRepository;
    private final ConnectionConverter connectionConverter;
    private final RouteRepository routeRepository;

    @Autowired
    public ConnectionServiceImpl(ConnectionRepository connectionRepository, ConnectionConverter connectionConverter, RouteRepository routeRepository) {
        this.connectionRepository = connectionRepository;
        this.connectionConverter = connectionConverter;
        this.routeRepository = routeRepository;
    }

    @Override
    public ConnectionDto create(ConnectionDto request) {
        Connection connection = connectionConverter.fromDtoToModel(request);
        return saveData(connection);
    }


    private ConnectionDto saveData(Connection connection) {
        Connection saved = connectionRepository.save(connection);
        return connectionConverter.fromModelToDto(saved);
    }

    @Override
    public Connection findById(Long id) {
        if (id == 0) return null;
        return connectionRepository.findById(id).orElseThrow(() -> {
            throw BusinessException.entityNotFoundException(EntityType.Connection, "connection.not.found");
        });
    }

    @Override
    public ConnectionDto findByIdDto(Long id) {

        return connectionConverter.fromModelToDto(findById(id));
    }

    @Override
    public Connection findTopByHubAndRoute(Hub hub, Route route) {
        return connectionRepository.findTopByHubAndRoute(hub, route).orElseThrow(() -> {
            throw BusinessException.entityNotFoundException(EntityType.Connection, "hub.not.found");
        });
    }

    @Override
    public List<Connection> findByRoute(Route route) {
        return connectionRepository.findByRoute(route);
    }

    @Override
    public void delete(Long id) {
        Connection connection = findById(id);
        connection.setRoute(null);
        connection.setHub(null);
        connection.setIsDeleted(true);
        connectionRepository.save(connection);
    }

    @Override
    public Connection fromSelect(SelectResponse select) {
        return findById(select.getId());
    }

    @Override
    public SelectResponse toSelect(Connection entity) {
        return new SelectResponse(entity.getId(), entity.selectToString());
    }

    @Override
    public List<Connection> saveAll(List<Connection> connections) {
        return connectionRepository.saveAllAndFlush(connections);
    }

    @Override
    public void deleteAll(List<Connection> connections) {
        for (Connection connection : connections) {
            delete(connection.getId());
        }
    }

    private List<Long> findChild(List<HubPermissionDto> hubList, Long parentId) {
        List<Long> list = new ArrayList<>();
        for (HubPermissionDto hubPermissionDto : hubList) {
            if (Objects.equals(hubPermissionDto.getParent(), parentId)) {
                list.add(hubPermissionDto.getId());
                if (hubPermissionDto.getChildren() != null)
                    list.addAll(findChild(hubPermissionDto.getChildren(), hubPermissionDto.getId()));
            } else
                list.add(hubPermissionDto.getId());
        }
        return list;
    }

    private List<Long> findAllHubId(List<HubPermissionDto> hubList) {
        List<Long> list = new ArrayList<>();
        for (HubPermissionDto hubPermissionDto : hubList) {
            list.add(hubPermissionDto.getId());
            if (hubPermissionDto.getChildren() != null)
                list.addAll(findChild(hubPermissionDto.getChildren(), hubPermissionDto.getId()));
        }
        return list;
    }

    @Override
    public Page<ConnectionDto> filter(ConnectionFilter filter, Pageable pageable) {
        if (filter.getHublist() != null) {
            Page<Connection> res = connectionRepository
                    .findAll((Specification<Connection>) (root, query, criteriaBuilder) -> {
                        List<Predicate> predicates = new ArrayList<>();
                        predicates.add(criteriaBuilder.equal(root.get("isDeleted"), false));
                        if (filter.getHub() != null && StringUtils.isNotBlank(filter.getHub())) {
                            predicates.add(criteriaBuilder.like(root.get("hub").get("name"), "%" + filter.getHub().trim() + "%"));
                        }
                        if (filter.getRoute() != null && StringUtils.isNotBlank(filter.getRoute())) {
                            predicates.add(criteriaBuilder.like(root.get("route").get("name"), "%" + filter.getRoute().trim() + "%"));
                        }
                        if (filter.getHublist() != null) {
                            List<Long> ids = findAllHubId(filter.getHublist());
                            Join<Object, Object> hubJoin = root.join("hub");
                            predicates.add(criteriaBuilder.and(hubJoin.get("id").in(ids)));
                        }
                        query.orderBy(criteriaBuilder.asc(root.get("id")));
                        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

                    }, pageable);
            return res.map(connectionConverter::fromModelToDto);
        } else
            throw BusinessException.entityNotFoundException(EntityType.Hub, "hub.list.not.found");
    }

    @Override
    public void physicalDelete(ConnectionDelDto dto) {
        Route route = routeRepository.findById(dto.getRouteId()).orElseThrow(() -> {
            throw BusinessException.valueException(EntityType.Connection, "route.not.found");
        });
        if (dto.getTimeStoppage() != null)
            route.setTimeStoppage((double) DateUtil.convertHHmmToMinute(dto.getTimeStoppage()));
        if (dto.getTransitTime() != null)
            route.setTransitTime((double) DateUtil.convertHHmmToMinute(dto.getTransitTime()));
        route.setDistance(dto.getDistanceVariance());
        route.setDistanceVariance(dto.getDistanceVariance());
        route.setNodes(dto.getNodes());
        routeRepository.save(route);
        connectionRepository.deleteAllById(dto.getDeleteNodeIds());
    }


}
