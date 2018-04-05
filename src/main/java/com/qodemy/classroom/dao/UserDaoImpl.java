package com.qodemy.classroom.dao;

import com.qodemy.classroom.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dmilut
 */

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    @Override
    public List<User> getAll() {
        Session session = sessionFactory.getCurrentSession();
        List<User> userList;
        userList = session.createQuery("from User").list();

        return userList;
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
        sessionFactory.getCurrentSession().save(user);
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