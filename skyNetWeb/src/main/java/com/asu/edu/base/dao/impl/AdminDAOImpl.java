package com.asu.edu.base.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.asu.edu.RegisterationController;
import com.asu.edu.base.dao.intrf.AdminDAOImplInterface;
import com.asu.edu.base.dao.BaseDAO;
import com.asu.edu.base.vo.PendingUsersVO;
import com.asu.edu.base.vo.RegisterationVO;
import com.asu.edu.base.vo.LogFilesVO;
import com.asu.edu.constants.SQLConstants;

public class AdminDAOImpl extends BaseDAO implements AdminDAOImplInterface {

	String calledFunction;

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<PendingUsersVO> getPendingUsers() {
		calledFunction = "pendingUsers";
		Integer isApproved = 0;
		Object[] param = new Object[1];
		param[0] = isApproved;
		String sql = SQLConstants.PENDING_USERS;
		ArrayList<PendingUsersVO> result = (ArrayList<PendingUsersVO>) this
				.getListByCriteria(sql, param);

		if (result != null) {
			for (PendingUsersVO userVo : result) {
				updateWithDepts(userVo);
			}
		}

		return result;
	}

	@Override
	public ArrayList<RegisterationVO> getUsersByRole(int role_id) {
		calledFunction = "getUsersByRole";
		Integer roleId = role_id;
		Object[] param = new Object[1];
		param[0] = roleId;
		String sql = SQLConstants.USERS_BY_ROLE;
		ArrayList<RegisterationVO> result = (ArrayList<RegisterationVO>) this
				.getListByCriteria(sql, param);

		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<LogFilesVO> getLogFiles() {
		calledFunction = "getLogFiles";
		Object[] param = new Object[1];
		param[0] = 1;
		String sql = SQLConstants.LOG_FILES;
		ArrayList<LogFilesVO> result = (ArrayList<LogFilesVO>) this
				.getListByCriteria(sql, param);
		return result;
	}

	@Override
	public void approveUser(String userId) {
		calledFunction = "approveUser";
		Integer id = Integer.parseInt(userId);
		Integer isApproved = 1;
		Object[] param = new Object[2];
		param[0] = isApproved;
		param[1] = id;
		String sql = SQLConstants.APPROVE_USER;
		this.preparedStatementUpdate(sql, param, true);
	}

	@Override
	public void rejectUser(String userId) {
		calledFunction = "rejectUSer";
		Integer id = Integer.parseInt(userId);
		Object[] param = new Object[1];
		param[0] = id;
		String sql = SQLConstants.REJECT_USER;
		this.preparedStatementUpdate(sql, param, true);
	}

	@Override
	public void modifyUser(PendingUsersVO user) {
		Logger logger = LoggerFactory.getLogger(AdminDAOImpl.class);
		calledFunction = "modifyUser";
		Object[] param = new Object[2];
		param[0] = user.getRoleId();
		param[1] = user.getUserId();
		logger.info("Modified role is : " + user.getRoleId() + " for user :  "
				+ user.getUserId());
		String sql = SQLConstants.MODIFY_USER_ROLE;
		this.preparedStatementUpdate(sql, param, true);

		// delete the existing depts for this user
		param = new Object[1];
		param[0] = user.getUserId();
		logger.info("Delete depts of user : " + param[0]);
		sql = SQLConstants.DELETE_USER_DEPTS;
		this.preparedStatementUpdate(sql, param, true);

		// modify depts for this user
		ArrayList<Integer> depts = user.getDeptIds();
		ArrayList<Integer> deptsArray = new ArrayList<Integer>();
		for (int i = 0; i < depts.size(); i++) {
			if (depts.get(i) > 0)
				deptsArray.add(depts.get(i));
		}

		param = new Object[deptsArray.size() * 2];
		sql = SQLConstants.ADD_DEPT_FOR_USER;
		for (int i = 0; i < deptsArray.size(); i++) {
			sql += "(?,?)";
			if (i < deptsArray.size() - 1)
				sql += " , ";
			param[2 * i] = user.getUserId();
			param[2 * i + 1] = deptsArray.get(i);
		}
		this.preparedStatementUpdate(sql, param, true);
	}

	@Override
	protected Object toDataObject(ResultSet rs) throws SQLException {
		if (calledFunction == "pendingUsers") {
			System.out.println("inside to data object - pendingUsers");
			PendingUsersVO vo = new PendingUsersVO();
			vo.setUserName(rs.getString("USER_NAME"));
			vo.setUserId(rs.getInt(2));
			vo.setRoleId(rs.getInt(3));
			vo.setRoleDesc(rs.getString("DESC"));

			return vo;
		} else if (calledFunction == "getUsersByRole") {
			System.out.println("inside to data object - getUsersByRole");
			RegisterationVO vo = new RegisterationVO();
			vo.setUserName(rs.getString("USER_NAME"));
			vo.setDepartment(rs.getString("NAME"));
			return vo;
		} else if (calledFunction == "getLogFiles") {
			System.out.println("inside to data object - getLogFiles");
			LogFilesVO vo = new LogFilesVO();
			System.out.println(rs.getString("PATH"));
			vo.setPathName(rs.getString("PATH"));
			vo.setModifiedDate(rs.getString("TIMESTAMP"));
			return vo;
		} else if (calledFunction == "updateWithDepts") {
			System.out.println("inside to data object - updateWithDepts");
			Map<Integer, String> depts = new HashMap<Integer, String>();
			depts.put(rs.getInt("id"), rs.getString("name"));
			return depts;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	private void updateWithDepts(PendingUsersVO vo) {
		calledFunction = "updateWithDepts";
		Object[] param = new Object[1];
		param[0] = vo.getUserId();
		String sql = SQLConstants.USER_DEPTS;
		ArrayList<Map<Integer, String>> result = (ArrayList<Map<Integer, String>>) this
				.getListByCriteria(sql, param);

		ArrayList<Integer> deptIds = new ArrayList<Integer>();
		ArrayList<String> deptNames = new ArrayList<String>();
		for (Map<Integer, String> dept : result) {
			for (Map.Entry<Integer, String> m : dept.entrySet()) {
				deptIds.add(m.getKey());
				deptNames.add(m.getValue());
			}
		}
		vo.setDeptIds(deptIds);
		vo.setDeptNames(deptNames);
	}
}
