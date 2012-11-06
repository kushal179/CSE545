package com.asu.edu.base.dao.intrf;

import org.springframework.security.core.Authentication;

import com.asu.edu.base.vo.UserVO;

public interface SecurityDAOImplInterface {
	
	public boolean isLoggedIn();
	public UserVO getUserDetails(Authentication authentication);
	public String getEmailForUser(String userName);
	public void setPasswordForUser(String userName, String passwd);	
	public boolean isValidPassword(String userName, String password);
}
