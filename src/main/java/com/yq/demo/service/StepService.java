package com.yq.demo.service;

import java.util.List;

import com.yq.demo.dao.*;
import com.yq.demo.entity.*;


public class StepService {
    private StepDAO stepDao;


    public List<Step> getAllSteps() {
        return stepDao.getAllSteps();
    }
    
    public StepDAO getStepDao() {
        return stepDao;
    }

    public void setStepDao(StepDAO stepDao) {
        this.stepDao = stepDao;
    }

    public void addStep(Step step) {
    	stepDao.addStep(step);
    }
    public Step getStepByUuid(String stepUuid){      
        return stepDao.getStepByStepUuid(stepUuid);
    }
    
    /**
     * get all the steps of a specified project 
     * @param projUuid
     * @return
     */
    public List<Step> getStepsByProjUuid(String projUuid) {  
         return stepDao.getStepsByProjUuid(projUuid);
    }
    
    public boolean delStepsByProjUuid(String projUuid) {  
        return stepDao.delStepsByProjUuid(projUuid);
    }
    
    public boolean delStepByUuid(String stepUuid) {
    	 return stepDao.delStepByUuid(stepUuid);
    }
}
