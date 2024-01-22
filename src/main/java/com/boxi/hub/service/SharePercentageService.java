package com.boxi.hub.service;

import com.boxi.hub.payload.dto.SharePercentageDto;

import java.math.BigDecimal;
import java.util.List;

public interface SharePercentageService {

    SharePercentageDto create(SharePercentageDto dto);

    SharePercentageDto edit(SharePercentageDto dto);

    SharePercentageDto findById(Long id);


    List<SharePercentageDto> fetchListOfPercentageByPrice(BigDecimal price);
}
