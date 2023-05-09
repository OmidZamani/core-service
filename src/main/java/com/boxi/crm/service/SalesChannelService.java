package com.boxi.crm.service;

import com.boxi.core.request.GenericFilter;
import com.boxi.core.response.SelectResponse;
import com.boxi.crm.payload.dto.CustomerSegmentDto;
import com.boxi.crm.payload.dto.SalesChannelDto;
import com.boxi.crm.payload.request.CustomerSegmentFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SalesChannelService {

    List<SelectResponse> SelectChannel(String filter);
    SalesChannelDto create(SalesChannelDto dto);
    SalesChannelDto edit(SalesChannelDto dto);
    Page<SalesChannelDto> filter(GenericFilter filter, Pageable pageable);
    void delete(Long id);

}
