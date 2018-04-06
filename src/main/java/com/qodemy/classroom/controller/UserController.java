package com.qodemy.classroom.controller;


import com.qodemy.classroom.model.User;
import com.qodemy.classroom.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@Api(tags = {"User"}, value = "user", description = "User API")
//TODO we should use uuid instead of primary key id from db
public class UserController {

    @Autowired
    private UserService userService;

    private static final Logger logger = LogManager.getLogger(UserController.class);

    @ApiOperation(value = "This Rest API is used to create a user.",
            notes = "This API will create a new user.",
            response = User.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created a new user", response = User.class),
            @ApiResponse(code = 409, message = "User already exists")
    })
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

    @ApiOperation(value = "This Rest API is used to return a user by given Id.",
            notes = "This API will return the user by given Id",
            response = User.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User", response = User.class),
            @ApiResponse(code = 404, message = "User not found")
    })
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

    @ApiOperation(value = "This Rest API is used to return users.",
            notes = "This API will return an array of users.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "An array of users", responseContainer = "List", response = User.class),
    })
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

    @ApiOperation(value = "This Rest API is used to update a user.",
            notes = "This API requires a valid id of the user to be updated.",
            response = User.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated user", response = User.class),
            @ApiResponse(code = 404, message = "User not found")
    })
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

    @ApiOperation(value = "This Rest API is used to delete a user by given Id.",
            notes = "This API will delete a user by given Id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted the user"),
            @ApiResponse(code = 404, message = "User not found")
    })
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
