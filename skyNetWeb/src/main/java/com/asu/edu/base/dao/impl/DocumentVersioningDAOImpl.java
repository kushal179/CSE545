package com.asu.edu.base.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

import com.asu.edu.base.dao.BaseDAO;
import com.asu.edu.base.dao.intrf.DocumentVersioningDAOImplInterface;
import com.asu.edu.base.vo.DepartmentVO;
import com.asu.edu.base.vo.FileVO;
import com.asu.edu.base.vo.FileVersionVO;
import com.asu.edu.base.vo.UserVO;
import com.asu.edu.constants.SQLConstants;

public class DocumentVersioningDAOImpl extends BaseDAO implements
		DocumentVersioningDAOImplInterface {

	private static final String GET_DOCUMENT_VERSIONS = "getDocumentVersions";
	private static final String GET_FILE = "getFile";
	String calledFunction;

	@Autowired
	private ShaPasswordEncoder passwordEncoder;

	@Override
	public ArrayList<FileVersionVO> getDocumentVersions(UserVO user, long fileId) {

		String sql = "select * from versioning v,user u where v.mod_user_id=u.id and v.file_id=?";

		calledFunction = GET_DOCUMENT_VERSIONS;

		Object[] params = new Object[1];
		params[0] = fileId;

		ArrayList<FileVersionVO> fileVersions = (ArrayList<FileVersionVO>) getListByCriteria(
				sql, params);

		calledFunction = GET_FILE;

		sql = SQLConstants.GET_FILE_INFO;

		params = new Object[1];
		params[0] = fileId;

		if (fileVersions != null) {

			FileVO fileVO = (FileVO) getRowByCriteria(sql, params);

			for (FileVersionVO fileVersionVO : fileVersions) {
				fileVersionVO.setFileName(fileVO.getFileName());
				fileVersionVO.setType(fileVO.getType());
				fileVersionVO.setOwnerId(fileVO.getOwnerId());
			}

			return fileVersions;
		}
		return null;
	}

	@Override
	protected Object toDataObject(ResultSet rs) throws SQLException {

		if (calledFunction == GET_DOCUMENT_VERSIONS) {
			FileVersionVO fileVersionVO = new FileVersionVO();
			fileVersionVO.setId(rs.getLong("FILE_ID"));
			fileVersionVO.setVersionId(rs.getInt("VERSION"));
			fileVersionVO.setModTime(rs.getDate("TIMESTAMP").toString());
			fileVersionVO.setModifiedByName(rs.getString("FIRST_NAME") + " "
					+ rs.getString("LAST_NAME"));
			return fileVersionVO;
		} else if (calledFunction == GET_FILE) {
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
		}

		return null;
	}

}
