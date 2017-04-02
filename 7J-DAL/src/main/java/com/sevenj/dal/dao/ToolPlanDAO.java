package com.sevenj.dal.dao;

import java.util.List;

import com.sevenj.model.AdditionalPricePlan;
import com.sevenj.model.Comment;
import com.sevenj.model.SearchToolPlanCriteria;
import com.sevenj.model.ToolPlan;
import com.sevenj.model.ToolPlanStatus;

public interface ToolPlanDAO extends BaseDAO<ToolPlan>{
  public List<ToolPlan> getToolPlanList(String pricePlanCode,int status) throws Exception;
  public long getTotalToolPlanCount(String pricePlanCode) throws Exception;
  public List<ToolPlan> getToolPlanList(String pricePlanCode) throws Exception;
  public List<ToolPlan> getToolPlanList(String pricePlanCode, int startIndex, int pageSize, String sortingColumn) throws Exception;
  public List<ToolPlan> getToolPlanList(String pricePlanCode,ToolPlanStatus status, int startIndex, int pageSize, String sortingColumn) throws Exception;
  public long getTotalToolPlanCount(String pricePlanCode, ToolPlanStatus status) throws Exception;
  public void createToolPlan(ToolPlan toolPlan) throws Exception;
  public void updateToolPlanStatus(String[] ids, ToolPlanStatus status) throws Exception;
  public List<ToolPlan> getToolPlanForImport(String pricePlanCode) throws Exception;
  public long getNumberOfToolPlansForImport(String pricePlanCode)throws Exception;
  public void updateToolPlan(ToolPlan toolPlan, String user) throws Exception;
  public void updateToolPlans(List<ToolPlan> toolPlans, String user) throws Exception;
  public List<ToolPlan> searchToolPlans(SearchToolPlanCriteria criteria, int startIndex, int pageSize, String sortingColumn) throws Exception;
  public long getTotalToolPlansBySearch(SearchToolPlanCriteria searchCriteria) throws Exception;
  public void deleteToolPlan(int id) throws Exception;
  public List<ToolPlan> searchToolPricePlans( SearchToolPlanCriteria searchCriteria, int startIndex, int pageSize, String sorting) throws Exception;
  public long getTotalToolPricePlansForSearch(SearchToolPlanCriteria searchCriteria) throws Exception;
  public List<ToolPlan> exportSearchToolPricePlans( SearchToolPlanCriteria searchCriteria, String sorting) throws Exception;
}
