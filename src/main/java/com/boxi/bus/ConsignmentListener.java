package com.boxi.bus;

import com.boxi.transport.payload.dto.BagDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
public class ConsignmentListener {
/*    @KafkaListener(
     id="bags-saga-core"    ,
     topics="bags-saga" ,
     containerGroup = "a",
     concurrency = "3"
    )
    @Transactional
    public void listen(BagDto dto){
        log.info("{}",dto);
    }*/

}
