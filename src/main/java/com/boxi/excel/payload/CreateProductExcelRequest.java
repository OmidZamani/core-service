package com.boxi.excel.payload;

import com.boxi.core.request.JsonBase;
import lombok.Data;

@Data
public class CreateProductExcelRequest
        extends JsonBase {
    private Long id;
    private String code;
    private String name;
    private String description;
    private Boolean isActive;
    private Boolean isDeleted;
    private String productGroup;
}
