package com.ales.fittrackapi.controllers;

import com.ales.fittrackapi.entities.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

interface IUserController {

    @GetMapping("/all")
    List<User> findAll();

    @GetMapping("/{id}")
    User findById(@PathVariable Long id);

    @GetMapping("/likeExample")
    List<User> findAllByExample(@RequestBody User user);

    @PostMapping
    User save(@RequestBody User user);

    @PostMapping("/saveList")
    List<User> saveAll(@RequestBody List<User> users);

    @DeleteMapping("/delete/{id}")
    String deleteById(@PathVariable Long id);

    @DeleteMapping("/delete/likeExample")
    String deleteByExample(@RequestBody User user);

    @PutMapping
    String update(@RequestBody User user);
}
