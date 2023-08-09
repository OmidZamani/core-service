package com.boxi.sms.payload.dto;

import com.boxi.core.response.SelectResponse;
import lombok.Data;

@Data
public class SmsDto {
    private String mobile;
    private String body;
    private SelectResponse senderUserId;
    private SelectResponse systemCallService;

}
