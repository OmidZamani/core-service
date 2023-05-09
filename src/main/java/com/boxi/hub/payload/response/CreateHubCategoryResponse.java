package com.boxi.hub.payload.response;

import com.boxi.hub.payload.request.CreateHubCategoryRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotBlank;


@EqualsAndHashCode(callSuper = true)
@ToString
@Data
public class CreateHubCategoryResponse extends CreateHubCategoryRequest {
    private Long id;

    @NotBlank
    private String code;

    @NotBlank
    private String name;

    private String description;
}
