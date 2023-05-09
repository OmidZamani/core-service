package com.boxi.transport.enums;

import com.boxi.core.response.SelectResponse;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

public enum  FleetType {

    justForHub("اختصاصی برای هاب",0),circularBetweenHubs("گردشی میان هاب",1);
    private final long value;
    private final String fa;

    private FleetType(String fa, long value) {
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
        return Arrays.stream(FleetType.values()).map(n -> new SelectResponse(n.getValue(), n.getFa())).collect(Collectors.toList());
    }

    public static FleetType findByValue(Long v) {
        for (FleetType s : EnumSet.allOf(FleetType.class)) {
            if (s.value == v) {
                return s;
            }
        }
        return null;

    }

    public static FleetType findByfa(String v) {
        for (FleetType s : EnumSet.allOf(FleetType.class)) {
            if (s.fa.equals(v) ) {
                return s;
            }
        }
        return null;

    }
}
