package com.yq.demo.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.yq.demo.common.BuildStatusEnum;
import com.yq.demo.entity.Build;


public class BuildDAO {
    /** Logger */
    private static final Logger log = Logger.getLogger(BuildDAO.class.getName());

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public List<Build> getAllBuilds() {
        String hsql = "from build order by buildno";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hsql);

        return query.list();
    }

    public List<Build> getBuildByProject(String projUuid) {
        String hsql="from build where projectid=? order by createdate";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hsql);
        query.setString(0, projUuid);

        return query.list();
    }

    public List<Build> getBuildByStatus(BuildStatusEnum statusEnum) {
        String hsql="from build  where status = ? order by createdate";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hsql);
        query.setInteger(0, statusEnum.getIndex());

        return query.list();
    }

    public Build getBuildByUuid(String buildUuid) {
        String hql = "from build as b where b.id=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0, buildUuid);

        return (Build)query.uniqueResult();
    }

    public boolean delBuildByUuid(String buildUuid) {
        //先删除所有Step log文件，然后删除step result，最后删除build 记录
        int count = 0;
        String hql = "delete result where buildid = ?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0, buildUuid);

        count = query.executeUpdate();

        hql = "delete build where id = ?";
        query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0, buildUuid);

        return (query.executeUpdate() > 0);
    }

    public String addBuild(Build build) {
        if (build.getProjectUuid() == null || build.getUserId() == -1 ) {
            log.warn("build projectId, userId information is not complete.");
        }

        sessionFactory.getCurrentSession().save(build);
        return build.getUuid();
    }

    public boolean updateBuild(Build build) {
        //result只能修改result和结束时间
        String hql = "update build set status=?, result=?, startDate=?, endDate=?  where id = ?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);

        query.setInteger(0, build.getStatus().getIndex());
        query.setInteger(1, build.getResult().getIndex());
        query.setTimestamp(2, build.getStartDate());
        query.setTimestamp(3, build.getEndDate());
        query.setString(4, build.getUuid());

        return (query.executeUpdate() > 0);
    }
}
