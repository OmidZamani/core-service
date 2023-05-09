package com.boxi.trm.payload.dto;

import com.boxi.core.response.SelectResponse;
import lombok.Data;

import java.util.List;

@Data
public class CalendarHubDto {
    private Long id;
    private Boolean isActive;
    private String code;
    private String name;
    private List<DispatchShiftDto> dispatchShifts;
    private Long yearNO;
    private SelectResponse selecthub;
}




