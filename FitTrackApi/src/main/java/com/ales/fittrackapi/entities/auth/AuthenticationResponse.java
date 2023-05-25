package com.ales.fittrackapi.entities.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class AuthenticationResponse {
    private String token;
}