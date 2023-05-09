package com.boxi.hub.payload.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
public class CreateHubCategoryRequest {

    private Long id;


    @NotBlank
    private String code;

    @NotBlank
    private String name;

    private String description;
}
