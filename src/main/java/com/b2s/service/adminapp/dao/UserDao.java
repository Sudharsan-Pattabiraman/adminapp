package com.b2s.service.adminapp.dao;

import com.b2s.service.adminapp.model.User;

import java.util.List;

/**
 * Created by spattabiraman on 10/19/2018.
 */
public interface UserDao {

    int insertUser(final User user);

    User retrieveUser(final String id, String password);

    int reviseUser(final String id, final String password, final User user);

    int deleteUser(final String id, final String password);

    List<User> getListOfUsers();

    User addUser(final User user);

    String erase(final String id, final String password);

    User modify(final String id, final String password, final User user);

    List<User> saveUsers (final List<User> userList);
}
