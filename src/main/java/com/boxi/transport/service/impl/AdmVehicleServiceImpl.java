package com.boxi.transport.service.impl;

import com.boxi.core.errors.BusinessException;
import com.boxi.core.errors.EntityType;
import com.boxi.core.response.SelectResponse;
import com.boxi.transport.entity.Vehicle;
import com.boxi.transport.payload.converter.AdmVehicleConverter;
import com.boxi.transport.payload.dto.FilterVehicle;
import com.boxi.transport.payload.dto.AdmVehicleDto;
import com.boxi.transport.repo.VehicleRepository;
import com.boxi.transport.service.AdmVehicleService;
import com.boxi.utils.DateUtil;
import org.springframework.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class AdmVehicleServiceImpl implements AdmVehicleService {

    private final VehicleRepository vehicleRepository;
    private final AdmVehicleConverter admVehicleConverter;

    @Autowired
    public AdmVehicleServiceImpl(VehicleRepository vehicleRepository, AdmVehicleConverter admVehicleConverter) {
        this.vehicleRepository = vehicleRepository;
        this.admVehicleConverter = admVehicleConverter;
    }

    @Override
    public AdmVehicleDto create(AdmVehicleDto request) {
        Vehicle entity = admVehicleConverter.fromDtoToModel(request);
        if (vehicleRepository.existsByVehicleNumber0AndVehicleNumber1AndVehicleNumber2AndVehicleNumber3(entity.getVehicleNumber0(), entity.getVehicleNumber1(), entity.getVehicleNumber2(), entity.getVehicleNumber3()))
            throw BusinessException.valueException(EntityType.Vehicle, "vehicle.exist");
//        entity.setIsActive(true);
        entity.setIsDeleted(false);
        entity.setIsOwnerShip(false);
        return saveVehicleData(entity);
    }

    @Override
    public AdmVehicleDto edit(AdmVehicleDto request) {
        Vehicle vehicle = findById(request.getId());
        admVehicleConverter.updateFromDto(request, vehicle);
        return saveVehicleData(vehicle);
    }

    private AdmVehicleDto saveVehicleData(Vehicle vehicle) {
        Vehicle saved = vehicleRepository.save(vehicle);
        return admVehicleConverter.fromModelToDto(saved);
    }

    @Override
    public Page<SelectResponse> select(String filter) {
        Pageable pageable = PageRequest.of(0, 100);
        Page<Vehicle> res = vehicleRepository
                .findAll((Specification<Vehicle>) (root, query, criteriaBuilder) -> {
                    List<Predicate> predicates = new ArrayList<>();
                    predicates.add(criteriaBuilder.equal(root.get("isDeleted"), false));
                    predicates.add(criteriaBuilder.equal(root.get("isActive"), true));
                    predicates.add(criteriaBuilder.equal(root.get("isOwnerShip"), false));
                    if (filter != null && !filter.isEmpty()) {
                        predicates.add(criteriaBuilder.like(root.get("vehicleNumber0"), "%" + filter.trim() + "%"));
                        predicates.add(criteriaBuilder.like(root.get("vehicleNumber1"), "%" + filter.trim() + "%"));
                        predicates.add(criteriaBuilder.like(root.get("vehicleNumber2"), "%" + filter.trim() + "%"));
                        predicates.add(criteriaBuilder.like(root.get("vehicleNumber3"), "%" + filter.trim() + "%"));
                    }
                    return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
                }, pageable);
        return res.map(this::toSelect);
    }

    private SelectResponse toSelect(Vehicle vehicle) {
        return new SelectResponse(vehicle.getId(), vehicle.selectToString());
    }


    @Override
    public Page<AdmVehicleDto> filter(FilterVehicle filter, Pageable pageable) {
        log.warn(">>>>>>>>>>>>>>>" + filter.toJson());
        Page<Vehicle> res = vehicleRepository
                .findAll((Specification<Vehicle>) (root, query, criteriaBuilder) -> {
                    List<Predicate> predicates = new ArrayList<>();
                    Predicate isDeleted = criteriaBuilder.equal(root.get("isDeleted"), false);
                    predicates.add(isDeleted);
                    predicates.add(criteriaBuilder.equal(root.get("isOwnerShip"), false));

                    if (filter.getIsActive() != null) {
                        predicates.add(criteriaBuilder.equal(root.get("isActive"), filter.getIsActive()));
                    }
                    if (org.springframework.util.StringUtils.hasText(filter.getVehicleNumber0())) {
                        predicates.add(criteriaBuilder.like(root.get("vehicleNumber0"), "%" + filter.getVehicleNumber0().trim() + "%"));
                    }
                    if (StringUtils.hasText(filter.getVehicleNumber1())) {
                        predicates.add(criteriaBuilder.like(root.get("vehicleNumber1"), "%" + filter.getVehicleNumber1().trim() + "%"));
                    }
                    if (StringUtils.hasText(filter.getVehicleNumber2())) {
                        predicates.add(criteriaBuilder.like(root.get("vehicleNumber2"), "%" + filter.getVehicleNumber2().trim() + "%"));
                    }
                    if (StringUtils.hasText(filter.getVehicleNumber3())) {
                        predicates.add(criteriaBuilder.like(root.get("vehicleNumber3"), "%" + filter.getVehicleNumber3().trim() + "%"));
                    }
                    if (StringUtils.hasText(filter.getHubName())) {
                        predicates.add(criteriaBuilder.like(root.get("hub").get("name"), "%" + filter.getHubName().trim() + "%"));
                    }
                    if (StringUtils.hasText(filter.getHubCode())) {
                        predicates.add(criteriaBuilder.equal(root.get("hub").get("code"), filter.getHubCode().trim()));
                    }
                    if (StringUtils.hasText(filter.getVehicleMakeSelect())) {
                        Join<Object, Object> VehicleMakeSelect = root.join("vehicleMake");
                        predicates.add(criteriaBuilder.like(VehicleMakeSelect.get("name"), "%" + filter.getVehicleMakeSelect().trim() + "%"));
                    }
                    if (filter.getSelectRoute() != null) {
                        Join<Object, Object> SelectRoute = root.join("route");
                        predicates.add(criteriaBuilder.equal(SelectRoute.get("id"), filter.getSelectRoute().getId()));
                    }

                    return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
                }, pageable);
        return res.map(admVehicleConverter::fromModelToDto);

    }

    @Override
    public Vehicle findById(Long id) {
        if (id == 0) return null;
        return vehicleRepository.findById(id).orElseThrow(() -> {
            throw BusinessException.entityNotFoundException(EntityType.Vehicle, "vehicle.not.found");
        });
    }

    @Override
    public void delete(Long id) {
        if (findById(id) != null)
            vehicleRepository.logicalDelete(id);
    }

    @Override
    public AdmVehicleDto get(Long id) {
        Vehicle o = findById(id);
        return admVehicleConverter.fromModelToDto(o);
    }

    @Override
    public List<AdmVehicleDto> listOfAdmVehicleInHub(AdmVehicleDto dto) {

        log.warn(">>>>>>>>>>>>>>>" + dto.toJson());
        List<Vehicle> all = vehicleRepository
                .findAll((Specification<Vehicle>) (root, query, criteriaBuilder) -> {
                    List<Predicate> predicates = new ArrayList<>();
                    Predicate isDeleted = criteriaBuilder.equal(root.get("isDeleted"), false);
                    predicates.add(isDeleted);
                    predicates.add(criteriaBuilder.equal(root.get("isOwnerShip"), false));
                    if (dto.getSelectHub() != null) {
                        predicates.add(criteriaBuilder.equal(root.get("hub").get("id"), dto.getSelectHub().getId()));
                    }

                    predicates.add(criteriaBuilder.equal(root.get("isActive"), true));

                    if (dto.getDayToStartWork() != null) {
                        Date date = DateUtil.convertDateToJalaliDateDto(dto.getDayToStartWork());
                        predicates.add(criteriaBuilder.between(criteriaBuilder.literal(date), root.get("timeToStartWork"), root.get("timeToFinishWork")));
                    }


                    return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
                });
        return all.stream().map(admVehicleConverter::fromModelToDto).collect(Collectors.toList());
    }

}
