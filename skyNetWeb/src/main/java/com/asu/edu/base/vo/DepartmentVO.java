package com.asu.edu.base.vo;

public class DepartmentVO {
	
	private int id;
	private String deptName;
	private String deptDesc;
	private long rootFileId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDeptDesc() {
		return deptDesc;
	}
	public void setDeptDesc(String deptDesc) {
		this.deptDesc = deptDesc;
	}
	public long getRootFileId() {
		return rootFileId;
	}
	public void setRootFileId(long rootFileId) {
		this.rootFileId = rootFileId;
	}
	

}
