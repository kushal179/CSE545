package com.asu.edu.base.dao.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;

import com.asu.edu.base.dao.BaseDAO;
import com.asu.edu.base.dao.intrf.FileDAOImplInterface;
import com.asu.edu.base.vo.FileVO;
import com.asu.edu.base.vo.PendingUsersVO;
import com.asu.edu.base.vo.ShareVO;
import com.asu.edu.constants.SQLConstants;
import com.asu.edu.security.EncryptDecrypt;

public class FileDAOImpl extends BaseDAO implements FileDAOImplInterface {
	String calledFunction;

	@Override
	protected Object toDataObject(ResultSet rs) throws SQLException {

		if (calledFunction == "getFile") {
			FileVO fileVO = new FileVO();
			fileVO.setFileName(rs.getString("FILE_NAME"));
			fileVO.setDeptId(rs.getInt("DEPT_ID"));
			fileVO.setId(rs.getInt("FILE_ID"));
			fileVO.setOwnerId(rs.getInt("OWNER_ID"));
			fileVO.setPath(rs.getString("PATH"));
			fileVO.setType(rs.getString("TYPE"));
			fileVO.setPassword(rs.getString("PASSWORD"));
			return fileVO;
		}
		if (calledFunction == "getParentFilePath") {
			return rs.getString("PATH");
		}
		if (calledFunction == "selectItem") {
			return rs.getInt("file_id");
		}

		if (calledFunction == "fileOwnerShipAuthorization") {
			return true;
		}
		if (calledFunction == "sharingAccessAuthorization") {
			return true;
		}
		if (calledFunction == "findDeptIdByDoc") {
			return rs.getInt("DEPT_ID");
		}
		if (calledFunction == "isLock") {
			return true;
		}
		if (calledFunction == "isLockOwner") {
			return true;
		}
		if (calledFunction == "deptByParent") {
			return rs.getInt("DEPT_ID");
		}
		

		return null;
	}

	public FileVO getFile(long id) {
		calledFunction = "getFile";
		String sql = SQLConstants.GET_FILE_FOR_DOWNLOAD;
		Object[] params = new Object[1];
		params[0] = id;
		FileVO vo = (FileVO) getRowByCriteria(sql, params);

		return vo;
	}

	public String getParentFilePath(int id) {
		calledFunction = "getParentFilePath";
		Object[] param = new Object[1];
		param[0] = id;
		String sql = SQLConstants.GET_FILE_PATH;
		String path = (String) getRowByCriteria(sql, param);
		return path;
	}

	public boolean saveFile(FileVO fileVO) {
		Object[] param = new Object[9];
		param = new Object[9];
		param[0] = fileVO.getPath();
		param[1] = fileVO.getOwnerId();
		param[2] = fileVO.getDeptId();
		param[3] = fileVO.getParentId();
		param[4] = fileVO.getFileName();
		param[5] = new java.sql.Timestamp(new java.util.Date().getTime());
		param[6] = fileVO.getContentType();
		param[7] = new java.sql.Timestamp(new java.util.Date().getTime());
		param[8] = fileVO.getPassword();
		String sql = SQLConstants.SAVE_FILE;
		return preparedStatementUpdate(sql, param, true) > 0;

	}

	public boolean saveFolder(FileVO fileVO) {
		Object[] param = new Object[7];
		param[0] = fileVO.getPath();
		param[1] = fileVO.getOwnerId();
		param[2] = fileVO.getDeptId();
		param[3] = fileVO.getParentId();
		param[4] = fileVO.getFileName();
		param[5] = new java.sql.Timestamp(new java.util.Date().getTime());
		param[6] = new java.sql.Timestamp(new java.util.Date().getTime());
		String sql = SQLConstants.SAVE_FOLDER;
		return preparedStatementUpdate(sql, param, true) > 0;
	}

	public boolean lock(Object[] param) {
		String sql = SQLConstants.LOCK_FILE;
		return preparedStatementUpdate(sql, param, true) > 0;
	}
	public boolean version(Object[] param){
		
		String sql = SQLConstants.VERSION;
		return preparedStatementUpdate(sql, param, true) > 0;
		
	}
	public int deptByParent(int parentId) {
		calledFunction = "deptByParent";
		Object[] param = new Object[1];
		param[0] = parentId;
		String sql = SQLConstants.DEPT_BY_PARENT;
		Object id = getRowByCriteria(sql, param);
		if (id == null)
			return 0;
		else
			return (Integer) id;
	}

	public boolean unLock(Object[] param) {
		String sql = SQLConstants.UNLOCK_FILE;
		return preparedStatementUpdate(sql, param, true) > 0;
	}

	public boolean delete(Object[] param) {
		String sql = SQLConstants.DELETE;
		return preparedStatementUpdate(sql, param, true) > 0;
	}

	public boolean deleteDir(Object[] param) {
		String sql = SQLConstants.DELETE_DIR;
		return preparedStatementUpdate(sql, param, true) > 0;
	}

	public boolean isLock(Object[] param) {
		calledFunction = "isLock";
		String sql = SQLConstants.IS_FILE_LOCK;
		if (getRowByCriteria(sql, param) != null)
			return true;
		else
			return false;
	}
	public boolean isLockOwner(Object[] param) {
		calledFunction = "isLockOwner";
		String sql = SQLConstants.IS_FILE_LOCK_WITH_OWNER;
		if (getRowByCriteria(sql, param) != null)
			return true;
		else
			return false;
	}
	

	public boolean fileOwnerShipAuthorization(Object[] param) {
		calledFunction = "fileOwnerShipAuthorization";
		String sql = SQLConstants.CHECKING_FILE_OWNERSHIP;
		if (getRowByCriteria(sql, param) != null)
			return true;
		else
			return false;
	}

	public boolean hasAccessAuthorization(Object[] param, String sql) {
		calledFunction = "sharingAccessAuthorization";
		if (getRowByCriteria(sql, param) != null)
			return true;
		else
			return false;

	}

	public int findDeptIdByDoc(Object[] param) {
		calledFunction = "findDeptIdByDoc";
		String sql = SQLConstants.CHECKING_DOC_DEPT;
		Object deptId = getRowByCriteria(sql, param);
		if (deptId == null)
			return 0;
		else
			return (Integer) deptId;
	}

	@Override
	public boolean shareItem(ShareVO shareVO, Long fromUserId) {
		calledFunction = "selectItem";
		EncryptDecrypt encryptDecrypt = new EncryptDecrypt();
		Integer itemId = 0;
		try {
			itemId = Integer.parseInt(encryptDecrypt.decrypt(shareVO
					.getItemhashedId()));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Object[] param = new Object[3];
		param[0] = itemId;
		param[1] = fromUserId;
		param[2] = shareVO.getToUserId();
		String sql = SQLConstants.SHARE_SELECT_ITEM;
		Integer fileid = (Integer) getRowByCriteria(sql, param);

		calledFunction = "shareItem";
		param = new Object[6];

		if (fileid == null) {
			param[0] = itemId;
			param[1] = fromUserId;
			param[2] = shareVO.getToUserId();
			param[4] = param[5] = 0;
			ArrayList<Integer> permissionsList = shareVO.getPermissions();
			if (permissionsList != null) {
				for (int i = 0; i < permissionsList.size(); i++) {
					param[2 + permissionsList.get(i)] = 1;
				}
			}
			param[3] = 1;
			sql = SQLConstants.SHARE_INSERT_ITEM;
		} else {
			param[1] = param[2] = 0;
			param[3] = itemId;
			param[4] = fromUserId;
			param[5] = shareVO.getToUserId();
			ArrayList<Integer> permissionsList = shareVO.getPermissions();
			if (permissionsList != null) {
				for (int i = 0; i < permissionsList.size(); i++) {
					param[permissionsList.get(i) - 1] = 1;
				}
			}
			param[0] = 1;
			sql = SQLConstants.SHARE_UPDATE_ITEM;
		}
		return preparedStatementUpdate(sql, param, true) > 0;
	}

	@Override
	public boolean unshareItem(String fileid, Long byUserID, Long toUserId) {
		calledFunction = "unshareItem";
		EncryptDecrypt encryptDecrypt = new EncryptDecrypt();
		Integer itemId = 0;
		try {
			itemId = Integer.parseInt(encryptDecrypt.decrypt(fileid));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Object[] param = new Object[3];
		param[0] = itemId;
		param[1] = byUserID;
		param[2] = toUserId;
		String sql = SQLConstants.UNSHARE_SELECT_ITEM;
		return this.preparedStatementUpdate(sql, param, true) > 0;
	}
}
