package com.boxi.PriceList.payload.dto;

import com.boxi.core.request.DateDto;
import com.boxi.core.response.SelectResponse;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class ConsignmentInfoDto {

    private Long consignmentId;

    private Long fromStateId;

    private Long fromCityId;

    private Long fromRegionId;

    private Long toStateId;

    private Long toCityId;

    private Long toRegionId;

    private Double declarativeWeight;

    private Double weight;

    private Double declarativeVolume;

    private Double volume;

    private Double declarativeLength;

    private Double length; // compare

    private Double declarativeWidth;

    private Double declarativeHeight;

    private BigDecimal declarativeValue;

    private Long numberOfPieces;

    private DateDto pickUpDate; //زمان پیک آپ

    private String pickUpTimeFrom;//زمان اظهاری طرف - که میخواهد مرسوله را در این زمان پیک آپ شود

    private String pickUpTimeTo;


    private SelectResponse selectContentType;
    private SelectResponse selectConsignmentType;

    private String content;

    private SelectResponse consignmentType;
}
