package com.ales.fittrackapi.controllers.auth;

import com.ales.fittrackapi.entities.auth.AuthenticationRequest;
import com.ales.fittrackapi.entities.User;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public interface IAuthController {

    @PostMapping("/login")
    ResponseEntity<String> createToken(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) throws Exception;

    @PostMapping("/register")
    User register(@RequestBody User user);
}
