package com.boxi.product.service;

import com.boxi.core.response.SelectResponse;
import com.boxi.product.entity.TimeCommitment;
import com.boxi.product.payload.dto.TimeCommitmentDto;
import com.boxi.product.payload.request.FilterTimeCommitment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TimeCommitmentService {

    Page<SelectResponse> select(String filter);

    TimeCommitment fromSelect(SelectResponse select);

    SelectResponse toSelect(TimeCommitment entity);

    TimeCommitmentDto create(TimeCommitmentDto request);

    TimeCommitmentDto edit(TimeCommitmentDto request);

    Page<TimeCommitmentDto> filter(FilterTimeCommitment filter, Pageable pageable);

    void delete(Long id);


}
