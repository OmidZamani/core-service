package com.boxi.PriceList.payload.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ExcelSelectResponseDto {
    private String ParentCode;
    private String Code;
    private String Name;

}
