package com.boxi.hub.service;

import com.boxi.core.response.SelectResponse;
import com.boxi.hub.entity.CustomCountryDevision;
import com.boxi.hub.payload.dto.CustomCountryDevisionDto;
import com.boxi.hub.payload.dto.CustomCountryDevisionExcelDto;
import com.boxi.hub.payload.dto.CustomCountryDevisionFilterDto;
import com.boxi.hub.payload.request.FilterCustomCountryDevision;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomCountryDevisionService {

    CustomCountryDevisionDto create(CustomCountryDevisionDto request);

    Page<CustomCountryDevisionFilterDto> filter(FilterCustomCountryDevision filter, Pageable pageable);

    SelectResponse toSelect(CustomCountryDevision countryDevision);

    CustomCountryDevision fromSelect(SelectResponse select) ;

    Page<SelectResponse> select(String filter);

    void delete(Long id);

    CustomCountryDevisionDto edit(CustomCountryDevisionDto request);

    boolean ExcelValidation(List<CustomCountryDevisionExcelDto> exceptionExcelDtos);

    List<CustomCountryDevisionDto> ImportExcel(List<CustomCountryDevisionExcelDto> customCountryDevisionDtos);
}
