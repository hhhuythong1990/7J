package com.sevenj.dal.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sevenj.dal.dao.HistoryDAO;
import com.sevenj.model.History;

public class HistoryDAOImpl extends BaseDAOImpl<History> implements HistoryDAO{
	@Override
	public List<History> getHistories(int toolPlanId) throws Exception {
		String hql="FROM History h where h.toolPlan.ID = :toolPlanId";
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("toolPlanId", toolPlanId);
		return executeAndGetList(hql, parameters);
	}
}
