package com.sevenj.dal.dao.impl;



import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.mysql.jdbc.StringUtils;
import com.sevenj.dal.dao.AdditionalPricePlanDAO;
import com.sevenj.dal.util.SearchUtils;
import com.sevenj.model.AdditionalPricePlan;
import com.sevenj.model.SearchAdditionalPricePlanCriteria;
import com.sevenj.model.ToolPlan;

public class AdditionalPricePlanDAOImpl extends BaseDAOImpl<AdditionalPricePlan> implements AdditionalPricePlanDAO{
	
	public void insertAdditionalPricePlan(AdditionalPricePlan additionalPricePlan) throws Exception {
		try{
				Calendar cal = Calendar.getInstance();
				additionalPricePlan.setCreationDate(cal.getTime());
				session = sessionFactory.openSession();
				tx = session.beginTransaction();
				session.save(additionalPricePlan);
				tx.commit();
			}catch(Exception ex){
			tx.rollback();
			throw ex;
		}finally{
			closeSession();
		}
	}
	
	public  AdditionalPricePlan searchAdditonalPricePlan(String pricePlanCode) throws Exception{
		AdditionalPricePlan additionalPricePlan=null;
		try{
			session = sessionFactory.openSession();
			additionalPricePlan= (AdditionalPricePlan)session.get(AdditionalPricePlan.class, pricePlanCode);
		}finally{
			closeSession();
		}
		return additionalPricePlan;
	}
	
	public void updateAdditonalPricePlan(AdditionalPricePlan additionalPricePlan) throws Exception{
		try{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.update(additionalPricePlan);
			tx.commit();
		}catch(Exception ex){
			tx.rollback();
			throw ex;
		}finally{
			closeSession();
		}
		
	}
	//search
	
	public List<AdditionalPricePlan> getAddittionalPricePlansByColum(String columName,int columValue,int startIndex, int pageSize, String sortingColumn)
			throws Exception {
		List<AdditionalPricePlan> result = null;
		try{
			session = sessionFactory.openSession();
			//String columNames="";
			String hql = "from  AdditionalPricePlan  where prioprityCode =:columValue ";
			String newHql=hql.replace("prioprityCode",columName);
			Query query = session.createQuery(newHql);
			query.setParameter("columValue",columValue);
			query.setFirstResult(startIndex);
			query.setMaxResults(pageSize);
			result = query.list();
		}finally{
			closeSession();
		}
		return result;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AdditionalPricePlan> getAdditionalPricePlanList(String sortName) throws Exception{
		List<AdditionalPricePlan> list;
		Session session = null;
		try{
			session = sessionFactory.openSession();
			String hql = "FROM AdditionalPricePlan";
			if (!StringUtils.isNullOrEmpty(sortName)){
				hql += " Order by " + sortName;  
			}
			list = (List<AdditionalPricePlan>)session.createQuery(hql).list();
		}finally{
			closeSession();
		}
		return list;
	}
	
	public int getIdByDescription(String columValue)
			throws Exception {
		int result = 0;
		try{
			session = sessionFactory.openSession();
			String hql = "select lk.id from Lookup lk where lk.description like :col ";
			Query query = session.createQuery(hql);
			query.setParameter("col","%"+columValue+"%");
			result=(Integer)query.list().get(0);
		}finally{
			closeSession();
		}
		return result;
	}
	public String getTypeNameByDescription(String columValue)
			throws Exception {
		String result = "";
		try{
			session = sessionFactory.openSession();
			String hql = "select lk.typeName from Lookup lk where lk.description like :col ";
			Query query = session.createQuery(hql);
			query.setParameter("col","%"+columValue+"%");
			result=(String)query.list().get(0);
		}finally{
			closeSession();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AdditionalPricePlan> searchAdditionalPricePlans( SearchAdditionalPricePlanCriteria searchCriteria, int startIndex, int pageSize, String sorting) throws Exception {
		try {
			session = sessionFactory.openSession();
			Criteria criteria = buildSearchCriteria(searchCriteria);
			criteria.setFirstResult(startIndex);
			criteria.setMaxResults(pageSize);
			SearchUtils.addOrderIfNeeded(criteria, sorting);
			List<AdditionalPricePlan> result = null;
			result = criteria.list();
			return result;
		} finally{
			closeSession();
		}
	}

	public long getTotalAdditionalPricePlansForSearch(SearchAdditionalPricePlanCriteria searchCriteria) throws Exception {
		try{
			session = sessionFactory.openSession();
			Criteria criteria = buildSearchCriteria(searchCriteria);
			return (Long)criteria.setProjection(Projections.rowCount()).uniqueResult();
		}finally{
			closeSession();
		}
	}
	
	private boolean isAllValue(String value){
		return "-1".equals(value);
	}
	
	private Criteria buildSearchCriteria(SearchAdditionalPricePlanCriteria criteria) throws Exception{
		Criteria cr = session.createCriteria(AdditionalPricePlan.class);
		
		if (!StringUtils.isNullOrEmpty(criteria.getPricePlanCode())){
			cr.add(Restrictions.eq("pricePlanCode", criteria.getPricePlanCode()));
		}
		
		if (!StringUtils.isNullOrEmpty(criteria.getPricePlanName())){
			cr.add(Restrictions.eq("pricePlanName", criteria.getPricePlanName()));
		}
		
		if (!isAllValue(criteria.getPlanType())){
			cr.add(Restrictions.eq("planType", Integer.valueOf(criteria.getPlanType())));
		}
		
		if (!isAllValue(criteria.getAccountLevel())){
			cr.add(Restrictions.eq("accountLevel", Integer.valueOf(criteria.getAccountLevel())));
		}
		
		if (!isAllValue(criteria.getLineLevel())){
			cr.add(Restrictions.eq("lineLevel", Integer.valueOf(criteria.getLineLevel())));
		}
		
		if (!isAllValue(criteria.getPrioprityCode())){
			cr.add(Restrictions.eq("prioprityCode", Integer.valueOf(criteria.getPrioprityCode())));
		}
		
		if (!StringUtils.isNullOrEmpty(criteria.getUsoc())){
			cr.add(Restrictions.eq("usoc", criteria.getUsoc()));
		}
		
		if (!isAllValue(criteria.getIsToolPricingPlan())){
			cr.add(Restrictions.eq("isToolPricingPlan", Integer.valueOf(criteria.getIsToolPricingPlan())));
		}
		
		if (!isAllValue(criteria.getNoOverseaDest())){
			cr.add(Restrictions.eq("noOverseaDest", Integer.valueOf(criteria.getNoOverseaDest())));
		}
		
		if (!isAllValue(criteria.getT28OverseaDest())){
			cr.add(Restrictions.eq("t28OverseaDest", Integer.valueOf(criteria.getT28OverseaDest())));
		}
		if (!isAllValue(criteria.getT29OverseaDest())){
			cr.add(Restrictions.eq("t29OverseaDest", Integer.valueOf(criteria.getT29OverseaDest())));
		}
		if (!isAllValue(criteria.getBlockOfTime())){
			cr.add(Restrictions.eq("blockOfTime", Integer.valueOf(criteria.getBlockOfTime())));
		}
		if (!isAllValue(criteria.getDollarCap())){
			cr.add(Restrictions.eq("dollarCap", Integer.valueOf(criteria.getDollarCap())));
		}
		if (!isAllValue(criteria.getBillMinium())){
			cr.add(Restrictions.eq("billMinium", Integer.valueOf(criteria.getBillMinium())));
		}
		if (!isAllValue(criteria.getQuantity())){
			cr.add(Restrictions.eq("quantity", Integer.valueOf(criteria.getQuantity())));
		}
		if (!isAllValue(criteria.getShortHaulRule())){
			cr.add(Restrictions.eq("shortHaulRule", Integer.valueOf(criteria.getShortHaulRule())));
		}
		if (!isAllValue(criteria.getCostCompareOnRule())){
			cr.add(Restrictions.eq("costCompareOnRule", Integer.valueOf(criteria.getCostCompareOnRule())));
		}
		if (!isAllValue(criteria.getWithSurcharges())){
			cr.add(Restrictions.eq("withSurcharges", Integer.valueOf(criteria.getWithSurcharges())));
		}
		if (!isAllValue(criteria.getMultiRateSchedule())){
			cr.add(Restrictions.eq("multiRateSchedule", Integer.valueOf(criteria.getMultiRateSchedule())));
		}
		if (!isAllValue(criteria.getAirTime())){
			cr.add(Restrictions.eq("airTime", Integer.valueOf(criteria.getAirTime())));
		}
		if (!isAllValue(criteria.getVolumeDiscount())){
			cr.add(Restrictions.eq("volumeDiscount", Integer.valueOf(criteria.getVolumeDiscount())));
		}
		if (!isAllValue(criteria.getContractDiscount())){
			cr.add(Restrictions.eq("contractDiscount", Integer.valueOf(criteria.getContractDiscount())));
		}
		if (!isAllValue(criteria.getIncrements60())){
			cr.add(Restrictions.eq("increments60", Integer.valueOf(criteria.getIncrements60())));
		}
		if (!isAllValue(criteria.getIncrements60or30())){
			cr.add(Restrictions.eq("increments60or30", Integer.valueOf(criteria.getIncrements60or30())));
		}
		if (!isAllValue(criteria.getIncrements30())){
			cr.add(Restrictions.eq("increments30", Integer.valueOf(criteria.getIncrements30())));
		}
		if (!isAllValue(criteria.getIncrements10())){
			cr.add(Restrictions.eq("increments10", Integer.valueOf(criteria.getIncrements10())));
		}
		if (!isAllValue(criteria.getIncrements1())){
			cr.add(Restrictions.eq("increments1", Integer.valueOf(criteria.getIncrements1())));
		}
		if (!isAllValue(criteria.getPeakOffPeak())){
			cr.add(Restrictions.eq("peakOffPeak", Integer.valueOf(criteria.getPeakOffPeak())));
		}
		if (!isAllValue(criteria.getIs247())){
			cr.add(Restrictions.eq("is247", Integer.valueOf(criteria.getIs247())));
		}
		if (!isAllValue(criteria.getThd1())){
			cr.add(Restrictions.eq("thd1", Integer.valueOf(criteria.getThd1())));
		}
		if (!isAllValue(criteria.getProvincialOntarioOnly())){
			cr.add(Restrictions.eq("provincialOntarioOnly", Integer.valueOf(criteria.getProvincialOntarioOnly())));
		}
		if (!isAllValue(criteria.getProvincialQuebecOnly())){
			cr.add(Restrictions.eq("provincialQuebecOnly", Integer.valueOf(criteria.getProvincialQuebecOnly())));
		}
		
		return cr;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AdditionalPricePlan> searchAdditionalPricePlans( SearchAdditionalPricePlanCriteria searchCriteria, String sorting) throws Exception {
		try{
			session = sessionFactory.openSession();
			Criteria criteria = buildSearchCriteria(searchCriteria);
			SearchUtils.addOrderIfNeeded(criteria, sorting);
			List<AdditionalPricePlan> result = null;
			result = criteria.list();
			return result;
		} finally{
			closeSession();
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<AdditionalPricePlan> getAdditionalPricePlanList(int startIndex, int pageSize, String sortingColumn) throws Exception {
		List<AdditionalPricePlan> additionalPricePlan = Collections.EMPTY_LIST;
		String hql = "FROM AdditionalPricePlan";
		if (!StringUtils.isNullOrEmpty(sortingColumn)){
			hql +=  " ORDER BY " + sortingColumn;
		}
		try{
			session = sessionFactory.openSession();
			Query query = session.createQuery(hql);
			query.setFirstResult(startIndex);
			query.setMaxResults(pageSize);
			additionalPricePlan = query.list();
		}finally{
			closeSession();
		}
		return additionalPricePlan;
	}

	public long getTotalAdditionalPricePlan() throws Exception {
		long totalRecordCount = 0;
		try{
			session = sessionFactory.openSession();
			String hql = "SELECT count(*) FROM AdditionalPricePlan";
			Query query = session.createQuery(hql);
			totalRecordCount = (Long)query.uniqueResult();
		}finally{
			closeSession();
		}
		return totalRecordCount;
	}
}
