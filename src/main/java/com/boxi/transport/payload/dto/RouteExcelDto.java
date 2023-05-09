package com.boxi.transport.payload.dto;

import com.boxi.core.request.JsonBase;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RouteExcelDto extends JsonBase {



    @NotBlank
    private String code;

    @NotBlank
    private String name;

    @NotBlank
    private String selectSourceHub;

    @NotBlank
    private String selectTargetHub;

//    @NotBlank
//    private Long nodes; //nodeCount

//    private Double distance;
//
//    private Double distanceVariance;
//
//    private String transitTime;
//
//    private String timeStoppage;
    private Boolean isActive;

    List<ConnectionExcelDto> connections;


}
