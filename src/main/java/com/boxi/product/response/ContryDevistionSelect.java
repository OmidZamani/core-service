package com.boxi.product.response;

import com.boxi.core.response.SelectResponse;
import com.boxi.hub.enums.CountryType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode()
@ToString
@Data
public class ContryDevistionSelect {
    private Long id;
    private String text;
    private SelectResponse countryType;
    private String shahrCode;
    private String shahrestanCode;
    private String ostanCode;

}
