package com.asu.edu;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.asu.edu.base.dao.intrf.SecurityDAOImplInterface;
import com.asu.edu.base.vo.UserVO;
import com.asu.edu.constants.CommonConstants;

@Controller
public class LoginController {

	private static final Logger logger = LoggerFactory
			.getLogger(LoginController.class);

	@Autowired
	private SecurityDAOImplInterface securityDAO = null;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String dispatcher(Model model, HttpSession session) {
		if (securityDAO.isLoggedIn()) {
			UserVO userVO = securityDAO.getUserDetails(SecurityContextHolder
					.getContext().getAuthentication());
			if (userVO != null) {
				/*
				 * this is the only place where user details will be generated
				 * and stored in the session
				 */
				session.setAttribute(CommonConstants.USER, userVO);
				if (userVO.getIsApproved() == 1)
					return "redirect:/welcome/home";
				else
					return "redirect:/welcome/temp";
			}

		}
		return "redirect:/loginfailed";

	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Locale locale, Model model) {
		logger.info("Welcome login! screen");
		System.out.println("At Login page:" + securityDAO.isLoggedIn());
		return "login";
	}

	@RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
	public String loginFailed(Model model) {
		logger.info("Welcome loginfailed! screen");
		return "login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String loginOut(Locale locale, Model model) {
		logger.info("Welcome logout! screen");
		return "logout";
	}

}
