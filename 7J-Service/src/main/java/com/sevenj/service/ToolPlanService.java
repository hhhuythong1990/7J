package com.sevenj.service;

import java.util.List;

import com.sevenj.model.Comment;
import com.sevenj.model.History;
import com.sevenj.model.SearchToolPlanCriteria;
import com.sevenj.model.ToolPlan;
import com.sevenj.model.ToolPlanStatus;

public interface ToolPlanService {
	public List<ToolPlan> getToolPlanList(String pricePlanCode,int status) throws Exception;
	public List<ToolPlan> getToolPlanList(String pricePlanCode) throws Exception;
	public List<ToolPlan> getToolPlanList(String pricePlanCode,ToolPlanStatus status,int startIndex, int pageSize, String sortingField) throws Exception;
	public List<ToolPlan> getToolPlanList(String pricePlanCode,int startIndex, int pageSize, String sortingField) throws Exception;

	public long getTotalToolPlanCount(String pricePlanCode,ToolPlanStatus status) throws Exception;
	public long getTotalToolPlanCount(String pricePlanCode) throws Exception;

	public void createToolPlan(ToolPlan toolPlan) throws Exception;
	
	public void updateToolPlanStatus(String[] ids, ToolPlanStatus status) throws Exception;
	
	public void copyToolPlans(List<ToolPlan> toolPlans, String targetPricePlanId, String currentUser) throws Exception;
	
	public List<ToolPlan> getToolPlanList(String[] ids) throws Exception;
	
	public void commentToolPlans(String[] ids, String comment, String user) throws Exception;
	
	public void checkOutToolPlan(int toolPlanId, String user) throws Exception;
	public void checkOutToolPlans(String[] toolPlanIds, String user) throws Exception;
	public void checkInToolPlans(String[] toolPlanIds) throws Exception;
	public void updateToolPlans(List<ToolPlan> toolPlans, String user) throws Exception;
	
	public void updateToolPlan(ToolPlan toolPlan, String user) throws Exception;
	
	public List<ToolPlan> getToolPlansForImport(String pricePlanCode) throws Exception;
	
	public long getNumberOfToolPlansForImport(String pricePlanCode) throws Exception;
	
	public ToolPlan getToolPlan(int id) throws Exception;
	
	public List<Comment> getComments(int toolPlanId) throws Exception;
	
	public List<ToolPlan> searchToolPlans(SearchToolPlanCriteria criteria, int startIndex, int pageSize, String sortingColumn) throws Exception;
	
	public long getTotalToolPlansForSearch(SearchToolPlanCriteria criteria) throws Exception;
	
	public void deleteToolPlans(String[] ids) throws Exception;
	
	public List<ToolPlan> searchToolsPricePlans( SearchToolPlanCriteria criteria, int startIndex, int pageSize, String sorting) throws Exception;
	
	public List<ToolPlan> exportSearchToolsPricePlans( SearchToolPlanCriteria criteria,String sorting) throws Exception;
	
	public long getTotalToolsPricePlansForSearch(SearchToolPlanCriteria criteria) throws Exception;
}
