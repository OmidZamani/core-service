package com.boxi.product.service.impl;

import com.boxi.core.errors.BusinessException;
import com.boxi.core.errors.EntityType;
import com.boxi.core.response.SelectResponse;
import com.boxi.product.entity.DiscountCode;
import com.boxi.product.entity.DiscountCodesArrangement;
import com.boxi.product.payload.converter.DiscountCodeConverter;
import com.boxi.product.payload.converter.DiscountCodesArrangementConverter;
import com.boxi.product.payload.dto.DiscountCodeDto;
import com.boxi.product.payload.dto.DiscountCodesArrangementDto;
import com.boxi.product.payload.dto.FilterDiscountCodeDto;
import com.boxi.product.repo.DiscountCodeRepository;
import com.boxi.product.repo.DiscountCodesArrangementRepository;
import com.boxi.product.service.DiscountCodeService;
import com.boxi.utils.DateUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DiscountCodeServiceImpl implements DiscountCodeService {

    private final DiscountCodeConverter discountCodeConverter;
    private final DiscountCodeRepository discountCodeRepository;

    private final DiscountCodesArrangementConverter discountCodesArrangementConverter;
    private final DiscountCodesArrangementRepository discountCodesArrangementRepository;


    public DiscountCodeServiceImpl(DiscountCodeConverter discountCodeConverter
            , DiscountCodeRepository discountCodeRepository
            , DiscountCodesArrangementConverter discountCodesArrangementConverter
            , DiscountCodesArrangementRepository discountCodesArrangementRepository) {
        this.discountCodeConverter = discountCodeConverter;
        this.discountCodeRepository = discountCodeRepository;
        this.discountCodesArrangementConverter = discountCodesArrangementConverter;
        this.discountCodesArrangementRepository = discountCodesArrangementRepository;
    }

    @Override
    public DiscountCodeDto create(DiscountCodeDto dto) {
        return saveCreate(dto);
    }

    private DiscountCodeDto saveCreate(DiscountCodeDto dto) {
        DiscountCode discountCode = discountCodeConverter.fromDtoToModel(dto);
        discountCode.setId(null);
        DiscountCode save = discountCodeRepository.save(discountCode);
        if (dto.getArrangements() != null) {
            List<DiscountCodesArrangement> discountCodesArrangements = new ArrayList<>();
            for (DiscountCodesArrangementDto arrangement : dto.getArrangements()) {
                DiscountCodesArrangement codesArrangement = discountCodesArrangementConverter.fromDtoToModel(arrangement);
                codesArrangement.setId(null);
                codesArrangement.setDiscountCode(save);
                discountCodesArrangements.add(discountCodesArrangementRepository.save(codesArrangement));
            }

            save.setArrangements(discountCodesArrangements);

        }
        return discountCodeConverter.fromModelToDto(save);
    }

    @Override
    public DiscountCodeDto edit(DiscountCodeDto dto) {
        return saveEdit(dto);
    }

    private Boolean isExist(Long id) {
        return discountCodeRepository.existsById(id);
    }

    private DiscountCodeDto saveEdit(DiscountCodeDto dto) {
        if (isExist(dto.getId())) {
            DiscountCode discountCode = discountCodeConverter.fromDtoToModel(dto);
            DiscountCode save = discountCodeRepository.save(discountCode);
            if (dto.getArrangements() != null) {
                List<DiscountCodesArrangement> discountCodesArrangements = new ArrayList<>();
                for (DiscountCodesArrangementDto arrangement : dto.getArrangements()) {
                    DiscountCodesArrangement codesArrangement = discountCodesArrangementConverter.fromDtoToModel(arrangement);
                    codesArrangement.setDiscountCode(save);
                    discountCodesArrangements.add(discountCodesArrangementRepository.save(codesArrangement));
                }

                save.setArrangements(discountCodesArrangements);

            }
            return discountCodeConverter.fromModelToDto(save);
        } else throw BusinessException.entityNotFoundException(EntityType.DiscountCode, "discountCode.not.found");

    }

    @Override
    public List<SelectResponse> select(String filter) {
        List<DiscountCode> discountCode = discountCodeRepository.findAll((Specification<DiscountCode>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasText(filter))
                predicates.add(criteriaBuilder.like(root.get("discountCode"), "%" + filter + "%"));

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
        return discountCode.stream().map(this::toSelect).collect(Collectors.toList());
    }

    private SelectResponse toSelect(DiscountCode discountCode) {
        return new SelectResponse(discountCode.getId(), discountCode.getDiscountCode());
    }

    @Override
    public void delete(Long id) {
        if (isExist(id)) {
            discountCodeRepository.deleteById(id);
        } else throw BusinessException.entityNotFoundException(EntityType.DiscountCode, "discountCode.not.found");
    }

    @Override
    public DiscountCodeDto findById(Long id) {
        if (isExist(id)) {
            DiscountCode discountCode = discountCodeRepository.findById(id).orElseThrow();
            return discountCodeConverter.fromModelToDto(discountCode);
        } else throw BusinessException.entityNotFoundException(EntityType.DiscountCode, "discountCode.not.found");
    }

    @Override
    public Page<DiscountCodeDto> filter(FilterDiscountCodeDto filter, Pageable pageable) {

        return discountCodeRepository.findAll((Specification<DiscountCode>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.getIsActive() != null)
                predicates.add(criteriaBuilder.equal(root.get("isActive"), filter.getIsActive()));

            if (filter.getOneTimeDiscountCode() != null)
                predicates.add(criteriaBuilder.equal(root.get("oneTimeDiscountCode"), filter.getOneTimeDiscountCode()));

            if (StringUtils.hasText(filter.getDiscountCode()))
                predicates.add(criteriaBuilder.like(root.get("discountCode"), "%" + filter + "%"));

            if (filter.getIsPublic())
                predicates.add(criteriaBuilder.equal(root.get("isPublic"), filter.getIsPublic()));

            if (filter.getValidDateFrom() != null && filter.getValidDateTo() != null) {
                Date from = DateUtil.convertDateToJalaliDateDto(filter.getValidDateFrom());
                Date to = DateUtil.convertDateToJalaliDateDto(filter.getValidDateTo());

                predicates.add(criteriaBuilder.between(root.get("validDateFrom"), from, to));
                predicates.add(criteriaBuilder.between(root.get("validDateTo"), from, to));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }, pageable).map(discountCodeConverter::fromModelToDto);
    }
}
