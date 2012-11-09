package com.asu.edu;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.codec.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.asu.edu.base.dao.intrf.DashboardDAOImplInterface;
import com.asu.edu.base.dao.intrf.FileDAOImplInterface;
import com.asu.edu.base.vo.DepartmentVO;
import com.asu.edu.base.vo.FileVO;
import com.asu.edu.base.vo.ShareVO;
import com.asu.edu.base.vo.UserVO;
import com.asu.edu.cache.MasterCache;
import com.asu.edu.constants.CommonConstants;
import com.asu.edu.security.EncryptDecrypt;

/**
 * Handles requests for the application home page.
 */
@Controller
public class SharedByDocumentsController {

	private static final int ROLE_CORPORATE_MANAGER = 5;

	private static final int ROLE_DEPARTMENT_MANAGER = 4;

	private static final int ROLE_REGULAR_EMP = 3;

	private static final Logger logger = LoggerFactory
			.getLogger(SharedByDocumentsController.class);

	@Autowired
	private DashboardDAOImplInterface dashboardDAO = null;

	@Autowired
	private FileDAOImplInterface fileDAO = null;

	private EncryptDecrypt encryptDecrypt;

	private Map<Integer, DepartmentVO> departmentMap;

	/**
	 * Simply selects the home view to render by returning its name.
	 */

	public SharedByDocumentsController() {
		departmentMap = MasterCache.getDepartmentMap();
		encryptDecrypt = new EncryptDecrypt();
	}

	@RequestMapping(value = "/SharedByYouDocuments", method = RequestMethod.GET)
	public String getDashBoardContents(
			@RequestParam("folderId") String folderId, HttpSession session,
			Map model) {
		logger.info("Dashboard screen");

		UserVO user = (UserVO) session.getAttribute("userVO");
		long parentId;
		int departmentId;

		if (user != null) {

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

			return "shared-by-documents";
		}

		return "redirect:/login";
	}

	private void updateHyperlinks(ArrayList<FileVO> files) {

		for (FileVO fileVO : files) {
			byte[] encodedBytes = null;

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
				hashedParentId = URLEncoder
						.encode(encryptDecrypt.encrypt(String.valueOf(fileVO
								.getParentId())), "UTF-8");
				fileVO.setHashedParentId(hashedParentId);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (fileVO.isDir())
				try {
					fileVO.setHyperlink("SharedByYouDocuments?deptId="
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

	@ExceptionHandler(IOException.class)
	public String handleIOException(IOException ex, HttpServletRequest request) {

		System.out.println("in exceptopn handler");
		return "documentManagement";
	}

	@RequestMapping(value = "/unshare", method = RequestMethod.POST)
	public String unshareItem(@RequestParam("fileid") String fileid,
			@RequestParam("userIdTo") String userIdTo, HttpSession session,
			Map model) {

		Object[] sqlParam = new Object[1];
		EncryptDecrypt util = new EncryptDecrypt();
		sqlParam[0] = Long.parseLong(util.decrypt(fileid));
		if (!fileDAO.isLock(sqlParam)) {
			boolean unshareReult = fileDAO.unshareItem(fileid,
					((UserVO) (session.getAttribute(CommonConstants.USER)))
							.getId(), Long.parseLong(userIdTo));
		}
		else {
			return "redirect:/error-page?error=File already locked";
		}

		return "redirect:/SharedByYouDocuments?folderId=-1";
	}

}
