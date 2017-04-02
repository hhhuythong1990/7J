package com.sevenj.dal.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sevenj.dal.dao.CommentDAO;
import com.sevenj.model.Comment;

public class CommentDAOImpl extends BaseDAOImpl<Comment> implements CommentDAO{

	@Override
	public List<Comment> getComments(int toolPlanId) throws Exception {
		String hql = "FROM Comment c where c.toolPlan.ID = :toolPlanId ORDER BY c.commentDate ASC";
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("toolPlanId", toolPlanId);
		return executeAndGetList(hql, parameters);
	}
}
