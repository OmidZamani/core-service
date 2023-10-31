package com.boxi.PriceList.api;

import com.boxi.PriceList.payload.dto.ConsignmentInfoDto;
import com.boxi.PriceList.payload.dto.ServiceDto;
import com.boxi.PriceList.payload.dto.SuggestionServiceDto;
import com.boxi.PriceList.service.ServiceService;
import com.boxi.PriceList.service.TermsOfServicesService;
import com.boxi.PriceList.service.impl.AvailableServiceSuggestion;
import com.boxi.core.response.SelectResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/core-api/available-service")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class AvailableServiceExchange {

    @Autowired
    AvailableServiceSuggestion availableServiceSuggestion;
    @Autowired
    ServiceService serviceService;

    @Autowired
    TermsOfServicesService termsOfServicesService;

    @PostMapping("/suggestionService")
    List<SuggestionServiceDto> post(@RequestBody ConsignmentInfoDto request) {
//        return availableServiceSuggestion.serviceSuggestionDetails(request);
        return termsOfServicesService.suggestionTermOfService(request,null);
    }

    @GetMapping("/{id}")
    List<SuggestionServiceDto> get(@PathVariable Long id) {
        return availableServiceSuggestion.serviceDetails(id);
    }

    @GetMapping("/exchange/basetypeselect")
    public List<SelectResponse> exchangeBaseTypeSelect() {
        return serviceService.baseTypeSelect();
    }

    @GetMapping("/exchange/findById/{id}")
    public ServiceDto serviceFindById(@PathVariable Long id) {
        return serviceService.findById(id);
    }

    @GetMapping("/findByDefaultServicePrice/{id}")
    public BigDecimal findByDefaultServicePrice(@PathVariable Long id){
        return serviceService.findByDefaultServicePrice(id);
    }
}
