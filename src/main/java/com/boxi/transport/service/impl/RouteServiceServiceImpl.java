package com.boxi.transport.service.impl;

import com.boxi.core.errors.BusinessException;
import com.boxi.core.errors.EntityType;
import com.boxi.core.request.RouteFilter;
import com.boxi.core.response.SelectResponse;
import com.boxi.hub.entity.Hub;
import com.boxi.hub.repo.HubRepository;
import com.boxi.transport.entity.Connection;
import com.boxi.transport.entity.Route;
import com.boxi.transport.payload.converter.ConnectionConverter;
import com.boxi.transport.payload.converter.RouteConverter;
import com.boxi.transport.payload.dto.ConnectionDto;
import com.boxi.transport.payload.dto.ConnectionExcelDto;
import com.boxi.transport.payload.dto.RouteDto;
import com.boxi.transport.payload.dto.RouteExcelDto;
import com.boxi.transport.repo.RouteRepository;
import com.boxi.transport.service.ConnectionService;
import com.boxi.transport.service.RouteService;
import com.boxi.utils.DateUtil;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class RouteServiceServiceImpl implements RouteService {
    private final RouteRepository routeRepository;
    private final RouteConverter routeConverter;
    private final ConnectionService connectionService;
    private final ConnectionConverter connectionConverter;

    private final HubRepository hubRepository;

    @Autowired
    public RouteServiceServiceImpl(RouteRepository routeRepository, RouteConverter routeConverter, ConnectionService connectionService, ConnectionConverter connectionConverter, HubRepository hubRepository) {
        this.routeRepository = routeRepository;
        this.routeConverter = routeConverter;
        this.connectionService = connectionService;
        this.connectionConverter = connectionConverter;
        this.hubRepository = hubRepository;
    }

    @Override
    public RouteDto create(RouteDto request) {
        if (isExist(request.getCode()))
            throw BusinessException.valueException(EntityType.Route, "route.is.duplicate");


        Route entity = routeConverter.fromDtoToModel(request);
//        if (request.getConnections().size() < 2)
//            throw BusinessException.valueException(EntityType.Route, "route.count.not.valid");
        List<ConnectionDto> connectionDtos = request.getConnections();
        entity.setConnections(null);//rely on many to one side
        entity.setIsDeleted(false);

        Route route = routeRepository.save(entity);
        List<Connection> connections = connectionDtos.stream().map(connectionConverter::fromDtoToModel).peek(c ->
                {
                    c.setIsActive(true);
                    c.setId(null);
                    c.setIsDeleted(false);
                    c.setRoute(route);
                }
        ).collect(Collectors.toList());
        List<Connection> savedConnections = connectionService.saveAll(connections);
        route.setConnections(savedConnections);
        return routeConverter.fromModelToDto(route);
    }


    @Override
    public RouteDto edit(RouteDto request) {
        Route route = findById(request.getId());
        routeConverter.updateFromDto(request, route);
        if (request.getConnections().size() < 2)
            throw BusinessException.valueException(EntityType.Route, "route.count.not.valid");
        List<ConnectionDto> cons = request.getConnections();
        List<Connection> newConnections = new ArrayList<>();
        Route savedRoute = routeRepository.save(route);
        for (ConnectionDto itr : cons) {
            if (itr.getId() == null) {
                //new Connection added , other one ignored
                Connection c = connectionConverter.fromDtoToModel(itr);
                c.setIsActive(true);
                c.setId(null);
                c.setIsDeleted(false);
                c.setRoute(savedRoute);
                newConnections.add(c);
            } else {
                Connection c = connectionConverter.fromDtoToModel(itr);
                c.setIsActive(true);
                c.setIsDeleted(false);
                c.setRoute(savedRoute);
                newConnections.add(c);
            }

        }
        connectionService.saveAll(newConnections);
        List<Connection> savedConnections = connectionService.findByRoute(route);
        route.setConnections(savedConnections);
        return routeConverter.fromModelToDto(route);
    }

    @Override
    public Route findById(Long id) {
        if (id == 0) return null;
        return routeRepository.findById(id).orElseThrow(() -> {
            throw BusinessException.entityNotFoundException(EntityType.Route, "route.not.found");
        });
    }


    private Route findByCode(String code) {
        return routeRepository.findTopByCode(code).orElseThrow(() -> {
            throw BusinessException.entityNotFoundException(EntityType.Route, "route.code.not.found");
        });
    }

    private Boolean isExist(String code) {
        return routeRepository.existsByCodeAndIsDeletedFalse(code);
    }

    @Override
    public void delete(Long id) {
        Route r = findById(id);
        r.setIsDeleted(true);
        routeRepository.save(r);
    }

    @Override
    public Page<RouteDto> filter(RouteFilter filter, Pageable pageable) {


        Page<Route> res = routeRepository
                .findAll((Specification<Route>) (root, query, criteriaBuilder) -> {
                    List<Predicate> predicates = new ArrayList<>();
                    Predicate isDeleted = criteriaBuilder.equal(root.get("isDeleted"), false);

                    if (filter.getIsActive() != null) {
                        predicates.add(criteriaBuilder.equal(root.get("isActive"), filter.getIsActive()));
                    }

                    if (filter.getName() != null && StringUtils.isNotBlank(filter.getName())) {
                        predicates.add(criteriaBuilder.like(root.get("name"), "%" + filter.getName().trim() + "%"));
                    }
                    if (filter.getHubName() != null && StringUtils.isNotBlank(filter.getHubName())) {
                        Join<Object, Object> join = root.join("connections", JoinType.LEFT);
                        predicates.add(criteriaBuilder.like(join.get("hub").get("name"), "%" + filter.getHubName().trim() + "%"));
                    }
                    if (filter.getSourceHub() != null) {
                        Join<Object, Object> join = root.join("sourceHub", JoinType.LEFT);
                        predicates.add(criteriaBuilder.equal(join.get("id"), filter.getSourceHub().getId()));
                    }
                    if (filter.getTargetHub() != null) {
                        Join<Object, Object> join = root.join("targetHub", JoinType.LEFT);
                        predicates.add(criteriaBuilder.equal(join.get("id"), filter.getTargetHub().getId()));
                    }


                    predicates.add(criteriaBuilder.and(isDeleted));
                    return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
                }, pageable);
        return res.map(routeConverter::fromModelToDto);
    }

    @Override
    public Page<SelectResponse> select(String filter) {
        Pageable pageable = PageRequest.of(0, 100);
        return routeRepository.findAll((Specification<Route>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("isDeleted"), false));

            predicates.add(criteriaBuilder.equal(root.get("isActive"), true));

            if (filter != null && !filter.isEmpty()) {
                Predicate name = criteriaBuilder.and(criteriaBuilder.like(criteriaBuilder.upper(root.get("name")), "%" + filter.trim() + "%"));
                Predicate code = criteriaBuilder.and(criteriaBuilder.like(criteriaBuilder.upper(root.get("code")), "%" + filter.trim() + "%"));
                predicates.add(criteriaBuilder.or(name, code));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        }, pageable).map(this::toSelect);
    }

    @Override
    public Route fromSelect(SelectResponse select) {
        if (select == null) return null;
        return findById(select.getId());
    }

    @Override
    public SelectResponse toSelect(Route entity) {
        return new SelectResponse(entity.getId(), entity.selectToString());
    }

    @Override
    public RouteDto get(Long id) {
        Route o = findById(id);

        return routeConverter.fromModelToDto(o);
    }

    @Override
    public boolean ExcelValidation(List<RouteExcelDto> routeExcelList) {

        int i = 1;
        for (RouteExcelDto routeExcelDto : routeExcelList) {
            if (routeRepository.existsByCodeAndIsDeletedFalse(routeExcelDto.getCode()))
                throw BusinessException.valueException(EntityType.Route,
                        "route.is.duplicate",
                        routeExcelDto.getSelectSourceHub() + "  ردیف " + i);

            if (!hubRepository.existsByCodeAndIsDeletedFalse(routeExcelDto.getSelectTargetHub()))
                throw BusinessException.valueException(EntityType.Route,
                        "hub.code.not.found",
                        routeExcelDto.getSelectTargetHub() + "  ردیف " + i);

            if (!hubRepository.existsByCodeAndIsDeletedFalse(routeExcelDto.getSelectSourceHub()))
                throw BusinessException.valueException(EntityType.Route,
                        "hub.code.not.found",
                        routeExcelDto.getSelectSourceHub() + "  ردیف " + i);


            i++;
        }


        return true;
    }

    @Override
    public List<RouteDto> ImportExcel(List<RouteExcelDto> routeExcelList) {

        List<RouteDto> routeDtos = new ArrayList<>();


        for (RouteExcelDto routeExcelDto : routeExcelList) {
            RouteDto routeDto = routeConverter.fromExcelToDto(routeExcelDto);

            Hub source = hubRepository.findByCode(routeExcelDto.getSelectSourceHub());
            routeDto.setSelectSourceHub(new SelectResponse(source.getId(), source.getName()));

            Hub byCode = hubRepository.findByCode(routeExcelDto.getSelectTargetHub());
            routeDto.setSelectTargetHub(new SelectResponse(byCode.getId(), byCode.getName()));
            int sumofDistance = 0;
            int sumofDistancevariance = 0;
            double Sumofstoptime = 0;
            double SumofTrancetime = 0;

            List<ConnectionDto> connectionDtos = new ArrayList<>();
            for (ConnectionExcelDto connection : routeExcelDto.getConnections()) {
                sumofDistance += connection.getDistanceFromPreviousHub();
                sumofDistancevariance += connection.getDistanceVariance();

                Sumofstoptime += (double) DateUtil.convertHHmmToMinute(connection.getTimeStoppage());
                SumofTrancetime += (double) DateUtil.convertHHmmToMinute(connection.getTransitTime());
                routeDto.setDistance(connection.getDistanceFromPreviousHub());
                routeDto.setDistanceVariance(connection.getDistanceVariance());

                ConnectionDto connectionDto = connectionConverter.fromExcelToDto(connection);
                Hub hubCo = hubRepository.findByCode(connection.getSelectHub());


                connectionDto.setSelectHub(new SelectResponse(hubCo.getId(), hubCo.getName()));

                connectionDtos.add(connectionDto);
            }
            routeDto.setNodes(Long.valueOf(connectionDtos.size()));
            routeDto.setTransitTime(String.valueOf(SumofTrancetime));
            routeDto.setTimeStoppage(String.valueOf(Sumofstoptime));


            routeDto.setConnections(connectionDtos);


            routeDtos.add(create(routeDto));
        }

        return routeDtos;
    }

    @Override
    public Page<RouteDto> selectRoute(Long source, Long destination) {
        Pageable pageable = PageRequest.of(0, 100);
        return routeRepository.findAll((Specification<Route>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("isDeleted"), false));

            predicates.add(criteriaBuilder.equal(root.get("isActive"), true));

            predicates.add(criteriaBuilder.equal(root.get("sourceHub").get("id"), source));
            predicates.add(criteriaBuilder.equal(root.get("targetHub").get("id"), destination));


            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        }, pageable).map(routeConverter::fromModelToDto);

    }

}
