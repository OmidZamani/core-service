package com.boxi.sms.service.Impl;


import com.boxi.sms.payload.dto.SendSMS;
import com.boxi.sms.payload.dto.SendSmsDto;
import com.boxi.sms.payload.dto.SmsDto;
import com.boxi.sms.service.SmsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.shaded.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SmsServiceImpl implements SmsService {


    @Override
    public SmsDto sendSms(SendSmsDto dto) {
        return sendSMS(dto);
    }


    private SmsDto sendSMS(SendSmsDto dto) {
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = "https://media.sms24.ir/SMSInOutBox/Send";
//        SMSBody.setMessage("سرکار خانم دکتر حقیری، سرکار خانم خوشامن برای شما درخواست ویزیت آنلاین دارد");


        JSONObject jsonObject = new JSONObject();
        jsonObject.put("UserName", "mt.09358050512");
        jsonObject.put("Password", "oaa#636");
        jsonObject.put("From", "10009611");
        jsonObject.put("To", dto.getPhoneNumber());
        jsonObject.put("Message", dto.getMessage());
        ResponseEntity<String> response
                = restTemplate.postForEntity(resourceUrl, jsonObject, String.class);
        SmsDto smsDto = new SmsDto();
        smsDto.setBody(response.getBody());
        return smsDto;
    }
}
