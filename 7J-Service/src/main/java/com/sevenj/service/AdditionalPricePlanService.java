package com.sevenj.service;

import java.util.List;

import com.sevenj.model.AdditionalPricePlan;
import com.sevenj.model.SearchAdditionalPricePlanCriteria;

public interface AdditionalPricePlanService {
  public List<AdditionalPricePlan> getAdditionalPricePlanList() throws Exception;
  public void createAdditionalPricePlan(AdditionalPricePlan additionalPricePlan ) throws Exception;
  public  AdditionalPricePlan searchAdditonalPricePlan(String pricePlanCode) throws Exception;
  public void updateAdditonalPricePlan(AdditionalPricePlan additionalPricePlan) throws Exception;
  public int getIdByDescription(String columValue)throws Exception;
  public String getTypeNameByDescription(String columValue)throws Exception;
  public List<AdditionalPricePlan> getAddittionalPricePlansByColum(String columName,int columValue,int startIndex, int pageSize, String sortingColumn)throws Exception ;
  public List<AdditionalPricePlan> getAdditionalPricePlanList(String jtSorting) throws Exception;
  public List<AdditionalPricePlan> searchAdditionalPricePlans( SearchAdditionalPricePlanCriteria criteria, int jtStartIndex, int jtPageSize, String jtSorting) throws Exception;
  public long getTotalAdditionalPricePlansForSearch( SearchAdditionalPricePlanCriteria criteria)throws Exception;
  public List<AdditionalPricePlan> searchAdditionalPricePlans( SearchAdditionalPricePlanCriteria criteria, String sorting) throws Exception;
  public List<AdditionalPricePlan> getAdditionalPricePlanList(int jtStartIndex, int jtPageSize, String jtSorting) throws Exception;
  public long getTotalAdditionalPricePlan() throws Exception;
}
