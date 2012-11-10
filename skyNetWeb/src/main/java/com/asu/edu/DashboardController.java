package com.asu.edu;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jasypt.util.binary.BasicBinaryEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.asu.edu.base.dao.intrf.DashboardDAOImplInterface;
import com.asu.edu.base.vo.DepartmentVO;
import com.asu.edu.base.vo.FileVO;
import com.asu.edu.base.vo.ShareVO;
import com.asu.edu.base.vo.UserVO;
import com.asu.edu.cache.MasterCache;
import com.asu.edu.security.EncryptDecrypt;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/Dashboard")
public class DashboardController {

	private static final int ROLE_CORPORATE_MANAGER = 5;

	private static final int ROLE_DEPARTMENT_MANAGER = 4;

	private static final int ROLE_REGULAR_EMP = 3;

	private static final int ROLE_GUEST_USER = 2;

	private static final Logger logger = LoggerFactory
			.getLogger(DashboardController.class);

	@Autowired
	private DashboardDAOImplInterface dashboardDAO = null;

	private EncryptDecrypt encryptDecrypt;

	private Map<Integer, DepartmentVO> departmentMap;

	/**
	 * Simply selects the home view to render by returning its name.
	 */

	public DashboardController() {
		departmentMap = MasterCache.getDepartmentMap();
		encryptDecrypt = new EncryptDecrypt();
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET)
	public String getDashBoardContents(@RequestParam("deptId") int deptId,
			@RequestParam("folderId") String folderId, HttpSession session,
			Map model) {
		logger.info("department Id = " + deptId);
		logger.info("folder Id = " + folderId);
		logger.info("Dashboard screen");
		
		UserVO user = (UserVO) session.getAttribute("userVO");
		long parentId;
		int departmentId;

		BasicBinaryEncryptor b = new BasicBinaryEncryptor();
		
		if (user != null) {

			if (user.getRoleId() == ROLE_GUEST_USER) {
				// HANDLE
			}

			if (deptId == -1)
				departmentId = user.getDepartments().get(0);
			else
				departmentId = deptId;

			if (folderId.equals("-1")) {
				parentId = departmentMap.get(departmentId).getRootFileId();
			} else {
				parentId = Long.valueOf(encryptDecrypt.decrypt(folderId));
			}

			ArrayList<FileVO> files = null;
			DepartmentVO dept = null;

			switch (user.getRoleId()) {
			case ROLE_REGULAR_EMP:
				dept = departmentMap.get(departmentId);
				files = dashboardDAO.getRegularEmployeeFiles(user, dept,
						parentId);
				break;
			case ROLE_DEPARTMENT_MANAGER:
				dept = departmentMap.get(departmentId);
				files = dashboardDAO.getManagerFiles(user, dept, parentId);
				break;
			case ROLE_CORPORATE_MANAGER:
				dept = departmentMap.get(departmentId);
				files = dashboardDAO.getCorporateManagerFiles(user, dept,
						parentId);
				break;
			}

			if (files != null) {
				updateHyperlinks(files);
			}

			model.put("files", files);
			model.put("deptId", departmentId);
			model.put("deptDesc", dept.getDeptDesc());
			model.put("parentFileId",
					encryptDecrypt.encrypt(String.valueOf(parentId)));
			model.put("shareVO", new ShareVO());
			model.put("shareToUsers",
					dashboardDAO.getapprovedNonAdminUsers(user.getId()));

			return "documentManagement";
		}

		return "redirect:/login";
	}

	private void updateHyperlinks(ArrayList<FileVO> files) {

		for (FileVO fileVO : files) {

			String hashedId;
			try {
				hashedId = encryptDecrypt
						.encrypt(String.valueOf(fileVO.getId()));
				fileVO.setHashedId(hashedId);
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			String hashedParentId;
			try {
				hashedParentId = encryptDecrypt.encrypt(String.valueOf(fileVO
								.getParentId()));
				fileVO.setHashedParentId(hashedParentId);
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (fileVO.isDir())
				try {
					fileVO.setHyperlink("Dashboard?deptId="
							+ fileVO.getDeptId() + "&folderId="
							+ URLEncoder.encode(fileVO.getHashedId(), "UTF-8"));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			else
				fileVO.setHyperlink("download?id=" + fileVO.getHashedId());
		}
	}

	@ExceptionHandler(Exception.class)
	public String handleIOException(Exception ex, HttpServletRequest request) {

		System.out.println("in exceptopn handler");
		return "redirect:/error-page?error=Invalid state reached";
	}

}
