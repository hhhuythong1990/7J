package com.sevenj.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.sevenj.dal.dao.HistoryDAO;
import com.sevenj.model.History;
import com.sevenj.model.ToolPlan;
import com.sevenj.service.ToolPlanService;
import com.sevenj.service.ToolPlanHistoryService;
import com.sevenj.service.impl.ToolPlanServiceImpl;
import com.sevenj.service.impl.ToolPlanHistoryServiceImpl;
import com.sevenj.web.common.BreadCrumbConst;
import com.sevenj.web.common.ModelConst;
import com.sevenj.web.common.SessionConst;
import com.sevenj.web.common.WebSession;
import com.sevenj.web.model.datatable.JsonResultList;
import com.sevenj.web.model.datatable.ResultEnum;

import dummiesmind.breadcrumb.springmvc.annotations.Link;

@Controller
@SessionAttributes({SessionConst.WEB_SESSION})
public class ToolPlanHistoryController {
	private ToolPlanService toolPlanService;
	private ToolPlanHistoryService historyService;
	private Logger logger = Logger.getLogger(ToolPlanHistoryController.class.getName());
	
	public ToolPlanHistoryController(){
		toolPlanService = new ToolPlanServiceImpl();
		historyService = new ToolPlanHistoryServiceImpl();
	}
	
	@RequestMapping(value="/getHistoriesByToolPlan.htm", method = RequestMethod.POST)
	public @ResponseBody JsonResultList<History> getHistories(@ModelAttribute(value=SessionConst.WEB_SESSION)WebSession webSession, int toolPlanId){
		JsonResultList<History> result = new JsonResultList<History>();
		try{
			result.setRecords(historyService.getHistories(toolPlanId));
			result.setResult(ResultEnum.OK);
		}catch(Exception ex){
			logger.error("Cannot get history of tool plan", ex);
			result.setResult(ResultEnum.ERROR);
			result.setMessage(ex.getMessage());
		}
		return result;
	}
	
	@Link(label=BreadCrumbConst.VIEW_TOOL_PLAN_HISTORY, family=BreadCrumbConst.MAIN_FLOW, parent=BreadCrumbConst.VIEW_PRICE_PLAN)
	@RequestMapping(value="/viewToolPlanHistory.htm", method=RequestMethod.GET)
	public ModelAndView viewHistory(@ModelAttribute(value=SessionConst.WEB_SESSION)WebSession webSession, int historyId){
		ToolPlan toolPlan = new ToolPlan();
		try{
			toolPlan = historyService.getHistory(historyId).parseHistory();
		}catch(Exception ex){
			logger.error("Cannot get history, caused by ", ex);
		}
		
		ModelAndView mv = new ModelAndView("viewToolPlanHistory", ModelConst.TOOL_PLAN, toolPlan);
		return mv;
	}
	
	@Link(label=BreadCrumbConst.COMPARE_TOOL_PLAN, family=BreadCrumbConst.MAIN_FLOW, parent=BreadCrumbConst.VIEW_PRICE_PLAN)
	@RequestMapping(value="/compareToolPlanHistory.htm", method=RequestMethod.GET)
	public ModelAndView compareHistory(@ModelAttribute(value=SessionConst.WEB_SESSION)WebSession webSession, int historyId, int toolPlanId){
		ToolPlan toolPlan = new ToolPlan();
		Map<String, Object> models = new HashMap<String, Object>();
		try{
			toolPlan = historyService.getHistory(historyId).parseHistory();
			models.put("oldToolPlan", toolPlan);
			models.put("currentToolPlan",toolPlanService.getToolPlan(toolPlanId));
		}catch(Exception ex){
			logger.error("Cannot compare history, caused by ", ex);
		}
		ModelAndView mv = new ModelAndView("compareToolPlanHistory", models);
		return mv;
	}
}
