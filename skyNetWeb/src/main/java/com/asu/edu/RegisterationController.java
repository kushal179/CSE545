package com.asu.edu;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.validation.Valid;

import net.tanesha.recaptcha.ReCaptcha;
import net.tanesha.recaptcha.ReCaptchaResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.asu.edu.base.dao.intrf.RegisterationDAOImplInterface;
import com.asu.edu.base.vo.RegisterationVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class RegisterationController {

	@Autowired
	private ReCaptcha reCaptcha = null;

	@Autowired
	private RegisterationDAOImplInterface registerationDAO = null;

	private static Map<Integer, String> deptMap;

	private static Map<Integer, String> roleMap;

	private static final Logger logger = LoggerFactory
			.getLogger(RegisterationController.class);

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String home1(Map<String, Object> model) {
		logger.info("Welcome home! the client locale is ");

		initMaps();

		model.put("registerationVO", new RegisterationVO());
		model.put("deptList", deptMap);
		model.put("roleList", roleMap);

		return "register";
	}

	private void initMaps() {
		if (deptMap == null) {
			deptMap = registerationDAO.getDepartments();
		}

		if (roleMap == null) {
			roleMap = registerationDAO.getRoles();
		}

	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String submitForm(@Valid RegisterationVO registerationVO,
			BindingResult result,
			@RequestParam("recaptcha_challenge_field") String challangeField,
			@RequestParam("recaptcha_response_field") String responseField,
			ServletRequest servletRequest, Map<String, Object> model) {

		if (!result.hasErrors()) {
			String remoteAddress = servletRequest.getRemoteAddr();
			ReCaptchaResponse reCaptchaResponse = this.reCaptcha.checkAnswer(
					remoteAddress, challangeField, responseField);
			if (!reCaptchaResponse.isValid()) {
				FieldError fieldError = new FieldError("registerationVO",
						"captcha", "Captcha worong. Please try again.");
				result.addError(fieldError);
			} else {
				
				logger.info("department is : " + registerationVO.getDepartment());
				logger.info("role is : " + registerationVO.getRole());
				
				if (registerationDAO.registerUser(registerationVO)) {
					logger.info("Registeration successful");
					return "login";
				}
				logger.info("Registeration failed");
			}
		}

		logger.info("form has erros !!!");
		model.put("deptList", deptMap);
		model.put("roleList", roleMap);

		return "register";
	}

}
