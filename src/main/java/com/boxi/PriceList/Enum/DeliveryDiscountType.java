package com.boxi.PriceList.Enum;

import com.boxi.core.response.SelectResponse;
import com.boxi.utils.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum DeliveryDiscountType {


    تعدادی ("تعدادی", 0), ریالی ("ریالی", 1);
    private final long value;
    private final String Type;

    DeliveryDiscountType(String type, long value) {
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
        return Arrays.stream(DeliveryDiscountType.values()).map(hubType -> new SelectResponse(hubType.getValue(), hubType.getType())).collect(Collectors.toList());
    }

    public static DeliveryDiscountType findByValue(Long v) {
        for (DeliveryDiscountType s : EnumSet.allOf(DeliveryDiscountType.class)) {
            if (s.value == v) {
                return s;
            }
        }
        return null;

    }
    public static DeliveryDiscountType findByFa(String Value) {
        for (DeliveryDiscountType s : EnumSet.allOf(DeliveryDiscountType.class)) {
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
