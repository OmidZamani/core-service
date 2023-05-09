package com.boxi.transport.payload.dto;

import com.boxi.core.request.JsonBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class ConnectionExcelDto extends JsonBase {




    private String code; //کد پرنت Route

    private String selectHub;

    private Double distanceFromPreviousHub; //فاصله از هاب قبلی

    private Double distanceVariance;// درصد انحراف

    private String transitTime; //مدت مسیر

    private String timeStoppage; //مدت توقف


}
