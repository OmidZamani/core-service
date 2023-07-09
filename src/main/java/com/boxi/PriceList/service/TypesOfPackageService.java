package com.boxi.PriceList.service;

import com.boxi.PriceList.payload.dto.TypesOfPackageDto;
import com.boxi.core.response.SelectResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TypesOfPackageService {
    TypesOfPackageDto create(TypesOfPackageDto dto);

    TypesOfPackageDto edit(TypesOfPackageDto dto);

    TypesOfPackageDto findById(Long id);

    void delete(Long id);

    Page<TypesOfPackageDto> filter(TypesOfPackageDto filter, Pageable pageable);

    List<SelectResponse> select(String filter);
}
