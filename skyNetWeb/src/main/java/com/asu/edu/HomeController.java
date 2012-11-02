package com.asu.edu;

import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.asu.edu.base.dao.intrf.AdminDAOImplInterface;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private AdminDAOImplInterface adminDAO = null;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String admin(Locale locale, Map model) {
		logger.info("Admin screen");
		model.put("pendingUsers", adminDAO.getPendingUsers());
		return "admin";
	}
	
	@RequestMapping(value = "/admin/approve", method = RequestMethod.POST)
	public String pendingUsersApprove(Locale locale, Map model, @RequestParam("userid") String userId) {
		logger.info("Admin Approve user request");
		// Approve user
		adminDAO.approveUser(userId);
		return "redirect:/admin";
	}	
	
	@RequestMapping(value = "/admin/reject", method = RequestMethod.POST)
	public String pendingUserReject(Locale locale, Map model, @RequestParam("userid") String userId) {
		logger.info("Admin Reject user request");
		// Reject user
		adminDAO.rejectUser(userId);
		return "redirect:/admin";
	}	
	
	@RequestMapping(value = "/admin/modify", method = RequestMethod.POST)
	public String pendingUserModify(Locale locale, Map model, @RequestParam("userid") String userId) {
		logger.info("Admin Modify user request");
		// Modify user
		return "redirect:/admin";
	}	
}
