package com.sevenj.web.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.sevenj.model.Destination;
import com.sevenj.model.PricePlan;
import com.sevenj.model.ToolPlan;
import com.sevenj.service.LookupDataService;
import com.sevenj.service.PricePlanService;
import com.sevenj.service.ToolPlanService;
import com.sevenj.service.impl.PricePlanServiceImpl;
import com.sevenj.service.impl.ToolPlanServiceImpl;
import com.sevenj.web.common.BreadCrumbConst;
import com.sevenj.web.common.ModelConst;
import com.sevenj.web.common.SessionConst;
import com.sevenj.web.common.WebSession;
import com.sevenj.web.data.LookupDataGeneratorFactory;
import com.sevenj.web.model.PricePlanInfo;
import com.sevenj.web.model.datatable.JsonResult;
import com.sevenj.web.model.datatable.JsonResultBase;
import com.sevenj.web.model.datatable.JsonResultList;
import com.sevenj.web.model.datatable.JsonResultListBase;
import com.sevenj.web.model.datatable.ResultEnum;
import com.sevenj.web.validator.PricePlanCreateValidator;
import com.sevenj.web.validator.PricePlanEditValidator;
import com.sevenj.web.validator.ToolPlanValidator;

import dummiesmind.breadcrumb.springmvc.annotations.Link;

@Controller
@SessionAttributes({ SessionConst.WEB_SESSION })
public class PricePlanController {
	private Logger logger = Logger.getLogger(PricePlanController.class
			.getName());
	private PricePlanService pricePlanService;
	private ToolPlanService toolPlanService;
	private LookupDataService lookupData;

	public PricePlanController() {
		pricePlanService = new PricePlanServiceImpl();
		toolPlanService = new ToolPlanServiceImpl();
	}

	@RequestMapping(value = "/getPricePlanListForJTable.htm", method = RequestMethod.POST)
	public @ResponseBody JsonResultList<PricePlan> getPricePlanListForJTable(
			int jtStartIndex,
			int jtPageSize,
			String jtSorting,
			@ModelAttribute(value = SessionConst.WEB_SESSION) WebSession webSession) {
		String projectId = webSession.getCurrentProject().getProjectId();
		int stt = webSession.getCurrentPricePlanStatusTab();
		JsonResultList<PricePlan> jsonResultList = new JsonResultList<PricePlan>();
		try {
			List<PricePlan> pricePlans = Collections.emptyList();
			// Get all price plan
			if (stt == 1) {
				pricePlans = pricePlanService.getPricePlansByProject(projectId,
						jtStartIndex, jtPageSize, jtSorting);
				jsonResultList.setTotalRecordsCount(pricePlanService
						.getTotalPricePlanCount(projectId));
			} else if (stt == 0) { // get pending price plan
				pricePlans = pricePlanService.getPendingPricePlansByProject(
						projectId, jtStartIndex, jtPageSize, jtSorting);
				jsonResultList.setTotalRecordsCount(pricePlanService
						.getCountForPendingPricePlans(projectId));
			}
			jsonResultList.setRecords(pricePlans);
			jsonResultList.setResult(ResultEnum.OK);
		} catch (Exception e) {
			logger.error("Caused by", e);
			jsonResultList.setResult(ResultEnum.ERROR);
			jsonResultList.setMessage(e.getMessage());
		}
		return jsonResultList;
	}

	@Link(label = BreadCrumbConst.CREATE_PRICE_PLAN, family = BreadCrumbConst.MAIN_FLOW, parent = BreadCrumbConst.VIEW_PROJECT)
	@RequestMapping(value = "/createNewPricePlan.htm", method = RequestMethod.GET)
	public ModelAndView createNewPricePlan() {
		Map<String, Object> models = initializeModels();
		ModelAndView mv = new ModelAndView("createNewPricePlan", models);
		return mv;
	}

	private Map<String, Object> initializeModels() {
		Map<String, Object> models = new HashMap<String, Object>();
		addNewModel(models, ModelConst.PRICE_PLAN_CODE);
		addNewModel(models, ModelConst.PRICE_PLAN_NAME);
		return models;
	}

	private void addNewModel(Map<String, Object> models, String name) {
		LookupDataGeneratorFactory generatorFactory = LookupDataGeneratorFactory
				.getInstance();
		models.put(name, generatorFactory.getGenerator(name).generate());
	}

	@ModelAttribute("pricePlan")
	public PricePlanInfo createPricePlanModel() {
		return new PricePlanInfo();
	}

//	@RequestMapping(value = "/createNewPricePlan.htm", method = RequestMethod.POST)
//	public ModelAndView createNewPricePlan( @ModelAttribute("pricePlan") @Validated PricePlanInfo pricePlan, BindingResult bindingResult,
//			@ModelAttribute(value = SessionConst.WEB_SESSION) WebSession webSession, HttpServletRequest request) {
//		JsonResultBase resultBase = new JsonResultBase();
//		ModelAndView mv = null;
//		try {
//			String currentProjectId = webSession.getCurrentProject().getProjectId();
//			PricePlanCreateValidator validator = new PricePlanCreateValidator();
//			PricePlan newPricePlan = new PricePlan();
//			if (!StringUtils.isEmpty(pricePlan.getPricePlanCode())) {
//				newPricePlan.setPricePlanCode(pricePlan.getPricePlanCode());
//			}
//			if (!StringUtils.isEmpty(pricePlan.getPricePlanName())) {
//				newPricePlan.setPricePlanName(pricePlan.getPricePlanName());
//			}
//
//			validator.validate(newPricePlan, bindingResult);
//			if (!bindingResult.hasErrors()) {
//				newPricePlan.setCreationUser(webSession.getCurrentUser().getUsername());
//				newPricePlan.setCreationDate(new Date());
//				pricePlanService.createNewPricePlan(currentProjectId, newPricePlan);
//				mv = new ModelAndView("redirect:viewProject.htm?projectId=" + currentProjectId);
//			} else {
//				mv = createNewPricePlan();
//			}
//		} catch (Exception ex) {
//			logger.error("Can't create price plan, caused by", ex);
//			resultBase.setResult(ResultEnum.ERROR);
//			resultBase.setMessage(ex.getMessage());
//		}
//		return mv;
//	}
	
	
	@RequestMapping(value = "/createNewPricePlan.htm", method = RequestMethod.POST)
	public ModelAndView createNewPricePlan( @ModelAttribute("pricePlan") @Validated PricePlan pricePlan, BindingResult bindingResult,
			@ModelAttribute(value = SessionConst.WEB_SESSION) WebSession webSession, HttpServletRequest request) {
		JsonResultBase resultBase = new JsonResultBase();
		ModelAndView mv = null;
		try {
			String currentProjectId = webSession.getCurrentProject().getProjectId();
			PricePlanService pricePlanService= new PricePlanServiceImpl();
			PricePlanCreateValidator validator = new PricePlanCreateValidator();
			
			PricePlan newPricePlan = new PricePlan();
			if (!StringUtils.isEmpty(pricePlan.getPricePlanCode())) {
				newPricePlan.setPricePlanCode(pricePlan.getPricePlanCode());
			}
			if (!StringUtils.isEmpty(pricePlan.getPricePlanName())) {
				newPricePlan.setPricePlanName(pricePlan.getPricePlanName());
			}

			validator.validate(newPricePlan, bindingResult);
			if (!bindingResult.hasErrors()) {
				newPricePlan.setCreationUser(webSession.getCurrentUser().getUsername());
				newPricePlan.setCreationDate(new Date());
				pricePlanService.createNewPricePlan(currentProjectId, newPricePlan);
				mv = new ModelAndView("redirect:viewProject.htm?projectId=" + currentProjectId);
			} else {
				mv = createNewPricePlan();
			}
		} catch (Exception ex) {
			logger.error("Can't create price plan, caused by", ex);
			resultBase.setResult(ResultEnum.ERROR);
			resultBase.setMessage(ex.getMessage());
		}
		return mv;
	}

	@Link(label = BreadCrumbConst.EDIT_PRICE_PLAN, family = BreadCrumbConst.MAIN_FLOW, parent = BreadCrumbConst.VIEW_PROJECT)
	@RequestMapping(value = "/editPricePlan.htm", method = RequestMethod.GET)
	public ModelAndView editPricePlan(
			String pricePlanCode,
			@ModelAttribute(value = SessionConst.WEB_SESSION) WebSession webSession) {
		PricePlan pricePlan = null;
		try {
			pricePlan = pricePlanService.getPricePlan(pricePlanCode);
		} catch (Exception ex) {
			logger.error(
					"Cannot get lookup data and get tool plan for edit, caused by",
					ex);
		}
		ModelAndView modelAndView = initializeEditPricePlan(pricePlan);
		return modelAndView;
	}

	private ModelAndView initializeEditPricePlan(PricePlan pricePlan) {
		Map<String, Object> models = new HashMap<String, Object>();
		try {
			// models = initializeModels();
			models.put(ModelConst.PRICE_PLAN, pricePlan);
		} catch (Exception ex) {
			logger.error(
					"Cannot get lookup data and get price plan for edit, caused by",
					ex);
		}
		ModelAndView modelAndView = new ModelAndView("editPricePlan", models);
		return modelAndView;
	}

	@RequestMapping(value = "/editPricePlan.htm", method = RequestMethod.POST)
	public ModelAndView editPricePlan(
			@ModelAttribute("pricePlan") @Validated PricePlan pricePlan,
			BindingResult bindingResult,
			@ModelAttribute(value = SessionConst.WEB_SESSION) WebSession webSession,
			HttpServletRequest request) {
		ModelAndView mv = null;
		String pricePlanCode = request.getParameter("originalPricePlanCode");
		String currentProjectId = webSession.getCurrentProject().getProjectId();
		try {
			PricePlan oldPricePlan = pricePlanService.getPricePlan(pricePlanCode);
//			PricePlanEditValidator validator = new PricePlanEditValidator();
//			validator.validate(pricePlan, bindingResult);
			if (!bindingResult.hasErrors()) {
				oldPricePlan.setPricePlanName(pricePlan.getPricePlanName());
				oldPricePlan.setCreationDate(new Date());
				oldPricePlan.setCreationUser(webSession.getCurrentUser()
						.getUsername());
				pricePlanService.updatePricePlan(oldPricePlan);
				mv = new ModelAndView("redirect:viewProject.htm?projectId="
						+ currentProjectId);
			} else {
				mv = initializeEditPricePlan(pricePlan);
			}
		} catch (Exception ex) {
			logger.error("Cannot update price plan, caused by ", ex);
		}
		return mv;
	}

	@RequestMapping(value = "/updatePricePlan.htm", method = RequestMethod.POST)
	public @ResponseBody JsonResult<PricePlan> updatePricePlan(
			@ModelAttribute PricePlan pricePlan,
			@ModelAttribute(value = SessionConst.WEB_SESSION) WebSession webSession,
			HttpServletRequest request) {
		JsonResult<PricePlan> jsonResult = new JsonResult<PricePlan>();
		String pricePlanCode = request.getParameter("originalPricePlanCode");
		try {
			PricePlan oldPricePlan = pricePlanService
					.getPricePlan(pricePlanCode);
			pricePlan.setPricePlanCode(pricePlanCode);
			pricePlan.setCBSSStatus(oldPricePlan.getCBSSStatus());
			pricePlan.setCreationDate(oldPricePlan.getCreationDate());
			pricePlan.setCreationUser(oldPricePlan.getCreationUser());
			pricePlan.setPricePlanStatus(oldPricePlan.getPricePlanStatus());
			pricePlan.setProject(oldPricePlan.getProject());
			pricePlanService.updatePricePlan(pricePlan);
			jsonResult.setResult(ResultEnum.OK);
			jsonResult.setRecord(pricePlan);
		} catch (Exception ex) {
			jsonResult.setResult(ResultEnum.ERROR);
			jsonResult.setMessage(ex.getMessage());
			logger.error("Caused by", ex);
		}
		return jsonResult;
	}

	@RequestMapping(value = "/deletePricePlan.htm", method = RequestMethod.POST)
	public @ResponseBody JsonResultBase deletePricePlan(String pricePlanCode) {
		JsonResultBase jsonResultBase = new JsonResultBase();
		try {
			pricePlanService.deletePricePlan(pricePlanCode);
			jsonResultBase.setResult(ResultEnum.OK);
		} catch (Exception ex) {
			logger.error("Caused by", ex);
			jsonResultBase.setResult(ResultEnum.ERROR);
			jsonResultBase.setMessage(ex.getMessage());
		}
		return jsonResultBase;
	}

	@RequestMapping(value = "/viewPricePlanNew.htm", method = RequestMethod.GET)
	public String getViewPricePlanNewPage(
			@ModelAttribute(value = SessionConst.WEB_SESSION) WebSession webSession,
			String pricePlanCode) {
		try {
			PricePlan pricePlan = pricePlanService.getPricePlan(pricePlanCode);
			PricePlanInfo pricePlanInfo = new PricePlanInfo();
			pricePlanInfo.setPricePlanCode(pricePlan.getPricePlanCode());
			pricePlanInfo.setPricePlanName(pricePlan.getPricePlanName());
			pricePlanInfo.setStatus(pricePlan.getPricePlanStatus());
			webSession.setCurrentPricePlan(pricePlanInfo);
		} catch (Exception ex) {
			logger.error("Cannot get price plan " + pricePlanCode, ex);
		}
		return "viewPricePlanNew";
	}

	@Link(label = BreadCrumbConst.VIEW_PRICE_PLAN, family = BreadCrumbConst.MAIN_FLOW, parent = BreadCrumbConst.VIEW_PROJECT)
	@RequestMapping(value = "/viewPricePlan.htm", method = RequestMethod.GET)
	public String viewPricePlan(
			String pricePlanCode,
			String selectedTab,
			@ModelAttribute(value = SessionConst.WEB_SESSION) WebSession webSession) {
		try {
			PricePlan pricePlan = pricePlanService.getPricePlan(pricePlanCode);
			PricePlanInfo pricePlanInfo = new PricePlanInfo();
			pricePlanInfo.setPricePlanCode(pricePlan.getPricePlanCode());
			pricePlanInfo.setPricePlanName(pricePlan.getPricePlanName());
			pricePlanInfo.setStatus(pricePlan.getPricePlanStatus());
			webSession.setCurrentPricePlan(pricePlanInfo);
			if (!StringUtils.isEmpty(selectedTab)) {
				webSession.setCurrentToolPlanStatusTab(Integer
						.valueOf(selectedTab));
			}
		} catch (Exception ex) {
			logger.error("Caused by", ex);
		}
		return "viewPricePlan";
	}

	// Get Table price plan Content
	@RequestMapping(value = "/getPricePlanTabContent.htm", method = RequestMethod.GET)
	public String getTabContentForPricePlan(
			String projectId,
			@ModelAttribute(value = SessionConst.WEB_SESSION) WebSession webSession,
			int status) {
		try {
			webSession.setCurrentPricePlanStatusTab(status);
			;
		} catch (Exception e) {
			logger.error("Caused by", e);
		}
		return "pricePlanTabContent";
	}

	@Link(label = BreadCrumbConst.IMPORT_PRICE_PLAN, family = BreadCrumbConst.MAIN_FLOW, parent = BreadCrumbConst.VIEW_PRICE_PLAN)
	@RequestMapping(value = "/importPricePlan.htm", method = RequestMethod.GET)
	public ModelAndView getImportPricePlanPage(
			@ModelAttribute WebSession webSession) {
		List<PricePlan> pricePlans = Collections.emptyList();
		try {
			// pricePlans =
			// pricePlanService.getPricePlans(webSession.getCurrentProject().getProjectId());
			pricePlans = pricePlanService.getPricePlans();
		} catch (Exception ex) {
			logger.error("Cannot get import data page", ex);
		}

		ModelAndView mv = new ModelAndView("importPricePlan", "pricePlans",
				pricePlans);
		return mv;
	}

	@RequestMapping(value = "/importPricePlan.htm", method = RequestMethod.POST)
	public @ResponseBody JsonResultBase importPricePlan(
			String sourcePricePlanCode, @ModelAttribute WebSession webSession) {
		JsonResultBase resultBase = new JsonResultBase();
		try {
			List<ToolPlan> toolPlans = toolPlanService
					.getToolPlansForImport(sourcePricePlanCode);
			String[] ignoreProperties = new String[] { "ID", "pricePlan",
					"tableEntryCreationDate", "comments", "histories" };
			List<ToolPlan> copiedToolPlans = new ArrayList<ToolPlan>();
			for (ToolPlan toolPlan : toolPlans) {
				ToolPlan newToolPlan = new ToolPlan();
				BeanUtils.copyProperties(toolPlan, newToolPlan,
						ignoreProperties);
				copiedToolPlans.add(newToolPlan);
			}
			pricePlanService.importPricePlan(webSession.getCurrentPricePlan()
					.getPricePlanCode(), copiedToolPlans, webSession
					.getCurrentUser().getUsername());
			resultBase.setResult(ResultEnum.OK);
		} catch (Exception ex) {
			logger.error("Cannot import price plan", ex);
			resultBase.setResult(ResultEnum.ERROR);
		}
		return resultBase;
	}

	@RequestMapping(value = "/getNumberOfEligibleToolPlansForImport.htm", method = RequestMethod.GET)
	public @ResponseBody JsonResultListBase getNumberOfEligibleToolPlansForImport(
			String pricePlanCode) {
		JsonResultListBase resultListBase = new JsonResultListBase();
		try {
			resultListBase.setTotalRecordsCount(toolPlanService
					.getNumberOfToolPlansForImport(pricePlanCode));
			resultListBase.setResult(ResultEnum.OK);
		} catch (Exception ex) {
			logger.error(
					"Cannot get number of eligible tool plans of  price plan "
							+ pricePlanCode, ex);
			resultListBase.setResult(ResultEnum.ERROR);
			resultListBase.setMessage(ex.getMessage());
		}
		return resultListBase;
	}

	@RequestMapping(value = "/forwardToViewPricePlan.htm", method = RequestMethod.GET)
	@Link(label = BreadCrumbConst.VIEW_PRICE_PLAN, family = BreadCrumbConst.MAIN_FLOW, parent = BreadCrumbConst.VIEW_PROJECT)
	public ModelAndView forward(
			String forward,
			@ModelAttribute(value = SessionConst.WEB_SESSION) WebSession webSession) {
		ModelAndView mav = new ModelAndView("viewProject", "forward",
				"viewPricePlan");
		return mav;
	}
}
