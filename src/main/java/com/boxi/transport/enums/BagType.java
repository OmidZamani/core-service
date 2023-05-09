package com.boxi.transport.enums;

import com.boxi.core.response.SelectResponse;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

public enum  BagType {

    Bag("کیسه",0),Box("جعبه",1),Handwheel("چرخ دستی",3);

    private final long value;
    private final String fa;

    private BagType(String fa, long value) {
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
        return Arrays.stream(BagType.values()).map(n -> new SelectResponse(n.getValue(), n.getFa())).collect(Collectors.toList());
    }

    public static BagType findByValue(Long v) {
        for (BagType s : EnumSet.allOf(BagType.class)) {
            if (s.value == v) {
                return s;
            }
        }
        return null;
    }
    public static BagType findByfa(String v) {
        for (BagType s : EnumSet.allOf(BagType.class)) {
            if (s.fa.equals(v)  ) {
                return s;
            }
        }
        return null;
    }
}
