package com.asu.edu.cache;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;


import com.asu.edu.base.dao.impl.CommonDAOImpl;
import com.asu.edu.base.dao.intrf.CommonDAOImplInterface;
import com.asu.edu.base.vo.DepartmentVO;
import com.asu.edu.base.vo.RoleVO;



public class MasterCache {
	
	private CommonDAOImplInterface commonDAO;
	private static LinkedHashMap<Integer,DepartmentVO> department;
	private static LinkedHashMap<Integer,RoleVO> role;
	
	static{
		try {
			reloadMasterCache() ;
		} catch (Exception e) {
			
			}
		}
	
	public static Map<Integer, DepartmentVO> getDepartmentMap()
	{
		CommonDAOImpl dao = new CommonDAOImpl();
		department= dao.getDepartments();
		return department;
	}
	
	public static Map<Integer, RoleVO> getRoleMap()
	{
		CommonDAOImpl dao = new CommonDAOImpl();
		role = dao.getRoles();
		return role;
	}
	
	public static void reloadMasterCache(){
		department = new LinkedHashMap<Integer, DepartmentVO>();
		role = new LinkedHashMap<Integer, RoleVO>();
		getDepartmentMap();
		getRoleMap();
	}
	
	public static void classStartup(){
		
	}

}
