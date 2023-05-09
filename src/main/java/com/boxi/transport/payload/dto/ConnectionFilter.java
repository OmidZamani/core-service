package com.boxi.transport.payload.dto;

import com.boxi.core.request.JsonBase;
import com.boxi.hub.payload.dto.HubPermissionDto;
import lombok.Data;

import java.util.List;

@Data
public class ConnectionFilter extends JsonBase {
    private String hub;
    private String route;
    private List<HubPermissionDto> hublist;
}
