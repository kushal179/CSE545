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
import com.asu.edu.base.vo.ShareVO;
import com.asu.edu.base.vo.UserVO;
import com.asu.edu.cache.MasterCache;

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

	private Map<Integer, DepartmentVO> departmentMap;

	/**
	 * Simply selects the home view to render by returning its name.
	 */

	public DashboardController() {
		departmentMap = MasterCache.getDepartmentMap();
	}

	@RequestMapping(method = RequestMethod.GET)
	public String getDashBoardContents(@RequestParam("deptId") int deptId,
			@RequestParam("folderId") String folderId, HttpSession session,
			Map model) {
		logger.info("department Id = " + deptId);
		logger.info("Dashboard screen");

		UserVO user = (UserVO) session.getAttribute("userVO");
		long parentId;
		int departmentId;

		if(user.getRoleId() == ROLE_GUEST_USER){
			// HANDLE 
		}
		
		if (user != null) {
			/*if (deptId.equals("-1"))
				departmentId = user.getDepartments().get(0);
			else {
				byte[] decodedBytes = Base64.decode(deptId.getBytes());
				departmentId = Integer.valueOf(new String(decodedBytes));
			}*/
			
			if (deptId == -1)
				departmentId = user.getDepartments().get(0);
			else
				departmentId= deptId;

			if (folderId.equals("-1")) {
				parentId = departmentMap.get(departmentId).getRootFileId();
			} else {
				byte[] decodedBytes = Base64.decode(folderId.getBytes());
				parentId = Long.valueOf(new String(decodedBytes));
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
				break;
			}

			if (files != null) {
				updateHyperlinks(files);
			}

			model.put("files", files);
			model.put("deptId", departmentId);
			model.put("deptDesc", dept.getDeptDesc());
			model.put("parentFileId", folderId);
			model.put("shareVO", new ShareVO());
			model.put("approvedUsers", new ArrayList<UserVO>());

			return "documentManagement";
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
				encodedBytes = Base64.encode(String.valueOf(fileVO.getParentId())
						.getBytes("UTF8"));
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
