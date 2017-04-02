package com.sevenj.dal.dao.impl;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.mysql.jdbc.StringUtils;
import com.sevenj.dal.dao.DestinationDAO;
import com.sevenj.model.Destination;

public class DestinationDAOImpl extends BaseDAOImpl<Destination> implements DestinationDAO{

	@Override
	public List<Destination> getAllDestination(String sortName)
			throws Exception {
		// TODO Auto-generated method stub
		List<Destination> destinations;
		Session session = null;
		try{
			session = sessionFactory.openSession();
			String hql = "FROM Destination";
			if (!StringUtils.isNullOrEmpty(sortName)){
				hql += " Order by " + sortName;  
			}
			destinations = (List<Destination>)session.createQuery(hql).list();
		}finally{
			closeSession();
		}
		return destinations;
	}

	@Override
	public void insertDestination(Destination destination) throws Exception {
		// TODO Auto-generated method stub
		try{			
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.save(destination);
			tx.commit();
		}catch(Exception ex){
			tx.rollback();
			throw ex;
		}finally{
			closeSession();
		}
	}

	@Override
	public void deleteDestination(String destinationID) throws Exception {
		// TODO Auto-generated method stub
		try{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Query query =session.createQuery("delete from Destination d where d.destinationCode = :destinationCode");
			query.setParameter("destinationCode", destinationID);
			query.executeUpdate();
			tx.commit();
		}catch(Exception e){
			tx.rollback();
			throw e;
		}finally{
			closeSession();
		}
	}

	@Override
	public void updateDestination(Destination destination) throws Exception {
		// TODO Auto-generated method stub
		try{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.update(destination);
			tx.commit();
		}catch(Exception e){
			tx.rollback();
			throw e;
		}finally{
			closeSession();
		}
	}

	@Override
	public Destination getDestination(String destinationID) throws Exception {
		// TODO Auto-generated method stub
		Destination destination = null;
		try{
			session = sessionFactory.openSession();
			destination = (Destination)session.get(Destination.class, destinationID);
		}finally{
			closeSession();
		}
		return destination;
	}

}
