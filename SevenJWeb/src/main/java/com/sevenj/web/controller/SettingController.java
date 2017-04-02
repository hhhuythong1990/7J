package com.sevenj.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.sevenj.model.Destination;
import com.sevenj.model.Project;
import com.sevenj.service.DestinationService;
import com.sevenj.service.impl.DestinationServiceImpl;
import com.sevenj.web.common.BreadCrumbConst;
import com.sevenj.web.common.SessionConst;
import com.sevenj.web.common.WebSession;
import com.sevenj.web.model.datatable.JsonResult;
import com.sevenj.web.model.datatable.JsonResultBase;
import com.sevenj.web.model.datatable.JsonResultList;
import com.sevenj.web.model.datatable.ResultEnum;

import dummiesmind.breadcrumb.springmvc.annotations.Link;

@Controller
@SessionAttributes({SessionConst.WEB_SESSION})
public class SettingController {
	private static final Logger logger = Logger.getLogger(SettingController.class);
	private DestinationService destinationService;
	
	public SettingController() {
		// TODO Auto-generated constructor stub
		this.destinationService = new DestinationServiceImpl();
	}
	
	@Link(label=BreadCrumbConst.SETTING_MANAGEMENT, family=BreadCrumbConst.MAIN_FLOW, parent="")
	@RequestMapping(value = "/settingList.htm", method = RequestMethod.GET)
	public String getSettingList() {
		return "settingList";
	}
	
	@RequestMapping(value = "/destinationListForJTable.htm", method = RequestMethod.POST)
	public @ResponseBody JsonResultList<Destination> getDestinationList(int jtStartIndex, int jtPageSize, String jtSorting){
		logger.info("Loading data destination for project table");
		JsonResultList<Destination> listResponse = new JsonResultList<Destination>();
		try {
			List<Destination> destinations = destinationService.getAllDestination(jtSorting);
			int totalRecordsCount = destinations.size();
			int toIndex = jtStartIndex + jtPageSize -1;
			if (toIndex > totalRecordsCount-1){
				listResponse.setRecords(destinations);
			}else{
				List<Destination> subdestinations = destinations.subList(jtStartIndex, toIndex);
				listResponse.setRecords(subdestinations);
			}
			
			listResponse.setResult(ResultEnum.OK);
			listResponse.setTotalRecordsCount(totalRecordsCount);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Caused by", e);
			listResponse.setResult(ResultEnum.ERROR);
			listResponse.setMessage(e.getMessage());
		}
		return listResponse;
	}
	
	@RequestMapping(value="/createNewDestination.htm", method = RequestMethod.GET)
	public String createNewDestination(){
		return "createNewDestination";
	}
	
	@RequestMapping(value="/createNewDestination.htm", method = RequestMethod.POST)
	public @ResponseBody JsonResult<Destination> createNewDestination(@ModelAttribute Destination destination){
		logger.info("Submit data to create new destination");
		JsonResult<Destination> destinationResponse = new JsonResult<Destination>();
		try {
			destinationService.createDestination(destination);
			destinationResponse.setResult(ResultEnum.OK);
			destinationResponse.setRecord(destination);
		} catch (Exception e) {
			logger.error("Caused by",e);
			destinationResponse.setResult(ResultEnum.ERROR);
			destinationResponse.setMessage(e.getMessage());
		}
		return destinationResponse;
	}
	
	@RequestMapping(value="/deleteDestination.htm", method = RequestMethod.POST)
	public @ResponseBody JsonResultBase deleteDestination(@RequestParam String destinationCode){
		logger.info("Delete Destination " + destinationCode);
		JsonResultBase jsonResultBase = new JsonResultBase();
		try {
			destinationService.deleteDestination(destinationCode);;
			jsonResultBase.setResult(ResultEnum.OK);
		} catch (Exception e) {
			logger.error("Caused by", e);
			jsonResultBase.setResult(ResultEnum.ERROR);
			jsonResultBase.setMessage(e.getMessage());
		}
		return jsonResultBase;
	}
	
	@RequestMapping(value="/updateDestination.htm", method = RequestMethod.GET)
	public ModelAndView showEditDestinationPage(String destinationCode){
		Destination destination = null;
		try{
			destination = destinationService.getDestination(destinationCode);
		}catch(Exception ex){
			logger.error("Caused by", ex);
		}
		ModelAndView view = new ModelAndView("editDestination","destination", destination);
		return view;
	}
	
	@RequestMapping(value="/updateDestination.htm", method = RequestMethod.POST)
	public @ResponseBody JsonResult<Destination> updateDestination(@ModelAttribute Destination destination, HttpServletRequest request){
		logger.info("Update Destination");
		JsonResult<Destination> jsonResult = new JsonResult<Destination>();
		destination.setDestinationCode(request.getParameter("destinationCode"));
		
		try{
			destinationService.updateDestination(destination);
			jsonResult.setRecord(destination);
			jsonResult.setResult(ResultEnum.OK);
		}catch(Exception e){
			logger.error("Caused by", e);
			jsonResult.setResult(ResultEnum.ERROR);
			jsonResult.setMessage(e.getMessage());
		}
		return jsonResult;
	}
}
