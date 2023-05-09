package com.boxi.hub.payload.dto;

import lombok.Data;

import java.util.List;

@Data
public class SimpleArrayWrapper {
    private List<String> in;
}
