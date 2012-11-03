package com.asu.edu.base.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;

import com.asu.edu.base.dao.BaseDAO;
import com.asu.edu.base.dao.intrf.CommonDAOImplInterface;
import com.asu.edu.base.vo.DepartmentVO;
import com.asu.edu.base.vo.RoleVO;
import com.asu.edu.constants.SQLConstants;

public class CommonDAOImpl extends BaseDAO implements CommonDAOImplInterface {

	private String calledFunction;
	
	@Override
	protected Object toDataObject(ResultSet rs) throws SQLException {
		if (calledFunction.equals("getDepartments")) {
			
			LinkedHashMap<Integer, DepartmentVO> deptMap = new LinkedHashMap<Integer, DepartmentVO>();
			rs.previous();
			while(rs.next()){
			DepartmentVO deptVO = new DepartmentVO();
			deptVO.setId(rs.getInt("ID"));
			deptVO.setDeptName(rs.getString("NAME"));
			deptVO.setDeptDesc("DESC");
			deptMap.put(deptVO.getId(), deptVO);
			}
			return deptMap;
		}
		if (calledFunction.equals("getRoles")) {
			LinkedHashMap<Integer, RoleVO> roleMap = new LinkedHashMap<Integer, RoleVO>();
			rs.previous();
			while(rs.next()){
			RoleVO roleVO = new RoleVO();
			roleVO.setId(rs.getInt("ID"));
			roleVO.setDesc(rs.getString("DESC"));
			roleVO.setCheckIn(rs.getInt("CHECKIN_OUT"));
			roleVO.setCreate(rs.getInt("CREATE"));
			roleVO.setDelete(rs.getInt("DELETE"));
			roleVO.setCreate(rs.getInt("DOWNLOAD"));
			roleVO.setUpdate(rs.getInt("UPDATE"));
			roleVO.setDelete(rs.getInt("SHARE"));
			roleMap.put(roleVO.getId(), roleVO);
			}
			return roleMap;
		}
		return null;
	}
	
	
	
	@Override
	public LinkedHashMap<Integer, DepartmentVO> getDepartments() {
		calledFunction = "getDepartments";
		String sql = SQLConstants.GET_DEPTARTMENTS;

		LinkedHashMap<Integer, DepartmentVO> deptMap = (LinkedHashMap<Integer, DepartmentVO>) getRowByCriteria(sql, (Object[]) null);
		
		return deptMap;
	}

	@Override
	public LinkedHashMap<Integer, RoleVO> getRoles() {
		calledFunction = "getRoles";
		String sql = SQLConstants.GET_ROLES;

		LinkedHashMap<Integer, RoleVO> roleMap = (LinkedHashMap<Integer, RoleVO>) getRowByCriteria(sql, (Object[]) null);
		
		return roleMap;
	}

}
