package com.sevenj.dal.dao;

import java.util.List;

import com.sevenj.model.Project;
import com.sevenj.model.Project;

public interface ProjectDAO extends BaseDAO<Project>{
   public void insertProject(Project project) throws Exception;
   public void deleteProject(String projectId) throws Exception;
   public void updateProject(Project project) throws Exception;
   public List<Project> getAllProjects(String sortName) throws Exception;
   public Project getProject(String projectId) throws Exception;
}
