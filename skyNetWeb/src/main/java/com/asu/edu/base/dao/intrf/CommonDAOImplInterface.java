package com.asu.edu.base.dao.intrf;

import java.util.LinkedHashMap;

import com.asu.edu.base.vo.DepartmentVO;
import com.asu.edu.base.vo.RoleVO;

public interface CommonDAOImplInterface  {
	
	
	public LinkedHashMap<Integer, DepartmentVO> getDepartments();
	public LinkedHashMap<Integer, RoleVO> getRoles();

}
