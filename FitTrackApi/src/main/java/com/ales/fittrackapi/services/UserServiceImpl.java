package com.ales.fittrackapi.services;

import com.ales.fittrackapi.entities.User;
import com.ales.fittrackapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> findAllByExample(User user) {
        System.out.println(Example.of(user));
        return userRepository.findAll(Example.of(user));
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> saveAll(List<User> users) {
        return userRepository.saveAll(users);
    }

    @Override
    public String deleteById(Long id) {
        User userToDelete = findById(id);
        if (userToDelete == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No user found with id: " + id) ;

        userRepository.deleteById(id);
        return "User with id: " + id + ", deleted successfully";
    }

    @Override
    public String deleteByExample(User user) {
        List<User> usersToDelete = findAllByExample(user);
        if (usersToDelete.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No user was found with given example");

        userRepository.deleteAll(usersToDelete);
        return usersToDelete.size() + " users deleted successfully";
    }


    @Override
    public User update(User user) {
        User userToUpdate = findById(user.getId());
        if (userToUpdate == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No user found with id " + user.getId());

        return userRepository.save(user);
    }
}
