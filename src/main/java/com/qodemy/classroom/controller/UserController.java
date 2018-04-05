package com.qodemy.classroom.controller;


import com.qodemy.classroom.model.User;
import com.qodemy.classroom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author dmilut
 */

@RestController
@RequestMapping("/users")
public class UserController {


    @Autowired
    private UserService userService;

    // =========================================== Create New User ========================================
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
