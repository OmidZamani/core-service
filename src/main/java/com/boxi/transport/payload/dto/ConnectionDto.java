package com.boxi.transport.payload.dto;

import com.boxi.core.request.JsonBase;
import com.boxi.core.response.SelectResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class ConnectionDto extends JsonBase {


    private Long id;

    private SelectResponse selectHub;

    private Double distanceFromPreviousHub; //فاصله از هاب قبلی

    private Double distanceVariance;// درصد انحراف

    private String transitTime; //مدت مسیر

    private String timeStoppage; //مدت توقف

    private SelectResponse selectRoute;

}
