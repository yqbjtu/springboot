package com.yq.demo.dao;

import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.yq.demo.entity.ScheduleTrigger;

public class ScheduleTriggerDAO {
    /** Logger */
    private static final Logger log = Logger.getLogger(ScheduleTriggerDAO.class.getName());

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public List<ScheduleTrigger> getAllScheduleTriggers() {
        String hsql = "from scheduletrigger";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hsql);

        return query.list();
    }

    public String addScheduleTrigger(ScheduleTrigger scheduleTrigger) {
        if (scheduleTrigger.getScheduuid() == null || scheduleTrigger.getProjuuid() == null ) {
            //还应该进行cron表达式的检测
            log.severe("add new scheduleTrigger,but schedule uuid or projecuuid information is not complete.");
        }

        sessionFactory.getCurrentSession().save(scheduleTrigger);
        return scheduleTrigger.getUuid();
    }

    public boolean delScheduleTriggerByUuid(String scheduleTriggerUuid) {
        String hql = "delete scheduletrigger where uuid= ?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0, scheduleTriggerUuid);

        return (query.executeUpdate() > 0);
    }
}
