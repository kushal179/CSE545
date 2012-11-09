package com.asu.edu;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
import com.asu.edu.security.EncryptDecrypt;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/SharedToYouDocuments")
public class SharedToDocumentsController {

	private static final Logger logger = LoggerFactory
			.getLogger(SharedToDocumentsController.class);

	@Autowired
	private DashboardDAOImplInterface dashboardDAO = null;

	private EncryptDecrypt encryptDecrypt;

	/**
	 * Simply selects the home view to render by returning its name.
	 */

	public SharedToDocumentsController() {
		encryptDecrypt = new EncryptDecrypt();
	}

	@RequestMapping(method = RequestMethod.GET)
	public String getSharedToYouContents(
			@RequestParam("folderId") String folderId, HttpSession session,
			Map model) {
		logger.info("Dashboard screen");

		UserVO user = (UserVO) session.getAttribute("userVO");
		long parentId;

		if (user != null) {

			if (folderId.equals("-1")) {
				parentId = Long.parseLong(folderId);
			} else {
				parentId = Long.valueOf(encryptDecrypt.decrypt(folderId));
			}

			ArrayList<FileVO> files = null;

			files = dashboardDAO.getSharedToDocuments(user, parentId);

			if (files != null)
				updateHyperlinks(files);

			model.put("files", files);
			model.put("parentFileId", parentId);

			return "shared-to-documents";
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
					fileVO.setHyperlink("SharedToYouDocuments?deptId="
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
