package com.boxi.PriceList.service.impl;

import com.boxi.PriceList.Enum.ConsignmentType;
import com.boxi.PriceList.entity.PriceList;
import com.boxi.PriceList.entity.Services;
import com.boxi.PriceList.entity.TermsOfServices;
import com.boxi.PriceList.payload.converter.TermsOfServicesConverter;
import com.boxi.PriceList.payload.dto.ConsignmentInfoDto;
import com.boxi.PriceList.payload.dto.SuggestDetailServiceInfDto;
import com.boxi.PriceList.payload.dto.SuggestionServiceDto;
import com.boxi.PriceList.payload.dto.TermsOfServicesDto;
import com.boxi.PriceList.repo.ServiceRepository;
import com.boxi.PriceList.repo.TermsOfServicesRepository;
import com.boxi.PriceList.service.TermsOfServicesService;
import com.boxi.core.response.SelectResponse;
import com.boxi.hub.entity.CountryDevision;
import com.boxi.hub.repo.CountryDevisionRepository;
import com.boxi.product.Enum.TimeUnit;
import com.boxi.product.entity.Product;
import com.boxi.product.entity.UsingProduct;
import com.boxi.product.repo.UsingProductRepository;
import lombok.extern.slf4j.Slf4j;
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
import java.util.Objects;

@Slf4j
@Service
@Transactional
public class TermsOfServicesServiceImpl implements TermsOfServicesService {


    private final TermsOfServicesRepository termsOfServicesRepository;
    private final TermsOfServicesConverter termsOfServicesConverter;
    private final ServiceRepository ServiceRepository;
    private final UsingProductRepository usingProductRepository;

    private final CountryDevisionRepository countryDevisionRepository;


    public TermsOfServicesServiceImpl(TermsOfServicesRepository termsOfServicesRepository
            , TermsOfServicesConverter termsOfServicesConverter, com.boxi.PriceList.repo.ServiceRepository serviceRepository, UsingProductRepository usingProductRepository, CountryDevisionRepository countryDevisionRepository) {
        this.termsOfServicesRepository = termsOfServicesRepository;
        this.termsOfServicesConverter = termsOfServicesConverter;
        ServiceRepository = serviceRepository;
        this.usingProductRepository = usingProductRepository;
        this.countryDevisionRepository = countryDevisionRepository;
    }

    @Override
    public TermsOfServicesDto create(TermsOfServicesDto dto) {

        return saveCreate(dto);
    }

    @Override
    public TermsOfServicesDto createAsService(Services services) {
        TermsOfServices termsOfServices = termsOfServicesConverter.fromServiceToTerms(services);
        termsOfServicesRepository.save(termsOfServices);

        return termsOfServicesConverter.fromModelToDto(termsOfServices);
    }

    public TermsOfServicesDto saveCreate(TermsOfServicesDto dto) {
        TermsOfServices termsOfServices = termsOfServicesConverter.fromDtoToModel(dto);
        termsOfServices.setId(null);
        return termsOfServicesConverter.fromModelToDto(termsOfServicesRepository.save(termsOfServices));
    }

    @Override
    public TermsOfServicesDto edit(TermsOfServicesDto dto) {
        return saveEdit(dto);
    }

    //سرویس های اصلی
    @Override
    public Page<TermsOfServicesDto> filter(TermsOfServicesDto filter, Pageable pageable) {

        Page<TermsOfServices> termsOfServices = termsOfServicesRepository.findAll((Specification<TermsOfServices>) (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (filter.getContentTypeId() != null && filter.getConsignmentType() != null) {
                Long types = 0L;
                if (filter.getConsignmentType().getId() == 0 && filter.getContentTypeId() == 0) types = 0L;
//                if (filter.getConsignmentType().getId() == 0 && filter.getContentTypeId() == 1) types = 1L; //با توجه به اطلاعات وارد شده در نزخ نامه این خط کامنت شد
                if (filter.getConsignmentType().getId() == 1 && filter.getContentTypeId() == 0) types = 5L;
                if (filter.getConsignmentType().getId() == 1 && filter.getContentTypeId() == 1) types = 5L;
                if (filter.getConsignmentType().getId() == 0 && filter.getContentTypeId() == 1) types = 5L;
                predicates.add(criteriaBuilder.equal(root.get("consignmentType"), ConsignmentType.findByValue(types)));
            }
            if (filter.getId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("id"), filter.getId()));
            }

            if (filter.getServiceType() != null) {
                predicates.add(criteriaBuilder.equal(root.get("serviceType"), filter.getServiceType().getId()));
            }

            if (filter.getServiceValidDateFrom() != null && filter.getServiceValidDateTo() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("serviceValidDateFrom"), filter.getServiceValidDateFrom()));
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("serviceValidDateTo"), filter.getServiceValidDateTo()));


            }


            if (filter.getPriceFormule() != null) {
                predicates.add(criteriaBuilder.equal(root.get("priceFormule"), filter.getPriceFormule()));
            }

            if (filter.getFromWeight() != null && filter.getToWeight() != null) {
                predicates.add(criteriaBuilder.or(criteriaBuilder.between(criteriaBuilder.literal(filter.getFromWeight()), root.get("fromWeight"), root.get("toWeight")), criteriaBuilder.isNull(root.get("fromWeight"))));
                predicates.add(criteriaBuilder.or(criteriaBuilder.between(criteriaBuilder.literal(filter.getToWeight()), root.get("fromWeight"), root.get("toWeight")), criteriaBuilder.isNull(root.get("toWeight"))));
            }

            if (filter.getFromDim() != null && filter.getToDimension() != null) {
                predicates.add(criteriaBuilder.between(criteriaBuilder.literal(filter.getToDimension()), root.get("fromDim"), root.get("toDimension")));
                predicates.add(criteriaBuilder.between(criteriaBuilder.literal(filter.getFromDim()), root.get("fromDim"), root.get("toDimension")));
            }

            if (filter.getFromValue() != null && filter.getToValue() != null) {
                predicates.add(criteriaBuilder.or(criteriaBuilder.between(criteriaBuilder.literal(filter.getFromValue()), root.get("fromValue"), root.get("toValue")), criteriaBuilder.isNull(root.get("fromValue"))));
                predicates.add(criteriaBuilder.or(criteriaBuilder.between(criteriaBuilder.literal(filter.getToValue()), root.get("fromValue"), root.get("toValue")), criteriaBuilder.isNull(root.get("toValue"))));
            }

            if (filter.getFromNumber() != null && filter.getToNumber() != null) {

                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("fromNumber"), filter.getFromNumber()));
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("toNumber"), filter.getToNumber()));
            }

            if (filter.getMinimumOrderQuantity() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("minimumOrderQuantity"), filter.getMinimumOrderQuantity()));
            }

            if (filter.getTimeCommitmentFrom() != null && filter.getTimeCommitmentTo() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("timeCommitmentFrom"), filter.getTimeCommitmentFrom()));
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("timeCommitmentTo"), filter.getTimeCommitmentTo()));
            }

            if (filter.getTimeCommitmentTimeUnit() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("timeCommitmentTimeUnit"), filter.getTimeCommitmentTimeUnit()));
            }
            if (filter.getSelectToCity() != null) {
                predicates.add(criteriaBuilder.equal(root.get("toCity"), filter.getSelectToCity().getId()));
            } else if (filter.getToRegionId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("toCity"), filter.getToRegionId()));
            }

            if (filter.getSelectFromCity() != null) {
                predicates.add(criteriaBuilder.equal(root.get("fromCity"), filter.getSelectFromCity().getId()));
            } else if (filter.getFromRegionId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("fromCity"), filter.getFromRegionId()));
            }


            if (filter.getSelectService() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("service"), filter.getSelectService().getId()));
            }
            if (filter.getSelectParentService() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("parentService"), filter.getSelectParentService().getId()));
            }

            query.distinct(true);

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }, pageable);

        return termsOfServices.map(termsOfServicesConverter::fromModelToDto);
    }

    @Override
    public List<SuggestionServiceDto> suggestionTermOfService(ConsignmentInfoDto filter, Pageable pageable) {
        TermsOfServicesDto termsOfServicesDto = termsOfServicesConverter.fromConsignmentInfoDtoToTermDto(filter);
        Pageable pageables = PageRequest.of(0, 100);
        if (filter.getSelectContentType() != null)
            termsOfServicesDto.setContentTypeId(filter.getSelectContentType().getId());
        if (filter.getSelectConsignmentType() != null)
            termsOfServicesDto.setConsignmentType(filter.getSelectConsignmentType());

        Page<TermsOfServicesDto> suggest = filter(termsOfServicesDto, pageables);
        if (suggest.getTotalElements() == 0) {
            if (filter.getFromRegionId() != null && filter.getToRegionId() != null) {
                termsOfServicesDto.setSelectFromCity(null);
                termsOfServicesDto.setSelectToCity(null);
                termsOfServicesDto.setFromRegionId(filter.getFromRegionId());
                termsOfServicesDto.setToRegionId(filter.getToRegionId());
            }
            suggest = filter(termsOfServicesDto, pageables);
        }

        if (suggest.getTotalElements() == 0) {
            termsOfServicesDto.setSelectFromCity(null);
            termsOfServicesDto.setSelectToCity(null);
            CountryDevision countryDevisionfrom = countryDevisionRepository.findById(filter.getFromCityId()).orElseThrow();
            CountryDevision countryDevisionTo = countryDevisionRepository.findById(filter.getToCityId()).orElseThrow();
            termsOfServicesDto.setFromRegionId(countryDevisionfrom.getParent().getId());
            termsOfServicesDto.setToRegionId(countryDevisionTo.getParent().getId());
            suggest = filter(termsOfServicesDto, pageables);

        }
        List<SuggestionServiceDto> suggestionServiceDtos = new ArrayList<>();

        for (TermsOfServicesDto ofServicesDto : suggest) {
            SuggestionServiceDto suggestionServiceDto = new SuggestionServiceDto();
            suggestionServiceDto.setName(ofServicesDto.getServiceName());
            suggestionServiceDto.setId(ofServicesDto.getSelectService().getId());
            suggestionServiceDto.setPrice(ofServicesDto.getPrice());
            suggestionServiceDto.setServiceType(ofServicesDto.getServiceType().getId());
            suggestionServiceDto.setTimeFrom(ofServicesDto.getTimeCommitmentFrom().toString());
            suggestionServiceDto.setTimeTo(ofServicesDto.getTimeCommitmentTo().toString());
            TimeUnit byValue = TimeUnit.findByValue(ofServicesDto.getTimeCommitmentTimeUnit());
            suggestionServiceDto.setTimeType(new SelectResponse(byValue.getValue(), byValue.getType()));
            suggestionServiceDtos.add(suggestionServiceDto);
        }
        return suggestionServiceDtos;
    }

    @Override
    public List<SuggestionServiceDto> detailsSuggestService(Long id) {
        Services getServiceProduct = ServiceRepository.findById(id).orElseThrow();
        List<SuggestionServiceDto> services = new ArrayList<>();
        List<UsingProduct> all1 = usingProductRepository.findAll((Specification<UsingProduct>) (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            Join<Object, Object> product = root.join("child");
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
                    suggestionServiceDto.setServiceType(0L);


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

    public Boolean checkArrayList(List<SuggestionServiceDto> list, SuggestionServiceDto dto) {

        for (SuggestionServiceDto suggestionServiceDto : list) {
            if (Objects.equals(suggestionServiceDto.getId(), dto.getId())) {
                return false;
            }
        }


        return true;
    }

    public TermsOfServicesDto saveEdit(TermsOfServicesDto dto) {
        TermsOfServices termsOfServices = termsOfServicesConverter.fromDtoToModel(dto);
        return termsOfServicesConverter.fromModelToDto(termsOfServicesRepository.save(termsOfServices));
    }

    @Override
    public void delete(TermsOfServicesDto dto) {

    }
}
