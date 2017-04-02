package com.sevenj.web.controller;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mysql.jdbc.StringUtils;
import com.sevenj.dal.dao.BaseDAO;
import com.sevenj.dal.dao.impl.BaseDAOImpl;
import com.sevenj.model.AdditionalPricePlan;
import com.sevenj.model.Lookup;
import com.sevenj.model.SearchAdditionalPricePlanCriteria;
import com.sevenj.service.AdditionalPricePlanService;
import com.sevenj.service.IReportService;
import com.sevenj.service.LookupService;
import com.sevenj.service.ReportServiceFactory;
import com.sevenj.service.impl.AdditionalPricePlanServiceImpl;
import com.sevenj.service.impl.LookupServiceImpl;
import com.sevenj.service.util.ReportConst;
import com.sevenj.web.common.BreadCrumbConst;
import com.sevenj.web.common.ModelConst;
import com.sevenj.web.common.SessionConst;
import com.sevenj.web.common.ValueConst;
import com.sevenj.web.common.WebSession;
import com.sevenj.web.model.AdditionalPricePlanView;
import com.sevenj.web.model.AdditionalPricePlanViewModel;
import com.sevenj.web.model.converter.ViewModelConverter;
import com.sevenj.web.model.converter.ViewModelConverterFactory;
import com.sevenj.web.model.datatable.JsonResult;
import com.sevenj.web.model.datatable.JsonResultList;
import com.sevenj.web.model.datatable.ResultEnum;
import com.sevenj.web.util.Utilities;
import com.sevenj.web.validator.AdditionalPricePlanValidator;

import dummiesmind.breadcrumb.springmvc.annotations.Link;

@Controller
@SessionAttributes({SessionConst.WEB_SESSION})
public class AdditionalPricePlanController {
    private AdditionalPricePlanService additionalPPService;
    private Logger logger = Logger.getLogger(AdditionalPricePlanController.class.getName());
    Utilities util = new Utilities();
    ValueConst valueConst = new ValueConst();
    
    public AdditionalPricePlanController(){
    	additionalPPService = new AdditionalPricePlanServiceImpl();
    }
    
    @Link(label=BreadCrumbConst.ADDITIONAL_PRICE_PLAN_MANAGEMENT, family=BreadCrumbConst.ADDITIONAL_PRICE_PLAN_FLOW, parent="")
	@RequestMapping(value="/getAdditionalPricePlanList.htm", method = RequestMethod.GET)
	public String getAdditionalPricePlanPage(){
		return "additionalPricePlanList";
	}
	
    
	@RequestMapping(value="/getAdditionalPricePlanListForJTable.htm", method=RequestMethod.POST)
	public @ResponseBody JsonResultList<AdditionalPricePlan> getPricePlanListForJTable(int jtStartIndex, int jtPageSize, String jtSorting,
			@ModelAttribute(value=SessionConst.WEB_SESSION) WebSession webSession){
		JsonResultList<AdditionalPricePlan> resultList = new JsonResultList<AdditionalPricePlan>();
		try{
//			List<AdditionalPricePlan> list = additionalPPService.getAdditionalPricePlanList();
			List<AdditionalPricePlan> list = additionalPPService.getAdditionalPricePlanList(jtStartIndex, jtPageSize, jtSorting);
			resultList.setRecords(list);
			resultList.setResult(ResultEnum.OK);
			resultList.setTotalRecordsCount(additionalPPService.getTotalAdditionalPricePlan());
		}catch(Exception ex){
			resultList.setMessage(ex.getMessage());
			resultList.setResult(ResultEnum.ERROR);
		}
		return resultList;
	}
	
	//get data fill GUI and redirect to createAdditonalPricePlan page
	@Link(label = BreadCrumbConst.ADD_NEW_ADD_PRICE_PLAN, family=BreadCrumbConst.ADDITIONAL_PRICE_PLAN_FLOW, parent = BreadCrumbConst.ADDITIONAL_PRICE_PLAN_MANAGEMENT)
	@RequestMapping(value="/CreateADPricePlan.htm", method = RequestMethod.GET)
	public ModelAndView getCreateAdditionalPricePlanPage(){
		LookupService lookupService = new LookupServiceImpl();
		Map<String, Object> models = new HashMap<String, Object>();
		try{
			models.put(ModelConst.PLANTYPES, lookupService.getDataList("PlanType"));
			models.put(ModelConst.PRIORITY_CODES, lookupService.getDataList("PrioprityCode"));
			models.put(ModelConst.ACCOUNT_BASE_LISTS, lookupService.getDataList("AccountBased"));
			models.put(ModelConst.IS_TOOL_PRICINGPLANS, lookupService.getDataList("IsToolPricingPlan"));
			models.put(ModelConst.NO_OVERSEA_DEST, lookupService.getDataList("NoOverseaDest"));
			models.put(ModelConst.GENERALS, lookupService.getDataList("General"));
			models.put(ModelConst.T29_OVERSEA_DESTS, lookupService.getDataList("T29OverseaDest"));
			models.put(ModelConst.BLOCK_OF_TIMES, lookupService.getDataList("BlockOfTime"));
			models.put(ModelConst.DOLL_AR_CAP, lookupService.getDataList("DollarCap"));
			models.put(ModelConst.BILL_MINIUMS, lookupService.getDataList("BillMinium"));
			models.put(ModelConst.QUANTITYS, lookupService.getDataList("Quantity"));
			models.put(ModelConst.AIR_TIMES, lookupService.getDataList("airTime"));
			models.put(ModelConst.INCREMENTS_60, lookupService.getDataList("Increments60"));
			models.put(ModelConst.INCREMENTS_60_OR_30, lookupService.getDataList("Increments60or30"));
			models.put(ModelConst.INCREMENTS_30, lookupService.getDataList("Increments30"));
			models.put(ModelConst.INCREMENTS_10, lookupService.getDataList("Increments10"));
			models.put(ModelConst.INCREMENTS_1, lookupService.getDataList("Increments1"));
			
		}catch(Exception ex){
			logger.error("Cannot get create additional price plan", ex);
		}
		
		ModelAndView mv = new ModelAndView("createAdditionalPricePlan",models);
		return mv;
	}

	@RequestMapping(value="/createADPricePlan.htm", method = RequestMethod.POST)
	public ModelAndView createtAdditionalPricePlan(@ModelAttribute("additionalPricePlanView") @Validated AdditionalPricePlanView additionalPricePlanView, BindingResult bindingResult, @ModelAttribute(value=SessionConst.WEB_SESSION) WebSession webSession, RedirectAttributes redirectAttributes){
		LookupService lookupService = new LookupServiceImpl();
		ModelAndView mv = null;
		try{
			AdditionalPricePlanValidator validator = new AdditionalPricePlanValidator();
			validator.validate(additionalPricePlanView, bindingResult);
			if (bindingResult.hasErrors()){
				redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.additionalPricePlanView",bindingResult);
				redirectAttributes.addFlashAttribute("additionalPricePlanView", additionalPricePlanView);
				mv = new ModelAndView("redirect:CreateADPricePlan.htm");
			}else{
				insertAdmisellaneousByPrioprityCode(additionalPricePlanView, lookupService, additionalPricePlanView.getPriorityCodeNew());
				insertAdmisellaneousByBlockOfTime(additionalPricePlanView, lookupService, additionalPricePlanView.getBlockOfTimeNew());
				AdditionalPricePlan newAddPricePlan = new AdditionalPricePlan();
				additionalPricePlanView.setCreationUser(webSession.getCurrentUser().getUsername());
				BeanUtils.copyProperties(additionalPricePlanView,newAddPricePlan);
				additionalPPService.createAdditionalPricePlan(newAddPricePlan);
				mv = new ModelAndView("redirect:getAdditionalPricePlanList.htm");
			}
			
		}catch(Exception e){
			logger.error("Can not create Additional Price Plan", e);
			mv = getCreateAdditionalPricePlanPage();
		}
	    return mv;
	}

	@SuppressWarnings("static-access")
	private void insertAdmisellaneousByBlockOfTime( AdditionalPricePlan additionalPricePlan, LookupService lookupService, String blockOfTimeInput) throws Exception {
		if(!StringUtils.isNullOrEmpty(blockOfTimeInput)){
			blockOfTimeInput = blockOfTimeInput.trim().toLowerCase();
			Lookup lookup = lookupService.getObjLookupByDescription(blockOfTimeInput);
			if(lookup == null){
				lookup = new Lookup();
				lookup.setDescription(blockOfTimeInput);
				lookup.setInheritFromGeneral(valueConst.INHERIT_FROM_GENERAL);
				lookup.setType(valueConst.TYPE_BLOCK_OF_TIME);
				lookup.setTypeName(valueConst.TYPENAME_BLOCK_OF_TIME);
				lookupService.addNewLookup(lookup);
			}
			additionalPricePlan.setBlockOfTime(lookup.getId());
		}
	}

	@SuppressWarnings("static-access")
	private void insertAdmisellaneousByPrioprityCode(AdditionalPricePlan additionalPricePlan, LookupService lookupService, String prioprityCodeInput)
			throws Exception {
		if(!StringUtils.isNullOrEmpty(prioprityCodeInput)){
			prioprityCodeInput = prioprityCodeInput.trim().toUpperCase();
			Lookup lookup = lookupService.getObjLookupByDescription(prioprityCodeInput);
			if(lookup == null){ // add lookup hash code
				lookup = new Lookup();
				lookup.setDescription(prioprityCodeInput);
				lookup.setInheritFromGeneral(valueConst.NONE_INHERIT_FROM_GENERAL);
				lookup.setType(valueConst.TYPE_PRIOPRITY_CODE);
				lookup.setTypeName(valueConst.TYPENAME_PRIOPRITY_CODE);
				lookupService.addNewLookup(lookup);
			}
			additionalPricePlan.setPriorityCode(lookup.getId());
		}
	}
	
	//view additional price plan for edit
	@Link(label=BreadCrumbConst.VIEW_ADD_PRICE_PLAN, family=BreadCrumbConst.ADDITIONAL_PRICE_PLAN_FLOW, parent=BreadCrumbConst.ADDITIONAL_PRICE_PLAN_MANAGEMENT)
	@RequestMapping(value="/viewAdditionalPricePlan.htm", method = RequestMethod.GET)
	public  ModelAndView  viewAdditionalPricePlan(String pricePlanCode, String selectedTab, @ModelAttribute(value=SessionConst.WEB_SESSION) WebSession webSession){
		 LookupService lookupService = new LookupServiceImpl();
		 ModelAndView  modelAndView = new ModelAndView("viewAdditionalPricePlan");
		 try {
			 Map<Integer,String> models=lookupService.getDataMap();
			 modelAndView.addObject("viewAdditionalPricePlan", additionalPPService.searchAdditonalPricePlan(pricePlanCode));
			 modelAndView.addObject("dataList", models);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return modelAndView;
	
	}
	
	@Link(label=BreadCrumbConst.EDIT_ADD_PRICE_PLAN, family=BreadCrumbConst.ADDITIONAL_PRICE_PLAN_FLOW, parent=BreadCrumbConst.ADDITIONAL_PRICE_PLAN_MANAGEMENT)
	@RequestMapping(value="/editAdditionalPricePlan.htm", method = RequestMethod.GET)
	public ModelAndView getEditAdditionalPricePlanPage(String pricePlanCode, String selectedTab, @ModelAttribute(value=SessionConst.WEB_SESSION) WebSession webSession)
	{
		LookupService lookupService = new LookupServiceImpl();
		Map<String, Object> models = new HashMap<String, Object>();
		try{
			models.put(ModelConst.PLANTYPES, lookupService.getDataList("PlanType"));
			models.put(ModelConst.PRIORITY_CODES, lookupService.getDataList("PrioprityCode"));
			models.put(ModelConst.ACCOUNT_BASE_LISTS, lookupService.getDataList("AccountBased"));
			models.put(ModelConst.IS_TOOL_PRICINGPLANS, lookupService.getDataList("IsToolPricingPlan"));
			models.put(ModelConst.NO_OVERSEA_DEST, lookupService.getDataList("NoOverseaDest"));
			models.put(ModelConst.GENERALS, lookupService.getDataList("General"));
			models.put(ModelConst.T29_OVERSEA_DESTS, lookupService.getDataList("T29OverseaDest"));
			models.put(ModelConst.BLOCK_OF_TIMES, lookupService.getDataList("BlockOfTime"));
			models.put(ModelConst.DOLL_AR_CAP, lookupService.getDataList("DollarCap"));
			models.put(ModelConst.BILL_MINIUMS, lookupService.getDataList("BillMinium"));
			models.put(ModelConst.QUANTITYS, lookupService.getDataList("Quantity"));
			models.put(ModelConst.AIR_TIMES, lookupService.getDataList("airTime"));
			models.put(ModelConst.INCREMENTS_60, lookupService.getDataList("Increments60"));
			models.put(ModelConst.INCREMENTS_60_OR_30, lookupService.getDataList("Increments60or30"));
			models.put(ModelConst.INCREMENTS_30, lookupService.getDataList("Increments30"));
			models.put(ModelConst.INCREMENTS_10, lookupService.getDataList("Increments10"));
			models.put(ModelConst.INCREMENTS_1, lookupService.getDataList("Increments1"));
			AdditionalPricePlan obj=additionalPPService.searchAdditonalPricePlan(pricePlanCode);
			models.put("entity",obj);
		}catch(Exception ex){
			logger.error("Cannot get editadditional price plan", ex);
		}
		
		ModelAndView mv = new ModelAndView("editAdditionalPricePlan",models);
		return mv;
	}
	
	@SuppressWarnings("static-access")
	@RequestMapping(value="/editAdditionalPricePlan.htm", method = RequestMethod.POST)
	public @ResponseBody JsonResult<AdditionalPricePlan> updatePricePlan(@ModelAttribute AdditionalPricePlan additionalPricePlan, @ModelAttribute(value=SessionConst.WEB_SESSION) WebSession webSession, HttpServletRequest request){
		JsonResult<AdditionalPricePlan> jsonResult = new JsonResult<AdditionalPricePlan>();
		String pricePlanCode = request.getParameter("pricePlanCode");
		LookupService lookupService = new LookupServiceImpl();
		try{
			AdditionalPricePlan oldAdditionPricePlan =additionalPPService.searchAdditonalPricePlan(pricePlanCode);
			additionalPricePlan.setPricePlanCode(pricePlanCode);
			insertAdmisellaneousByPrioprityCode(additionalPricePlan, lookupService, request.getParameter(valueConst.PRIOPRITY_CODE_INPUT));
			insertAdmisellaneousByBlockOfTime(additionalPricePlan, lookupService, request.getParameter(valueConst.BLOCK_OF_TIME_INPUT));
			additionalPricePlan.setCreationUser(oldAdditionPricePlan.getCreationUser());
			additionalPricePlan.setCreationDate(oldAdditionPricePlan.getCreationDate());
			additionalPPService.updateAdditonalPricePlan(additionalPricePlan);
			jsonResult.setResult(ResultEnum.OK);
			jsonResult.setRecord(additionalPricePlan);
		}catch(Exception ex){
			jsonResult.setResult(ResultEnum.ERROR);
			jsonResult.setMessage(ex.getMessage());
			logger.error("Caused by", ex);
		}
		return jsonResult;
	}

	@Link(label=BreadCrumbConst.GENERATE_ADD_PRICE_PLAN, family=BreadCrumbConst.ADDITIONAL_PRICE_PLAN_FLOW, parent=BreadCrumbConst.ADDITIONAL_PRICE_PLAN_MANAGEMENT)
	@RequestMapping(value="/generateAdditionalPricePlanList.htm", method = RequestMethod.GET)
	public  String generateAdditionalPricePlanPage(){
	
		 return "generateAdditionalPricePlanList";
	
	}
	
	
	@RequestMapping(value="/generateAdditionalPricePlanList.htm", method=RequestMethod.POST)
	public @ResponseBody JsonResultList<AdditionalPricePlan> getGeneratePricePlanListForJTable(int jtStartIndex, int jtPageSize, String jtSorting,
			@ModelAttribute(value=SessionConst.WEB_SESSION) WebSession webSession){
		JsonResultList<AdditionalPricePlan> resultList = new JsonResultList<AdditionalPricePlan>();
		
		try {
			List<AdditionalPricePlan> list = additionalPPService.getAdditionalPricePlanList(jtSorting);
					
			int totalRecordsCount = list.size();
			int toIndex = jtStartIndex + jtPageSize -1;
			if (toIndex > totalRecordsCount-1){
				resultList.setRecords(list);
			}else{
				List<AdditionalPricePlan> subProjects = list.subList(jtStartIndex, toIndex);
				resultList.setRecords(subProjects);
			}
			resultList.setResult(ResultEnum.OK);
			resultList.setTotalRecordsCount(totalRecordsCount);
			
		} catch (Exception e) {
			logger.error("Caused by: ", e);
			resultList.setResult(ResultEnum.ERROR);
			resultList.setMessage(e.getMessage());
		}  
		return resultList;
	}
	
	@Link(label=BreadCrumbConst.SEARCH_ADD_PRICE_PLAN, family=BreadCrumbConst.ADDITIONAL_PRICE_PLAN_FLOW, parent=BreadCrumbConst.ADDITIONAL_PRICE_PLAN_MANAGEMENT)
	@RequestMapping(value="/searchADPricePlan.htm", method = RequestMethod.GET)
	public ModelAndView searchAdditionalPricePlanPage(@ModelAttribute SearchAdditionalPricePlanCriteria criteria, Model model, @ModelAttribute(value=SessionConst.WEB_SESSION) WebSession webSession){
		LookupService lookupService = new LookupServiceImpl();
		Map<String, Object> models = new HashMap<String, Object>();
		try{
			models.put(ModelConst.GENERALS, lookupService.getDataList("General"));
			models.put(ModelConst.PLANTYPES, lookupService.getDataList("PlanType"));
			models.put(ModelConst.PRIORITY_CODES, lookupService.getDataList("PrioprityCode"));
			models.put(ModelConst.IS_TOOL_PRICINGPLANS, lookupService.getDataList("IsToolPricingPlan"));
			models.put(ModelConst.NO_OVERSEA_DEST, lookupService.getDataList("NoOverseaDest"));
			models.put(ModelConst.T29_OVERSEA_DESTS, lookupService.getDataList("T29OverseaDest"));
			models.put(ModelConst.BLOCK_OF_TIMES, lookupService.getDataList("BlockOfTime"));
			models.put(ModelConst.DOLL_AR_CAP, lookupService.getDataList("DollarCap"));
			models.put(ModelConst.BILL_MINIUMS, lookupService.getDataList("BillMinium"));
			models.put(ModelConst.QUANTITYS, lookupService.getDataList("Quantity"));
			models.put(ModelConst.AIR_TIMES, lookupService.getDataList("airTime"));
			models.put(ModelConst.INCREMENTS_60, lookupService.getDataList("Increments60"));
			models.put(ModelConst.INCREMENTS_60_OR_30, lookupService.getDataList("Increments60or30"));
			models.put(ModelConst.INCREMENTS_30, lookupService.getDataList("Increments30"));
			models.put(ModelConst.INCREMENTS_10, lookupService.getDataList("Increments10"));
			models.put(ModelConst.INCREMENTS_1, lookupService.getDataList("Increments1"));
			models.put(ModelConst.ACCOUNT_BASE_LISTS, lookupService.getDataList("AccountBased"));
		}catch(Exception ex){
			logger.error("Cannot get search additional price plan info", ex);
		}
		
		ModelAndView mv = new ModelAndView("searchADPricePlan", models);
		return mv;
	}
	
	@RequestMapping(value="/searchADPricePlan.htm", method=RequestMethod.POST)
	public @ResponseBody JsonResultList<AdditionalPricePlanViewModel> getAllPricePlanListForJTable(@ModelAttribute SearchAdditionalPricePlanCriteria criteria, int jtStartIndex, int jtPageSize, String jtSorting,
			@ModelAttribute(value=SessionConst.WEB_SESSION) WebSession webSession){
		JsonResultList<AdditionalPricePlanViewModel> resultList = new JsonResultList<AdditionalPricePlanViewModel>();
		Map<String, Object> models = new HashMap<String, Object>();
		try{
			List<AdditionalPricePlan> list = null;
			list = additionalPPService.searchAdditionalPricePlans(criteria, jtStartIndex, jtPageSize, jtSorting);
			resultList.setRecords(formatListAddPricePlan(list));
			if(list != null && list.size() > 0){
				models.put(ModelConst.KEY_EXPORT_EXCEL, "show");
			}
			resultList.setResult(ResultEnum.OK);
			resultList.setTotalRecordsCount(additionalPPService.getTotalAdditionalPricePlansForSearch(criteria));
		}catch(Exception e){
			logger.error("Caused by: ", e);
			resultList.setResult(ResultEnum.ERROR);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
		
	}
	
	@SuppressWarnings("unchecked")
	private List<AdditionalPricePlanViewModel> formatListAddPricePlan(List<AdditionalPricePlan> list){
		List<AdditionalPricePlanViewModel> listResult = new ArrayList<AdditionalPricePlanViewModel>();
		try {
			if(list != null && list.size() >0){
				for (AdditionalPricePlan obj : list) {
					ViewModelConverter<AdditionalPricePlan, AdditionalPricePlanViewModel > viewModelConverter = ViewModelConverterFactory.getInstance().getConverter(AdditionalPricePlan.class.getName());
					AdditionalPricePlanViewModel objModel = viewModelConverter.convertToViewModel(obj);
					listResult.add(objModel);
				}
			}
			return listResult;
		} catch (Exception e) {
			logger.error("Caused by: ", e);
			return null;
		}
	}

	
	@SuppressWarnings({"unchecked" })
	@RequestMapping(value="/exportToExcelGenerateAdditionalPricePlanList.htm", method = RequestMethod.GET)
	public  HttpEntity<byte[]> exportToExcelGenerateAdditionalPricePlanListPage(@ModelAttribute(value=SessionConst.WEB_SESSION) WebSession webSession, HttpServletRequest request) throws Exception{
		try {
			/**
             * 	PROCESS EXCEL
             */
			HSSFWorkbook workbook = new HSSFWorkbook();
			IReportService<AdditionalPricePlan> iReport = ReportServiceFactory.getInstance().getReportService(ReportConst.REPORT_ADD_PRICE_PLAN);
			String sNameExcel = "AdditionalPricePlanInfo_" + util.getYearMonthDayCur();
			List<AdditionalPricePlan> list = additionalPPService.getAdditionalPricePlanList("planType");
			workbook = iReport.exportExcel(list);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			try {
				workbook.write(bos);
			} finally {
				bos.close();
			}
			byte[] documentBody = bos.toByteArray();
			HttpHeaders header = new HttpHeaders();
			header.setContentType(new MediaType("application", "xls"));
			header.set("Content-Disposition", "attachment; filename=" + sNameExcel + ".xls");
			header.setContentLength(documentBody.length);
			return new HttpEntity<byte[]>(documentBody, header);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//view description additional price plan
	@RequestMapping(value="/viewDescriptionAdditionalPricePlan.htm", method = RequestMethod.GET)
	public ModelAndView viewDescriptionAdditionalPricePlan(String pricePlanCode, String selectedTab, @ModelAttribute(value=SessionConst.WEB_SESSION) WebSession webSession){
		ModelAndView modelAndView = new ModelAndView("viewDescriptionAdditionalPricePlan");
		 try {
			 modelAndView.addObject("viewDescriptionAdditionalPricePlan", additionalPPService.searchAdditonalPricePlan(pricePlanCode));
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return modelAndView;
	}
	
	
	@SuppressWarnings({"unchecked" })
	@RequestMapping(value="/exportToExcelADDPricePlanSearch.htm", method = RequestMethod.GET)
	public  HttpEntity<byte[]> exportToExcelADDPricePlanSearch(@ModelAttribute SearchAdditionalPricePlanCriteria criteria, @ModelAttribute(value=SessionConst.WEB_SESSION) WebSession webSession, HttpServletRequest request) throws Exception{
		try {
			HSSFWorkbook workbook = new HSSFWorkbook();
			IReportService<AdditionalPricePlan> iReport = ReportServiceFactory.getInstance().getReportService(ReportConst.REPORT_ADD_PRICE_PLAN);
			String sNameExcel = "AdditionalPricePlanInfo_" + util.getYearMonthDayCur();
			List<AdditionalPricePlan> list = additionalPPService.searchAdditionalPricePlans(criteria, "planType ASC");
			workbook = iReport.exportExcel(list);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			try {
				workbook.write(bos);
			} finally {
				bos.close();
			}
			byte[] documentBody = bos.toByteArray();
			HttpHeaders header = new HttpHeaders();
			header.setContentType(new MediaType("application", "xls"));
			header.set("Content-Disposition", "attachment; filename=" + sNameExcel + ".xls");
			header.setContentLength(documentBody.length);
			return new HttpEntity<byte[]>(documentBody, header);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@ModelAttribute("additionalPricePlanView")
	private AdditionalPricePlanView createAdditionalPricePlanView(){
		AdditionalPricePlanView additionalPricePlanView = new AdditionalPricePlanView();
		return additionalPricePlanView;
	}
	
	
}


