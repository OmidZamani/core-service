package com.boxi.transport.api;

import com.boxi.core.request.GenericFilter;
import com.boxi.core.response.Response;
import com.boxi.core.response.SelectResponse;
import com.boxi.transport.payload.dto.VehicleCategoryDto;
import com.boxi.transport.service.VehicleCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/core-api/vehicleCategory")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class VehicleCategoryApi {

    private final VehicleCategoryService _service;


    @PostMapping
    public Response create(@RequestBody VehicleCategoryDto request) {
        log.warn(request.toJson());
        VehicleCategoryDto response=_service.createVehicleCategory(request);
        return  Response.ok().setPayload(response);
    }

    @PutMapping
    public Response edit(@RequestBody VehicleCategoryDto request) {
        log.warn(request.toJson());
        VehicleCategoryDto response=_service.editVehicleCategory(request);
        return  Response.ok().setPayload(response);
    }

    @DeleteMapping("/{id}")
    public Response delete(@PathVariable Long id) {
        _service.delete(id);
        return  Response.ok();
    }


    @PostMapping("/filter")
    public Response filter(@RequestParam(name = "pageNumber",defaultValue = "1",required=false) Integer pageNumber,
                           @RequestParam(name = "pageSize",defaultValue = "10",required=false) Integer pageSize,
                           @RequestBody GenericFilter request) {
        log.warn(request.toJson());
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize);
        Page<VehicleCategoryDto> response = _service.filter(request,pageable);
        return  Response.ok().setPayload(response);
    }


    @GetMapping("/select")
    public Response select(@RequestParam(name = "filter",required = true) String filter) {
        Page<SelectResponse> response = _service.select(filter);
        return  Response.ok().setPayload(response);
    }
}
