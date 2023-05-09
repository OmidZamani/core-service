package com.boxi.hub.projection;

import org.springframework.beans.factory.annotation.Value;

public interface CategoryHubSelectView {

    Long getId();

    @Value("#{target.name+ ' ' +target.code}")
    String getText();
}
