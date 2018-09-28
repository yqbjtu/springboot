package com.yq.demo.service;

import java.util.List;

import com.yq.demo.dao.EnvEntryDAO;
import com.yq.demo.entity.EnvEntry;


public class EnvEntryService {
    private EnvEntryDAO envEntryDao;

    public List<EnvEntry> getAllEnvEntries() {
        return envEntryDao.getAllEnvEntries();
    }

    public EnvEntryDAO getEnvEntryDao() {
        return envEntryDao;
    }

    public void setEnvEntryDao(EnvEntryDAO envEntryDao) {
        this.envEntryDao = envEntryDao;
    }

    public void addEnvEntry(EnvEntry envEntry) {
        envEntryDao.addEnvEntry(envEntry);
    }
    public EnvEntry getEnvEntryByUuid(String envEntryUuid){
        return envEntryDao.getEnvEntryByUuid(envEntryUuid);
    }

    public boolean delEnvEntryByUuid(String envEntryUuid) {
        return envEntryDao.delEnvEntryByUuid(envEntryUuid);
    }

    public boolean delEnvEntryByEnvUuid(String envUuid) {
        return envEntryDao.delEnvEntryByEnvUuid(envUuid);
    }

    public boolean updateEnv(EnvEntry envEntry) {
        return envEntryDao.updateEnvEntry(envEntry);
    }

    public List<EnvEntry> getEnvEntriesByEnvUuid(String envUuid) {
        return envEntryDao.getEnvEntriesByEnvUuid(envUuid);
    }
}
