package com.boxi.transport.service.impl;

import com.boxi.core.errors.BusinessException;
import com.boxi.core.errors.EntityType;
import com.boxi.core.response.SelectResponse;
import com.boxi.hub.entity.Hub;
import com.boxi.hub.payload.dto.HubPermissionDto;
import com.boxi.hub.repo.HubRepository;
import com.boxi.transport.entity.Gate;
import com.boxi.transport.payload.converter.GateConverter;
import com.boxi.transport.payload.dto.GateDto;
import com.boxi.transport.payload.dto.GateExcelDto;
import com.boxi.transport.payload.dto.GateFilter;
import com.boxi.transport.payload.request.HubFilter;
import com.boxi.transport.repo.GateRepository;
import com.boxi.transport.service.GateService;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class GateServiceImpl implements GateService {

    private final GateRepository gateRepository;
    private final GateConverter gateConverter;
    private final HubRepository hubRepository;


    @Autowired
    public GateServiceImpl(GateRepository gateRepository, GateConverter gateConverter, HubRepository hubRepository) {
        this.gateRepository = gateRepository;
        this.gateConverter = gateConverter;
        this.hubRepository = hubRepository;
    }

    @Override
    public Page<SelectResponse> select(String filter, HubFilter hubFilter) {
        Pageable pageable = PageRequest.of(0, 100);
        if (hubFilter.getHublist() != null) {
            return gateRepository.findAll((Specification<Gate>) (root, query, criteriaBuilder) -> {
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(criteriaBuilder.equal(root.get("isDeleted"), false));

                predicates.add(criteriaBuilder.equal(root.get("isActive"), true));
                if (filter != null && !filter.isEmpty()) {
                    Predicate name = criteriaBuilder.and(criteriaBuilder.like(criteriaBuilder.upper(root.get("name")), "%" + filter.trim() + "%"));
                    Predicate code = criteriaBuilder.and(criteriaBuilder.like(criteriaBuilder.upper(root.get("code")), "%" + filter.trim() + "%"));
                    predicates.add(criteriaBuilder.or(name, code));
                }
                List<Long> ids = findAllhubid(hubFilter.getHublist());
                Join<Object, Object> hubjoin = root.join("hub");
                predicates.add(criteriaBuilder.and(hubjoin.get("id").in(ids)));

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }, pageable).map(this::toSelect);

        } else
            throw BusinessException.entityNotFoundException(EntityType.Hub, "hub.list.not.found");
    }

    private List<Long> findchild(List<HubPermissionDto> hublist, Long parendId) {
        List<Long> list = new ArrayList<>();
        for (HubPermissionDto hubPermissionDto : hublist) {
            if (hubPermissionDto.getParent() == parendId) {
                list.add(hubPermissionDto.getId());
                if (hubPermissionDto.getChildren() != null)
                    list.addAll(findchild(hubPermissionDto.getChildren(), hubPermissionDto.getId()));
            }else
                list.add(hubPermissionDto.getId());
        }
        return list;
    }

    private List<Long> findAllhubid(List<HubPermissionDto> hublist) {
        List<Long> list = new ArrayList<>();
        for (HubPermissionDto hubPermissionDto : hublist) {
            list.add(hubPermissionDto.getId());
            if (hubPermissionDto.getChildren() != null)
                list.addAll(findchild(hubPermissionDto.getChildren(), hubPermissionDto.getId()));
        }
        return list;
    }

    @Override
    public Page<GateDto> filter(GateFilter filter, Pageable pageable) {
        if (filter.getHublist() != null) {
            Page<Gate> res = gateRepository
                    .findAll((Specification<Gate>) (root, query, criteriaBuilder) -> {
                        List<Predicate> predicates = new ArrayList<>();
                        predicates.add(criteriaBuilder.equal(root.get("isDeleted"), false));

                        predicates.add(criteriaBuilder.equal(root.get("isActive"), true));
                        if (filter.getCode() != null && StringUtils.isNotBlank(filter.getCode())) {
                            predicates.add(criteriaBuilder.like(root.get("code"), "%" + filter.getCode().trim() + "%"));
                        }
                        if (filter.getHub() != null && StringUtils.isNotBlank(filter.getHub())) {
                            predicates.add(criteriaBuilder.like(root.get("hub").get("name"), "%" + filter.getHub().trim() + "%"));
                        }
                        if (filter.getHublist() != null) {
                            List<Long> ids = findAllhubid(filter.getHublist());
                            Join<Object, Object> hubjoin = root.join("hub");
                            predicates.add(criteriaBuilder.and(hubjoin.get("id").in(ids)));
                        }
                        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
                    }, pageable);
            return res.map(gateConverter::fromModelToDto);
        } else
            throw BusinessException.entityNotFoundException(EntityType.Hub, "hub.list.not.found");
    }

    private Boolean isExist(String code) {
        return gateRepository.existsByCodeAndIsDeletedFalse(code);
    }


    @Override
    public GateDto create(GateDto request) {
        if (isExist(request.getCode()))
            throw BusinessException.valueException(EntityType.Gate, "gate.is.duplicate");

        Gate entity = gateConverter.fromDtoToModel(request);
        entity.setId(null).setIsActive(true).setIsDeleted(false);
        return saveGateData(entity);
    }

    @Override
    public GateDto edit(GateDto request) {
        Gate vehicleMake = findById(request.getId());
        gateConverter.updateFromDto(request, vehicleMake);
        return saveGateData(vehicleMake);
    }

    @Override
    public void delete(Long id) {
        gateRepository.logicalDelete(id);
    }


    private GateDto saveGateData(Gate gate) {
        Gate saved = gateRepository.save(gate);
        return gateConverter.fromModelToDto(saved);
    }

    @Override
    public Gate findById(Long id) {
        if (id == 0) return null;
        return gateRepository.findById(id).orElseThrow(() -> {
            throw BusinessException.entityNotFoundException(EntityType.Gate, "gate.not.found");
        });
    }

    @Override
    public GateDto findByIdDto(Long id) {

        if (id == 0) return null;
        return gateConverter.fromModelToDto(gateRepository.findById(id).orElseThrow(() -> {
            throw BusinessException.entityNotFoundException(EntityType.Gate, "gate.not.found");
        }));


    }

    @Override
    public Gate fromSelect(SelectResponse select) {
        if (select == null) return null;
        return findById(select.getId());
    }

    @Override
    public SelectResponse toSelect(Gate entity) {
        return new SelectResponse(entity.getId(), entity.selectToString());
    }

    @Override
    public boolean ExcelValidation(List<GateExcelDto> gateExcelList) {
        int i = 1;
        for (GateExcelDto gateExcelDto : gateExcelList) {
            if (gateRepository.existsByCodeAndIsDeletedFalse(gateExcelDto.getCode()))
                throw BusinessException.valueException(EntityType.Gate,
                        "gate.is.duplicate",
                        gateExcelDto.getCode() + "  ردیف " + i);


            if (!hubRepository.existsByCodeAndIsDeletedFalse(gateExcelDto.getSelectHub())) {
                throw BusinessException.valueException(EntityType.Gate,
                        "hub.code.not.found",
                        gateExcelDto.getSelectHub() + "  ردیف " + i);

            }
            i++;
        }

        return true;
    }

    @Override
    public List<GateDto> ImportExcel(List<GateExcelDto> gateExcelList) {
        List<GateDto> list = new ArrayList<>();

        for (GateExcelDto gateExcelDto : gateExcelList) {
            GateDto gateDto = gateConverter.fromExcelToDto(gateExcelDto);
            Hub byCode = hubRepository.findByCode(gateExcelDto.getSelectHub());
            gateDto.setSelectHub(new SelectResponse(byCode.getId(), byCode.getName()));
            list.add(create(gateDto));
        }
        return list;

    }


}
