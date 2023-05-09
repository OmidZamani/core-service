package com.boxi.transport.payload.dto;


import com.boxi.core.request.DateDto;
import com.boxi.core.response.SelectResponse;
import lombok.Data;

@Data
public class VehicleExceptionsDto {
    private Long id;
    private String description;
    private SelectResponse selectVehicle;
    private SelectResponse selectException;
    private DateDto dateDto;

}
