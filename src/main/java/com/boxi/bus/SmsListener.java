package com.boxi.bus;

import com.boxi.bus.dto.PluralMessage;
import com.boxi.sms.service.Impl.CallSmsStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SmsListener {

    private final CallSmsStrategy callSmsStrategy;

    @Autowired
    public SmsListener(CallSmsStrategy callSmsStrategy) {
        this.callSmsStrategy = callSmsStrategy;
    }

    @KafkaListener(
            id = "sms",
            topics = "sms-topic",
            groupId = "sms",
            concurrency = "3")
    public void listen(PluralMessage pluralMessage) {
        log.info("Received: {}", pluralMessage);
        callSmsStrategy.executeStrategy(pluralMessage);
    }
}