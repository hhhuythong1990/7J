package com.sevenj.service.impl;

import java.util.List;

import com.sevenj.dal.dao.AdditionalPricePlanDAO;
import com.sevenj.dal.dao.impl.AdditionalPricePlanDAOImpl;
import com.sevenj.model.AdditionalPricePlan;
import com.sevenj.model.SearchAdditionalPricePlanCriteria;
import com.sevenj.service.AdditionalPricePlanService;

public class AdditionalPricePlanServiceImpl implements AdditionalPricePlanService{
    private AdditionalPricePlanDAO additionalPricePlanDAO;
    public AdditionalPricePlanServiceImpl(){
    	additionalPricePlanDAO = new AdditionalPricePlanDAOImpl();
    }
	public List<AdditionalPricePlan> getAdditionalPricePlanList()
			throws Exception {
		return additionalPricePlanDAO.getList(AdditionalPricePlan.class);
	}
	public void createAdditionalPricePlan(AdditionalPricePlan additionalPricePlan) throws Exception {
		additionalPricePlanDAO.insertAdditionalPricePlan(additionalPricePlan);
	}
	public  AdditionalPricePlan searchAdditonalPricePlan(String pricePlanCode) throws Exception
	{
		return additionalPricePlanDAO.searchAdditonalPricePlan(pricePlanCode);
	}
	public void updateAdditonalPricePlan(AdditionalPricePlan additionalPricePlan) throws Exception{
		additionalPricePlanDAO.updateAdditonalPricePlan(additionalPricePlan);
	}
	public List<AdditionalPricePlan> getAddittionalPricePlansByColum(String columName,int columValue,int startIndex, int pageSize, String sortingColumn)throws Exception  {
		return additionalPricePlanDAO.getAddittionalPricePlansByColum(columName, columValue, startIndex, pageSize, sortingColumn);
	}
	
	public int getIdByDescription(String columValue)throws Exception 
	{
		return  additionalPricePlanDAO.getIdByDescription(columValue);
	}
	public String getTypeNameByDescription(String columValue)throws Exception {
		return additionalPricePlanDAO.getTypeNameByDescription(columValue);
	}
	
	public List<AdditionalPricePlan> getAdditionalPricePlanList(String sortName)throws Exception  {
		return additionalPricePlanDAO.getAdditionalPricePlanList(sortName);
	}

	public List<AdditionalPricePlan> searchAdditionalPricePlans( SearchAdditionalPricePlanCriteria criteria, int startIndex, int pageSize, String sorting) throws Exception {
		return additionalPricePlanDAO.searchAdditionalPricePlans( criteria, startIndex, pageSize, sorting);
	}
	public long getTotalAdditionalPricePlansForSearch(SearchAdditionalPricePlanCriteria criteria) throws Exception {
		return additionalPricePlanDAO.getTotalAdditionalPricePlansForSearch(criteria);
	}
	
	public List<AdditionalPricePlan> searchAdditionalPricePlans( SearchAdditionalPricePlanCriteria criteria, String sorting) throws Exception {
		return additionalPricePlanDAO.searchAdditionalPricePlans( criteria, sorting );
	}
	
	public List<AdditionalPricePlan> getAdditionalPricePlanList(int jtStartIndex, int jtPageSize, String jtSorting) throws Exception {
		return additionalPricePlanDAO.getAdditionalPricePlanList(jtStartIndex, jtPageSize, jtSorting);
	}
	
	public long getTotalAdditionalPricePlan() throws Exception {
		return additionalPricePlanDAO.getTotalAdditionalPricePlan();
	}
}
