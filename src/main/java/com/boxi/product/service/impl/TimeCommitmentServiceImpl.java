package com.boxi.product.service.impl;

import com.boxi.core.errors.BusinessException;
import com.boxi.core.errors.EntityType;
import com.boxi.core.response.SelectResponse;
import com.boxi.product.Enum.TimeUnit;
import com.boxi.product.entity.TimeCommitment;
import com.boxi.product.payload.converter.TimeCommitmentConverter;
import com.boxi.product.payload.dto.TimeCommitmentDto;
import com.boxi.product.payload.request.FilterTimeCommitment;
import com.boxi.product.repo.TimeCommitmentRepository;
import com.boxi.product.service.TimeCommitmentService;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TimeCommitmentServiceImpl implements TimeCommitmentService {

    private final TimeCommitmentRepository timeCommitmentRepository;
    private final TimeCommitmentConverter timeCommitmentConverter;

    public TimeCommitmentServiceImpl(TimeCommitmentRepository timeCommitmentRepository, TimeCommitmentConverter timeCommitmentConverter) {
        this.timeCommitmentRepository = timeCommitmentRepository;
        this.timeCommitmentConverter = timeCommitmentConverter;
    }

    @Override
    public Page<SelectResponse> select(String filter) {
        Pageable pageable = PageRequest.of(0, 100);
        return timeCommitmentRepository.findAll((Specification<TimeCommitment>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("isDeleted"), false));
            predicates.add(criteriaBuilder.equal(root.get("isActive"), true));
            if (filter != null && !filter.isEmpty()) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.like(criteriaBuilder.upper(root.get("name")), "%" + filter.trim() + "%")));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        }, pageable).map(this::toSelect);
    }

    @Override
    public TimeCommitment fromSelect(SelectResponse select) {
        if (select == null) return null;
        return findTimeCommitmentById(select.getId());
    }

    private TimeCommitment findTimeCommitmentById(Long id) {
        return timeCommitmentRepository.findById(id).orElseThrow(() -> {
            throw BusinessException.entityNotFoundException(EntityType.TimeCommitment, "timeCommitment.not.found");
        });
    }

    @Override
    public SelectResponse toSelect(TimeCommitment entity) {
        return new SelectResponse(entity.getId(), entity.selectToString());
    }

    @Override
    public TimeCommitmentDto create(TimeCommitmentDto request) {
        request.setId(null);
        request.setIsDeleted(false);
        TimeCommitment timeCommitment = timeCommitmentConverter.fromDtoToModel(request);
        return savecreate(timeCommitment);
    }

    private TimeCommitmentDto savecreate(TimeCommitment timeCommitment) {
        timeCommitment.setIsDeleted(false);
        TimeCommitment saved = timeCommitmentRepository.save(timeCommitment);
        return timeCommitmentConverter.fromModelToDto(saved);
    }

    private TimeCommitment findById(Long id) {
        if (id == 0) return null;
        return timeCommitmentRepository.findById(id).orElseThrow(() -> {
            throw BusinessException.entityNotFoundException(EntityType.TimeCommitment, "timeCommitment.not.found");
        });
    }

    private Boolean isExist(Long id) {
        return timeCommitmentRepository.existsByid(id);

    }

    @Override
    public TimeCommitmentDto edit(TimeCommitmentDto request) {
        if(! isExist(request.getId()))
            throw BusinessException.entityNotFoundException(EntityType.TimeCommitment, "timeCommitment.not.found");
        return saveData(request);

    }

    private TimeCommitmentDto saveData(TimeCommitmentDto timeCommitment) {
        timeCommitment.setIsDeleted(false);
        TimeCommitment save = timeCommitmentRepository.save(timeCommitmentConverter.fromDtoToModel(timeCommitment));
        return timeCommitmentConverter.fromModelToDto(save);
    }

    @Override
    public Page<TimeCommitmentDto> filter(FilterTimeCommitment filter, Pageable pageable) {
        Page<TimeCommitment> res = timeCommitmentRepository
                .findAll((Specification<TimeCommitment>) (root, query, criteriaBuilder) -> {
                    List<Predicate> predicates = new ArrayList<>();
                    predicates.add(criteriaBuilder.equal(root.get("isDeleted"), false));

                    if (filter.getIsActive() == null)
                        predicates.add(criteriaBuilder.equal(root.get("isActive"), true));
                    else {
                        predicates.add(criteriaBuilder.equal(root.get("isActive"), filter.getIsActive()));
                    }
                    if (filter.getName() != null && StringUtils.isNotBlank(filter.getName())) {
                        predicates.add(criteriaBuilder.like(root.get("name"), "%" + filter.getName().trim() + "%"));
                    }
                    if (filter.getTimeUnit() != null) {
                        predicates.add(criteriaBuilder.equal(root.get("timeUnit"),filter.getTimeUnit().getId() ));

                    }
                    return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
                }, pageable);
        return res.map(timeCommitmentConverter::fromModelToDto);
    }

    @Override
    public void delete(Long id) {
        if (!isExist(id))
            throw BusinessException.entityNotFoundException(EntityType.TimeCommitment, "timeCommitment.not.found");

        timeCommitmentRepository.logicalDelete(id);
    }
}
