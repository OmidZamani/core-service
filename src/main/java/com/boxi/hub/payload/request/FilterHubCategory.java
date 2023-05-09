package com.boxi.hub.payload.request;

import com.boxi.core.request.JsonBase;
import lombok.Data;

@Data
public class FilterHubCategory extends JsonBase {
    private String code;
    private String name;
}
