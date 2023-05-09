package com.boxi.transport.service.impl;

import com.boxi.core.errors.BusinessException;
import com.boxi.core.errors.EntityType;
import com.boxi.core.request.GenericFilter;
import com.boxi.core.response.SelectResponse;
import com.boxi.transport.entity.VehicleCategory;
import com.boxi.transport.payload.converter.VehicleCategoryConverter;
import com.boxi.transport.payload.dto.VehicleCategoryDto;
import com.boxi.transport.repo.VehicleCategoryRepository;
import com.boxi.transport.service.VehicleCategoryService;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
public class VehicleCategoryServiceImpl implements VehicleCategoryService {
    private final VehicleCategoryRepository vehicleCategoryRepository;
    private final VehicleCategoryConverter vehicleCategoryConverter;

    @Autowired
    public VehicleCategoryServiceImpl(VehicleCategoryRepository vehicleCategoryRepository,VehicleCategoryConverter vehicleCategoryConverter) {
        this.vehicleCategoryRepository = vehicleCategoryRepository;
        this.vehicleCategoryConverter = vehicleCategoryConverter;
    }

    @Override
    public VehicleCategoryDto createVehicleCategory(VehicleCategoryDto request) {
        VehicleCategory entity = vehicleCategoryConverter.fromDtoToModel(request);
        return saveCatData(entity);
    }

    @Override
    public VehicleCategoryDto editVehicleCategory(VehicleCategoryDto request) {
        VehicleCategory cat = vehicleCategoryRepository.findById(request.getId()).orElseThrow(() -> {
            throw BusinessException.valueException(EntityType.VehicleCategory, "vehicle.category.not.found");
        });
        BeanUtils.copyProperties(request,cat);
        return saveCatData(cat);
    }



    private VehicleCategoryDto saveCatData(VehicleCategory cat){
        VehicleCategory saved = vehicleCategoryRepository.save(cat);
        return vehicleCategoryConverter.fromModelToDto(saved);
    }
    @Override
    public VehicleCategory findById(Long id) {
        if(id==0) return null;
        return vehicleCategoryRepository.findById(id).orElseThrow(() -> {
            throw BusinessException.entityNotFoundException(EntityType.VehicleCategory, "vehicle.category.not.found");
        });
    }

    @Override
    public void delete(Long id) {
        VehicleCategory cat = findById(id);
        cat.setIsDeleted(true);
        vehicleCategoryRepository.save(cat);
    }

    @Override
    public Page<VehicleCategoryDto> filter(GenericFilter filter, Pageable pageable) {
        Page<VehicleCategory> res = vehicleCategoryRepository
                .findAll((Specification<VehicleCategory>) (root, query, criteriaBuilder) -> {
                    List<Predicate> predicates = new ArrayList<>();
                    Predicate isDeleted = criteriaBuilder.equal(root.get("isDeleted"), false);
                    if (filter.getSearch() != null && StringUtils.isNotBlank(filter.getSearch())) {
                        Predicate code = criteriaBuilder.like(root.get("code"), "%" + filter.getSearch().trim() + "%");
                        Predicate name = criteriaBuilder.like(root.get("name"), "%" + filter.getSearch().trim() + "%");
                        predicates.add(criteriaBuilder.or(name, code));
                    }
                    predicates.add(criteriaBuilder.and(isDeleted));
                    return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
                }, pageable);
        return res.map(vehicleCategoryConverter::fromModelToDto);

    }

    @Override
    public Page<SelectResponse> select(String filter) {
         Pageable pageable = PageRequest.of(0, 100);
        return vehicleCategoryRepository.findAll((Specification<VehicleCategory>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (filter != null && !filter.isEmpty()) {
                Predicate name = criteriaBuilder.and(criteriaBuilder.like(criteriaBuilder.upper(root.get("name")), "%" + filter.trim() + "%"));
                Predicate code = criteriaBuilder.and(criteriaBuilder.like(criteriaBuilder.upper(root.get("code")), "%" + filter.trim() + "%"));
                predicates.add(criteriaBuilder.or(name, code));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        }, pageable).map(this::toSelect);
    }

    @Override
    public VehicleCategory fromSelect(SelectResponse select) {
        if(select==null) return null;
        return findById(select.getId());
    }

    @Override
    public SelectResponse toSelect(VehicleCategory entity) {
        return new SelectResponse(entity.getId(),entity.selectToString());
    }

}
