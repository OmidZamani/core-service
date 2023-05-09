package com.boxi.PriceList.api;

import com.boxi.PriceList.payload.dto.SuggestionPriceDto;
import com.boxi.PriceList.payload.request.PriceListRequest;
import com.boxi.PriceList.service.impl.CalculatePriceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/core-api/price-service")
@RequiredArgsConstructor
@CrossOrigin(origins = "*",allowedHeaders = "*")
@Slf4j
public class CalculatePriceExchange {

    @Autowired
    CalculatePriceService calculatePriceService;


}
