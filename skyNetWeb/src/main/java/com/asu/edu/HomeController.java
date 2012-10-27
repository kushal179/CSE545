package com.asu.edu;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.asu.edu.base.dao.intrf.SecurityDAOImplInterface;

import com.asu.edu.base.dao.intrf.AdminDAOImplInterface;
import com.asu.edu.base.dao.intrf.SecurityDAOImplInterface;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private SecurityDAOImplInterface securityDAO = null;
	@Autowired
	private AdminDAOImplInterface adminDAO = null;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! the client locale is "+ locale.toString());
		
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String name = user.getUsername();
		model.addAttribute("username", name);
		
		
		System.out.println("User name"+securityDAO.getUserId("Kushal"));
		return "documentManagement";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Locale locale, Model model) {
		logger.info("Welcome login! screen");
		return "login";
	}
	
	@RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
	public String loginFailed(Locale locale, Model model) {
		logger.info("Welcome login! screen");
		return "loginfailed";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String loginOut(Locale locale, Model model) {
		logger.info("Welcome login! screen");
		return "logout";
	}
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String admin(Locale locale, Map model) {
		logger.info("Admin screen");
		model.put("pendingUsers", adminDAO.getPendingUsers());
		return "admin";
	}
	
}
