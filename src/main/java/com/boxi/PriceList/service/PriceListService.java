package com.boxi.PriceList.service;

import com.boxi.PriceList.entity.PriceList;
import com.boxi.PriceList.payload.dto.*;
import com.boxi.PriceList.payload.request.FilterPriceList;
import com.boxi.core.response.SelectResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PriceListService {

    PriceListDto create(PriceListDto request);

    PriceListDto edit(PriceListDto request);

    PriceListFilterDto findById(Long id);

    Page<PriceListFilterDto> filter(FilterPriceList request, Pageable pageable);

    Page<PriceListFilterDto> filter_enhanced(FilterPriceList filter, Pageable pageable);

    PriceListDetailDto SelectEdit(String filter);

    List<SelectResponse> consignmentType();

    List<PriceListFilterDto> SE(String filter);

    void Delete (Long Id);

    void DeletePriceListDetails(Long Id);

    List<SelectResponse> SelectList();

    boolean ExcelValidation(List<PriceListExcelDto> priceListExcelList);

    List<PriceListDto> ImportExcel(List<PriceListExcelDto> priceListExcelList);

    List<PriceListSuggestDto> check(FilterPriceList filter, Pageable pageable);


    PriceListDto findByIdAndIsActiveTrue(Long id);

    List<PriceListDetailDto> findByIdAndIsActiveTrueByDetails(Long id, ConsignmentInfoDto dto);

    Iterable<PriceList> findAll();
}
