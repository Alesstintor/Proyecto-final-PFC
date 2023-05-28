package com.ales.fittrackapi.controllers.user;

import com.ales.fittrackapi.entities.User;
import com.ales.fittrackapi.services.user.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserControllerImpl implements IUserController {

    private final IUserService userService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("/myUser")
    public User findAuthenticatedUser() {return userService.findAuthenticatedUser();}

    @PostMapping("/likeExample")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<User> findAllByExample(@RequestBody User user) {
        return userService.findAllByExample(user);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public User save(User user) {
        return userService.save(user);
    }

    @PostMapping("/saveList")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<User> saveAll(@RequestBody List<User> users) {
        return userService.saveAll(users);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteById(@PathVariable Long id) {
        userService.deleteById(id);
    }

    @DeleteMapping("/likeExample")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteByExample(@RequestBody User user) {
        userService.deleteByExample(user);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public User update(@RequestBody User user) {
        return userService.update(user);
    }

    @PatchMapping("/myUser")
    public User updateAuthenticatedUser(@RequestBody User user) {
        return userService.updateAuthenticatedUser(user);
    }

}
