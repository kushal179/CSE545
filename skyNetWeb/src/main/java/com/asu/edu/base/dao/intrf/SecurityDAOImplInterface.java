package com.asu.edu.base.dao.intrf;

import org.springframework.security.core.Authentication;

import com.asu.edu.base.vo.UserVO;

public interface SecurityDAOImplInterface {
	
	public boolean isLoggedIn();
	public UserVO getUserDetails(Authentication authentication);

}
