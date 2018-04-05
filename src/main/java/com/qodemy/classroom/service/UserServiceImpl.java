package com.qodemy.classroom.service;


import com.qodemy.classroom.dao.UserDao;
import com.qodemy.classroom.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public User findByName(String name) {
        return null;
    }

    @Override
    public void create(User user) {
        userDao.create(user);
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public boolean exists(User user) {
        return false;
    }
}