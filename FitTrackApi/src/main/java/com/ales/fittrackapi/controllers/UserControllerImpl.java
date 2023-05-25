package com.ales.fittrackapi.controllers;

import com.ales.fittrackapi.entities.User;
import com.ales.fittrackapi.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserControllerImpl implements IUserController{

    @Autowired
    IUserService userService;

    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

    @Override
    public User findById(Long id) {
        return userService.findById(id);
    }

    @Override
    public List<User> findAllByExample(User user) {
        return userService.findAllByExample(user);
    }

    @Override
    public User save(User user) {
        return userService.save(user);
    }

    @Override
    public List<User> saveAll(@RequestBody List<User> users) {
        return userService.saveAll(users);
    }

    @Override
    public void deleteById(Long id) {
        userService.deleteById(id);
    }

    @Override
    public void deleteByExample(@RequestBody User user) {
        userService.deleteByExample(user);
    }

    @Override
    public User update(@RequestBody User user) {
        return userService.update(user);
    }

}
