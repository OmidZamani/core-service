package com.boxi.product.payload.dto;

import com.boxi.product.response.ContryDevistionSelect;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
public class ContryDevistionFromTO {
    private List<ContryDevistionSelect> fromCountryDevision;
    private List<ContryDevistionSelect> toCountryDevision;
}
