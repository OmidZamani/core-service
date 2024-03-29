package com.boxi.hub.service.impl;

import com.boxi.core.errors.BusinessException;
import com.boxi.core.errors.EntityType;
import com.boxi.core.response.SelectResponse;
import com.boxi.hub.entity.Hub;
import com.boxi.hub.entity.PudoStation;
import com.boxi.hub.payload.converter.PudoStationConverter;
import com.boxi.hub.payload.dto.PudoStationDto;
import com.boxi.hub.repo.HubRepository;
import com.boxi.hub.repo.PudoStationRepository;
import com.boxi.hub.service.PudoStationService;
import com.boxi.utils.DateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PudoStationServiceImpl implements PudoStationService {

    private final PudoStationConverter pudoStationConverter;
    private final PudoStationRepository pudoStationRepository;
    private final HubRepository hubRepository;

    public PudoStationServiceImpl(PudoStationConverter PudoStationConverter
            , PudoStationRepository PudoStationRepository, HubRepository hubRepository) {
        this.pudoStationConverter = PudoStationConverter;
        this.pudoStationRepository = PudoStationRepository;
        this.hubRepository = hubRepository;
    }

    @Override
    public PudoStationDto create(PudoStationDto dto) {
        return saveCreate(dto);
    }

    private PudoStationDto saveCreate(PudoStationDto dto) {
        PudoStation PudoStation = pudoStationConverter.fromDtoToModel(dto);
        PudoStation.setId(null);
        PudoStation.setIsDeleted(false);
        if (PudoStation.getIsActive() == null) PudoStation.setIsActive(true);
        return pudoStationConverter.fromModelToDto(pudoStationRepository.save(PudoStation));
    }

    @Override
    public PudoStationDto edit(PudoStationDto dto) {
        return saveEdit(dto);
    }

    private Boolean isExists(Long id) {
        return pudoStationRepository.existsById(id);
    }

    private PudoStationDto saveEdit(PudoStationDto dto) {
        if (isExists(dto.getId())) {
            PudoStation PudoStation = pudoStationConverter.fromDtoToModel(dto);
            PudoStation.setIsDeleted(false);
            if (PudoStation.getIsActive() == null) PudoStation.setIsActive(true);
            return pudoStationConverter.fromModelToDto(pudoStationRepository.save(PudoStation));
        } else
            throw BusinessException.entityNotFoundException(EntityType.PudoStation, "pudo.Staion.not.found");
    }

    @Override
    public PudoStationDto findById(Long id) {
        PudoStation PudoStation = pudoStationRepository.findById(id).orElseThrow(() -> {
            throw BusinessException.entityNotFoundException(EntityType.PudoStation, "pudo.Staion.not.found");
        });
        return pudoStationConverter.fromModelToDto(PudoStation);
    }

    @Override
    public void delete(Long id) {
        if (isExists(id))
            pudoStationRepository.deleteById(id);
        else
            throw BusinessException.entityNotFoundException(EntityType.PudoStation, "pudo.Staion.not.found");
    }

    @Override
    public Page<PudoStationDto> filter(PudoStationDto request, Pageable pageable) {

        return pudoStationRepository.findAll((Specification<PudoStation>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("isDeleted"), false));

            if (request.getIsActive() != null)
                predicates.add(criteriaBuilder.equal(root.get("isActive"), request.getIsActive()));

            if (request.getIsDeliveryPossible() != null)
                predicates.add(criteriaBuilder.equal(root.get("isDeliveryPossible"), request.getIsDeliveryPossible()));

            if (request.getIsPickupPossible() != null)
                predicates.add(criteriaBuilder.equal(root.get("isPickupPossible"), request.getIsPickupPossible()));

            if (request.getCode() != null)
                predicates.add(criteriaBuilder.like(root.get("code"), "%" + request.getCode() + "%"));

            if (request.getName() != null)
                predicates.add(criteriaBuilder.equal(root.get("name"), "%" + request.getName() + "%"));

            if (request.getLocationStartDate() != null)
                predicates.add(criteriaBuilder.equal(root.get("locationStartDate"), DateUtil.convertDateToJalaliDateDto(request.getLocationStartDate())));


            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        }, pageable).map(pudoStationConverter::fromModelToDto);

    }

    @Override
    public List<SelectResponse> select(String filter) {
        return pudoStationRepository.findAll((Specification<PudoStation>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("isDeleted"), false));
            predicates.add(criteriaBuilder.equal(root.get("isActive"), true));


            if (StringUtils.hasText(filter)) {
                Predicate code = criteriaBuilder.like(root.get("code"), "%" + filter + "%");
                Predicate name = criteriaBuilder.like(root.get("name"), "%" + filter + "%");
                predicates.add(criteriaBuilder.or(code, name));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        }).stream().map(this::toSelect).collect(Collectors.toList());
    }

    @Override
    public List<PudoStationDto> listOfStation() {

        return pudoStationRepository.findAll().stream().map(pudoStationConverter::fromModelToDto).collect(Collectors.toList());
    }

    @Override
    public List<PudoStationDto> getUnusedPudoStations(List<Long> pudostationIds, Long hubId) {
        List<PudoStationDto> list = new ArrayList<>();
        for (PudoStation station : pudoStationRepository.findAllByIsDeletedFalseAndIsActiveTrueAndHub(new Hub().setId(hubId))) {
            if (!pudostationIds.contains(station.getId())) {
                PudoStationDto pudoStationDto = new PudoStationDto();
                BeanUtils.copyProperties(station, pudoStationDto);
                list.add(pudoStationDto);
            }
        }
        return list;
    }

    @Override
    public List<PudoStationDto> findByIdList(List<Long> list, Long hubId) {
        return pudoStationRepository.findAllByIdInAndHubAndIsActiveIsTrueAndIsDeletedIsFalse(list, hubRepository.findById(hubId).orElseThrow())
                .stream().map(pudoStationConverter::fromModelToDto).collect(Collectors.toList());
    }

    @Override
    public List<PudoStationDto> findByNotIdList(List<Long> list, Long hubId) {
        return pudoStationRepository.findAllByIdNotInAndHubAndIsActiveIsTrueAndIsDeletedIsFalse(list, hubRepository.findById(hubId).orElseThrow()).stream().map(pudoStationConverter::fromModelToDto).collect(Collectors.toList());

    }

    @Override
    public List<PudoStationDto> findStationByHubId(Long hubId) {

        return pudoStationRepository.findAllByIsDeletedFalseAndIsActiveTrueAndHub(hubRepository.findById(hubId).orElseThrow()).stream().map(pudoStationConverter::fromModelToDto).collect(Collectors.toList());
    }

    private SelectResponse toSelect(PudoStation pudoStation) {
        return new SelectResponse(pudoStation.getId(), pudoStation.getName());
    }
}
