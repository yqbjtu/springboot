package com.yq.demo.service;

import java.util.List;

import com.yq.demo.dao.PluginDAO;
import com.yq.demo.entity.Plugin;


public class PluginService {
    private PluginDAO pluginDao;

    public List<Plugin> getAllPlugins() {
        return pluginDao.getAllPlugins();
    }

    public PluginDAO getPluginDao() {
        return pluginDao;
    }

    public void setPluginDao(PluginDAO pluginDao) {
        this.pluginDao = pluginDao;
    }

    public void addPlugin(Plugin plugin) {
        pluginDao.addPlugin(plugin);
    }

    public Plugin getPluginByUuid(String pluginUuid){
        return pluginDao.getPluginByUuid(pluginUuid);
    }

    public Plugin getPluginByName(String pluginName) {
         return pluginDao.getPluginByName(pluginName);
    }


    public int delPluginByUuid(String pluginUuid) {
         return pluginDao.delPluginByUuid(pluginUuid);
    }
}
