package com.boxi.product.payload.dto;

import com.boxi.hub.enums.CountryType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode()
@ToString
@Data
public class ContryDevistionCodeDto {
    private Long id;
    private String text;
    private String code;
    private CountryType countryType;

}
