package com.ales.fittrackapi.controllers;

import com.ales.fittrackapi.entities.AuthenticationRequest;
import com.ales.fittrackapi.entities.AuthenticationResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public interface IAuthController {

    @PostMapping("/login")
    AuthenticationResponse createToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception;
}
