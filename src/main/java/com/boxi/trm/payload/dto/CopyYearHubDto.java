package com.boxi.trm.payload.dto;

import com.boxi.core.response.SelectResponse;
import lombok.Data;

@Data
public class CopyYearHubDto {
    private Long oldyear;
    private Long newyear;
    private SelectResponse selecthub;
    private SelectResponse selectCalenderhub;

}
