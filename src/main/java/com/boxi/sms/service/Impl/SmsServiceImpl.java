package com.boxi.sms.service.Impl;


import com.boxi.sms.payload.dto.SendSmsDto;
import com.boxi.sms.payload.dto.SmsDto;
import com.boxi.sms.service.SmsService;
import com.nimbusds.jose.shaded.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SmsServiceImpl implements SmsService {


    @Override
    public SmsDto sendSms(SendSmsDto dto) {
//        return sendSMS(dto);
        return sendSMSKavehNegar(dto);
    }

    @Value("${app.sms.user.name}")
    private String smsUserName;


    @Value("${app.sms.user.password}")
    private String smsPass;


    @Value("${app.sms.user.from}")
    private String smsFrom;
    @Value("${app.sms.user.from.kavehnegar}")
    private String smsFromKavehNegar;

    @Value("${sms_send_status}")
    private Boolean smsStatus;

    final static String resourceUrl = "https://media.sms24.ir/SMSInOutBox/Send";
    final static String resourceUrlKavehNegar = "https://api.kavenegar.com/v1/70414374326C654D64764148356343664B56496F7A34464D7A6E66696B4D50336978515A715878563146413D/sms/send.json";


    private SmsDto sendSMS(SendSmsDto dto) {
        System.out.println("Start Send SMS");
        RestTemplate restTemplate = new RestTemplate();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("UserName", smsUserName);
        jsonObject.put("Password", smsPass);
        jsonObject.put("From", smsFrom);
        if (dto.getPhoneNumber().substring(0, 2).equals("00"))
            dto.setPhoneNumber(dto.getPhoneNumber().substring(1, 11));
        jsonObject.put("To", dto.getPhoneNumber());
        jsonObject.put("Message", dto.getMessage());
        // System.out.println(">>>>>>>>>>>>"+ dto.getMessage());
        if (smsStatus) {
            ResponseEntity<String> response
                    = restTemplate.postForEntity(resourceUrl, jsonObject, String.class);
            SmsDto smsDto = new SmsDto();
            smsDto.setBody(response.getBody());

            //1 متن اشتباه
            //0
            //16 شماره موبایل اشتباه
            System.out.println("sms:" + smsDto);
            return smsDto;
        } else {
            return null;
        }
    }

    private SmsDto sendSMSKavehNegar(SendSmsDto dto) {
        System.out.println("Start Send SMS");
        RestTemplate restTemplate = new RestTemplate();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("from", smsFrom);
        if (dto.getPhoneNumber().substring(0, 2).equals("00"))
            dto.setPhoneNumber(dto.getPhoneNumber().substring(1, 11));
        jsonObject.put("receptor", dto.getPhoneNumber());
        jsonObject.put("message", dto.getMessage());
        if (smsStatus) {
            ResponseEntity<String> response
                    = restTemplate.postForEntity(String.format("%s?receptor=%s&sender=%s&message=%s", resourceUrlKavehNegar, dto.getPhoneNumber(), smsFromKavehNegar, dto.getMessage()), jsonObject, String.class);
            SmsDto smsDto = new SmsDto();
            smsDto.setBody(response.getBody());
            System.out.println("sms:" + smsDto);
            return smsDto;
        } else
            return null;
    }
}
