package com.boxi.hub.service;

import com.boxi.hub.payload.dto.SharePercentageDto;

public interface SharePercentageService {

    SharePercentageDto create(SharePercentageDto dto);

    SharePercentageDto edit(SharePercentageDto dto);

    SharePercentageDto findById(Long id);


}
