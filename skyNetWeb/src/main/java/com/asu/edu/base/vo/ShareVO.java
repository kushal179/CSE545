package com.asu.edu.base.vo;

import java.util.ArrayList;

public class ShareVO {

	private String itemhashedId;

	private Integer toUserId;

	private ArrayList<Integer> permissions;

	public String getItemhashedId() {
		return itemhashedId;
	}

	public void setItemhashedId(String itemhashedId) {
		this.itemhashedId = itemhashedId;
	}

	public Integer getToUserId() {
		return toUserId;
	}

	public void setToUserId(Integer toUserId) {
		this.toUserId = toUserId;
	}

	public ArrayList<Integer> getPermissions() {
		return permissions;
	}

	public void setPermissions(ArrayList<Integer> permissions) {
		this.permissions = permissions;
	}
}
