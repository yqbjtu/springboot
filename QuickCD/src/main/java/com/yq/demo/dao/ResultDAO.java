package com.yq.demo.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.yq.demo.entity.Result;


public class ResultDAO {
    /** Logger */
    private static final Logger log = Logger.getLogger(ResultDAO.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public List<Result> getResultByBuildUuid(String buildUuid) {
        //String hsql = "from result as r where r.buildid = ? order by r.seq";
        String hsql = "from result  where buildid = ? order by seq";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hsql);
        query.setString(0, buildUuid);

        return query.list();
    }

    public boolean delResultByBuildUuid(String buildUuid) {
        String hsql = "delete result where buildid = ?";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hsql);
        query.setString(0, buildUuid);

        return (query.executeUpdate() >= 0);
    }

    public Result getResultByUuid(String resultUuid) {
        String hsql = "from result where id = ?";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hsql);
        query.setString(0, resultUuid);

        return (Result)query.uniqueResult();
    }

    public String addResult(Result result) {
        if (result.getBuildUuid() == null || result.getSeq() == -1 ) {
            log.warn("result build uuid and seq information is not complete.");
        }

        sessionFactory.getCurrentSession().save(result);
        return result.getUuid();
    }

    public boolean updateResult(Result result) {
        String hql = "update result set status=?, startdate=?, enddate=?,  agentid=?, envid=?,data=? where id = ?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);

        query.setInteger(0, result.getStatus().getIndex());
        query.setTimestamp(1, result.getStartDate());
        query.setTimestamp(2, result.getEndDate());
        query.setString(3, result.getAgentUuid());
        query.setString(4, result.getEnvUuid());
        query.setString(5, result.getData());
        query.setString(6, result.getUuid());

        return (query.executeUpdate() > 0);
    }
}
