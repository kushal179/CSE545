package com.asu.edu.base.dao.intrf;

import java.util.Map;

import com.asu.edu.base.vo.RegisterationVO;

public interface RegisterationDAOImplInterface {
	
	public boolean registerUser(RegisterationVO user);
	
	public Map<Integer, String> getDepartments();
	
	public Map<Integer, String> getRoles();

}
