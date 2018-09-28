package com.yq.demo.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.yq.demo.entity.PluginStep;


public class PluginStepDAO {
    /** Logger */
    private static final Logger log = Logger.getLogger(PluginStepDAO.class.getName());

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public List<PluginStep> getAllPluginSteps() {
        String hsql = "from plugin_step";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hsql);

        return query.list();
    }

    public PluginStep getPluginStepByUuid(String pluginStepUuid) {
        String hsql = "from plugin_step where id = ?";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hsql);
        query.setString(0, pluginStepUuid);

        return (PluginStep)query.uniqueResult();
    }

    public PluginStep getPluginStepByName(String pluginStepName) {
        String hsql = "from plugin_step where name = ?";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hsql);
        query.setString(0, pluginStepName);

        return (PluginStep)query.uniqueResult();
    }

    /**
     * get all the pluginsteps of a specified plugin
     * @param projUuid
     * @return
     */
    public List<PluginStep> getPluginStepsByPluginUuid(String pluginUuid) {
        String hsql = "from plugin_step where plugin_id = ?";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hsql);
        query.setString(0, pluginUuid);

        return query.list();
    }

    public int delPluginStepByUuid(String pluginStepUuid) {
        //第一步获取该plugin的所有pluginstep的seq，
        //第二部，获取使用pluginstep的所有step
        //确保没有任何step使用该pluginStep，然后删除pluginStep，在删除plugin

        // 如果是pluginStep需要在表 plugin_step_props_value删除内容
        String hql = "delete plugin_step where id = ?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0, pluginStepUuid);

        int result = query.executeUpdate();
        return result;
    }

    public int delPluginStepsByPluginUuid(String pluginUuid) {
        //第一步获取该plugin的所有pluginstep的seq，
        //第二部，获取使用pluginstep的所有step
        //确保没有任何step使用该pluginStep，然后删除pluginStep，在删除plugin

        // 如果是pluginStep需要在表 plugin_step_props_value删除内容
        String hql = "delete plugin_step where plugin_id = ?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0, pluginUuid);

        int count = query.executeUpdate();
        return count;
    }

    /*
     * User can use getPluginStepsByPluginUuid method to get the pluginStep uuid
     */
    public void addPluginStep(PluginStep pluginStep) {
        if (pluginStep.getName() == null || pluginStep.getPluginUuid() == null
                || pluginStep.getVersion() == null) {
            log.fatal("plug name,pluginId,version information is not complete.");
        }

        sessionFactory.getCurrentSession().save(pluginStep);
    }

}
