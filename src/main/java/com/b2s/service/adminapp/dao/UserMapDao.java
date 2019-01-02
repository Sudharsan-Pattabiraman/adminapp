package com.b2s.service.adminapp.dao;

import com.b2s.service.adminapp.exception.NotFoundException;
import com.b2s.service.adminapp.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by spattabiraman on 12/20/2018.
 */
@Repository
public class UserMapDao implements UserDao {

    final Map<String, User> userMap = new HashMap<>();

    public Map<String, User> getUserMap() {
        return userMap;
    }

    @Override
    public int insertUser(final User user) {

        return 0;
    }

    public User addUser(final User user) {

        userMap.put(user.getUserId(), user);
        System.out.println(userMap);
        return userMap.get(user.getUserId());
    }

    @Override
    public User retrieveUser(final String id, final String password) {
        List<User> userList = new ArrayList<User>(userMap.values());
        for (User user : userList) {
            if (user.getUserId().equalsIgnoreCase(id) && user.getUserPassword().equals(password)) {
                return user;
            }
        }
        throw new NotFoundException("User not found");
    }

    @Override
    public int reviseUser(final String id, final String password, final User user) {
        return 0;
    }

    @Override
    public int deleteUser(String id, String password) {
        return 0;
    }

    public String erase(final String id, final String password){

        User user = userMap.remove(id);
        if (user.getUserPassword().equals(password)) {
            return "User deleted";
        }
        throw new NotFoundException("User not found");
    }

    @Override
    public User modify(String id, String password, User user) {

        User dummyUser = userMap.replace(id, user);
        if (!dummyUser.getUserAddress().equals(user.getUserAddress())){
            return user;
        }
        throw new NotFoundException("User not updated");
    }

    @Override
    public List<User> saveUsers(List<User> userList) {
        return null;
    }

    @Override
    public List<User> getListOfUsers() {

        return new ArrayList<User>(userMap.values());
    }
}
