package com.boxi.sms.service;

import com.boxi.sms.payload.dto.SendSmsDto;
import com.boxi.sms.payload.dto.SmsDto;

public interface SmsService {
    SmsDto sendSms(SendSmsDto dto);
}
