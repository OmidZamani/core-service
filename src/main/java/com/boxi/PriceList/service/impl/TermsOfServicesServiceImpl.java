package com.boxi.PriceList.service.impl;

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

    public TermsOfServicesServiceImpl(TermsOfServicesRepository termsOfServicesRepository
            , TermsOfServicesConverter termsOfServicesConverter, com.boxi.PriceList.repo.ServiceRepository serviceRepository, UsingProductRepository usingProductRepository) {
        this.termsOfServicesRepository = termsOfServicesRepository;
        this.termsOfServicesConverter = termsOfServicesConverter;
        ServiceRepository = serviceRepository;
        this.usingProductRepository = usingProductRepository;
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

    @Override
    public Page<TermsOfServicesDto> filter(TermsOfServicesDto filter, Pageable pageable) {

        Page<TermsOfServices> termsOfServices = termsOfServicesRepository.findAll((Specification<TermsOfServices>) (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();
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

            if (filter.getConsignmentType() != null) {
                predicates.add(criteriaBuilder.equal(root.get("consignmentType"), filter.getConsignmentType().getId()));
            }

            if (filter.getPriceFormule() != null) {
                predicates.add(criteriaBuilder.equal(root.get("priceFormule"), filter.getPriceFormule()));
            }

            if (filter.getFromWeight() != null && filter.getToWeight() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("fromWeight"), filter.getFromWeight()));
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("toWeight"), filter.getToWeight()));
            }

            if (filter.getFromDim() != null && filter.getToDimension() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("fromDim"), filter.getFromDim()));
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("toDimension"), filter.getToDimension()));
            }

            if (filter.getFromValue() != null && filter.getToValue() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("fromValue"), filter.getFromValue()));
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("toValue"), filter.getToValue()));
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
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("toCity"), filter.getSelectToCity().getId()));
            }
            if (filter.getSelectFromCity() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("fromCity"), filter.getSelectFromCity().getId()));
            }

            if (filter.getSelectService() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("service"), filter.getSelectService().getId()));
            }
            if (filter.getSelectParentService() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("parentService"), filter.getSelectParentService().getId()));
            }


            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }, pageable);

        return termsOfServices.map(termsOfServicesConverter::fromModelToDto);


    }

    @Override
    public List<SuggestionServiceDto> suggestionTermOfService(ConsignmentInfoDto filter, Pageable pageable) {
        TermsOfServicesDto termsOfServicesDto = termsOfServicesConverter.fromConsignmentInfoDtoToTermDto(filter);
        Pageable pageables = PageRequest.of(0, 100);
        Page<TermsOfServicesDto> filter1 = filter(termsOfServicesDto, pageables);
        List<SuggestionServiceDto> suggestionServiceDtos = new ArrayList<>();
        for (TermsOfServicesDto ofServicesDto : filter1) {
            SuggestionServiceDto suggestionServiceDto = new SuggestionServiceDto();
            suggestionServiceDto.setName(ofServicesDto.getServiceName());
            suggestionServiceDto.setId(ofServicesDto.getSelectService().getId());
            suggestionServiceDto.setPrice(ofServicesDto.getPrice());
            suggestionServiceDto.setServiceType(ofServicesDto.getServiceType().getId());
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
