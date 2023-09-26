package com.boxi.transport.enums;

import com.boxi.core.response.SelectResponse;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

public enum MisMatchStatus {

    PENDING("در انتظار تایید", 0),CONFIRMED("تایید شده", 1),UNCONFIRMED("عدم تایید",2);
    private final long value;
    private final String fa;

    private MisMatchStatus(String fa, long value) {
        this.value = value;
        this.fa = fa;
    }

    public long getValue() {
        return value;
    }

    public String getFa() {
        return fa;
    }

    public static List<SelectResponse> select() {
        return Arrays.stream(MisMatchStatus.values()).map(n -> new SelectResponse(n.getValue(), n.getFa())).collect(Collectors.toList());
    }

    public static MisMatchStatus findByValue(Long v) {
        for (MisMatchStatus s : EnumSet.allOf(MisMatchStatus.class)) {
            if (s.value == v) {
                return s;
            }
        }
        return null;
    }
}