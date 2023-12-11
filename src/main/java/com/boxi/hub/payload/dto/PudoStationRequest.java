package com.boxi.hub.payload.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PudoStationRequest implements Serializable {
    private static final long serialVersionUID = 1470500056693501059L;
    private List<Long> pudostationIds;
    private Long hubId;
}
