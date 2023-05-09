package com.boxi.trm.payload.dto;

import com.boxi.core.response.SelectResponse;
import com.boxi.trm.enums.MONTH;
import lombok.Data;

import java.util.List;

@Data
public class NormalShiftDto {
    private SelectResponse selecthub;
    private SelectResponse dispatchShiftType;
    private Long year;
    private Long month;

    private List<DispatchShiftDto> saturday;
    private List<DispatchShiftDto> sunday;
    private List<DispatchShiftDto> monday;
    private List<DispatchShiftDto> tuesday;
    private List<DispatchShiftDto> wednesday;
    private List<DispatchShiftDto> thursday;
    private List<DispatchShiftDto> friday;
}
