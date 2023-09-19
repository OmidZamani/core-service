package com.boxi.transport.service;
import com.boxi.core.response.SelectResponse;
import com.boxi.transport.entity.Dock;
import com.boxi.transport.payload.dto.DockDto;
import com.boxi.transport.payload.dto.DockExcelDto;
import com.boxi.transport.payload.dto.DockFilter;
import com.boxi.transport.payload.request.HubFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DockService {
    Page<SelectResponse> select(String filter, HubFilter  hubFilter);

    Page<DockDto> filter(DockFilter filter, Pageable pageable);

    DockDto create(DockDto request);

    DockDto edit(DockDto request);

    Dock findById(Long id);

    DockDto findByIdDto(Long id);

    Dock fromSelect(SelectResponse select);

    SelectResponse toSelect(Dock entity);

    void delete(Long id);

    boolean ExcelValidation(List<DockExcelDto> dockExcelList);

    List<DockDto> ImportExcel(List<DockExcelDto> dockExcelList);

    DockDto updateStatus(DockDto dto);

    DockDto findByCodeInClient(String code);
}
