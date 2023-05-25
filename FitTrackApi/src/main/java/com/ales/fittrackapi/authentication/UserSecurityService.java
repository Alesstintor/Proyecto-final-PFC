package com.ales.fittrackapi.authentication;

import com.ales.fittrackapi.entities.User;
import com.ales.fittrackapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static org.springframework.security.core.userdetails.User.withUsername;

@Service
public class UserSecurityService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("Username " + username + " not found"));

       String[] roles = user.getType().stream().map(Enum::toString).toArray(String[]::new);

        return withUsername(user.getUsername()).password(user.getPassword()).roles(roles).build();
    }
}


