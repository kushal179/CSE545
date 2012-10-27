package com.asu.edu.base.vo;

public class PendingUsersVO {

	private String userName;
	private String deptName;
	private String roleDesc;
	public String getRoleDesc() {
		return roleDesc;
	}
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserName() {
		return userName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDeptName() {
		return deptName;
	}
}