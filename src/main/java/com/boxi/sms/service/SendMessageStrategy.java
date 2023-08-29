package com.boxi.sms.service;

import com.boxi.bus.dto.PluralMessage;

public interface SendMessageStrategy {
    public void execute(PluralMessage overallMessage);
}
