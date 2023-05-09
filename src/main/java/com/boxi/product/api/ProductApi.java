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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping( "/core-api/product")
@RequiredArgsConstructor
@CrossOrigin(origins = "*",allowedHeaders = "*")
@Slf4j
public class ProductApi {

    private final ProductService productService;
    private final ConvertExcelServiceImpl convertExcelService;

    @GetMapping("/genPinCode")
    public Response genPinCode(@AuthenticationPrincipal Jwt jwt) {
      /*  jwt.getClaims().get("user_name");
         jwt.getSubject());*/
        System.out.println("genPinCode");
        return  Response.ok().setPayload(UUID.randomUUID().toString());
    }

    // @PreAuthorize("hasPermission('hasAccess','100401')")
    @PostMapping
    public Response createproduct(@RequestBody ProductDto request) {
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
    public Response select(@RequestParam(name = "filter",required = true) String filter) {
        Page<SelectResponse> response = productService.select(filter);
        return  Response.ok().setPayload(response);
    }

    // @PreAuthorize("hasPermission('hasAccess','100402')")
    @PostMapping("/importexcelfile")
    public Response createByExcel(@RequestParam("file") MultipartFile excel, @RequestParam("Entity") String Entity, HttpServletRequest request) throws IOException {
        String contextPath = request.getRequestURI();
        log.warn(Entity);
        String Dto = Entity;
        List<ProductExcelDto> productExcelDtos =
                (List<ProductExcelDto>) convertExcelService.ConvertExcelToObjects(ProductExcelDto.class, excel);

        if (productService.ExcelValidation(productExcelDtos)) {

            List<ProductDto> productDtos = productService.ImportExcel(productExcelDtos);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Product",productDtos.size());
            int Allatribute= 0 ;
            for (int i = 0; i < productDtos.size(); i++) {
                Allatribute+=productDtos.get(i).getAttribute().size();
            }
            jsonObject.put("productAttribute",Allatribute);

            return Response.ok().setPayload(jsonObject);
        }
        else {
            return Response.ok();
        }
    }


}
