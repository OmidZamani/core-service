package com.boxi.transport.service;

import com.boxi.core.response.SelectResponse;
import com.boxi.transport.entity.Gate;
import com.boxi.transport.payload.dto.GateDto;
import com.boxi.transport.payload.dto.GateExcelDto;
import com.boxi.transport.payload.dto.GateFilter;
import com.boxi.transport.payload.request.HubFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface GateService {

    GateDto create(GateDto request);

    GateDto edit(GateDto request);

    void delete(Long id);

    Page<GateDto> filter(GateFilter filter, Pageable pageable);

    Page<SelectResponse> select(String filter, HubFilter hubFilter);

    Gate findById(Long id);

    GateDto findByIddto(Long id);

    Gate fromSelect(SelectResponse select);


    SelectResponse toSelect(Gate entity);

    boolean ExcelValidation(List<GateExcelDto> gateExcelDtos);

    List<GateDto> ImportExcel(List<GateExcelDto> gateExcelDtos);
}
