package com.boxi.hub.service.impl;

import com.boxi.core.errors.BusinessException;
import com.boxi.core.errors.EntityType;
import com.boxi.core.response.SelectResponse;
import com.boxi.hub.entity.CustomDevisionDetail;
import com.boxi.hub.payload.converter.CustomDevisionDetailConverter;
import com.boxi.hub.payload.dto.CustomDevisionDetailDto;
import com.boxi.hub.payload.request.FilterCustomDevisionDetail;
import com.boxi.hub.repo.CustomDevisionDetailRepository;
import com.boxi.hub.service.CustomDevisionDetailService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CustomDevisionDetailServiceImpl implements CustomDevisionDetailService {

    private final CustomDevisionDetailRepository customDevisionDetailRepository;
    private final CustomDevisionDetailConverter customDevisionDetailConverter;

    public CustomDevisionDetailServiceImpl(CustomDevisionDetailRepository customDevisionDetailRepository,
                                           CustomDevisionDetailConverter customDevisionDetailConverter) {
        this.customDevisionDetailRepository = customDevisionDetailRepository;
        this.customDevisionDetailConverter = customDevisionDetailConverter;
    }

    @Override
    public CustomDevisionDetailDto create(CustomDevisionDetailDto request) {
        request.setId(null);
        request.setIsDeleted(false);
        CustomDevisionDetail customCountryDevision = customDevisionDetailConverter.fromDtoToModel(request);
        CustomDevisionDetail save = customDevisionDetailRepository.save(customCountryDevision);
        return customDevisionDetailConverter.fromModelToDto(save);

    }


    @Override
    public Page<CustomDevisionDetailDto> filter(FilterCustomDevisionDetail filter, Pageable pageable) {
        Page<CustomDevisionDetail> all = customDevisionDetailRepository.findAll((Specification<CustomDevisionDetail>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("isDeleted"), false));
            if (filter.getFromCountryDevision() != null)
                predicates.add(criteriaBuilder.equal(root.get("fromCountryDevision"), filter.getFromCountryDevision()));

            if (filter.getToCountryDevision() != null)
                predicates.add(criteriaBuilder.equal(root.get("toCountryDevision"), filter.getToCountryDevision()));


            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        }, pageable);
        return all.map(customDevisionDetailConverter::fromModelToDto);

    }

    @Override
    public SelectResponse toSelect(CustomDevisionDetail customDevisionDetail) {
        return new SelectResponse(customDevisionDetail.getCustomCountryDevision().getId(), customDevisionDetail.getCustomCountryDevision().getName());
    }

    @Override
    public CustomDevisionDetail fromSelect(SelectResponse select) {
        return null;
    }

    @Override
    public Page<SelectResponse> select(String filter) {
        Pageable pageable = PageRequest.of(0, 100);
        Page<CustomDevisionDetail> all = customDevisionDetailRepository.findAll((Specification<CustomDevisionDetail>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("isActive"), true));
            predicates.add(criteriaBuilder.equal(root.get("isDeleted"), false));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));


        }, pageable);
        return all.map(this::toSelect);
    }

    @Override
    public void delete(Long id) {

        if (customDevisionDetailRepository.findById(id) == null)
            throw BusinessException.entityNotFoundException(EntityType.CustomCountryDevisionDetails, "customCountryDevisionDetails.not.found");
        customDevisionDetailRepository.deleteById(id);

    }

    @Override
    public CustomDevisionDetailDto edit(CustomDevisionDetailDto request) {
        if (customDevisionDetailRepository.findById(request.getId()) == null)
            throw BusinessException.entityNotFoundException(EntityType.CustomCountryDevisionDetails, "customCountryDevisionDetails.not.found");

        request.setIsDeleted(false);
        CustomDevisionDetail customCountryDevision = customDevisionDetailConverter.fromDtoToModel(request);
        CustomDevisionDetail save = customDevisionDetailRepository.save(customCountryDevision);
        return customDevisionDetailConverter.fromModelToDto(save);

    }
}
