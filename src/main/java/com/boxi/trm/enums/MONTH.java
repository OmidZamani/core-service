package com.boxi.trm.enums;

import com.boxi.core.response.SelectResponse;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

public enum MONTH {

    FARVARDIN("فروردین", 1), ORDIBEHESHT("اردیبهشت", 2), KHORDAD("خرداد", 3),
    TIR("تیر", 4), MORDAD("مرداد", 5), SHAHRIVAR("شهریور", 6),
    MEHR("مهر", 7), ABAN("آبان", 8), AZAR("آذر", 9),
    DEY("دی", 10), BAHMAN("بهمن", 11), ESFAND("اسفند", 12);

    private final long value;
    private final String fa;

    private MONTH(String fa, long value) {
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
        return Arrays.stream(MONTH.values()).map(n -> new SelectResponse(n.getValue(), n.getFa())).collect(Collectors.toList());
    }

    public static MONTH findByValue(Long v) {
        for (MONTH s : EnumSet.allOf(MONTH.class)) {
            if (s.value == v) {
                return s;
            }
        }
        return null;
    }

    public static MONTH findByfa(String v) {
        for (MONTH s : EnumSet.allOf(MONTH.class)) {
            if (s.fa.equals(v)) {
                return s;
            }
        }
        return null;
    }
}
