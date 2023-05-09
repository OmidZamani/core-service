package com.boxi.core.request;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SimpleWrapper {
    private String in;
}
