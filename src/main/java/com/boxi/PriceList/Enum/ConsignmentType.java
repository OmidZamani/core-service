package com.boxi.PriceList.Enum;

import com.boxi.core.response.SelectResponse;
import com.boxi.utils.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ConsignmentType {

    letter ("نامه", 0), jouf ("جوف", 1),newspapers ("مطبوعات", 2),bag ("کیسه", 3),postcard ("کارت پستال", 4),PARCEL ("بسته", 4);
    private final long value;
    private final String Type;

    ConsignmentType(String type, long value) {
        this.value = value;
        Type = type;
    }

    public long getValue() {
        return value;
    }

    public String getType() {
        return Type;
    }

    public static List<SelectResponse> select() {
        return Arrays.stream(ConsignmentType.values()).map(hubType -> new SelectResponse(hubType.getValue(), hubType.getType())).collect(Collectors.toList());
    }

    public static ConsignmentType findByValue(Long v) {
        for (ConsignmentType s : EnumSet.allOf(ConsignmentType.class)) {
            if (s.value == v) {
                return s;
            }
        }
        return null;

    }
    public static ConsignmentType findByFa(String Value) {
        for (ConsignmentType s : EnumSet.allOf(ConsignmentType.class)) {
            if (s.Type.equals(Value)) {
                return s;
            }
        }
        return null;

    }

    public String selectToString() {
        return (this.getType()!=null ? this.getType():"" )+ (Constants.separator) +(this.getValue());
    }
}
