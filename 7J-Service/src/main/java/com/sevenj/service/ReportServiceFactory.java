package com.sevenj.service;

import java.util.HashMap;
import java.util.Map;

import com.sevenj.service.impl.ADDPricePlanReportServiceImpl;
import com.sevenj.service.impl.ToolPlanReportServiceImpl;
import com.sevenj.service.util.ReportConst;

public class ReportServiceFactory {

	@SuppressWarnings("rawtypes")
	private Map<String, Class<? extends IReportService>> maps = new HashMap<String, Class<? extends IReportService>>();
	private static ReportServiceFactory instance = new ReportServiceFactory(); 
	
	public static ReportServiceFactory getInstance(){
		return instance;
	}
	
	public ReportServiceFactory(){
		initialize();
	}
	public void initialize(){
		maps.put(ReportConst.REPORT_ADD_PRICE_PLAN, ADDPricePlanReportServiceImpl.class);
		maps.put(ReportConst.REPORT_TOOL_PLAN, ToolPlanReportServiceImpl.class);
	}
	
	@SuppressWarnings("rawtypes")
	public IReportService getReportService(String type) throws Exception{
		Class clazz = maps.get(type);
		IReportService service =  (IReportService) clazz.newInstance();
		return service;
	}
	
}
