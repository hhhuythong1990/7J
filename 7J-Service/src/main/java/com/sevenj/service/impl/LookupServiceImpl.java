package com.sevenj.service.impl;

import java.util.List;
import java.util.Map;

import com.sevenj.dal.dao.LookupDAO;
import com.sevenj.dal.dao.impl.LookupDAOImpl;
import com.sevenj.model.Lookup;
import com.sevenj.service.LookupService;

public class LookupServiceImpl implements LookupService {
	private LookupDAO dao;

	public LookupServiceImpl() {
		this.dao = new LookupDAOImpl();
	}

	public List<Lookup> getDataList(String typeName) throws Exception {
		return dao.getDataList(typeName);
	}
	public Map<Integer,String> getDataMap() throws Exception{
		return dao.getDataMap();
	}
	
	public Lookup getObjLookupByDescription(String description) throws Exception{
		return dao.getObjLookupByDescription(description);
	}

	@Override
	public void addNewLookup(Lookup lookup) throws Exception {
		dao.createEntity(lookup);
		lookup = dao.getObjLookupByDescription(lookup.getDescription());
	}
}
