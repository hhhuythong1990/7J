package com.sevenj.service;

import java.util.List;

import com.sevenj.model.History;
import com.sevenj.model.ToolPlan;

public interface ToolPlanHistoryService {
	public List<History> getHistories(int toolPlanId) throws Exception;
	public History getHistory(int historyId) throws Exception;
}
