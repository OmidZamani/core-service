package com.boxi.product.payload.request;

import com.boxi.core.request.JsonBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class FilterProductGroup extends JsonBase {
    private Long ID;
    private Data Created;
    private Long CREATEDBY;
    private Data EDITED;
    private Long EDITEDBY;
    private Long ISACTIVE;
    private Long ISDELETED;
    private String CODE;
    private String NAME;
}
