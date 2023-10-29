package com.boxi.PriceList.payload.dto;

import com.boxi.core.response.SelectResponse;
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
    private String timeTo;
    private String timeFrom;
    private SelectResponse timeType;
//   TimeCommitment Details
    private Double timeCommitmentFrom;
    private Double timeCommitmentTo;
    private Long timeCommitmentTimeUnit;
    private Long timeCommitmentId;
}
