package com.sevenj.service;

import java.util.List;

import com.sevenj.model.User;

public interface UserService {
	public List<User> getUserList(int startIndex, int pageSize,String sortingColumn) throws Exception;
	public void createUser(User user) throws Exception;
	public User getUser(String username) throws Exception;
}
