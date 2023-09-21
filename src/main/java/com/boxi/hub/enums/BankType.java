package com.boxi.hub.enums;

import com.boxi.core.response.SelectResponse;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum BankType {

    MELI("ملی", 0), SEPAH("سپه", 1), SANATMADAN("صنعت و معدن", 2), KESHAVARZI("کشاورزی", 3), MASKAN("مسکن", 4), TOSESADERAT("توسعه صادرات", 5), TOSETAVON("توسعه تعاون", 6), POSTBANK("پست بانک", 7), EGTESADNOVIN("افتصاد نوین", 8), PARSIAN("پارسیان", 9), KARAFARIN("کارآفرین", 10), SAMAN("سامان", 11), SINA("سینا", 12), SHAHR("شهر", 13), DAY("دی", 14), SADERAT("صادرات", 15), MELAT("ملت", 16), TEJARAT("تجارت", 17), REFAH("رفاه", 18), GARDESHGARI("گردشگری", 19), IRANZAMIN("ابران زمین", 20), SARMAYE("سرمایه", 21), PASARGAD("پاسارکاد", 22), AYANDE("آبنده", 23), MEHRIRAN("مهر ایران", 24), RESALAT("رسالت", 25);
    private final long value;
    private final String fa;

    private BankType(String fa, long value) {
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
        return Arrays.stream(BankType.values()).map(hubType -> new SelectResponse(hubType.getValue(), hubType.getFa())).collect(Collectors.toList());
    }

    public static BankType findByValue(Long v) {
        for (BankType s : EnumSet.allOf(BankType.class)) {
            if (s.value == v) {
                return s;
            }
        }
        return null;

    }

    public static BankType findByFa(String fa) {
        for (BankType s : EnumSet.allOf(BankType.class)) {
            if (s.fa.equals(fa)) {
                return s;
            }
        }
        return null;

    }
}

