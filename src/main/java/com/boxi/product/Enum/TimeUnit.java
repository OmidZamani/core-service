package com.boxi.product.Enum;

import com.boxi.core.response.SelectResponse;
import com.boxi.utils.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TimeUnit {

    ساعت("ساعت", 0), روز("روز", 1),ماه("ماه", 2),سال("سال", 3);
    private final long value;
    private final String Type;

    TimeUnit(String type, long value) {
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
        return Arrays.stream(TimeUnit.values()).map(hubType -> new SelectResponse(hubType.getValue(), hubType.getType())).collect(Collectors.toList());
    }

    public static TimeUnit findByValue(Long v) {
        for (TimeUnit s : EnumSet.allOf(TimeUnit.class)) {
            if (s.value == v) {
                return s;
            }
        }
        return null;

    }
    public static TimeUnit findByFa(String Value) {
        for (TimeUnit s : EnumSet.allOf(TimeUnit.class)) {
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
