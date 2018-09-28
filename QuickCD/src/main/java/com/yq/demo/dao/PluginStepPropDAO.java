package com.yq.demo.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.yq.demo.entity.PluginStepProp;


public class PluginStepPropDAO {
    /** Logger */
    private static final Logger log = Logger.getLogger(PluginStepPropDAO.class.getName());

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public List<PluginStepProp> getAllPluginStepProps() {
        String hsql = "from plugin_step_prop";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hsql);

        return query.list();
    }

    /**
     * get all the pluginsteps of a specified plugin
     * @param projUuid
     * @return
     */
    public List<PluginStepProp> getPluginStepPropsByPluginStepUuid(String pluginStepUuid) {
        String hsql = "from plugin_step_prop where plugin_step_id = ?";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hsql);
        query.setString(0, pluginStepUuid);

        return query.list();
    }

    public PluginStepProp getPluginStepPropByUuid(String pluginStepPropUuid) {
        String hsql = "from plugin_step_prop where id = ?";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hsql);
        query.setString(0, pluginStepPropUuid);

        return (PluginStepProp)query.uniqueResult();
    }

    public PluginStepProp getPluginStepPropByName(String pluginStepPropName, String pluginStepUuid) {
        String hsql = "from plugin_step_prop where name = ? and plugin_step_id=?";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hsql);
        query.setString(0, pluginStepPropName);
        query.setString(1, pluginStepUuid);

        return (PluginStepProp)query.uniqueResult();
    }

    public int delPluginStepPropsByPluginStepUuid(String pluginStepUuid) {
        //第一步获取该plugin的所有pluginstep的seq，
        //第二部，获取使用pluginstep的所有step
        //确保没有任何step使用该pluginStep，然后删除pluginStep，在删除plugin

        // 如果是pluginStep需要在表 plugin_step_props_value删除内容
        String hql = "delete plugin_step_prop where plugin_step_id = ?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0, pluginStepUuid);

        int result = query.executeUpdate();
        return result;
    }

    /*
     * User can use getPluginStepsByPluginUuid method to get the pluginStep uuid
     */
    public void addPluginStepProp(PluginStepProp pluginStepProp) {
        if (pluginStepProp.getName() == null || pluginStepProp.getPluginStepUuid() == null
                || pluginStepProp.getVersion() == null) {
            log.fatal("plug name,pluginStepId,version information is not complete.");
        }

        sessionFactory.getCurrentSession().save(pluginStepProp);
    }

}
