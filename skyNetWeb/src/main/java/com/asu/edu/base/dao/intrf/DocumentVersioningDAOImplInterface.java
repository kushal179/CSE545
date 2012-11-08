package com.asu.edu.base.dao.intrf;

import java.util.ArrayList;

import com.asu.edu.base.vo.DepartmentVO;
import com.asu.edu.base.vo.FileVO;
import com.asu.edu.base.vo.FileVersionVO;
import com.asu.edu.base.vo.UserVO;

public interface DocumentVersioningDAOImplInterface {

	public ArrayList<FileVersionVO> getDocumentVersions(UserVO user, long fileId);
}
