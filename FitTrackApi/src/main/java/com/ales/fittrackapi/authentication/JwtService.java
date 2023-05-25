package com.ales.fittrackapi.authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Collection;
import java.util.Date;

@Service
public class JwtService {
    private static final int EXPIRATION_TIME = 1000 * 60 * 60;
    private static final String AUTHORITIES = "authorities";
    private final SecretKey SECRET_KEY;
    @Value("${SECURITY.SECRET.KEY}")
    private String KEY;

    public JwtService() {
        if (KEY == null) KEY = "oG4mgbYBQg20l01wRHcB285CunSwnk33Oi5YQUzjYssWSsTAu8";
        SECRET_KEY = Keys.hmacShaKeyFor(Decoders.BASE64.decode(KEY));
    }

    public String createToken(UserDetails userDetails) {
        String username = userDetails.getUsername();
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        return Jwts.builder()
                .setSubject(username)
                .claim(AUTHORITIES, authorities)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS512)
                .compact();
    }

    public Boolean hasTokenExpired(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return (userDetails.getUsername().equals(username) && !hasTokenExpired(token));

    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public Collection<? extends GrantedAuthority> getAuthorities(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody();
        return (Collection<? extends GrantedAuthority>) claims.get(AUTHORITIES);
    }
}