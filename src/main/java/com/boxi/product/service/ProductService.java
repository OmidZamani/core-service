package com.boxi.product.service;

import com.boxi.core.response.SelectResponse;
import com.boxi.product.entity.Product;
import com.boxi.product.entity.UsingProduct;
import com.boxi.product.payload.dto.ProductDto;
import com.boxi.product.payload.dto.ProductExcelDto;
import com.boxi.product.payload.request.FilterProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    Page<SelectResponse> selectProduct(String filter);

    Product fromSelect(SelectResponse select);

    SelectResponse toSelect(Product entity);

    ProductDto createProduct(ProductDto request);

    ProductDto edit(ProductDto request);

    Page<ProductDto> filter(FilterProduct filter, Pageable pageable);

    void delete(Long id);

    Page<SelectResponse> select(String filter);

    List<SelectResponse> fetchUsingProducts(List<UsingProduct> usingProductList);

    boolean ExcelValidation(List<ProductExcelDto> productExcelList);

    List<ProductDto> ImportExcel(List<ProductExcelDto> productExcelList);
}
