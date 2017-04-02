package com.sevenj.service.impl;

import java.util.Calendar;
import java.util.List;

import com.sevenj.dal.dao.ProjectDAO;
import com.sevenj.dal.dao.impl.ProjectDAOImpl;
import com.sevenj.model.Project;
import com.sevenj.service.ProjectService;

public class ProjectServiceImpl implements ProjectService{
    private ProjectDAO projectDao;
    
    public ProjectServiceImpl(ProjectDAO dao){
    	this.projectDao = dao;
    }
    
    public ProjectServiceImpl(){
    	this.projectDao = new ProjectDAOImpl();
    }
    
	public List<Project> getAllProjects(String sortName) throws Exception {
		return projectDao.getAllProjects(sortName);
	}

	public void createNewProject(Project project) throws Exception {
		projectDao.insertProject(project);
	}

	public void deleteProject(String projectId) throws Exception {
		projectDao.deleteProject(projectId);
	}

	public void updateProject(Project project) throws Exception {
		Project oldProject = projectDao.getProject(project.getProjectId());
		oldProject.setProjectName(project.getProjectName());
		oldProject.setPeat(project.getPeat());
		projectDao.updateProject(oldProject);
	}

	public Project getProject(String projectId) throws Exception {
		return projectDao.getProject(projectId);
	}
}
