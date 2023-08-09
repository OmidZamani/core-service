package com.boxi.sms.payload.dto;

import com.boxi.core.request.JsonBase;
import lombok.Data;

@Data
public class SendSmsDto  extends JsonBase {
    private String phoneNumber ;
    private String message ;
    private String[] mobiles ;
    private String[] userGroupID;
    private Long sendDateInTimeStamp;
}




