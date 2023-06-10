package com.boxi.transport.api;
//1009	ADM vehicle	 AdmVehicleApi 	وسایل نقلیه اجاره ای

import com.boxi.core.response.Response;
import com.boxi.transport.enums.FleetType;
import com.boxi.transport.payload.dto.FilterVehicle;
import com.boxi.transport.payload.dto.AdmVehicleDto;
import com.boxi.transport.service.AdmVehicleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/core-api/admVehicle")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AdmVehicleApi {

    private final AdmVehicleService _service;


//    @PreAuthorize("hasPermission('hasAccess','100901')")
    @PostMapping
    public Response create(@RequestBody AdmVehicleDto request) {
        log.warn(request.toJson());
        AdmVehicleDto response = _service.create(request);
        return Response.ok().setPayload(response);
    }

    // @PreAuthorize("hasPermission('hasAccess','100903')")
    @PutMapping
    public Response edit(@RequestBody AdmVehicleDto request) {
        log.warn(request.toJson());
        AdmVehicleDto response = _service.edit(request);
        return Response.ok().setPayload(response);
    }

    // @PreAuthorize("hasPermission('hasAccess','100904')")
    @DeleteMapping("/{id}")
    public Response delete(@PathVariable Long id) {
        _service.delete(id);
        return Response.ok();
    }

    @PostMapping("/filter")
    public Response filter(@RequestParam(name = "pageNumber", defaultValue = "1", required = false) Integer pageNumber,
                           @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                           @RequestBody FilterVehicle request) {
        log.warn(request.toJson());
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        Page<AdmVehicleDto> response = _service.filter(request, pageable);
        return Response.ok().setPayload(response);
    }

    @GetMapping("/select")
    public Response select(@RequestParam(name = "filter" ) String filter) {
        return Response.ok().setPayload(_service.select(filter));
    }

    @GetMapping("/{id}")
    public Response get(@PathVariable Long id) {
        return Response.ok().setPayload(_service.get(id));
    }


    @GetMapping("/selectFleetTypes")
    public Response selectFleetTypes() {
        return Response.ok().setPayload(FleetType.select());
    }
}
