package com.boxi.product.service.impl;

import com.boxi.core.errors.BusinessException;
import com.boxi.core.errors.EntityType;
import com.boxi.core.response.SelectResponse;
import com.boxi.hub.entity.CountryDevision;
import com.boxi.hub.service.CountryDevisionService;
import com.boxi.product.entity.Product;
import com.boxi.product.entity.ProductAttribute;
import com.boxi.product.entity.ProductAttributeDevision;
import com.boxi.product.entity.UsingProduct;
import com.boxi.product.payload.converter.ProductAttributeConverter;
import com.boxi.product.payload.converter.ProductAttributeDevisionConverter;
import com.boxi.product.payload.dto.ProductAttributeDevisionDto;
import com.boxi.product.payload.dto.ProductAttributeDevisionSelectDto;
import com.boxi.product.payload.dto.ProductAttributeDto;
import com.boxi.product.payload.dto.UsingProductDto;
import com.boxi.product.payload.request.FilterProductAttribute;
import com.boxi.product.repo.ProductAttributeDevisionRepository;
import com.boxi.product.repo.ProductAttributeRepository;
import com.boxi.product.repo.ProductRepository;
import com.boxi.product.repo.UsingProductRepository;
import com.boxi.product.response.ProductAttributeSelectDto;
import com.boxi.product.service.ProductAttributeDevisionService;
import com.boxi.product.service.ProductAttributeService;
import com.boxi.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
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
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class ProductAttributeServiceImpl implements ProductAttributeService {
    private final ProductAttributeRepository productAttributeRepository;
    private final ProductAttributeConverter productAttributeConverter;

    private final ProductAttributeDevisionRepository productAttributeDevisionRepository;
    private final ProductAttributeDevisionConverter productAttributeDevisionConverter;
    private final ProductAttributeDevisionService productAttributeDevisionService;

    private final UsingProductRepository productRepository;

    private final CountryDevisionService countryDevisionService;


    private final ProductService productService;
    private final ProductRepository productRepositorys;

    public ProductAttributeServiceImpl(ProductAttributeRepository productAttributeRepository,
                                       ProductAttributeConverter productAttributeConverter,
                                       ProductAttributeDevisionRepository productAttributeDevisionRepository,
                                       ProductAttributeDevisionConverter productAttributeDevisionConverter,
                                       ProductAttributeDevisionService productAttributeDevisionService,
                                       UsingProductRepository productRepository,
                                       CountryDevisionService countryDevisionService,
                                       ProductService productService, ProductRepository productRepositorys) {
        this.productAttributeRepository = productAttributeRepository;
        this.productAttributeConverter = productAttributeConverter;
        this.productAttributeDevisionRepository = productAttributeDevisionRepository;
        this.productAttributeDevisionConverter = productAttributeDevisionConverter;
        this.productAttributeDevisionService = productAttributeDevisionService;
        this.productRepository = productRepository;
        this.countryDevisionService = countryDevisionService;
        this.productService = productService;
        this.productRepositorys = productRepositorys;
    }

    @Override
    public Page<SelectResponse> select(String filter) {
        Pageable pageable = PageRequest.of(0, 100);
        return productAttributeRepository.findAll((Specification<ProductAttribute>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (filter != null && !filter.isEmpty()) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.like(criteriaBuilder.upper(root.get("name")), "%" + filter.trim() + "%")));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        }, pageable).map(this::toSelect);
    }

    private ProductAttribute findTimeCommitmentById(Long id) {
        return productAttributeRepository.findById(id).orElseThrow(() -> {
            throw BusinessException.entityNotFoundException(EntityType.ProductAttribute, "product.attribute.not.found");
        });
    }

    @Override
    public ProductAttribute fromSelect(SelectResponse select) {
        if (select == null) return null;
        return findTimeCommitmentById(select.getId());
    }

    @Override
    public SelectResponse toSelect(ProductAttribute entity) {
        return new SelectResponse(entity.getId(), entity.selectToString());
    }

    @Override

    public List<ProductAttributeDto> create(List<ProductAttributeDto> request) {
        ProductAttributeDto productAttributeDto1;
        List<ProductAttributeDto> productAttributeList = new ArrayList<>();


        for (ProductAttributeDto productAttributeDto : request) {
            productAttributeDto.setId(null);
            ProductAttribute productAttribute = productAttributeConverter.fromDtoToModel(productAttributeDto);
            ProductAttribute save = productAttributeRepository.save(productAttribute);

            List<ProductAttributeDevision> productAttributeDivisions =
                    productAttributeDto.getAttributeDivition().stream().map(productAttributeDevisionConverter::fromDtoToModel).peek(c ->
                    {
                        c.setIsActive(true);
                        c.setId(null);
                        c.setIsDeleted(false);
                        c.setProductAttribute(save);
                    }).collect(Collectors.toList());
            List<ProductAttributeDevision> saved = productAttributeDevisionService.saveAll(productAttributeDivisions);
            if (productAttributeDto.getUsingProduct() != null)
                for (UsingProductDto usingProductDto : productAttributeDto.getUsingProduct()) {
                    UsingProduct usingProduct = new UsingProduct();
                    usingProduct.setId(null);
                    usingProduct.setParent(productAttribute.getProduct());
                    Product product = new Product();
                    product.setId(usingProductDto.getChild().getId());
                    usingProduct.setChild(product);
                    usingProduct.setProductAttribute(save);
                    UsingProduct save1 = productRepository.save(usingProduct);
                    usingProductDto.setId(save1.getId());
                }


            productAttributeDto.setAttributeDivition(productAttributeDto.getAttributeDivition());
            productAttributeDto1 = productAttributeConverter.fromModelToDto(save);
            productAttributeDto1.setAttributeDivition(productAttributeDevisionConverter.fromModelToDtoList(saved));
            productAttributeList.add(productAttributeDto1);
        }


        return productAttributeList;
    }


    public ProductAttributeDto SaveEdit(ProductAttributeDto request, ProductAttribute productAttribute) {
        ProductAttributeDto productAttributeDto1;
        List<ProductAttributeDto> productAttributeList = new ArrayList<>();
        productAttributeDevisionRepository.deleteByProductAttribute(productAttribute);


        List<ProductAttributeDevision> productAttributeDivisions =
                request.getAttributeDivition().stream().map(productAttributeDevisionConverter::fromDtoToModel).peek(c ->
                {
                    c.setIsActive(true);
                    c.setId(null);
                    c.setIsDeleted(false);
                    c.setProductAttribute(productAttribute);
                }).collect(Collectors.toList());
        List<ProductAttributeDevision> saved = productAttributeDevisionService.saveAll(productAttributeDivisions);
        request.setAttributeDivition(request.getAttributeDivition());
        productAttributeDto1 = productAttributeConverter.fromModelToDto(productAttribute);
        productAttributeDto1.setAttributeDivition(productAttributeDevisionConverter.fromModelToDtoList(saved));
        productAttributeList.add(productAttributeDto1);


        return request;
    }

    private ProductAttributeDto saveProductData(ProductAttribute productAttribute) {
        ProductAttribute saved = productAttributeRepository.save(productAttribute);
        return productAttributeConverter.fromModelToDto(saved);
    }

    public ProductAttribute findTopById(Long id) {

        return productAttributeRepository.findById(id).orElseThrow(() -> {
            throw BusinessException.entityNotFoundException(EntityType.ProductAttribute, "product.attribute.not.found");
        });


    }


    private ProductAttributeDto saveData(ProductAttribute attribute) {
        ProductAttribute saved = productAttributeRepository.save(attribute);
        return productAttributeConverter.fromModelToDto(saved);
    }

    @Override
    public List<ProductAttributeDto> edit(List<ProductAttributeDto> request) {

        ProductAttributeDto productAttributeDto1 = new ProductAttributeDto();
        List<ProductAttributeDto> productAttributeList = new ArrayList<>();
        for (ProductAttributeDto productAttributeDto : request) {
            ProductAttribute productAttribute = productAttributeConverter.fromDtoToModel(productAttributeDto);
            ProductAttribute save = productAttributeRepository.save(productAttribute);
            if (productAttributeDto.getAttributeDivition().size() != 0) {

                productAttributeDevisionRepository.deleteAllByProductAttribute(save);
                List<ProductAttributeDevision> productAttributeDivisions = new ArrayList<>();
                for (ProductAttributeDevisionDto productAttributeDevisionDto : productAttributeDto.getAttributeDivition()) {
                    ProductAttributeDevision c = productAttributeDevisionConverter.fromDtoToModel(productAttributeDevisionDto);
                    c.setIsActive(true);
                    c.setIsDeleted(false);
                    c.setProductAttribute(save);
                    if (productAttributeDevisionDto.getToCountryDevision() != null)
                        c.setToCountryDevision(new CountryDevision().setId(productAttributeDevisionDto.getToCountryDevision().getId()));
                    if (productAttributeDevisionDto.getFromCountryDevision() != null)
                        c.setFromCountryDevision(new CountryDevision().setId(productAttributeDevisionDto.getFromCountryDevision().getId()));
                    ProductAttributeDevision save1 = productAttributeDevisionRepository.save(c);
                    productAttributeDivisions.add(save1);
                }

                productAttributeDto1.setAttributeDivition(productAttributeDevisionConverter.fromModelToDtoList(productAttributeDivisions));
            }
            if (productAttributeDto.getUsingProduct() != null) {
                List<UsingProductDto> usingProductList = new ArrayList<>();
                productRepository.deleteByProductAttribute(save);
                for (UsingProductDto usingProductDto : productAttributeDto.getUsingProduct()) {
                    UsingProduct usingProduct = new UsingProduct();

                    usingProduct.setParent(productAttribute.getProduct());
                    Product product = new Product();
                    product.setId(usingProductDto.getChild().getId());
                    usingProduct.setChild(product);
                    usingProduct.setProductAttribute(save);
                    UsingProduct save1 = productRepository.save(usingProduct);
                    usingProductDto.setId(save1.getId());
                    usingProductList.add(usingProductDto);


                }
                productAttributeDto.setUsingProduct(usingProductList);
            }


            productAttributeDto.setAttributeDivition(productAttributeDto.getAttributeDivition());
            productAttributeDto1 = productAttributeConverter.fromModelToDto(save);
            productAttributeList.add(productAttributeDto);
        }


        return productAttributeList;
    }

    @Override
    public List<ProductAttributeSelectDto> filter(FilterProductAttribute filter, Pageable pageable) {
        List<ProductAttributeSelectDto> productAttributeSelectList = new ArrayList<>();
        for (ProductAttribute productAttribute : productAttributeRepository.FeatchProductAttributeDevision(filter.getProduct().getId())) {
            List<ProductAttributeDevision> byProductAttribute = productAttributeDevisionRepository.findByProductAttribute(productAttribute);
            List<ProductAttributeDevisionSelectDto> productAttributeDivisionSelectDto1 = new ArrayList<>();
            ProductAttributeSelectDto productAttributeDto = productAttributeConverter.fromDtoToSelect(productAttributeConverter.fromModelToDto(productAttribute));
            for (ProductAttributeDevision productAttributeDevision : byProductAttribute) {
                ProductAttributeDevisionSelectDto productAttributeDevisionSelectDto = new ProductAttributeDevisionSelectDto();
                productAttributeDevisionSelectDto.setId(productAttributeDevision.getId());
                productAttributeDevisionSelectDto.setToCountryDevision(countryDevisionService.SelectTreeToParent(productAttributeDevision.getToCountryDevision().getId()));
                productAttributeDevisionSelectDto.setFromCountryDevision(countryDevisionService.SelectTreeToParent(productAttributeDevision.getFromCountryDevision().getId()));
                productAttributeDivisionSelectDto1.add(productAttributeDevisionSelectDto);
            }
            productAttributeDto.setAttributeDivition(productAttributeDivisionSelectDto1);
            List<UsingProduct> byParent = productRepository.findByProductAttribute(productAttribute);
            productAttributeDto.setUsingProduct(productService.fetchUsingProducts(byParent));
            productAttributeSelectList.add(productAttributeDto);
        }

        return productAttributeSelectList;
    }


    @Override
    public List<ProductAttributeDto> check(FilterProductAttribute filter) {
        List<ProductAttribute> all = productAttributeRepository.findAll((Specification<ProductAttribute>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.getProduct() != null)
                predicates.add(criteriaBuilder.equal(root.get("product").get("id"), filter.getProduct().getId()));

            if (filter.getFromDim() != null)
                predicates.add(criteriaBuilder.equal(root.get("fromDim"), filter.getFromDim()));

            if (filter.getToDimension() != null)
                predicates.add(criteriaBuilder.equal(root.get("toDimension"), filter.getToDimension()));

            if (filter.getFromValue() != null)
                predicates.add(criteriaBuilder.equal(root.get("fromWeight"), filter.getFromValue()));

            if (filter.getToWeight() != null)
                predicates.add(criteriaBuilder.equal(root.get("toWeight"), filter.getToWeight()));

            if (filter.getToValue() != null)
                predicates.add(criteriaBuilder.equal(root.get("toValue"), filter.getToValue()));

            if (filter.getTimeCommitment() != null) {
                Join<Object, Object> TimeCommitment = root.join("timeCommitment");
                if (filter.getTimeCommitment().getTo() != null) {
                    predicates.add(criteriaBuilder.equal(TimeCommitment.get("to"), filter.getTimeCommitment().getTo()));
                }
                if (filter.getTimeCommitment().getFrom() != null) {
                    predicates.add(criteriaBuilder.equal(TimeCommitment.get("from"), filter.getTimeCommitment().getFrom()));
                }
                if (filter.getTimeCommitment().getName() != null) {
                    predicates.add(criteriaBuilder.equal(TimeCommitment.get("name"), filter.getTimeCommitment().getName()));
                }
                if (filter.getTimeCommitment().getSelecttedtimeUnit() != null) {
                    predicates.add(criteriaBuilder.equal(TimeCommitment.get("timeUnit").get("Id"), filter.getTimeCommitment().getSelecttedtimeUnit().getId()));
                }
            }

            if (filter.getProductAttributeDevisionsDto() != null) {
                Join<Object, Object> attr = root.join("productAttributeDevisions");
                if (filter.getProductAttributeDevisionsDto().getFromCountryDevision() != null) {
                    predicates.add(criteriaBuilder.equal(attr.get("fromCountryDevision").get("id"),
                            filter.getProductAttributeDevisionsDto().getFromCountryDevision().getId()));
                }

                if (filter.getProductAttributeDevisionsDto().getToCountryDevision() != null) {
                    predicates.add(criteriaBuilder.equal(attr.get("toCountryDevision").get("id"),
                            filter.getProductAttributeDevisionsDto().getToCountryDevision().getId()));
                }
            }
            query.distinct(true);
            query.orderBy(criteriaBuilder.asc(root.get("id")));
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        });

        return all.stream().map(productAttributeConverter::fromModelToDto).collect(Collectors.toList());

    }


    @Override
    public void delete(Long id) {
        ProductAttribute productAttribute = findTopById(id);
        productRepository.deleteByProductAttribute(productAttribute);
        if (productAttributeRepository.existsById(id)) {
            productAttributeDevisionRepository.deleteByProductAttribute(productAttribute);
            productAttributeRepository.deleteById(id);
        }

    }

    @Override
    public ProductAttributeSelectDto SelectEdit(String filter) {
        ProductAttribute productAttribute = findTopById(Long.valueOf(filter));

        ProductAttributeDto productAttributeDto1 = productAttributeConverter.fromModelToDto(productAttribute);

        ProductAttributeSelectDto productAttributeSelectDto = productAttributeConverter.fromDtoToSelect(productAttributeDto1);
        List<ProductAttributeDevision> byProductAttribute = productAttributeDevisionRepository.findByProductAttribute(productAttribute);
        ProductAttributeDevisionSelectDto productAttributeDevisionSelectDto = new ProductAttributeDevisionSelectDto();
        List<ProductAttributeDevisionSelectDto> productAttributeDivisionSelectDto1 = new ArrayList<>();
        for (ProductAttributeDevision productAttributeDevision : byProductAttribute) {

            productAttributeDevisionSelectDto.setId(productAttributeDevision.getId());
            productAttributeDevisionSelectDto.setToCountryDevision(countryDevisionService.SelectTreeToParent(productAttributeDevision.getFromCountryDevision().getId()));
            productAttributeDevisionSelectDto.setFromCountryDevision(countryDevisionService.SelectTreeToParent(productAttributeDevision.getToCountryDevision().getId()));
            productAttributeDivisionSelectDto1.add(productAttributeDevisionSelectDto);
        }
        productAttributeSelectDto.setAttributeDivition(productAttributeDivisionSelectDto1);
        return productAttributeSelectDto;
    }

    @Override
    public List<ProductAttributeDto> findById(Long id) {

        List<ProductAttribute> allByProduct = productAttributeRepository.findAllByProduct(new Product().setId(id));
        return allByProduct.stream().map(productAttributeConverter::fromModelToDto).collect(Collectors.toList());
    }

    @Override
    public List<ProductAttributeSelectDto> findByAttribute(Long id) {
        List<ProductAttributeSelectDto> productAttributeSelectList = new ArrayList<>();
        List<ProductAttribute> allByProduct = productAttributeRepository.findAllByProduct(new Product().setId(id));
        for (ProductAttribute productAttribute : allByProduct) {
            ProductAttributeDto productAttributeDto1 = productAttributeConverter.fromModelToDto(productAttribute);

            ProductAttributeSelectDto productAttributeSelectDto = productAttributeConverter.fromDtoToSelect(productAttributeDto1);
            List<ProductAttributeDevision> byProductAttribute = productAttributeDevisionRepository.findByProductAttribute(productAttribute);
            List<ProductAttributeDevisionSelectDto> productAttributeDivisionSelectDto1 = new ArrayList<>();
            for (ProductAttributeDevision productAttributeDevision : byProductAttribute) {
                ProductAttributeDevisionSelectDto productAttributeDevisionSelectDto = new ProductAttributeDevisionSelectDto();
                productAttributeDevisionSelectDto.setId(productAttributeDevision.getId());
                productAttributeDevisionSelectDto.setToCountryDevision(countryDevisionService.SelectTreeToParent(productAttributeDevision.getToCountryDevision().getId()));
                productAttributeDevisionSelectDto.setFromCountryDevision(countryDevisionService.SelectTreeToParent(productAttributeDevision.getFromCountryDevision().getId()));
                productAttributeDivisionSelectDto1.add(productAttributeDevisionSelectDto);
            }
            productAttributeSelectDto.setAttributeDivition(productAttributeDivisionSelectDto1);
            List<SelectResponse> selectResponses = new ArrayList<>();
            if (productAttributeSelectDto.getUsingProduct() != null) {
                for (SelectResponse selectResponse : productAttributeSelectDto.getUsingProduct()) {
                    UsingProduct usingProduct = productRepository.findById(selectResponse.getId()).orElseThrow();
                    Product product = productRepositorys.findById(usingProduct.getChild().getId()).orElseThrow();
                    selectResponses.add(new SelectResponse(product.getId(), product.getName()));
                }
                productAttributeSelectDto.setUsingProduct(selectResponses);
                productAttributeSelectList.add(productAttributeSelectDto);
            }
        }
        return productAttributeSelectList;

    }
}




