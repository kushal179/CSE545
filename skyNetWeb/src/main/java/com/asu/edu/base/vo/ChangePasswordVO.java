package com.asu.edu.base.vo;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class ChangePasswordVO {

	private String userName;

	private String oldPassword;

	@NotEmpty(message = "New Password must not be blank.")
	@Size(min = 6, max = 20, message = "Size should be between 6-20")
	@Pattern(regexp="(?!^[0-9]*$)(?!^[a-zA-Z!@#$%^&*()_+=<>?]*$)^([a-zA-Z!@#$%^&*()_+=<>?0-9]{6,15})$", message="password must be alphanumeric")
	private String newPassword;
	
	@NotEmpty(message = "New Password must not be blank.")
	@Size(min = 6, max = 20, message = "Size should be between 6-20")
	@Pattern(regexp="(?!^[0-9]*$)(?!^[a-zA-Z!@#$%^&*()_+=<>?]*$)^([a-zA-Z!@#$%^&*()_+=<>?0-9]{6,15})$", message="password must be alphanumeric")
	private String rePassword;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getRePassword() {
		return rePassword;
	}

	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}
}
