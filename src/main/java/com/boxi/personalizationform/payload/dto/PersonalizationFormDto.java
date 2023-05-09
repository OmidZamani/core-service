package com.boxi.personalizationform.payload.dto;

import com.boxi.core.request.JsonBase;
import com.boxi.core.response.SelectResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PersonalizationFormDto extends JsonBase {

    private Long id;
    private SelectResponse selectUser;
    private SelectResponse selectPermission;
    private String filterPersonalize;
    private String operationPersonalize;
    private String listPersonalize;
}
