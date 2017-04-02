package com.sevenj.web.controller;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.jfree.xml.generator.model.Comments;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.sevenj.model.Comment;
import com.sevenj.model.PricePlan;
import com.sevenj.model.ToolPlan;
import com.sevenj.service.PricePlanService;
import com.sevenj.service.ToolPlanService;
import com.sevenj.service.impl.PricePlanServiceImpl;
import com.sevenj.service.impl.ToolPlanServiceImpl;
import com.sevenj.web.common.BreadCrumbConst;
import com.sevenj.web.common.ModelConst;
import com.sevenj.web.common.SessionConst;
import com.sevenj.web.common.SevenJConst;
import com.sevenj.web.common.WebSession;
import com.sevenj.web.model.datatable.JsonResultBase;
import com.sevenj.web.model.datatable.JsonResultList;
import com.sevenj.web.model.datatable.ResultEnum;

import dummiesmind.breadcrumb.springmvc.annotations.Link;

@Controller
@SessionAttributes({SessionConst.WEB_SESSION})
public class ToolPlanCommentController {
	private ToolPlanService toolPlanService;
	private PricePlanService pricePlanService;
	
	private Logger logger = Logger.getLogger(ToolPlanController.class.getName());
	
	public ToolPlanCommentController(){
		toolPlanService = new ToolPlanServiceImpl();
		pricePlanService = new PricePlanServiceImpl();
	  }
	
	@RequestMapping(value="/commentToolPlans.htm", method = RequestMethod.POST)
	public @ResponseBody JsonResultBase commentToolPlans(String selectedToolPlanIds,String comment, @ModelAttribute(value=SessionConst.WEB_SESSION)WebSession webSession){
		  String[] ids = selectedToolPlanIds.split(SevenJConst.COMMA);
		  logger.info("Copy tool plans: " + selectedToolPlanIds);
		  JsonResultBase resultBase = new JsonResultBase();
		  try{
			  toolPlanService.commentToolPlans(ids, comment, webSession.getCurrentUser().getUsername());
			  resultBase.setResult(ResultEnum.OK);
		  }catch(Exception ex){
			  logger.error("Can't comment tool plans, caused by", ex);
			  resultBase.setResult(ResultEnum.ERROR);
			  resultBase.setMessage(ex.getMessage());
		  }
		  return resultBase;
	 }
	
	@RequestMapping(value="/getCommentsByToolPlan.htm", method = RequestMethod.POST)
	public @ResponseBody JsonResultList<Comment> getComments(@ModelAttribute(value=SessionConst.WEB_SESSION)WebSession webSession, int toolPlanId, String jtSorting){
		JsonResultList<Comment> comments = new JsonResultList<Comment>();
		try{
			comments.setRecords(toolPlanService.getComments(toolPlanId));
			comments.setResult(ResultEnum.OK);
		}catch(Exception ex){
			logger.error("Cannot get comments, caused by", ex);
			comments.setResult(ResultEnum.ERROR);
			comments.setMessage(ex.getMessage());
		}
		return comments;
	}
	
	@Link(label=BreadCrumbConst.COMMENT_TOOL_PLAN, family=BreadCrumbConst.MAIN_FLOW, parent=BreadCrumbConst.VIEW_PRICE_PLAN)
	@RequestMapping(value="/commentToolPlans.htm", method= RequestMethod.GET)
	 public ModelAndView getCommentToolPlansPage(@ModelAttribute(value=SessionConst.WEB_SESSION)WebSession webSession, String selectedToolPlanIds){
		  return getViewPageForSelectedToolPlans(webSession, selectedToolPlanIds, "commentToolPlans");
	 }
	 
	 private ModelAndView getViewPageForSelectedToolPlans(WebSession session, String selectedToolPlanIds, String viewName){
		  String projectId = session.getCurrentProject().getProjectId();
		  List<PricePlan> pricePlans = Collections.emptyList();
		  try{
			  pricePlans = pricePlanService.getPricePlans(projectId);
		  }catch(Exception ex){
			  logger.error("Can't get price plans of project " + projectId + ", caused by: ", ex);
		  }
		  ModelMap modelMap = new ModelMap();
		  modelMap.addAttribute(ModelConst.PRICE_PLAN_LIST,pricePlans);
		  modelMap.addAttribute(ModelConst.SELECTED_TOOL_PLANS, selectedToolPlanIds);
		  ModelAndView mv = new ModelAndView(viewName,modelMap);
		  return mv;
	  }
}
