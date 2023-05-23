package com.boxi.PriceList.payload.dto;

import com.boxi.PriceList.Enum.ServiceType;
import com.boxi.PriceList.entity.Services;
import com.boxi.core.response.SelectResponse;
import com.boxi.hub.entity.CountryDevision;
import com.boxi.product.payload.dto.CountryDevisionDto;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class TermsOfServicesDto {
    private Long id;
    private String serviceName;
    private SelectResponse serviceType;
    private String serviceDescription;
    private Date serviceValidDateFrom;
    private Date serviceValidDateTo;
    private SelectResponse consignmentType;
    private BigDecimal price;
    private String priceFormule;
    private Double fromWeight;
    private Double toWeight;
    private Double fromDim;
    private Double toDimension;
    private BigDecimal fromValue;
    private BigDecimal toValue;
    private Long fromNumber;
    private Long toNumber;
    private Long minimumOrderQuantity;
    private Double timeCommitmentFrom;
    private Double timeCommitmentTo;
    private Long timeCommitmentTimeUnit;
    private SelectResponse selectToCity;
    private SelectResponse selectFromCity;
    private SelectResponse selectService;
    private SelectResponse selectParentService;
}
