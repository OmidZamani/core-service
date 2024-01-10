package com.boxi.sms.service.Impl;


import com.boxi.sms.payload.dto.SendSMS;
import com.boxi.sms.payload.dto.SendSmsDto;
import com.boxi.sms.payload.dto.SmsDto;
import com.boxi.sms.service.SmsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.shaded.json.JSONObject;
import org.aspectj.bridge.IMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SmsServiceImpl implements SmsService {


    @Override
    public SmsDto sendSms(SendSmsDto dto) {
        return sendSMS(dto);
    }

    @Value("${app.sms.user.name}")
    private String smsUserName;


    @Value("${app.sms.user.password}")
    private String smsPass;


    @Value("${app.sms.user.from}")
    private String smsFrom;

    final static String resourceUrl = "https://media.sms24.ir/SMSInOutBox/Send";

    private SmsDto sendSMS(SendSmsDto dto) {
        System.out.println("Start Send SMS");
        RestTemplate restTemplate = new RestTemplate();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("UserName", smsUserName);
        jsonObject.put("Password", smsPass);
        jsonObject.put("From", smsFrom);
        if(dto.getPhoneNumber().substring(0,2).equals("00"))
            dto.setPhoneNumber(dto.getPhoneNumber().substring(1,11));
        jsonObject.put("To", dto.getPhoneNumber());
        jsonObject.put("Message", dto.getMessage());
       // System.out.println(">>>>>>>>>>>>"+ dto.getMessage());
        ResponseEntity<String> response
                = restTemplate.postForEntity(resourceUrl, jsonObject, String.class);
        SmsDto smsDto = new SmsDto();
        smsDto.setBody(response.getBody());
        //1 متن اشتباه
        //0
        //16 شماره موبایل اشتباه
        System.out.println("sms:"+smsDto);
        return smsDto;
    }
}
