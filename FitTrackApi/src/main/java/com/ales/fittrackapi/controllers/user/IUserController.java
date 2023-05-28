package com.ales.fittrackapi.controllers.user;

import com.ales.fittrackapi.entities.User;

import java.util.List;

interface IUserController {

    List<User> findAll();

    User findById( Long id);

    User findAuthenticatedUser();

    List<User> findAllByExample( User user);

    User save( User user);

    List<User> saveAll( List<User> users);

    void deleteById( Long id);

    void deleteByExample( User user);

    User update( User user);

    User updateAuthenticatedUser(User user);
}
