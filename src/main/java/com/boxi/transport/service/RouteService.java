package com.boxi.transport.service;

import com.boxi.core.request.RouteFilter;
import com.boxi.core.response.SelectResponse;
import com.boxi.transport.entity.Route;
import com.boxi.transport.payload.dto.RouteDto;
import com.boxi.transport.payload.dto.RouteExcelDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RouteService {

    RouteDto create(RouteDto request);

    RouteDto edit(RouteDto request);

    Route findById(Long id);

    void delete(Long id);

    Page<RouteDto> filter(RouteFilter filter, Pageable pageable);

    Page<SelectResponse> select(String filter);

    Route fromSelect(SelectResponse select);

    SelectResponse toSelect(Route entity);

    RouteDto get(Long id);

    boolean ExcelValidation(List<RouteExcelDto> routeExcelList);

    List<RouteDto> ImportExcel(List<RouteExcelDto> routeExcelList);

    Page<RouteDto> selectRoute(Long source, Long destination);
}
