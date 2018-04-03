package com.qodemy.classroom.service;

import com.qodemy.classroom.model.User;

import java.util.List;

public interface UserService {

    void saveUser(User user);

    List<User> findAllUsers();
}