package com.boxi.hub.enums;

import com.boxi.core.response.SelectResponse;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ContryTypes {

    Ostan("استان", 0), ciity("شهر", 1), hubRegion("منطقه هاب", 2), pickupRegion("منطقه جمع آوری", 3), 
    deliveryRegion("منطقه توزیع", 4), pickupDeliveryRegion("منطقه جمع آوری /توزیع", 5);
    private final long value;
    private final String fa;

    private ContryTypes(String fa, long value) {
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
        return Arrays.stream(ContryTypes.values()).map(hubType -> new SelectResponse(hubType.getValue(), hubType.getFa())).collect(Collectors.toList());
    }

    public static ContryTypes findByValue(Long v) {
        for (ContryTypes s : EnumSet.allOf(ContryTypes.class)) {
            if (s.value == v) {
                return s;
            }
        }
        return null;

    }

    public static ContryTypes findByFa(String fa) {
        for (ContryTypes s : EnumSet.allOf(ContryTypes.class)) {
            if (s.fa.equals(fa)) {
                return s;
            }
        }
        return null;

    }
}

