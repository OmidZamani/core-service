package com.boxi.trm.payload.dto;

import com.boxi.core.request.DateDto;
import com.boxi.core.response.SelectResponse;
import lombok.Data;

@Data
public class FindByDispatcherByHubDto {
    private Long hubid;
    private DateDto dateDto;
    private SelectResponse shiftType;

}
