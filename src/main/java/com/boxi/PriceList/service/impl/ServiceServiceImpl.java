package com.boxi.PriceList.service.impl;

import com.boxi.PriceList.Enum.ConsignmentType;
import com.boxi.PriceList.Enum.ServiceType;
import com.boxi.PriceList.entity.*;
import com.boxi.PriceList.payload.converter.PriceListConverter;
import com.boxi.PriceList.payload.converter.ServiceConvertor;
import com.boxi.PriceList.payload.converter.TermsOfServicesConverter;
import com.boxi.PriceList.payload.dto.PriceListDto;
import com.boxi.PriceList.payload.dto.ServiceDto;
import com.boxi.PriceList.payload.dto.ServiceNameWithCodeDto;
import com.boxi.PriceList.payload.dto.TermsOfServicesDto;
import com.boxi.PriceList.payload.request.FilterService;
import com.boxi.PriceList.repo.*;
import com.boxi.PriceList.service.ServiceService;
import com.boxi.core.errors.BusinessException;
import com.boxi.core.errors.EntityType;
import com.boxi.core.response.SelectResponse;
import com.boxi.excel.payload.CreateServiceExcelRequest;
import com.boxi.hub.entity.CountryDevision;
import com.boxi.hub.entity.CustomDevisionDetail;
import com.boxi.hub.repo.CustomCountryDevisionRepository;
import com.boxi.product.entity.Product;
import com.boxi.product.entity.ProductAttribute;
import com.boxi.product.repo.ProductAttributeRepository;
import com.boxi.product.repo.ProductRepository;
import com.boxi.utils.DateUtil;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.SerializationUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.lang.Exception;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class ServiceServiceImpl implements ServiceService {

    private final ServiceRepository serviceRepository;
    private final ServiceConvertor serviceConvertor;

    private final PriceListRepository priceListRepository;
    private final PriceListConverter priceListConverter;

    private final ProductRepository productRepository;
    private final ProductAttributeRepository productAttributeRepository;


    private final ServiceDeliveryCustomersRepository serviceDeliveryCustomersRepository;

    private final TermsOfServicesRepository termsOfServicesRepository;
    private final TermsOfServicesConverter termsOfServicesConverter;

    private final PriceDetailDevisionRepository priceDetailDevisionRepository;

    private final CustomCountryDevisionRepository customCountryDevisionRepository;
    private final PriceListDetailRepository priceListDetailRepository;


    public ServiceServiceImpl(ServiceRepository serviceRepository
            , ServiceConvertor serviceConvertor
            , PriceListRepository priceListRepository
            , PriceListConverter priceListConverter
            , ProductRepository productRepository
            , ProductAttributeRepository productAttributeRepository
            , ServiceDeliveryCustomersRepository serviceDeliveryCustomersRepository
            , TermsOfServicesRepository termsOfServicesRepository
            , TermsOfServicesConverter termsOfServicesConverter
            , PriceDetailDevisionRepository priceDetailDevisionRepository, CustomCountryDevisionRepository customCountryDevisionRepository, PriceListDetailRepository priceListDetailRepository) {
        this.serviceRepository = serviceRepository;
        this.serviceConvertor = serviceConvertor;
        this.priceListRepository = priceListRepository;
        this.priceListConverter = priceListConverter;
        this.productRepository = productRepository;
        this.productAttributeRepository = productAttributeRepository;

        this.serviceDeliveryCustomersRepository = serviceDeliveryCustomersRepository;
        this.termsOfServicesRepository = termsOfServicesRepository;

        this.termsOfServicesConverter = termsOfServicesConverter;
        this.priceDetailDevisionRepository = priceDetailDevisionRepository;
        this.customCountryDevisionRepository = customCountryDevisionRepository;
        this.priceListDetailRepository = priceListDetailRepository;
    }

    public void createServiceInTermOfService(Services services) {

//        First Delete All service in termsOfServices then add service
        termsOfServicesRepository.deleteByService(services);
        PriceList priceList = priceListRepository.findById(services.getPriceList().getId()).orElseThrow();
        for (PriceListDetail priceListDetail : priceList.getPriceListDetails()) {
            if (services.getProduct().getId() == priceListDetail.getProduct().getId()) {
//          Find TimeCommitment  as Product Attribute
                if (priceListDetail.getCustomCountryDevision() != null)
                    for (CustomDevisionDetail customDevisionDetailD : priceListDetail.getCustomCountryDevision().getCustomDevisionDetails()) {

                        List<ProductAttribute> all = findAllProductAttributeByCountryDevision(priceListDetail, services, customDevisionDetailD);
                        if (all.size() != 0) {
                            for (ProductAttribute productAttributes : all) {

                                if (productAttributes.getProduct().getId() == services.getProduct().getId()) {
                                    TermsOfServices termsOfServices = mapServiceToTermOfService(services, priceListDetail, productAttributes);

                                    if (customDevisionDetailD != null) {
                                        termsOfServices.setServiceType(ServiceType.findByValue(services.getType()));
                                        TermsOfServicesDto termsOfServicesDto = termsOfServicesConverter.fromModelToDto(termsOfServices);
                                        TermsOfServices terms = termsOfServicesConverter.fromDtoToModel(termsOfServicesDto);
                                        terms.setService(services);
                                        terms.setFromCity(customDevisionDetailD.getFromCountryDevision());
                                        terms.setToCity(customDevisionDetailD.getToCountryDevision());
                                        terms.setId(null);
                                        terms.setIsActive(true);
                                        terms.setServiceDescription(services.getDescription());
                                        if (!termsOfServicesRepository.existsTermsOfServicesByServiceAndFromCityAndToCityAndFromValueAndToValueAndFromWeightAndToWeightAndFromDimAndToDimensionAndTimeCommitmentFromAndTimeCommitmentToAndTimeCommitmentTimeUnitAndFromNumberAndToNumber(
                                                services, terms.getFromCity(), terms.getToCity(), terms.getFromValue(), terms.getToValue(), terms.getFromWeight(), terms.getToWeight(), terms.getFromDim(), terms.getToDimension(),
                                                terms.getTimeCommitmentFrom(), terms.getTimeCommitmentTo(), terms.getTimeCommitmentTimeUnit(), terms.getFromNumber(), terms.getToNumber()
                                        ))
                                            termsOfServicesRepository.save(terms);
                                    }
                                    if (priceListDetail.getPriceDetailDevisions() != null) {

                                        for (PriceDetailDevision priceDetailDevision : priceDetailDevisionRepository.findAllByPriceListDetail(priceListDetail)) {
                                            termsOfServices.setServiceType(ServiceType.findByValue(services.getType()));
                                            TermsOfServicesDto termsOfServicesDto = termsOfServicesConverter.fromModelToDto(termsOfServices);
                                            TermsOfServices terms = termsOfServicesConverter.fromDtoToModel(termsOfServicesDto);
                                            terms.setService(services);
                                            terms.setFromCity(priceDetailDevision.getFromCountryDevision());
                                            terms.setToCity(priceDetailDevision.getToCountryDevision());
                                            terms.setId(null);
                                            terms.setIsActive(true);
                                            terms.setServiceDescription(services.getDescription());
                                            if (!termsOfServicesRepository.existsTermsOfServicesByServiceAndFromCityAndToCityAndFromValueAndToValueAndFromWeightAndToWeightAndFromDimAndToDimensionAndTimeCommitmentFromAndTimeCommitmentToAndTimeCommitmentTimeUnitAndFromNumberAndToNumber(
                                                    services, terms.getFromCity(), terms.getToCity(), terms.getFromValue(), terms.getToValue(), terms.getFromWeight(), terms.getToWeight(), terms.getFromDim(), terms.getToDimension(),
                                                    terms.getTimeCommitmentFrom(), terms.getTimeCommitmentTo(), terms.getTimeCommitmentTimeUnit(), terms.getFromNumber(), terms.getToNumber()
                                            ))
                                                termsOfServicesRepository.save(terms);
                                        }

                                    }

//                        else {
//                            termsOfServices.setServiceType(ServiceType.findByValue(services.getType()));
//                            termsOfServices.setId(null);
//                            termsOfServices.setIsActive(true);
//                            termsOfServices.setServiceDescription(services.getDescription());
//                            termsOfServicesRepository.save(termsOfServices);
//                        }

                                }
                            }
                        }
                    }
                else {
                    List<ProductAttribute> all = findAllProductAttribute(priceListDetail, services);
                    if (all.size() != 0) {
                        for (ProductAttribute productAttributes : all) {

                            if (productAttributes.getProduct().getId() == services.getProduct().getId()) {
                                TermsOfServices termsOfServices = mapServiceToTermOfService(services, priceListDetail, productAttributes);
                                if (priceListDetail.getPriceDetailDevisions() != null) {

                                    for (PriceDetailDevision priceDetailDevision : priceDetailDevisionRepository.findAllByPriceListDetail(priceListDetail)) {
                                        termsOfServices.setServiceType(ServiceType.findByValue(services.getType()));
                                        Object clone = SerializationUtils.clone(termsOfServices);
                                        TermsOfServicesDto termsOfServicesDto = termsOfServicesConverter.fromModelToDto(termsOfServices);
                                        TermsOfServices terms = termsOfServicesConverter.fromDtoToModel(termsOfServicesDto);
                                        terms.setService(services);

                                        terms.setFromCity(priceDetailDevision.getFromCountryDevision());
                                        terms.setToCity(priceDetailDevision.getToCountryDevision());
                                        terms.setId(null);
                                        terms.setIsActive(true);
                                        terms.setServiceDescription(services.getDescription());
                                        if (!termsOfServicesRepository.existsTermsOfServicesByServiceAndFromCityAndToCityAndFromValueAndToValueAndFromWeightAndToWeightAndFromDimAndToDimensionAndTimeCommitmentFromAndTimeCommitmentToAndTimeCommitmentTimeUnitAndFromNumberAndToNumber(
                                                services, terms.getFromCity(), terms.getToCity(), terms.getFromValue(), terms.getToValue(), terms.getFromWeight(), terms.getToWeight(), terms.getFromDim(), terms.getToDimension(),
                                                terms.getTimeCommitmentFrom(), terms.getTimeCommitmentTo(), terms.getTimeCommitmentTimeUnit(), terms.getFromNumber(), terms.getToNumber()
                                        ))
                                            termsOfServicesRepository.save(terms);
                                    }

                                }

                            }


                        }

                    }

                }
            }
        }
    }

    private TermsOfServices mapServiceToTermOfService(Services services, PriceListDetail priceListDetail, ProductAttribute productAttributes) {
        TermsOfServices termsOfServices = new TermsOfServices();

        termsOfServices.setServiceName(services.getName());
        termsOfServices.setService(services);
        if (priceListDetail.getConsignmentType() != null)
            termsOfServices.setConsignmentType(ConsignmentType.findByValue(priceListDetail.getConsignmentType()));

        termsOfServices.setServiceValidDateFrom(services.getValidDateFrom());
        termsOfServices.setServiceValidDateTo(services.getValidDateTo());
        termsOfServices.setPrice(priceListDetail.getPrice());


        termsOfServices.setTimeCommitmentFrom(productAttributes.getTimeCommitment().getFrom());
        termsOfServices.setTimeCommitmentTo(productAttributes.getTimeCommitment().getTo());
        termsOfServices.setTimeCommitmentTimeUnit(productAttributes.getTimeCommitment().getTimeUnit().getValue());
        termsOfServices.setTimeCommitmentId(productAttributes.getTimeCommitment().getId());

        termsOfServices.setMinimumOrderQuantity(services.getMinimumOrderQuantity());

        termsOfServices.setFromWeight(priceListDetail.getFromWeight());
        termsOfServices.setToWeight(priceListDetail.getToWeight());

        termsOfServices.setFromDim(priceListDetail.getFromDim());
        termsOfServices.setToDimension(priceListDetail.getToDimension());

        termsOfServices.setFromValue(priceListDetail.getFromValue());
        termsOfServices.setToValue(priceListDetail.getToValue());

        termsOfServices.setFromNumber(priceListDetail.getFromNumber());
        termsOfServices.setToNumber(priceListDetail.getToNumber());
        return termsOfServices;
    }

    private List<ProductAttribute> findAllProductAttribute(PriceListDetail priceListDetail, Services services) {
        return productAttributeRepository.findAll((Specification<ProductAttribute>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("product"), services.getProduct()));

            predicates.add(criteriaBuilder.equal(root.get("product").get("isDeleted"), false));
            predicates.add(criteriaBuilder.equal(root.get("product").get("isActive"), true));


            if (priceListDetail.getFromDim() != null & priceListDetail.getToDimension() != null) {

                predicates.add(criteriaBuilder.between(criteriaBuilder.literal(priceListDetail.getFromDim()), root.get("fromDim"), root.get("toDimension")));
                predicates.add(criteriaBuilder.between(criteriaBuilder.literal(priceListDetail.getToDimension()), root.get("fromDim"), root.get("toDimension")));
            }
            if (priceListDetail.getFromWeight() != null && priceListDetail.getToWeight() != null) {
                predicates.add(criteriaBuilder.between(criteriaBuilder.literal(priceListDetail.getFromWeight()), root.get("fromWeight"), root.get("toWeight")));
                predicates.add(criteriaBuilder.between(criteriaBuilder.literal(priceListDetail.getToWeight()), root.get("fromWeight"), root.get("toWeight")));
            }

            if (priceListDetail.getFromValue() != null & priceListDetail.getToValue() != null) {
                predicates.add(criteriaBuilder.between(criteriaBuilder.literal(priceListDetail.getFromValue()), root.get("fromValue"), root.get("toValue")));
                predicates.add(criteriaBuilder.between(criteriaBuilder.literal(priceListDetail.getToValue()), root.get("fromValue"), root.get("toValue")));

            }
            if (priceListDetail.getCustomCountryDevision() != null) {
                Join<Object, Object> productAttributeDevisions = root.join("productAttributeDevisions", JoinType.INNER);
                for (CustomDevisionDetail customDevisionDetail : priceListDetail.getCustomCountryDevision().getCustomDevisionDetails()) {
                    Predicate fromCountryDevision = criteriaBuilder.equal(productAttributeDevisions.get("fromCountryDevision"), customDevisionDetail.getFromCountryDevision());
                    Predicate toCountryDevision = criteriaBuilder.equal(productAttributeDevisions.get("toCountryDevision"), customDevisionDetail.getToCountryDevision());
                    predicates.add(criteriaBuilder.and(fromCountryDevision, toCountryDevision));
                }
            } else if (priceListDetail.getPriceDetailDevisions() != null) {
                List<CountryDevision> To = priceListDetail.getPriceDetailDevisions().stream().map(PriceDetailDevision::getFromCountryDevision).collect(Collectors.toList());
                List<CountryDevision> From = priceListDetail.getPriceDetailDevisions().stream().map(PriceDetailDevision::getToCountryDevision).collect(Collectors.toList());

                for (CountryDevision countryDevision : To) {
                    log.warn("Dev TO -> " + countryDevision.getName() + " - " + countryDevision.getId());
                }
                for (CountryDevision countryDevision : From) {
                    log.warn("Dev From -> " + countryDevision.getName() + " - " + countryDevision.getId());
                }
                Join<Object, Object> productAttributeDevisions = root.join("productAttributeDevisions", JoinType.INNER);
                predicates.add(criteriaBuilder.and(productAttributeDevisions.get("fromCountryDevision").in(To)));
                predicates.add(criteriaBuilder.and(productAttributeDevisions.get("toCountryDevision").in(From)));
            }

            query.distinct(true);
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        });

    }

    private List<ProductAttribute> findAllProductAttributeByCountryDevision(PriceListDetail priceListDetail, Services services, CustomDevisionDetail customDevisionDetailD) {
        return productAttributeRepository.findAll((Specification<ProductAttribute>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("product"), services.getProduct()));

            predicates.add(criteriaBuilder.equal(root.get("product").get("isDeleted"), false));
            predicates.add(criteriaBuilder.equal(root.get("product").get("isActive"), true));


            if (priceListDetail.getFromDim() != null & priceListDetail.getToDimension() != null) {

                predicates.add(criteriaBuilder.between(criteriaBuilder.literal(priceListDetail.getFromDim()), root.get("fromDim"), root.get("toDimension")));
                predicates.add(criteriaBuilder.between(criteriaBuilder.literal(priceListDetail.getToDimension()), root.get("fromDim"), root.get("toDimension")));
            }
            if (priceListDetail.getFromWeight() != null && priceListDetail.getToWeight() != null) {
                predicates.add(criteriaBuilder.between(criteriaBuilder.literal(priceListDetail.getFromWeight()), root.get("fromWeight"), root.get("toWeight")));
                predicates.add(criteriaBuilder.between(criteriaBuilder.literal(priceListDetail.getToWeight()), root.get("fromWeight"), root.get("toWeight")));
            }

            if (priceListDetail.getFromValue() != null & priceListDetail.getToValue() != null) {
                predicates.add(criteriaBuilder.between(criteriaBuilder.literal(priceListDetail.getFromValue()), root.get("fromValue"), root.get("toValue")));
                predicates.add(criteriaBuilder.between(criteriaBuilder.literal(priceListDetail.getToValue()), root.get("fromValue"), root.get("toValue")));

            }
            if (priceListDetail.getCustomCountryDevision() != null) {
                Join<Object, Object> productAttributeDevisions = root.join("productAttributeDevisions", JoinType.INNER);

                Predicate fromCountryDevision = criteriaBuilder.equal(productAttributeDevisions.get("fromCountryDevision"), customDevisionDetailD.getFromCountryDevision());
                Predicate toCountryDevision = criteriaBuilder.equal(productAttributeDevisions.get("toCountryDevision"), customDevisionDetailD.getToCountryDevision());
                predicates.add(criteriaBuilder.and(fromCountryDevision, toCountryDevision));

            } else if (priceListDetail.getPriceDetailDevisions() != null) {
                List<CountryDevision> To = priceListDetail.getPriceDetailDevisions().stream().map(PriceDetailDevision::getFromCountryDevision).collect(Collectors.toList());
                List<CountryDevision> From = priceListDetail.getPriceDetailDevisions().stream().map(PriceDetailDevision::getToCountryDevision).collect(Collectors.toList());

                for (CountryDevision countryDevision : To) {
                    log.warn("Dev TO -> " + countryDevision.getName() + " - " + countryDevision.getId());
                }
                for (CountryDevision countryDevision : From) {
                    log.warn("Dev From -> " + countryDevision.getName() + " - " + countryDevision.getId());
                }
                Join<Object, Object> productAttributeDevisions = root.join("productAttributeDevisions", JoinType.INNER);
                predicates.add(criteriaBuilder.and(productAttributeDevisions.get("fromCountryDevision").in(To)));
                predicates.add(criteriaBuilder.and(productAttributeDevisions.get("toCountryDevision").in(From)));
            }

            query.distinct(true);
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        });

    }


    public ServiceDto createService(ServiceDto request) {
        request.setCode(genServiceCode());
        if (serviceRepository.existsByCode(request.getCode())) {
            throw BusinessException.valueException(EntityType.Service, "service.delivery.code.duplicate");
        }

        PriceList priceList = priceListRepository.findById(request.getPriceList().getId()).orElseThrow(() -> {
            throw BusinessException.valueException(EntityType.Service, "price.list.code.not.exist");
        });
        PriceListDto priceListDto = priceListConverter.fromModelToDto(priceList);

        Date serviceFromDate = DateUtil.convertDateToJalaliDateDto(request.getValidDateFrom());
        Date serviceToDate = DateUtil.convertDateToJalaliDateDto(request.getValidDateTo());

        Date priceListToDate = DateUtil.convertDateToJalaliDateDto(priceListDto.getValidDateTo());

        Date priceListFromDate = DateUtil.convertDateToJalaliDateDto(priceListDto.getValidDateFrom());

        if (serviceFromDate.compareTo(serviceToDate) > 0) {
            throw BusinessException.valueException(EntityType.Service, "price.list.fromdate.notvalid");
        }

        if (serviceFromDate.compareTo(priceListFromDate) < 0) {
            throw BusinessException.valueException(EntityType.Service, "price.list.fromdate.notvalid");
        }


        if (serviceToDate.compareTo(priceListToDate) > 0) {
            throw BusinessException.valueException(EntityType.Service, "price.list.fromdate.notvalid");
        }

        request.setId(null);
        request.setIsDeleted(false);

        Services save = serviceRepository.save(serviceConvertor.fromDtoToModel(request));


        request.setId(save.getId());

//        Add Service to TermOfService
        createServiceInTermOfService(save);
        return request;
    }

    private String genServiceCode() {

        Long aLong = serviceRepository.maxServiceId();
        return "S00" + aLong.toString();
    }

    @Override
    public ServiceDto create(ServiceDto request) {
        return createService(request);
    }

    @Override
    public Page<ServiceDto> filter(FilterService filter, Pageable pageable) {

        Page<Services> all = serviceRepository.findAll((Specification<Services>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(criteriaBuilder.equal(root.get("isDeleted"), false));
            if (filter.getIsActive() != null)
                predicates.add(criteriaBuilder.equal(root.get("isActive"), filter.getIsActive()));

            if (filter.getCode() != null && StringUtils.isNotBlank(filter.getCode()))
                predicates.add(criteriaBuilder.like(root.get("code"), "%" + filter.getCode().trim() + "%"));

            if (filter.getName() != null && StringUtils.isNotBlank(filter.getName()))
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + filter.getName().trim() + "%"));

            if (filter.getProduct() != null) {
                predicates.add(criteriaBuilder.equal(root.get("product"), filter.getProduct().getId()));
            }

            if (filter.getType() != null)
                predicates.add(criteriaBuilder.equal(root.get("type"), filter.getType().getId()));

            if (filter.getPriceList() != null)
                predicates.add(criteriaBuilder.equal(root.get("priceList"), filter.getPriceList().getId()));


            if (StringUtils.isNotBlank(filter.getDescription()))
                predicates.add(criteriaBuilder.like(root.get("description"), filter.getDescription()));

            query.orderBy(criteriaBuilder.desc(root.get("Id")));

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));

        }, pageable);


        return all.map(serviceConvertor::fromModelToDto);

    }

    @Override
    public ServiceDto SelectEdit(String filter) {
        return null;
    }

    @Override
    public void delete(Long id) {
        serviceRepository.deleteById(id);
        termsOfServicesRepository.deleteByService(new Services().setId(id));
    }

    @Override
    public void deleteDetails(Long id) {
        serviceDeliveryCustomersRepository.deleteById(id);
    }

    @Override
    public ServiceDto edit(ServiceDto request) {
        if (!serviceRepository.existsById(request.getId()))
            throw BusinessException.valueException(EntityType.Service, "service.not.exist");
        request.setIsDeleted(false);
        Services save = serviceRepository.save(serviceConvertor.fromDtoToModel(request));
        createServiceInTermOfService(save);
        return serviceConvertor.fromModelToDto(save);
    }

    @Override
    public List<ServiceDto> ImportExcel(List<CreateServiceExcelRequest> createServiceExcelRequests) {
        List<ServiceDto> serviceList = new ArrayList<>();

        for (CreateServiceExcelRequest createServiceExcelRequest : createServiceExcelRequests) {

            if (createServiceExcelRequest.getCode() != null) {
                ServiceDto serviceDto = serviceConvertor.fromExcelToDto(createServiceExcelRequest);

                PriceList byPriceListName = priceListRepository.findByPriceListCode(serviceDto.getPriceList().getText());
                serviceDto.setPriceList(new SelectResponse(byPriceListName.getId(), byPriceListName.getPriceListName()));

                Product byCode = productRepository.findByCode(serviceDto.getProduct().getText());
                serviceDto.setProduct(new SelectResponse(byCode.getId(), byCode.getName()));
                serviceDto.setIsDeleted(false);

                Services save = serviceRepository.save(serviceConvertor.fromDtoToModel(serviceDto));
                ServiceDto serviceDto1 = serviceConvertor.fromModelToDto(save);
                serviceDto1.setProduct(serviceDto.getProduct());
                serviceDto1.setPriceList(serviceDto.getPriceList());


                serviceList.add(serviceDto1);

            }


        }
        return serviceList;

    }

    @Override
    public boolean ExcelValidation(List<CreateServiceExcelRequest> createServiceExcelRequests) {
        int i = 1;
        for (CreateServiceExcelRequest createServiceExcelRequest : createServiceExcelRequests) {
            if (createServiceExcelRequest.getCode() != null) {
                if (serviceRepository.existsByCode(createServiceExcelRequest.getCode())) {

                    throw BusinessException.valueException(EntityType.Service,
                            "service.code.not.exist", createServiceExcelRequest.getCode() + "  ردیف " + i);

                }
                Product byCode = productRepository.findByCode(createServiceExcelRequest.getProduct());
                if (byCode == null) {
                    throw BusinessException.valueException(EntityType.Service,
                            "product.code.not.exist", createServiceExcelRequest.getProduct() + "  ردیف " + i);
                }
                PriceList byPriceListCode = priceListRepository.findByPriceListCode(createServiceExcelRequest.getPriceList());

                if (byPriceListCode == null) {
                    throw BusinessException.valueException(EntityType.Service,
                            "price.list.code.not.exist", createServiceExcelRequest.getProduct() + "  ردیف  " + i);
                }
            }
            i++;
        }

        return true;
    }

    @Override
    public List<SelectResponse> select(String filter) {
        List<Services> all = serviceRepository.findAll((Specification<Services>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.isNotBlank(filter))
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + filter.trim() + "%"));


            predicates.add(criteriaBuilder.equal(root.get("isDeleted"), false));
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        });
        List<SelectResponse> selectResponses = new ArrayList<>();
        for (Services services : all) {
            selectResponses.add(new SelectResponse(services.getId(), services.getName()));
        }
        return selectResponses;

    }

    @Override
    public ServiceDto findByPriceList(PriceListDto dto) {
        Services byPriceList = serviceRepository.findByPriceList(priceListConverter.fromDtoToModel(dto));
        return serviceConvertor.fromModelToDto(byPriceList);
    }

    public List<SelectResponse> baseTypeSelect() {
        List<Services> servicesList = serviceRepository.findByTypeAndIsActiveIsTrue(ServiceType.BASE.getValue());
        return servicesList.stream().map(this::toSelect).collect(Collectors.toList());
    }

    public List<SelectResponse> additionalTypeSelect() {
        List<Services> servicesList = serviceRepository.findByTypeAndIsActiveIsTrue(ServiceType.ADDITIONAL.getValue());
        return servicesList.stream().map(this::toSelect).collect(Collectors.toList());
    }

    @Override
    public SelectResponse toSelect(Services services) {
        return new SelectResponse(services.getId(), services.getName());
    }

    @Override
    public ServiceDto findById(Long id) {

        try {
            if (id == 22) {
                return serviceConvertor.fromModelToDto(serviceRepository.findById(22L).orElseThrow());
            } else if (id == 21) {
                return serviceConvertor.fromModelToDto(serviceRepository.findById(21L).orElseThrow());
            } else if (id == 823 || id == 825) {
                return serviceConvertor.fromModelToDto(serviceRepository.findById(229L).orElseThrow());
            } else
                return serviceConvertor.fromModelToDto(serviceRepository.findById(id).orElseThrow());
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public List<ServiceNameWithCodeDto> serviceNameWithCode() {
        return serviceRepository.findByIsDeletedFalseAndIsActiveIsTrue();
    }

    @Override
    public BigDecimal findByDefaultServicePrice(Long id) {

        if (id == 21)
            return priceListDetailRepository.findById(5L).orElseThrow().getPrice();
        if (id == 22)
            return priceListDetailRepository.findById(6L).orElseThrow().getPrice();
        else
            return priceListDetailRepository.findById(id).orElseThrow().getPrice();
    }

}
