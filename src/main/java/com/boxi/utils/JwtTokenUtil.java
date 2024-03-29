package com.boxi.utils;

import lombok.extern.slf4j.Slf4j;
/*import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;*/
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.function.Function;
import java.util.stream.Collectors;


@Component
@Slf4j
public class JwtTokenUtil implements Serializable {
/*
    @Value("${app.jwt.secret.key}")
    private String jwtSecretKey;

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);

        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecretKey)
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String createTokenFromAuth(Authentication authentication) {
        final String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        log.info(">>>"+authorities);
        return generateToken(authentication.getName(), authorities);
    }

    private String generateToken(String username, String authorities) {
        long currentTimestampInMillis = System.currentTimeMillis();

        return Jwts.builder()
                .setSubject(username)
                .claim(Constants.AUTHORITIES_KEY, authorities)
                .signWith(SignatureAlgorithm.HS256, jwtSecretKey)
                .setIssuedAt(new Date(currentTimestampInMillis))
                .setExpiration(new Date(currentTimestampInMillis + (Constants.TOKEN_LIFETIME_SECONDS * 1000)))
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);

        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public UsernamePasswordAuthenticationToken getAuthentication(
            final String token, final Authentication existingAuth, final UserDetails userDetails
    ) {

        final JwtParser jwtParser = Jwts.parser().setSigningKey(jwtSecretKey);

        final Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);

        final Claims claims = claimsJws.getBody();

        final Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(Constants.AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        log.warn("authorities:{}",authorities.toString());
        return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
    }*/
}
