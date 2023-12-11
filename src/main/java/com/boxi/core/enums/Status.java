package com.boxi.core.enums;

import lombok.Getter;

@Getter
public enum Status {
    DE_ACTIVE(0),ACTIVE(1);
    private final long value;
     Status(long value) {
        this.value = value;
    }

}
