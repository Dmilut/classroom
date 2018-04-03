package com.qodemy.classroom.dao;

import com.qodemy.classroom.model.User;

import java.util.List;

public interface UserDao {

    void saveUser(User user);

    List<User> findAllUsers();
}
