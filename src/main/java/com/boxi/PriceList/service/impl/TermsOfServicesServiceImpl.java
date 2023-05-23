package com.boxi.PriceList.service.impl;

import com.boxi.PriceList.entity.Services;
import com.boxi.PriceList.entity.TermsOfServices;
import com.boxi.PriceList.payload.converter.TermsOfServicesConverter;
import com.boxi.PriceList.payload.dto.TermsOfServicesDto;
import com.boxi.PriceList.repo.TermsOfServicesRepository;
import com.boxi.PriceList.service.TermsOfServicesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;

import java.util.List;

@Slf4j
@Service
@Transactional
public class TermsOfServicesServiceImpl implements TermsOfServicesService {


    private final TermsOfServicesRepository termsOfServicesRepository;
    private final TermsOfServicesConverter termsOfServicesConverter;

    public TermsOfServicesServiceImpl(TermsOfServicesRepository termsOfServicesRepository
            , TermsOfServicesConverter termsOfServicesConverter) {
        this.termsOfServicesRepository = termsOfServicesRepository;
        this.termsOfServicesConverter = termsOfServicesConverter;
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

    public TermsOfServicesDto saveEdit(TermsOfServicesDto dto) {
        TermsOfServices termsOfServices = termsOfServicesConverter.fromDtoToModel(dto);
        return termsOfServicesConverter.fromModelToDto(termsOfServicesRepository.save(termsOfServices));
    }

    @Override
    public void delete(TermsOfServicesDto dto) {

    }
}
