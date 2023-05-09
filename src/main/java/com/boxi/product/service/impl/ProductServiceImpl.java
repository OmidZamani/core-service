package com.boxi.product.service.impl;


import com.boxi.core.errors.BusinessException;
import com.boxi.core.errors.EntityType;
import com.boxi.core.response.SelectResponse;
import com.boxi.hub.repo.CountryDevisionRepository;
import com.boxi.product.entity.Product;
import com.boxi.product.entity.ProductAttribute;
import com.boxi.product.entity.ProductGroup;
import com.boxi.product.entity.UsingProduct;
import com.boxi.product.payload.converter.ProductAttributeConverter;
import com.boxi.product.payload.converter.ProductConverter;
import com.boxi.product.payload.converter.ProductGroupConverter;
import com.boxi.product.payload.converter.UsingProductConverter;
import com.boxi.product.payload.dto.ProductAttributeDto;
import com.boxi.product.payload.dto.ProductAttributeExcelDto;
import com.boxi.product.payload.dto.ProductDto;
import com.boxi.product.payload.dto.ProductExcelDto;
import com.boxi.product.payload.request.FilterProduct;
import com.boxi.product.repo.*;
import com.boxi.product.service.ProductService;
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
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductConverter productConvert;

    private final ProductGroupRepository productGroupRepository;
    private final ProductGroupConverter productGroupConverter;

    private final TimeCommitmentRepository timeCommitmentRepository;
    private final CountryDevisionRepository countryDevisionRepository;

    private final ProductAttributeRepository productAttributeRepository;
    private final ProductAttributeConverter productAttributeConverter;


    private final UsingProductConverter usingProductConverter;
    private final UsingProductRepository usingProductRepository;

    public ProductServiceImpl(ProductRepository productRepository, ProductConverter productConvert,
                              ProductGroupRepository productGroupRepository,
                              ProductGroupConverter productGroupConverter,
                              TimeCommitmentRepository timeCommitmentRepository,
                              CountryDevisionRepository countryDevisionRepository,
                              ProductAttributeRepository productAttributeRepository,
                              ProductAttributeConverter productAttributeConverter,

                              UsingProductConverter usingProductConverter,
                              UsingProductRepository usingProductRepository) {
        this.productRepository = productRepository;
        this.productConvert = productConvert;
        this.productGroupRepository = productGroupRepository;
        this.productGroupConverter = productGroupConverter;
        this.timeCommitmentRepository = timeCommitmentRepository;
        this.countryDevisionRepository = countryDevisionRepository;
        this.productAttributeRepository = productAttributeRepository;
        this.productAttributeConverter = productAttributeConverter;

        this.usingProductConverter = usingProductConverter;
        this.usingProductRepository = usingProductRepository;
    }

    @Override

    public Page<SelectResponse> selectProduct(String filter) {
        Pageable pageable = PageRequest.of(0, 100);
        return null;
    }

    private Boolean isExist(String code) {

        return productRepository.existsBycode(code);
    }

    @Override
    public ProductDto edit(ProductDto request) {
        if(! productRepository.existsById(request.getId()))
            throw BusinessException.valueException(EntityType.Product,"product.not.found");

        return saveData(request);
    }

    private ProductDto saveData(ProductDto dto) {
        dto.setIsDeleted(false);
        Product saved = productRepository.save(productConvert.fromDtoToModel(dto));
        return productConvert.fromModelToDto(saved);

    }

    @Override
    public ProductDto createProduct(ProductDto request) {
        request.setId(null);
        request.setIsDeleted(false);
        if (isExist(request.getCode()))
            throw BusinessException.valueException(EntityType.Product, "product.is.duplicate");

        Product product = productConvert.fromDtoToModel(request);
        return saveProductData(product);
    }

    @Override
    public Page<ProductDto> filter(FilterProduct filter, Pageable pageable) {
        Page<Product> res = productRepository
                .findAll((Specification<Product>) (root, query, criteriaBuilder) -> {

                    List<Predicate> predicates = new ArrayList<>();

                    predicates.add(criteriaBuilder.equal(root.get("isDeleted"), false));
                    if (filter.getIsActive() == null)
                        predicates.add(criteriaBuilder.equal(root.get("isActive"), true));
                    else
                        predicates.add(criteriaBuilder.equal(root.get("isActive"), filter.getIsActive()));

                    if (filter.getCode() != null && StringUtils.isNotBlank(filter.getCode())) {
                        predicates.add(criteriaBuilder.like(root.get("code"), "%" + filter.getCode().trim() + "%"));
                    }
                    if (filter.getName() != null && StringUtils.isNotBlank(filter.getName())) {
                        predicates.add(criteriaBuilder.like(root.get("name"), "%" + filter.getName().trim() + "%"));
                    }
                    if (filter.getProductGroup() != null && StringUtils.isNotBlank(filter.getProductGroup())) {
                        predicates.add(criteriaBuilder.equal(root.get("productGroup").get("id"), filter.getProductGroup()));

                    }
                    query.orderBy(criteriaBuilder.desc(root.get("id")));
                    return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
                }, pageable);
        return res.map(productConvert::fromModelToDto);
    }

    @Override
    public void delete(Long id) {
        if (!productRepository.existsById(id))
            throw BusinessException.valueException(EntityType.Product, "product.code.not.found");

        productRepository.logicalDelete(id);
    }

    @Override
    public Page<SelectResponse> select(String filter) {
        Pageable pageable = PageRequest.of(0, 100);
        Page<Product> res = productRepository
                .findAll((Specification<Product>) (root, query, criteriaBuilder) -> {
                    List<Predicate> predicates = new ArrayList<>();

                    predicates.add(criteriaBuilder.equal(root.get("isDeleted"), false));
                    predicates.add(criteriaBuilder.equal(root.get("isActive"), true));
                    if (filter != null && !filter.isEmpty()) {
                        predicates.add(criteriaBuilder.like(root.get("Code"), "%" + filter.trim() + "%"));
                    }
                    return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
                }, pageable);
        return res.map(this::toSelect);

    }

    @Override
    public List<SelectResponse> featchUsingProducts(List<UsingProduct> usingProductDtos) {
        List<SelectResponse> usingProductDtos1 = new ArrayList<>();
        for (UsingProduct usingProductDto : usingProductDtos) {
            Product byId = findById(usingProductDto.getChild().getId());
            usingProductDtos1.add(new SelectResponse(byId.getId(), byId.getName()));
        }
        return usingProductDtos1;

    }

    private ProductDto saveProductData(Product product) {

        Product saved = productRepository.save(product);
        ProductDto productDto = productConvert.fromModelToDto(saved);
        return productDto;
    }

    private Page<SelectResponse> getProducts(Long productId, String filter, Pageable pageable) {

        return productRepository.findAll((Specification<Product>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("PRODUCTID"), productId)));
            if (filter != null && !filter.isEmpty()) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.like(criteriaBuilder.upper(root.get("name")), "%" + filter.trim() + "%")));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        }, pageable).map(this::toSelect);
    }


    @Override
    public Product fromSelect(SelectResponse select) {
        if (select == null) return null;
        return findById(select.getId());
    }

    private Product findById(Long id) {
        if (id == 0) return null;
        return productRepository.findById(id).orElseThrow(() -> {
            throw BusinessException.entityNotFoundException(EntityType.Product,"product.not.found");
        });
    }


    @Override
    public SelectResponse toSelect(Product entity) {
        return new SelectResponse(entity.getId(), entity.getName());
    }

    @Override
    public boolean ExcelValidation(List<ProductExcelDto> productExcelDtos) {

        int i = 1;
        for (ProductExcelDto productExcelDto : productExcelDtos) {

            if (!productGroupRepository.existsByCode(productExcelDto.getProductGroup()))
                throw BusinessException.valueException(EntityType.Product, "product.code.not.found",
                        productExcelDto.getCode() + "  ردیف " + i);

            int b = 1;
            for (ProductAttributeExcelDto productAttributeExcelDto : productExcelDto.getProductAttribute()) {
                if (productAttributeExcelDto.getFromDim() > productAttributeExcelDto.getToDimension())
                    throw BusinessException.valueException(EntityType.Product,
                            "product.attribute.dimension.not.valid",
                            productExcelDto.getCode() + "  ردیف " + i);

                if(productAttributeExcelDto.getUsingProduct()!=null) {
                    String[] split = productAttributeExcelDto.getUsingProduct().split(" - ");
                    for (String s : split) {
                        if (!productRepository.existsBycode(s))
                            throw BusinessException.valueException(EntityType.Product,
                                    "product.code.not.found",
                                    s + "  ردیف " + b + " sheet productAttribute ");


                    }
                }
                if (!timeCommitmentRepository.existsByName(productAttributeExcelDto.getTimeCommitment()))
                    throw BusinessException.valueException(EntityType.Product,
                            "timeCommitment.code.not.found",
                            productAttributeExcelDto.getTimeCommitment() + "  ردیف " + b + " sheet productAttribute ");

                for (String s : productAttributeExcelDto.getAttributeDivitionto().split(" - ")) {
                    if (!countryDevisionRepository.existsByCode(s))
                        throw BusinessException.valueException(EntityType.Product,
                                "countrydevision.code.not.found",
                                s + "  ردیف " + b + " sheet productAttribute ");

                }
                for (String s : productAttributeExcelDto.getAttributeDivitionfrom().split(" - ")) {
                    if (!countryDevisionRepository.existsByCode(s))
                        throw BusinessException.valueException(EntityType.Product,
                                "countrydevision.code.not.found",
                                s + "  ردیف " + b + " sheet productAttribute ");
                }
                b++;
            }
            i++;
        }


        return true;
    }

    @Override
    public List<ProductDto> ImportExcel(List<ProductExcelDto> productExcelDtos) {
        List<ProductDto> productDtos = new ArrayList<>();
        for (ProductExcelDto productExcelDto : productExcelDtos) {
            ProductDto productDto = productConvert.fromExcelToDto(productExcelDto);
            ProductGroup byCode = productGroupRepository.findByCode(productExcelDto.getProductGroup());
            productDto.setProductGroup(new SelectResponse(byCode.getId(), byCode.getName()));
            ProductDto product = new ProductDto();
            if (productRepository.existsByCode(productExcelDto.getCode())) {
                Product byCode1 = productRepository.findByCode(productExcelDto.getCode());
                product = productConvert.fromModelToDto(byCode1);

            } else
                product = createProduct(productDto);

            productDto.setId(product.getId());
            List<ProductAttributeDto> productAttributes = new ArrayList<>();
            for (ProductAttributeExcelDto productAttributeExcelDto : productExcelDto.getProductAttribute()) {
                ProductAttribute productAttribute = new ProductAttribute();
                productAttribute.setProduct(productConvert.fromDtoToModel(product));

                productAttribute.setFromDim(productAttributeExcelDto.getFromDim());
                productAttribute.setToDimension(productAttributeExcelDto.getToDimension());

                productAttribute.setFromValue(productAttributeExcelDto.getFromValue());
                productAttribute.setToValue(productAttributeExcelDto.getToValue());

                productAttribute.setFromWeight(productAttributeExcelDto.getFromWeight());
                productAttribute.setToWeight(productAttributeExcelDto.getToWeight());


                productAttribute.setTimeCommitment(timeCommitmentRepository.findByName(productAttributeExcelDto.getTimeCommitment()));
                List<UsingProduct> usingProducts = new ArrayList<>();
                if(productAttributeExcelDto.getUsingProduct()!=null) {
                    for (String s : productAttributeExcelDto.getUsingProduct().split(" - ")) {
                        UsingProduct usingProduct = new UsingProduct();
                        usingProduct.setParent(productConvert.fromDtoToModel(product));
                        usingProduct.setChild(productRepository.findByCode(s));
                        usingProducts.add(usingProduct);
                    }
                }
                productAttribute.setUsingProducts(usingProducts);
                productAttributeRepository.save(productAttribute);
                productAttributes.add(productAttributeConverter.fromModelToDto(productAttribute));
                productDto.setAttribute(productAttributes);
            }


            productDtos.add(productDto);
        }


        return productDtos;
    }


}
