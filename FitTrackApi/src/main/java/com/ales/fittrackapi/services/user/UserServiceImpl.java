package com.ales.fittrackapi.services.user;

import com.ales.fittrackapi.entities.User;
import com.ales.fittrackapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService{


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public User findAuthenticatedUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("User not found"));
    }

    @Override
    public List<User> findAllByExample(User user) {
        System.out.println(Example.of(user));
        return userRepository.findAll(Example.of(user));
    }

    @Override
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public List<User> saveAll(List<User> users) {
        return userRepository.saveAll(users);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.delete(findById(id));
    }

    @Override
    public void deleteByExample(User user) {
        List<User> usersToDelete = findAllByExample(user);
        if (usersToDelete.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        userRepository.deleteAll(usersToDelete);
    }


    @Override
    public User update(User user) {
        findById(user.getId());
        return userRepository.save(user);
    }

    @Override
    public User updateAuthenticatedUser(User user) {
        User savedUser = findAuthenticatedUser();

        if (user.getName() != null) savedUser.setName(user.getName());
        if (user.getSurname() != null) savedUser.setSurname(user.getSurname());
        if (user.getAge() > 0) savedUser.setAge(user.getAge());
        if (user.getWeight() > 0) savedUser.setWeight(user.getWeight());
        if (user.getHeight() > 0) savedUser.setHeight(user.getHeight());
        if (user.getGenre() != null) savedUser.setGenre(user.getGenre());

        return userRepository.save(savedUser);
    }
}
