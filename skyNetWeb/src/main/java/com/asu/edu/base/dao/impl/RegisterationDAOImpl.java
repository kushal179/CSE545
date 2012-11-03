package com.asu.edu.base.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Component;

import com.asu.edu.RegisterationController;
import com.asu.edu.base.dao.BaseDAO;
import com.asu.edu.base.dao.intrf.RegisterationDAOImplInterface;
import com.asu.edu.base.vo.RegisterationVO;
import com.asu.edu.constants.SQLConstants;

@Component("Registeration")
public class RegisterationDAOImpl extends BaseDAO implements
		RegisterationDAOImplInterface {

	@Autowired
	private ShaPasswordEncoder passwordEncoder;

	private static final int LOGIN_ATTEMPTS_POS = 7;
	private static final int IS_APPROVED_POS = 6;
	private static final int ROLE_ID_POS = 5;
	private static final int EMAIL_POS = 4;
	private static final int LAST_NAME_POS = 3;
	private static final int FIRST_NAME_POS = 2;
	private static final int PASSWORD_POS = 1;
	private static final int USER_NAME_POS = 0;
	String calledFunction;

	@Override
	protected Object toDataObject(ResultSet rs) throws SQLException {

		if (calledFunction.equalsIgnoreCase("getUser")) {
			return rs.getInt("id");
		}
		return null;
	}

	@Override
	public boolean registerUser(RegisterationVO user) {
		calledFunction = "registerUser";
		Object[] param = new Object[8];
		param[USER_NAME_POS] = user.getUserName();

		String saltedPassword = passwordEncoder.encodePassword(
				user.getPassword(), user.getUserName());

		param[PASSWORD_POS] = saltedPassword;
		param[FIRST_NAME_POS] = user.getFirstName();
		param[LAST_NAME_POS] = user.getLastName();
		param[EMAIL_POS] = user.getEmail();
		param[ROLE_ID_POS] = user.getRoleId();
		Logger logger = LoggerFactory.getLogger(RegisterationController.class);
		logger.info("role is : " + user.getRole() + "  " + user.getRoleId());
		param[IS_APPROVED_POS] = false;
		param[LOGIN_ATTEMPTS_POS] = 0;
		String sql = SQLConstants.USER_REG;

		// Query Successful
		boolean userCreated = preparedStatementUpdate(sql, param, true) > 0;

		calledFunction = "getUser";
		param = new Object[1];
		param[0] = user.getUserName();
		sql = SQLConstants.GET_USER_ID;
		long userId = ((ArrayList<Integer>) getListByCriteria(sql, param))
				.get(0);

		calledFunction = "addToDept";
		ArrayList<Integer> depts = user.getDepartments();
		ArrayList<Integer> deptsArray = new ArrayList<Integer>();
		for (int i = 0; i < depts.size(); i++) {
			if (depts.get(i) > 0)
				deptsArray.add(depts.get(i));
		}

		param = new Object[deptsArray.size() * 2];
		sql = SQLConstants.ADD_DEPT_FOR_USER;
		for (int i = 0; i < deptsArray.size(); i++) {
			sql += "(?,?)";
			if (i < deptsArray.size() - 1)
				sql += " , ";
			param[2*i] = userId;
			param[2*i + 1] = deptsArray.get(i);
		}
		boolean deptUpdated = preparedStatementUpdate(sql, param, true) > 0;

		if (userCreated && deptUpdated)
			return true;

		// Query failed
		return false;
	}

	public ShaPasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

	public void setPasswordEncoder(ShaPasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
}
