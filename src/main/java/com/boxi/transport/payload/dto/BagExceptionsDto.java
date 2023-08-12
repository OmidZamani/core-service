package com.boxi.transport.payload.dto;


import com.boxi.core.response.SelectResponse;
import lombok.Data;

@Data
public class BagExceptionsDto {
    private Long id;
    private String description;
    private SelectResponse selectException;
    private SelectResponse selectBag;
}



