package com.asu.edu.base.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.asu.base.dao.intrf.SecurityDAOImplInterface;
import com.asu.edu.base.dao.BaseDAO;
import com.asu.edu.constants.SQLConstants;

public class SecurityDAOImpl extends BaseDAO implements SecurityDAOImplInterface{

	String calledFunction;
	@Override
	protected Object toDataObject(ResultSet rs) throws SQLException {
		
		if(calledFunction=="userId")
		{
			System.out.println("inside to data object");
			
				System.out.println("inside to data object");
				return rs.getString("password");
		}

		return null;
	}
	
	
	public String getUserId(String paramValue){
		calledFunction = "userId";
		Object[] param = new Object[1];
		param[0] = paramValue;
		String sql  = SQLConstants.USER_REG;
		String result = (String)this.getRowByCriteria(sql, param);
		
		return result;
	}

	

}
