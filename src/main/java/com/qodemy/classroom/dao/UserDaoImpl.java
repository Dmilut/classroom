package com.qodemy.classroom.dao;

import com.qodemy.classroom.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;
/*
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }*/

    public void saveUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @SuppressWarnings("unchecked")
    public List<User> findAllUsers() {
        Session session = sessionFactory.getCurrentSession();
        List<User> userList = session.createQuery("from User").list();

        return userList;
    }
}