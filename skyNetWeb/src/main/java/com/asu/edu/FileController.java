package com.asu.edu;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.asu.edu.base.dao.intrf.FileDAOImplInterface;
import com.asu.edu.base.vo.FileVO;
import com.asu.edu.base.vo.PendingUsersVO;
import com.asu.edu.base.vo.ShareVO;
import com.asu.edu.base.vo.UserVO;
import com.asu.edu.constants.CommonConstants;
import com.asu.edu.security.EncryptDecrypt;
import com.asu.edu.util.Authorization;

@Controller
public class FileController {

	@Autowired
	private FileDAOImplInterface fileDAO = null;

	@Autowired
	private Authorization auth = null;

	EncryptDecrypt util = new EncryptDecrypt();

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(HttpServletRequest request,
			@RequestParam("dept-id") String dept_Id,
			@RequestParam("parent-file-id") String parent_Id,
			HttpServletResponse response, HttpSession session) {

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile multipartFile = multipartRequest.getFile("file");
		// int deptId = Integer.parseInt(util.decrypt(dept_Id));
		int deptId = Integer.parseInt(dept_Id);
		int parentId = Integer.parseInt(util.decrypt(parent_Id));
		FileVO fileVO = new FileVO();
		fileVO.setFileName(multipartFile.getOriginalFilename());
		fileVO.setContentType(multipartFile.getContentType());
		fileVO.setOwnerId(((UserVO) session.getAttribute(CommonConstants.USER))
				.getId());
		try {
			fileVO.setDeptId(deptId);

			fileVO.setParentId(parentId);
			String path = fileDAO.getParentFilePath(parentId);
			path = path + "/" + multipartFile.getOriginalFilename();
			fileVO.setPath(path);
			if (fileDAO.saveFile(fileVO)) {
				FileOutputStream f = new FileOutputStream(path);
				f.write(multipartFile.getBytes());
				f.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "redirect:/Dashboard?deptId=-1&folderId=-1";
	}

	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public void download(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("id") String file_Id,HttpSession session) {
		long id = Long.parseLong(util.decrypt(file_Id));
		HashMap<String, String> param = new HashMap<String, String>();
		param.put(CommonConstants.REQ_PARAM_FILE_ID, file_Id);

		if (auth.isAuthorize(CommonConstants.CHECKIN_OUT, session, param)) {
		FileVO fileVO = (FileVO) fileDAO.getFile(id);
		if(fileVO!=null)
		{
		response.setContentType(fileVO.getContentType());
		response.setHeader("Content-Disposition", "attachment;filename="
				+ fileVO.getFileName());
		File file = new File(fileVO.getPath());
		FileInputStream fileIn;
		ServletOutputStream out;
		try {
			fileIn = new FileInputStream(file);
			out = response.getOutputStream();
			byte[] outputByte = new byte[4096];
			while (fileIn.read(outputByte, 0, 4096) != -1) {
				out.write(outputByte, 0, 4096);
			}

			fileIn.close();
			out.flush();
			out.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
		else{
			System.out.println("File does not exist");
		}
		}
		else{
			System.out.println("Not Authorized");
		}

	}
	
	@RequestMapping(value = "/downloadlogfile", method = RequestMethod.GET)
	public void downloadlogfile(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("logfilename") String fileName) {
		response.setContentType(null);
		response.setHeader("Content-Disposition", "attachment;filename="
				+ fileName);
		File file = new File(CommonConstants.LOG_FILES_PATH + "/" + fileName);
		FileInputStream fileIn;
		ServletOutputStream out;
		try {
			fileIn = new FileInputStream(file);
			out = response.getOutputStream();
			byte[] outputByte = new byte[4096];
			while (fileIn.read(outputByte, 0, 4096) != -1) {
				out.write(outputByte, 0, 4096);
			}

			fileIn.close();
			out.flush();
			out.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@RequestMapping(value = "/lock", method = RequestMethod.POST)
	private String checkOut(@RequestParam("id") String file_Id,

	@RequestParam("dept-id") String dept_Id,
			@RequestParam("parent-file-id") String parent_Id,
			HttpSession session) {
		HashMap<String, String> param = new HashMap<String, String>();
		param.put(CommonConstants.REQ_PARAM_FILE_ID, file_Id);
		param.put(CommonConstants.REQ_PARAM_DEPTID, dept_Id);
		param.put(CommonConstants.REQ_PARAM_PARENTID, parent_Id);

		if (auth.isAuthorize(CommonConstants.CHECKIN_OUT, session, param)) {

			Object[] sqlParam = new Object[1];
			sqlParam[0] = Long.parseLong(util.decrypt(file_Id));
			if (!fileDAO.isLock(sqlParam))
				fileDAO.lock(sqlParam);
			else
				return "redirect:/error-page?error=File already locked";
		} else {
			return "redirect:/error-page?error=Not Authorized";
		}

		return "redirect:/Dashboard?deptId=-1&folderId=-1";

	}

	@RequestMapping(value = "/unlock", method = RequestMethod.POST)
	private String checkIn(@RequestParam("id") String file_Id,
			@RequestParam("dept-id") String dept_Id,
			@RequestParam("parent-file-id") String parent_Id,
			HttpSession session) {
		HashMap<String, String> param = new HashMap<String, String>();
		param.put(CommonConstants.REQ_PARAM_FILE_ID, file_Id);
		param.put(CommonConstants.REQ_PARAM_DEPTID, dept_Id);
		param.put(CommonConstants.REQ_PARAM_PARENTID, parent_Id);

		if (auth.isAuthorize(CommonConstants.CHECKIN_OUT, session, param)) {

			Object[] sqlParam = new Object[1];
			sqlParam[0] = Long.parseLong(util.decrypt(file_Id));
			if (fileDAO.unLock(sqlParam))
				;
			else
				return "redirect:/error-page?error=File Could not be unlocked";
		} else {
			return "redirect:/error-page?error=Not Authorized";
		}

		return "redirect:/Dashboard?deptId=-1&folderId=-1";

	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	private String delete(@RequestParam("id") String file_Id,
			@RequestParam("dept-id") String dept_Id,
			@RequestParam("parent-file-id") String parent_Id,
			HttpSession session) {
		HashMap<String, String> param = new HashMap<String, String>();
		param.put(CommonConstants.REQ_PARAM_FILE_ID, file_Id);
		param.put(CommonConstants.REQ_PARAM_DEPTID, dept_Id);
		param.put(CommonConstants.REQ_PARAM_PARENTID, parent_Id);

		if (auth.isAuthorize(CommonConstants.DELETE, session, param)) {
			long Idfile = Long.parseLong(util.decrypt(file_Id));
			Object[] sqlParam = new Object[1];
			sqlParam[0] = Idfile;
			if (!fileDAO.isLock(sqlParam)) {
				FileVO vo = (FileVO) fileDAO.getFile(Idfile);
				String path = vo.getPath();
				File f1 = new File(path);
				boolean isDir = f1.isDirectory();
				if (f1.delete()) {
					if (isDir) {
						sqlParam = new Object[2];
						sqlParam[0] = Idfile;
						sqlParam[1] = path + "%";
						fileDAO.deleteDir(sqlParam);
					} else
						fileDAO.delete(sqlParam);
				} else
					return "redirect:/error-page?error=File Could not be deleted";
			} else {
				return "redirect:/error-page?error=File Could not be deleted since locked";
			}
		} else {
			return "redirect:/error-page?error=Not Authorized";
		}

		return "redirect:/Dashboard?deptId=-1&folderId=-1";

	}

	@RequestMapping(value = "/shareComponent", method = RequestMethod.POST)
	public String shareItem(@ModelAttribute("shareVO") ShareVO shareVO,
			@RequestParam("dept-id") int deptId,
			BindingResult result,
			ServletRequest servletRequest, Map<String, Object> model,
			HttpSession session) {
		boolean shareresult = fileDAO
				.shareItem(shareVO, ((UserVO) (session
						.getAttribute(CommonConstants.USER))).getId());

		return "redirect:/Dashboard?deptId=-1&folderId=-1";
	}
}
