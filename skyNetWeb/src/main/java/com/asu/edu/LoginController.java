package com.asu.edu;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.asu.edu.base.dao.intrf.SecurityDAOImplInterface;


@Controller
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private SecurityDAOImplInterface securityDAO = null;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! the client locale is "+ locale.toString());
		

		return "documentManagement";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Locale locale, Model model) {
		logger.info("Welcome login! screen");
		return "login";
	}
	
	@RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
	public String loginFailed(Locale locale, Model model) {
		logger.info("Welcome loginfailed! screen");
		return "loginfailed";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String loginOut(Locale locale, Model model) {
		logger.info("Welcome logout! screen");
		return "logout";
	}

}
