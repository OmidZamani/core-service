package com.boxi.hub.payload.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SimpleHubDto {
    private Long id;

    private String code;

    private String name;

}
