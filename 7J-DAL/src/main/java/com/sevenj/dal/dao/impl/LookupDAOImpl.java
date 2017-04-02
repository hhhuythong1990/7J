package com.sevenj.dal.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import com.sevenj.dal.dao.LookupDAO;
import com.sevenj.model.Lookup;

public class LookupDAOImpl extends BaseDAOImpl<Lookup> implements LookupDAO {

	@Override
	public List<Lookup> getDataList(String typeName) throws Exception {
		String hql = "FROM Lookup where typeName = :typeNameValue";
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("typeNameValue", typeName);
		return executeAndGetList(hql, parameters);
	}
	
	@SuppressWarnings("unchecked")
	public Map<Integer,String> getDataMap() throws Exception{
		String hql = "from Lookup ";
		session = sessionFactory.openSession();
		Query query = session.createQuery(hql);
		List<Lookup> listResult=query.list();
		Map<Integer,String> map= new HashMap<Integer, String>();
		for(Lookup lookup : listResult)
		{
			map.put(lookup.getId(),lookup.getDescription());
		}
		return map;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Lookup getObjLookupByDescription(String description) throws Exception{
		try{
			session = sessionFactory.openSession();
			String hql = "FROM Lookup where description = :Description";
			Query query = session.createQuery(hql);
			query.setParameter("Description", description);
			List<Lookup> listResult=query.list();
			if(listResult != null&& listResult.size() > 0 ){
				return listResult.get(0);
			}
		}finally{
			closeSession();
		}
		return null;
	}
}
