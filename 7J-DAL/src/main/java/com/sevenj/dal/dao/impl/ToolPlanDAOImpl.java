package com.sevenj.dal.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.mysql.jdbc.StringUtils;
import com.sevenj.dal.dao.ToolPlanDAO;
import com.sevenj.dal.util.SearchUtils;
import com.sevenj.dal.util.Utilities;
import com.sevenj.model.AdditionalPricePlan;
import com.sevenj.model.CBSSStatus;
import com.sevenj.model.Comment;
import com.sevenj.model.History;
import com.sevenj.model.SearchAdditionalPricePlanCriteria;
import com.sevenj.model.SearchToolPlanCriteria;
import com.sevenj.model.ToolPlan;
import com.sevenj.model.ToolPlanStatus;

public class ToolPlanDAOImpl extends BaseDAOImpl<ToolPlan> implements ToolPlanDAO{

	public List<ToolPlan> getToolPlanList(String pricePlanCode, int status) throws Exception {
		List<ToolPlan> toolPlans = Collections.EMPTY_LIST;
//		String hql = "FROM ToolPlan tp WHERE tp.approveStatus = :status AND tp.pricePlan.pricePlanCode = :pricePlanCode";
		String hql = "FROM ToolPlan tp WHERE tp.pricePlan.pricePlanCode = :pricePlanCode";
		try{
			session = sessionFactory.openSession();
			Query query = session.createQuery(hql);
//			query.setParameter("status", status);
			query.setParameter("pricePlanCode", pricePlanCode);
			toolPlans = query.list();
		}finally{
			closeSession();
		}
		return toolPlans;
	}
	
	public List<ToolPlan> getToolPlanList(String pricePlanCode, ToolPlanStatus status, int startIndex, int pageSize, String sortingColumn) throws Exception {
		List<ToolPlan> toolPlans = Collections.EMPTY_LIST;
		String hql = "FROM ToolPlan tp WHERE tp.approveStatus = :status AND tp.pricePlan.pricePlanCode = :pricePlanCode";
		if (!StringUtils.isNullOrEmpty(sortingColumn)){
			hql +=  " ORDER BY tp." + sortingColumn;
		}
		
		try{
			session = sessionFactory.openSession();
			Query query = session.createQuery(hql);
			query.setParameter("status", status.getStatusCode());
			query.setParameter("pricePlanCode", pricePlanCode);
			query.setFirstResult(startIndex);
			query.setMaxResults(pageSize);
			toolPlans = query.list();
		}finally{
			closeSession();
		}
		return toolPlans;
	}

	public long getTotalToolPlanCount(String pricePlanCode,
			ToolPlanStatus status) throws Exception {
		long totalRecordCount = 0;
		try{
			session = sessionFactory.openSession();
			String hql = "SELECT count(*) FROM ToolPlan WHERE pricePlan.pricePlanCode = :pricePlanCode AND approveStatus = :status";
			Query query = session.createQuery(hql);
			query.setParameter("pricePlanCode", pricePlanCode);
			query.setParameter("status", status.getStatusCode());
			//totalRecordCount = ((Long)query.iterate().next()).longValue();
			totalRecordCount = (Long)query.uniqueResult();
		}finally{
			closeSession();
		}
		return totalRecordCount;
	}

	public void createToolPlan(ToolPlan toolPlan) throws Exception {
		try{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Integer id = (Integer)session.save(toolPlan);
			toolPlan.setID(id);
//			History history = createHistory(toolPlan, toolPlan.getTableEntryCreationUser());
//			session.save(history);
			tx.commit();
		} catch(Exception ex){
			tx.rollback();
			throw ex;
		}finally{
			closeSession();
		}		
	}

	@Override
	public void updateToolPlanStatus(String[] ids, ToolPlanStatus status) throws Exception {
		try{
			String hql = "UPDATE ToolPlan set approveStatus = :status where ID= :toolPlanid";
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			for(String id: ids){
				Query query = session.createQuery(hql);
				query.setParameter("status", status.getStatusCode());
				query.setParameter("toolPlanid", Integer.parseInt(id));
				query.executeUpdate();
			}
			tx.commit();
		}catch(Exception ex){
			tx.rollback();
			throw ex;
		}finally{
			closeSession();
		}
	}

	@Override
	public List<ToolPlan> getToolPlanForImport(String pricePlanCode) throws Exception {
		List<ToolPlan> toolPlans = Collections.emptyList();
		try{
			session = sessionFactory.openSession();
			Query query = generateToolPlanQueryForImport(session, pricePlanCode,null);
			toolPlans = query.list();
		}finally{
			closeSession();
		}
		return toolPlans;
	}

	private Query generateToolPlanQueryForImport(Session session,String pricePlanCode, String selectColumn) throws Exception{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date dateFrom = formatter.parse("9999-12-31 00:00:00");
        Date dateTo = formatter.parse("9999-12-31 23:59:59");

        String hql = "";
        if (!StringUtils.isNullOrEmpty(selectColumn)){
        	hql = selectColumn + " ";
        }
		hql += "FROM ToolPlan tp where tp.pricePlan.pricePlanCode = :pricePlanCode AND tp.tableEntryExpirationDate BETWEEN :dateFrom AND :dateTo";
		
		Query query = session.createQuery(hql);
		query.setParameter("pricePlanCode", pricePlanCode);
		query.setParameter("dateFrom",dateFrom);
		query.setParameter("dateTo",dateTo);
		return query;
	}
	
	@Override
	public long getNumberOfToolPlansForImport(String pricePlanCode)
			throws Exception {
		long count =0;
		try{
			session = sessionFactory.openSession();
			Query query = generateToolPlanQueryForImport(session, pricePlanCode,"SELECT count(*)");
			count = (Long)query.uniqueResult();
		}finally{
			closeSession();
		}
		return count;
	}

	@Override
	public void updateToolPlan(ToolPlan toolPlan, String user) throws Exception {
		try{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			updateToolPlan(toolPlan,user,session);
			tx.commit();
		}catch(Exception ex){
			tx.rollback();
			throw ex;
		}finally{
			closeSession();
		}
	}
	
	private void updateToolPlan(ToolPlan toolPlan, String user, Session session) throws Exception{
		History history = createHistory(toolPlan.getID(), user,session);
		session.save(history);
		toolPlan.setStatus(ToolPlanStatus.CHECKIN.getStatusCode());
		toolPlan.setCbssStatus(CBSSStatus.CHANGED.getStatusCode());
		session.merge(toolPlan);
	}
	
	private History createHistory(int toolPlanId, String user, Session session) throws Exception{
		ToolPlan oldToolPlan = (ToolPlan)session.get(ToolPlan.class, toolPlanId);
		History history = new History();
		history.setToolPlan(oldToolPlan);
		history.setToolPlanRecord(oldToolPlan.toHistoryString());
		history.setHistoryType(1);
		history.setChangeUser(user);
		history.setHistoryDate(Utilities.getCurrentDate());
		return history;
	}
	
//	private History createHistory(ToolPlan toolPlan, String user) throws Exception{
//		History history = new History();
//		history.setToolPlan(toolPlan);
//		history.setToolPlanRecord(toolPlan.toHistoryString());
//		history.setHistoryType(1);
//		history.setChangeUser(user);
//		history.setHistoryDate(Utilities.getCurrentDate());
//		return history;
//	}

	@Override
	public void updateToolPlans(List<ToolPlan> toolPlans, String user)
			throws Exception {
		try{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			for (ToolPlan toolPlan : toolPlans) {
				updateToolPlan(toolPlan,user,session);
			}
			tx.commit();
		}catch(Exception ex){
			tx.rollback();
			throw ex;
		}finally{
			closeSession();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ToolPlan> searchToolPlans(SearchToolPlanCriteria searchCriteria,
			int startIndex, int pageSize, String sortingColumn)
			throws Exception {
		try{
			session = sessionFactory.openSession();
			Criteria criteria = buildSearchCriteria(searchCriteria);
			criteria.setFirstResult(startIndex);
			criteria.setMaxResults(pageSize);
			SearchUtils.addOrderIfNeeded(criteria, sortingColumn);
			return criteria.list();
		} finally{
			closeSession();
		}
	}
	
	private boolean isAllValue(String value){
		return "-1".equals(value);
	}
		
	private Criteria buildSearchCriteria(SearchToolPlanCriteria criteria) throws Exception{
		Criteria cr = session.createCriteria(ToolPlan.class);
		if(!StringUtils.isNullOrEmpty(criteria.getRatePeriodSequenceNumber())){
			cr.add(Restrictions.eq("pricePlan.pricePlanCode", criteria.getPricePlanCode()));
		}

		if (!isAllValue(criteria.getPricePlanStatus())){
			cr.add(Restrictions.eq("approveStatus", Integer.valueOf(criteria.getPricePlanStatus())));
		}

		if (!isAllValue(criteria.getOriginatingDestinationCode())){
			cr.add(Restrictions.eq("originatingDestinationCode", criteria.getOriginatingDestinationCode()));
		}
		
		if (!isAllValue(criteria.getTerminatingSatelliteCode())){
			cr.add(Restrictions.eq("terminatingSatelliteCode", criteria.getTerminatingSatelliteCode()));
		}
		
		if (!StringUtils.isNullOrEmpty(criteria.getRate())){
			cr.add(Restrictions.eq("rate", Double.valueOf(criteria.getRate())));
		}
		
		if (!isAllValue(criteria.getRateType())){
			cr.add(Restrictions.eq("rateType", criteria.getRateType()));
		}
		
		if (!isAllValue(criteria.getWeekDefinitionCode())){
			cr.add(Restrictions.eq("weekDefinitionCode", criteria.getWeekDefinitionCode()));
		}
		
		if (!StringUtils.isNullOrEmpty(criteria.getRatePeriodSequenceNumber())){
			cr.add(Restrictions.eq("ratePeriodSequenceNumber", Integer.valueOf(criteria.getRatePeriodSequenceNumber())));
		}
		
		if (!isAllValue(criteria.getTypeOfCall())){
			cr.add(Restrictions.eq("typeOfCall", criteria.getTypeOfCall()));
		}
		
		if (!isAllValue(criteria.getSpeacialTypeOfCall())){
			cr.add(Restrictions.eq("speacialTypeOfCall", criteria.getSpeacialTypeOfCall()));
		}
		
		if (!StringUtils.isNullOrEmpty(criteria.getRateCapSequenceNumber())){
			cr.add(Restrictions.eq("rateCapSequenceNumber", Integer.valueOf(criteria.getRateCapSequenceNumber())));
		}
		
		if (!isAllValue(criteria.getCustomerSpecificRatingCode())){
			cr.add(Restrictions.eq("customerSpecificRatingCode", criteria.getCustomerSpecificRatingCode()));
		}
		
		if (criteria.getTableEntryCreationDate() != null){
			cr.add(Restrictions.eq("tableEntryCreationDate", criteria.getTableEntryCreationDate()));
		}
		
		if (criteria.getTableEntryExpirationDate() != null){
			cr.add(Restrictions.eq("tableEntryExpirationDate", criteria.getTableEntryExpirationDate()));
		}
		
		if (!isAllValue(criteria.getRateTimeUnit())){
			cr.add(Restrictions.eq("rateTimeUnit", criteria.getRateTimeUnit()));
		}
		
		if (!StringUtils.isNullOrEmpty(criteria.getMessageLevelPercentDiscount())){
			cr.add(Restrictions.eq("messageLevelPercentDiscount", Double.valueOf(criteria.getMessageLevelPercentDiscount())));
		}
		
		if (!StringUtils.isNullOrEmpty(criteria.getCapThresholdCode())){
			cr.add(Restrictions.ilike("capThresholdCode", criteria.getCapThresholdCode()));
		}
		
		if (!StringUtils.isNullOrEmpty(criteria.getMinimumTime())){
			cr.add(Restrictions.eq("minimumTime", Integer.valueOf(criteria.getMinimumTime())));
		}
		
		if (!StringUtils.isNullOrEmpty(criteria.getIncrementTime())){
			cr.add(Restrictions.eq("incrementTime", Integer.valueOf(criteria.getIncrementTime())));
		}
		
		if (!isAllValue(criteria.getIncrementTimeUnit())){
			cr.add(Restrictions.eq("incrementTimeUnit", criteria.getIncrementTimeUnit()));
		}
		return cr;
	}

	@Override
	public long getTotalToolPlansBySearch(SearchToolPlanCriteria searchCriteria)
			throws Exception {
		try{
			session = sessionFactory.openSession();
			Criteria criteria = buildSearchCriteria(searchCriteria);
			return (Long)criteria.setProjection(Projections.rowCount()).uniqueResult();
		}finally{
			closeSession();
		}
	}
	
	@Override
	public void deleteToolPlan(int id) throws Exception {
		try{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Query query =session.createQuery("delete from ToolPlan p where p.ID = :id");
			query.setParameter("id", id);
			query.executeUpdate();
			tx.commit();
		}catch(Exception ex){
			tx.rollback();
			throw ex;
		}finally{
			closeSession();
		}
	}

	@Override
	public List<ToolPlan> getToolPlanList(String pricePlanCode)
			throws Exception {
		List<ToolPlan> toolPlans = Collections.EMPTY_LIST;
		String hql = "FROM ToolPlan tp WHERE tp.pricePlan.pricePlanCode = :pricePlanCode";
		try{
			session = sessionFactory.openSession();
			Query query = session.createQuery(hql);
			query.setParameter("pricePlanCode", pricePlanCode);
			toolPlans = query.list();
		}finally{
			closeSession();
		}
		return toolPlans;
	}
	
	public long getTotalToolPlanCount(String pricePlanCode) throws Exception {
		long totalRecordCount = 0;
		try{
			session = sessionFactory.openSession();
			String hql = "SELECT count(*) FROM ToolPlan WHERE pricePlan.pricePlanCode = :pricePlanCode";
			Query query = session.createQuery(hql);
			query.setParameter("pricePlanCode", pricePlanCode);
			totalRecordCount = (Long)query.uniqueResult();
		}finally{
			closeSession();
		}
		return totalRecordCount;
	}
	
	public List<ToolPlan> getToolPlanList(String pricePlanCode, int startIndex, int pageSize, String sortingColumn) throws Exception {
		List<ToolPlan> toolPlans = Collections.EMPTY_LIST;
		String hql = "FROM ToolPlan tp WHERE tp.pricePlan.pricePlanCode = :pricePlanCode";
		if (!StringUtils.isNullOrEmpty(sortingColumn)){
			hql +=  " ORDER BY tp." + sortingColumn;
		}else{
			hql +=  " ORDER BY tp.tableEntryCreationDate DESC";
		}
		
		try{
			session = sessionFactory.openSession();
			Query query = session.createQuery(hql);
			query.setParameter("pricePlanCode", pricePlanCode);
			query.setFirstResult(startIndex);
			query.setMaxResults(pageSize);
			toolPlans = query.list();
		}finally{
			closeSession();
		}
		return toolPlans;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ToolPlan> searchToolPricePlans( SearchToolPlanCriteria searchCriteria, int startIndex, int pageSize, String sorting) throws Exception {
		try {
			session = sessionFactory.openSession();
			Criteria criteria = buildSearchCriteria(searchCriteria);
			criteria.setFirstResult(startIndex);
			criteria.setMaxResults(pageSize);
			SearchUtils.addOrderIfNeeded(criteria, sorting);
			List<ToolPlan> result = null;
			result = criteria.list();
			return result;
		} finally{
			closeSession();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ToolPlan> exportSearchToolPricePlans( SearchToolPlanCriteria searchCriteria, String sorting) throws Exception {
		try {
			session = sessionFactory.openSession();
			Criteria criteria = buildSearchCriteria(searchCriteria);
			criteria.setFirstResult(1);
			criteria.setMaxResults((int) getTotalToolPricePlansForSearch(searchCriteria));
			SearchUtils.addOrderIfNeeded(criteria, sorting);
			List<ToolPlan> result = null;
			result = criteria.list();
			return result;
		} finally{
			closeSession();
		}
	}
	
	public long getTotalToolPricePlansForSearch(SearchToolPlanCriteria searchCriteria) throws Exception {
		try{
			session = sessionFactory.openSession();
			Criteria criteria = buildSearchCriteria(searchCriteria);
			return (Long)criteria.setProjection(Projections.rowCount()).uniqueResult();
		}finally{
			closeSession();
		}
	}
}
