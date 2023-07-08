package com.boxi.ruleEngine.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ProductPriceRequest {
    private Double weight;
    private String cdt; //رده جغرافیایی
    private String serviceCode;
    private Double timeCommitmentDuration; //مدت ارایه خدمت to-from
    private Double timeCommitmentFrom; //from
    private String countryDevision;
}
