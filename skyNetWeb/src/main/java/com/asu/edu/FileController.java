package com.asu.edu;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.asu.edu.base.dao.intrf.FileDAOImplInterface;
import com.asu.edu.base.vo.FileVO;
import com.asu.edu.base.vo.UserVO;
import com.asu.edu.constants.CommonConstants;
import com.asu.edu.security.EncryptDecrypt;

@Controller
public class FileController {

	@Autowired
	private FileDAOImplInterface fileDAO = null;
	EncryptDecrypt util = new EncryptDecrypt();
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(HttpServletRequest request,
			@RequestParam("dept-id") String dept_Id,
			@RequestParam("parent-file-id") String parent_Id,
			HttpServletResponse response, HttpSession session) {
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile multipartFile = multipartRequest.getFile("file");
		//int deptId = Integer.parseInt(util.decrypt(dept_Id));
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

		return "redirect:/Dashboard?deptId="+deptId+"&folderId="+parentId;
	}

	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public void download(HttpServletRequest request,
			HttpServletResponse response,@RequestParam("id") String Id) {
		int id = Integer.parseInt(util.decrypt(Id));
		FileVO fileVO = (FileVO) fileDAO.getFile(id);
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
	
	@RequestMapping(value = "/checkout", method = RequestMethod.POST)
	private String checkOut(@RequestParam("id") String Id,
			@RequestParam("dept-id") String dept_Id,
			@RequestParam("parent-file-id") String parent_Id, HttpSession session) {
			int id  = Integer.parseInt(util.decrypt(Id));
			//int deptId = Integer.parseInt(util.decrypt(dept_Id));
			int deptId = Integer.parseInt(dept_Id);
			int parentId = Integer.parseInt(util.decrypt(parent_Id));
			Object[] param = new Object[1];
			param[0]=id;
			//Authorization for			
			fileDAO.lock(param);
			
		
		return "redirect:/Dashboard?deptId="+deptId+"&folderId="+parentId;
		
		
	}
}
