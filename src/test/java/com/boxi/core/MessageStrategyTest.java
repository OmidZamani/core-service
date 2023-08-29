
package com.boxi.core;

import com.boxi.bus.dto.PluralMessage;
import com.boxi.sms.service.Impl.CallSmsStrategy;
import com.boxi.bus.dto.MessageType;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@Disabled("due to changed query typ by hojjat")
@SpringBootTest
@RunWith(SpringRunner.class)
public class MessageStrategyTest
{

    @Autowired
    CallSmsStrategy callSmsStrategy;



    @Test
    @DisplayName("strategy test")
    void test() {

        PluralMessage overallMessage=new PluralMessage();
        overallMessage.setMessageType(MessageType.LOGIN);
        HashMap<String,String> data=new HashMap<>();
        data.put("NAME","علی");
        data.put("DATE","1980/01/05");
        overallMessage.setData(data);
        callSmsStrategy.executeStrategy(overallMessage);
    }




}

