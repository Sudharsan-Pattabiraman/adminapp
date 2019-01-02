package com.b2s.service.adminapp.service;

import com.b2s.service.adminapp.dao.JdbcProductDao;
import com.b2s.service.adminapp.dao.JdbcUserDao;
import com.b2s.service.adminapp.dao.UserMapDao;
import com.b2s.service.adminapp.exception.NotFoundException;
import com.b2s.service.adminapp.model.User;
import com.b2s.service.adminapp.dao.UserDao;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by spattabiraman on 10/19/2018.
 */
@Service
public class DefaultUserService implements UserService {

    private final UserDao userDao;
    private final UserValidator userValidator;

    public DefaultUserService(final JdbcUserDao jdbcUserDao, final UserValidator userValidator) {
        this.userDao = jdbcUserDao;
        this.userValidator = userValidator;
    }

    public User saveUser(final User user) {

        userValidator.validate(user);
        int addedLines = userDao.insertUser(user);
        System.out.println(addedLines);
        return user;
        //return userDao.addUser(user);
    }

    public User display(final String id, final String password) {

        return userDao.retrieveUser(id, password);
    }

    public User modifyUser(final String id, final String password, final User user) {

        userValidator.validate(user);
        int updatedLines = userDao.reviseUser(id, password, user);
        if (updatedLines == 0) {
            throw new NotFoundException("User id not valid");
        }
        return User.builder().withUserId(id).withUserPassword(user.getUserPassword())
                .withUserName(user.getUserName()).withUserAddress(user.getUserAddress())
                .withUserCity(user.getUserCity().orElse(null))
                .withUserState(user.getUserState())
                .withUserZIP(user.getUserZIP()).build();
        //return userDao.modify(id, password, user);
    }

    public String deleteUser(final String id, final String password) {

        int deletedLines = userDao.deleteUser(id, password);
        if (deletedLines == 0) {
            throw new NotFoundException("Id incorrect");
        }
        return "User deleted";
        // return userDao.erase(id, password);
    }

    public List<User> getAllUsers() {

        return userDao.getListOfUsers();
    }

    @Override
    public List<User> insertListOfUsers(final List<User> userList) {
        return userDao.saveUsers(userList);
    }
}
