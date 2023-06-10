package com.boxi.transport.service;

import com.boxi.core.response.SelectResponse;
import com.boxi.transport.entity.VehicleMake;
import com.boxi.transport.payload.dto.VehicleMakeDto;
import com.boxi.transport.payload.dto.VehicleMakeExcelDto;
import com.boxi.transport.payload.request.VehicleMakeFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VehicleMakeService {
    Page<SelectResponse> select(String filter);

    Page<VehicleMakeDto> filter(VehicleMakeFilter filter, Pageable pageable);

    VehicleMakeDto create(VehicleMakeDto request);

    VehicleMakeDto edit(VehicleMakeDto request);

    VehicleMake findById(Long id);

    VehicleMake fromSelect(SelectResponse select);

    SelectResponse toSelect(VehicleMake entity);

    void delete(Long id);

    VehicleMakeDto get(Long id);

    boolean ExcelValidation(List<VehicleMakeExcelDto> vehicleMakeExcelList);

    List<VehicleMakeDto> ImportExcel(List<VehicleMakeExcelDto> vehicleMakeExcelList);
}
