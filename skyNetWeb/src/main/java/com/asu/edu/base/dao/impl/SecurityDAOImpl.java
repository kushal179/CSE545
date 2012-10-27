package com.asu.edu.base.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

import com.asu.edu.base.dao.BaseDAO;
import com.asu.edu.base.dao.intrf.SecurityDAOImplInterface;
import com.asu.edu.base.vo.UserVO;
import com.asu.edu.constants.SQLConstants;

public class SecurityDAOImpl extends BaseDAO implements SecurityDAOImplInterface,AuthenticationProvider {

	String calledFunction;
	@Override
	protected Object toDataObject(ResultSet rs) throws SQLException {
		
		if(calledFunction=="authenticate")
		{
			System.out.println("inside authenticate to data object");
			UserVO userVO = new UserVO();
			
			userVO.setId(rs.getInt("ID"));
			userVO.setUserName(rs.getString("USER_NAME"));
			userVO.setEmail(rs.getString("EMAIL"));
			userVO.setFirstName(rs.getString("FIRST_NAME"));
			userVO.setLastName(rs.getString("LAST_NAME"));
			userVO.setIsApproved(rs.getInt("IS_APPROVED"));
			userVO.setLoginAttempts(rs.getInt("LOGIN_ATTEMPTS"));
			userVO.setRoleId(rs.getInt("ROLE_ID"));
			userVO.setDeptId(rs.getInt("DEPT_ID"));
			
			return userVO;
		}
		
		if(calledFunction=="userRole")
		{
			System.out.println("inside userRole to data object");
			return rs.getString("DESC");
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


	@Override
	public Authentication authenticate(Authentication auth)
			throws AuthenticationException {

		calledFunction = "authenticate";
		Object[] prepareParams = new Object[2];
		prepareParams[0] = auth.getName();
		prepareParams[1] = auth.getCredentials();
		UserVO userVO = (UserVO)this.getRowByCriteria(SQLConstants.USER_LOGIN, prepareParams);
		if(userVO!=null)
		{
			System.out.println(userVO.getUserName());
			if(userVO.getUserName().equals(auth.getPrincipal()))
			{
				calledFunction = "userRole";
				Object[] param = new Object[1];
				param[0] = userVO.getUserName();
				String role = (String)this.getRowByCriteria(SQLConstants.USER_ROLE,param);
				List<GrantedAuthority> authoritites = new ArrayList<GrantedAuthority>();
				authoritites.add((new GrantedAuthorityImpl(role)));
				return new UsernamePasswordAuthenticationToken(auth.getName(), auth.getCredentials(), authoritites);
			}
		}
		
		return null;
	}


	@Override
	public boolean supports(Class<? extends Object> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}

	

}
