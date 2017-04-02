package com.sevenj.web.controller;

import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.sevenj.model.PricePlan;
import com.sevenj.model.Project;
import com.sevenj.service.ProjectService;
import com.sevenj.service.impl.ProjectServiceImpl;
import com.sevenj.web.common.BreadCrumbConst;
import com.sevenj.web.common.SessionConst;
import com.sevenj.web.common.WebSession;
import com.sevenj.web.model.ProjectInfo;
import com.sevenj.web.model.datatable.JsonResultList;
import com.sevenj.web.model.datatable.JsonResult;
import com.sevenj.web.model.datatable.JsonResultBase;
import com.sevenj.web.model.datatable.ResultEnum;

import dummiesmind.breadcrumb.springmvc.annotations.Link;

@Controller
@SessionAttributes({SessionConst.WEB_SESSION})
public class ProjectController {
	
	private static final Logger logger = Logger.getLogger(ProjectController.class);
	private ProjectService projectService;
	
	public ProjectController(){
		this.projectService = new ProjectServiceImpl();
	}
	
	@Link(label=BreadCrumbConst.PROJECT_MANAGEMENT, family=BreadCrumbConst.MAIN_FLOW, parent="")
	@RequestMapping(value = "/projectList.htm", method = RequestMethod.GET)
	public String getProjectList() {
		return "projectList";
	}
	
	@RequestMapping(value = "/projectListForJTable.htm", method = RequestMethod.POST)
	public @ResponseBody JsonResultList<Project> getProjectList(int jtStartIndex, int jtPageSize, String jtSorting){
		logger.info("Loading data for project table");
		JsonResultList<Project> listResponse = new JsonResultList<Project>();
		try {
			List<Project> projects = projectService.getAllProjects(jtSorting);
			int totalRecordsCount = projects.size();
			int toIndex = jtStartIndex + jtPageSize -1;
			if (toIndex > totalRecordsCount-1){
				listResponse.setRecords(projects);
			}else{
				List<Project> subProjects = projects.subList(jtStartIndex, toIndex);
				listResponse.setRecords(subProjects);
			}
			
			listResponse.setResult(ResultEnum.OK);
			listResponse.setTotalRecordsCount(totalRecordsCount);
		} catch (Exception e) {
			logger.error("Caused by", e);
			listResponse.setResult(ResultEnum.ERROR);
			listResponse.setMessage(e.getMessage());
		}  
		return listResponse;
	}
	
	@RequestMapping(value="/createNewProject.htm", method = RequestMethod.GET)
	public String createNewProject(){
		return "newProject";
	}
	
	@RequestMapping(value="/createNewProject.htm", method = RequestMethod.POST)
	public @ResponseBody JsonResult<Project> createNewProject(@ModelAttribute Project project,
			@ModelAttribute(value=SessionConst.WEB_SESSION) WebSession webSession){
		logger.info("Submit data to create new project");
		JsonResult<Project> projectResponse = new JsonResult<Project>();
		try {
			project.setCreatedUser(webSession.getCurrentUser().getUsername());
			projectService.createNewProject(project);
			projectResponse.setResult(ResultEnum.OK);
			projectResponse.setRecord(project);
		} catch (Exception e) {
			logger.error("Caused by",e);
			projectResponse.setResult(ResultEnum.ERROR);
			projectResponse.setMessage(e.getMessage());
		}
		return projectResponse;
	}
	
	@RequestMapping(value="/deleteProject.htm", method = RequestMethod.POST)
	public @ResponseBody JsonResultBase deleteProject(@RequestParam String projectId){
		logger.info("Delete project " + projectId);
		JsonResultBase jsonResultBase = new JsonResultBase();
		try {
			projectService.deleteProject(projectId);
			jsonResultBase.setResult(ResultEnum.OK);
		} catch (Exception e) {
			logger.error("Caused by", e);
			jsonResultBase.setResult(ResultEnum.ERROR);
			jsonResultBase.setMessage(e.getMessage());
		}
		return jsonResultBase;
	}
	@RequestMapping(value="/updateProject.htm", method = RequestMethod.GET)
	public ModelAndView showEditProjectPage(String projectCode){
		Project project = null;
		try{
			project = projectService.getProject(projectCode);
		}catch(Exception ex){
			logger.error("Caused by", ex);
		}
		ModelAndView view = new ModelAndView("editProject","project", project);
		return view;
	}
	@RequestMapping(value="/updateProject.htm", method = RequestMethod.POST)
	public @ResponseBody JsonResult<Project> updateProject(@ModelAttribute Project project, HttpServletRequest request){
		logger.info("Update project");
		JsonResult<Project> jsonResult = new JsonResult<Project>();
		project.setProjectId(request.getParameter("originalProjectCode"));
		try{
			projectService.updateProject(project);
			jsonResult.setRecord(project);
			jsonResult.setResult(ResultEnum.OK);
		}catch(Exception e){
			logger.error("Caused by", e);
			jsonResult.setResult(ResultEnum.ERROR);
			jsonResult.setMessage(e.getMessage());
		}
		return jsonResult;
	}
	
	@Link(label=BreadCrumbConst.VIEW_PROJECT, family=BreadCrumbConst.MAIN_FLOW, parent=BreadCrumbConst.PROJECT_MANAGEMENT)
	@RequestMapping(value="/viewProject.htm", method = RequestMethod.GET)
	public String getPriceListByProject(String projectId, @ModelAttribute(value=SessionConst.WEB_SESSION) WebSession webSession){
		try {
			Project project = projectService.getProject(projectId);
			webSession.setCurrentProject(new ProjectInfo(project.getProjectId(), project.getProjectName(), project.getPeat()));
		} catch (Exception e) {
			logger.error("Caused by", e);
		}		
		return "viewProject";
	}
}
