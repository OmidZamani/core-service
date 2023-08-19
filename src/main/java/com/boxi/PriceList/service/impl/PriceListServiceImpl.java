package com.boxi.PriceList.service.impl;

import com.boxi.PriceList.Enum.ConsignmentType;
import com.boxi.PriceList.entity.PriceDetailDevision;
import com.boxi.PriceList.entity.PriceList;
import com.boxi.PriceList.entity.PriceListDetail;
import com.boxi.PriceList.payload.converter.PriceDetailDevisionConverter;
import com.boxi.PriceList.payload.converter.PriceListConverter;
import com.boxi.PriceList.payload.converter.PriceListDetailConverter;
import com.boxi.PriceList.payload.dto.*;
import com.boxi.PriceList.repo.PriceDetailDevisionRepository;
import com.boxi.PriceList.repo.PriceListDetailRepository;
import com.boxi.PriceList.repo.PriceListRepository;
import com.boxi.PriceList.payload.request.FilterPriceList;
import com.boxi.PriceList.repo.ServiceRepository;
import com.boxi.PriceList.service.PriceListService;
import com.boxi.PriceList.service.ServiceService;
import com.boxi.core.errors.BusinessException;
import com.boxi.core.errors.EntityType;
import com.boxi.core.response.SelectResponse;
import com.boxi.hub.entity.CountryDevision;
import com.boxi.hub.entity.CustomCountryDevision;
import com.boxi.hub.payload.converter.CustomCountryDevisionConverter;
import com.boxi.hub.repo.CountryDevisionRepository;
import com.boxi.hub.repo.CustomCountryDevisionRepository;
import com.boxi.product.entity.Product;
import com.boxi.product.entity.ProductAttribute;
import com.boxi.product.repo.ProductAttributeRepository;
import com.boxi.product.repo.ProductRepository;
import com.boxi.product.response.ContryDevistionSelect;
import com.boxi.utils.DateUtil;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.swing.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
@Slf4j
public class PriceListServiceImpl implements PriceListService {

    private final PriceListRepository priceListRepository;
    private final PriceListConverter priceListConverter;

    private final PriceListDetailRepository priceListDetailRepository;
    private final PriceListDetailConverter priceListDetailConverter;

    private final PriceDetailDevisionConverter priceDetailDevisionConverter;
    private final PriceDetailDevisionRepository priceDetailDevisionRepository;

    private final CustomCountryDevisionRepository customCountryDevisionRepository;
    private final CustomCountryDevisionConverter customCountryDevisionConverter;

    private final CountryDevisionRepository countryDevisionRepository;

    private final ProductRepository productRepository;

    private final ServiceRepository serviceRepository;

    private ProductAttributeRepository productAttributeRepository;

    private final ServiceService serviceService;


    public PriceListServiceImpl(PriceListRepository priceListRepository,
                                PriceListConverter priceListConverter,
                                PriceListDetailRepository priceListDetailRepository,
                                PriceListDetailConverter priceListDetailConverter,
                                PriceDetailDevisionConverter priceDetailDevisionConverter,
                                PriceDetailDevisionRepository priceDetailDevisionRepository,
                                CustomCountryDevisionRepository customCountryDevisionRepository,
                                CustomCountryDevisionConverter customCountryDevisionConverter,
                                CountryDevisionRepository countryDevisionRepository,
                                ProductRepository productRepository,
                                ServiceRepository serviceRepository,
                                ProductAttributeRepository productAttributeRepository, ServiceService serviceService) {
        this.priceListRepository = priceListRepository;
        this.priceListConverter = priceListConverter;
        this.priceListDetailRepository = priceListDetailRepository;
        this.priceListDetailConverter = priceListDetailConverter;
        this.priceDetailDevisionConverter = priceDetailDevisionConverter;
        this.priceDetailDevisionRepository = priceDetailDevisionRepository;
        this.customCountryDevisionRepository = customCountryDevisionRepository;
        this.customCountryDevisionConverter = customCountryDevisionConverter;
        this.countryDevisionRepository = countryDevisionRepository;
        this.productRepository = productRepository;
        this.serviceRepository = serviceRepository;
        this.productAttributeRepository = productAttributeRepository;
        this.serviceService = serviceService;

    }

    private Boolean checkProductAttribute(List<PriceListDetailDto> request) {

        for (PriceListDetailDto priceListDetailDto : request) {
            List<ProductAttribute> all = productAttributeRepository.findAll((Specification<ProductAttribute>) (root, query, criteriaBuilder) -> {
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(criteriaBuilder.between(criteriaBuilder.literal(priceListDetailDto.getToWeight()), root.get("fromWeight"), root.get("toWeight")));
                predicates.add(criteriaBuilder.between(criteriaBuilder.literal(priceListDetailDto.getFromWeight()), root.get("fromWeight"), root.get("toWeight")));


                predicates.add(criteriaBuilder.between(criteriaBuilder.literal(priceListDetailDto.getFromValue()), root.get("fromValue"), root.get("toValue")));
                predicates.add(criteriaBuilder.between(criteriaBuilder.literal(priceListDetailDto.getToValue()), root.get("fromValue"), root.get("toValue")));

                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            });
            if(all.size()==0)
                return false;
        }

        return true;
    }

    public PriceListDto createPriceList(PriceListDto request) {
        if (priceListRepository.existsByPriceListCode(request.getPriceListCode()))
            throw BusinessException.valueException(EntityType.PriceList,
                    "code.exist",
                    request.getPriceListCode());
        if (checkProductAttribute(request.getPriceListDetails())) {
            PriceList priceList = priceListConverter.fromDtoToModel(request);
            priceList.setPriceListDetails(null);
            PriceList save = priceListRepository.save(priceList);
            List<PriceListDetailDto> priceListDetails = new ArrayList<>();
            if (request.getPriceListDetails() != null) {
                for (PriceListDetailDto priceListDetailBase : request.getPriceListDetails()) {
                    PriceListDetail priceListDetail = priceListDetailConverter.fromDtoToModel(priceListDetailBase);
                    priceListDetail.setIsDeleted(false);
                    priceListDetail.setPriceList(save);
                    PriceListDetail detail = priceListDetailRepository.save(priceListDetail);

                    PriceListDetailDto priceListDetailDto = priceListDetailConverter.fromModelToDto(detail);

                    priceListDetail.setCustomCountryDevision(priceListDetail.getCustomCountryDevision());
                    List<PriceDetailDevisionDto> priceDetailDivisions = new ArrayList<>();
                    if (priceListDetail.getPriceDetailDevisions() != null) {
                        for (PriceDetailDevision priceDetailDevision : priceListDetail.getPriceDetailDevisions()) {

                            priceDetailDevision.setIsDeleted(false);

                            priceDetailDevision.setIsActive(true);

                            priceDetailDevision.setPriceListDetail(priceListDetailConverter.fromDtoToModel(priceListDetailDto));

                            PriceDetailDevision save1 = priceDetailDevisionRepository.save(priceDetailDevision);

                            priceDetailDivisions.add(priceDetailDevisionConverter.fromModelToDto(save1));
                        }
                    }
                    priceListDetailDto.setPriceDetailDevisions(priceDetailDivisions);
                    priceListDetails.add(priceListDetailDto);
                }
            }
            PriceListDto priceListDto = priceListConverter.fromModelToDto(save);
            priceListDto.setPriceListDetails(priceListDetails);
            return priceListDto;
        } else
            throw BusinessException.entityNotFoundException(EntityType.PriceList, "pricelist.data.is.not.valid");
    }


    @Override
    public PriceListDto create(PriceListDto request) {
        return createPriceList(request);
    }

    @Override
    public PriceListDto edit(PriceListDto request) {

        if (!priceListRepository.existsById(request.getId()))
            throw BusinessException.valueException(EntityType.PriceList, "price.list.not.exist");

        PriceList priceList = priceListConverter.fromDtoToModel(request);
        priceList.setIsDeleted(false);
        PriceList save = priceListRepository.save(priceList);
        request.setId(save.getId());
        for (PriceListDetailDto priceListDetail : request.getPriceListDetails()) {
            PriceListDetail listDetail = priceListDetailConverter.fromDtoToModel(priceListDetail);
            listDetail.setPriceDetailDevisions(null);
            listDetail.setIsActive(true);
            listDetail.setIsDeleted(false);
            listDetail.setPriceList(priceList);
            PriceListDetail save1 = priceListDetailRepository.save(listDetail);

            if (priceListDetail.getPriceDetailDevisions() != null && priceListDetail.getPriceDetailDevisions().size() != 0) {
                priceDetailDevisionRepository.deleteByPriceListDetail(save1);
                for (PriceDetailDevisionDto priceDetailDivision : priceListDetail.getPriceDetailDevisions()) {
                    PriceDetailDevision priceDetailDivision1 = priceDetailDevisionConverter.fromDtoToModel(priceDetailDivision);
                    priceDetailDivision1.setIsActive(true);
                    priceDetailDivision1.setIsDeleted(false);
                    if (priceDetailDivision.getFromCountryDevision() != null && priceDetailDivision.getToCountryDevision() != null) {
                        priceDetailDivision1.setFromCountryDevision(new CountryDevision().setId(priceDetailDivision.getFromCountryDevision().getId()));
                        priceDetailDivision1.setToCountryDevision(new CountryDevision().setId(priceDetailDivision.getToCountryDevision().getId()));
                        priceDetailDivision1.setPriceListDetail(save1);
                        log.warn(priceDetailDivision1.getPriceListDetail().getId().toString());
                        if (priceDetailDivision1.getFromCountryDevision() != null)
                            priceDetailDevisionRepository.save(priceDetailDivision1);
                    }
                }
            }
        }
        return request;
    }

    @Override
    public PriceListDto findById(Long id) {
        PriceList priceList = priceListRepository.findById(id).orElseThrow();
        return priceListConverter.fromModelToDto(priceList);
    }

    @Override
    public Page<PriceListFilterDto> filter(FilterPriceList filter, Pageable pageable) {

        Page<PriceList> res = priceListRepository
                .findAll((Specification<PriceList>) (root, query, criteriaBuilder) -> {
                    List<Predicate> predicates = new ArrayList<>();
                    predicates.add(criteriaBuilder.equal(root.get("isDeleted"), false));
                    if (filter.getIsActive() != null)
                        predicates.add(criteriaBuilder.equal(root.get("isActive"), filter.getIsActive()));

                    if (filter.getCode() != null && StringUtils.isNotBlank(filter.getCode())) {
                        predicates.add(criteriaBuilder.like(root.get("priceListCode"), "%" + filter.getCode().trim() + "%"));
                    }

                    if (filter.getName() != null && StringUtils.isNotBlank(filter.getName())) {
                        predicates.add(criteriaBuilder.like(root.get("priceListName"), "%" + filter.getName().trim() + "%"));
                    }
                    if (filter.getIsActive() != null)
                        predicates.add(criteriaBuilder.equal(root.get("isActive"), filter.getIsActive()));

                    if (filter.getPriceListDate() != null)
                        predicates.add(criteriaBuilder.equal(root.get("priceListDate"), DateUtil.convertDateToJalaliDateDto(filter.getPriceListDate())));


                    if (filter.getValidDateTo() != null)
                        predicates.add(criteriaBuilder.equal(root.get("validDateTo"), DateUtil.convertJalaliDayTimeToTimeStamp(filter.getValidDateTo())));

                    if (filter.getPriceListDetails() != null) {
                        if (filter.getPriceListDetails().getProduct() != null) {
                            Join<PriceListDetail, PriceList> joinSales = root.join("priceListDetails", JoinType.LEFT);
                            predicates.add(criteriaBuilder.equal(joinSales.get("product"), filter.getPriceListDetails().getProduct().getId()));
                        }
                    }
                    if (filter.getPriceListDetails() != null) {
                        Join<PriceListDetail, PriceList> joinSales = root.join("priceListDetails", JoinType.LEFT);
                        if (filter.getPriceListDetails().getConsignmentType() != null) {
                            predicates.add(criteriaBuilder.equal(joinSales.get("consignmentType"), filter.getPriceListDetails().getConsignmentType().getId()));
                        }
                        if (filter.getPriceListDetails().getPriceDetailDevisions() != null) {
                            Join<PriceListDetail, PriceList> division = joinSales.join("priceDetailDevisions", JoinType.LEFT);
                            predicates.add(criteriaBuilder.equal(division.get("fromCountryDevision"), filter.getPriceListDetails().getPriceDetailDevisions().get(0).getFromCountryDevision().getId()));
                            predicates.add(criteriaBuilder.equal(division.get("toCountryDevision"), filter.getPriceListDetails().getPriceDetailDevisions().get(0).getToCountryDevision().getId()));

                        }
                    }

                    query.orderBy(criteriaBuilder.desc(root.get("id")));
                    query.distinct(true);
                    return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));

                }, pageable);

        return res.map(priceListConverter::fromFilterMap);

    }

    @Override
    public PriceListDetailDto SelectEdit(String filter) {

//        Pageable pageable = PageRequest.of(0, 10);
//        Page<PriceListDetail> priceListDetails = priceListDetailRepository.findAll((Specification<PriceListDetail>) (root, query, criteriaBuilder) -> {
//            List<Predicate> predicates = new ArrayList<>();
//            predicates.add(criteriaBuilder.equal(root.get("Id"), filter));
//
//            return criteriaBuilder.and(predicates.toArray((new Predicate[predicates.size()])));
//
//        }, pageable);

//TODO
        return null;
    }

    @Override
    public List<SelectResponse> consignmentType() {

        return ConsignmentType.select();
    }

    @Override
    public List<PriceListFilterDto> SE(String filter) {
        List<PriceList> all = priceListRepository.findAll();
        Stream<PriceListDto> priceListDtoStream = all.stream().map(priceListConverter::fromModelToDto);
        return priceListDtoStream.map(priceListConverter::fromDtoSelectToModel).collect(Collectors.toList());
    }

    @Override
    public void Delete(Long Id) {

        PriceList priceList = priceListRepository.findById(Id).orElseThrow();
        if (serviceRepository.existsByPriceList(priceList))
            throw BusinessException.valueException(EntityType.PriceList, "pricelist.delete.relation.on.service");
        else {
            List<PriceListDetail> byPriceList = priceListDetailRepository.findByPriceList(new PriceList().setId(Id));
            for (PriceListDetail listDetail : byPriceList) {
                priceDetailDevisionRepository.deleteByPriceListDetail(listDetail);
            }
            for (PriceListDetail listDetail : byPriceList)
                priceListDetailRepository.deleteById(listDetail.getId());

            priceListRepository.deleteById(Id);
        }

    }

    @Override
    public void DeletePriceListDetails(Long Id) {


        PriceListDetail listDetail = priceListDetailRepository.findById(Id).orElseThrow(() -> {
            throw BusinessException.valueException(EntityType.PriceListDetails, "price.list.details.not.exist");
        });
        priceDetailDevisionRepository.deleteByPriceListDetail(listDetail);
        priceListDetailRepository.deleteById(Id);
    }

    @Override
    public List<SelectResponse> SelectList() {
        List<SelectResponse> selectResponses = new ArrayList<>();
        List<PriceList> all = priceListRepository.findAll((Specification<PriceList>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("isDeleted"), false));
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));

        });
        for (PriceList priceList : all) {
            selectResponses.add(new SelectResponse(priceList.getId(), priceList.getPriceListName()));
        }

        return selectResponses;
    }

    @Override
    public boolean ExcelValidation(List<PriceListExcelDto> priceListExcelList) {

        int i = 1;
        for (PriceListExcelDto priceListExcelDto : priceListExcelList) {
            if (priceListRepository.existsByPriceListCode(priceListExcelDto.getCode()))
                throw BusinessException.valueException(EntityType.PriceList,
                        "code.exist",
                        priceListExcelDto.getCode() + "  ردیف " + i);

            int B = 1;
            for (PriceListDetailExcelDto priceListDetail : priceListExcelDto.getPriceListDetails()) {

                if (!productRepository.existsByCodeAndIsDeletedFalse(priceListDetail.getProduct()))
                    throw BusinessException.valueException(EntityType.PriceList,
                            "code.not.exist",
                            priceListDetail.getProduct() + " priceListDetails " + "  ردیف " + B);


                if (!countryDevisionRepository.existsByCode(priceListDetail.getPriceDetailDevisionsto()))
                    throw BusinessException.valueException(EntityType.PriceList,
                            "country.not.exist",
                            priceListDetail.getProduct() + " priceListDetails " + "  ردیف " + B);


                if (!countryDevisionRepository.existsByCode(priceListDetail.getPriceDetailDevisionsfrom()))
                    throw BusinessException.valueException(EntityType.PriceList,
                            "country.not.exist",
                            priceListDetail.getProduct() + " priceListDetails " + "  ردیف " + B);


                B++;
            }


            i++;

        }


        return true;
    }

    @Override
    public List<PriceListDto> ImportExcel(List<PriceListExcelDto> priceListExcelList) {
        List<PriceListDto> priceListList = new ArrayList<>();
        for (PriceListExcelDto priceListExcelDto : priceListExcelList) {
            PriceListDto priceListDto = priceListConverter.fromExcelToDto(priceListExcelDto);
            List<PriceListDetailDto> list = new ArrayList<>();

            for (PriceListDetailExcelDto priceListDetail : priceListExcelDto.getPriceListDetails()) {
                PriceListDetailDto priceListDetailDto = priceListDetailConverter.fromExcelToDto(priceListDetail);
                ConsignmentType byFa = ConsignmentType.findByFa(priceListDetail.getConsignmentType());
                priceListDetailDto.setConsignmentType(new SelectResponse(byFa.getValue(), byFa.getType()));

                CustomCountryDevision byIdCode = customCountryDevisionRepository.findByCode(priceListDetail.getCustomDevision());
                priceListDetailDto.setCustomDevision(customCountryDevisionConverter.fromModelToDto(byIdCode));
                priceListDetailDto.setIsDeleted(false);
                priceListDetailDto.setIsParametric(priceListDetail.getIsParametric());
                Product byCode = productRepository.findByCode(priceListDetail.getProduct());
                priceListDetailDto.setProduct(new SelectResponse(byCode.getId(), byCode.getName()));

                List<PriceDetailDevisionDto> priceDetailDevisions = new ArrayList<>();
                for (String s : priceListDetail.getPriceDetailDevisionsfrom().split(" - ")) {
                    PriceDetailDevision priceDetailDevision = new PriceDetailDevision();
                    CountryDevision byCode1 = countryDevisionRepository.findByCode(s);


                    priceDetailDevision.setFromCountryDevision(byCode1);
                    priceDetailDevisions.add(priceDetailDevisionConverter.fromModelToDto(priceDetailDevision));
                }
                int i = 0;
                for (String s : priceListDetail.getPriceDetailDevisionsto().split(" - ")) {
                    PriceDetailDevision priceDetailDevision = new PriceDetailDevision();
                    CountryDevision byCode1 = countryDevisionRepository.findByCode(s);
                    priceDetailDevision.setToCountryDevision(byCode1);

                    if (priceDetailDevisions.size() < i)
                        priceDetailDevisions.add(priceDetailDevisionConverter.fromModelToDto(priceDetailDevision));
                    else {
                        ContryDevistionSelect contryDevistionSelect = new ContryDevistionSelect();
                        contryDevistionSelect.setId(byCode1.getId());
                        contryDevistionSelect.setText(byCode1.getName());
                        contryDevistionSelect.setCountryType(new SelectResponse(byCode1.getCountryType().getValue(), byCode1.getCountryType().getFa()));
                        priceDetailDevisions.get(i).setToCountryDevision(contryDevistionSelect);
                    }


                    i++;
                }
                priceListDetailDto.setPriceDetailDevisions(priceDetailDevisions);
                list.add(priceListDetailDto);
            }

            priceListDto.setPriceListDetails(list);
            priceListDto.setPriceListDetails(list);
            priceListDto.setIsDeleted(false);
            priceListList.add(create(priceListDto));


        }

        return priceListList;
    }

    @Override
    public List<PriceListSuggestDto> check(FilterPriceList filter, Pageable pageable) {

        Page<PriceList> res = priceListRepository
                .findAll((Specification<PriceList>) (root, query, criteriaBuilder) -> {
                    List<Predicate> predicates = new ArrayList<>();
                    predicates.add(criteriaBuilder.equal(root.get("isDeleted"), false));
                    predicates.add(criteriaBuilder.equal(root.get("isActive"), true));
                    if (filter.getCode() != null && StringUtils.isNotBlank(filter.getCode())) {
                        predicates.add(criteriaBuilder.like(root.get("priceListCode"), "%" + filter.getCode().trim() + "%"));
                    }

                    if (filter.getName() != null && StringUtils.isNotBlank(filter.getName())) {
                        predicates.add(criteriaBuilder.like(root.get("priceListName"), "%" + filter.getName().trim() + "%"));
                    }

                    if (filter.getPriceListDate() != null)
                        predicates.add(criteriaBuilder.equal(root.get("priceListDate"), DateUtil.convertDateToJalaliDateDto(filter.getPriceListDate())));


                    if (filter.getValidDateTo() != null)
                        predicates.add(criteriaBuilder.equal(root.get("validDateTo"), DateUtil.convertJalaliDayTimeToTimeStamp(filter.getValidDateTo())));

                    if (filter.getPriceListDetails() != null) {
                        Join<PriceListDetail, PriceList> priceListDetails = root.join("priceListDetails", JoinType.LEFT);
                        if (filter.getPriceListDetails().getConsignmentType() != null) {
                            predicates.add(criteriaBuilder.equal(priceListDetails.get("consignmentType"), filter.getPriceListDetails().getConsignmentType().getId()));
                        }

                        if ((filter.getPriceListDetails().getFromDim() != null) && (filter.getPriceListDetails().getToDimension() != null)) {
                            predicates.add(criteriaBuilder.between(criteriaBuilder.literal(filter.getPriceListDetails().getFromDim()),
                                    priceListDetails.get("fromDim"),
                                    priceListDetails.get("toDimension")));
                        }

                        if ((filter.getPriceListDetails().getFromWeight() != null) && (filter.getPriceListDetails().getToWeight() != null)) {
                            predicates.add(criteriaBuilder.between(criteriaBuilder.literal(filter.getPriceListDetails().getFromWeight()),
                                    priceListDetails.get("fromWeight"),
                                    priceListDetails.get("toWeight")));
                        }

                        if ((filter.getPriceListDetails().getFromValue() != null) && (filter.getPriceListDetails().getToValue() != null)) {
                            predicates.add(criteriaBuilder.between(criteriaBuilder.literal(filter.getPriceListDetails().getFromValue()),
                                    priceListDetails.<BigDecimal>get("fromValue"),
                                    priceListDetails.<BigDecimal>get("toValue")));
                        }
                        if (filter.getPriceListDetails().getPriceDetailDevisions() != null) {
                            Join<Object, Object> division = root.join("priceDetailDevisions");
                            predicates.add(criteriaBuilder.equal(division.get(""),
                                    filter.getPriceListDetails().getPriceDetailDevisions().get(0).getFromCountryDevision().getId()));
                        }


                    }


                    query.orderBy(criteriaBuilder.desc(root.get("id")));
                    query.distinct(true);
                    return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));

                }, pageable);
        List<PriceListSuggestDto> priceListFilterList = new ArrayList<>();
        for (PriceList re : res) {
            ServiceDto serviceDto = serviceService.findByPriceList(priceListConverter.fromModelToDto(re));
            if (serviceDto != null) {
                PriceListSuggestDto priceListSuggestDto = new PriceListSuggestDto();
                priceListSuggestDto.setPrice(re.getPriceListDetails().get(0).getPrice());
                priceListSuggestDto.setServiceid(serviceDto.getId());
                priceListSuggestDto.setServicename(serviceDto.getName());
                priceListSuggestDto.setPricelistid(serviceDto.getPriceList().getId());
                priceListSuggestDto.setServiceDescription(serviceDto.getDescription());
                priceListFilterList.add(priceListSuggestDto);
            }
        }

        return priceListFilterList;
    }

    @Override
    public PriceListDto findByIdAndIsActiveTrue(Long id) {

        PriceList byIdAndIsActiveIsTrueAndIsDeletedIsFalse = priceListRepository.findByIdAndIsActiveIsTrueAndIsDeletedIsFalse(id);
        return priceListConverter.fromModelToDto(byIdAndIsActiveIsTrueAndIsDeletedIsFalse);
    }

    @Override
    public List<PriceListDetailDto> findByIdAndIsActiveTrueByDetails(Long id, ConsignmentInfoDto dto) {
        List<PriceListDetail> all = priceListDetailRepository.findAll((Specification<PriceListDetail>) (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(cb.equal(root.get("priceList").get("id"), id));
            Join<PriceListDetail, PriceDetailDevision> PDivision = root.join("priceDetailDevisions", JoinType.LEFT);

            if (dto.getFromStateId() != null) {
                predicates.add(cb.equal(PDivision.get("fromCountryDevision").get("id"), dto.getFromStateId()));
            }
            if (dto.getFromCityId() != null) {
                predicates.add(cb.equal(PDivision.get("fromCountryDevision").get("id"), dto.getFromCityId()));
            }
            if (dto.getFromRegionId() != null) {
                predicates.add(cb.equal(PDivision.get("fromCountryDevision").get("id"), dto.getFromRegionId()));
            }


            if (dto.getToStateId() != null) {
                predicates.add(cb.equal(PDivision.get("toCountryDevision").get("id"), dto.getToStateId()));
            }
            if (dto.getToCityId() != null) {
                predicates.add(cb.equal(PDivision.get("toCountryDevision").get("id"), dto.getToCityId()));
            }
            if (dto.getToRegionId() != null) {
                predicates.add(cb.equal(PDivision.get("toCountryDevision").get("id"), dto.getToRegionId()));
            }

            predicates.add(cb.lessThanOrEqualTo(root.get("fromWeight"), 0));
            predicates.add(cb.greaterThanOrEqualTo(root.get("toWeight"), dto.getWeight()));


            // بازه ارزشی-CHECK BY DECLARATION VALUE چک با ارزش اظهاری
            if (dto.getDeclarativeValue() != null) {
                BigDecimal d_value = dto.getDeclarativeValue();
                predicates.add(cb.lessThanOrEqualTo(root.<BigDecimal>get("fromValue"), BigDecimal.valueOf(0)));
                predicates.add(cb.greaterThanOrEqualTo(root.<BigDecimal>get("toValue"), d_value));
            }

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        });
        return all.stream().map(priceListDetailConverter::fromModelToDto).collect(Collectors.toList());

    }

    @Override
    public Iterable<PriceList> findAll() {
        return priceListRepository.findAll();
    }


}
