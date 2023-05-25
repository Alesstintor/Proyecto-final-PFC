package com.ales.fittrackapi.controllers.auth;

import com.ales.fittrackapi.services.auth.JwtService;
import com.ales.fittrackapi.services.auth.MyUserDetailsService;
import com.ales.fittrackapi.entities.auth.AuthenticationRequest;
import com.ales.fittrackapi.entities.User;
import com.ales.fittrackapi.services.user.UserServiceImpl;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthControllerImpl implements IAuthController{

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    MyUserDetailsService myUserDetailsService;
    @Autowired
    JwtService jwtService;
    @Autowired
    UserServiceImpl userService;

    @Autowired


    @Override
    public ResponseEntity<String> createToken(AuthenticationRequest authenticationRequest, HttpServletResponse response) throws Exception {
        try {
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(), authenticationRequest.getPassword());

            authenticationManager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new Exception("Invalid username or password", e);
        }
        UserDetails userDetails = myUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        if (userDetails == null) return ResponseEntity.status(400).body("Error Authenticating");

        String token = jwtService.createToken(userDetails);

        Cookie cookie = new Cookie("jwt", token);
        cookie.setMaxAge(7 * 24 * 60 * 60);
        cookie.setHttpOnly(true);
        cookie.setPath("/");

        response.addCookie(cookie);

        return ResponseEntity.ok(token);
    }

    @Override
    public User register(User user) {
        return userService.save(user);
    }
}
