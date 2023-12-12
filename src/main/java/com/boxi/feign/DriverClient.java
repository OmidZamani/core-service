package com.boxi.feign;

import com.boxi.core.response.Response;
import com.boxi.feign.dto.DriverDto;
import com.boxi.feign.dto.DriverShiftDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@FeignClient(name = "resource-api-driver", url = "${feign.client.url}/resource-api/driver")
public interface DriverClient {
    @PostMapping()
    Response create(@RequestBody DriverDto dto);

    @PostMapping("/driver")
    DriverDto createdriver(@RequestBody DriverDto dto);

    @PostMapping("/findbyidlist/{id}")
    List<DriverShiftDto> findbyIdreturnDto(@PathVariable List<Long> id);


    @GetMapping("/findbyid/{id}")
    DriverDto findbyIdbyid(@PathVariable Long id);

    @PostMapping("/listAbsentDriver/{hubid}")
    List<DriverDto> listAbsentDriver(@RequestBody List<Long> id, @PathVariable Long hubid);

    @PostMapping("/updateDriverPresence")
    DriverDto updateDriverPresence(@RequestBody DriverDto dto);


    @PutMapping("/driver")
    DriverDto editdriver(@RequestBody DriverDto dto);

    @GetMapping("/findByUserId/{id}")
    DriverDto findByUserId(@PathVariable Long id);
}
