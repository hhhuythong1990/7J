package com.sevenj.dal.dao.impl;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Query;

import com.mysql.jdbc.StringUtils;
import com.sevenj.dal.dao.PricePlanDAO;
import com.sevenj.model.PricePlan;

public class PricePlanDAOImpl extends BaseDAOImpl<PricePlan> implements PricePlanDAO{

	public List<PricePlan> getPricePlansByProject(String projectId,int startIndex, int pageSize, String sortingColumn)
			throws Exception {
		List<PricePlan> result = null;
		try{
			session = sessionFactory.openSession();
			String hql = "FROM PricePlan p where p.project.projectId = :projectId";
			if (!StringUtils.isNullOrEmpty(sortingColumn)){
				hql += " Order by " + sortingColumn;
			}
			Query query = session.createQuery(hql);
			query.setParameter("projectId", projectId);
			query.setFirstResult(startIndex);
			query.setMaxResults(pageSize);
			result = query.list();
		}finally{
			closeSession();
		}
		return result;
	}

	public void insertPricePlan(PricePlan pricePlan) throws Exception {
		try{
			Calendar cal = Calendar.getInstance();
			pricePlan.setCreationDate(cal.getTime());
			
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.save(pricePlan);
			tx.commit();
		}catch(Exception ex){
			tx.rollback();
			throw ex;
		}finally{
			closeSession();
		}
	}

	public PricePlan getPricePlan(String pricePlanCode) throws Exception {
		PricePlan pricePlan = null;
		try{
			session = sessionFactory.openSession();
			pricePlan = (PricePlan)session.get(PricePlan.class, pricePlanCode);
		}finally{
			closeSession();
		}
		return pricePlan;
	}

	public long getTotalPricePlanCount(String projectId) throws Exception {
		long totalRecordCount = 0;
		try{
			session = sessionFactory.openSession();
			String hql = "select count(*) from PricePlan where project.projectId = :projectId";
			Query query = session.createQuery(hql);
			query.setParameter("projectId", projectId);
			totalRecordCount = ((Long)query.iterate().next()).longValue();
		}finally{
			closeSession();
		}
		return totalRecordCount;
	}

	public void updatePricePlan(PricePlan pricePlan) throws Exception {
		try{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.update(pricePlan);
			tx.commit();
		}catch(Exception ex){
			tx.rollback();
			throw ex;
		}finally{
			closeSession();
		}
	}

	public void deletePricePlan(String pricePlanCode) throws Exception {
		try{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Query query =session.createQuery("delete from PricePlan p where p.pricePlanCode = :pricePlanCode");
			query.setParameter("pricePlanCode", pricePlanCode);
			query.executeUpdate();
			tx.commit();
		}catch(Exception ex){
			tx.rollback();
			throw ex;
		}finally{
			closeSession();
		}
	}
	public List<PricePlan> getPendingPricePlansByProject(String projectId,int startIndex, int pageSize, String sortingColumn)
			throws Exception {
		List<PricePlan> result = null;
		try{
			session = sessionFactory.openSession();
			String hql = "select distinct tp.pricePlan from ToolPlan tp where tp.pricePlan.project.projectId = :projectId and tp.approveStatus=0";
			if (!StringUtils.isNullOrEmpty(sortingColumn)){
				hql += " Order by tp.pricePlan." + sortingColumn;
			}
			Query query = session.createQuery(hql);
			query.setParameter("projectId", projectId);
			query.setFirstResult(startIndex);
			query.setMaxResults(pageSize);
			result = query.list();
		}finally{
			closeSession();
		}
		return result;
	}

	@Override
	public long getCountForPendingPricePlans(String projectId) throws Exception {
		long count = 0;
		String hql = "select count(distinct tp.pricePlan) from ToolPlan tp where tp.pricePlan.project.projectId = :projectId and tp.approveStatus=0";
		try{
			session = sessionFactory.openSession();
			Query query = session.createQuery(hql);
			query.setParameter("projectId", projectId);
			count = (long)query.uniqueResult();
		}finally{
			closeSession();
		}
		return count;
	}

}
