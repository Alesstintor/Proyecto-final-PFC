package com.ales.fittrackapi.services;

import com.ales.fittrackapi.entities.User;

import java.util.List;

public interface IUserService {

    List<User> findAll();
    User findById(Long id);
    List<User> findAllByExample(User user);
    User save(User user);
    List<User> saveAll(List<User> users);
    String deleteById(Long id);
    String deleteByExample(User user);
    User update(User user);

}
