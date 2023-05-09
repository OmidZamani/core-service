package com.boxi.transport.payload.request;

import com.boxi.hub.payload.dto.HubPermissionDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class HubFilter {
    private List<HubPermissionDto> hublist;
}
