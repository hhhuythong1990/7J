package com.sevenj.dal.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.sevenj.dal.dao.BaseDAO;
import com.sevenj.dal.util.HibernateUtil;

public class BaseDAOImpl<T> implements BaseDAO<T>{
	protected Session session = null;
	protected Transaction tx = null;
	protected SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	
	@SuppressWarnings("unchecked")
	public List<T> executeAndGetList(String hql, Map<String, Object> parameters) throws Exception {
		List<T> result = null;
		try{
			session = sessionFactory.openSession();
			Query query = session.createQuery(hql);
			if (parameters != null){
				for(Entry<String,Object> entry: parameters.entrySet()){
					query.setParameter(entry.getKey(), entry.getValue());
				}
			}
			result = query.list();
		}finally{
			closeSession();
		}
		
		return result;
	}
	
	protected void closeSession() throws Exception{
		if (session != null && session.isOpen()){
			session.close();
		}
	}

	@Override
	public List<T> getListByPrimaryKeys(Class<T> clazz, String pkProperty, List<?> keys)
			throws Exception {
		List<T> result = null;
		try{
			session = sessionFactory.openSession();
		    result = session.createCriteria(clazz).add(Restrictions.in(pkProperty, keys)).list();
		}finally{
			closeSession();
		}
		return result;
	}

	@Override
	public void createEntitiesBatch(List<T> listEntities) throws Exception {
		try{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			for (T entity : listEntities) {
				session.save(entity);
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
	public T getEntityById(Class clazz, Object id) throws Exception {
		T result = null;
		try{
			session = sessionFactory.openSession();
			result= (T)session.get(clazz, (Serializable)id);
		}finally{
			closeSession();
		}
		return result;
	}

	@Override
	public void update(T entity) throws Exception {
		try{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(entity);
			tx.commit();
		}catch(Exception ex){
			tx.rollback();
			throw ex;
		}finally{
			closeSession();
		}
	}

	@Override
	public void updateEntityBatch(List<T> listEntities) throws Exception {
		try{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			for (T entity : listEntities) {
				session.saveOrUpdate(entity);
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
	public List<T> getList(Class<T> clazz) throws Exception {
		List<T> result = null;
		try{
			session = sessionFactory.openSession();
		    result = session.createCriteria(clazz).list();
		}finally{
			closeSession();
		}
		return result;
	}

	@Override
	public long getCount(String hql) throws Exception {
		long count = 0;
		try{
			session = sessionFactory.openSession();
			Query query = session.createQuery(hql);
			count = (Long)query.uniqueResult();
		}finally{
			closeSession();
		}
		return count;
	}

	@Override
	public void createEntity(T entity) throws Exception {
		try{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.save(entity);
			tx.commit();
		}catch(Exception ex){
			tx.rollback();
			throw ex;
		}finally{
			closeSession();
		}
	}
}
