package com.boxi.hub.payload.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class HubPermissionDto {
    private Long id;
    private String value;
    private String label;
    private Long parent;
    private List<HubPermissionDto> children;

}
