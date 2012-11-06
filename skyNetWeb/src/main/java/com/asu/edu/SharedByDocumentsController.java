package com.asu.edu;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.codec.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.asu.edu.base.dao.intrf.DashboardDAOImplInterface;
import com.asu.edu.base.vo.DepartmentVO;
import com.asu.edu.base.vo.FileVO;
import com.asu.edu.base.vo.UserVO;
import com.asu.edu.cache.MasterCache;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/SharedByDocements")
public class SharedByDocumentsController {

	private static final int ROLE_CORPORATE_MANAGER = 5;

	private static final int ROLE_DEPARTMENT_MANAGER = 4;

	private static final int ROLE_REGULAR_EMP = 3;

	private static final Logger logger = LoggerFactory
			.getLogger(SharedByDocumentsController.class);

	@Autowired
	private DashboardDAOImplInterface dashboardDAO = null;

	private Map<Integer, DepartmentVO> departmentMap;

	/**
	 * Simply selects the home view to render by returning its name.
	 */

	public SharedByDocumentsController() {
		departmentMap = MasterCache.getDepartmentMap();
	}

	@RequestMapping(method = RequestMethod.GET)
	public String getDashBoardContents(
			@RequestParam("folderId") String folderId, HttpSession session,
			Map model) {
		logger.info("Dashboard screen");

		UserVO user = (UserVO) session.getAttribute("userVO");
		long parentId;
		int departmentId;

		if (user != null) {
			/*
			 * if (deptId.equals("-1")) departmentId =
			 * user.getDepartments().get(0); else { byte[] decodedBytes =
			 * Base64.decode(deptId.getBytes()); departmentId =
			 * Integer.valueOf(new String(decodedBytes)); }
			 */

			if (folderId.equals("-1")) {
				parentId = Long.parseLong(folderId);
			} else {
				byte[] decodedBytes = Base64.decode(folderId.getBytes());
				parentId = Long.valueOf(new String(decodedBytes));
			}

			ArrayList<FileVO> files = null;

			files = dashboardDAO.getSharedByDocuments(user, parentId);

			if (files != null)
				updateHyperlinks(files);

			model.put("files", files);
			model.put("parentFileId", parentId);

			return "sharedDocuments";
		}

		return "redirect:/login";
	}

	private void updateHyperlinks(ArrayList<FileVO> files) {

		for (FileVO fileVO : files) {
			byte[] encodedBytes = null;
			try {
				encodedBytes = Base64.encode(String.valueOf(fileVO.getId())
						.getBytes("UTF8"));
				fileVO.setHashedId(new String(encodedBytes));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				encodedBytes = Base64.encode(String.valueOf(
						fileVO.getParentId()).getBytes("UTF8"));
				fileVO.setHashedParentId(new String(encodedBytes));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (fileVO.isDir())
				fileVO.setHyperlink("Dashboard?deptId=" + fileVO.getDeptId()
						+ "&folderId=" + fileVO.getHashedId());
			else
				fileVO.setHyperlink("download?id=" + fileVO.getHashedId());
		}

	}

	@ExceptionHandler(IOException.class)
	public String handleIOException(IOException ex, HttpServletRequest request) {

		System.out.println("in exceptopn handler");
		return "documentManagement";
	}

	static {

	}
}