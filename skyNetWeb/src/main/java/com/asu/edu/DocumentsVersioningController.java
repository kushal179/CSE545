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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.asu.edu.base.dao.intrf.DocumentVersioningDAOImplInterface;
import com.asu.edu.base.vo.FileVersionVO;
import com.asu.edu.base.vo.UserVO;
import com.asu.edu.security.EncryptDecrypt;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/versions")
public class DocumentsVersioningController {

	private static final Logger logger = LoggerFactory
			.getLogger(DocumentsVersioningController.class);

	@Autowired
	private DocumentVersioningDAOImplInterface documentVersioningDAO = null;

	private EncryptDecrypt encryptDecrypt;

	/**
	 * Simply selects the home view to render by returning its name.
	 */

	public DocumentsVersioningController() {
		encryptDecrypt = new EncryptDecrypt();
	}

	@RequestMapping(method = RequestMethod.POST)
	public String getDashBoardContents(@RequestParam("file-id") String fileId,
			HttpSession session, Map model) {
		logger.info("Dashboard screen");

		UserVO user = (UserVO) session.getAttribute("userVO");

		if (user != null) {

			long decryptedFileId = 0;
			if (fileId.equals("-1")) {
				decryptedFileId = Long.parseLong(fileId);
			} else {
				String file;
				try {
					file = encryptDecrypt.decrypt(fileId);
					decryptedFileId = Long.valueOf(file);
				} catch (SecurityException e) {
					e.printStackTrace();
				}
			}

			ArrayList<FileVersionVO> fileVersions = null;

			fileVersions = documentVersioningDAO.getDocumentVersions(user,
					decryptedFileId);

			if (fileVersions != null)
				updateHyperlinks(fileVersions);

			model.put("files", fileVersions);

			return "versioning-documents";
		}

		return "redirect:/login";
	}

	private void updateHyperlinks(ArrayList<FileVersionVO> files) {

		for (FileVersionVO fileVersionVO : files) {

			try {
				String hashedId = encryptDecrypt
						.encrypt(String.valueOf(fileVersionVO.getId()));
				fileVersionVO.setHashedId(hashedId);
			} catch (SecurityException e) {
				e.printStackTrace();
			}

			try {
				String hashedVersionId = encryptDecrypt
						.encrypt(String.valueOf(fileVersionVO.getVersionId()));
				fileVersionVO.setHashedVersionId(hashedVersionId);
			} catch (SecurityException e) {
				e.printStackTrace();
			}

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
