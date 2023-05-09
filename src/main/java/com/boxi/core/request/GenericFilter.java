package com.boxi.core.request;

import lombok.Data;

@Data
public class GenericFilter extends JsonBase {
    private String search;
    private Boolean isActive;

}
