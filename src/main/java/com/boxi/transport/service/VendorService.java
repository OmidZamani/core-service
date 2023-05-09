package com.boxi.transport.service;

import com.boxi.core.response.SelectResponse;
import com.boxi.transport.entity.Vendor;
import com.boxi.transport.payload.dto.VendorDto;
import com.boxi.transport.payload.dto.VendorExcelDto;
import com.boxi.core.request.GenericFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VendorService {
    VendorDto createVendor(VendorDto request);
    VendorDto updateVendor(VendorDto request);
    Page<VendorDto> filter(GenericFilter request, Pageable pageable);
    Vendor findById(Long id);
    void delete(Long id);
    Page<SelectResponse> select(String filter);
    Vendor fromSelect(SelectResponse select);
    SelectResponse toSelect(Vendor vendor);
    void createByExcel(MultipartFile excel);

    boolean ExcelValidation(List<VendorExcelDto> vendorExcelDtos);

    List<VendorDto> ImportExcel(List<VendorExcelDto> vendorExcelDtos);
}
