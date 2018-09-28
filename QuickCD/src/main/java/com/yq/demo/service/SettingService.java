package com.yq.demo.service;

import java.util.List;

import com.yq.demo.dao.SettingDAO;
import com.yq.demo.entity.Setting;

public class SettingService {
    private SettingDAO settingDao;


    public List<Setting> getAllSettings() {
        return settingDao.getAllSettings();
    }

    public SettingDAO getSettingDao() {
        return settingDao;
    }

    public void setSettingDao(SettingDAO settingDao) {
        this.settingDao = settingDao;
    }

    public void addSetting(Setting setting) {
        settingDao.addSetting(setting);
    }

    public boolean delSettingByUuid(String uuid) {
        return settingDao.delSettingByUuid(uuid);
    }

    public boolean updateSetting(Setting setting) {
        return settingDao.updateSetting(setting);
    }

    public Setting getSettingByName(String name) {
        return settingDao.getSettingByName(name);
    }


}
