package com.boxi.product.api;
//1004	product def	ProductApi	تعریف محصول
import com.boxi.core.response.Response;
import com.boxi.core.response.SelectResponse;
import com.boxi.excel.service.impl.ConvertExcelServiceImpl;

import com.boxi.product.payload.dto.ProductDto;
import com.boxi.product.payload.dto.ProductExcelDto;
import com.boxi.product.payload.request.FilterProduct;
import com.boxi.product.service.ProductService;
import com.nimbusds.jose.shaded.json.JSONObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping( "/core-api/product")
@RequiredArgsConstructor
@CrossOrigin(origins = "*",allowedHeaders = "*")
@Slf4j
public class ProductApi {

    private final ProductService productService;
    private final ConvertExcelServiceImpl convertExcelService;



    // @PreAuthorize("hasPermission('hasAccess','100401')")
    @PostMapping
    public Response createProduct(@RequestBody ProductDto request) {
        log.warn(request.toJson());
        request.setIsDeleted(false);
        ProductDto response=productService.createProduct(request);
        return  Response.ok().setPayload(response);
    }
    // @PreAuthorize("hasPermission('hasAccess','100403')")
    @PutMapping
    public Response edit(@Valid @RequestBody ProductDto request) {
        log.warn(request.toJson());
        ProductDto response=productService.edit(request);
        return  Response.ok().setPayload(response);
    }
    // @PreAuthorize("hasPermission('hasAccess','100404')")
    @DeleteMapping("/{id}")
    public Response delete(@PathVariable Long id) {
        productService.delete(id);
        return  Response.ok();
    }
    @PostMapping("/filter")
    public Response filter(@RequestParam(name = "pageNumber",defaultValue = "1",required=false) Integer pageNumber,
                           @RequestParam(name = "pageSize",defaultValue = "10",required=false) Integer pageSize,
                           @RequestBody FilterProduct request) {
        log.warn(request.toJson());
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize);
        Page<ProductDto> response = productService.filter(request,pageable);
        return  Response.ok().setPayload(response);
    }

    @GetMapping("/select")
    public Response select(@RequestParam(name = "filter"  ) String filter) {
        Page<SelectResponse> response = productService.select(filter);
        return  Response.ok().setPayload(response);
    }

    // @PreAuthorize("hasPermission('hasAccess','100402')")
    @PostMapping("/importexcelfile")
    public Response createByExcel(@RequestParam("file") MultipartFile excel, @RequestParam("Entity") String Entity) throws IOException {
        log.warn(Entity);
        List<ProductExcelDto> productExcelList =
                (List<ProductExcelDto>) convertExcelService.ConvertExcelToObjects(ProductExcelDto.class, excel);

        if (productService.ExcelValidation(productExcelList)) {

            List<ProductDto> productList = productService.ImportExcel(productExcelList);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Product", productList.size());
            int allAttribute = 0 ;
            for (ProductDto dto : productList) {
                allAttribute += dto.getAttribute().size();
            }
            jsonObject.put("productAttribute", allAttribute);

            return Response.ok().setPayload(jsonObject);
        }
        else {
            return Response.ok();
        }
    }


}
