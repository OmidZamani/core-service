package com.boxi.trm.payload.request;

import com.boxi.core.request.JsonBase;
import com.boxi.core.response.SelectResponse;
import com.boxi.hub.payload.dto.HubPermissionDto;
import lombok.Data;

import java.util.List;

@Data
public class FilterCalendar
        extends JsonBase {

    private Long year;
    private Long month;
    private Long day;
    private SelectResponse selecthub;
    private SelectResponse calendarhub;
    private String name;
    private Boolean isActive;
    List<HubPermissionDto> hublist;



}
