package com.ales.fittrackapi.controllers.user;

import com.ales.fittrackapi.entities.User;
import com.ales.fittrackapi.services.user.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserControllerImpl implements IUserController {


    private final IUserService userService;

    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User findById(Long id) {
        return userService.findById(id);
    }

    @GetMapping("/likeExample")
    public List<User> findAllByExample(User user) {
        return userService.findAllByExample(user);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User save(User user) {
        return userService.save(user);
    }

    @PostMapping("/saveList")
    @ResponseStatus(HttpStatus.CREATED)
    public List<User> saveAll(@RequestBody List<User> users) {
        return userService.saveAll(users);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(Long id) {
        userService.deleteById(id);
    }

    @DeleteMapping("/likeExample")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByExample(@RequestBody User user) {
        userService.deleteByExample(user);
    }

    @PutMapping
    public User update(@RequestBody User user) {
        return userService.update(user);
    }

}
