package com.sevenj.web.controller;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.cxf.common.util.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.sevenj.model.AdditionalPricePlan;
import com.sevenj.model.Destination;
import com.sevenj.model.PricePlan;
import com.sevenj.model.SearchToolPlanCriteria;
import com.sevenj.model.ToolPlan;
import com.sevenj.model.ToolPlanStatus;
import com.sevenj.service.IReportService;
import com.sevenj.service.LookupDataService;
import com.sevenj.service.PricePlanService;
import com.sevenj.service.ReportServiceFactory;
import com.sevenj.service.ToolPlanService;
import com.sevenj.service.impl.LookupDataServiceImpl;
import com.sevenj.service.impl.PricePlanServiceImpl;
import com.sevenj.service.impl.ToolPlanServiceImpl;
import com.sevenj.service.util.ReportConst;
import com.sevenj.web.common.BreadCrumbConst;
import com.sevenj.web.common.ModelConst;
import com.sevenj.web.common.SessionConst;
import com.sevenj.web.common.SevenJConst;
import com.sevenj.web.common.WebSession;
import com.sevenj.web.data.LookupDataGeneratorFactory;
import com.sevenj.web.model.CreatedToolPlan;
import com.sevenj.web.model.ToolPlanModel;
import com.sevenj.web.model.UpdateToolPlanInfo;
import com.sevenj.web.model.converter.ViewModelConverter;
import com.sevenj.web.model.converter.ViewModelConverterFactory;
import com.sevenj.web.model.datatable.JsonResultBase;
import com.sevenj.web.model.datatable.JsonResultList;
import com.sevenj.web.model.datatable.ResultEnum;
import com.sevenj.web.validator.ToolPlanValidator;

import dummiesmind.breadcrumb.springmvc.annotations.Link;


@Controller
@SessionAttributes({SessionConst.WEB_SESSION})
public class ToolPlanController {
  private Logger logger = Logger.getLogger(ToolPlanController.class.getName());
  private ToolPlanService toolPlanService;
  private LookupDataService lookupData;
  private PricePlanService pricePlanService;
  public ToolPlanController(){
	  toolPlanService = new ToolPlanServiceImpl();
	  lookupData = new LookupDataServiceImpl();
	  pricePlanService = new PricePlanServiceImpl();
  }
  
  @RequestMapping(value="/getToolPlanList.htm", method = RequestMethod.GET)
  public String getToolPlanListPage(@ModelAttribute(value=SessionConst.WEB_SESSION)WebSession webSession, int status){
	  webSession.setCurrentToolPlanStatusTab(status);
	  return "toolPlanList";  
  }
  
  @RequestMapping(value="/getToolPlans.htm", method = RequestMethod.POST)
  public @ResponseBody JsonResultList<ToolPlan> getAprrovedToolPlan(@ModelAttribute(value=SessionConst.WEB_SESSION)WebSession webSession, int jtStartIndex, int jtPageSize, String jtSorting){
	  String pricePlanCode = webSession.getCurrentPricePlan().getPricePlanCode();
	  return getToolPlanList(pricePlanCode, jtStartIndex, jtPageSize, jtSorting);  
  }
  @SuppressWarnings({"unchecked" })
	@RequestMapping(value="/exportToExcelToolPlan.htm", method = RequestMethod.GET)
	public  HttpEntity<byte[]> exportToExcelToolPlan(@ModelAttribute(value=SessionConst.WEB_SESSION) WebSession webSession, HttpServletRequest request) throws Exception{
		try {
			/**
           * 	PROCESS EXCEL
           */
			String pricePlanCode = webSession.getCurrentPricePlan().getPricePlanCode();
		  	int selectedTab = webSession.getCurrentToolPlanStatusTab();
			HSSFWorkbook workbook = new HSSFWorkbook();
			IReportService<ToolPlan> iReportService = ReportServiceFactory.getInstance().getReportService(ReportConst.REPORT_TOOL_PLAN);
			String sNameExcel = pricePlanCode+"_"+new SimpleDateFormat("yyyy-mm-dd").format(new Date());
			List<ToolPlan> list = toolPlanService.getToolPlanList(pricePlanCode,selectedTab);
			workbook = iReportService.exportExcel(list);
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
			logger.error("Cannot export tool plans to excel", e);
		}
		return null;
	}
	@SuppressWarnings({"unchecked" })
	@RequestMapping(value="/exportSearchToolPricePlan.htm", method = RequestMethod.GET)
	public  HttpEntity<byte[]> exportSearchToolPricePlan(@ModelAttribute SearchToolPlanCriteria criteria,@ModelAttribute(value=SessionConst.WEB_SESSION) WebSession webSession, HttpServletRequest request) throws Exception{
		try {
			/**
           * 	PROCESS EXCEL
           */
			HSSFWorkbook workbook = new HSSFWorkbook();
			IReportService<ToolPlan> iReportService = ReportServiceFactory.getInstance().getReportService(ReportConst.REPORT_TOOL_PLAN);
			List<ToolPlan> list = toolPlanService.exportSearchToolsPricePlans(criteria, "ID ASC");
			workbook = iReportService.exportExcel(list);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			try {
				workbook.write(bos);
			} finally {
				bos.close();
			}
			byte[] documentBody = bos.toByteArray();
			HttpHeaders header = new HttpHeaders();
			header.setContentType(new MediaType("application", "xls"));
			header.set("Content-Disposition", "attachment; filename=search.xls");
			header.setContentLength(documentBody.length);
			return new HttpEntity<byte[]>(documentBody, header);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
  @RequestMapping(value="/getToolPlanById.htm", method = RequestMethod.POST)
  public @ResponseBody JsonResultList<ToolPlan> getToolPlanById(@ModelAttribute(value=SessionConst.WEB_SESSION)WebSession webSession, int toolPlanId){
	  JsonResultList<ToolPlan> jsonToolPlans = new JsonResultList<ToolPlan>();
	  try{
		  jsonToolPlans.setResult(ResultEnum.OK);
		  ToolPlan toolPlan = toolPlanService.getToolPlan(toolPlanId);
		  List<ToolPlan> records = new ArrayList<ToolPlan>();
		  records.add(toolPlan);
		  jsonToolPlans.setRecords(records);
	  }catch(Exception ex){
		  logger.error("Cannot get tool plan, caused by", ex);
		  jsonToolPlans.setResult(ResultEnum.ERROR);
		  jsonToolPlans.setMessage(ex.getMessage());
	  }
	  return jsonToolPlans;
  }
  
  private JsonResultList<ToolPlan> getToolPlanList(String pricePlanCode, ToolPlanStatus status, int startIndex, int pageSize, String sorting){
	  JsonResultList<ToolPlan> jsonResultList = new JsonResultList<ToolPlan>();
	  try{
		  List<ToolPlan> toolPlans = toolPlanService.getToolPlanList(pricePlanCode,status, startIndex, pageSize, sorting);
		  jsonResultList.setRecords(toolPlans);
		  jsonResultList.setResult(ResultEnum.OK);
		  jsonResultList.setTotalRecordsCount(toolPlanService.getTotalToolPlanCount(pricePlanCode, status));
	  }catch(Exception ex){
		  logger.error("Caused by", ex);
		  jsonResultList.setResult(ResultEnum.ERROR);
		  jsonResultList.setMessage(ex.getMessage());
	  }
	  
	  return jsonResultList;
  }
  
  private JsonResultList<ToolPlan> getToolPlanList(String pricePlanCode, int startIndex, int pageSize, String sorting){
	  JsonResultList<ToolPlan> jsonResultList = new JsonResultList<ToolPlan>();
	  try{
		  List<ToolPlan> toolPlans = toolPlanService.getToolPlanList(pricePlanCode,startIndex, pageSize, sorting);
		  jsonResultList.setRecords(toolPlans);
		  jsonResultList.setResult(ResultEnum.OK);
		  jsonResultList.setTotalRecordsCount(toolPlanService.getTotalToolPlanCount(pricePlanCode));
	  }catch(Exception ex){
		  logger.error("Caused by", ex);
		  jsonResultList.setResult(ResultEnum.ERROR);
		  jsonResultList.setMessage(ex.getMessage());
	  }
	  
	  return jsonResultList;
  }
  
  private JsonResultList<ToolPlan> searchToolPlans(SearchToolPlanCriteria criteria, int startIndex, int pageSize, String sorting){
	  JsonResultList<ToolPlan> jsonResultList = new JsonResultList<ToolPlan>();
	  try{
		  List<ToolPlan> toolPlans = toolPlanService.searchToolPlans(criteria, startIndex, pageSize, sorting);
		  jsonResultList.setRecords(toolPlans);
		  jsonResultList.setResult(ResultEnum.OK);
		  jsonResultList.setTotalRecordsCount(toolPlanService.getTotalToolPlansForSearch(criteria));
	  }catch(Exception ex){
		  logger.error("Caused by", ex);
		  jsonResultList.setResult(ResultEnum.ERROR);
		  jsonResultList.setMessage(ex.getMessage());
	  }
	  
	  return jsonResultList;
  }
  
  @Link(label=BreadCrumbConst.CREATE_TOOL_PLAN, family=BreadCrumbConst.MAIN_FLOW, parent=BreadCrumbConst.VIEW_PRICE_PLAN)
  @RequestMapping(value="/createToolPlan.htm", method = RequestMethod.GET)
  public ModelAndView createToolPlan(){
	  Map<String, Object> models  = initializeModels();
	  ModelAndView mv = new ModelAndView("createToolPlan",models);
	  return mv;
  }
  
  @Link(label=BreadCrumbConst.EXTRACT_PRICE_PLAN, family=BreadCrumbConst.MAIN_FLOW, parent=BreadCrumbConst.EXTRACT_PRICE_PLAN)
  @RequestMapping(value="/extractPricePlan.htm", method = RequestMethod.GET)
  public ModelAndView extractPricePlan(){
	  Map<String, Object> models  = initializeModels();
	  ModelAndView mv = new ModelAndView("extractPricePlan",models);
	  return mv;
  }
  
 
  private Map<String, Object> initializeModels(){
	  Map<String, Object> models = new HashMap<String, Object>();
	  List<Destination> destinations = Collections.emptyList();
	  try{
		  destinations = lookupData.getDestinationList();
	  }catch(Exception ex){
		  logger.error("Error in create tool plan", ex);
	  }
	  models.put(ModelConst.DESTINATIONS, destinations);
	  addNewModel(models,ModelConst.WEEK_DEFINITION_CODES);
	  addNewModel(models,ModelConst.SATELLITE_CODES);
	  addNewModel(models,ModelConst.TYPE_OF_CALLS);
	  addNewModel(models, ModelConst.SPECIAL_TYPE_OF_CALLS);
	  addNewModel(models, ModelConst.CUSTOMER_SPECIFIC_RATING_CODES);
	  addNewModel(models, ModelConst.CAP_THRESHOLD_CODE);
	  addNewModel(models, ModelConst.RATE_TYPE);
	  addNewModel(models, ModelConst.RATE_TIME_UNIT);
	  return models;
  }
  
  @RequestMapping(value="/createToolPlan.htm", method=RequestMethod.POST)
  public ModelAndView createToolPlan(@ModelAttribute("createdToolPlan") @Validated CreatedToolPlan toolPlan, BindingResult bindingResult, @ModelAttribute(value=SessionConst.WEB_SESSION)WebSession webSession, HttpServletRequest request){
	  JsonResultBase resultBase = new JsonResultBase();
	  ModelAndView mv = null;
	  try{
		  ToolPlanValidator validator = new ToolPlanValidator();
		  ToolPlan newToolPlan = new ToolPlan();
		  BeanUtils.copyProperties(toolPlan, newToolPlan,new String[]{"pricePlan","comments","histories"});
		  if (!StringUtils.isEmpty(toolPlan.getCapThresholdCodeNew())){
			  newToolPlan.setCapThresholdCode(toolPlan.getCapThresholdCodeNew());
		  }
		  if (StringUtils.isEmpty(toolPlan.getCustomerSpecificRatingCodeNew())){
			  newToolPlan.setCustomerSpecificRatingCode(toolPlan.getCustomerSpecificRatingCodeNew());
		  }
		  PricePlan pricePlan = pricePlanService.getPricePlan(webSession.getCurrentPricePlan().getPricePlanCode());
		  newToolPlan.setPricePlan(pricePlan);
		  validator.validate(newToolPlan, bindingResult);
		  if (!bindingResult.hasErrors()){
			  //checkAddNewValues(toolPlan);
			  newToolPlan.setTableEntryCreationUser(webSession.getCurrentUser().getUsername());
			  newToolPlan.setTableEntryCreationDate(new Date());			  
			  toolPlanService.createToolPlan(newToolPlan);
			  mv = new ModelAndView("redirect:forwardToViewPricePlan.htm");
		  }else{
			  mv = createToolPlan();
		  }
	  }catch(Exception ex){
		  logger.error("Can't create tool plan, caused by", ex);
		  resultBase.setResult(ResultEnum.ERROR);
		  resultBase.setMessage(ex.getMessage());
	  }
	  return mv;
  }
  
//  @Link(label=BreadCrumbConst.COPY_TOOL_PLAN, family=BreadCrumbConst.MAIN_FLOW, parent="")
//  @RequestMapping(value="/copyToolPlan.htm", method= RequestMethod.GET)
//  public String copyToolPlans(){
//	  return "copyToolPlan";
//  }
  
  @RequestMapping(value="/approveToolPlan.htm", method = RequestMethod.POST)
  public @ResponseBody JsonResultBase approveToolPlan(String selectedToolPlanIds, @ModelAttribute(value=SessionConst.WEB_SESSION)WebSession webSession){
	  String[] ids = selectedToolPlanIds.split(SevenJConst.COMMA);
	  JsonResultBase resultBase = new JsonResultBase();
	  try{
		  toolPlanService.updateToolPlanStatus(ids, ToolPlanStatus.APPROVED);
		  resultBase.setResult(ResultEnum.OK);
	  }catch(Exception ex){
		  logger.error("Can't approve tool plan, caused by", ex);
		  resultBase.setResult(ResultEnum.ERROR);
		  resultBase.setMessage(ex.getMessage());
	  }
	  return resultBase;
  }
  
  @RequestMapping(value="/rejectToolPlan.htm", method = RequestMethod.POST)
  public @ResponseBody JsonResultBase rejectToolPlan(String selectedToolPlanIds, @ModelAttribute(value=SessionConst.WEB_SESSION)WebSession webSession){
	  String[] ids = selectedToolPlanIds.split(SevenJConst.COMMA);
	  JsonResultBase resultBase = new JsonResultBase();
	  try{
		  toolPlanService.updateToolPlanStatus(ids, ToolPlanStatus.REJECTED);
		  resultBase.setResult(ResultEnum.OK);
	  }catch(Exception ex){
		  logger.error("Can't reject tool plan, caused by", ex);
		  resultBase.setResult(ResultEnum.ERROR);
		  resultBase.setMessage(ex.getMessage());
	  }
	  return resultBase;
  }
  
  @RequestMapping(value="/copyToolPlans.htm", method = RequestMethod.POST)
  public @ResponseBody JsonResultBase copyToolPlans(String selectedToolPlanIds,String destinationPricePlanCode, @ModelAttribute(value=SessionConst.WEB_SESSION)WebSession webSession){
	  logger.info("Copy tool plans: " + selectedToolPlanIds);
	  JsonResultBase resultBase = new JsonResultBase();
	  try{
		  List<ToolPlan> toolPlans = toolPlanService.getToolPlanList(selectedToolPlanIds.split(SevenJConst.COMMA));
		  //make a new copy
		  List<ToolPlan> copiedToolPlans = new ArrayList<ToolPlan>();
		  for(ToolPlan toolPlan: toolPlans){
			  String[] ignoreProperties = new String[] {"ID","pricePlan","tableEntryCreationDate","comments","histories"};
			  ToolPlan newToolPlan = new ToolPlan();
			  BeanUtils.copyProperties(toolPlan, newToolPlan, ignoreProperties);
			  copiedToolPlans.add(newToolPlan);
		  }
		  toolPlanService.copyToolPlans(copiedToolPlans, destinationPricePlanCode, webSession.getCurrentUser().getUsername());
		  resultBase.setResult(ResultEnum.OK);
	  }catch(Exception ex){
		  logger.error("Can't copy tool plans, caused by", ex);
		  resultBase.setResult(ResultEnum.ERROR);
		  resultBase.setMessage(ex.getMessage());
	  }
	  return resultBase;
  }
  //@Link(label=BreadCrumbConst.COPY_TOOL_PLAN, family=BreadCrumbConst.MAIN_FLOW, parent="")
  @Link(label=BreadCrumbConst.COPY_TOOL_PLAN, family=BreadCrumbConst.MAIN_FLOW, parent="")
  @RequestMapping(value="/copyToolPlans.htm", method = RequestMethod.GET)
  public ModelAndView getCopyToolPlansPage(@ModelAttribute(value=SessionConst.WEB_SESSION)WebSession webSession, String selectedToolPlanIds){
	  return getViewPageForSelectedToolPlans(webSession,selectedToolPlanIds,"copyToolPlans");
  }
  
  @RequestMapping(value="/checkOutToolPlan.htm", method = RequestMethod.POST)
  public @ResponseBody JsonResultBase checkOutToolPlan(@ModelAttribute(value=SessionConst.WEB_SESSION)WebSession webSession, int toolPlanId){
	  JsonResultBase resultBase = new JsonResultBase();
	  try{
		  toolPlanService.checkOutToolPlan(toolPlanId, webSession.getCurrentUser().getUsername());
		  resultBase.setResult(ResultEnum.OK);
	  }catch(Exception ex){
		  logger.error("Can't check out tool plan " + toolPlanId, ex);
		  resultBase.setMessage(ex.getMessage());
		  resultBase.setResult(ResultEnum.ERROR);
	  }
	  return resultBase;
  }
  
  @Link(label=BreadCrumbConst.UPDATE_TOOL_PLAN, family=BreadCrumbConst.MAIN_FLOW, parent=BreadCrumbConst.VIEW_PRICE_PLAN)
  @RequestMapping(value="/updateToolPlans.htm", method = RequestMethod.GET)
  public ModelAndView getUpdateToolPlansPage(@ModelAttribute(value=SessionConst.WEB_SESSION)WebSession webSession,String selectedToolPlanIds){
	  try{
		  String[] ids = selectedToolPlanIds.split(SevenJConst.COMMA);
		  toolPlanService.checkOutToolPlans(ids, webSession.getCurrentUser().getUsername());
	  }catch(Exception ex){
		  logger.error("Cannot check out tool plan, caused by", ex);
	  }
	  return getViewPageForSelectedToolPlans(webSession, selectedToolPlanIds, "updateToolPlans");
  }
  
  @RequestMapping(value="/updateToolPlans.htm", method = RequestMethod.POST)
  public @ResponseBody ModelAndView updateToolPlans(@ModelAttribute(value=SessionConst.WEB_SESSION)WebSession webSession,@ModelAttribute("updateToolPlanInfo") @Valid UpdateToolPlanInfo updateToolPlanInfo, BindingResult result){
	  String selectedToolPlanIds = updateToolPlanInfo.getSelectedToolPlanIds();
	  String[] ids = updateToolPlanInfo.getSelectedToolPlanIds().split(SevenJConst.COMMA);
	  ModelAndView mv = null;
	  try{
		  if (!result.hasErrors()){
			  if (updateToolPlanInfo.getRate() != -1 || updateToolPlanInfo.getTableEntryEffectiveDate() != null 
					  || updateToolPlanInfo.getTableEntryExpirationDate() != null){
				  List<ToolPlan> toolPlans = toolPlanService.getToolPlanList(ids);
				  for (ToolPlan toolPlan : toolPlans) {
					  updateToolPlanIfNeeded(toolPlan,updateToolPlanInfo);
				  }
				  toolPlanService.updateToolPlans(toolPlans, webSession.getCurrentUser().getUsername());
			  }else{
				  toolPlanService.checkInToolPlans(ids);
			  }
			  mv = new ModelAndView("redirect:forwardToViewPricePlan.htm");
		  }else{
			  mv = new ModelAndView("updateToolPlans",ModelConst.SELECTED_TOOL_PLANS,selectedToolPlanIds);
		  }
	  }catch(Exception ex){
		  logger.error("Cannot update tool plans", ex);
		  mv = new ModelAndView("updateToolPlans",ModelConst.SELECTED_TOOL_PLANS,selectedToolPlanIds);
	  }
	  return mv;
  }
  
  private void updateToolPlanIfNeeded(ToolPlan toolPlan, UpdateToolPlanInfo updateToolPlanInfo){
	  double rate = updateToolPlanInfo.getRate(); 
	  if (rate != -1){
		  toolPlan.setRate(rate);
	  }
	  
	  Date tableEntryEffectiveDate = updateToolPlanInfo.getTableEntryEffectiveDate(); 
	  if (tableEntryEffectiveDate != null){
		  toolPlan.setTableEntryEffectiveDate(tableEntryEffectiveDate);
	  }
	  
	  Date tableEntryExpirationDate = updateToolPlanInfo.getTableEntryExpirationDate();
	  if (tableEntryExpirationDate != null){
		  toolPlan.setTableEntryExpirationDate(tableEntryExpirationDate);
	  }
	  toolPlan.setStatus(ToolPlanStatus.CHECKIN.getStatusCode());
  }
  
  private ModelAndView getViewPageForSelectedToolPlans(WebSession session, String selectedToolPlanIds, String viewName){
	  String projectId = session.getCurrentProject().getProjectId();
	  List<PricePlan> pricePlans = Collections.emptyList();
	  try{
		  pricePlans = pricePlanService.getNotAprrovedPricePlans(projectId);
	  }catch(Exception ex){
		  logger.error("Can't get price plans of project " + projectId + ", caused by: ", ex);
	  }
	  ModelMap modelMap = new ModelMap();
	  modelMap.addAttribute(ModelConst.PRICE_PLAN_LIST,pricePlans);
	  modelMap.addAttribute(ModelConst.SELECTED_TOOL_PLANS, selectedToolPlanIds);
	  ModelAndView mv = new ModelAndView(viewName,modelMap);
	  return mv;
  }
  
  @RequestMapping(value="/getSelectedToolPlans.htm", method = RequestMethod.POST)
  public @ResponseBody JsonResultList<ToolPlan> getSelectedToolPlan(@ModelAttribute(value=SessionConst.WEB_SESSION)WebSession webSession, int jtStartIndex, int jtPageSize, String jtSorting, String selectedToolPlanIds){
	  JsonResultList<ToolPlan> list = new JsonResultList<ToolPlan>();
	  try{
		  String[] toolPlanIds = selectedToolPlanIds.split(SevenJConst.COMMA);
		  List<ToolPlan> toolPlans = toolPlanService.getToolPlanList(toolPlanIds);
		  list.setRecords(toolPlans);
		  list.setResult(ResultEnum.OK);
	  }catch(Exception ex){
		  logger.error("Cannot get selected tool plans, caused by ", ex);
		  list.setMessage(ex.getMessage());
		  list.setResult(ResultEnum.ERROR);
	  }
	  return list;
  }
  
  @RequestMapping(value="/searchToolPlans.htm", method = RequestMethod.GET)
  public ModelAndView getSearchToolPlansPage(@ModelAttribute(value=SessionConst.WEB_SESSION)WebSession webSession){
	  Map<String, Object> models = new HashMap<String, Object>();
	  try{
		  List<Destination> destinations = Collections.emptyList();
		  destinations = lookupData.getDestinationList();
		  models.put(ModelConst.DESTINATIONS, destinations);
		  addNewModel(models, ModelConst.WEEK_DEFINITION_CODES);
		  addNewModel(models, ModelConst.SATELLITE_CODES);
		  addNewModel(models, ModelConst.TYPE_OF_CALLS);
		  addNewModel(models, ModelConst.SPECIAL_TYPE_OF_CALLS);
		  addNewModel(models, ModelConst.CUSTOMER_SPECIFIC_RATING_CODES);
		  addNewModel(models, ModelConst.CAP_THRESHOLD_CODE);
		  addNewModel(models, ModelConst.RATE_TYPE);
		  addNewModel(models, ModelConst.RATE_TIME_UNIT);
		  addNewModel(models, ModelConst.TOOL_PLAN_STATUSES);
	  }catch(Exception ex){
		  logger.error("Cannot get search tool plan page", ex);
	  }
	  ModelAndView mv = new ModelAndView("searchToolPlan", models);
	  return mv;
  }
  
  @Link(label=BreadCrumbConst.EDIT_TOOL_PLAN, family=BreadCrumbConst.MAIN_FLOW, parent=BreadCrumbConst.VIEW_PRICE_PLAN)
  @RequestMapping(value="/editToolPlan.htm", method = RequestMethod.GET)
  public ModelAndView editToolPlan(int toolPlanId, @ModelAttribute(value=SessionConst.WEB_SESSION)WebSession webSession){
	  ToolPlan toolPlan = null;
	  try{
		  toolPlan = toolPlanService.getToolPlan(toolPlanId);
	  }catch(Exception ex){
		  logger.error("Cannot get lookup data and get tool plan for edit, caused by", ex);
	  }
	  ModelAndView modelAndView = initializeEditToolPlan(toolPlan);
	  return modelAndView;
  }
  
  private ModelAndView initializeEditToolPlan(ToolPlan toolPlan){
	  Map<String, Object> models = new HashMap<String, Object>();
	  try{
		  models = initializeModels();
		  if (toolPlan.getCbssStatus() != 7){
			  Destination originatingDestination = lookupData.getDestinationById(toolPlan.getOriginatingDestinationCode());
			  models.put(ModelConst.ORIGINATING_DESTINATION, originatingDestination);
			  Destination terminatingDestination = lookupData.getDestinationById(toolPlan.getTerminatingDestinationCode());
			  models.put(ModelConst.TERMINATING_DESTINATION, terminatingDestination);
		  }
		  models.put(ModelConst.TOOL_PLAN, toolPlan);
	  }catch(Exception ex){
		  logger.error("Cannot get lookup data and get tool plan for edit, caused by", ex);
	  }
	  ModelAndView modelAndView = new ModelAndView("editToolPlan",models);
	  return modelAndView;
  }
  
  @RequestMapping(value="/editToolPlan.htm", method = RequestMethod.POST)
  public ModelAndView editToolPlan(@ModelAttribute("toolPlan") @Validated ToolPlan toolPlan, BindingResult bindingResult, @ModelAttribute(value=SessionConst.WEB_SESSION)WebSession webSession){
	  ModelAndView mv = null;
	  try{
		  ToolPlan oldToolPlan = toolPlanService.getToolPlan(toolPlan.getID());
		  toolPlan.setPricePlan(oldToolPlan.getPricePlan());
		  ToolPlanValidator validator = new ToolPlanValidator();
		  validator.validate(toolPlan, bindingResult);
		  if (!bindingResult.hasErrors()){
			  if (toolPlan.getCbssStatus() != 7){
				  oldToolPlan.setRate(toolPlan.getRate());
				  oldToolPlan.setTableEntryEffectiveDate(toolPlan.getTableEntryEffectiveDate());
				  oldToolPlan.setTableEntryExpirationDate(toolPlan.getTableEntryExpirationDate());
			  }else{
				  String[] ignoreProperties = new String[]{"pricePlan","tableEntryCreationDate","status","checkOutUser","approveStatus","cbssStatus","tableEntryCreationUser","rateValidationIndicator","comments","histories"};
				  BeanUtils.copyProperties(toolPlan, oldToolPlan, ignoreProperties);
			  }
			  toolPlanService.updateToolPlan(oldToolPlan, webSession.getCurrentUser().getUsername());
			  mv = new ModelAndView("redirect:forwardToViewPricePlan.htm");
		  }else{
			  mv = initializeEditToolPlan(toolPlan);
		  }
	  }catch(Exception ex){
		  logger.error("Cannot update tool plan, caused by ", ex);
	  }
	  return mv;
  }
  
  @Link(label=BreadCrumbConst.VIEW_TOOL_PLAN, family=BreadCrumbConst.MAIN_FLOW, parent=BreadCrumbConst.VIEW_PRICE_PLAN)
  @RequestMapping(value="/viewToolPlan.htm", method = RequestMethod.GET)
  public ModelAndView getViewEditPage(int toolPlanId, @ModelAttribute(value=SessionConst.WEB_SESSION)WebSession webSession){
	  Map<String, Object> models = new HashMap<String, Object>();
	  try{
		  ToolPlan toolPlan = toolPlanService.getToolPlan(toolPlanId);
		  models.put(ModelConst.TOOL_PLAN, toolPlan);
	  }catch(Exception ex){
		  logger.error("Cannot view tool plan, caused by ", ex);
	  }
	  ModelAndView mv = new ModelAndView("viewToolPlan", models);
	  return mv;
  }
  
  private void checkAddNewValues(CreatedToolPlan toolPlan){
	  String customerSpecificRatingCodeNew = toolPlan.getCustomerSpecificRatingCodeNew();
	  if (!StringUtils.isEmpty(customerSpecificRatingCodeNew)){
		  toolPlan.setCustomerSpecificRatingCode(customerSpecificRatingCodeNew);
	  }
	  String capThresholdCodeNew = toolPlan.getCapThresholdCodeNew();
	  if (!StringUtils.isEmpty(capThresholdCodeNew)){
		  toolPlan.setCapThresholdCode(capThresholdCodeNew);
	  }
  }
  
  private void addNewModel(Map<String, Object> models, String name){
	  LookupDataGeneratorFactory generatorFactory = LookupDataGeneratorFactory.getInstance(); 
	  models.put(name, generatorFactory.getGenerator(name).generate());
  }
  
  @InitBinder
  private void dateBinder(WebDataBinder binder) {
      //The date format to parse or output your dates
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      //Create a new CustomDateEditor
      CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
      //Register it as custom editor for the Date type
      binder.registerCustomEditor(Date.class, editor);
  }

	@RequestMapping(value = "/deleteToolPlans.htm", method = RequestMethod.POST)
	public @ResponseBody JsonResultBase deletePricePlan(String selectedToolPlanIds) {
		JsonResultBase jsonResultBase = new JsonResultBase();
		try {
			String[] ids = selectedToolPlanIds.split(SevenJConst.COMMA);
			toolPlanService.deleteToolPlans(ids);
			jsonResultBase.setResult(ResultEnum.OK);
		} catch (Exception ex) {
			logger.error("Caused by", ex);
			jsonResultBase.setResult(ResultEnum.ERROR);
			jsonResultBase.setMessage(ex.getMessage());
		}
		return jsonResultBase;
	}

	
	@RequestMapping(value = "/validateToolPlans.htm", method = RequestMethod.POST)
	public @ResponseBody JSONArray validateToolPlan(@ModelAttribute ToolPlan toolPlan){
		Map<String, String> returnValid = new HashMap<String, String>();
//		returnValid = toolPlan.validate();
		JSONArray jsonArray = new JSONArray();
		for(Entry<String, String> e: returnValid.entrySet()){
			JSONObject jsonObject = new JSONObject();
			jsonArray.add(jsonObject);
		}
		
		return jsonArray;
	}
  

	@Link(label=BreadCrumbConst.SEARCH_TOOL_PRICE_PLAN, family=BreadCrumbConst.MAIN_FLOW, parent=BreadCrumbConst.VIEW_PROJECT)
	@RequestMapping(value="/searchToolPricePlan.htm", method = RequestMethod.GET)
	  public ModelAndView getSearchToolPlan(@ModelAttribute(value=SessionConst.WEB_SESSION)WebSession webSession){
		  Map<String, Object> models = new HashMap<String, Object>();
		  try{
			  List<Destination> destinations = Collections.emptyList();
			  destinations = lookupData.getDestinationList();
			  models.put(ModelConst.DESTINATIONS, destinations);
			  addNewModel(models, ModelConst.WEEK_DEFINITION_CODES);
			  addNewModel(models, ModelConst.SATELLITE_CODES);
			  addNewModel(models, ModelConst.TYPE_OF_CALLS);
			  addNewModel(models, ModelConst.SPECIAL_TYPE_OF_CALLS);
			  addNewModel(models, ModelConst.CUSTOMER_SPECIFIC_RATING_CODES);
			  addNewModel(models, ModelConst.CAP_THRESHOLD_CODE);
			  addNewModel(models, ModelConst.RATE_TYPE);
			  addNewModel(models, ModelConst.RATE_TIME_UNIT);
			  addNewModel(models, ModelConst.TOOL_PLAN_STATUSES);
		  }catch(Exception ex){
			  logger.error("Cannot get search tool plan page", ex);
		  }
		  ModelAndView mv = new ModelAndView("searchToolPricePlan", models);
		  return mv;
	  }
	@RequestMapping(value="/searchToolPricePlan.htm", method=RequestMethod.POST)
	public @ResponseBody JsonResultList<ToolPlanModel> getAllPricePlanListForJTable(@ModelAttribute SearchToolPlanCriteria criteria, int jtStartIndex, int jtPageSize, String jtSorting,
			@ModelAttribute(value=SessionConst.WEB_SESSION) WebSession webSession){
		JsonResultList<ToolPlanModel> resultList = new JsonResultList<ToolPlanModel>();
		try{
			List<ToolPlan> list = null;
			list = toolPlanService.searchToolsPricePlans(criteria, jtStartIndex, jtPageSize, jtSorting);
			resultList.setRecords(formatListAddPricePlan(list));
			resultList.setResult(ResultEnum.OK);
			resultList.setTotalRecordsCount(toolPlanService.getTotalToolsPricePlansForSearch(criteria));
		}catch(Exception e){
			logger.error("Caused by: ", e);
			resultList.setResult(ResultEnum.ERROR);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
		
	}
	
	@SuppressWarnings("unchecked")
	private List<ToolPlanModel> formatListAddPricePlan(List<ToolPlan> list){
		List<ToolPlanModel> listResult = new ArrayList<ToolPlanModel>();
		try {
			if(list != null && list.size() >0){
				for (ToolPlan obj : list) {
					ViewModelConverter<ToolPlan, ToolPlanModel > viewModelConverter = ViewModelConverterFactory.getInstance().getConverter(ToolPlan.class.getName());
					ToolPlanModel objModel = viewModelConverter.convertToViewModel(obj);
					listResult.add(objModel);
				}
			}
			return listResult;
		} catch (Exception e) {
			logger.error("Caused by: ", e);
			return null;
		}
	}
	
	@ModelAttribute("createdToolPlan")
	public CreatedToolPlan createToolPlanModel(){
		return new CreatedToolPlan();
	}
	
	@ModelAttribute("updateToolPlanInfo")
	public UpdateToolPlanInfo createUpdateToolInfo(){
		return new UpdateToolPlanInfo();
	}
	
}
