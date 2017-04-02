package com.sevenj.dal.dao;

import java.util.List;

import com.sevenj.model.User;

public interface UserDAO extends BaseDAO<User>{
	public User getUser(String username, String password) throws Exception;
	public List<User> getUserList(int startIndex, int pageSize,String sortingColumn) throws Exception;
}
