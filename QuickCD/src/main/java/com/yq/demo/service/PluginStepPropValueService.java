package com.yq.demo.service;

import java.util.List;

import com.yq.demo.dao.PluginStepPropValueDAO;
import com.yq.demo.entity.PluginStepPropValue;


public class PluginStepPropValueService {
    private PluginStepPropValueDAO pluginStepPropValueDao;

    public PluginStepPropValueDAO getpluginStepPropValueDao() {
        return pluginStepPropValueDao;
    }

    public void setpluginStepPropValueDao(PluginStepPropValueDAO pluginStepPropValueDao) {
        this.pluginStepPropValueDao = pluginStepPropValueDao;
    }

    public List<PluginStepPropValue> getAllPluginStepPropValues() {
        return pluginStepPropValueDao.getAllPluginStepPropValues();
    }

    public List<PluginStepPropValue> getPluginStepPropValuesByPluginStepUuid(String pluginStepUuid) {
        return pluginStepPropValueDao.getPluginStepPropValuesByPluginStepUuid(pluginStepUuid);
    }

    public List<PluginStepPropValue> getPluginStepPropValuesByStepUuid(String stepUuid) {
        return pluginStepPropValueDao.getPluginStepPropValuesByStepUuid(stepUuid);
    }

    public List<PluginStepPropValue> getPluginStepPropValuesByName(String pluginStepPropName, String pluginStepUuid) {
        return pluginStepPropValueDao.getPluginStepPropValuesByName(pluginStepPropName, pluginStepUuid);
    }

    public void addPluginStep(PluginStepPropValue pluginStepPropValue) {
        pluginStepPropValueDao.addPluginStepPropValue(pluginStepPropValue);
    }

    public PluginStepPropValue getPluginStepPropValueByUuid(String pluginStepPropValueUuid){
        return pluginStepPropValueDao.getPluginStepPropValueByUuid(pluginStepPropValueUuid);
    }

    public int delPluginStepPropValuesByPluginStepPropUuid(String pluginStepPropUuid) {
         return pluginStepPropValueDao.delPluginStepPropValuesByPluginStepPropUuid(pluginStepPropUuid);
    }

    public int delPluginStepPropValuesByStepUuid(String stepUuid) {
        return pluginStepPropValueDao.delPluginStepPropValuesByStepUuid(stepUuid);
    }

    public int delPluginStepPropValuesByPluginStepUuid(String pluginStepUuid) {
        return pluginStepPropValueDao.delPluginStepPropValuesByPluginStepUuid(pluginStepUuid);
   }
}
