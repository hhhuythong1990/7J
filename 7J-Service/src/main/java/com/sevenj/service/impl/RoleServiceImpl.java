package com.sevenj.service.impl;

import java.util.List;

import com.sevenj.dal.dao.RoleDAO;
import com.sevenj.dal.dao.impl.RoleDAOImpl;
import com.sevenj.model.Role;
import com.sevenj.service.RoleService;

public class RoleServiceImpl implements RoleService{
	private RoleDAO roleDAO;

	public RoleServiceImpl(){
		roleDAO = new RoleDAOImpl();
	}
	public List<Role> getRoles() throws Exception {
		return roleDAO.getList(Role.class);
	}
	public Role getRole(int roleId) throws Exception {
		return roleDAO.getEntityById(Role.class, new Integer(roleId));
	}

}
