package com.boxi.sms.api;

import com.boxi.core.response.Response;
import com.boxi.sms.payload.dto.SendSmsDto;
import com.boxi.sms.service.SmsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/core-api/sms")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class SmsApi {
    private final SmsService smsService;
    @PostMapping("/send")
    private Response sendSms(@RequestBody SendSmsDto smsDto){
        return Response.ok().setPayload(smsService.sendSms(smsDto));
    }
}
