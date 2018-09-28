package com.yq.demo.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.yq.demo.entity.Project;

public class ProjectDAO {
    /** Logger */
    private static final Logger log = Logger.getLogger(ProjectDAO.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public List<Project> getAllProjects() {
        String hsql = "from project";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hsql);

        return query.list();
    }

    public Project getProjectByName(String projName){
        String hsql="from project where name=?";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hsql);
        query.setString(0, projName);

        return (Project)query.uniqueResult();
    }

    public Project getProjectByUuid(String projUuid) {
        String hql = "from project where id=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0, projUuid);

        return (Project)query.uniqueResult();
    }

    public List<Project> getProjectsByEnvUuid(String envUuid) {
        String hql = "from project where envid=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0, envUuid);

        return query.list();
    }

    public String addProject(Project proj) {
        if (proj.getName() == null || proj.getAgentPoolUuid() == null ) {
            log.warn("add new project,but project name or agentpool information is not complete.");
        }

        sessionFactory.getCurrentSession().save(proj);
        return proj.getUuid();
    }

    public boolean delProjectByUuid(String projUuid) {
        boolean isDeleted = StepDAO.delStepsByProjUuid(sessionFactory, projUuid);
        String hql = "delete project where id = ?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0, projUuid);

        return (query.executeUpdate() > 0);
    }

    public boolean updateProject(Project proj) {
        String hql = "update project set name=?, active=?, agentpoolid=?, data=?, description=?, envid=? where id = ?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);

        query.setString(0, proj.getName());
        query.setInteger(1, proj.getActive());
        query.setString(2, proj.getAgentPoolUuid());
        query.setString(3, proj.getData());
        query.setString(4, proj.getDesc());
        query.setString(5, proj.getEnvUuid());
        query.setString(6, proj.getUuid());

        return (query.executeUpdate() > 0);
    }
}
