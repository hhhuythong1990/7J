package com.sevenj.service.impl;

import java.util.List;

import com.sevenj.dal.dao.BaseDAO;
import com.sevenj.dal.dao.impl.BaseDAOImpl;
import com.sevenj.model.Destination;
import com.sevenj.service.LookupDataService;

public class LookupDataServiceImpl implements LookupDataService{
    private BaseDAO<Destination> dao;
    
    public LookupDataServiceImpl(){
    }
    
	public List<Destination> getDestinationList() throws Exception {
		dao = new BaseDAOImpl<Destination>();
		String hql = "FROM Destination d Order by d.sortOrder asc";
		return dao.executeAndGetList(hql, null);
	}

	public Destination getDestinationById(String id) throws Exception {
		return dao.getEntityById(Destination.class, id);
	}
}
