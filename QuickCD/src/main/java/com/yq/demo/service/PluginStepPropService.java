package com.yq.demo.service;

import java.util.List;

import com.yq.demo.dao.PluginStepPropDAO;
import com.yq.demo.entity.PluginStepProp;


public class PluginStepPropService {
    private PluginStepPropDAO pluginStepPropDao;

    public PluginStepPropDAO getPluginStepPropDao() {
        return pluginStepPropDao;
    }

    public void setpluginStepPropDao(PluginStepPropDAO pluginStepPropDao) {
        this.pluginStepPropDao = pluginStepPropDao;
    }

    public List<PluginStepProp> getAllPluginStepProps() {
        return pluginStepPropDao.getAllPluginStepProps();
    }

    public List<PluginStepProp> getPluginStepPropsByPluginStepUuid(String pluginStepUuid) {
        return pluginStepPropDao.getPluginStepPropsByPluginStepUuid(pluginStepUuid);
    }

    public void addPluginStepProp(PluginStepProp pluginStepProp) {
        pluginStepPropDao.addPluginStepProp(pluginStepProp);
    }

    public PluginStepProp getPluginStepPropByUuid(String pluginStepPropUuid){
        return pluginStepPropDao.getPluginStepPropByUuid(pluginStepPropUuid);
    }

    public PluginStepProp getPluginStepPropByName(String pluginStepPropName, String pluginStepUuid) {
         return pluginStepPropDao.getPluginStepPropByName(pluginStepPropName, pluginStepUuid);
    }

    public int delPluginStepPropsByPluginStepUuid(String pluginStepUuid) {
         return pluginStepPropDao.delPluginStepPropsByPluginStepUuid(pluginStepUuid);
    }
}
