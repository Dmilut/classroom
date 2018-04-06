package com.qodemy.classroom.controller;


import com.qodemy.classroom.model.User;
import com.qodemy.classroom.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author dmilut
 */

@RestController
@RequestMapping("/users")
@Api(tags = {"User"}, value = "user", description = "User API")
public class UserController {

    @Autowired
    private UserService userService;

    // =========================================== Create New User ========================================

    @ApiOperation(value = "This Rest API is used to create a user.",
            notes = "This API will create a new user.",
            response = User.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created a new user", response = User.class)
    })
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody User user, UriComponentsBuilder ucBuilder) {

        if (userService.exists(user)) {

            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        userService.create(user);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());

        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

}
