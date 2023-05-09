package com.boxi.transport.enums;

import com.boxi.core.response.SelectResponse;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

public enum VehicleStatus {

    READYTOUSE("آماده بکار",0),UNUSEABLE("غیر قابل بهره برداری",1);

    private final long value;
    private final String fa;

    private VehicleStatus(String fa, long value) {
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
        return Arrays.stream(VehicleStatus.values()).map(n -> new SelectResponse(n.getValue(), n.getFa())).collect(Collectors.toList());
    }

    public static VehicleStatus findByValue(Long v) {
        for (VehicleStatus s : EnumSet.allOf(VehicleStatus.class)) {
            if (s.value == v) {
                return s;
            }
        }
        return null;
    }
    public static VehicleStatus findByfa(String v) {
        for (VehicleStatus s : EnumSet.allOf(VehicleStatus.class)) {
            if (s.fa.equals(v)  ) {
                return s;
            }
        }
        return null;
    }
}
