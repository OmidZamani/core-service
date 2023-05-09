package com.boxi.PriceList.Enum;

import com.boxi.core.response.SelectResponse;
import com.boxi.utils.Constants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ServiceType {

    BASE ("پایه", 0), ADDITIONAL ("تکمیلی", 1);
    private final long value;
    private final String Type;

    ServiceType(String type, long value) {
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
        return Arrays.stream(ServiceType.values()).map(hubType -> new SelectResponse(hubType.getValue(), hubType.getType())).collect(Collectors.toList());
    }


    public static ServiceType findByValue(Long v) {
        for (ServiceType s : EnumSet.allOf(ServiceType.class)) {
            if (s.value == v) {
                return s;
            }
        }
        return null;

    }
    public static ServiceType findByFa(String Value) {
        for (ServiceType s : EnumSet.allOf(ServiceType.class)) {
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
