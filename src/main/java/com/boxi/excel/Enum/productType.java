package com.boxi.excel.Enum;

import com.boxi.core.response.SelectResponse;
import com.boxi.utils.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum productType {

    پایه ("پایه", 0), تکمیلی ("تکمیلی", 1);
    private final long value;
    private final String Type;

    productType(String type, long value) {
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
        return Arrays.stream(productType.values()).map(hubType -> new SelectResponse(hubType.getValue(), hubType.getType())).collect(Collectors.toList());
    }

    public static productType findByValue(Long v) {
        for (productType s : EnumSet.allOf(productType.class)) {
            if (s.value == v) {
                return s;
            }
        }
        return null;

    }
    public static productType findByFa(String Value) {
        for (productType s : EnumSet.allOf(productType.class)) {
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
