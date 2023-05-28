package com.ales.fittrackapi.controllers.auth;

import com.ales.fittrackapi.entities.auth.AuthenticationResponse;
import com.ales.fittrackapi.entities.auth.RegisterRequest;
import com.ales.fittrackapi.services.auth.IAuthService;
import com.ales.fittrackapi.entities.auth.AuthenticationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthControllerImpl implements IAuthController{

    private final IAuthService authService;

    // /auth/login
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(authService.authenticate(authenticationRequest));
    }

    // auth/register
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(authService.register(registerRequest));
    }

    @GetMapping("/revalidate")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<AuthenticationResponse> revalidateToken() {
        return ResponseEntity.ok(authService.revalidateToken());
    }
}
