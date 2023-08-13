package com.boxi.utils;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;

import java.security.Principal;

import static org.springframework.security.core.context.SecurityContextHolder.getContext;
/*import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

import static org.springframework.security.core.context.SecurityContextHolder.getContext;*/

@Slf4j
public class UserSecurityUtil {

    private static Authentication getCurrentContextAuthentication() {
        return getContext().getAuthentication();

    }

    public static String getAuthName() {
        Authentication authentication = getContext().getAuthentication();

        log.warn(">>>>>>>>>>>"+authentication.getName());
        return authentication.getName();

    }






    private UserSecurityUtil() {
    }

    public static String getCurrentUsernameWithId() {
      String username = "";
        if (getCurrentContextAuthentication() == null)
            return "unknown";

        Object principal = getCurrentContextAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
            log.warn("user name 1"+username);
        } else {
            username = principal.toString();
            log.warn("user name 2"+username);
        }
        return username;
    }

    public static Long getCurrentUserId() {
        String userName = getCurrentUsernameWithId();
        if (!userName.equals("unknown")) {
            String[] parts = userName.split("-");
            return Long.valueOf(parts[1]);
        }
        return null;
    }

/*



    public static Collection<SimpleGrantedAuthority> getCurrentUserRoles() {
        return (Collection<SimpleGrantedAuthority>) getContext().getAuthentication().getAuthorities();
    }

    public static boolean isCurrentUserInRole(String authority) {
        Authentication authentication = getCurrentContextAuthentication();
        if (authentication != null) {
            return authentication.getAuthorities().stream()
                    .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(authority));
        }
        return false;
    }

    public static synchronized String getAuthorizationHeader(){

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                        .getRequest();
        String auth_header = request.getHeader(Constants.HEADER_STRING);
        log.warn(auth_header);
        return auth_header;

    }

*/
}
