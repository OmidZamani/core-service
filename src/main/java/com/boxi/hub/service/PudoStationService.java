package com.boxi.hub.service;

import com.boxi.core.response.SelectResponse;
import com.boxi.hub.payload.dto.PudoStationDto;
import jdk.dynalink.linker.LinkerServices;
import org.h2.command.dml.Select;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PudoStationService {
    PudoStationDto create(PudoStationDto dto);

    PudoStationDto edit(PudoStationDto dto);

    PudoStationDto findById(Long id);

    void delete(Long id );

    Page<PudoStationDto> filter(PudoStationDto request, Pageable pageable);


    List<SelectResponse> select(String filter);
}