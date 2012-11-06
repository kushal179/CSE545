package com.asu.edu.base.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

import com.asu.edu.base.dao.BaseDAO;
import com.asu.edu.base.dao.intrf.DashboardDAOImplInterface;
import com.asu.edu.base.vo.DepartmentVO;
import com.asu.edu.base.vo.FileVO;
import com.asu.edu.base.vo.UserVO;
import com.asu.edu.constants.SQLConstants;
import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

public class DashboardDAOImpl extends BaseDAO implements
		DashboardDAOImplInterface {

	private static final String GET_FILES = "getRegularEmployeeFiles";
	private static final String GET_SHARED_FILES = "getSharedFiles";
	String calledFunction;

	@Autowired
	private ShaPasswordEncoder passwordEncoder;

	@Override
	public ArrayList<FileVO> getRegularEmployeeFiles(UserVO userVO,
			DepartmentVO departmentVO, long folderId) {

		if (userVO == null || departmentVO == null)
			return null;

		calledFunction = GET_FILES;

		String sql = SQLConstants.GET_REGULAR_USER_FILES;
		Object[] params = new Object[2];
		params[0] = userVO.getId();
		params[1] = folderId;
		ArrayList<FileVO> files = (ArrayList<FileVO>) getListByCriteria(sql,
				params);
		return files;
	}

	@Override
	public ArrayList<FileVO> getManagerFiles(UserVO userVO,
			DepartmentVO departmentVO, long folderId) {

		if (userVO == null || departmentVO == null)
			return null;

		calledFunction = GET_FILES;

		String sql = SQLConstants.GET_DEPT_MANAGER_FILES;
		Object[] params = new Object[2];
		params[0] = departmentVO.getId();
		params[1] = folderId;
		ArrayList<FileVO> files = (ArrayList<FileVO>) getListByCriteria(sql,
				params);

		return files;
	}

	@Override
	public ArrayList<FileVO> getCorporateManagerFiles(UserVO userVO,
			DepartmentVO departmentVO, long folderId) {

		if (userVO == null || departmentVO == null)
			return null;

		calledFunction = GET_FILES;

		String sql = SQLConstants.GET_CORPORATE_MANAGER_FILES;
		Object[] params = new Object[2];
		params[0] = departmentVO.getId();
		params[1] = folderId;
		ArrayList<FileVO> files = (ArrayList<FileVO>) getListByCriteria(sql,
				params);

		return files;
	}

	@Override
	public ArrayList<FileVO> getSharedByDocuments(UserVO userVO, long folderId) {

		String sql = "select * from sharing s inner join files f inner join user u on s.file_id = f.file_id and f.owner_id = u.id where s.user_id_by = ?";
		calledFunction = GET_SHARED_FILES;

		Object[] params;
		if (folderId == -1) {
			params = new Object[1];
			params[0] = userVO.getId();
		} else {
			sql += "and f.parent_id = ?";

			params = new Object[2];
			params[0] = userVO.getId();
			params[1] = folderId;
		}

		ArrayList<FileVO> files = (ArrayList<FileVO>) getListByCriteria(sql,
				params);

		return files;
	}

	@Override
	public ArrayList<FileVO> getSharedToDocuments(UserVO userVO,
			DepartmentVO departmentVO, long folderId) {

		String sql = "select * from sharing s inner join files f on s.file_id = f.file_id where s.shared_to = ? and f.parent_id = ?";

		Object[] params = new Object[2];
		params[0] = userVO.getId();
		params[1] = folderId;
		ArrayList<FileVO> files = (ArrayList<FileVO>) getListByCriteria(sql,
				params);

		return files;
	}
	
	public ArrayList<UserVO> getapprovedNonAdminUsers(long userid) {
		calledFunction = "getapprovedNonAdminUsers";
		String sql = SQLConstants.SHARE_TO_USERS;
		Object[] params = new Object[3];
		params[0] = 1;
		params[1] = 1;
		params[2] = userid;
		ArrayList<UserVO> users = (ArrayList<UserVO>) getListByCriteria(sql,
				params);

		return users;

	}

	@Override
	protected Object toDataObject(ResultSet rs) throws SQLException {
		if (calledFunction == GET_FILES) {
			FileVO fileVO = new FileVO();
			fileVO.setId(rs.getLong("FILE_ID"));
			fileVO.setFileName(rs.getString("FILE_NAME"));
			fileVO.setPath(rs.getString("PATH"));
			fileVO.setOwnerId(rs.getLong("OWNER_ID"));
			fileVO.setParentId(rs.getLong("PARENT_ID"));
			fileVO.setDeptId(rs.getInt("DEPT_ID"));
			fileVO.setModTime(rs.getDate("MOD_TIME").toString());
			fileVO.setCreateTime(rs.getDate("CREATION_TIME").toString());
			fileVO.setLock(rs.getBoolean("LOCK"));
			fileVO.setDir(rs.getBoolean("IS_DIR"));
			return fileVO;
			
		} else if (calledFunction == GET_SHARED_FILES) {
			FileVO fileVO = new FileVO();
			fileVO.setId(rs.getLong("FILE_ID"));
			fileVO.setFileName(rs.getString("FILE_NAME"));
			fileVO.setPath(rs.getString("PATH"));
			fileVO.setOwnerId(rs.getLong("OWNER_ID"));
			fileVO.setParentId(rs.getLong("PARENT_ID"));
			fileVO.setDeptId(rs.getInt("DEPT_ID"));
			fileVO.setModTime(rs.getDate("MOD_TIME").toString());
			fileVO.setCreateTime(rs.getDate("CREATION_TIME").toString());
			fileVO.setLock(rs.getBoolean("LOCK"));
			fileVO.setDir(rs.getBoolean("IS_DIR"));
			fileVO.setSharedByName(rs.getString("first_name") + " "
					+ rs.getString("last_name"));
			return fileVO;
			
		} else if (calledFunction == "getapprovedNonAdminUsers") {
			UserVO userVO=new UserVO();
			userVO.setId(rs.getLong("id"));
			userVO.setUserName(rs.getString("user_name"));
			return userVO;
		}

		return null;
	}

}
