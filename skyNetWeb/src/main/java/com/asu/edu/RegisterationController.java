package com.asu.edu;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.validation.Valid;

import net.tanesha.recaptcha.ReCaptcha;
import net.tanesha.recaptcha.ReCaptchaResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.asu.edu.base.vo.RegisterationVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class RegisterationController {

	@Autowired
	private ReCaptcha reCaptcha = null;

	private static Map<String, String> deptMap;

	private static Map<String, String> roleMap;

	private static final Logger logger = LoggerFactory
			.getLogger(RegisterationController.class);

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String home1(Map model) {
		logger.info("Welcome home! the client locale is ");

		model.put("registerationVO", new RegisterationVO());
		model.put("deptList", deptMap);
		model.put("roleList", roleMap);

		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String submitForm(@Valid RegisterationVO registerationVO,
			BindingResult result,
			@RequestParam("recaptcha_challenge_field") String challangeField,
			@RequestParam("recaptcha_response_field") String responseField,
			ServletRequest servletRequest, Map model) {

		if (!result.hasErrors()) {
			String remoteAddress = servletRequest.getRemoteAddr();
			ReCaptchaResponse reCaptchaResponse = this.reCaptcha.checkAnswer(
					remoteAddress, challangeField, responseField);
			if (!reCaptchaResponse.isValid()) {

				FieldError fieldError = new FieldError("registerationVO",
						"captcha", "Please try again.");
				result.addError(fieldError);
			} else
				return "login";
		} else {
			logger.info("form has erros !!!");
			model.put("deptList", deptMap);
			model.put("roleList", roleMap);
		}

		return "register";
	}

	static {
		deptMap = new LinkedHashMap<String, String>();
		deptMap.put("Logistics & Supply", "Logistics & Supply");
		deptMap.put("HR", "HR");
		deptMap.put("IT Support", "IT Support");
		deptMap.put("Sales & Promotion", "Sales & Promotion");
		deptMap.put("Research & Development", "Research & Development");
		deptMap.put("Finance", "Finance");

		roleMap = new LinkedHashMap<String, String>();
		roleMap.put("Guest User", "Guest User");
		roleMap.put("Corporate", "Corporate");
		roleMap.put("Department Manager", "Department Manager");
		roleMap.put("Regular Employee", "Regular Employee");
	}
}
