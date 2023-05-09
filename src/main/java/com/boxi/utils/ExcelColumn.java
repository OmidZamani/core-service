package com.boxi.utils;


import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelColumn {
    String name();
    String numberFormat() default "General";
}