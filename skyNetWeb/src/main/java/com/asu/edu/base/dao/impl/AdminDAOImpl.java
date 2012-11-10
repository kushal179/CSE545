package com.asu.edu.base.dao.impl;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.asu.edu.RegisterationController;
import com.asu.edu.base.dao.intrf.AdminDAOImplInterface;
import com.asu.edu.base.dao.BaseDAO;
import com.asu.edu.base.vo.PendingUsersVO;
import com.asu.edu.base.vo.RegisterationVO;
import com.asu.edu.base.vo.LogFilesVO;
import com.asu.edu.constants.CommonConstants;
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
	public ArrayList<PendingUsersVO> getUsersByRole(int role_id) {
		calledFunction = "getUsersByRole";
		Integer roleId = role_id;
		Object[] param = new Object[3];
		param[0] = 1;
		param[1] = 0;
		param[2] = roleId;
		String sql = SQLConstants.USERS_BY_ROLE;
		ArrayList<PendingUsersVO> result = (ArrayList<PendingUsersVO>) this
				.getListByCriteria(sql, param);

		if (result != null) {
			for (PendingUsersVO userVo : result) {
				updateWithDepts(userVo);
			}
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<LogFilesVO> getLogFiles() {
		calledFunction = "getLogFiles";

		/*
		 * Object[] param = new Object[1]; param[0] = 1; String sql =
		 * SQLConstants.LOG_FILES; ArrayList<LogFilesVO> result =
		 * (ArrayList<LogFilesVO>) this .getListByCriteria(sql, param); return
		 * result;
		 */
		String path = CommonConstants.LOG_FILES_PATH;
		ArrayList<LogFilesVO> files = new ArrayList<LogFilesVO>();
		File folder = new File(path);
		if (folder != null) {
			File[] listOfFiles = folder.listFiles();
			if (listOfFiles != null) {
				for (int i = 0; i < listOfFiles.length; i++) {
					if (listOfFiles[i].isFile()) {
						LogFilesVO logFileVO = new LogFilesVO();
						logFileVO.setPathName(listOfFiles[i].getName());
						Long timeSinceEpoch = listOfFiles[i].lastModified();
						Calendar cal = new GregorianCalendar();
						cal.setTimeInMillis(timeSinceEpoch);

						DateFormat dateFormat = new SimpleDateFormat(
								"yyyy/MM/dd HH:mm:ss");
						String modifieddate = dateFormat.format(cal.getTime());
						logFileVO.setModifiedDate(modifieddate);
						logFileVO.setHyperLink("downloadlogfile?logfilename="
								+ listOfFiles[i].getName());
						files.add(logFileVO);
					}
				}
			}
		}
		return files;
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
		if (depts != null) {
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
			PendingUsersVO vo = new PendingUsersVO();
			vo.setUserName(rs.getString("USER_NAME"));
			vo.setUserId(rs.getInt("ID"));

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
		if (result != null) {
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

	@Override
	public void deactivateUser(String userId) {
		calledFunction = "deactivateUser";
		Integer id = Integer.parseInt(userId);
		Integer deactivate = 1;
		Object[] param = new Object[2];
		param[0] = deactivate;
		param[1] = userId;
		String sql = SQLConstants.DEACTIVATE_USER;
		this.preparedStatementUpdate(sql, param, true);

	}
}
