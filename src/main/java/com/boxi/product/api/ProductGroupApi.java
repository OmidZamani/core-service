package com.boxi.product.api;
//product group	ProductGroupApi تعریف گروه محصول
import com.boxi.core.response.Response;
import com.boxi.core.response.SelectResponse;
import com.boxi.product.payload.dto.ProductGroupDto;
import com.boxi.product.payload.request.FilterProduct;
import com.boxi.product.service.ProductGroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/core-api/productGroup")
@RequiredArgsConstructor
@CrossOrigin(origins = "*",allowedHeaders = "*")
@Slf4j
public class ProductGroupApi {
    private final ProductGroupService productService;



    @GetMapping("/genPinCode")
    public Response genPinCode() {
        System.out.println("genPinCode");
        return  Response.ok().setPayload(UUID.randomUUID().toString());

    }

    // @PreAuthorize("hasPermission('hasAccess','101101')")
    @PostMapping
    public Response create(@RequestBody ProductGroupDto request) {

        log.warn(request.toJson());
        ProductGroupDto response=productService.createProductGroup(request);
        return  Response.ok().setPayload(response);
    }
    // @PreAuthorize("hasPermission('hasAccess','101102')")
    @PutMapping
    public Response edit(@Valid @RequestBody ProductGroupDto request) {
        log.warn(request.toJson());
        ProductGroupDto response=productService.edit(request);
        return  Response.ok().setPayload(response);
    }
    // @PreAuthorize("hasPermission('hasAccess','101103')")
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
        Page<ProductGroupDto> response = productService.filter(request,pageable);
        return  Response.ok().setPayload(response);
    }

    @GetMapping("/select")
    public Response select(@RequestParam(name = "filter",required = true) String filter) {
        Page<SelectResponse> response = productService.select(filter);
        return  Response.ok().setPayload(response);
    }

}
