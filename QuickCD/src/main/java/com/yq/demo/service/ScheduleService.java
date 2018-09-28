package com.yq.demo.service;

import java.util.List;

import com.yq.demo.dao.ScheduleDAO;
import com.yq.demo.entity.Schedule;

public class ScheduleService {
    private ScheduleDAO scheduleDao;

    public int scheduleCount(){
        return scheduleDao.getAllSchedules().size();
    }

    public  List<Schedule> getAllSchedules(){
        return scheduleDao.getAllSchedules();
    }

    public ScheduleDAO getProjectDao() {
        return scheduleDao;
    }

    public void setScheduleDao(ScheduleDAO scheduleDao) {
        this.scheduleDao = scheduleDao;
    }

    public Schedule getScheduleByName(String scheduleName){
        return scheduleDao.getScheduleByName(scheduleName);
    }

    public Schedule getScheduleByUuid(String scheduleUuid) {
         return scheduleDao.getScheduleByUuid(scheduleUuid);
    }

    public boolean delScheduleByUuid(String scheduleUuid) {
        return scheduleDao.delScheduleByUuid(scheduleUuid);
    }

    public String addSchedule(Schedule schedule) {
        return scheduleDao.addSchedule(schedule);
    }
}
