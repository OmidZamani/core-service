package com.boxi.ruleEngine.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ProductPriceRequest {
    private Double weight;
    private String cdt; //رده جغرافیایی
    private String  timeCommitment; //مدت ارایه خدمت 12:10 format
    private String serviceCode;
    //....TODO add other values
}
