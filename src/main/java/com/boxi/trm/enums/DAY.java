package com.boxi.trm.enums;

import com.boxi.core.response.SelectResponse;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

public enum DAY {

    SATERDAY("شنبه", 1), SUNDAY("یکشنبه", 2), MONDAY("دوشنبه", 3), TUESDAY (" سه شنبه", 4),
    WEDNESDAY("چهارشنبه", 5), THURSDAY(" پنجشنبه", 6), FRIDAY("جمعه", 7);

    private final long value;
    private final String fa;

    private DAY(String fa, long value) {
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
        return Arrays.stream(DAY.values()).map(n -> new SelectResponse(n.getValue(), n.getFa())).collect(Collectors.toList());
    }

    public static DAY findByValue(Long v) {
        for (DAY s : EnumSet.allOf(DAY.class)) {
            if (s.value == v) {
                return s;
            }
        }
        return null;
    }

    public static DAY findByfa(String v) {
        for (DAY s : EnumSet.allOf(DAY.class)) {
            if (s.fa.equals(v)) {
                return s;
            }
        }
        return null;
    }
}
