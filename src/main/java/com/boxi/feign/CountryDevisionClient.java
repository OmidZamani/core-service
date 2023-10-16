package com.boxi.feign;

import com.boxi.product.payload.dto.CountryDevisionDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name="core-service",url = "${feign.client.url}/resource-api/countryDevision")
public interface CountryDevisionClient {
    @GetMapping()
    public List<CountryDevisionDto> getItems(@RequestParam Long id);
}
