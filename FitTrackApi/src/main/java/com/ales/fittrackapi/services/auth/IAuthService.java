package com.ales.fittrackapi.services.auth;

import com.ales.fittrackapi.entities.auth.AuthenticationRequest;
import com.ales.fittrackapi.entities.auth.AuthenticationResponse;
import com.ales.fittrackapi.entities.auth.RegisterRequest;

public interface IAuthService {
    AuthenticationResponse register(RegisterRequest registerRequest);

    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);

    AuthenticationResponse revalidateToken();
}
