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

    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    public List<User> findAllUsers() {

        return userDao.findAllUsers();
    }
}