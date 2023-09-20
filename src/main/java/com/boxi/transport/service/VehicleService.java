package com.boxi.transport.service;

import com.boxi.core.response.SelectResponse;
import com.boxi.transport.entity.Vehicle;
import com.boxi.transport.payload.dto.*;
import com.boxi.transport.payload.request.HubFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VehicleService {
    Page<SelectResponse> select(String filter, HubFilter hubFilter);

    Page<VehicleDto> filter(FilterVehicle request, Pageable pageable);

    VehicleDto create(VehicleDto request);
    VehicleDto createClient(VehicleDto request);

    VehicleDto edit(VehicleDto request);

    Vehicle findById(Long id);

    VehicleDto clientFindById(Long id);

    void delete(Long id);

    boolean ExcelValidation(List<VehicleExcelDto> vehicleExcelList);

    List<VehicleDto> ImportExcel(List<VehicleExcelDto> vehicleExcelList);

    List<SelectResponse> getVehicleType();

    VehicleDto clientFindByCarTag(CarTagDto dto);

    VehicleExceptionsDto createException(VehicleExceptionsDto dto);

    List<Long> findByType(Long type, Long HubId);

    List<Long> findByMake(Long type, Long HubId);

    VehicleDto updateAssignable(VehicleDto dto);

    List<Long> findByVendor(Long id);

    VehicleMakeDto findByMakeById(Long id);

    List<CarTagDto> findByCarTagNumberSuggest(CarTagDto dto);

    List<VehicleExceptionsDto> findVehicleException(VehicleExceptionsDto dto);

    Page<VehicleDto> listOfVehicleInHub(FilterVehicle request, Pageable pageable);


    List<AdmVehicleDto> listOfAdmVehicleInHub(AdmVehicleDto dto);

    List<CarTagDto> clientSelect(CarTagDto dto, Long hubId);

    VehicleDto findByDriverId(Long driverId);
}
