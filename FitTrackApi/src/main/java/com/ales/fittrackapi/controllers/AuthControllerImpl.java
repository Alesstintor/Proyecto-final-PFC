package com.ales.fittrackapi.controllers;

import com.ales.fittrackapi.authentication.JwtService;
import com.ales.fittrackapi.authentication.MyUserDetailsService;
import com.ales.fittrackapi.entities.AuthenticationRequest;
import com.ales.fittrackapi.entities.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
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


    @Override
    public AuthenticationResponse createToken(AuthenticationRequest authenticationRequest) throws Exception {
        try {
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(), authenticationRequest.getPassword());

            authenticationManager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new Exception("Invalid username or password", e);
        }
        UserDetails userDetails = myUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        String token = jwtService.createToken(userDetails);
        return new AuthenticationResponse(token);
    }
}
