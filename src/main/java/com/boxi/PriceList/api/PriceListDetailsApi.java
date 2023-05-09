package com.boxi.PriceList.api;

import com.boxi.PriceList.service.PriceListService;
import com.boxi.core.response.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/core-api/pricelistdeteil")
@RequiredArgsConstructor
@CrossOrigin(origins = "*",allowedHeaders = "*")
@Slf4j
public class PriceListDetailsApi {

    private final PriceListService priceListService;

    @DeleteMapping("/{id}")
    public Response delete(@PathVariable Long id) {
        priceListService.DeletePriceListDetails(id);
        return  Response.ok();
    }
}
