package com.asu.edu.base.vo;

import java.util.ArrayList;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class UserVO {

	private long id;
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private int roleId;
	private ArrayList<Integer> departments;
	private int isApproved;
	private int loginAttempts;
	private String forgot_userName;
	private String oldPassword;
	private String newPassword;
	@NotEmpty(message = "Password must not be blank.")
	@Size(min = 6, max = 20, message = "Size should be between 6-20")
	private String rePassword;

	public String getRePassword() {
		return rePassword;
	}

	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getForgot_userName() {
		return forgot_userName;
	}

	public void setForgot_userName(String forgot_userName) {
		this.forgot_userName = forgot_userName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(int isApproved) {
		this.isApproved = isApproved;
	}

	public int getLoginAttempts() {
		return loginAttempts;
	}

	public void setLoginAttempts(int loginAttempts) {
		this.loginAttempts = loginAttempts;
	}

	public ArrayList<Integer> getDepartments() {
		return departments;
	}

	public void setDepartments(ArrayList<Integer> departments) {
		this.departments = departments;
	}

}
