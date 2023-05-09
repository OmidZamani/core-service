package com.boxi.transport.service;

import com.boxi.core.response.SelectResponse;
import com.boxi.transport.entity.Vehicle;
import com.boxi.transport.payload.dto.FilterVehicle;
import com.boxi.transport.payload.dto.AdmVehicleDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdmVehicleService {
    Page<SelectResponse> select(String filter);
    Page<AdmVehicleDto> filter(FilterVehicle request, Pageable pageable);
    AdmVehicleDto create(AdmVehicleDto request);
    AdmVehicleDto edit(AdmVehicleDto request);
    Vehicle findById(Long id);
    void delete(Long id);
    AdmVehicleDto get(Long id);
}
