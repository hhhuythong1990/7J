package com.sevenj.service;

import java.util.List;

import com.sevenj.model.PricePlan;
import com.sevenj.model.ToolPlan;

public interface PricePlanService {
	public void createNewPricePlan(String projectId, PricePlan pricePlan) throws Exception;
	public List<PricePlan> getPricePlansByProject(String projectId,int startIndex, int pageSize, String sortingColumn) throws Exception;
	public long getTotalPricePlanCount(String projectId) throws Exception;
	public PricePlan getPricePlan(String pricePlanCode) throws Exception;
	public void updatePricePlan(PricePlan pricePlan) throws Exception;
	public void deletePricePlan(String pricePlanCode) throws Exception;
	public List<PricePlan> getPricePlans(String projectId) throws Exception;
	public List<PricePlan> getPricePlans() throws Exception;
	public List<PricePlan> getNotAprrovedPricePlans(String projectId) throws Exception;
	public List<PricePlan> getPendingPricePlansByProject(String projectId,int startIndex, int pageSize, String sortingColumn)throws Exception;
	public void importPricePlan(String destinationPricePlanCode, List<ToolPlan> toolPlans, String user) throws Exception;
	public long getCountForPendingPricePlans(String projectId) throws Exception;
}
