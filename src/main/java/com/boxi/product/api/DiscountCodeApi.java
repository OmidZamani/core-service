package com.boxi.product.api;

import com.boxi.core.response.Response;
import com.boxi.product.payload.dto.DiscountCodeDto;
import com.boxi.product.payload.dto.FilterDiscountCodeDto;
import com.boxi.product.payload.request.FilterProduct;
import com.boxi.product.service.DiscountCodeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/core-api/discountCode")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class DiscountCodeApi {
    private final DiscountCodeService discountCodeService;

    @PostMapping()
    public Response create(@RequestBody DiscountCodeDto dto) {
        return Response.ok().setPayload(discountCodeService.create(dto));
    }

    @PutMapping()
    public Response edit(@RequestBody DiscountCodeDto dto) {
        return Response.ok().setPayload(discountCodeService.edit(dto));
    }

    @GetMapping("/{id}")
    public Response findById(@PathVariable Long id) {
        return Response.ok().setPayload(discountCodeService.findById(id));
    }

    @DeleteMapping("/{id}")
    public Response delete(@PathVariable Long id) {
        discountCodeService.delete(id);
        return Response.ok();
    }

    @PostMapping("/filter")
    public Response filter(@RequestParam(name = "pageNumber", defaultValue = "1", required = false) Integer pageNumber, @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize, @RequestBody FilterDiscountCodeDto request) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        return Response.ok().setPayload(discountCodeService.filter(request, pageable));
    }

    @GetMapping("/select")
    public Response select(@RequestParam(name = "filter") String filter) {
        return Response.ok().setPayload(discountCodeService.select(filter));
    }
}
