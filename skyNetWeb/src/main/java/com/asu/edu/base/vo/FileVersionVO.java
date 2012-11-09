package com.asu.edu.base.vo;

public class FileVersionVO {

	private static final String ICON_PATH = "resources/icons/";

	private long id;
	private String hashedId;
	private String versionId;
	private String hashedVersionId;
	private String fileName;
	private String path;
	private long ownerId;
	private int deptId;
	private String modTime;
	private String iconFile = ICON_PATH + "file.jpg";
	private String hyperlink;
	private String modifiedByName;
	private String type;

	public void setId(long l) {
		this.id = l;
	}

	public long getId() {
		return id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
		if (fileName.matches(".*(.jpg|.JPG|.gif|.GIF|.png|.PNG)")) {
			setIconFile(ICON_PATH + "image.jpg");
			setType("Image file");
		} else if (fileName.matches(".*(.doc|.DOC)")) {
			setIconFile(ICON_PATH + "doc.jpg");
			setType("Word Doc");
		} else if (fileName.matches(".*(.pdf|.PDF)")) {
			setIconFile(ICON_PATH + "pdf.jpg");
			setType("PDF Doc");
		} else if (fileName.matches(".*(.ppt|.PPT|.pptx|.PPTX)")) {
			setIconFile(ICON_PATH + "ppt.jpg");
			setType("Presentation");
		} else if (fileName.matches(".*(.xls|.XLS|.xlsx|.XLSX)")) {
			setIconFile(ICON_PATH + "xls.jpg");
			setType("Excel Doc");
		} else if (fileName.matches(".*(.txt|.TXT)")) {
			setIconFile(ICON_PATH + "txt.jpg");
			setType("Text Doc");
		} else if (fileName.matches(".*[.].+")) {
			setIconFile(ICON_PATH + "file.jpg");
			setType("File");
		} else {
			setIconFile(ICON_PATH + "folder.png");
			setType("Folder");
		}
	}

	public String getModTime() {
		return modTime;
	}

	public void setModTime(String modTime) {
		this.modTime = modTime;
	}

	public long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(long ownerId) {
		this.ownerId = ownerId;
	}

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getIconFile() {
		return iconFile;
	}

	public void setIconFile(String iconFile) {
		this.iconFile = iconFile;
	}

	public String getHyperlink() {
		return hyperlink;
	}

	public void setHyperlink(String hyperlink) {
		this.hyperlink = hyperlink;
	}

	public String getHashedId() {
		return hashedId;
	}

	public void setHashedId(String hashedId) {
		this.hashedId = hashedId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVersionId() {
		return versionId;
	}

	public void setVersionId(String string) {
		this.versionId = string;
	}

	public String getHashedVersionId() {
		return hashedVersionId;
	}

	public void setHashedVersionId(String hashedVersionId) {
		this.hashedVersionId = hashedVersionId;
	}

	public String getModifiedByName() {
		return modifiedByName;
	}

	public void setModifiedByName(String modifiedByName) {
		this.modifiedByName = modifiedByName;
	}

}
