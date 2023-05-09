package com.boxi.transport.service;


import com.boxi.core.request.GenericFilter;
import com.boxi.core.response.SelectResponse;
import com.boxi.transport.entity.VehicleCategory;
import com.boxi.transport.payload.dto.VehicleCategoryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VehicleCategoryService {
    VehicleCategoryDto createVehicleCategory(VehicleCategoryDto request);

    VehicleCategory findById(Long id);

    void delete(Long id);

    Page<VehicleCategoryDto> filter(GenericFilter request, Pageable pageable);

    Page<SelectResponse> select(String search);

    VehicleCategoryDto editVehicleCategory(VehicleCategoryDto request);

    VehicleCategory fromSelect(SelectResponse select);

    SelectResponse toSelect(VehicleCategory vehicleCategory);

}
