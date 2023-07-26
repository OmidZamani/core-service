package com.boxi.core.api;


import com.boxi.core.response.Response;
import com.boxi.utils.UserSecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/core-api/status")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j

public class StatusApi {


    @GetMapping()
    public Response createWorkContent() {
        String user = UserSecurityUtil.getAuthName();
        return Response.ok().setPayload("working .."+user);
    }

    @RequestMapping(value = "/anonymous", method = RequestMethod.GET)
    public ResponseEntity<String> getAnonymous() {
        return ResponseEntity.ok("Anonymous user role acl");
    }

/*    @RolesAllowed("user")*/
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<String> getUser() {
        return ResponseEntity.ok("test normal user role acl");
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ResponseEntity<String> getAdmin() {
        return ResponseEntity.ok("test admin role acl");
    }

/*    @RolesAllowed({ "admin", "user" })*/
    @RequestMapping(value = "/all-user", method = RequestMethod.GET)
    public ResponseEntity<String> getAllUser() {
        return ResponseEntity.ok("All User acl test");
    }

    @RequestMapping(value = "/version", method = RequestMethod.GET)
    public ResponseEntity<String> version() {
        return ResponseEntity.ok("31 October 13:45");
    }
}