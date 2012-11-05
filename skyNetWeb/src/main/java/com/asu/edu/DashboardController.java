package com.asu.edu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
			@RequestParam("folderId") long folderId, HttpSession session,
			Map model) {
		logger.info("department Id = " + deptId);
		logger.info("Dashboard screen");

		UserVO user = (UserVO) session.getAttribute("userVO");

		if (user != null) {
			if (deptId == -1)
				deptId = user.getDepartments().get(0);
			if (folderId == -1)
				folderId = departmentMap.get(deptId).getRootFileId();

			ArrayList<FileVO> files = null;
			DepartmentVO dept = null;

			switch (user.getRoleId()) {
			case ROLE_REGULAR_EMP:
				dept = departmentMap.get(deptId);
				files = dashboardDAO.getRegularEmployeeFiles(user, dept,
						folderId);
				break;
			case ROLE_DEPARTMENT_MANAGER:
				dept = departmentMap.get(deptId);
				files = dashboardDAO.getManagerFiles(user, dept, folderId);
				break;
			case ROLE_CORPORATE_MANAGER:
				break;
			}

			updateHyperlinks(files);

			model.put("files", files);
			model.put("deptId", deptId);
			model.put("deptDesc", dept.getDeptDesc());
			model.put("parentFileId", folderId);

			return "documentManagement";
		}

		return "redirect:/login";
	}

	private void updateHyperlinks(ArrayList<FileVO> files) {
		for (FileVO fileVO : files) {
			if (fileVO.isDir())
				fileVO.setHyperlink("Dashboard?deptId="
						+ fileVO.getDeptId() + "&folderId=" + fileVO.getId());
			else
				fileVO.setHyperlink("download?id=" + fileVO.getId());
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
