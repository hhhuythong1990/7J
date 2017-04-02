package com.sevenj.service;

import java.util.List;

import com.sevenj.model.User;

public interface AuthenticationService {
	public User login(String userName, String password) throws Exception;
}
