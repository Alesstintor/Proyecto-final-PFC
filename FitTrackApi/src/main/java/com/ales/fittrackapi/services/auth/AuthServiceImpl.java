package com.ales.fittrackapi.services.auth;

import com.ales.fittrackapi.entities.Roles;
import com.ales.fittrackapi.entities.User;
import com.ales.fittrackapi.entities.auth.AuthenticationRequest;
import com.ales.fittrackapi.entities.auth.AuthenticationResponse;
import com.ales.fittrackapi.entities.auth.MyUserDetails;
import com.ales.fittrackapi.entities.auth.RegisterRequest;
import com.ales.fittrackapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final IJwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final MyUserDetailsService myUserDetailsService;

    @Override
    public AuthenticationResponse register(RegisterRequest registerRequest) {
        User user = User.builder()
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .name(registerRequest.getName())
                .surname(registerRequest.getSurname())
                .type(List.of(Roles.USER))
                .build();

        User savedUser = userRepository.save(user);
        String token = jwtService.createToken(new MyUserDetails(savedUser));
        return AuthenticationResponse.builder().token(token).build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUsername(), authenticationRequest.getPassword());

        authenticationManager.authenticate(authentication);

        UserDetails userDetails = myUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        String token = jwtService.createToken(userDetails);

        return AuthenticationResponse.builder().token(token).build();
    }
}
