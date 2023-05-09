package com.boxi.hub.payload.request;

import com.boxi.core.request.JsonBase;
import lombok.Data;

@Data
public class IranProvinceAndCityRequest extends JsonBase {
    private String filter;
}
