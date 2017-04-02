package com.sevenj.service;

import java.util.List;

import com.sevenj.model.Role;

public interface RoleService {
  public List<Role> getRoles() throws Exception;
  public Role getRole(int roleId) throws Exception;
 }
