package com.boxi.PriceList.service.impl;

import com.boxi.PriceList.entity.PriceDetailDevision;
import com.boxi.PriceList.entity.PriceList;
import com.boxi.PriceList.entity.PriceListDetail;
import com.boxi.PriceList.entity.Services;
import com.boxi.PriceList.payload.dto.*;
import com.boxi.PriceList.repo.PriceListDetailRepository;
import com.boxi.PriceList.repo.PriceListRepository;
import com.boxi.PriceList.repo.ServiceRepository;
import com.boxi.PriceList.service.PriceListService;
import com.boxi.hub.repo.CustomDevisionDetailRepository;
import com.boxi.hub.service.CountryDevisionService;
import com.boxi.product.entity.Product;
import com.boxi.product.entity.ProductAttribute;
import com.boxi.product.entity.ProductAttributeDevision;
import com.boxi.product.entity.UsingProduct;
import com.boxi.product.repo.UsingProductRepository;
import com.boxi.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AvailableServiceSuggestion {

    final ServiceRepository ServiceRepository;
    final CountryDevisionService countryDevisionService;
    final CalculatePriceService calculatePriceService;
    final PriceListService priceListService;
    final PriceListRepository priceListRepository;
    final PriceListDetailRepository priceListDetailRepository;
    final UsingProductRepository usingProductRepository;
    final CustomDevisionDetailRepository customDevisionDetailRepository;


    @Autowired
    public AvailableServiceSuggestion(com.boxi.PriceList.repo.ServiceRepository serviceRepository,
                                      CountryDevisionService countryDevisionService,
                                      CalculatePriceService calculatePriceService,
                                      PriceListService priceListService,
                                      PriceListRepository priceListRepository,
                                      PriceListDetailRepository priceListDetailRepository,
                                      UsingProductRepository usingProductRepository, CustomDevisionDetailRepository customDevisionDetailRepository) {
        ServiceRepository = serviceRepository;
        this.countryDevisionService = countryDevisionService;
        this.calculatePriceService = calculatePriceService;
        this.priceListService = priceListService;
        this.priceListRepository = priceListRepository;
        this.priceListDetailRepository = priceListDetailRepository;
        this.usingProductRepository = usingProductRepository;

        this.customDevisionDetailRepository = customDevisionDetailRepository;
    }


    /*
--   1
   query by is active  , and date between from and to, join ba product and join ba product attribute
   check fromWeight check by consignment weight and other data and then join ba product attribute division
   fromCountryDivision and toCountry just city -> it shows service can declare without price
--  2
   and to get price out put of above must join ba price list and price list ba PRICE LIST DETAIL  IT SHOWS PRICE OF PRODUCT
    */


    public List<SuggestionServiceDto> serviceSuggestionDetails(ConsignmentInfoDto consignmentInfoDto) {


        List<PriceListDetail> all = priceListDetailRepository.findAll((Specification<PriceListDetail>) (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("isActive"), true));
            predicates.add(cb.equal(root.get("isDeleted"), false));
            //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            Join<Object, Object> objectObjectJoin = root.join("priceList");
            Date dayCheck = new Date();
            predicates.add(cb.lessThanOrEqualTo(objectObjectJoin.<Date>get("validDateFrom"), dayCheck));
            predicates.add(cb.greaterThanOrEqualTo(objectObjectJoin.<Date>get("validDateTo"), dayCheck));
            //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            Join<PriceListDetail, PriceDetailDevision> PDivision = root.join("priceDetailDevisions", JoinType.LEFT);

            List<Long> fromList = new ArrayList<>();
            if (consignmentInfoDto.getFromCityId() != null) {
                fromList.add(consignmentInfoDto.getFromCityId());
            }

            if (consignmentInfoDto.getFromRegionId() != null) {
                fromList.add(consignmentInfoDto.getFromRegionId());
            }
            if (consignmentInfoDto.getFromCityId() != null || consignmentInfoDto.getFromRegionId() != null) {
                Predicate ids = cb.and(PDivision.get("fromCountryDevision").get("id").in(fromList));
                Predicate parent = cb.and(PDivision.get("fromCountryDevision").get("parent").in(fromList));
                predicates.add(cb.or(ids, parent));
            }


            List<Long> toList = new ArrayList<>();

            if (consignmentInfoDto.getToCityId() != null) {
                toList.add(consignmentInfoDto.getToCityId());
            }


            if (consignmentInfoDto.getToRegionId() != null) {
                toList.add(consignmentInfoDto.getToRegionId());
            }
            if (consignmentInfoDto.getToRegionId() != null || consignmentInfoDto.getToCityId() != null) {
                Predicate ids = cb.and(PDivision.get("toCountryDevision").get("id").in(toList));
                Predicate parent = cb.and(PDivision.get("toCountryDevision").get("parent").in(toList));


                predicates.add(cb.or(ids, parent));
            }

            Double weight = 0.0;
            if (consignmentInfoDto.getDeclarativeWeight() != null) {
                weight = consignmentInfoDto.getDeclarativeWeight();
            } else {
                weight = consignmentInfoDto.getWeight();
            }
            predicates.add(cb.lessThanOrEqualTo(root.get("fromWeight"), 0));
            predicates.add(cb.greaterThanOrEqualTo(root.get("toWeight"), weight));

            BigDecimal d_value = BigDecimal.ZERO;
            // بازه ارزشی-CHECK BY DECLARATION VALUE چک با ارزش اظهاری
            if (consignmentInfoDto.getDeclarativeValue() != null) {
                d_value = consignmentInfoDto.getDeclarativeValue();
                predicates.add(cb.lessThanOrEqualTo(root.<BigDecimal>get("fromValue"), BigDecimal.valueOf(0)));
                predicates.add(cb.greaterThanOrEqualTo(root.<BigDecimal>get("toValue"), d_value));
            }

            query.distinct(true);
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        });
        List<SuggestionServiceDto> services = new ArrayList<>();
        for (PriceListDetail listDetail : all) {
            SuggestionServiceDto suggestionServiceDto = new SuggestionServiceDto();
            suggestionServiceDto.setPrice(listDetail.getPrice());
            Services byPriceList = ServiceRepository.findTopByPriceListAndProductAndType(listDetail.getPriceList(), listDetail.getProduct(), 0L);
            if (byPriceList != null) {
                suggestionServiceDto.setId(byPriceList.getId());
                suggestionServiceDto.setCode(byPriceList.getCode());
                suggestionServiceDto.setName(byPriceList.getName());
                suggestionServiceDto.setServiceType(byPriceList.getType());
                suggestionServiceDto.setSupplementary(false);
                services.add(suggestionServiceDto);
            }

        }


        return services;
    }

    public Boolean checkArrayList(List<SuggestionServiceDto> list, SuggestionServiceDto dto) {

        for (SuggestionServiceDto suggestionServiceDto : list) {
            if (Objects.equals(suggestionServiceDto.getId(), dto.getId())) {
                return false;
            }
        }


        return true;
    }

    public List<SuggestionServiceDto> serviceDetails(Long serviceId) {
        Services getServiceProduct = ServiceRepository.findById(serviceId).orElseThrow();
        List<SuggestionServiceDto> services = new ArrayList<>();
        List<UsingProduct> all1 = usingProductRepository.findAll((Specification<UsingProduct>) (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            Join<Object, Object> product = root.join("parent");
            predicates.add(cb.equal(product.get("id"), getServiceProduct.getProduct().getId()));
            query.distinct(true);
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        });

        for (UsingProduct usingProduct : all1) {
            List<SuggestDetailServiceInfDto> serviceInfList = ServiceRepository.getsuggestDetails(usingProduct.getChild().getId(), new Date());
            for (SuggestDetailServiceInfDto serviceInfDto : serviceInfList) {
                SuggestionServiceDto suggestionServiceDto = new SuggestionServiceDto();
                suggestionServiceDto.setId(serviceInfDto.getId());


                Services byPriceListAndProduct = ServiceRepository.findTopByPriceListAndProductAndType(new PriceList().setId(serviceInfDto.getPriceListId()), new Product().setId(serviceInfDto.getProductId()), 1L);
                if (byPriceListAndProduct != null) {
                    suggestionServiceDto.setId(byPriceListAndProduct.getId());
                    suggestionServiceDto.setName(byPriceListAndProduct.getName());
                    suggestionServiceDto.setPrice(serviceInfDto.getPrice());
                    suggestionServiceDto.setServiceType(1L);


                    if (checkArrayList(services, suggestionServiceDto))
                        services.add(suggestionServiceDto);
                    else {
                        for (SuggestionServiceDto service : services) {
                            if (!Objects.equals(service.getPrice(), serviceInfDto.getPrice()))
                                suggestionServiceDto.setPrice(serviceInfDto.getPrice());
                        }

                    }
                }
            }

        }

        return services;
    }


    public List<SuggestionServiceDto> serviceSuggestion(ConsignmentInfoDto consignmentInfoDto) { //Deprecated
        List<Services> res = ServiceRepository
                .findAll((Specification<Services>) (root, query, cb) -> {

                    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                    List<Predicate> predicates = new ArrayList<>();
                    predicates.add(cb.equal(root.get("isDeleted"), false));
                    predicates.add(cb.equal(root.get("isActive"), true));

                    Date dayCheck = new Date();
                    if (consignmentInfoDto.getPickUpDate() != null) {
                        dayCheck = DateUtil.convertDateToJalaliDateDto(consignmentInfoDto.getPickUpDate());
                    }
                    predicates.add(cb.lessThanOrEqualTo(root.<Date>get("validDateFrom"), dayCheck));
                    predicates.add(cb.greaterThanOrEqualTo(root.<Date>get("validDateTo"), dayCheck));

                    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                    Join<Services, Product> productJoin = root.join("product", JoinType.LEFT);
                    predicates.add(cb.equal(productJoin.get("isActive"), true));

                    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                    Join<Product, ProductAttribute> productAttrJoin = productJoin.join("productAttributes", JoinType.LEFT);

                    //........................... weight check
                    Double weight = 0.0;
                    if (consignmentInfoDto.getDeclarativeWeight() != null) {
                        weight = consignmentInfoDto.getDeclarativeWeight();
                    } else {
                        weight = consignmentInfoDto.getWeight();
                    }
                    predicates.add(cb.lessThanOrEqualTo(productAttrJoin.get("fromWeight"), 0));
                    predicates.add(cb.greaterThanOrEqualTo(productAttrJoin.get("toWeight"), weight));

                    //...........................dimension check
                    Double dimension = 0.0;
                    if (consignmentInfoDto.getDeclarativeWidth() != null && consignmentInfoDto.getDeclarativeHeight() != null && consignmentInfoDto.getDeclarativeLength() != null) {
                        dimension = consignmentInfoDto.getDeclarativeWidth() * consignmentInfoDto.getDeclarativeHeight() * consignmentInfoDto.getDeclarativeLength();
                        predicates.add(cb.lessThanOrEqualTo(productAttrJoin.get("fromDim"), 0));
                        predicates.add(cb.greaterThanOrEqualTo(productAttrJoin.get("toDimension"), dimension));
                    }

                    //.......................... declare value check
                    BigDecimal d_value = BigDecimal.ZERO;
                    // بازه ارزشی-CHECK BY DECLARATION VALUE چک با ارزش اظهاری
                    if (consignmentInfoDto.getDeclarativeValue() != null) {
                        d_value = consignmentInfoDto.getDeclarativeValue();
                        predicates.add(cb.lessThanOrEqualTo(productAttrJoin.<BigDecimal>get("fromValue"), BigDecimal.valueOf(0)));
                        predicates.add(cb.greaterThanOrEqualTo(productAttrJoin.<BigDecimal>get("toValue"), d_value));
                    }

                    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

                    Join<ProductAttribute, ProductAttributeDevision> ProductAttributeDevisionJoin = productAttrJoin.join("productAttributeDevisions", JoinType.LEFT);
                    if (consignmentInfoDto.getFromStateId() != null) {
                        predicates.add(cb.equal(ProductAttributeDevisionJoin.get("fromCountryDevision").get("id"), consignmentInfoDto.getFromStateId()));
                    }
                    if (consignmentInfoDto.getFromCityId() != null) {
                        predicates.add(cb.equal(ProductAttributeDevisionJoin.get("fromCountryDevision").get("id"), consignmentInfoDto.getFromCityId()));
                    }
                    if (consignmentInfoDto.getFromRegionId() != null) {
                        predicates.add(cb.equal(ProductAttributeDevisionJoin.get("fromCountryDevision").get("id"), consignmentInfoDto.getFromRegionId()));
                    }


                    if (consignmentInfoDto.getToStateId() != null) { //TODO May just region exist
                        predicates.add(cb.equal(ProductAttributeDevisionJoin.get("toCountryDevision").get("id"), consignmentInfoDto.getToStateId()));
                    }
                    if (consignmentInfoDto.getToCityId() != null) {
                        predicates.add(cb.equal(ProductAttributeDevisionJoin.get("toCountryDevision").get("id"), consignmentInfoDto.getToCityId()));
                    }
                    if (consignmentInfoDto.getToRegionId() != null) {
                        predicates.add(cb.equal(ProductAttributeDevisionJoin.get("toCountryDevision").get("id"), consignmentInfoDto.getToRegionId()));
                    }

                    //------------------------PriceList
                    Join<Services, PriceList> priceListJoin = root.join("priceList", JoinType.LEFT);
                    predicates.add(cb.equal(priceListJoin.get("isActive"), true));
                    predicates.add(cb.equal(priceListJoin.get("isDeleted"), false));
                    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                    Join<PriceList, PriceListDetail> priceListDetailsJoin = priceListJoin.join("priceListDetails", JoinType.LEFT);
                    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                    Join<PriceListDetail, PriceDetailDevision> PDivision = priceListDetailsJoin.join("priceDetailDevisions", JoinType.LEFT);

                    if (consignmentInfoDto.getFromStateId() != null) {
                        predicates.add(cb.equal(PDivision.get("fromCountryDevision").get("id"), consignmentInfoDto.getFromStateId()));
                    }
                    if (consignmentInfoDto.getFromCityId() != null) {
                        predicates.add(cb.equal(PDivision.get("fromCountryDevision").get("id"), consignmentInfoDto.getFromCityId()));
                    }
                    if (consignmentInfoDto.getFromRegionId() != null) {
                        predicates.add(cb.equal(PDivision.get("fromCountryDevision").get("id"), consignmentInfoDto.getFromRegionId()));
                    }


                    if (consignmentInfoDto.getToStateId() != null) { //TODO May just region exist
                        predicates.add(cb.equal(PDivision.get("toCountryDevision").get("id"), consignmentInfoDto.getToStateId()));
                    }
                    if (consignmentInfoDto.getToCityId() != null) {
                        predicates.add(cb.equal(PDivision.get("toCountryDevision").get("id"), consignmentInfoDto.getToCityId()));
                    }
                    if (consignmentInfoDto.getToRegionId() != null) {
                        predicates.add(cb.equal(PDivision.get("toCountryDevision").get("id"), consignmentInfoDto.getToRegionId()));
                    }

                    predicates.add(cb.lessThanOrEqualTo(priceListDetailsJoin.get("fromWeight"), 0));
                    predicates.add(cb.greaterThanOrEqualTo(priceListDetailsJoin.get("toWeight"), weight));


                    // بازه ارزشی-CHECK BY DECLARATION VALUE چک با ارزش اظهاری
                    if (consignmentInfoDto.getDeclarativeValue() != null) {
                        d_value = consignmentInfoDto.getDeclarativeValue();
                        predicates.add(cb.lessThanOrEqualTo(priceListDetailsJoin.<BigDecimal>get("fromValue"), BigDecimal.valueOf(0)));
                        predicates.add(cb.greaterThanOrEqualTo(priceListDetailsJoin.<BigDecimal>get("toValue"), d_value));
                    }


                    query.distinct(true);
                    return cb.and(predicates.toArray(new Predicate[predicates.size()]));

                });
        SuggestionServiceDto suggestionServiceDto;
        List<SuggestionServiceDto> services = new ArrayList<>();
        for (Services itr : res) {
            suggestionServiceDto = new SuggestionServiceDto();
            suggestionServiceDto.setId(itr.getId());
            suggestionServiceDto.setCode(itr.getCode());
            suggestionServiceDto.setName(itr.getName());
            suggestionServiceDto.setServiceType(itr.getType());
            Double AllPriceList = (double) 0;

            PriceListDto byId = priceListService.findByIdAndIsActiveTrue(itr.getPriceList().getId());

            for (PriceListDetailDto priceListDetail : byId.getPriceListDetails()) {

                if (checkPriceListDetail(consignmentInfoDto, priceListDetail)) {
                    AllPriceList += priceListDetail.getPrice().doubleValue();
                }
            }


            suggestionServiceDto.setSupplementary(false);
            suggestionServiceDto.setPrice(BigDecimal.valueOf(AllPriceList));
            if (AllPriceList != 0)
                services.add(suggestionServiceDto);
        }
        for (Services itr : res) {
            List<UsingProduct> byParent = usingProductRepository.findByChild(itr.getProduct());
            for (UsingProduct usingProduct : byParent) {
                services.addAll(checkProductInUsing(usingProduct));
            }

        }
        return services.stream().distinct().collect(Collectors.toList());
    }

    private List<SuggestionServiceDto> checkProductInUsing(UsingProduct product) {
        List<Services> res = ServiceRepository
                .findAll((Specification<Services>) (root, query, cb) -> {
                    List<Predicate> predicates = new ArrayList<>();
                    predicates.add(cb.equal(root.get("isDeleted"), false));
                    predicates.add(cb.equal(root.get("isActive"), true));

                    predicates.add(cb.equal(root.get("product").get("id"), product.getParent().getId()));
                    Date dayCheck = new Date();
                    predicates.add(cb.lessThanOrEqualTo(root.<Date>get("validDateFrom"), dayCheck));
                    predicates.add(cb.greaterThanOrEqualTo(root.<Date>get("validDateTo"), dayCheck));
                    return cb.and(predicates.toArray(new Predicate[predicates.size()]));
                });

        List<SuggestionServiceDto> services = new ArrayList<>();
        for (Services re : res) {
            PriceListFilterDto byId = priceListService.findById(re.getPriceList().getId());
            for (PriceListDetailFilterDto priceListDetail : byId.getPriceListDetails()) {
                SuggestionServiceDto suggestionServiceDto = new SuggestionServiceDto();
                suggestionServiceDto.setSupplementary(true);
                suggestionServiceDto.setId(re.getId());
                suggestionServiceDto.setName(re.getName());
                suggestionServiceDto.setCode(re.getCode());
                suggestionServiceDto.setPrice(priceListDetail.getPrice());
                suggestionServiceDto.setServiceType(re.getType());
                services.add(suggestionServiceDto);

            }


        }
        return services;
    }

    private boolean checkPriceListDetail(ConsignmentInfoDto consignmentInfoDto, PriceListDetailDto priceListDetail) {
        Boolean bResult = false;
        if (consignmentInfoDto.getDeclarativeValue() != null)
            if ((priceListDetail.getFromValue().doubleValue() >= consignmentInfoDto.getDeclarativeValue().doubleValue()) &&
                    (priceListDetail.getToValue().doubleValue() <= consignmentInfoDto.getDeclarativeValue().doubleValue()))
                bResult = true;
            else
                bResult = false;

        if (consignmentInfoDto.getWeight() != null)
            if ((priceListDetail.getToWeight().doubleValue() >= consignmentInfoDto.getWeight().doubleValue()) &&
                    (priceListDetail.getToWeight().doubleValue() <= consignmentInfoDto.getWeight().doubleValue()))
                bResult = true;
            else
                bResult = false;

        if (consignmentInfoDto.getDeclarativeWidth() != null) {
            double dimension = consignmentInfoDto.getDeclarativeWidth() * consignmentInfoDto.getDeclarativeHeight() * consignmentInfoDto.getDeclarativeLength();

            if (priceListDetail.getFromDim() != null)
                if (priceListDetail.getFromDim() >= dimension && priceListDetail.getFromDim() <= dimension)
                    bResult = true;
                else
                    bResult = false;
        }

        for (PriceDetailDevisionDto priceDetailDivision : priceListDetail.getPriceDetailDevisions()) {
            if ((priceDetailDivision.getFromCountryDevision().getId() == consignmentInfoDto.getFromCityId()) &&
                    (priceDetailDivision.getToCountryDevision().getId() == consignmentInfoDto.getFromCityId())) {
                bResult = true;
                break;
            } else
                bResult = false;

        }


        return bResult;
    }

    private SuggestionServiceDto mapToSuggestionService(Services in) {
        SuggestionServiceDto out = new SuggestionServiceDto();
        return out.setCode(in.getCode()).setName(in.getName()).setId(in.getId());

    }


    private Join checkProductIsActive(Root<Services> root, CriteriaBuilder
            cb, List<Predicate> predicates) {
        Join<Services, Product> productJoin = root.join("product");
        predicates.add(cb.equal(productJoin.get("isActive"), true));
        return productJoin;
    }

    public List<SuggestionServiceDto> serviceDetailsByConsignment(Long id, ConsignmentInfoDto dto) {
        Services getServiceProduct = ServiceRepository.findById(id).orElseThrow();
        List<SuggestionServiceDto> services = new ArrayList<>();
        List<UsingProduct> all1 = usingProductRepository.findAll((Specification<UsingProduct>) (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            Join<Object, Object> product = root.join("parent");
            predicates.add(cb.equal(product.get("id"), getServiceProduct.getProduct().getId()));
            query.distinct(true);
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        });

        for (UsingProduct usingProduct : all1) {
            List<SuggestDetailServiceInfDto> serviceInfList = ServiceRepository.getsuggestDetails(usingProduct.getChild().getId(), new Date());
            for (SuggestDetailServiceInfDto serviceInfDto : serviceInfList) {
                SuggestionServiceDto suggestionServiceDto = new SuggestionServiceDto();
                suggestionServiceDto.setId(serviceInfDto.getId());


                Services byPriceListAndProduct = ServiceRepository.findTopByPriceListAndProductAndType(new PriceList().setId(serviceInfDto.getPriceListId()), new Product().setId(serviceInfDto.getProductId()), 1L);


                if (byPriceListAndProduct != null) {

                    for (PriceListDetail priceListDetail : byPriceListAndProduct.getPriceList().getPriceListDetails()) {

                        if ((priceListDetail.getFromWeight() >= dto.getWeight() && priceListDetail.getToWeight() <= dto.getWeight())
                                &&
                                (priceListDetail.getFromValue().doubleValue() >= dto.getDeclarativeValue().doubleValue() && priceListDetail.getToValue().doubleValue() <= dto.getDeclarativeValue().doubleValue())

                        ) {
                            suggestionServiceDto.setId(byPriceListAndProduct.getId());
                            suggestionServiceDto.setName(byPriceListAndProduct.getName());
                            suggestionServiceDto.setPrice(serviceInfDto.getPrice());
                            suggestionServiceDto.setServiceType(1L);


                            if (checkArrayList(services, suggestionServiceDto))
                                services.add(suggestionServiceDto);
                            else {
                                for (SuggestionServiceDto service : services) {
                                    if (!Objects.equals(service.getPrice(), serviceInfDto.getPrice()))
                                        suggestionServiceDto.setPrice(serviceInfDto.getPrice());
                                }

                            }
                        }

                    }

                }
            }

        }

        return services;
    }
}
