package com.boxi.transport.payload.dto;

import lombok.Data;

import java.util.List;

@Data
public class ConnectionDelDto {
     List<Long> deleteNodeIds;

     private Long routeId; //

     private Long nodes; //nodeCount

     private Double distance;

     private Double distanceVariance;

     private String transitTime;

     private String timeStoppage;
}
