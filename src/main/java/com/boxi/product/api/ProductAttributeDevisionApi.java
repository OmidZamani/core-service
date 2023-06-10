package com.boxi.product.api;

import com.boxi.core.response.Response;
import com.boxi.core.response.SelectResponse;
import com.boxi.product.payload.dto.ProductAttributeDevisionDto;
import com.boxi.product.payload.request.FilterProductAttributeDevision;
import com.boxi.product.service.ProductAttributeDevisionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/core-api/productattributedevision")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class ProductAttributeDevisionApi {
    private final ProductAttributeDevisionService productAttributeDevisionService;


    @GetMapping("/genPinCode")
    public Response genPinCode() {
        System.out.println("genPinCode");
        return Response.ok().setPayload(UUID.randomUUID().toString());
    }

    @PostMapping
    public Response create(@RequestBody ProductAttributeDevisionDto request) {

        log.warn(request.toJson());

        ProductAttributeDevisionDto response = productAttributeDevisionService.create(request);
        return Response.ok().setPayload(response);
    }

    @PutMapping
    public Response edit(@Valid @RequestBody ProductAttributeDevisionDto request) {
        log.warn(request.toJson());
        ProductAttributeDevisionDto response = productAttributeDevisionService.edit(request);
        return Response.ok().setPayload(response);
    }

    @DeleteMapping("/{id}")
    public Response delete(@PathVariable Long id) {
        productAttributeDevisionService.delete(id);
        return Response.ok();
    }

    @PostMapping("/filter")
    public Response filter(@RequestParam(name = "pageNumber", defaultValue = "1", required = false) Integer pageNumber,
                           @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                           @RequestBody FilterProductAttributeDevision request) {
        log.warn(request.toJson());
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        Page<ProductAttributeDevisionDto> response = productAttributeDevisionService.filter(request, pageable);
        return Response.ok().setPayload(response);
    }

    @GetMapping("/select")
    public Response select(@RequestParam(name = "filter" ) String filter) {
        Page<SelectResponse> response = productAttributeDevisionService.select(filter);
        return Response.ok().setPayload(response);
    }


}
