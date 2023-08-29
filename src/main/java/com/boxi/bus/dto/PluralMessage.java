package com.boxi.bus.dto;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.HashMap;

@Data
@Accessors(chain = true)
@ToString
public class PluralMessage {

    private MessageType messageType;

    private HashMap<String,String> data;

    private String status;

    //RAHGIRI
    //NAME
    //DATE
    //INTERVAL
    //DATE_TIME
    //REASON
    //CONTACT_NUMBER
    //HUB
    //MICROHUB
    //PASS
    //ADDRESS



}
