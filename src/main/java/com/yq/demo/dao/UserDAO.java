package com.yq.demo.dao;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.yq.demo.entity.User;

public class UserDAO {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public List<User> getAllUser(){

        String hsql="from user";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hsql);

        return query.list();
    }

    public User getUserByName(String username){

        String hsql="from user where username=?";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hsql);
        query.setString(0, username);

        return (User)query.uniqueResult();
    }

    public User getUserByID(Integer id) {

        String hql = "from user where id=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setInteger(0, id);

        return (User)query.uniqueResult();
    }


    public void addUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    public boolean delUser(Integer id) {

        String hql = "delete user where id = ?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setInteger(0, id);

        return (query.executeUpdate() > 0);
    }

    public boolean updateUser(User user) {

        //usertype 在创建是决定不能修改，active可以单独使用，不在更新用户中，can_delete内部属性，暂时不提供更改
        String hql = "update user set username=?, password=?, fullname=?, email=? ,dateformat=? ,"
                + " timeformat=?, timezone=? ,language=?, data=? "
                + " where id = ?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0, user.getUsername());
        query.setString(1, user.getPassword());
        query.setString(2, user.getFullname());
        query.setString(3, user.getEmail());
        query.setString(4, user.getDateFormat());
        query.setString(5, user.getTimeFormat());
        query.setString(6, user.getTimeZone());
        query.setString(7, user.getLanguage());
        query.setString(8, user.getData());
        query.setInteger(9, user.getId());

        return (query.executeUpdate() > 0);
    }
}