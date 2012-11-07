package com.asu.edu.base.vo;

public class LogFilesVO {
	private String pathName;
	private String modifiedDate;
	private String hyperLink;
	
	public String getHyperLink() {
		return hyperLink;
	}
	public void setHyperLink(String hyperLink) {
		this.hyperLink = hyperLink;
	}
	public String getPathName() {
		return pathName;
	}
	public void setPathName(String pathName) {
		this.pathName = pathName;
	}
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getModifiedDate() {
		return modifiedDate;
	}
}
