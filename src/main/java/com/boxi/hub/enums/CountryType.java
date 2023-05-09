package com.boxi.hub.enums;

import com.boxi.core.response.SelectResponse;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum CountryType {
        PROVINCE("استان",0), CITY("شهر",1),  hubRegion(" هاب", 2), pickupRegion(" جمع آوری", 3),
        deliveryRegion(" توزیع", 4), pickupDeliveryRegion(" جمع آوری /توزیع", 5);

        private final long value;
        private final String fa;

        private CountryType(String fa, long value) {
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
                return Arrays.stream(CountryType.values()).map(hubType -> new SelectResponse(hubType.getValue(), hubType.getFa())).collect(Collectors.toList());
        }

        public static CountryType findByValue(Long v) {
                for (CountryType s : EnumSet.allOf(CountryType.class)) {
                        if (s.value == v) {
                                return s;
                        }
                }
                return null;

        }

        public static CountryType findByFa(String fa) {
                for (CountryType s : EnumSet.allOf(CountryType.class)) {
                        if (s.fa.equals(fa)) {
                                return s;
                        }
                }
                return null;

        }

}
