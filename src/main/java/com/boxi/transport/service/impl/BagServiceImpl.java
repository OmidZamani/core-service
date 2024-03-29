package com.boxi.transport.service.impl;

import com.boxi.core.errors.BusinessException;
import com.boxi.core.errors.EntityType;
import com.boxi.core.response.SelectResponse;
import com.boxi.hub.entity.Hub;
import com.boxi.hub.payload.converter.HubConverter;
import com.boxi.hub.payload.dto.HubPermissionDto;
import com.boxi.hub.repo.HubRepository;
import com.boxi.transport.entity.Bag;
import com.boxi.transport.entity.BagExceptions;
import com.boxi.transport.enums.BagStatus;
import com.boxi.transport.enums.BagType;
import com.boxi.transport.payload.converter.BagConverter;
import com.boxi.transport.payload.converter.BagExceptionsConvertor;
import com.boxi.transport.payload.dto.BagDto;
import com.boxi.transport.payload.dto.BagExcelDto;
import com.boxi.transport.payload.dto.BagExceptionsDto;
import com.boxi.transport.payload.dto.BagFilter;
import com.boxi.transport.payload.request.HubFilter;
import com.boxi.transport.repo.BagExceptionsRepository;
import com.boxi.transport.repo.BagRepository;
import com.boxi.transport.service.BagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class BagServiceImpl implements BagService {

    private final BagRepository bagRepository;
    private final BagConverter bagConverter;

    private final HubRepository hubRepository;
    private final HubConverter hubConverter;

    private final BagExceptionsRepository bagExceptionsRepository;
    private final BagExceptionsConvertor bagExceptionsConvertor;

    @Autowired
    public BagServiceImpl(BagRepository bagRepository, BagConverter bagConverter, HubRepository hubRepository, HubConverter hubConverter, BagExceptionsRepository bagExceptionsRepository, BagExceptionsConvertor bagExceptionsConvertor) {
        this.bagRepository = bagRepository;
        this.bagConverter = bagConverter;
        this.hubRepository = hubRepository;
        this.hubConverter = hubConverter;
        this.bagExceptionsRepository = bagExceptionsRepository;
        this.bagExceptionsConvertor = bagExceptionsConvertor;
    }

    @Override
    public Page<SelectResponse> select(String filter, HubFilter hubList) {
        if (hubList.getHublist() != null) {
            Pageable pageable = PageRequest.of(0, 100);
            Page<Bag> res = bagRepository.findAll((Specification<Bag>) (root, query, criteriaBuilder) -> {
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(criteriaBuilder.equal(root.get("isDeleted"), false));
                if (filter != null && !filter.isEmpty()) {
                    predicates.add(criteriaBuilder.like(root.get("bagNumber"), "%" + filter.trim() + "%"));
                }

                List<Long> ids = findAllhubid(hubList.getHublist());
                Join<Object, Object> hubjoinsource = root.join("sourceHub", JoinType.LEFT);
                Predicate source = criteriaBuilder.and(criteriaBuilder.or(hubjoinsource.get("id").in(ids)));

//                Join<Object, Object> hubjoindestination = root.join("destinationHub", JoinType.LEFT);
//                Predicate dis = criteriaBuilder.and(criteriaBuilder.or(hubjoindestination.get("id").in(ids)));


                Join<Object, Object> hubjoinowner = root.join("ownerHub", JoinType.LEFT);
                Predicate own = criteriaBuilder.and(criteriaBuilder.or(hubjoinowner.get("id").in(ids)));

                predicates.add(criteriaBuilder.or(source, own));
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }, pageable);
            return res.map(this::toSelect);
        } else throw BusinessException.entityNotFoundException(EntityType.Hub, "hub.list.not.found");
    }

    private List<Long> findchild(List<HubPermissionDto> hublist, Long parendId) {
        List<Long> list = new ArrayList<>();
        for (HubPermissionDto hubPermissionDto : hublist) {
            if (hubPermissionDto.getParent() == parendId) {
                list.add(hubPermissionDto.getId());
                if (hubPermissionDto.getChildren() != null)
                    list.addAll(findchild(hubPermissionDto.getChildren(), hubPermissionDto.getId()));
            } else
                list.add(hubPermissionDto.getId());
        }
        return list;
    }

    private List<Long> findAllhubid(List<HubPermissionDto> hublist) {
        List<Long> list = new ArrayList<>();
        for (HubPermissionDto hubPermissionDto : hublist) {
            list.add(hubPermissionDto.getId());
            if (hubPermissionDto.getChildren() != null)
                list.addAll(findchild(hubPermissionDto.getChildren(), hubPermissionDto.getId()));
        }
        return list;
    }

    @Override
    public Page<BagDto> filter(BagFilter filter, Pageable pageable) {
        if (filter.getHublist() != null) {
            Page<Bag> res = bagRepository.findAll((Specification<Bag>) (root, query, criteriaBuilder) -> {
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(criteriaBuilder.equal(root.get("isDeleted"), false));

                if (filter.getIsActive() != null)
                    predicates.add(criteriaBuilder.equal(root.get("isActive"), filter.getIsActive()));

                if (StringUtils.hasText(filter.getBagNumber())) {
                    predicates.add(criteriaBuilder.like(root.get("bagNumber"), "%" + filter.getBagNumber().trim() + "%"));
                }
                if (filter.getSelectsourceHub() != null) {
                    predicates.add(criteriaBuilder.equal(root.get("sourceHub").get("id"), filter.getSelectsourceHub().getId()));
                }
                if (filter.getSelectBagType() != null && filter.getSelectBagType().getId() != null) {
                    predicates.add(criteriaBuilder.equal(root.get("bagType"), BagType.findByValue(filter.getSelectBagType().getId())));
                }
                if (filter.getSelectdestinationHub() != null) {
                    predicates.add(criteriaBuilder.equal(root.get("destinationHub").get("id"), filter.getSelectdestinationHub().getId()));
                }
                if (StringUtils.hasText(filter.getConsignmentsDestinationHubName())) {
                    predicates.add(criteriaBuilder.equal(root.get("consignmentsDestinationHub").get("id"), filter.getConsignmentsDestinationHubName()));
                }
                if (StringUtils.hasText(filter.getVehicleNumber0())) {
                    predicates.add(criteriaBuilder.equal(root.get("carrier").get("vehicleNumber0"), filter.getVehicleNumber0()));
                }
                if (StringUtils.hasText(filter.getVehicleNumber1())) {
                    predicates.add(criteriaBuilder.equal(root.get("carrier").get("vehicleNumber1"), filter.getVehicleNumber1()));
                }
                if (StringUtils.hasText(filter.getVehicleNumber2())) {
                    predicates.add(criteriaBuilder.equal(root.get("carrier").get("vehicleNumber2"), filter.getVehicleNumber2()));
                }
                if (StringUtils.hasText(filter.getVehicleNumber3())) {
                    predicates.add(criteriaBuilder.equal(root.get("carrier").get("vehicleNumber3"), filter.getVehicleNumber3()));
                }

                if (filter.getStatus() != null)
                    predicates.add(criteriaBuilder.equal(root.get("status"), BagStatus.findByValue(filter.getStatus().getId())));

                if (filter.getHublist() != null && filter.getSelectsourceHub() == null) {
                    List<Long> ids = findAllhubid(filter.getHublist());

                    if (filter.getSelectTrip() != null)
                        predicates.add(criteriaBuilder.equal(root.get("tripId"), filter.getSelectTrip()));


                    Predicate source = criteriaBuilder.and(root.get("sourceHub").get("id").in(ids));


                    Predicate dis = criteriaBuilder.and(root.get("destinationHub").get("id").in(ids));


                    Predicate own = criteriaBuilder.and(root.get("ownerHub").get("id").in(ids));


                    Predicate cur = criteriaBuilder.and(root.get("currentHub").get("id").in(ids));

                    predicates.add(criteriaBuilder.or(source, own, dis, cur));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }, pageable);
            return res.map(bagConverter::fromModelToDto);
        } else throw BusinessException.entityNotFoundException(EntityType.Hub, "hub.list.not.found");
    }

    private String uniqueBarCodeNum(BagDto request) {
        return "D" + request.getSelectSourceHub().getId() + "D" + request.getSelectDestinationHub().getId() + "M" + request.getSelectConsignmentsDestinationHub().getId() + "T" + System.nanoTime();
    }

    Boolean isExist(String bagNumber) {
        return bagRepository.existsByBagNumberAndIsDeletedFalse(bagNumber);
    }

    @Override
    public BagDto create(BagDto request) {

        log.warn(request.toJson());
        request.setId(null);
//        if (!StringUtils.hasText(request.getBagNumber())) {
//            throw BusinessException.entityNotFoundException(EntityType.Bag, "bag.code.not.add") ;
//        }
        request.setBagNumber(AutoGenerateBarcode());
        Bag bag = bagConverter.fromDtoToModel(request);
        bag.setIsActive(request.getIsActive());
        bag.setIsDeleted(false);
        bag.setStatus(BagStatus.waitingForBagging);

        bag.setCurrentHub(new Hub().setId(request.getSelectSourceHub().getId()));
        if (request.getIsActive() == null) bag.setIsActive(true);
        return saveData(bag);
    }

    private String AutoGenerateBarcode() {
        String bag = bagRepository.findTopOrderByBagNumberDesc();
        if (StringUtils.hasText(bag)) {
            Long LastBagNumber = Long.valueOf(bag) + 1;
            String s = String.valueOf(LastBagNumber);
            if (!s.substring(0, 1).equals("1")) {
                s = "1" + s;
                return s;
            } else {
                return String.valueOf(LastBagNumber);
            }
        } else {
            return "100000000";
        }

    }

    @Override
    public BagDto edit(BagDto dto) {
        Bag bag = findById(dto.getId());
        bagConverter.updateFromDto(dto, bag);
        if (dto.getSelecttrip() == null)
            bag.setTripId(null);
        return saveData(bag);
    }

    private BagDto saveData(Bag bag) {
        if (!StringUtils.hasText(bag.getBagNumber())) {

            if (bag.getCurrentHub() != null) {
                Bag topByCurrentHubOrderByIdDesc = bagRepository.findTopByCurrentHubOrderByIdDesc(bag.getCurrentHub());
                if (topByCurrentHubOrderByIdDesc == null) {

                }
            }
        }
        Bag saved = bagRepository.save(bag);
        return bagConverter.fromModelToDto(saved);
    }

    @Override
    public Bag findById(Long id) {
        if (id == 0) return null;
        return bagRepository.findById(id).orElseThrow(() -> {
            throw BusinessException.entityNotFoundException(EntityType.Bag, "bag.not.found");
        });
    }

    @Override
    public BagDto findByIdDto(Long id) {
        return bagConverter.fromModelToDto(bagRepository.findById(id).orElseThrow());
    }


    @Override
    public void delete(Long id) {
        if (findById(id) != null) bagRepository.logicalDelete(id);
    }

    @Override
    public boolean ExcelValidation(List<BagExcelDto> bagExcelList) {
        int i = 1;
        for (BagExcelDto bagExcelDto : bagExcelList) {
            if (bagRepository.existsByBagNumber(bagExcelDto.getBagNumber())) {
                throw BusinessException.valueException(EntityType.EXCEPTION, "bag.is.duplicate", bagExcelDto.getBagNumber() + "  ردیف " + i);
            }
            if (!hubRepository.existsByCodeAndIsDeletedFalse(bagExcelDto.getSelectSourceHub())) {
                throw BusinessException.valueException(EntityType.EXCEPTION, "hub.code.not.found", bagExcelDto.getSelectSourceHub() + "  ردیف " + i);
            }

            i++;
        }
        return true;

    }

    public SelectResponse HubBagNumbertoSelectResponse(String BagNumber) {
        Hub byCode = hubRepository.findByCode(BagNumber);
        return (new SelectResponse(byCode.getId(), byCode.getCode()));
    }

    @Override
    public List<BagDto> ImportExcel(List<BagExcelDto> bagExcelList) {
        List<BagDto> bagDtos = new ArrayList<>();
        for (BagExcelDto bagExcelDto : bagExcelList) {
            BagDto bagDto = new BagDto();

            bagDto.setBagNumber(bagExcelDto.getBagNumber());

            bagDto.setSelectSourceHub(HubBagNumbertoSelectResponse(bagExcelDto.getSelectSourceHub()));

            BagType bagType = BagType.findByfa(bagExcelDto.getSelectBagType());
            bagDto.setSelectBagType(new SelectResponse(bagType.getValue(), bagType.getFa()));
            bagDtos.add(create(bagDto));
        }


        return bagDtos;
    }

    @Override
    public BagDto findByBagNumber(String number) {
        Bag byBagNumber = bagRepository.findByBagNumber(number);
        return bagConverter.fromModelToDto(byBagNumber);
    }

    @Override
    public BagExceptionsDto createException(BagExceptionsDto dto) {
        return savecreateException(dto);
    }

    @Override
    public List<BagExceptionsDto> listOfException(BagExceptionsDto dto) {
        return bagExceptionsRepository.findAll((Specification<BagExceptions>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (dto.getSelectBag() != null)
                predicates.add(criteriaBuilder.equal(root.get("bag").get("id"), dto.getSelectBag().getId()));
            if (dto.getSelectException() != null)
                predicates.add(criteriaBuilder.equal(root.get("exception").get("id"), dto.getSelectException().getId()));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }).stream().map(bagExceptionsConvertor::fromDtoToModel).collect(Collectors.toList());

    }

    @Override
    public BagDto editStatus(BagDto dto) {

        Bag byId = findById(dto.getId());
        byId.setIsActive(dto.getIsActive());
        Bag save = bagRepository.save(byId);
        return bagConverter.fromModelToDto(save);

    }


    @Override
    public List<SelectResponse> reportTotal(Long hubId) {
        List<String> selectResponses = bagRepository.totalReport(new Hub().setId(hubId));
        List<SelectResponse> list = new ArrayList<>();
        list.add(new SelectResponse(0L, "Bagging"));
        list.add(new SelectResponse(0L, "bagged"));
        list.add(new SelectResponse(0L, "enterHub"));
        list.add(new SelectResponse(0L, "loaded"));
        list.add(new SelectResponse(0L, "waitingForBagging"));
        for (String selectRespons : selectResponses) {
            for (SelectResponse selectResponse : list) {
                if (selectResponse.getText().equals(selectRespons.split(",")[1]))
                    selectResponse.setId(Long.valueOf(selectRespons.split(",")[0]));
            }


//            list.add(new SelectResponse(Long.valueOf(selectRespons.split(",")[0]), selectRespons.split(",")[1]));
        }


        return list;
    }

    @Override
    public List<BagDto> bagList(String bagList) {

        List<Bag> all = bagRepository.findAll((Specification<Bag>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            String[] longs = bagList.split(",");

            predicates.add(criteriaBuilder.and(root.get("id").in(longs)));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
        return all.stream().map(bagConverter::fromModelToDto).collect(Collectors.toList());
    }

    @Override
    public List<BagDto> bagListofList(String[] bagList) {

        List<Bag> all = bagRepository.findAll((Specification<Bag>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();


            predicates.add(criteriaBuilder.and(root.get("id").in(bagList)));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
        return all.stream().map(bagConverter::fromModelToDto).collect(Collectors.toList());
    }

    @Override
    public List<BagDto> listOfBagInHub(Long hubId) {
        List<Bag> all = bagRepository.findAll((Specification<Bag>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.and(root.get("currentHub").in(hubId)));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
        return all.stream().map(bagConverter::fromModelToDto).collect(Collectors.toList());

    }

    @Override
    public List<BagDto> listOfBagInBagList(Long[] bagList) {
        return bagRepository.findAllByIdIn(bagList).stream().map(bagConverter::fromModelToDto).collect(Collectors.toList());
    }

    @Override
    public List<BagDto> bagContradictionList(Long tripId) {
        List<Bag> resultList = new ArrayList<>();
        resultList.addAll(bagRepository.findAllByExtraLoadIsTrueAndExtraLoadInVehicleIdIsNotNullAndTripIdAndIsActiveIsTrueAndIsDeletedIsFalse(tripId));
        resultList.addAll(bagRepository.findAllByLackOfLoadIsTrueAndTripIdAndIsActiveIsTrueAndIsDeletedIsFalse(tripId));
        return resultList.stream().map(bagConverter::fromModelToDto).collect(Collectors.toList());
    }

    public BagExceptionsDto savecreateException(BagExceptionsDto dto) {
        findById(dto.getSelectBag().getId());
        dto.setId(null);
        BagExceptions save = bagExceptionsRepository.save(bagExceptionsConvertor.fromDtoToModel(dto));
        return bagExceptionsConvertor.fromDtoToModel(save);

    }

    @Override
    public SelectResponse toSelect(Bag entity) {
        return new SelectResponse(entity.getId(), entity.selectToString());
    }


}
