package com.boxi.crm.service;

import com.boxi.core.response.SelectResponse;
import com.boxi.crm.payload.dto.CustomerSegmentDto;

import com.boxi.crm.payload.request.CustomerSegmentFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerSegmentService {
    List<SelectResponse> Select(String filter);
    CustomerSegmentDto create(CustomerSegmentDto customerSegmentDto);
    CustomerSegmentDto edit(CustomerSegmentDto customerSegmentDto);
    Page<CustomerSegmentDto> filter(CustomerSegmentFilter filter, Pageable pageable);
    CustomerSegmentDto findedit(Long id);
    void delete(Long id);

}
