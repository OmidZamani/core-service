package com.boxi.PriceList.service;

import com.boxi.core.response.SelectResponse;
import com.boxi.feign.dto.CustomerDto;

import java.util.List;

public interface ServiceDeliveryCustomersService {
    List<SelectResponse> select(String filter);
    CustomerDto loadCustomerById(Long Id);
}
