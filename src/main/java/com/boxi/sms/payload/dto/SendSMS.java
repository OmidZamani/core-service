package com.boxi.sms.payload.dto;

import com.boxi.core.request.JsonBase;
import lombok.Data;

@Data
public class SendSMS extends JsonBase {
    private String userName;
    private String password;
    private String from;
    private String to;
    private String message;

}
