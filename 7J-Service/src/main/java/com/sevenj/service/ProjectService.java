package com.sevenj.service;

import java.util.List;

import com.sevenj.model.Project;

public interface ProjectService {
	public List<Project> getAllProjects(String sortName) throws Exception;
	public void createNewProject(Project project) throws Exception;
	public void deleteProject(String projectId) throws Exception;
	public void updateProject(Project project) throws Exception;
	public Project getProject(String projectId) throws Exception;
	
}
