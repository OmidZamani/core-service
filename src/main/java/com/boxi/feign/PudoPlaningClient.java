package com.boxi.feign;

import com.boxi.core.response.Response;
import com.boxi.core.response.SelectResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "consignment-pudo-service", url = "${feign.client.url}/consignment-api/pudoPlanning")
public interface PudoPlaningClient {
    @PostMapping("/createConsignmentList/{pudoExecutation}/{pudoVehicle}")
    List<SelectResponse> createConsignmentList(@RequestBody List<SelectResponse> consignmentList, @PathVariable Long pudoExecutation, @PathVariable Long pudoVehicle);

    @PostMapping("/updateConsignmentListInGeo")
    Response updateConsignmentListInGeo(List<SelectResponse> list);


    @PostMapping("/updateConsignmentListInGeoMdl")
    Response updateConsignmentListInGeoMdl(List<SelectResponse> list);
    @GetMapping("/findByPolygonInVehicleId/{vehicleId}/{pudoExecutation}")
     Boolean findByPolygonInVehicleId(@PathVariable Long vehicleId, @PathVariable Long pudoExecutation);
}

