package com.asu.edu.base.vo;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class RegisterationVO {

	public static final String error = "Eror";

	@NotEmpty(message = "Username must not be blank.")
	@Size(min = 1, max = 20, message = "Size should be between 1-20")
	private String userName;
	@NotEmpty(message = "Password must not be blank.")
	@Size(min = 6, max = 20, message = "Size should be between 1-20")
	// @Pattern(regexp="(?!^[0-9]*$)(?!^[a-zA-Z!@#$%^&*()_+=<>?]*$)^([a-zA-Z!@#$%^&*()_+=<>?0-9]{6,15})$")
	private String password;
	@NotEmpty(message = "First Name must not be blank.")
	private String firstName;
	@NotEmpty(message = "Last Name must not be blank.")
	private String lastName;
	@NotEmpty(message = "Email must not be blank.")
	@Pattern(regexp = "^([a-z0-9]+)([._-]([0-9a-z_-]+))*@([a-z0-9]+)([._-]([0-9a-z]+))*([.]([a-z0-9]+){2,4})$", message = "Email-id is not valid")
	private String email;
	@NotEmpty(message = "Password must not be blank.")
	@Pattern(regexp="[0-9]+")
	private String department;
	@NotEmpty(message = "Password must not be blank.")
	@Pattern(regexp="[0-9]+")
	private String role;

	private String captcha;
	private int roleId;
	private int deptId;
	private int isApproved;
	private int loginAttempts;

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
		return role == null ? Integer.getInteger(role.trim()) : -1;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getDeptId() {
		return department == null ? Integer.getInteger(department.trim()) : -1;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
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

	/*
	 * public String getRePassword() { return rePassword; } public void
	 * setRePassword(String rePassword) { this.rePassword = rePassword; }
	 */
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

}
