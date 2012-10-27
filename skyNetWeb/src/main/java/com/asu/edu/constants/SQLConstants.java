package com.asu.edu.constants;

public interface SQLConstants {
	
	public static final String USER_REG = "";
	
	public static final String USER_LOGIN = "select * from user where USER_NAME=? AND PASSWORD=?";
	public static final String USER_ROLE = "SELECT U.USER_NAME, R.DESC FROM USER U, ROLES R WHERE U.ROLE_ID = R.ID AND U.USER_NAME = ?";

}
