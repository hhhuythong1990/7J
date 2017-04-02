package com.sevenj.service.impl;

import java.util.List;

import com.sevenj.dal.dao.UserDAO;
import com.sevenj.dal.dao.impl.UserDAOImpl;
import com.sevenj.model.User;
import com.sevenj.service.UserService;

public class UserServiceImpl implements UserService{
	private UserDAO userDao;
	
	public UserServiceImpl(){
		userDao = new UserDAOImpl();
	}
	public List<User> getUserList(int startIndex, int pageSize,String sortingColumn) throws Exception{
		return userDao.getUserList(startIndex, pageSize, sortingColumn);
	}
	public void createUser(User user) throws Exception {
		userDao.createEntity(user);
	}
	public User getUser(String username) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
