package com.sevenj.dal.dao;
import com.sevenj.model.Comment;

public interface CommentDAO extends BaseDAO<Comment>{
	 public java.util.List<Comment> getComments(int toolPlanId) throws Exception;
}
