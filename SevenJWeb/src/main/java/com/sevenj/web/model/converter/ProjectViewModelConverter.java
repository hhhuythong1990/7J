//package com.sevenj.web.model.converter;
//
//import com.sevenj.model.Project;
//import com.sevenj.web.model.ProjectView;
//
//public class ProjectViewModelConverter implements ViewModelConverter<Project,ProjectView>{
//	@Override
//	public ProjectView convertToViewModel(Project businessObject) throws Exception {
//		ProjectView  projectView = new ProjectView();
//		projectView.setProjectId(businessObject.getProjectId());
//		projectView.setProjectName(businessObject.getProjectName());
//		projectView.setPeat(businessObject.getPeat());
//		projectView.setCreatedDate(businessObject.getCreatedDate());
//		projectView.setCreatedUser(businessObject.getCreatedUser());
//		return projectView;
//	}
//
//	@Override
//	public Project convertToBusinessModel(ProjectView viewModel)
//			throws Exception {
//		Project project = new Project();
//		project.setProjectName(viewModel.getProjectName());
//		project.setPeat(viewModel.getPeat());
//		return project;
//	}
//
//  
//}
