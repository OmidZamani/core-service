package com.boxi.hub.enums;

import com.boxi.core.response.SelectResponse;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.*;
import java.util.stream.Collectors;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum HubType {

    MAIN_HUB("اصلی", 0), BRANCH("شعبه", 1);
    private final long value;
    private final String fa;

    private HubType(String fa, long value) {
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
        return Arrays.stream(HubType.values()).map(hubType -> new SelectResponse(hubType.getValue(), hubType.getFa())).collect(Collectors.toList());
    }

    public static HubType findByValue(Long v) {
        for (HubType s : EnumSet.allOf(HubType.class)) {
            if (s.value == v) {
                return s;
            }
        }
        return null;

    }

    public static HubType findByFa(String fa) {
        for (HubType s : EnumSet.allOf(HubType.class)) {
            if (s.fa.equals(fa)) {
                return s;
            }
        }
        return null;

    }
}

