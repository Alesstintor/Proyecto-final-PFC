package com.ales.fittrackapi.controllers.auth;

import com.ales.fittrackapi.entities.auth.AuthenticationResponse;
import com.ales.fittrackapi.entities.auth.RegisterRequest;
import com.ales.fittrackapi.services.auth.IAuthService;
import com.ales.fittrackapi.entities.auth.AuthenticationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RequiredArgsConstructor
public class AuthControllerImpl implements IAuthController{

    private final IAuthService authService;

    @Autowired

    // /auth/login
    @Override
    public ResponseEntity<AuthenticationResponse> login(AuthenticationRequest authenticationRequest) {

        return ResponseEntity.ok(authService.authenticate(authenticationRequest));


    }

    // auth/register
    @Override
    public ResponseEntity<AuthenticationResponse> register(RegisterRequest registerRequest) {
        return ResponseEntity.ok(authService.register(registerRequest));
    }
}
