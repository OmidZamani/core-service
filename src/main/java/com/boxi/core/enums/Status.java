package com.boxi.core.enums;

public enum Status {
    DE_ACTIVE(0),ACTIVE(1);
    private final long value;
    private Status(long value) {
        this.value = value;
    }
    public long getValue() {
        return value;
    }
}
