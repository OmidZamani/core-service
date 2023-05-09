package com.boxi.trm.payload.dto;

import com.boxi.core.response.SelectResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class CopyDispatchShiftDto {
    private List<DispatchShiftDto> shiftDtos;
    private SelectResponse oldcalendar;
    private SelectResponse newcalendar;

}
