package com.boxi.PriceList.payload.dto;

import com.boxi.PriceList.Enum.ServiceType;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/*use in ui of consignment*/
@Data
@Accessors(chain = true)
public class SuggestionServiceDto {
    private Long id;
    private String name;
    private String code;
    private BigDecimal price;
    private Long serviceType;
    private Boolean supplementary;
}
