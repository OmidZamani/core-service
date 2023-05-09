package com.boxi.hub.payload.dto;

import com.boxi.core.request.JsonBase;
import com.boxi.core.response.SelectResponse;
import lombok.Data;

import java.util.List;

@Data
public class ZoneDto extends JsonBase {
    private SelectResponse selectHub;
    private SelectResponse selectuser;
    private SelectResponse countrydevision;
    private SelectResponse countrytype;
    private String polygon;
}
