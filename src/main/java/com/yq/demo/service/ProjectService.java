package com.yq.demo.service;

import java.util.List;

import com.yq.demo.dao.ProjectDAO;
import com.yq.demo.entity.Project;

public class ProjectService {
    private ProjectDAO projectDao;

    public int projectCount(){
        return projectDao.getAllProjects().size();
    }

    public  List<Project> getAllProjects(){
        return projectDao.getAllProjects();
    }

    public ProjectDAO getProjectDao() {
        return projectDao;
    }

    public void setProjectDao(ProjectDAO projectDao) {
        this.projectDao = projectDao;
    }

    public Project getProjectByName(String projName){
        return projectDao.getProjectByName(projName);
    }

    public Project getProjectByUuid(String projUuid) {
         return projectDao.getProjectByUuid(projUuid);
    }

    public boolean delProjectByUuid(String projUuid) {
        return projectDao.delProjectByUuid(projUuid);
    }

    public String addProject(Project proj) {
        return projectDao.addProject(proj);
    }

    public boolean updateProject(Project proj) {
        return projectDao.updateProject(proj);
    }
}
