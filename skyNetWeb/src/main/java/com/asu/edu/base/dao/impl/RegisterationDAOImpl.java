package com.asu.edu.base.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.asu.edu.RegisterationController;
import com.asu.edu.base.dao.BaseDAO;
import com.asu.edu.base.dao.intrf.RegisterationDAOImplInterface;
import com.asu.edu.base.vo.RegisterationVO;
import com.asu.edu.constants.SQLConstants;

public class RegisterationDAOImpl extends BaseDAO implements
		RegisterationDAOImplInterface {

	private static final int LOGIN_ATTEMPTS_POS = 8;
	private static final int IS_APPROVED_POS = 7;
	private static final int DEPT_ID_POS = 6;
	private static final int ROLE_ID_POS = 5;
	private static final int EMAIL_POS = 4;
	private static final int LAST_NAME_POS = 3;
	private static final int FIRST_NAME_POS = 2;
	private static final int PASSWORD_POS = 1;
	private static final int USER_NAME_POS = 0;
	String calledFunction;

	@Override
	protected Object toDataObject(ResultSet rs) throws SQLException {

		if (calledFunction.equals("getDepartments")) {
			return new Department(rs.getInt("id"), rs.getString("name"));
		}
		else if (calledFunction.equals("getRoles")) {
			return new Role(rs.getInt("id"), rs.getString("desc"));
		}
		return null;
	}

	@Override
	public boolean registerUser(RegisterationVO user) {
		calledFunction = "registerUser";
		Object[] param = new Object[9];
		param[USER_NAME_POS] = user.getUserName();
		param[PASSWORD_POS] = user.getPassword();
		param[FIRST_NAME_POS] = user.getFirstName();
		param[LAST_NAME_POS] = user.getLastName();
		param[EMAIL_POS] = user.getEmail();
		param[ROLE_ID_POS] = user.getRoleId();
		Logger logger = LoggerFactory
				.getLogger(RegisterationController.class); 
				logger.info("role is : " + user.getRole() + "  " +  user.getRoleId());
		param[DEPT_ID_POS] = user.getDepartment();
		param[IS_APPROVED_POS] = false;
		param[LOGIN_ATTEMPTS_POS] = 0;
		String sql = SQLConstants.USER_REG;

		// Query Successful
		if (preparedStatementUpdate(sql, param, true) > 0)
			return true;

		// Query failed
		return false;
	}

	@Override
	public Map<Integer, String> getDepartments() {
		calledFunction = "getDepartments";
		String sql = SQLConstants.GET_DEPTARTMENTS;

		ArrayList<Department> deptList = (ArrayList<Department>) getListByCriteria(
				sql, (Object[]) null);
		Map<Integer, String> deptMap = new LinkedHashMap<Integer, String>();
		for (Department department : deptList) {
			deptMap.put(department.deptId, department.deptName);
		}
		return deptMap;
	}

	@Override
	public Map<Integer, String> getRoles() {
		calledFunction = "getRoles";
		String sql = SQLConstants.GET_ROLES;

		ArrayList<Role> roleList = (ArrayList<Role>) getListByCriteria(
				sql, (Object[]) null);
		Map<Integer, String> roleMap = new LinkedHashMap<Integer, String>();
		for (Role role : roleList) {
			roleMap.put(role.roleId,role.roleName);
		}
		return roleMap;
	}
	
	private class Department {
		int deptId;
		String deptName;

		public Department(int id, String name) {
			this.deptId = id;
			this.deptName = name;
		}
	}
	
	private class Role {
		int roleId;
		String roleName;

		public Role(int id, String name) {
			this.roleId = id;
			this.roleName = name;
		}
	}
}
