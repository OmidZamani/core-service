package com.boxi.plural.service.payload;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ConsignmentPluralRequest {

    private Long deliveryHubId;

    private Long originHubId=0L;

    private Long destinationHubId=0L;// هاب مقصد

    private Long lastExceptionId; //آخرین خطای مبادله

    private Long nextHubId=0L; //from connection

    private Long activeHubId=0L;

    private Long bagId;

}
