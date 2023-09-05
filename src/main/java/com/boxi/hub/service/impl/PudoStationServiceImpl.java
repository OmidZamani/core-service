package com.boxi.hub.service.impl;

import com.boxi.core.errors.BusinessException;
import com.boxi.core.errors.EntityType;
import com.boxi.hub.entity.PudoStation;
import com.boxi.hub.payload.converter.PudoStationConverter;
import com.boxi.hub.payload.dto.PudoStationDto;
import com.boxi.hub.repo.PudoStationRepository;
import com.boxi.hub.service.PudoStationService;
import com.boxi.utils.DateUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PudoStationServiceImpl implements PudoStationService {

    private final PudoStationConverter pudoStationConverter;
    private final PudoStationRepository pudoStationRepository;

    public PudoStationServiceImpl(PudoStationConverter PudoStationConverter
            , PudoStationRepository PudoStationRepository) {
        this.pudoStationConverter = PudoStationConverter;
        this.pudoStationRepository = PudoStationRepository;
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
}
