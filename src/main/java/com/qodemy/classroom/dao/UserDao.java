package com.qodemy.classroom.dao;

import com.qodemy.classroom.model.User;

import java.util.List;

/**
 * @author dmilut
 */

public interface UserDao {

    List<User> getAll();

    User findById(int id);

    User findByName(String name);

    void create(User user);

    void update(User user);

    void delete(int id);

    boolean exists(User user);
}

