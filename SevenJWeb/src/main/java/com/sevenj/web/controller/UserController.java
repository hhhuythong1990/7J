package com.sevenj.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.sevenj.model.Project;
import com.sevenj.model.Role;
import com.sevenj.model.User;
import com.sevenj.service.RoleService;
import com.sevenj.service.UserService;
import com.sevenj.service.impl.RoleServiceImpl;
import com.sevenj.service.impl.UserServiceImpl;
import com.sevenj.web.common.BreadCrumbConst;
import com.sevenj.web.common.SessionConst;
import com.sevenj.web.common.WebSession;
import com.sevenj.web.model.datatable.JsonOption;
import com.sevenj.web.model.datatable.JsonOptionList;
import com.sevenj.web.model.datatable.JsonResult;
import com.sevenj.web.model.datatable.JsonResultBase;
import com.sevenj.web.model.datatable.JsonResultList;
import com.sevenj.web.model.datatable.ResultEnum;
import com.sevenj.web.model.datatable.RoleOption;

import dummiesmind.breadcrumb.springmvc.annotations.Link;

@Controller
@SessionAttributes({SessionConst.WEB_SESSION})
public class UserController {
	private Logger logger = Logger.getLogger(UserController.class.getName());
	private UserService userService;
	private RoleService roleService;
	
	public UserController(){
		userService = new UserServiceImpl();
		roleService = new RoleServiceImpl();
	}
	
	@Link(label=BreadCrumbConst.USER_MANAGEMENT, family=BreadCrumbConst.USER_MANAGEMENT_FLOW, parent="")
	@RequestMapping(value="/userManagement.htm", method=RequestMethod.GET)
	public String setting(){
		return "userManagement";
	}
	
	@RequestMapping(value="/userManagement.htm", method=RequestMethod.POST)
	public @ResponseBody JsonResultList<User> getUserListForJTable(int jtStartIndex, int jtPageSize, String jtSorting){
		JsonResultList<User> jsonResultList = new JsonResultList<User>();
		try {
			List<User> users = userService.getUserList(jtStartIndex, jtPageSize, jtSorting);
			jsonResultList.setRecords(users);
			jsonResultList.setResult(ResultEnum.OK);
		} catch (Exception e) {
			logger.error("Cannot get user list, caused by", e);
			jsonResultList.setResult(ResultEnum.ERROR);
			jsonResultList.setMessage(e.getMessage());
		}
		return jsonResultList;
	}
	
	@RequestMapping(value="/getRoles.htm", method = RequestMethod.POST)
	public @ResponseBody JsonOptionList<RoleOption> getRoleList(){
		JsonOptionList<RoleOption> jsonOption = new JsonOptionList<RoleOption>();
		try{
			List<Role> roles = roleService.getRoles();
			List<RoleOption> roleOptions = new ArrayList<RoleOption>();
			for (Role role : roles) {
				RoleOption roleOption = new RoleOption();
				roleOption.setRecord(role);
				roleOptions.add(roleOption);
			}
			jsonOption.setOptions(roleOptions);
			jsonOption.setResult(ResultEnum.OK);
		}catch(Exception ex){
			jsonOption.setMessage(ex.getMessage());
			jsonOption.setResult(ResultEnum.ERROR);
			logger.error("Cannot get role list, caused by", ex);
		}
		return jsonOption;
	}
	
	@RequestMapping(value="/createNewUser.htm", method=RequestMethod.POST)
	public @ResponseBody JsonResult<User> createNewUser(@ModelAttribute User user, @ModelAttribute(value=SessionConst.WEB_SESSION)WebSession webSession, HttpServletRequest request){
		JsonResult<User> resultBase = new JsonResult<User>();
		try{
		    String roleId = request.getParameter("roleId");
		    Role role = roleService.getRole(Integer.parseInt(roleId));
		    user.setRole(role);
		    userService.createUser(user);
		    resultBase.setRecord(user);
		    resultBase.setResult(ResultEnum.OK);
		}catch(Exception ex){
			logger.error("Cannot create new user, caused by ", ex);
			resultBase.setMessage(ex.getMessage());
			resultBase.setResult(ResultEnum.ERROR);
		}
		return resultBase;
	}
	
}
