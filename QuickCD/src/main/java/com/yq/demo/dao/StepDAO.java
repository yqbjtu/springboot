package com.yq.demo.dao;

import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.yq.demo.entity.Step;


public class StepDAO {
    /** Logger */
    private static final Logger log = Logger.getLogger(StepDAO.class.getName());

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public List<Step> getAllSteps() {
        String hsql = "from step";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hsql);

        return query.list();
    }

    public Step getStepByStepUuid(String stepUuid) {
        String hsql = "from step where id = ?";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hsql);
        query.setString(0, stepUuid);

        return (Step)query.uniqueResult();
    }

    /**
     * get all the steps of a specific project
     * @param projUuid
     * @return
     */
    public List<Step> getStepsByProjUuidAndStepName(String projUuid, String stepName) {
        String hsql = "from step where projectid = ? and name=? order by seq";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hsql);
        query.setString(0, projUuid);
        query.setString(1, stepName);

        return query.list();
    }

    /**
     * get all the steps of a specified project
     * @param projUuid
     * @return
     */
    public List<Step> getStepsByProjUuid(String projUuid) {
        String hsql = "from step where projectid = ? order by seq";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hsql);
        query.setString(0, projUuid);

        return query.list();
    }

    public List<Step> getStepsByEnvUuid(String envUuid) {
        String hsql = "from step where envid = ?";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hsql);
        query.setString(0, envUuid);

        return query.list();
    }

    public boolean delStepsByProjUuid(String projUuid) {
        return delStepsByProjUuid(sessionFactory, projUuid);
    }

    public static boolean delStepsByProjUuid(SessionFactory sessionFactory, String projUuid) {
        String hql = "delete step where projectid = ?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0, projUuid);

        return (query.executeUpdate() >= 0);
    }

    public boolean delStepByUuid(String stepUuid) {
        //第一步获取该step的seq，然后修改其余所有step的seq
        Step step = getStepByStepUuid(stepUuid);
        boolean result = true;
        Integer seq = 1;
        if (null == step) {
            log.info("can't find step uuid:" + stepUuid);
            result = false;
        } else {
            seq = step.getSeq();

            //如果是pluginStep需要在表     plugin_step_props_value删除内容
            String hql = "delete plugin_step_prop_value where step_id = ?";
            Query query = sessionFactory.getCurrentSession().createQuery(hql);
            query.setString(0, stepUuid);
            int propValueCount = query.executeUpdate();
            log.info("delete " + propValueCount + " propValues.");

            hql = "delete step where id = ?";
            query = sessionFactory.getCurrentSession().createQuery(hql);
            query.setString(0, stepUuid);

            result = (query.executeUpdate() == 0);

            hql = "update step set seq = seq -1 where projectid = ? and seq > ?";
            query = sessionFactory.getCurrentSession().createQuery(hql);
            query.setString(0, step.getProjectUuid());
            query.setInteger(1, seq);
            int count = query.executeUpdate();
            log.info("affected rows:" + count);
        }

        return result;
    }

    public void addStep(Step step) {
        if (step.getName() == null || step.getProjectUuid() == null
                || step.getCmd() == null) {
            log.severe("step name,cmd.project information is not complete.");
        }

        /*String hsql = "SELECT MAX(sp.seq) FROM step sp where projectid = ?";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hsql);
        query.setString(0, step.getProjectUuid());

        Integer seq = query.getFirstResult();*/
        Integer seq = getStepsByProjUuid(step.getProjectUuid()).size() + 1;
        step.setSeq(seq);
        sessionFactory.getCurrentSession().save(step);
        log.info("NewStepUuid:" + step.getUuid());
        //如果是pluginStep需要在表     plugin_step_props_value填充内容

    }

    public void updateStep(Step step) {
        //step sql, projectUuid不能修改，active可以单独使用，不在更新用户中
        /*String hql = "update user set username=?, password=?, fullname=?, email=? ,dateformat=? ,"
                + " timeformat=?, timezone=? ,language=?, data=? "
                + " where id = ?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0, step.getUsername());
        query.setString(1, step.getPassword());
        query.setString(2, step.getFullname());
        query.setString(3, step.getEmail());
        query.setString(4, step.getDateFormat());
        query.setString(5, step.getTimeFormat());
        query.setString(6, step.getTimeZone());
        query.setString(7, step.getLanguage());
        query.setString(8, step.getData());
        query.setInteger(9, step.getId());

        return (query.executeUpdate() > 0);*/


      //如果是pluginStep需要在表     plugin_step_props_value更新内容
    }
}
