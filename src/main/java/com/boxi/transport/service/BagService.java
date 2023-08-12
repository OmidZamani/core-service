package com.boxi.transport.service;
import com.boxi.core.response.SelectResponse;
import com.boxi.transport.entity.Bag;
import com.boxi.transport.payload.dto.BagDto;
import com.boxi.transport.payload.dto.BagExcelDto;
import com.boxi.transport.payload.dto.BagExceptionsDto;
import com.boxi.transport.payload.dto.BagFilter;
import com.boxi.transport.payload.request.HubFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BagService {
    Page<SelectResponse> select(String filter, HubFilter hubList);

    Page<BagDto> filter(BagFilter filter, Pageable pageable);

    BagDto create(BagDto request);

    BagDto edit(BagDto request);

    Bag findById(Long id);

    BagDto findByIdDto(Long id);

    SelectResponse toSelect(Bag entity);

    void delete(Long id);

    boolean ExcelValidation(List<BagExcelDto> bagExcelList);

    List<BagDto> ImportExcel(List<BagExcelDto> bagExcelList);

    BagDto findByBagNumber(String number);

    BagExceptionsDto createException(BagExceptionsDto dto);

    List<BagExceptionsDto> listOfException(BagExceptionsDto dto);

    BagDto editStatus(BagDto dto);

    List<SelectResponse> reportTotal(Long hubId);

    List<BagDto> bagList(String bagList);

    List<BagDto> bagListofList(String[] bagList);
}
