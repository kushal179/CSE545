package com.asu.edu.base.dao.intrf;

import com.asu.edu.base.vo.FileVO;

public interface FileDAOImplInterface {

	public FileVO getFile(int id);

	public String getParentFilePath(int id);

	public boolean saveFile(FileVO fileVO);
	
	public boolean lock(Object[] param);
	
	public boolean lockAuthorization(Object[] param);

}
