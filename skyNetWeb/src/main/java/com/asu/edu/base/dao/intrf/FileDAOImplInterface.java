package com.asu.edu.base.dao.intrf;

import javax.servlet.http.HttpServletRequest;

import com.asu.edu.base.vo.FileVO;

public interface FileDAOImplInterface {

	public FileVO getFile(HttpServletRequest request);

	public String getParentFilePath(int id);

	public int saveFile(FileVO fileVO);

}
