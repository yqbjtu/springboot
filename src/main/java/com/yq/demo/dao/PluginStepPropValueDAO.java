package com.yq.demo.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.yq.demo.entity.PluginStepPropValue;


public class PluginStepPropValueDAO {
    /** Logger */
    private static final Logger log = Logger.getLogger(PluginStepPropValueDAO.class.getName());

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public List<PluginStepPropValue> getAllPluginStepPropValues() {
        String hsql = "from plugin_step_prop_value";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hsql);

        return query.list();
    }

    /**
     * get all the pluginstepPropValues of a specified pluginStep
     * @param pluginStepUuid
     * @return
     */
    public List<PluginStepPropValue> getPluginStepPropValuesByPluginStepUuid(String pluginStepUuid) {
        String hsql =
            "from plugin_step_prop_value where plugin_step_prop_id in "
            + " (select id from plugin_step_prop where plugin_step_id=?) ";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hsql);
        query.setString(0, pluginStepUuid);

        return query.list();
    }

    /**
     * get all the pluginstepPropValues of a specified step of project
     * @param projUuid
     * @return
     */
    public List<PluginStepPropValue> getPluginStepPropValuesByStepUuid(String stepUuid) {
        String hsql = "from plugin_step_prop_value where step_id = ?";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hsql);
        query.setString(0, stepUuid);

        return query.list();
    }

    public PluginStepPropValue getPluginStepPropValueByUuid(String pluginStepPropValueUuid) {
        String hsql = "from plugin_step_prop_value where id = ?";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hsql);
        query.setString(0, pluginStepPropValueUuid);

        return (PluginStepPropValue)query.uniqueResult();
    }

    public List<PluginStepPropValue> getPluginStepPropValuesByName(String pluginStepPropName, String stepUuid) {
        String hsql = "from plugin_step_prop_value where name = ? and step_id=?";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hsql);
        query.setString(0, pluginStepPropName);
        query.setString(1, stepUuid);

        return query.list();
    }

    public int delPluginStepPropValuesByPluginStepPropUuid(String pluginStepPropUuid) {
        //第一步获取该plugin的所有pluginstep的seq，
        //第二部，获取使用pluginstep的所有step
        //确保没有任何step使用该pluginStep，然后删除pluginStep，在删除plugin

        // 如果是pluginStep需要在表 plugin_step_props_value删除内容
        String hql = "delete plugin_step_prop_value where plugin_step_prop_id = ?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0, pluginStepPropUuid);

        int result = query.executeUpdate();
        return result;
    }

    public int delPluginStepPropValuesByPluginStepUuid(String pluginStepUuid) {
        //第一步获取该plugin的所有pluginstep的seq，
        //第二部，获取使用pluginstep的所有step
        //确保没有任何step使用该pluginStep，然后删除pluginStep，在删除plugin

        // 如果是pluginStep需要在表 plugin_step_props_value删除内容
        String hql = "delete plugin_step_prop_value where plugin_step_prop_id "
                + "in (select id from plugin_step_prop where plugin_step_id=?) ";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0, pluginStepUuid);

        int result = query.executeUpdate();
        return result;
    }

    public int delPluginStepPropValuesByStepUuid(String stepUuid) {
        //第一步获取该plugin的所有pluginstep的seq，
        //第二部，获取使用pluginstep的所有step
        //确保没有任何step使用该pluginStep，然后删除pluginStep，在删除plugin

        // 如果是pluginStep需要在表 plugin_step_props_value删除内容
        String hql = "delete plugin_step_prop_value where step_id = ?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0, stepUuid);

        int result = query.executeUpdate();
        return result;
    }

    /*
     * User can use getPluginStepsByPluginUuid method to get the pluginStep uuid
     */
    public void addPluginStepPropValue(PluginStepPropValue pluginStepPropValue) {
        if (pluginStepPropValue.getName() == null || pluginStepPropValue.getPluginStepPropUuid() == null
            || pluginStepPropValue.getStepUuid() == null || pluginStepPropValue.getValue() == null) {
            log.fatal("pluginProp name,pluginStepPropUuid,StepUuid, value information is not complete.");
        }

        sessionFactory.getCurrentSession().save(pluginStepPropValue);
    }

}
