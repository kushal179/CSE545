package com.asu.edu.base.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.asu.edu.base.dao.intrf.AdminDAOImplInterface;
import com.asu.edu.base.dao.BaseDAO;
import com.asu.edu.base.vo.PendingUsersVO;
import com.asu.edu.constants.SQLConstants;

public class AdminDAOImpl extends BaseDAO implements AdminDAOImplInterface{

	String calledFunction;
	
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<PendingUsersVO> getPendingUsers() {
		calledFunction = "pendingUsers";
		Integer isApproved = 0;
		Object[] param = new Object[1];
		param[0] = isApproved;
		String sql  = SQLConstants.PENDING_USERS;
		ArrayList<PendingUsersVO> result = (ArrayList<PendingUsersVO>)this.getListByCriteria(sql, param);
		
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
		String sql  = SQLConstants.APPROVE_USER;
		this.preparedStatementUpdate(sql, param, true);		
	}
	
	@Override
	public void rejectUser(String userId) {
		calledFunction = "rejectUSer";
		Integer id = Integer.parseInt(userId);
		Object[] param = new Object[1];
		param[0] = id;
		String sql  = SQLConstants.REJECT_USER;
		this.preparedStatementUpdate(sql, param, true);		
	}

	@Override
	protected Object toDataObject(ResultSet rs) throws SQLException {
		if(calledFunction=="pendingUsers")
		{
				System.out.println("inside to data object");
				PendingUsersVO vo = new PendingUsersVO();
				vo.setUserName(rs.getString("USER_NAME"));
				vo.setUserId(rs.getInt("ID"));
				vo.setRoleDesc(rs.getString("DESC"));
				vo.setDeptName(rs.getString("NAME"));
				
				return vo;
		}
		return null;	
	}

}
