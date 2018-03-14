package com.yq.demo.service;

import java.util.List;

import com.yq.demo.dao.EnvDAO;
import com.yq.demo.entity.Env;
import com.yq.demo.entity.EnvEntry;


public class EnvService {
    private EnvDAO envDao;

    public List<Env> getAllEnvs() {
        return envDao.getAllEnvs();
    }

    public EnvDAO getEnvDao() {
        return envDao;
    }

    public void setEnvDao(EnvDAO envDao) {
        this.envDao = envDao;
    }

    public void addEnv(Env env) {
        envDao.addEnv(env);
    }
    public Env getEnvByUuid(String envUuid){
        return envDao.getEnvByUuid(envUuid);
    }

    /**
     * get all the env entries of a specified env
     * @param envUuid
     * @return
     */
    public List<EnvEntry> getEnvEntriesByUuid(String envUuid) {
         return envDao.getEnvEntriesByUuid(envUuid);
    }

    /*
     * delete entry firstly, then del env itself
     */
    public boolean delEnvByUuid(String envUuid) {
        return envDao.delEnvByUuid(envUuid);
    }

    public boolean updateEnv(Env env) {
        return envDao.updateEnv(env);
    }
}
