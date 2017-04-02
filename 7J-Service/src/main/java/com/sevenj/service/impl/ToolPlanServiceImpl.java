package com.sevenj.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sevenj.dal.dao.BaseDAO;
import com.sevenj.dal.dao.CommentDAO;
import com.sevenj.dal.dao.HistoryDAO;
import com.sevenj.dal.dao.PricePlanDAO;
import com.sevenj.dal.dao.ToolPlanDAO;
import com.sevenj.dal.dao.impl.BaseDAOImpl;
import com.sevenj.dal.dao.impl.CommentDAOImpl;
import com.sevenj.dal.dao.impl.HistoryDAOImpl;
import com.sevenj.dal.dao.impl.PricePlanDAOImpl;
import com.sevenj.dal.dao.impl.ToolPlanDAOImpl;
import com.sevenj.dal.util.Utilities;
import com.sevenj.model.AdditionalPricePlan;
import com.sevenj.model.CBSSStatus;
import com.sevenj.model.Comment;
import com.sevenj.model.History;
import com.sevenj.model.PricePlan;
import com.sevenj.model.SearchAdditionalPricePlanCriteria;
import com.sevenj.model.SearchToolPlanCriteria;
import com.sevenj.model.ToolPlan;
import com.sevenj.model.ToolPlanStatus;
import com.sevenj.service.ToolPlanService;
import com.sevenj.service.util.ServiceUtils;

public class ToolPlanServiceImpl implements ToolPlanService{
	private ToolPlanDAO toolPlanDAO;
	private PricePlanDAO pricePlanDAO;
	private CommentDAO commentDAO;
	private HistoryDAO historyDAO;

	public ToolPlanServiceImpl(){
		toolPlanDAO = new ToolPlanDAOImpl();
		pricePlanDAO = new PricePlanDAOImpl();
		commentDAO = new CommentDAOImpl();
		historyDAO = new HistoryDAOImpl();
	}
	public ToolPlanServiceImpl(ToolPlanDAO toolPlanDAO){
		this.toolPlanDAO = toolPlanDAO;
	}
	public List<ToolPlan> getToolPlanList(String pricePlanCode, int status) throws Exception {
		return toolPlanDAO.getToolPlanList(pricePlanCode, status);
	}
	public List<ToolPlan> getToolPlanList(String pricePlanCode, ToolPlanStatus status,int startIndex, int pageSize, String sortingField) throws Exception {
		return toolPlanDAO.getToolPlanList(pricePlanCode, status, startIndex, pageSize,sortingField);
	}
	
	public long getTotalToolPlanCount(String pricePlanCode,
			ToolPlanStatus status) throws Exception {
		return toolPlanDAO.getTotalToolPlanCount(pricePlanCode, status);
	}
	public void createToolPlan(ToolPlan toolPlan) throws Exception {
		toolPlan.setRateValidationIndicator("2");
		toolPlan.setApproveStatus(ToolPlanStatus.PENDING.getStatusCode());
		toolPlan.setCbssStatus(CBSSStatus.LOCAL.getStatusCode());
		toolPlanDAO.createToolPlan(toolPlan);
	}
	public void updateToolPlanStatus(String[] ids, ToolPlanStatus status) throws Exception {
		toolPlanDAO.updateToolPlanStatus(ids, status);
	}
	public void copyToolPlans(List<ToolPlan> toolPlans, String targetPricePlanCode,
			String currentUser) throws Exception{
		PricePlan pricePlan = pricePlanDAO.getPricePlan(targetPricePlanCode);
		for (ToolPlan toolPlan: toolPlans) {
			copyToolPlan(toolPlan, pricePlan, currentUser);
		}
		toolPlanDAO.createEntitiesBatch(toolPlans);
	}
	
	private void copyToolPlan(ToolPlan toolPlan, PricePlan targetPricePlan, String currentUser){
		toolPlan.setPricePlan(targetPricePlan);
		toolPlan.setTableEntryCreationUser(currentUser);
		toolPlan.setTableEntryCreationDate(Utilities.getCurrentDate());
		toolPlan.setCbssStatus(CBSSStatus.JUST_PASTE.getStatusCode());
		toolPlan.setApproveStatus(ToolPlanStatus.PENDING.getStatusCode());
	}
	public List<ToolPlan> getToolPlanList(String[] ids) throws Exception {
		return toolPlanDAO.getListByPrimaryKeys(ToolPlan.class, "ID", ServiceUtils.convertStringsToInteger(ids));
	}
	
	public void commentToolPlans(String[] ids, String commentContent, String user) throws Exception {
		List<Comment> comments = new ArrayList<Comment>();
		for (String id : ids) {
			ToolPlan toolPlan = toolPlanDAO.getEntityById(ToolPlan.class, Integer.parseInt(id));
			Comment comment = createComment(toolPlan, commentContent, user);
			comments.add(comment);
		}
		BaseDAO<Comment> dao = new BaseDAOImpl<Comment>();
		dao.createEntitiesBatch(comments);
	}
	
	private Comment createComment(ToolPlan toolPlan, String commentContent, String user){
		Comment comment = new Comment();
		comment.setComment(commentContent);
		comment.setCommentDate(Utilities.getCurrentDate());
		comment.setCommentUser(user);
		comment.setToolPlan(toolPlan);
		return comment;
	}
	
	public void checkOutToolPlan(int toolPlanId, String user) throws Exception {
		ToolPlan toolPlan = toolPlanDAO.getEntityById(ToolPlan.class, toolPlanId);
		toolPlan.setStatus(ToolPlanStatus.CHECKOUT.getStatusCode());
		toolPlan.setCheckOutUser(user);
		toolPlanDAO.update(toolPlan);
	}
	public void updateToolPlans(List<ToolPlan> toolPlans, String user) throws Exception {
		toolPlanDAO.updateToolPlans(toolPlans, user);
		
	}
	public List<ToolPlan> getToolPlansForImport(String pricePlanCode)
			throws Exception {
		return toolPlanDAO.getToolPlanForImport(pricePlanCode);
	}
	
	public long getNumberOfToolPlansForImport(String pricePlanCode)
			throws Exception {
		return toolPlanDAO.getNumberOfToolPlansForImport(pricePlanCode);
	}
	public ToolPlan getToolPlan(int id) throws Exception {
		return toolPlanDAO.getEntityById(ToolPlan.class, id);
	}
	
	public void updateToolPlan(ToolPlan toolPlan, String user) throws Exception {
		toolPlan.setStatus(ToolPlanStatus.CHECKIN.getStatusCode());
		toolPlanDAO.updateToolPlan(toolPlan, user);
	}
	public List<Comment> getComments(int toolPlanId) throws Exception {
		return commentDAO.getComments(toolPlanId);
	}
	public List<ToolPlan> searchToolPlans(SearchToolPlanCriteria criteria,
			int startIndex, int pageSize, String sortingColumn)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	public long getTotalToolPlansForSearch(SearchToolPlanCriteria criteria)
			throws Exception {
		return toolPlanDAO.getTotalToolPlansBySearch(criteria);
	}
	
	public void deleteToolPlans(String[] ids) throws Exception{
		for (String id: ids) {
			toolPlanDAO.deleteToolPlan(Integer.parseInt(id));
		}
	}
	@Override
	public List<ToolPlan> getToolPlanList(String pricePlanCode)
			throws Exception {
		toolPlanDAO.getToolPlanList(pricePlanCode);
		return null;
	}
	@Override
	public long getTotalToolPlanCount(String pricePlanCode) throws Exception {
		return toolPlanDAO.getTotalToolPlanCount(pricePlanCode);
	}
	@Override
	public List<ToolPlan> getToolPlanList(String pricePlanCode, int startIndex,
			int pageSize, String sortingField) throws Exception {
		return toolPlanDAO.getToolPlanList(pricePlanCode, startIndex, pageSize, sortingField);
	}
	public List<ToolPlan> searchToolsPricePlans( SearchToolPlanCriteria criteria, int startIndex, int pageSize, String sorting) throws Exception {
		return toolPlanDAO.searchToolPricePlans(criteria, startIndex, pageSize, sorting);
	}
	public List<ToolPlan> exportSearchToolsPricePlans( SearchToolPlanCriteria criteria, String sorting) throws Exception {
		return toolPlanDAO.exportSearchToolPricePlans(criteria,sorting);
	}
	public long getTotalToolsPricePlansForSearch(SearchToolPlanCriteria criteria) throws Exception {
		return toolPlanDAO.getTotalToolPricePlansForSearch(criteria);
	}
	@Override
	public void checkOutToolPlans(String[] toolPlanIds, String user) throws Exception {
		List<ToolPlan> toolPlans = new ArrayList<ToolPlan>();
		for(String toolPlanId: toolPlanIds){
			ToolPlan toolPlan = toolPlanDAO.getEntityById(ToolPlan.class, Integer.parseInt(toolPlanId));
			toolPlan.setStatus(ToolPlanStatus.CHECKOUT.getStatusCode());
			toolPlan.setCheckOutUser(user);
			toolPlans.add(toolPlan);
		}
		toolPlanDAO.updateEntityBatch(toolPlans);
	}
	@Override
	public void checkInToolPlans(String[] toolPlanIds) throws Exception {
		List<ToolPlan> toolPlans = new ArrayList<ToolPlan>();
		for(String toolPlanId: toolPlanIds){
			ToolPlan toolPlan = toolPlanDAO.getEntityById(ToolPlan.class, Integer.parseInt(toolPlanId));
			toolPlan.setStatus(ToolPlanStatus.CHECKIN.getStatusCode());
			toolPlans.add(toolPlan);
		}
		toolPlanDAO.updateEntityBatch(toolPlans);
	}
	
}
