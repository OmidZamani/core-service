package com.boxi.transport.payload.dto;

import com.boxi.core.request.JsonBase;
import com.boxi.core.response.SelectResponse;
import com.boxi.transport.entity.Route;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RouteDto extends JsonBase {

    private Long id;

    @NotBlank
    private String code;

    @NotBlank
    private String name;

    @NotNull
    private SelectResponse selectSourceHub;

    @NotNull
    private SelectResponse selectTargetHub;

    private Boolean isActive;

    @NotNull
    private Long nodes; //nodeCount

    private Double distance;

    private Double distanceVariance;

    private String transitTime;

    private String timeStoppage;

    private List<ConnectionDto> connections;




}
