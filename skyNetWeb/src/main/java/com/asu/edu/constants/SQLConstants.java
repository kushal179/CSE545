package com.asu.edu.constants;

public interface SQLConstants {
	
	public static final String USER_REG = "";
	
	public static final String USER_LOGIN = "select * from user where USER_NAME=? AND PASSWORD=?";
	public static final String USER_ROLE = "SELECT U.USER_NAME, R.DESC FROM USER U, ROLES R WHERE U.ROLE_ID = R.ID AND U.USER_NAME = ?";

	public static final String PENDING_USERS = "SELECT U.USER_NAME, R.DESC, D.NAME FROM DOCKLOUD.USER U, DOCKLOUD.ROLES R, DOCKLOUD.DEPARTMENT D WHERE D.ID = U.DEPT_ID AND U.ROLE_ID = R.ID AND U.IS_APPROVED = ?;";


}
