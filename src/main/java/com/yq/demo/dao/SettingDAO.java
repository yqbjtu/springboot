package com.yq.demo.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.yq.demo.entity.Setting;

public class SettingDAO {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public List<Setting> getAllSettings() {
        String hsql = "from setting";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hsql);

        return query.list();
    }

    public Setting getSettingByName(String name) {
        String hsql="from setting where name=?";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hsql);
        query.setString(0, name);

        return (Setting)query.uniqueResult();
    }


    public Setting getSettingByUuid(String uuid) {
        String hql = "from setting where id=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0, uuid);

        return (Setting)query.uniqueResult();
    }

    public void addSetting(Setting setting) {
        sessionFactory.getCurrentSession().save(setting);
    }

    public boolean delSettingByUuid(String uuid) {
        String hql = "delete setting where id = ?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0, uuid);

        return (query.executeUpdate() > 0);
    }

    public boolean updateSetting(Setting setting) {
        String hql =
            "update setting set name=?, value=?, description=?, data=? where id = ?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);

        query.setString(0, setting.getName());
        query.setString(1, setting.getValue());
        query.setString(2, setting.getDesc());
        query.setString(3, setting.getData());
        query.setString(4, setting.getUuid());

        return (query.executeUpdate() > 0);
    }
}
