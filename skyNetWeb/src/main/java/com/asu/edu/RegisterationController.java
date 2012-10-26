package com.asu.edu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.asu.edu.base.vo.UserVO;



@Controller
@RequestMapping("/registeration")
public class RegisterationController {
	
	
private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
	}
	
	
	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(Model model) { 
		UserVO userVO = new UserVO();
		model.addAttribute(userVO);
		return "register";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String register(@ModelAttribute UserVO userVO, BindingResult result) {
		
		
		return "register";
	}

}
