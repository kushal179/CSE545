package com.asu.edu.base.dao.intrf;

import java.util.ArrayList;

import com.asu.edu.base.vo.PendingUsersVO;
import com.asu.edu.base.vo.RegisterationVO;
import com.asu.edu.base.vo.LogFilesVO;
public interface AdminDAOImplInterface {

		public ArrayList<PendingUsersVO> getPendingUsers();
		
		public void approveUser(String userId);
		
		public void rejectUser(String userId);
		public ArrayList<RegisterationVO> getUsersByRole(int role_id);
		public ArrayList<LogFilesVO> getLogFiles();
}
