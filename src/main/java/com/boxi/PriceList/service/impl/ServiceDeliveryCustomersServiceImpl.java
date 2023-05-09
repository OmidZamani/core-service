package com.boxi.PriceList.service.impl;

import com.boxi.PriceList.entity.ServiceDeliveryCustomers;
import com.boxi.PriceList.payload.converter.ServiceDeliveryCustomersConverter;
import com.boxi.PriceList.repo.ServiceDeliveryCustomersRepository;
import com.boxi.PriceList.service.ServiceDeliveryCustomersService;
import com.boxi.core.response.SelectResponse;
import com.boxi.feign.CustomerClient;
import com.boxi.feign.dto.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
@Service
@Transactional
public class ServiceDeliveryCustomersServiceImpl implements ServiceDeliveryCustomersService {
    private final ServiceDeliveryCustomersRepository serviceDeliveryCustomersRepository;
    private final ServiceDeliveryCustomersConverter serviceDeliveryCustomersConverter;

    @Autowired
    private CustomerClient customerClient;

    public ServiceDeliveryCustomersServiceImpl(ServiceDeliveryCustomersRepository serviceDeliveryCustomersRepository,
                                               ServiceDeliveryCustomersConverter serviceDeliveryCustomersConverter) {
        this.serviceDeliveryCustomersRepository = serviceDeliveryCustomersRepository;
        this.serviceDeliveryCustomersConverter = serviceDeliveryCustomersConverter;
    }


    @Override
    public List<SelectResponse> select(String filter) {
        List<ServiceDeliveryCustomers> all = serviceDeliveryCustomersRepository.findAll();
        List<SelectResponse> selectResponses = new ArrayList<>();
        for (ServiceDeliveryCustomers serviceDeliveryCustomers : all) {
            selectResponses.add(new SelectResponse(serviceDeliveryCustomers.getId(),serviceDeliveryCustomers.getServiceDelivery().getName()));
        }
        return selectResponses;

    }

    @Override
    public CustomerDto loadCustomerById(Long Id) {
        ServiceDeliveryCustomers serviceDeliveryCustomers = serviceDeliveryCustomersRepository.findById(Id).orElseThrow();
        return customerClient.getfindById(serviceDeliveryCustomers.getCustomerId());
    }
}
