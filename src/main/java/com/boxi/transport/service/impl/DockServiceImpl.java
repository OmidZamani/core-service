package com.boxi.transport.service.impl;

import com.boxi.core.errors.BusinessException;
import com.boxi.core.errors.EntityType;
import com.boxi.core.response.SelectResponse;
import com.boxi.hub.entity.Hub;
import com.boxi.hub.payload.dto.HubPermissionDto;
import com.boxi.hub.repo.HubRepository;
import com.boxi.transport.entity.Dock;
import com.boxi.transport.payload.converter.DockConverter;
import com.boxi.transport.payload.dto.DockDto;
import com.boxi.transport.payload.dto.DockExcelDto;
import com.boxi.transport.payload.dto.DockFilter;
import com.boxi.transport.payload.request.HubFilter;
import com.boxi.transport.repo.DockRepository;
import com.boxi.transport.service.DockService;
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
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class DockServiceImpl implements DockService {

    private final DockRepository dockRepository;
    private final DockConverter dockConverter;

    private final HubRepository hubRepository;

    @Autowired
    public DockServiceImpl(DockRepository dockRepository, DockConverter dockConverter, HubRepository hubRepository) {
        this.dockRepository = dockRepository;
        this.dockConverter = dockConverter;
        this.hubRepository = hubRepository;
    }


    @Override
    public Page<SelectResponse> select(String filter, HubFilter hubFilter) {
        Pageable pageable = PageRequest.of(0, 100);
        if (hubFilter.getHublist() != null) {
            return dockRepository.findAll((Specification<Dock>) (root, query, criteriaBuilder) -> {
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(criteriaBuilder.equal(root.get("isDeleted"), false));
                predicates.add(criteriaBuilder.equal(root.get("isActive"), true));
                if (filter != null && !filter.isEmpty()) {
                    Predicate name = criteriaBuilder.and(criteriaBuilder.like(criteriaBuilder.upper(root.get("name")), "%" + filter.trim() + "%"));
                    Predicate code = criteriaBuilder.and(criteriaBuilder.like(criteriaBuilder.upper(root.get("code")), "%" + filter.trim() + "%"));
                    predicates.add(criteriaBuilder.or(name, code));
                }
                Join<Object, Object> dockHub = root.join("hub");
                List<Long> ids = findAllHubId(hubFilter.getHublist());
                predicates.add(criteriaBuilder.and(dockHub.get("id").in(ids)));

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }, pageable).map(this::toSelect);
        } else
            throw BusinessException.entityNotFoundException(EntityType.Hub, "hub.list.not.found");
    }

    private List<Long> findChild(List<HubPermissionDto> hubList, Long parentId) {
        List<Long> list = new ArrayList<>();
        for (HubPermissionDto hubPermissionDto : hubList) {
            if (Objects.equals(hubPermissionDto.getParent(), parentId)) {
                list.add(hubPermissionDto.getId());
                if (hubPermissionDto.getChildren() != null)
                    list.addAll(findChild(hubPermissionDto.getChildren(), hubPermissionDto.getId()));
            } else
                list.add(hubPermissionDto.getId());
        }
        return list;
    }

    private List<Long> findAllHubId(List<HubPermissionDto> hubList) {
        List<Long> list = new ArrayList<>();
        for (HubPermissionDto hubPermissionDto : hubList) {
            list.add(hubPermissionDto.getId());
            if (hubPermissionDto.getChildren() != null)
                list.addAll(findChild(hubPermissionDto.getChildren(), hubPermissionDto.getId()));
        }
        return list;
    }

    @Override
    public Page<DockDto> filter(DockFilter filter, Pageable pageable) {
        if (filter.getHublist() != null) {
            Page<Dock> res = dockRepository
                    .findAll((Specification<Dock>) (root, query, criteriaBuilder) -> {
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
                            Join<Object, Object> dockHub = root.join("hub");
                            List<Long> ids = findAllHubId(filter.getHublist());
                            predicates.add(criteriaBuilder.and(dockHub.get("id").in(ids)));
                        }
                        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
                    }, pageable);
            return res.map(dockConverter::fromModelToDto);
        } else
            throw BusinessException.entityNotFoundException(EntityType.Hub, "hub.list.not.found");

    }


    @Override
    public DockDto create(DockDto request) {
        if (isExist(request.getCode()))
            throw BusinessException.valueException(EntityType.Dock, "dock.is.duplicate");

        Dock entity = dockConverter.fromDtoToModel(request);
        entity.setId(null);
        entity.setIsDeleted(false);
        entity.setIsActive(true);
        return saveDockData(entity);
    }

    private Boolean isExist(String code) {
        return dockRepository.existsByCodeAndIsDeletedFalse(code);
    }

    @Override
    public DockDto edit(DockDto request) {
        Dock dock = findById(request.getId());
        dockConverter.updateFromDto(request, dock);
        if (dock.getIsActive() == null) dock.setIsActive(true);
        return saveDockData(dock);
    }

    private DockDto saveDockData(Dock dock) {
        Dock saved = dockRepository.save(dock);
        return dockConverter.fromModelToDto(saved);
    }

    @Override
    public Dock findById(Long id) {
        if (id == 0) return null;
        return dockRepository.findById(id).orElseThrow(() -> {
            throw BusinessException.entityNotFoundException(EntityType.Dock, "dock.not.found");
        });
    }

    @Override
    public DockDto findByIdDto(Long id) {
        if (id == 0) return null;
        return dockConverter.fromModelToDto(dockRepository.findById(id).orElseThrow(() -> {
            throw BusinessException.entityNotFoundException(EntityType.Dock, "dock.not.found");
        }));

    }

    @Override
    public Dock fromSelect(SelectResponse select) {
        if (select == null) return null;
        return findById(select.getId());
    }

    @Override
    public SelectResponse toSelect(Dock entity) {
        return new SelectResponse(entity.getId(), entity.selectToString());
    }

    @Override
    public void delete(Long id) {
        dockRepository.logicalDelete(id);
    }

    @Override
    public boolean ExcelValidation(List<DockExcelDto> dockExcelList) {
        int i = 1;
        for (DockExcelDto dockExcelDto : dockExcelList) {

            if (dockRepository.existsByCodeAndIsDeletedFalse(dockExcelDto.getCode())) {
                throw BusinessException.valueException(EntityType.Dock,
                        "dock.is.duplicate",
                        dockExcelDto.getSelectHub() + "  ردیف " + i);
            }
            if (!hubRepository.existsByCodeAndIsDeletedFalse(dockExcelDto.getSelectHub())) {
                throw BusinessException.valueException(EntityType.Dock,
                        "hub.code.not.found",
                        dockExcelDto.getSelectHub() + "  ردیف " + i);
            }
            i++;
        }


        return true;

    }

    @Override
    public List<DockDto> ImportExcel(List<DockExcelDto> dockExcelList) {
        List<DockDto> dockList = new ArrayList<>();
        for (DockExcelDto dockExcelDto : dockExcelList) {
            DockDto dockDto = new DockDto();
            dockDto.setCode(dockExcelDto.getCode());
            dockDto.setName(dockExcelDto.getName());

            Hub byCode = hubRepository.findByCode(dockExcelDto.getSelectHub());
            dockDto.setSelectHub(new SelectResponse(byCode.getId(), byCode.getName()));
            dockList.add(create(dockDto));
        }


        return dockList;
    }

    @Override
    public DockDto updateStatus(DockDto dto) {
        return null;
    }

    @Override
    public DockDto findByCodeInClient(String code) {
        return dockRepository.findByCodeAndIsDeletedFalse(code);
    }

    @Override
    public List<DockDto> externalFindByHubId(Long hubId) {

        List<Dock> allByHubAndIsActiveIsTrueAndIsDeletedIsFalse = dockRepository.findAllByHubAndIsActiveIsTrueAndIsDeletedIsFalse(new Hub().setId(hubId));
        return allByHubAndIsActiveIsTrueAndIsDeletedIsFalse.stream().map(dockConverter::fromModelToDto).collect(Collectors.toList());
    }


}
