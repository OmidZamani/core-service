package com.boxi.crm.service.impl;

import com.boxi.core.errors.BusinessException;
import com.boxi.core.errors.EntityType;
import com.boxi.core.request.GenericFilter;
import com.boxi.core.response.SelectResponse;
import com.boxi.crm.entity.SalesChannel;
import com.boxi.crm.payload.converter.SalesChannelConverter;
import com.boxi.crm.payload.dto.SalesChannelDto;
import com.boxi.crm.payload.request.CustomerSegmentFilter;
import com.boxi.crm.repo.SalesChannelRepository;
import com.boxi.crm.service.SalesChannelService;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SalesChannelServiceImpl implements SalesChannelService {
    private final SalesChannelRepository salesChannelRepository;
    private final SalesChannelConverter salesChannelConverter;

    public SalesChannelServiceImpl(SalesChannelRepository salesChannelRepository, SalesChannelConverter salesChannelConverter) {
        this.salesChannelRepository = salesChannelRepository;
        this.salesChannelConverter = salesChannelConverter;
    }


    @Override
    public List<SelectResponse> SelectChannel(String filter) {
        List<SalesChannel> all = salesChannelRepository.findAll((Specification<SalesChannel>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("isDeleted"), false));
            predicates.add(criteriaBuilder.equal(root.get("isActive"), true));
            if (filter != null && StringUtils.isNotBlank(filter))
                predicates.add(criteriaBuilder.equal(root.get("name"), "%" + filter + "%"));

            return criteriaBuilder.and(predicates.toArray(predicates.toArray(new Predicate[0])));
        });
        List<SelectResponse> selectResponses = new ArrayList<>();
        for (SalesChannel salesChannel : all) {
            selectResponses.add(new SelectResponse(salesChannel.getId(), salesChannel.getName()));
        }

        return selectResponses;
    }

    public Boolean isExists(String code){
        return salesChannelRepository.existsByCodeAndIsDeletedFalse(code);
    }

    @Override
    public SalesChannelDto create(SalesChannelDto dto) {

        if (isExists(dto.getCode()))
            throw BusinessException.valueException(EntityType.CUSEOMERSEGMENT, "Sales.Channel.is.duplicate");

        dto.setIsDeleted(false);
        dto.setId(null);
        SalesChannel save = salesChannelRepository.save(salesChannelConverter.fromDtoToModel(dto));
        return salesChannelConverter.fromModelToDto(save);
    }

    @Override
    public SalesChannelDto edit(SalesChannelDto dto) {
        dto.setIsDeleted(false);
        SalesChannel save = salesChannelRepository.save(salesChannelConverter.fromDtoToModel(dto));
        return salesChannelConverter.fromModelToDto(save);
    }

    @Override
    public Page<SalesChannelDto> filter(GenericFilter filter, Pageable pageable) {
        Page<SalesChannel> all = salesChannelRepository.findAll((Specification<SalesChannel>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            Predicate isDeleted = criteriaBuilder.equal(root.get("isDeleted"), false);
            if (filter.getSearch() != null && StringUtils.isNotBlank(filter.getSearch())) {
                Predicate code = criteriaBuilder.like(root.get("code"), "%" + filter.getSearch().trim() + "%");
                Predicate name = criteriaBuilder.like(root.get("name"), "%" + filter.getSearch().trim() + "%");
                predicates.add(criteriaBuilder.or(name, code));
            }
            if (filter.getIsActive() != null)
                predicates.add(criteriaBuilder.equal(root.get("isActive"), filter.getIsActive()));

            predicates.add(criteriaBuilder.and(isDeleted));
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        }, pageable);
        return all.map(salesChannelConverter::fromModelToDto);
    }

    @Override
    public void delete(Long id) {
        salesChannelRepository.deleteById(id);
    }
}
