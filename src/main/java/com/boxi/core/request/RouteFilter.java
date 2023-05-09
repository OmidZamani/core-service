package com.boxi.core.request;

import com.boxi.core.response.SelectResponse;
import com.boxi.hub.entity.Hub;
import lombok.Data;

@Data
public class RouteFilter extends JsonBase {
    private String name;
    private String hubName;
    private Boolean isActive;
    private SelectResponse sourceHub;
    private SelectResponse targetHub;

}
