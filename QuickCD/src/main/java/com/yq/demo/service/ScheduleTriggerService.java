package com.yq.demo.service;

import java.util.List;

import com.yq.demo.dao.ScheduleTriggerDAO;
import com.yq.demo.entity.ScheduleTrigger;

public class ScheduleTriggerService {
    private ScheduleTriggerDAO schedTriggerDao;

    public  List<ScheduleTrigger> getAllScheduleTriggers(){
        return schedTriggerDao.getAllScheduleTriggers();
    }

    public ScheduleTriggerDAO getSchedTriggerDao() {
        return schedTriggerDao;
    }

    public void setSchedTriggerDao(ScheduleTriggerDAO schedTriggerDao) {
        this.schedTriggerDao = schedTriggerDao;
    }

    public boolean delScheduleTriggerByUuid(String scheduleUuid) {
        return schedTriggerDao.delScheduleTriggerByUuid(scheduleUuid);
    }

    public String addScheduleTrigger(ScheduleTrigger scheduleTrigger) {
        return schedTriggerDao.addScheduleTrigger(scheduleTrigger);
    }
}
