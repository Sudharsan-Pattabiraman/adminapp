package com.b2s.service.adminapp.controller;

import com.b2s.service.adminapp.model.User;
import com.b2s.service.adminapp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/users")
    public User create(@RequestBody final User user) {

        logger.info("Saving user details");
        return userService.saveUser(user);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/users/{id}")
    public User view(@PathVariable final String id, @RequestParam final String password) {

        logger.info("Retrieve user with id");
        return userService.display(id, password);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/users/{id}")
    public User update(@PathVariable final String id, @RequestParam final String password, @RequestBody final User user) {

        logger.info("Modifying user details");
        return userService.modifyUser(id, password, user);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/users/{id}")
    public String delete(@PathVariable final String id, @RequestParam final String password) {

        logger.info("Deleting a resource");
        return userService.deleteUser(id, password);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/users")
    public List<User> listOfUsers() {

        logger.info("List of users");
        return userService.getAllUsers();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/usersList")
    public List<User> createUsers(@RequestBody final List<User> userList){

        logger.info("Saving list of users using batchUpdate");
        return userService.insertListOfUsers(userList);
    }
}
