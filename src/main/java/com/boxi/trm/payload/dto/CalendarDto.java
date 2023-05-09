package com.boxi.trm.payload.dto;

import com.boxi.core.request.DateDto;
import com.boxi.core.request.JsonBase;
import com.boxi.core.response.SelectResponse;
import com.boxi.hub.entity.Hub;
import com.boxi.trm.entity.DispatchShift;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class CalendarDto extends JsonBase {
    private Long id;
    private String shamsi;
    private String shamsiDayName;
    private Long shamsiDayNO;
    private String shamsiQuarterName;
    private Long monthNO;
    private String monthName;
    private Long yearNO;
    private Long monthDayCount;
    private Long shamsiDayNumber;
    private List<DispatchShiftDto> dispatchShifts;
    private DateDto date;

}


