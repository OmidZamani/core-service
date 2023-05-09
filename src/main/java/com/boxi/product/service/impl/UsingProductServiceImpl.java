package com.boxi.product.service.impl;

import com.boxi.core.errors.BusinessException;
import com.boxi.core.errors.EntityType;
import com.boxi.core.response.SelectResponse;
import com.boxi.product.entity.UsingProduct;
import com.boxi.product.payload.converter.UsingProductConverter;
import com.boxi.product.payload.dto.UsingProductDto;
import com.boxi.product.payload.request.FilterUsingProduct;
import com.boxi.product.repo.UsingProductRepository;
import com.boxi.product.service.UsingProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UsingProductServiceImpl implements UsingProductService {
    private final UsingProductRepository productRepository;
    private final UsingProductConverter usingProductConverter;

    public UsingProductServiceImpl(UsingProductRepository productRepository, UsingProductConverter usingProductConverter) {
        this.productRepository = productRepository;
        this.usingProductConverter = usingProductConverter;
    }

    private Boolean isExist(Long Parent) {
        return productRepository.existsById(Parent);

    }

    @Override
    public UsingProduct fromSelect(SelectResponse select) {

        return null;
    }

    @Override
    public SelectResponse toSelect(UsingProduct entity) {
        return null;
    }

    @Override
    public UsingProductDto create(UsingProductDto request) {
        request.setId(null);
        UsingProduct usingProduct = usingProductConverter.fromDtoToModel(request);

        return saveUsingProduct(usingProduct);
    }

    private UsingProductDto saveUsingProduct(UsingProduct usingProduct) {

        if (!isExist(usingProduct.getParent().getId()))
            throw BusinessException.valueException(EntityType.UsingProduct, "using.product.not.found");

        if (!isExist(usingProduct.getChild().getId()))
            throw BusinessException.valueException(EntityType.UsingProduct, "using.product.not.found");

        UsingProduct saved = productRepository.save(usingProduct);
        return usingProductConverter.fromModelToDto(saved);
    }


    private UsingProductDto saveData(UsingProduct product) {
        if (!isExist(product.getParent().getId()))
            throw BusinessException.valueException(EntityType.UsingProduct, "using.product.not.found");

        if (!isExist(product.getChild().getId()))
            throw BusinessException.valueException(EntityType.UsingProduct, "using.product.not.found");

        UsingProduct saved = productRepository.save(product);
        return usingProductConverter.fromModelToDto(saved);

    }

    @Override
    public UsingProductDto edit(UsingProductDto request) {
        UsingProduct usingProduct = findById(request.getId());
//        if(isExist(usingProduct.getParent().getId()))
//            throw BusinessException.throwException(EntityType.UsingProduct, ExceptionType.DUPLICATE_ENTITY);
        usingProductConverter.updateFromDto(request, usingProduct);
        return saveData(usingProduct);
    }

    private UsingProduct findById(Long id) {
        if (id == 0) return null;
        return productRepository.findById(id).orElseThrow(() -> {
            throw BusinessException.entityNotFoundException(EntityType.UsingProduct, "using.product.not.found");
        });
    }

    @Override
    public Page<UsingProductDto> filter(FilterUsingProduct filter, Pageable pageable) {

        return null;
    }

    @Override
    public void delete(Long id) {
        UsingProduct usingProduct = new UsingProduct();
        usingProduct.setId(id);
        if (findById(id) == null)
            throw BusinessException.entityNotFoundException(EntityType.UsingProduct, "using.product.not.found");

        productRepository.delete(usingProduct);
    }

    @Override
    public Page<SelectResponse> select(String filter) {
        return null;
    }


}
