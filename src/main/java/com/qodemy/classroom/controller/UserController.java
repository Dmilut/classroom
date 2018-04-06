package com.qodemy.classroom.controller;


import com.qodemy.classroom.model.User;
import com.qodemy.classroom.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * @author dmilut
 */

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    private static final Logger logger = LogManager.getLogger(UserController.class);

    // Create New User
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody User user, UriComponentsBuilder ucBuilder) {
        logger.info("creating new user: {}", user);
        if (userService.exists(user)) {
            logger.info("a user with name " + user.getName() + " already exists");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        userService.create(user);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());

        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    // Get User By ID
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<User> get(@PathVariable("id") int id) {
        logger.info("getting user with id: {}", id);
        User user = userService.findById(id);

        if (user == null) {
            logger.info("user with id {} not found", id);
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    // Get All Users
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAll() {
        logger.info("getting all users");
        List<User> users = userService.getAll();

        if (users == null || users.isEmpty()) {
            logger.info("no users found");
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    // Update Existing User
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<User> update(@PathVariable long id, @RequestBody User user) {
        logger.info("updating user: {}", user);
        User currentUser = userService.findById(id);

        if (currentUser == null) {
            logger.info("User with id {} not found", id);
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

        userService.update(id, user);

        return new ResponseEntity<User>(currentUser, HttpStatus.OK);
    }

    // Delete User
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        logger.info("deleting user with id: {}", id);
        User user = userService.findById(id);

        if (user == null) {
            logger.info("Unable to delete. User with id {} not found", id);
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        userService.delete(id);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
