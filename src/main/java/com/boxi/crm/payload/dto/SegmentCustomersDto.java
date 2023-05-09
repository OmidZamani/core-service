package com.boxi.crm.payload.dto;

import com.boxi.core.response.SelectResponse;
import com.boxi.crm.entity.CustomerSegment;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SegmentCustomersDto {
    private Long id;
    private SelectResponse selectcustomer;
    private SelectResponse segment;
}
