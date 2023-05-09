package com.boxi.product.api;
//100405	add product attribute	ProductAttributeApi	افزودن مشخصات محصول

import com.boxi.core.response.Response;
import com.boxi.core.response.SelectResponse;
import com.boxi.product.payload.dto.ProductAttributeDto;
import com.boxi.product.payload.request.FilterProductAttribute;
import com.boxi.product.response.ProductAttributeSelectDto;
import com.boxi.product.service.ProductAttributeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/core-api/productattribute")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class ProductAttributeApi {

    private final ProductAttributeService productAttributeService;

    @GetMapping("/genPinCode")
    public Response genPinCode() {
        System.out.println("genPinCode");
        return Response.ok().setPayload(UUID.randomUUID().toString());
    }

    // @PreAuthorize("hasPermission('hasAccess','100405')")
    @PostMapping
    public Response create(@RequestBody List<ProductAttributeDto> request) {
        log.warn(request.toString());

        List<ProductAttributeDto> productAttributeDto = productAttributeService.create(request);
        return Response.ok().setPayload(productAttributeDto);
    }

    // @PreAuthorize("hasPermission('hasAccess','100406')")
    @PutMapping
    public Response edit(@Valid @RequestBody List<ProductAttributeDto> request) {
        log.warn(request.toString());
        List<ProductAttributeDto> response = productAttributeService.edit(request);
        return Response.ok().setPayload(response);
    }

    // @PreAuthorize("hasPermission('hasAccess','100407')")
    @DeleteMapping("/{id}")
    public Response delete(@PathVariable Long id) {
        productAttributeService.delete(id);
        return Response.ok();
    }

    @PostMapping("/filter")
    public Response filter(@RequestParam(name = "pageNumber", defaultValue = "1", required = false) Integer pageNumber,
                           @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                           @RequestBody FilterProductAttribute request) {
        log.warn(request.toJson());
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);

        List<ProductAttributeSelectDto> filter = productAttributeService.filter(request, pageable);


        return Response.ok().setPayload(filter);

    }

    @GetMapping("/select")
    public Response select(@RequestParam(name = "filter", required = true) String filter) {
        Page<SelectResponse> response = productAttributeService.select(filter);
        return Response.ok().setPayload(response);
    }

    @GetMapping("/selectbyedit")
    public Response selectByEdit(@RequestParam(name = "filter", required = true) String filter) {


        return Response.ok().setPayload(productAttributeService.SelectEdit(filter));
    }

    @GetMapping("/findByAttribute/{id}")
    public Response findByAttribute(@PathVariable Long id) {
        return Response.ok().setPayload(productAttributeService.findByAttribute(id));
    }

    @GetMapping("/findByProduct/{id}")
    public Response findallatt(@PathVariable Long id) {
        return Response.ok().setPayload(productAttributeService.findById(id));
    }

    @PostMapping("/checkproduct")
    public Response check(@RequestBody FilterProductAttribute request) {
        log.warn(request.toJson());
        List<ProductAttributeDto> filter = productAttributeService.check(request);


        return Response.ok().setPayload(filter);

    }

}
