package com.boxi.transport.enums;

import com.boxi.core.response.SelectResponse;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

public enum  FuelType {
    Benzin("بنزین",0),Gasoil("گازوییل",1),Gas("گاز",2);
    private final long value;
    private final String fa;

    private FuelType(String fa, long value) {
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
        return Arrays.stream(FuelType.values()).map(n -> new SelectResponse(n.getValue(), n.getFa())).collect(Collectors.toList());
    }

    public static FuelType findByValue(Long v) {
        for (FuelType s : EnumSet.allOf(FuelType.class)) {
            if (s.value == v) {
                return s;
            }
        }
        return null;

    }

    public static FuelType findByfa(String v) {
        for (FuelType s : EnumSet.allOf(FuelType.class)) {
            if (s.fa.equals(v) ) {
                return s;
            }
        }
        return null;

    }
}
