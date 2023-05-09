package com.boxi.PriceList.service;

import com.boxi.PriceList.entity.Exception;
import com.boxi.PriceList.payload.dto.ExceptionDto;
import com.boxi.PriceList.payload.dto.ExceptionExcelDto;
import com.boxi.PriceList.payload.dto.FindVehicleInException;
import com.boxi.PriceList.payload.request.FilterException;
import com.boxi.core.response.SelectResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ExceptionService {
    ExceptionDto create(ExceptionDto Dto);

    ExceptionDto edit(ExceptionDto Dto);

    void delete(Long Id);

    Page<ExceptionDto> filter(FilterException filter, Pageable pageable);

    List<ExceptionDto> ImportExcel(List<ExceptionExcelDto> exceptionExcelDtos);

    boolean ExcelValidation(List<ExceptionExcelDto> exceptionExcelDtos);

    Exception findById(Long id);

    Page<SelectResponse> select(String filter);

    List<SelectResponse> selectByType(String filter, Long type);

    List<ExceptionDto> vehicleException(FindVehicleInException vehicle);
}
