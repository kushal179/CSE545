package com.asu.edu.constants;

public interface SQLConstants {

	public static final String USER_LOGIN = "select * from user where USER_NAME=? AND PASSWORD=?";
	public static final String USER_ROLE = "SELECT U.USER_NAME, R.DESC FROM user U, roles R WHERE U.ROLE_ID = R.ID AND U.USER_NAME = ?";
	public static final String USER_DEPT = "select dept_id from user_dept where user_id=?";
	public static final String GET_USER_ID = "select id from user where user_name=?";
	public static final String ADD_DEPT_FOR_USER = "insert into user_dept values ";

	public static final String USER_DEPTS = "select d.id, d.name from user_dept ud, department d where ud.dept_id = d.id AND ud.user_id = ?;";

	public static final String USERS_BY_ROLE = "select U.user_name, D.name from dockloud.user U, dockloud.department D, dockloud.user_dept U_D, dockloud.roles R where U.id=U_D.user_id and D.id=U_D.dept_id and R.id=U.role_id AND R.id = ?;";

	public static final String LOG_FILES = "SELECT L.path, DATE_FORMAT(L.timestamp, '%d/%m/%Y') AS timestamp FROM dockloud.logs L WHERE ?";

	public static final String USER_REG = "insert into user(user_name,password,first_name,last_name,email,role_id,is_approved,login_attempts) values(?,?,?,?,?,?,?,?)";

	public static final String GET_DEPTARTMENTS = "select * from department";

	public static final String GET_ROLES = "select * from roles";

	/* Dashboard DAO */
	public static final String GET_REGULAR_USER_FILES = "select * from files where owner_id=? and parent_id=?";
	public static final String GET_DEPT_MANAGER_FILES = "select * from files F inner join user U on F.owner_id=U.id where F.dept_id=? and F.parent_id=? and U.role_id!=5";
	public static final String GET_CORPORATE_MANAGER_FILES = "select * from files where dept_id=? and parent_id=?";

	/* Document versioning*/
	public static final String GET_FILE_INFO="select * from files where file_id=?";

	
	/* file upload ,download,check-in/out */
	public static final String GET_FILE_FOR_DOWNLOAD = "select * from files where file_id=?";

	public static final String GET_FILE_PATH = "select path from files where file_id=?";

	public static final String SAVE_FILE = "insert into files(path,owner_id,dept_id,parent_id,file_name,creation_time,type,mod_time) values(?,?,?,?,?,?,?,?)";

	public static final String LOCK_FILE = "update files f set f.lock = 1 where file_id=?";
	public static final String UNLOCK_FILE = "update files f set f.lock = 0 where file_id=?";
	public static final String DELETE = "delete from files f where f.file_id=? and f.lock=0";
	public static final String DELETE_DIR = "delete from files f where f.file_id=? and f.lock=0 and f.path like ?";

	public static final String IS_FILE_LOCK = "select * from files f where f.lock=1 and instr((select path from files f where f.file_id=?),f.path)";

	public static final String CHECKING_FILE_OWNERSHIP = "select * from files where owner_id=? and file_id=?";

	public static final String CHECKING_SHARING_RIGHTS = "select * from sharing where user_id_to=? and file_id=? and %=1";

	public static final String CHECKING_DOC_DEPT = "select dept_id from files where file_id=?";

	// Used by Bharath
	public static final String APPROVE_USER = "UPDATE user SET IS_APPROVED = ? WHERE ID = ?;";

	public static final String MODIFY_USER_ROLE = "update user set role_id = ? where id = ?;";

	public static final String REJECT_USER = "DELETE FROM user WHERE ID = ?;";

	public static final String PENDING_USERS = "select u.user_name, u.id, r.id, r.desc from user u, roles r where u.role_id = r.id and u.is_approved = ?;";

	public static final String DELETE_USER_DEPTS = "delete from user_dept WHERE user_id = ?;";

	// In share dailog populate approved, non admin and not current user
	public static final String SHARE_TO_USERS = "select u.user_name, u.id from user u where u.role_id <> ? and u.is_approved = ? and u.id <> ?;";

	public static final String GET_EMAIL_ID = "select email from user where user_name=?;";

	public static final String LOGIN_ATTEMPTS = "select u.login_attempts from user u where u.user_name = ?;";
	public static final String UPDATE_LOGIN_ATTEMPTS = "update dockloud.user set login_attempts = ? where user_name = ?;";
	// *********************************

	public static final String UPDATE_PASSWORD = "update user set password=? where user_name=?;";

	// Share items
	public static final String SHARE_SELECT_ITEM = "select * from sharing where file_id = ? and user_id_by = ? and user_id_to=?;";
	public static final String SHARE_INSERT_ITEM = "insert into sharing (file_id,user_id_by,user_id_to,download,file_update,checkin_out) values (?,?,?,?,?,?);";
	public static final String SHARE_UPDATE_ITEM = "update sharing s set s.download=? , s.file_update=? , s.checkin_out=? where s.file_id=? and s.user_id_by=? and s.user_id_to=?;";
	// *********************************
}
