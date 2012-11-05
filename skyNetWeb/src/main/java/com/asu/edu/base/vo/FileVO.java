package com.asu.edu.base.vo;

public class FileVO {

	private static final String ICON_PATH = "/edu/resources/icons/";
	
	private long id;
	private String fileName;
	private String type;
	private String path;
	private long ownerId;
	private int deptId;
	private String modTime;
	private String createTime;
	private boolean lock;
	private String password;
	private long parentId;
	private boolean isDir;
	private String iconFile;
	private String hyperlink;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getModTime() {
		return modTime;
	}

	public void setModTime(String modTime) {
		this.modTime = modTime;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public boolean getLock() {
		return lock;
	}

	public void setLock(boolean lock) {
		this.lock = lock;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean isDir() {
		return isDir;
	}

	public void setDir(boolean isDir) {
		this.isDir = isDir;
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

}
