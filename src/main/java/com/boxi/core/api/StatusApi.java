package com.boxi.core.api;

import com.boxi.core.response.Response;
import com.boxi.utils.UserSecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/core-api/status")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j

public class StatusApi {

    @GetMapping()
    public Response<Object> stats(@AuthenticationPrincipal Jwt jwt) {

        return Response.ok().setPayload("service is working .. principal user name is :" + UserSecurityUtil.getAuthName() + " and extracted jwt data is :" + Collections.singletonMap("data", jwt.getClaims()));
    }

    @GetMapping("/anonymous")
    public ResponseEntity<String> getAnonymous() {
        return ResponseEntity.ok("Anonymous user role acl");
    }

    /*    @RolesAllowed("user")*/
    @GetMapping("/user")
    public ResponseEntity<String> getUser() {
        return ResponseEntity.ok("test normal user role acl");
    }

    @GetMapping("/admin")
    public ResponseEntity<String> getAdmin() {
        return ResponseEntity.ok("test admin role acl");
    }

    /*    @RolesAllowed({ "admin", "user" })*/
    @GetMapping( "/all-user")
    public ResponseEntity<String> getAllUser() {
        return ResponseEntity.ok("All User acl test");
    }

    @GetMapping( "/version")
    public ResponseEntity<String> version() {
        return ResponseEntity.ok("31 October 13:45");
    }
}