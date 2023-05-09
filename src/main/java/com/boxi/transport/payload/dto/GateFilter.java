package com.boxi.transport.payload.dto;

import com.boxi.core.request.JsonBase;
import com.boxi.hub.payload.dto.HubPermissionDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class GateFilter extends JsonBase {
    private String hub;
    private String code;
    private List<HubPermissionDto> hublist;
}
