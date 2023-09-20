package com.boxi.transport.service.impl;

import com.boxi.core.errors.BusinessException;
import com.boxi.core.errors.EntityType;
import com.boxi.core.response.SelectResponse;
import com.boxi.hub.entity.Hub;
import com.boxi.hub.payload.dto.HubPermissionDto;
import com.boxi.hub.repo.HubRepository;
import com.boxi.transport.entity.Vehicle;
import com.boxi.transport.entity.VehicleExceptions;
import com.boxi.transport.entity.VehicleMake;
import com.boxi.transport.entity.Vendor;
import com.boxi.transport.enums.FleetType;
import com.boxi.transport.enums.VehicleStatus;
import com.boxi.transport.enums.VehicleType;
import com.boxi.transport.payload.converter.AdmVehicleConverter;
import com.boxi.transport.payload.converter.VehicleConverter;
import com.boxi.transport.payload.converter.VehicleExceptionsConverter;
import com.boxi.transport.payload.converter.VehicleMakeConverter;
import com.boxi.transport.payload.dto.*;
import com.boxi.transport.payload.request.HubFilter;
import com.boxi.transport.repo.VehicleExceptionsRepository;
import com.boxi.transport.repo.VehicleMakeRepository;
import com.boxi.transport.repo.VehicleRepository;
import com.boxi.transport.service.VehicleService;
import com.boxi.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final VehicleConverter vehicleConverter;
    private final HubRepository hubRepository;
    private final VehicleMakeRepository vehicleMakeRepository;
    private final VehicleMakeConverter vehicleMakeConverter;

    private final VehicleExceptionsConverter vehicleExceptionsConverter;
    private final VehicleExceptionsRepository vehicleExceptionsRepository;

    private final AdmVehicleConverter admVehicleConverter;


    @Autowired
    public VehicleServiceImpl(VehicleRepository vehicleRepository,
                              VehicleConverter vehicleConverter,
                              HubRepository hubRepository,
                              VehicleMakeRepository vehicleMakeRepository,
                              VehicleMakeConverter vehicleMakeConverter, VehicleExceptionsConverter vehicleExceptionsConverter,
                              VehicleExceptionsRepository vehicleExceptionsRepository, AdmVehicleConverter admVehicleConverter) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleConverter = vehicleConverter;
        this.hubRepository = hubRepository;
        this.vehicleMakeRepository = vehicleMakeRepository;
        this.vehicleMakeConverter = vehicleMakeConverter;
        this.vehicleExceptionsConverter = vehicleExceptionsConverter;
        this.vehicleExceptionsRepository = vehicleExceptionsRepository;
        this.admVehicleConverter = admVehicleConverter;
    }

    @Override
    public VehicleDto create(VehicleDto request) {
        Vehicle entity = vehicleConverter.fromDtoToModel(request);
        if (vehicleRepository.existsByVehicleNumber0AndVehicleNumber1AndVehicleNumber2AndVehicleNumber3(entity.getVehicleNumber0(), entity.getVehicleNumber1(), entity.getVehicleNumber2(), entity.getVehicleNumber3()))
            throw BusinessException.valueException(EntityType.Vehicle, "vehicle.is.duplicate");

        entity.setIsDeleted(false);
        entity.setIsActive(request.getIsActive());
        entity.setIsOwnerShip(true);

        return saveVehicleData(entity);
    }

    @Override
    public VehicleDto createClient(VehicleDto request) {
        Vehicle entity = vehicleConverter.fromDtoToModel(request);
        Vehicle findVehicle = vehicleRepository.findByVehicleNumber0AndVehicleNumber1AndVehicleNumber2AndVehicleNumber3(entity.getVehicleNumber0(), entity.getVehicleNumber1(), entity.getVehicleNumber2(), entity.getVehicleNumber3());
        if (findVehicle != null) {
            entity.setId(findVehicle.getId());

            Vehicle save = vehicleRepository.save(entity);
            return vehicleConverter.fromModelToDto(save);
        }

        entity.setIsDeleted(false);
        entity.setIsActive(request.getIsActive());
        entity.setIsOwnerShip(true);

        return saveVehicleData(entity);

    }

    @Override
    public VehicleDto edit(VehicleDto request) {
        Vehicle vehicle = findById(request.getId());
        vehicleConverter.updateFromDto(request, vehicle);
        return saveVehicleData(vehicle);
    }

    private VehicleDto saveVehicleData(Vehicle vehicle) {
        vehicle.setIsOwnerShip(true);
        Vehicle saved = vehicleRepository.save(vehicle);
        return vehicleConverter.fromModelToDto(saved);
    }


    @Override
    public Page<SelectResponse> select(String filter, HubFilter hubFilter) {
        if (hubFilter.getHublist() != null) {
            Pageable pageable = PageRequest.of(0, 100);
            Page<Vehicle> res = vehicleRepository
                    .findAll((Specification<Vehicle>) (root, query, criteriaBuilder) -> {
                        List<Predicate> predicates = new ArrayList<>();
                        predicates.add(criteriaBuilder.equal(root.get("isDeleted"), false));
                        predicates.add(criteriaBuilder.equal(root.get("isOwnerShip"), true));
                        predicates.add(criteriaBuilder.equal(root.get("isActive"), true));

                        if (filter != null && !filter.isEmpty()) {
                            predicates.add(criteriaBuilder.like(root.get("vehicleNumber0"), "%" + filter.trim() + "%"));
                            predicates.add(criteriaBuilder.like(root.get("vehicleNumber1"), "%" + filter.trim() + "%"));
                            predicates.add(criteriaBuilder.like(root.get("vehicleNumber2"), "%" + filter.trim() + "%"));
                            predicates.add(criteriaBuilder.like(root.get("vehicleNumber3"), "%" + filter.trim() + "%"));
                        }
                        List<Long> ids = findAllHubId(hubFilter.getHublist());
                        Join<Object, Object> hubJoin = root.join("hub");
                        predicates.add(criteriaBuilder.and(hubJoin.get("id").in(ids)));

                        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
                    }, pageable);
            return res.map(this::toSelect);
        } else
            throw BusinessException.entityNotFoundException(EntityType.Vehicle, "hub.list.not.found");
    }

    private SelectResponse toSelect(Vehicle vehicle) {
        return new SelectResponse(vehicle.getId(), vehicle.selectToString());
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
    public Page<VehicleDto> filter(FilterVehicle filter, Pageable pageable) {
        if (filter.getHublist() != null) {
            Page<Vehicle> res = vehicleRepository
                    .findAll((Specification<Vehicle>) (root, query, criteriaBuilder) -> {
                        List<Predicate> predicates = new ArrayList<>();
                        predicates.add(criteriaBuilder.equal(root.get("isDeleted"), false));

                        if (filter.getIsActive() != null) {
                            predicates.add(criteriaBuilder.equal(root.get("isActive"), filter.getIsActive()));
                        }

                        predicates.add(criteriaBuilder.equal(root.get("isOwnerShip"), true));

                        if (StringUtils.hasText(filter.getVehicleNumber0())) {
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
                        if (filter.getStatus() != null) {
                            predicates.add(criteriaBuilder.equal(root.get("status"), VehicleStatus.findByValue(filter.getStatus().getId())));
                        }
                        if (filter.getSelecttype() != null) {
                            predicates.add(criteriaBuilder.equal(root.get("type"), VehicleType.findByValue(filter.getSelecttype().getId())));
                        }

                        if (StringUtils.hasText(filter.getHubName())) {
                            predicates.add(criteriaBuilder.like(root.get("hub").get("name"), "%" + filter.getHubName().trim() + "%"));
                        }
                        if (StringUtils.hasText(filter.getHubCode())) {
                            predicates.add(criteriaBuilder.equal(root.get("hub").get("code"), filter.getHubCode().trim()));
                        }

                        if (filter.getHublist() != null) {
                            if (filter.getTransportVehicleList() == null) {
                                List<Long> ids = findAllHubId(filter.getHublist());
                                Join<Object, Object> hubJoin = root.join("hub");
                                predicates.add(criteriaBuilder.and(hubJoin.get("id").in(ids)));
                            }
                        }

                        if (filter.getTransportVehicleList() != null) {

                            Predicate id1 = criteriaBuilder.and(root.get("hub").get("id").in(filter.getHublist().get(0).getId()));

                            Predicate id = criteriaBuilder.and(root.get("id").in(filter.getTransportVehicleList()));
                            predicates.add(criteriaBuilder.or(id, id1));
                        }

                        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
                    }, pageable);
            return res.map(vehicleConverter::fromModelToDto);
        } else
            throw BusinessException.entityNotFoundException(EntityType.Hub, "hub.list.not.found");

    }

    @Override
    public Vehicle findById(Long id) {
        if (id == 0) return null;
        return vehicleRepository.findById(id).orElseThrow(() -> {
            throw BusinessException.entityNotFoundException(EntityType.Vehicle, "vehicle.not.found");
        });
    }

    @Override
    public VehicleDto clientFindById(Long id) {

        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(
                () -> {
                    throw BusinessException.valueException(EntityType.Vehicle, "vehicle.not.found");
                }
        );
        VehicleDto vehicleDto = vehicleConverter.fromModelToDto(vehicle);

        if (vehicle.getWeightCapacity() != null && vehicle.getAllocatedWeight() != null)
            vehicleDto.setAssignableWeight(vehicle.getWeightCapacity() - vehicle.getAllocatedWeight().doubleValue());
        else
            vehicleDto.setAssignableWeight((double) 0);

        if (vehicle.getVolumeCapacity() != null && vehicle.getAllocatedVolume() != null)
            vehicleDto.setAssignableVolume(vehicle.getVolumeCapacity() - vehicle.getAllocatedVolume().doubleValue());
        else
            vehicleDto.setAssignableVolume((double) 0);

        return vehicleDto;
    }

    @Override
    public void delete(Long id) {
        vehicleRepository.logicalDelete(id);
    }

    @Override
    public boolean ExcelValidation(List<VehicleExcelDto> vehicleExcelList) {

        int i = 1;
        for (VehicleExcelDto vehicleExcelDto : vehicleExcelList) {
            if (!hubRepository.existsByCodeAndIsDeletedFalse(vehicleExcelDto.getSelectHub()))
                throw BusinessException.valueException(EntityType.Vehicle,
                        "hub.not.found",
                        vehicleExcelDto.getSelectHub() + "  ردیف " + i);
            if (!vehicleMakeRepository.existsByCodeAndIsDeletedFalse(vehicleExcelDto.getVehicleMakeSelect()))
                throw BusinessException.valueException(EntityType.Vehicle,
                        "code.vmk.not.found",
                        vehicleExcelDto.getVehicleMakeSelect() + "  ردیف " + i);

            if (vehicleRepository.existsByVehicleNumber0AndVehicleNumber1AndVehicleNumber2AndVehicleNumber3(vehicleExcelDto.getVehicleNumber0(), vehicleExcelDto.getVehicleNumber1(), vehicleExcelDto.getVehicleNumber2(), vehicleExcelDto.getVehicleNumber3()))
                throw BusinessException.valueException(EntityType.Vehicle, "vehicle.is.duplicate", "vehicle.exist", "  ردیف " + i);
            i++;
        }


        return true;
    }

    @Override
    public List<VehicleDto> ImportExcel(List<VehicleExcelDto> vehicleExcelList) {
        List<VehicleDto> vehicleList = new ArrayList<>();

        for (VehicleExcelDto vehicleExcelDto : vehicleExcelList) {
            VehicleDto vehicleDto = vehicleConverter.fromExcelToDto(vehicleExcelDto);

            Hub byCode = hubRepository.findByCode(vehicleExcelDto.getSelectHub());
            vehicleDto.setSelectHub(new SelectResponse(byCode.getId(), byCode.getName()));

            VehicleMake byCode1 = vehicleMakeRepository.findByCode(vehicleExcelDto.getVehicleMakeSelect());
            vehicleDto.setVehicleMakeSelect(new SelectResponse(byCode1.getId(), byCode1.getName()));


            vehicleDto.setFleetTypeSelect(new SelectResponse(FleetType.findByfa(vehicleExcelDto.getFleetTypeSelect()).getValue(), FleetType.findByfa(vehicleExcelDto.getFleetTypeSelect()).getFa()));

            vehicleList.add(create(vehicleDto));

        }
        return vehicleList;
    }

    @Override
    public List<SelectResponse> getVehicleType() {

        return VehicleType.select();
    }

    @Override
    public VehicleDto clientFindByCarTag(CarTagDto dto) {

        Vehicle vehicle = vehicleRepository.findByVehicleNumber0AndVehicleNumber1AndVehicleNumber2AndVehicleNumber3(dto.getVehicleNumber0(),
                dto.getVehicleNumber1(),
                dto.getVehicleNumber2(),
                dto.getVehicleNumber3()
        );
        return vehicleConverter.fromModelToDto(vehicle);

    }

    @Override
    public VehicleExceptionsDto createException(VehicleExceptionsDto dto) {
        return saveCreateException(dto);
    }

    @Override
    public List<Long> findByType(Long type, Long HubId) {
        List<Vehicle> allByTypeAndHub = vehicleRepository.findAllByTypeAndHub(VehicleType.findByValue(type), new Hub().setId(HubId));

        return allByTypeAndHub.stream().map(Vehicle::getId).collect(Collectors.toList());
    }

    @Override
    public List<Long> findByMake(Long type, Long HubId) {
        List<Vehicle> allByTypeAndHub = vehicleRepository.findAllByVehicleMakeAndHub(new VehicleMake().setId(type), new Hub().setId(HubId));

        return allByTypeAndHub.stream().map(Vehicle::getId).collect(Collectors.toList());

    }

    @Override
    public VehicleDto updateAssignable(VehicleDto dto) {
        Vehicle vehicle = vehicleRepository.findById(dto.getId()).orElseThrow();
        vehicle.setAllocatedVolume(BigDecimal.valueOf(dto.getAssignableVolume()));
        vehicle.setAllocatedWeight(BigDecimal.valueOf(dto.getAssignableWeight()));

        return vehicleConverter.fromModelToDto(vehicleRepository.save(vehicle));

    }

    @Override
    public List<Long> findByVendor(Long id) {

        List<Vehicle> allByVendorAndiAndIsActiveIsTrueAndIsDeletedIsFalse = vehicleRepository.findAllByVendorAndIsActiveIsTrueAndIsDeletedIsFalse(new Vendor().setId(id));
        return allByVendorAndiAndIsActiveIsTrueAndIsDeletedIsFalse.stream().map(Vehicle::getId).collect(Collectors.toList());
    }

    @Override
    public VehicleMakeDto findByMakeById(Long id) {
        return vehicleMakeConverter.fromModelToDto(vehicleMakeRepository.findById(id).orElseThrow());
    }

    @Override
    public List<CarTagDto> findByCarTagNumberSuggest(CarTagDto dto) {
        return vehicleRepository.findByVehicleNumber0OrVehicleNumber1OrVehicleNumber2OrVehicleNumber3(dto.getVehicleNumber0(),
                dto.getVehicleNumber1(),
                dto.getVehicleNumber2(),
                dto.getVehicleNumber3()).stream().map(this::tocartagenumber).collect(Collectors.toList());
    }

    @Override
    public List<VehicleExceptionsDto> findVehicleException(VehicleExceptionsDto dto) {
        Date date = DateUtil.convertDateToJalaliDateDto(dto.getDateDto());
        List<VehicleExceptions> allByCreatedDateAndVehicle = vehicleExceptionsRepository.findAllByCreatedDateBetweenAndVehicle(date, date, new Vehicle().setId(dto.getSelectVehicle().getId()));
        return allByCreatedDateAndVehicle.stream().map(vehicleExceptionsConverter::fromModelToDto).collect(Collectors.toList());
    }

    @Override
    public Page<VehicleDto> listOfVehicleInHub(FilterVehicle filter, Pageable pageable) {
        Page<Vehicle> res = vehicleRepository
                .findAll((Specification<Vehicle>) (root, query, criteriaBuilder) -> {
                    List<Predicate> predicates = new ArrayList<>();
                    predicates.add(criteriaBuilder.equal(root.get("isDeleted"), false));

                    if (filter.getIsActive() != null) {
                        predicates.add(criteriaBuilder.equal(root.get("isActive"), filter.getIsActive()));
                    }

                    predicates.add(criteriaBuilder.equal(root.get("isOwnerShip"), true));

                    if (StringUtils.hasText(filter.getVehicleNumber0())) {
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
                    if (filter.getStatus() != null) {
                        predicates.add(criteriaBuilder.equal(root.get("status"), VehicleStatus.findByValue(filter.getStatus().getId())));
                    }
                    if (filter.getSelecttype() != null) {
                        predicates.add(criteriaBuilder.equal(root.get("type"), VehicleType.findByValue(filter.getSelecttype().getId())));
                    }

                    if (StringUtils.hasText(filter.getHubName())) {
                        predicates.add(criteriaBuilder.like(root.get("hub").get("name"), "%" + filter.getHubName().trim() + "%"));
                    }
                    if (StringUtils.hasText(filter.getHubCode())) {
                        predicates.add(criteriaBuilder.equal(root.get("hub").get("code"), filter.getHubCode().trim()));
                    }

                    if (filter.getHublist() != null) {
                        if (filter.getTransportVehicleList() == null) {
                            List<Long> ids = findAllHubId(filter.getHublist());
                            Join<Object, Object> hubJoin = root.join("hub");
                            predicates.add(criteriaBuilder.and(hubJoin.get("id").in(ids)));
                        }
                    }

                    if (filter.getTransportVehicleList() != null) {

                        Predicate id1 = criteriaBuilder.and(root.get("hub").get("id").in(filter.getHublist().get(0).getId()));

                        Predicate id = criteriaBuilder.and(root.get("id").in(filter.getTransportVehicleList()));
                        predicates.add(criteriaBuilder.or(id, id1));
                    }

                    return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
                }, pageable);
        return res.map(vehicleConverter::fromModelToDto);
    }

    @Override
    public List<AdmVehicleDto> listOfAdmVehicleInHub(AdmVehicleDto dto) {

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

    @Override
    public List<CarTagDto> clientSelect(CarTagDto dto, Long hubId) {
        List<Vehicle> all = vehicleRepository
                .findAll((Specification<Vehicle>) (root, query, criteriaBuilder) -> {
                    List<Predicate> predicates = new ArrayList<>();
                    predicates.add(criteriaBuilder.equal(root.get("isDeleted"), false));
                    predicates.add(criteriaBuilder.equal(root.get("isActive"), true));
                    predicates.add(criteriaBuilder.equal(root.get("hub").get("id"), hubId));
                    if (StringUtils.hasText(dto.getVehicleNumber0()))
                        predicates.add(criteriaBuilder.like(root.get("vehicleNumber0"), "%" + dto.getVehicleNumber0() + "%"));

                    if (StringUtils.hasText(dto.getVehicleNumber1()))
                        predicates.add(criteriaBuilder.like(root.get("vehicleNumber1"), "%" + dto.getVehicleNumber1() + "%"));

                    if (StringUtils.hasText(dto.getVehicleNumber2()))
                        predicates.add(criteriaBuilder.like(root.get("vehicleNumber2"), "%" + dto.getVehicleNumber2() + "%"));

                    if (StringUtils.hasText(dto.getVehicleNumber3()))
                        predicates.add(criteriaBuilder.like(root.get("vehicleNumber3"), "%" + dto.getVehicleNumber3() + "%"));


                    return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
                });
        return all.stream().map(this::tocartagenumber).collect(Collectors.toList());
    }

    @Override
    public VehicleDto findByDriverId(Long driverId) {

        return vehicleConverter.fromModelToDto(vehicleRepository.findTopByFirstDriverIdOrSecondDriverIdOrderByIdDesc(driverId, driverId));
    }


    private CarTagDto tocartagenumber(Vehicle vehicle) {
        CarTagDto dto = new CarTagDto();
        dto.setVehicleNumber0(vehicle.getVehicleNumber0());
        dto.setVehicleNumber1(vehicle.getVehicleNumber1());
        dto.setVehicleNumber2(vehicle.getVehicleNumber2());
        dto.setVehicleNumber3(vehicle.getVehicleNumber3());
        dto.setId(vehicle.getId());
        return dto;
    }

    public VehicleExceptionsDto saveCreateException(VehicleExceptionsDto dto) {
        findById(dto.getSelectVehicle().getId());
        dto.setId(null);
        VehicleExceptions save = vehicleExceptionsRepository.save(vehicleExceptionsConverter.fromDtoToModel(dto));
        return vehicleExceptionsConverter.fromModelToDto(save);
    }

}
