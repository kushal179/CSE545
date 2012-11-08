package com.asu.edu.base.dao.intrf;

import java.util.ArrayList;

import org.springframework.security.config.authentication.UserServiceBeanDefinitionParser;

import com.asu.edu.base.vo.DepartmentVO;
import com.asu.edu.base.vo.FileVO;
import com.asu.edu.base.vo.FileVersionVO;
import com.asu.edu.base.vo.UserVO;

public interface DashboardDAOImplInterface {

	public ArrayList<FileVO> getRegularEmployeeFiles(UserVO userVO,
			DepartmentVO departmentVO, long folderId);

	public ArrayList<FileVO> getManagerFiles(UserVO userVO,
			DepartmentVO departmentVO, long folderId);

	public ArrayList<FileVO> getCorporateManagerFiles(UserVO userVO,
			DepartmentVO departmentVO, long folderId);

	public ArrayList<FileVO> getSharedByDocuments(UserVO userVO, long folderId);

	public ArrayList<UserVO> getapprovedNonAdminUsers(long userid);

	public ArrayList<FileVO> getSharedToDocuments(UserVO userVO, long folderId);
	
}
