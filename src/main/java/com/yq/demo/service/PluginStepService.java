package com.yq.demo.service;

import java.util.List;

import com.yq.demo.dao.PluginStepDAO;
import com.yq.demo.entity.PluginStep;


public class PluginStepService {
    private PluginStepDAO pluginStepDao;

    public List<PluginStep> getAllPluginSteps() {
        return pluginStepDao.getAllPluginSteps();
    }

    public List<PluginStep> getPluginStepsByPluginUuid(String pluginUuid) {
        return pluginStepDao.getPluginStepsByPluginUuid(pluginUuid);
    }

    public PluginStepDAO getpluginStepDao() {
        return pluginStepDao;
    }

    public void setpluginStepDao(PluginStepDAO pluginStepDao) {
        this.pluginStepDao = pluginStepDao;
    }

    public void addPluginStep(PluginStep pluginStep) {
        pluginStepDao.addPluginStep(pluginStep);
    }

    public PluginStep getPluginStepByUuid(String pluginStepUuid){
        return pluginStepDao.getPluginStepByUuid(pluginStepUuid);
    }

    public PluginStep getPluginStepByName(String pluginStepName) {
         return pluginStepDao.getPluginStepByName(pluginStepName);
    }


    public int delPluginByUuid(String pluginStepUuid) {
         return pluginStepDao.delPluginStepByUuid(pluginStepUuid);
    }

    public int delPluginStepsByPluginUuid(String pluginUuid) {
        return pluginStepDao.delPluginStepsByPluginUuid(pluginUuid);
   }
}
