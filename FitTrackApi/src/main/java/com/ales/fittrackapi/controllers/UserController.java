package com.ales.fittrackapi.controllers;

import com.ales.fittrackapi.entities.User;
import com.ales.fittrackapi.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;

    @GetMapping("/all")
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("/likeExample")
    public List<User> findAllByExample(@RequestBody User user) {
        return userService.findAllByExample(user);
    }

    @PostMapping
    public User save(@RequestBody User user) {
        return userService.save(user);
    }

    @PostMapping("/saveList")
    public List<User> saveAll(@RequestBody List<User> users) {
        return userService.saveAll(users);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        return userService.deleteById(id);
    }

    @DeleteMapping("/delete/likeExample")
    public String deleteByExample(@RequestBody User user) {
        return userService.deleteByExample(user);
    }

    @PutMapping
    public String update(@RequestBody User user) {
        return userService.update(user);
    }

}
