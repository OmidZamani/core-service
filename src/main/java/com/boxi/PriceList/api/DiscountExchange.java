package com.boxi.PriceList.api;


import com.boxi.PriceList.payload.request.DiscountRequest;
import com.boxi.PriceList.payload.response.AvailableDiscountResponse;
import com.boxi.PriceList.service.ServiceDeliveryService;
import com.boxi.PriceList.service.ServiceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping( "/core-api/discount-service")
@RequiredArgsConstructor
@CrossOrigin(origins = "*",allowedHeaders = "*")
@Slf4j
public class DiscountExchange {

    @Autowired
    ServiceDeliveryService _service;


    @PostMapping("/percent")
    public BigDecimal getallpercentoflist(@RequestBody DiscountRequest request){
        return  _service.findAllDiscountPercent(request);
    }

}
