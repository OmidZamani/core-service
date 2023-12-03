package com.boxi.feign;

import com.boxi.core.response.SelectResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "consignment-pudo-service", url = "${feign.client.url}/consignment-api/pudoPlanning")
public interface PudoPlaningClient {
    @PostMapping("/createConsignmentList/{pudoExecutation}/{pudoVehicle}")
     List<SelectResponse> createConsignmentList(@RequestBody List<SelectResponse> consignmentList, @PathVariable Long pudoExecutation, @PathVariable Long pudoVehicle);
}
