package com.sevenj.web.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.sevenj.model.User;
import com.sevenj.service.AuthenticationService;
import com.sevenj.service.impl.AuthenticationServiceImpl;
import com.sevenj.web.common.SessionConst;
import com.sevenj.web.common.WebSession;
import com.sevenj.web.model.datatable.JsonResultList;
import com.sevenj.web.model.datatable.ResultEnum;

@Controller
@SessionAttributes({SessionConst.WEB_SESSION})
public class AuthenticationController {
    private Logger logger = Logger.getLogger(AuthenticationController.class.getName());
    private AuthenticationService authenticationService;

    @Autowired
    private MessageSource message;

    public AuthenticationController(){
    	authenticationService= new AuthenticationServiceImpl();
    }

	@RequestMapping(value="/login.htm", method=RequestMethod.GET)
	public String login(){
		return "login";
	}
	
	@RequestMapping(value="/login.htm", method=RequestMethod.POST)
	public ModelAndView login(String username, String password,HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		String result = "redirect:projectList.htm";
		AuthenticationService authenService = new AuthenticationServiceImpl();
		try {
			User user = authenService.login(username, password);
			if (user == null){
				view.addObject("errorMessage", "Invalid username or password");
				result = "login";
			}else{
				WebSession webSession = new WebSession();
				webSession.setCurrentUser(user);
				view.addObject("webSession", webSession);
			}
		} catch (Exception e) {
			logger.error("Caused by", e);
			request.setAttribute("errorMessage", e.getMessage());
			result = "login";
		}
		
		view.setViewName(result);
		return view;
	}
	
	@RequestMapping(value="/logout.htm", method=RequestMethod.GET)
	public String logout(HttpServletRequest request){
		request.getSession().removeAttribute(SessionConst.WEB_SESSION);
		return "redirect:login.htm";
	}
}

