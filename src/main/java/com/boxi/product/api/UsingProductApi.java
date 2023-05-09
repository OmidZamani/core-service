package com.boxi.product.api;

import com.boxi.core.response.Response;
import com.boxi.core.response.SelectResponse;
import com.boxi.product.payload.dto.UsingProductDto;
import com.boxi.product.payload.request.FilterUsingProduct;
import com.boxi.product.service.UsingProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping( "/core-api/usingproduct")
@RequiredArgsConstructor
@CrossOrigin(origins = "*",allowedHeaders = "*")
@Slf4j
public class UsingProductApi {

    private final UsingProductService usingProductService;


    @GetMapping("/genPinCode")
    public Response genPinCode() {
        System.out.println("genPinCode");
        return  Response.ok().setPayload(UUID.randomUUID().toString());

    }

    @PostMapping
    public Response createproduct(@RequestBody UsingProductDto request) {

        log.warn(request.toJson());
        UsingProductDto response=usingProductService.create(request);
        return  Response.ok().setPayload(response);
    }
    @PutMapping
    public Response edit(@Valid @RequestBody UsingProductDto request) {
        log.warn(request.toJson());
        UsingProductDto response=usingProductService.edit(request);
        return  Response.ok().setPayload(response);
    }
    @DeleteMapping("/{id}")
    public Response delete(@PathVariable Long id) {
        usingProductService.delete(id);
        return  Response.ok();
    }
    @PostMapping("/filter")
    public Response filter(@RequestParam(name = "pageNumber",defaultValue = "1",required=false) Integer pageNumber,
                           @RequestParam(name = "pageSize",defaultValue = "10",required=false) Integer pageSize,
                           @RequestBody FilterUsingProduct request) {
        log.warn(request.toJson());
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize);
        Page<UsingProductDto> response = usingProductService.filter(request,pageable);
        return  Response.ok().setPayload(response);
    }

    @GetMapping("/select")
    public Response select(@RequestParam(name = "filter",required = true) String filter) {
        Page<SelectResponse> response = usingProductService.select(filter);
        return  Response.ok().setPayload(response);
    }

}
