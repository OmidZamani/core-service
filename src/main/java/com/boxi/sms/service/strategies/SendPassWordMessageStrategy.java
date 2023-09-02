package com.boxi.sms.service.strategies;

import com.boxi.core.conf.PropConf;
import com.boxi.bus.dto.PluralMessage;
import com.boxi.sms.payload.dto.SendSmsDto;
import com.boxi.sms.service.SendMessageStrategy;
import com.boxi.sms.service.SmsService;
import org.apache.commons.lang3.text.StrSubstitutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SendPassWordMessageStrategy implements SendMessageStrategy {



    private static PropConf propConfig;
    private final String KEY="passwordSMS";
    private final SmsService smsService;

    @Autowired
    public SendPassWordMessageStrategy(PropConf propConfig, SmsService smsService) {
        SendPassWordMessageStrategy.propConfig = propConfig;
        this.smsService = smsService;
    }

    //NAME & DATE

    String getMessage(PluralMessage overallMessage) {
        Optional<String> templateContent = Optional.ofNullable(propConfig.getConfigValue(KEY));
        if (templateContent.isPresent()) {
            StrSubstitutor sub = new StrSubstitutor(overallMessage.getData(), "{", "}");
            String msg = sub.replace(templateContent.get());
            return msg;
        }
        return null;
    }

    @Override
    public void execute(PluralMessage overallMessage) {
        String to = overallMessage.getData().get("NAME");
        String message = getMessage(overallMessage);
        System.out.println(message);
//        to="0"+to;
        System.out.println(to);
        SendSmsDto dto=new SendSmsDto();
        dto.setMessage(message);
        dto.setPhoneNumber(to);
        smsService.sendSms(dto);
    }
}
