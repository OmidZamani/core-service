package com.boxi.hub.payload.request;

import com.boxi.core.request.JsonBase;
import com.boxi.core.response.SelectResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class FilterCustomDevisionDetail
        extends JsonBase {
    private Long id;
    private Boolean isActive;
    private Boolean isDeleted;
    private SelectResponse customDevision;
    private SelectResponse toCountryDevision;
    private SelectResponse fromCountryDevision;
}
