package com.ales.fittrackapi.controllers.auth;

import com.ales.fittrackapi.entities.auth.AuthenticationRequest;
import com.ales.fittrackapi.entities.auth.AuthenticationResponse;
import com.ales.fittrackapi.entities.auth.RegisterRequest;
import org.springframework.http.ResponseEntity;

public interface IAuthController {

    ResponseEntity<AuthenticationResponse> login(AuthenticationRequest authenticationRequest) throws Exception;

    ResponseEntity<AuthenticationResponse> register(RegisterRequest registerRequest);
}