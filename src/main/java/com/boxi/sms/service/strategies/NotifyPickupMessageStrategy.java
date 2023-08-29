package com.boxi.sms.service.strategies;

import com.boxi.core.conf.PropConf;
import com.boxi.bus.dto.PluralMessage;
import com.boxi.sms.service.SendMessageStrategy;
import org.apache.commons.lang3.text.StrSubstitutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class NotifyPickupMessageStrategy implements SendMessageStrategy {

    private static PropConf propConfig;
    private final String KEY="notifyPickupSMS";

    @Autowired
    public NotifyPickupMessageStrategy(PropConf propConfig) {
        NotifyPickupMessageStrategy.propConfig = propConfig;
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
        String message = getMessage(overallMessage);
        System.out.println(message);
    }
}
