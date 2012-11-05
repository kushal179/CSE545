package com.asu.edu.base.vo;

import java.util.ArrayList;

public class PendingUsersVO {

	private String userName;
	private String roleDesc;
	private Integer userId;
	private Integer roleId;
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	private ArrayList<String> deptNames;
	private ArrayList<Integer> deptIds;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRoleDesc() {
		return roleDesc;
	}
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public ArrayList<String> getDeptNames() {
		return deptNames;
	}
	public void setDeptNames(ArrayList<String> deptNames) {
		this.deptNames = deptNames;
	}
	public ArrayList<Integer> getDeptIds() {
		return deptIds;
	}
	public void setDeptIds(ArrayList<Integer> deptIds) {
		this.deptIds = deptIds;
	}
	
}