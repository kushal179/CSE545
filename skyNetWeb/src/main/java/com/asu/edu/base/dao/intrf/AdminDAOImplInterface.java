package com.asu.edu.base.dao.intrf;

import java.util.ArrayList;

import com.asu.edu.base.vo.PendingUsersVO;

public interface AdminDAOImplInterface {

		public ArrayList<PendingUsersVO> getPendingUsers();
		
		public void approveUser(String userId);
		
		public void rejectUser(String userId);
}
