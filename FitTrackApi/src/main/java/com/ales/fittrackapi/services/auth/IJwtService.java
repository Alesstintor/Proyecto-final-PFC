package com.ales.fittrackapi.services.auth;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.function.Function;

public interface IJwtService {

    String createToken(UserDetails userDetails);

    Boolean hasTokenExpired(String token);
    Boolean validateToken(String token, UserDetails userDetails);

    Date extractExpiration(String token);

    String extractUsername(String token);

    Collection<? extends GrantedAuthority> getAuthorities(String token);

    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

    Claims extractAllClaims(String token);
}
