package com.boxi.feign;

import com.boxi.feign.dto.CustomerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CustomerClient", url = "http://boxi:40000/customer-api")
public interface CustomerClient {

    @GetMapping("/customer/findbyid/{id}")
    public CustomerDto getfindById(@PathVariable Long id);
}
