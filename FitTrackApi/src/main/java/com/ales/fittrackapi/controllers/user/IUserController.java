package com.ales.fittrackapi.controllers.user;

import com.ales.fittrackapi.entities.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

interface IUserController {

    List<User> findAll();

    User findById(@PathVariable Long id);

    List<User> findAllByExample(@RequestBody User user);

    User save(@RequestBody User user);

    List<User> saveAll(@RequestBody List<User> users);

    void deleteById(@PathVariable Long id);

    void deleteByExample(@RequestBody User user);

    User update(@RequestBody User user);
}
