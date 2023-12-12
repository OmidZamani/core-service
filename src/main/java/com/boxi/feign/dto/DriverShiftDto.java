package com.boxi.feign.dto;


import com.boxi.core.response.SelectResponse;
import lombok.Data;

import java.io.Serializable;

@Data
public class DriverShiftDto implements Serializable {
    private Long id;
    private String name;
    private SelectResponse shift;

}
