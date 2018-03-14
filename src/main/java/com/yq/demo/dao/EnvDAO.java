package com.yq.demo.dao;

import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.yq.demo.entity.Env;
import com.yq.demo.entity.EnvEntry;


public class EnvDAO {
    /** Logger */
    private static final Logger log = Logger.getLogger(EnvDAO.class.getName());

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public List<Env> getAllEnvs() {

        String hsql = "from env";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hsql);

        return query.list();
    }

    public Env getEnvByUuid(String envUuid) {

        String hsql = "from env where id = ?";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hsql);
        query.setString(0, envUuid);

        return (Env)query.uniqueResult();
    }

    /**
     * get all the envEntry of a specific env
     * @param projUuid
     * @return
     */
    public List<EnvEntry> getEnvEntriesByUuid(String envUuid) {

        String hsql = "from enventry where envid = ? order by seq";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hsql);
        query.setString(0, envUuid);
        return query.list();
    }
    /*
     * del the envEntry firstly, then del env itself
     */
    public boolean delEnvByUuid(String envUuid) {
        //第一步确保没有project或者agent， step在使用env

        //第二步获取该env的所有enventry
        boolean result = true;
        String hql = "delete enventry where envid =  ?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0, envUuid);

        int count = query.executeUpdate();
        log.info("affected rows:" + count);

        //第三步骤
        hql = "delete env where id = ?";
        query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0, envUuid);
        count = query.executeUpdate();
        log.info("affected rows:" + count);

        return count == 1 ? true : false;
    }

    public void addEnv(Env env) {
        if (env.getName() == null) {
            log.severe("env name information is not complete.");
        }

        sessionFactory.getCurrentSession().save(env);
    }

    public boolean updateEnv(Env env) {
        String hql = "update env set name=?, description=?  where id = ?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0, env.getName());
        query.setString(1, env.getDesc());
        query.setString(2, env.getUuid());

        return (query.executeUpdate() > 0);
    }
}
