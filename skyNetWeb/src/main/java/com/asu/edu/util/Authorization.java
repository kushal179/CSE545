package com.asu.edu.util;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import com.asu.edu.base.dao.intrf.FileDAOImplInterface;
import com.asu.edu.base.vo.RoleVO;
import com.asu.edu.base.vo.UserVO;
import com.asu.edu.cache.MasterCache;
import com.asu.edu.constants.CommonConstants;
import com.asu.edu.constants.SQLConstants;
import com.asu.edu.security.EncryptDecrypt;

public class Authorization {

	
	private FileDAOImplInterface fileDAO;
	

	public FileDAOImplInterface getFileDAO() {
		return fileDAO;
	}

	public void setFileDAO(FileDAOImplInterface fileDAO) {
		this.fileDAO = fileDAO;
	}

	public boolean isOwner(UserVO userVO, long fileId) {
		boolean isOwner = false;
		Object[] param = new Object[2];
		param[0] = userVO.getId();
		param[1] = fileId;
		isOwner = fileDAO.fileOwnerShipAuthorization(param);
		return isOwner;

	}

	public boolean hasAccessPrivileges(UserVO userVO,String access, long fileId) {
		boolean hasAccessPrivileges = false;
		if(access.equals(CommonConstants.DELETE))
			return hasAccessPrivileges;
		Object[] param = new Object[2];
		param[0] = userVO.getId();
		param[1] = fileId;
		String sql = SQLConstants.CHECKING_SHARING_RIGHTS;
		sql = sql.replaceAll("%", access);
		hasAccessPrivileges = fileDAO.hasAccessAuthorization(param, sql);
		return hasAccessPrivileges;
	}

	public boolean isPartOfDepartment(UserVO userVO, long fileId,String access) {
		boolean isPartOfDepartment = false;
		ArrayList<Integer> deptList = userVO.getDepartments();
		Object[] param = new Object[1];
		param[0] = fileId;
		int deptId = fileDAO.findDeptIdByDoc(param);
		if(deptList.contains(deptId))
			isPartOfDepartment = true;
		return isPartOfDepartment;
	}

	public boolean isAuthorize(String access, HttpSession session,
			HashMap<String, String> param) {
		boolean isAuthorize = false;
		EncryptDecrypt crypt = new EncryptDecrypt();
		UserVO userVO = (UserVO) session.getAttribute(CommonConstants.USER);
		long fileId = Long.parseLong(crypt.decrypt(param.get(CommonConstants.REQ_PARAM_FILE_ID)));
		RoleVO roleVO = MasterCache.getRoleMap().get(userVO.getRoleId());
		int role = roleVO.getId();
		switch (role) {
		case CommonConstants.ROLE_GUEST_USR_ID:
			isAuthorize = hasAccessPrivileges(userVO,access, fileId);
			break;
		case CommonConstants.ROLE_REGULAR_EMP_ID:
			isAuthorize = isOwner(userVO,fileId);
			if(isAuthorize){
				 break;
			}
			isAuthorize = hasAccessPrivileges(userVO,access,fileId);
			break;

		case CommonConstants.ROLE_CORPORATE_MGR_ID:
		case CommonConstants.ROLE_DEPARTMENT_MGR_ID:
			isAuthorize = isPartOfDepartment(userVO, fileId,access);
			break;
		}
		return isAuthorize;
	}

}
