package com.boxi.plural.service.payload;

import com.boxi.core.response.SelectResponse;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ConsignmentFilterAsset {

    private SelectResponse deliveryBoundTime;//from delivery api //بازه زمانی تحویل

    private SelectResponse deliveryHub;

    private SelectResponse originHub;

    private SelectResponse destinationHub;// هاب مقصد

    private SelectResponse lastException; //آخرین خطای مبادله

    private SelectResponse nextHub; //from connection

    private SelectResponse bag;

    private SelectResponse activeHub; //هاب جاری

    //............................
    //not implemented

    private String IMEI_1="-1"; // دستگاه پز

    private String machine_1="-1"; //id device of pos

    private String IMEI_2="-1";

    private String machine_2="-1";

    private String IMEI_3="-1";

    private String machine_3="-1";
}
