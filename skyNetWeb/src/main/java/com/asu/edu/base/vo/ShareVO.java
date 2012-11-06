package com.asu.edu.base.vo;

import java.util.ArrayList;

public class ShareVO {

	private String path;

	private String fromUser;

	private String toUser;

	private ArrayList<String> permissions;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getFromUser() {
		return fromUser;
	}

	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}

	public String getToUser() {
		return toUser;
	}

	public void setToUser(String toUser) {
		this.toUser = toUser;
	}

	public ArrayList<String> getPermissions() {
		return permissions;
	}

	public void setPermissions(ArrayList<String> permissions) {
		this.permissions = permissions;
	}
}
