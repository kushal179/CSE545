package com.asu.edu.base.dao.intrf;

import com.asu.edu.base.vo.FileVO;
import com.asu.edu.base.vo.ShareVO;
import com.asu.edu.base.vo.UserVO;
import com.asu.edu.constants.CommonConstants;

public interface FileDAOImplInterface {

	public FileVO getFile(long id);

	public String getParentFilePath(int id);

	public boolean saveFile(FileVO fileVO);

	public boolean lock(Object[] param);

	public boolean fileOwnerShipAuthorization(Object[] param);

	public boolean shareItem(ShareVO shareVO, Long fromUserId);

	public boolean hasAccessAuthorization(Object[] param, String sql);

	public int findDeptIdByDoc(Object[] param);

	public boolean isLock(Object[] param);

	public boolean unLock(Object[] param);

	public boolean delete(Object[] param);

	public boolean deleteDir(Object[] param);

	public int deptByParent(int parentId);

	public boolean saveFolder(FileVO fileVO);

	public boolean unshareItem(String fileid, Long byUserID, Long toUserId);

}
