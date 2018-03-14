package com.yq.demo.service;

import java.util.List;

import com.yq.demo.common.BuildStatusEnum;
import com.yq.demo.dao.*;
import com.yq.demo.entity.*;

public class BuildService {
    private BuildDAO buildDao;

    public  List<Build> getAllBuilds(){
        return buildDao.getAllBuilds();
    }
    
    public  List<Build> getBuildByProjectUuid(String projUuid){
        return buildDao.getBuildByProject(projUuid);
    }
    
    public BuildDAO getBuildDao() {
        return buildDao;
    }

    public void setBuildDao(BuildDAO buildDao) {
        this.buildDao = buildDao;
    }

    
    public Build getBuildByUuid(String buildUuid) {  
         return buildDao.getBuildByUuid(buildUuid);
    }
    
    public List<Build> getBuildByStatus(BuildStatusEnum statusEnum) {
    	 return buildDao.getBuildByStatus(statusEnum);
    }
    
    public boolean delBuildByUuid(String buildUuid) {  
        return buildDao.delBuildByUuid(buildUuid);
    }
    
    public boolean updateBuild(Build build) {  
        return buildDao.updateBuild(build);
    }
    
    public String addBuild(Build build) {  
        return buildDao.addBuild(build);
    }
}
