package com.boxi.bus;

import com.boxi.bus.dto.PluralMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SmsListener {


    @KafkaListener(
            id = "sms",
            topics = "sms-topic",
            groupId = "sms",
            concurrency = "3")
    public void listen(PluralMessage pluralMessage) {
        log.info("Received: {}", pluralMessage);
        if (pluralMessage.getStatus().equals("PROCESSED")) {

        }
    }
}