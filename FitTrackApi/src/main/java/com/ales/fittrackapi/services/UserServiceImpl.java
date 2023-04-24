package com.ales.fittrackapi.services;

import com.ales.fittrackapi.entities.User;
import com.ales.fittrackapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

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
        return null;
    }

    @Override
    public String deleteByExample(User user) {
        return null;
    }

    @Override
    public String update(User user) {
        return null;
    }
}
