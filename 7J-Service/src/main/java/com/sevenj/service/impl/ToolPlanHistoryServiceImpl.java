package com.sevenj.service.impl;

import java.util.List;

import com.sevenj.dal.dao.HistoryDAO;
import com.sevenj.dal.dao.impl.HistoryDAOImpl;
import com.sevenj.model.History;
import com.sevenj.service.ToolPlanHistoryService;

public class ToolPlanHistoryServiceImpl implements ToolPlanHistoryService{
	private HistoryDAO historyDAO;
	
	public ToolPlanHistoryServiceImpl(){
		historyDAO = new HistoryDAOImpl();
	}
	public List<History> getHistories(int toolPlanId) throws Exception {
		return historyDAO.getHistories(toolPlanId);
	}
	public History getHistory(int historyId) throws Exception {
		History history = historyDAO.getEntityById(History.class, historyId);
		return history;
	}
}
