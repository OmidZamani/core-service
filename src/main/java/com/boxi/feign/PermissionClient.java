package com.boxi.feign;

import com.boxi.core.request.PermissionsCodeDto;
import com.boxi.core.request.SimpleWrapper;
import com.boxi.core.response.PermissionDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "permissionClient", url = "${feign.client.url}/resource-api")
public interface PermissionClient {

    @PostMapping(value = "/permission/fetchPermissionsByRoleName")
    List<PermissionDto> fetchPermissionsByRoleName(@RequestBody SimpleWrapper roleName);


    @PostMapping("/fetchPermissionsCodesByUserName")
    public PermissionsCodeDto fetchPermissionsCodesName(@Valid @RequestBody SimpleWrapper request);
    @GetMapping("/permission/findbycode/{code}")
    public PermissionDto findbyCode(@PathVariable String code);

 /*    @Headers("X-Auth-Token: {Authorization}")
    @PostMapping(value = "/authorizationInfo")
    SecUser getUserByToken(@Param("Authorization") String accessToken);*/

}