package com.b2s.service.adminapp.service;

import com.b2s.service.adminapp.model.User;

import java.util.List;

/**
 * Created by spattabiraman on 10/19/2018.
 */
public interface UserService {

    User saveUser(final User user);

    User display(final String id, final String password);

    User modifyUser(final String id, final String password, final User user);

    String deleteUser(final String id, final String password);

    List<User> getAllUsers();

    List<User> insertListOfUsers(final List<User> userList);
}
