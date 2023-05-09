package com.boxi.trm.enums;

import com.boxi.core.response.SelectResponse;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

public enum DispatchShiftType {

    PICKUP("جمع آوری",0),DELIVERY("توزیع",1),PICKUPDELIVERY("جمع آوری/توزیع",2),EXCHANGE("مبادله",3);

    private final long value;
    private final String fa;

    private DispatchShiftType(String fa, long value) {
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
        return Arrays.stream(DispatchShiftType.values()).map(n -> new SelectResponse(n.getValue(), n.getFa())).collect(Collectors.toList());
    }

    public static DispatchShiftType findByValue(Long v) {
        for (DispatchShiftType s : EnumSet.allOf(DispatchShiftType.class)) {
            if (s.value == v) {
                return s;
            }
        }
        return null;
    }
    public static DispatchShiftType findByfa(String v) {
        for (DispatchShiftType s : EnumSet.allOf(DispatchShiftType.class)) {
            if (s.fa.equals(v)  ) {
                return s;
            }
        }
        return null;
    }
}
