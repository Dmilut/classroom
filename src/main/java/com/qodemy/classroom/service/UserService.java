package com.qodemy.classroom.service;

import com.qodemy.classroom.model.User;

import java.util.List;

/**
 * @author dmilut
 */

public interface UserService {

    List<User> getAll();

    User findById(long id);

    User findByName(String name);

    void create(User user);

    void update(long id, User user);

    void delete(long id);

    boolean exists(User user);
}