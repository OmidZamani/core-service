package com.boxi.core.request;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class DateDto {
    private Integer day;
    private Integer month;
    private Integer year;
}
