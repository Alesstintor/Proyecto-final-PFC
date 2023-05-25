package com.ales.fittrackapi.controllers.user;

import com.ales.fittrackapi.entities.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
interface IUserController {

    @GetMapping
    List<User> findAll();

    @GetMapping("/{id}")
    User findById(@PathVariable Long id);

    @GetMapping("/likeExample")
    List<User> findAllByExample(@RequestBody User user);

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    User save(@RequestBody User user);

    @PostMapping("/saveList")
    @ResponseStatus(HttpStatus.CREATED)
    List<User> saveAll(@RequestBody List<User> users);

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteById(@PathVariable Long id);

    @DeleteMapping("/likeExample")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteByExample(@RequestBody User user);

    @PutMapping
    User update(@RequestBody User user);
}
