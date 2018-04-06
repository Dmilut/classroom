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
    public User findById(long id) {
        return sessionFactory.getCurrentSession().get(User.class, id);
    }

    @Override
    public User findByName(String name) {
        return sessionFactory.getCurrentSession().get(User.class, name);
    }

    @Override
    public void create(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public void update(long id, User user) {
        Session session = sessionFactory.getCurrentSession();
        User currentUser = session.byId(User.class).load(id);
        currentUser.setName(user.getName());
/*        currentUser.setEmail(user.getEmail());
        currentUser.setLogin(user.getLogin());
        currentUser.setPassword(user.getPassword());*/

        session.flush();
    }

    @Override
    public void delete(long id) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.byId(User.class).load(id);

        session.delete(user);
    }

    @Override
    public boolean exists(User user) {
        //TODO we should check by login instead of id.
        if (findById(user.getId()) != null) {
            return true;
        }
        return false;
    }
}