package com.boxi.utils;

import com.boxi.core.response.SelectResponse;

public abstract class SelectUtil {

    public static Boolean  NZ_CHECK(SelectResponse select){
        if (select == null) return true;
        if(select.getId()==null)return true;
        if(select.getId() == 0) return true;
        return false;
    }
}
