package com.asu.edu.base.vo;

public class FileVO {

	private static final String ICON_PATH = "resources/icons/";
	
	private long id;
	private String hashedId;
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
	private String hashedParentId;
	private boolean dir;
	private String iconFile;
	private String hyperlink;
	private String contentType;
	private long sharedById;
	private long sharedToId;
	private String sharedByName;
	private String sharedToName;
	private boolean updateAllowed;
	private boolean lockAllowed;
	
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

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	public String getHashedId() {
		return hashedId;
	}

	public void setHashedId(String hashedId) {
		this.hashedId = hashedId;
	}
	
	public String getHashedParentId() {
		return hashedParentId;
	}

	public void setHashedParentId(String hashedParentId) {
		this.hashedParentId = hashedParentId;
	}

	public String getSharedToName() {
		return sharedToName;
	}

	public void setSharedToName(String sharedToName) {
		this.sharedToName = sharedToName;
	}

	public String getSharedByName() {
		return sharedByName;
	}

	public void setSharedByName(String sharedByName) {
		this.sharedByName = sharedByName;
	}

	public long getSharedById() {
		return sharedById;
	}

	public void setSharedById(long sharedById) {
		this.sharedById = sharedById;
	}

	public long getSharedToId() {
		return sharedToId;
	}

	public void setSharedToId(long sharedToId) {
		this.sharedToId = sharedToId;
	}

	public boolean isLockAllowed() {
		return lockAllowed;
	}

	public void setLockAllowed(boolean lockAllowed) {
		this.lockAllowed = lockAllowed;
	}

	public boolean isUpdateAllowed() {
		return updateAllowed;
	}

	public void setUpdateAllowed(boolean updateAllowed) {
		this.updateAllowed = updateAllowed;
	}

	public boolean isDir() {
		return dir;
	}

	public void setDir(boolean dir) {
		this.dir = dir;
	}

}
