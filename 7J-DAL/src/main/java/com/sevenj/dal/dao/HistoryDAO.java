package com.sevenj.dal.dao;

import java.util.List;

import com.sevenj.model.History;

public interface HistoryDAO  extends BaseDAO<History>{
	public List<History> getHistories(int toolPlanId) throws Exception;
}
