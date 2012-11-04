package com.asu.edu.constants;

public interface SQLConstants {

	public static final String USER_LOGIN = "select * from user where USER_NAME=? AND PASSWORD=?";
	public static final String USER_ROLE = "SELECT U.USER_NAME, R.DESC FROM user U, roles R WHERE U.ROLE_ID = R.ID AND U.USER_NAME = ?";
	public static final String USER_DEPT = "select dept_id from user_dept where user_id=?";
	public static final String GET_USER_ID = "select id from user where user_name=?";
	public static final String ADD_DEPT_FOR_USER = "insert into user_dept values ";

	public static final String PENDING_USERS = "SELECT U.USER_NAME, U.ID, R.DESC, D.NAME FROM user U, roles R,user_dept UD, department D WHERE U.ID = UD.USER_ID AND UD.DEPT_ID = D.ID AND U.ROLE_ID = R.ID AND U.IS_APPROVED = ?;";

	public static final String USERS_BY_ROLE = "SELECT U.USER_NAME, D.NAME FROM user U, roles R, department D WHERE D.ID = U.DEPT_ID AND U.ROLE_ID = R.ID AND R.ID = ?;";

	public static final String LOG_FILES = "SELECT L.PATH, DATE_FORMAT(L.TIMESTAMP, '%d/%m/%Y') AS TIMESTAMP FROM DOCKLOUD.LOGS L WHERE ?";

	public static final String APPROVE_USER = "UPDATE user SET IS_APPROVED = ? WHERE ID = ?;";

	public static final String REJECT_USER = "DELETE FROM user WHERE ID = ?;";

	public static final String USER_REG = "insert into user(user_name,password,first_name,last_name,email,role_id,is_approved,login_attempts) values(?,?,?,?,?,?,?,?)";

	public static final String GET_DEPTARTMENTS = "select * from department";

	public static final String GET_ROLES = "select * from roles";

	/* Dashboard DAO */
	public static final String GET_REGULAR_USER_FILES = "select * from files where owner_id=? and parent_id=?";
	public static final String GET_DEPT_MANAGER_FILES = "select * from files F inner join user U on F.owner_id=U.id where F.dept_id=? and F.parent_id=? and U.role_id!=5";
	public static final String GET_CORPORATE_MANAGER_FILES = "select * from files where dept_id=? and parent_id=?";
	
	/*file upload download */
	public static final String GET_FILE_FOR_DOWNLOAD = "select * from files where file_id=?";

	public static final String GET_FILE_PATH = "select path from files where parent_id=?";

	public static final String SAVE_FILE = "inset into files(path,owner_id,dept_id,parent_id,file_name,creation_time,type) values(?,?,?,?,?,?,?)";

}
