package com.sevenj.dal.dao;

import java.util.List;

import com.sevenj.model.PricePlan;

public interface PricePlanDAO extends BaseDAO<PricePlan>{
   public List<PricePlan> getPricePlansByProject(String projectId, int startIndex, int pageSize,String sortingColumn) throws Exception;
   public void insertPricePlan(PricePlan pricePlan) throws Exception;
   public PricePlan getPricePlan(String pricePlanCode) throws Exception;
   public long getTotalPricePlanCount(String projectId) throws Exception;
   public void updatePricePlan(PricePlan pricePlan) throws Exception;
   public void deletePricePlan(String pricePlanCode) throws Exception;
   public List<PricePlan> getPendingPricePlansByProject(String projectId,int startIndex, int pageSize, String sortingColumn) throws Exception ;
   public long getCountForPendingPricePlans(String projectId) throws Exception;
}
