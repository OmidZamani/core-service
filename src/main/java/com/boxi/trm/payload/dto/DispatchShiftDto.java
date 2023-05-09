package com.boxi.trm.payload.dto;

import com.boxi.core.request.DateDto;
import com.boxi.core.request.JsonBase;
import com.boxi.core.response.SelectResponse;
import com.boxi.trm.entity.Calendar;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;

@Data
@EqualsAndHashCode(callSuper = false)
public class DispatchShiftDto extends JsonBase {
    private Long id;
    private Boolean isActive;
    private Boolean isDeleted;
    private String code;
    private String name;
    private String timeFrom;
    private String timeTo;
    private Long priority;
    private SelectResponse dispatchShiftType;
    private DateDto calendarDate;
    private SelectResponse selectcalendar;
    private SelectResponse selectcalendarhub;
    private SelectResponse selecthub;
}

