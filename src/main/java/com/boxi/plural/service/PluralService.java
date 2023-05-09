package com.boxi.plural.service;

import com.boxi.plural.service.payload.ConsignmentFilterAsset;
import com.boxi.plural.service.payload.ConsignmentPluralRequest;

public interface PluralService {
    ConsignmentFilterAsset fetchData(ConsignmentPluralRequest request);
}
