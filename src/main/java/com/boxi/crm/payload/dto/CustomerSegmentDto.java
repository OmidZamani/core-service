package com.boxi.crm.payload.dto;

import com.boxi.core.request.JsonBase;
import com.boxi.crm.entity.SegmentCustomers;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
@Data
@EqualsAndHashCode(callSuper = false)
public class CustomerSegmentDto extends JsonBase {
    private Long Id;
    private Boolean isActive;
    private Boolean isDeleted;
    private String code;
    private String name;
    private String description;
    private List<SegmentCustomersDto> segmentCustomers;
}
