package com.sevenj.service.impl;

import java.util.List;

import com.sevenj.dal.dao.UserDAO;
import com.sevenj.dal.dao.impl.UserDAOImpl;
import com.sevenj.model.User;
import com.sevenj.service.AuthenticationService;

public class AuthenticationServiceImpl implements AuthenticationService{
	private UserDAO userDao;
	
	public AuthenticationServiceImpl(){
		userDao = new UserDAOImpl();
	}
	
	public User login(String username, String password) throws Exception {
		return userDao.getUser(username, password);
	}
}
