package com.boxi.core.permission;

import java.io.Serializable;


import com.boxi.core.request.PermissionsCodeDto;
import com.boxi.core.request.SimpleWrapper;
import com.boxi.feign.PermissionClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.AbstractOAuth2TokenAuthenticationToken;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class CustomPermissionEvaluator implements PermissionEvaluator {


    private PermissionClient permissionClient;

    @Autowired
    public void setCustomRepository(PermissionClient permissionClient) {
        this.permissionClient = permissionClient;
    }


    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {

        if ((authentication == null) || (targetDomainObject == null) || !(permission instanceof String)) {
            return false;
        }
        return chkPermission(authentication, permission.toString().toUpperCase());
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
                                 Object permission) {
        log.warn(">>>>>>>>2" + permission);

        return false;
    }

    private boolean chkPermission(Authentication authentication, String permission) {
        log.warn(">>>>>>>>>>>>{},{},{}", authentication.toString(), permission);
        return hasPermission(permission);

    }

    public Boolean hasPermission(String permission) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AbstractOAuth2TokenAuthenticationToken<?>) {
            var oauthAuth = (AbstractOAuth2TokenAuthenticationToken<?>) authentication;
            log.warn("OAuth authentication: {}", oauthAuth);
            var token = (Jwt) oauthAuth.getToken();//TODO
            log.warn("token: {}", token);
            log.warn(">>>>>>>>name:{} or {} " + oauthAuth.getName(),authentication.getName());
            PermissionsCodeDto response = permissionClient.fetchPermissionsCodesName(new SimpleWrapper().setIn(oauthAuth.getName()));
            log.warn(response.toString());
            return response.getCode().contains(permission);

        } else {
            // No OAuth authentication
            log.warn("TODO {} {}", authentication.getClass(), authentication);
            return false;
        }
    }


}