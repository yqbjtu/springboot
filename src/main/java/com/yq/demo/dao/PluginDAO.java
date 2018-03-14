package com.yq.demo.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.yq.demo.entity.Plugin;


public class PluginDAO {
    /** Logger */
    private static final Logger log = Logger.getLogger(PluginDAO.class.getName());

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public List<Plugin> getAllPlugins() {
        String hsql = "from plugin";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hsql);

        return query.list();
    }

    public Plugin getPluginByUuid(String pluginUuid) {
        String hsql = "from plugin where id = ?";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hsql);
        query.setString(0, pluginUuid);

        return (Plugin)query.uniqueResult();
    }

    public Plugin getPluginByName(String pluginName) {
        String hsql = "from plugin where name = ?";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hsql);
        query.setString(0, pluginName);

        return (Plugin)query.uniqueResult();
    }


    public int delPluginByUuid(String pluginUuid) {
        //第一步获取该plugin的所有pluginstep的seq，
        //第二部，获取使用pluginstep的所有step
        //确保没有任何step使用该pluginStep，然后删除pluginStep，在删除plugin

        // 如果是pluginStep需要在表 plugin_step_props_value删除内容
        String hql = "delete plugin where id = ?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0, pluginUuid);

        int result = query.executeUpdate();
        return result;
    }

    /*
     * user can use getbyName method to get the plugin uuid
     */
    public void addPlugin(Plugin plugin) {
        if (plugin.getName() == null || plugin.getIdentifier() == null
                || plugin.getVersion() == null) {
            log.fatal("plug name,identifier,version information is not complete.");
        }

        sessionFactory.getCurrentSession().save(plugin);
    }

}
