package com.sevenj.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sevenj.dal.dao.PricePlanDAO;
import com.sevenj.dal.dao.ProjectDAO;
import com.sevenj.dal.dao.ToolPlanDAO;
import com.sevenj.dal.dao.impl.PricePlanDAOImpl;
import com.sevenj.dal.dao.impl.ProjectDAOImpl;
import com.sevenj.dal.dao.impl.ToolPlanDAOImpl;
import com.sevenj.dal.util.Utilities;
import com.sevenj.model.CBSSStatus;
import com.sevenj.model.PricePlan;
import com.sevenj.model.PricePlanStatus;
import com.sevenj.model.ToolPlan;
import com.sevenj.model.ToolPlanStatus;
import com.sevenj.service.PricePlanService;

public class PricePlanServiceImpl implements PricePlanService {
	private PricePlanDAO pricePlanDAO;
	private ProjectDAO projectDAO;
	private ToolPlanDAO toolPlanDAO;
	
	public PricePlanServiceImpl(){
		pricePlanDAO = new PricePlanDAOImpl();
		projectDAO = new ProjectDAOImpl();
		toolPlanDAO = new ToolPlanDAOImpl();
	}

	public void createNewPricePlan(String projectId, PricePlan pricePlan) throws Exception {
		pricePlan.setPricePlanStatus(PricePlanStatus.NEW.getStatusCode());
		pricePlan.setCBSSStatus(CBSSStatus.LOCAL.getStatusCode());
		pricePlan.setProject(projectDAO.getProject(projectId));
		pricePlanDAO.insertPricePlan(pricePlan);
	}

	public List<PricePlan> getPricePlansByProject(String projectId, int startIndex, int pageSize,
			String sortingColumn) throws Exception {
		return pricePlanDAO.getPricePlansByProject(projectId, startIndex, pageSize, sortingColumn);
	}

	public PricePlan getPricePlan(String pricePlanCode) throws Exception {
		return pricePlanDAO.getPricePlan(pricePlanCode);
	}

	public long getTotalPricePlanCount(String projectId) throws Exception {
		return pricePlanDAO.getTotalPricePlanCount(projectId);
	}

	public void updatePricePlan(PricePlan pricePlan) throws Exception {
		pricePlanDAO.updatePricePlan(pricePlan);
	}

	public void deletePricePlan(String pricePlanCode) throws Exception {
		pricePlanDAO.deletePricePlan(pricePlanCode);
	}
	
	public List<PricePlan> getPricePlans() throws Exception {
		String hql = "select distinct tp.pricePlan from ToolPlan tp where tp.approveStatus = 0 order by tp.pricePlan.pricePlanName ASC";
		Map<String, Object> paras = new HashMap<String, Object>();
		paras = null;
		return pricePlanDAO.executeAndGetList(hql, paras);
	}

	public List<PricePlan> getPricePlans(String projectId) throws Exception {
		String hql = "FROM PricePlan pp where pp.project.projectId = :projectId";		
		Map<String, Object> paras = new HashMap<String, Object>();
		paras.put("projectId", projectId);
		return pricePlanDAO.executeAndGetList(hql, paras);
	}
	
	public List<PricePlan> getPendingPricePlansByProject(String projectId,int startIndex, int pageSize, String sortingColumn)throws Exception {
		return pricePlanDAO.getPendingPricePlansByProject(projectId, startIndex, pageSize, sortingColumn);
	}

	public void importPricePlan(String destinationPricePlanCode, List<ToolPlan> toolPlans, String user) throws Exception {
		PricePlan pricePlan = pricePlanDAO.getPricePlan(destinationPricePlanCode);
		for (ToolPlan toolPlan : toolPlans) {
			toolPlan.setPricePlan(pricePlan);
			toolPlan.setRateValidationIndicator("2");
			toolPlan.setApproveStatus(ToolPlanStatus.PENDING.getStatusCode());
			toolPlan.setCbssStatus(CBSSStatus.JUST_PASTE.getStatusCode());
			toolPlan.setTableEntryCreationUser(user);
			toolPlan.setTableEntryCreationDate(Utilities.getCurrentDate());
		}
		toolPlanDAO.createEntitiesBatch(toolPlans);
	}
	
	public long getCountForPendingPricePlans(String projectId) throws Exception{
		return pricePlanDAO.getCountForPendingPricePlans(projectId);
	}

	@Override
	public List<PricePlan> getNotAprrovedPricePlans(String projectId)
			throws Exception {
		Map<String,Object> parameters = new HashMap<String,Object>();
		parameters.put("pricePlanStatus", PricePlanStatus.APPROVED.getStatusCode());
		parameters.put("projectId", projectId);
		return pricePlanDAO.executeAndGetList("FROM PricePlan p where p.pricePlanStatus != :pricePlanStatus and p.project.projectId = :projectId", parameters);
	}
}
