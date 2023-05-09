package com.boxi.hub.payload.dto;

import com.boxi.core.response.SelectResponse;
import com.boxi.product.response.ContryDevistionSelect;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString
public class CustomDevisionDetailFilterDto {
    private Long id;
    private Boolean isActive;
    private Boolean isDeleted;
    private SelectResponse customDevision;
    private List<ContryDevistionSelect> toCountryDevision;
    private List<ContryDevistionSelect> fromCountryDevision;
}
