package com.boxi.transport.enums;

import com.boxi.core.response.SelectResponse;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

public enum VehicleType {

    Motorcycles("موتور سیکلت",0),vans("وانت",1),Trucks("کامیونت",2),SEDAN("سواری",3);

    private final long value;
    private final String fa;

    private VehicleType(String fa, long value) {
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
        return Arrays.stream(VehicleType.values()).map(n -> new SelectResponse(n.getValue(), n.getFa())).collect(Collectors.toList());
    }

    public static VehicleType findByValue(Long v) {
        for (VehicleType s : EnumSet.allOf(VehicleType.class)) {
            if (s.value == v) {
                return s;
            }
        }
        return null;
    }
    public static VehicleType findByfa(String v) {
        for (VehicleType s : EnumSet.allOf(VehicleType.class)) {
            if (s.fa.equals(v)  ) {
                return s;
            }
        }
        return null;
    }
}
