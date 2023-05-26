package com.ales.fittrackapi.services.user;

import com.ales.fittrackapi.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUserService {

    List<User> findAll();
    User findById(Long id);
    List<User> findAllByExample(User user);
    User save(User user);
    List<User> saveAll(List<User> users);
    void deleteById(Long id);
    void deleteByExample(User user);
    User update(User user);

}
