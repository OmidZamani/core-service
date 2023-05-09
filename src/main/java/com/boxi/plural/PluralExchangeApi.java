package com.boxi.plural;

import com.boxi.plural.service.PluralService;
import com.boxi.plural.service.payload.ConsignmentFilterAsset;
import com.boxi.plural.service.payload.ConsignmentPluralRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/core-api/plural")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PluralExchangeApi {

    @Autowired
    PluralService pluralService;

    @PostMapping("/consignmentFilterAsset")
    public ConsignmentFilterAsset get(@RequestBody ConsignmentPluralRequest request) {
        return pluralService.fetchData(request);
    }
}
