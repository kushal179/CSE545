package com.asu.edu;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.asu.edu.base.vo.UserVO;
import com.asu.edu.constants.CommonConstants;


@Controller
@SessionAttributes({CommonConstants.USER})
public class DocumentHomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(DocumentHomeController.class);
	
	@RequestMapping(value = "/welcome/home", method = RequestMethod.GET)
	public String userHome(ModelMap map,@ModelAttribute UserVO user,HttpSession session)
	{
		logger.info(user.getFirstName());
		
		return "documentManagement";
	}
	
	

}
