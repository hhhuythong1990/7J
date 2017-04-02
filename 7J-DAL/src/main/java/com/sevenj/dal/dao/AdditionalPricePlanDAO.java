package com.sevenj.dal.dao;

import java.util.List;

import com.sevenj.model.AdditionalPricePlan;
import com.sevenj.model.SearchAdditionalPricePlanCriteria;

public interface AdditionalPricePlanDAO extends BaseDAO<AdditionalPricePlan>{
	public void insertAdditionalPricePlan(AdditionalPricePlan additionalPricePlan) throws Exception;
	public  AdditionalPricePlan searchAdditonalPricePlan(String pricePlanCode) throws Exception;
	public void updateAdditonalPricePlan(AdditionalPricePlan additionalPricePlan) throws Exception;
	public int getIdByDescription(String columValue)throws Exception ;
	public List<AdditionalPricePlan> getAddittionalPricePlansByColum(String columName,int columValue,int startIndex, int pageSize, String sortingColumn)throws Exception  ;
	public String getTypeNameByDescription(String columValue)throws Exception ;
	public List<AdditionalPricePlan> getAdditionalPricePlanList(String sortName) throws Exception;
	public List<AdditionalPricePlan> searchAdditionalPricePlans( SearchAdditionalPricePlanCriteria criteria, int startIndex, int pageSize, String sorting) throws Exception;
	public long getTotalAdditionalPricePlansForSearch( SearchAdditionalPricePlanCriteria criteria) throws Exception;
	List<AdditionalPricePlan> getAdditionalPricePlanList(int startIndex, int pageSize, String sortingColumn) throws Exception;
	public long getTotalAdditionalPricePlan() throws Exception;
	List<AdditionalPricePlan> searchAdditionalPricePlans( SearchAdditionalPricePlanCriteria searchCriteria, String sorting)throws Exception;
}
