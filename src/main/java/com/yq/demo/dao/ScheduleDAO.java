package com.yq.demo.dao;

import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.yq.demo.entity.Schedule;

public class ScheduleDAO {
    /** Logger */
    private static final Logger log = Logger.getLogger(ScheduleDAO.class.getName());

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public List<Schedule> getAllSchedules() {
        String hsql = "from schedule";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hsql);

        return query.list();
    }

    public Schedule getScheduleByName(String scheduleName){
        String hsql="from schedule where name=?";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hsql);
        query.setString(0, scheduleName);

        return (Schedule)query.uniqueResult();
    }

    public Schedule getScheduleByUuid(String scheduleUuid) {
        String hql = "from schedule where uuid=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0, scheduleUuid);

        return (Schedule)query.uniqueResult();
    }

    public String addSchedule(Schedule schedule) {
        if (schedule.getName() == null || schedule.getData() == null ) {
            //还应该进行cron表达式的检测
            log.severe("add new schedule,but schedule name or cron information is not complete.");
        }

        sessionFactory.getCurrentSession().save(schedule);
        return schedule.getUuid();
    }

    public boolean delScheduleByUuid(String scheduleUuid) {
        //先确保没有scheduleTrigger才能删除
        String hql = "delete schedule where uuid= ?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0, scheduleUuid);

        return (query.executeUpdate() > 0);
    }
}
