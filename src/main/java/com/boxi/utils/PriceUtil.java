package com.boxi.utils;

import java.math.BigDecimal;

public  class PriceUtil {

    public static BigDecimal DoubleToBigDecimal(Integer price) {
        if(price !=null)
        return BigDecimal.valueOf(price).stripTrailingZeros();
        return BigDecimal.ZERO;
    }
}
