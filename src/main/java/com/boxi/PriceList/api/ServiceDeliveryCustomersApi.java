package com.boxi.PriceList.api;

import com.boxi.PriceList.service.ServiceDeliveryCustomersService;
import com.boxi.core.response.Response;
import com.boxi.core.response.SelectResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/core-api/servicedeliverycustomers")
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*",allowedHeaders = "*")
@Slf4j

public class ServiceDeliveryCustomersApi {
    private final ServiceDeliveryCustomersService service;
    @GetMapping("/select")
    public Response select(@RequestParam(name = "filter") String filter) {
        List<SelectResponse> response =service.select(filter);
        return  Response.ok().setPayload(response);
    }
}
