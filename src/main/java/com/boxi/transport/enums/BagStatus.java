package com.boxi.transport.enums;

import com.boxi.core.response.SelectResponse;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

public enum BagStatus {

    waitingForBagging("در انتظار بسته بندی", 0), Bagging("در حال کیسه بندی", 1), bagged("کیسه بندی شده", 2), loaded("بارگیری شده", 3),
    enterHub("ورود به هاب", 4), unUseable("غیرقابل بهره برداری", 5);

    private final long value;
    private final String fa;

    private BagStatus(String fa, long value) {
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
        return Arrays.stream(BagStatus.values()).map(n -> new SelectResponse(n.getValue(), n.getFa())).collect(Collectors.toList());
    }

    public static BagStatus findByValue(Long v) {
        for (BagStatus s : EnumSet.allOf(BagStatus.class)) {
            if (s.value == v) {
                return s;
            }
        }
        return null;
    }

    public static BagStatus findByfa(String v) {
        for (BagStatus s : EnumSet.allOf(BagStatus.class)) {
            if (s.fa.equals(v)) {
                return s;
            }
        }
        return null;
    }
}
