package com.philip.edu.eval.schooluser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.philip.edu.eval.bean.BackendData;
import com.philip.edu.eval.bean.TblSchoolMajor;
import com.philip.edu.eval.bean.TblSchoolUser;


@Controller
@RequestMapping(value = "/schooluser")
public class SchoolUserController {
	
	private static final Logger logger = Logger.getLogger(SchoolUserController.class);
	
	@Autowired
	private SchoolUserService service;

	
	@RequestMapping(value = "/schoolUser", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<BackendData> schoolUser(){
		
		ArrayList usersList = (ArrayList) service.getNameSchoolUser();
		logger.info("successfully get the roles list");
		BackendData data = new BackendData();
		data.setMsg("");
		data.setCode(0); 
		data.setData(usersList);
		data.setCount(usersList.size());

		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}
	

	@RequestMapping(value="/addSchoolUser", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<BackendData> addSchoolUser(HttpServletRequest request) {
		
		TblSchoolUser schooluser = new TblSchoolUser();
		
		String schoolId = request.getParameter("schoolId");
		String userId = request.getParameter("userId");

		if(schoolId != null  && !schoolId.equals("")) {
		    
			if(userId != null && !userId.equals("") ) {
				schooluser.setSchoolId(Integer.parseInt(schoolId));
				schooluser.setUserId(Integer.parseInt(userId));
				int num = service.updateSchoolUser(schooluser);
				if(num==0) {
					service.createSchoolUser(schooluser);
				}
			}else {
				service.deleteSchoolUser(Integer.parseInt(schoolId));
			}
		} 
		

 
		BackendData data = new BackendData();
		data.setMsg("用户添加成功");
		data.setCode(0); 
		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/updateschooluser", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<BackendData> updateUsers(HttpServletRequest request) {
			
		TblSchoolUser schooluser = new TblSchoolUser();
		String id = request.getParameter("id");
	
		String schoolId = request.getParameter("schoolId");
		String userId = request.getParameter("userId");
		String username = request.getParameter("username");
		
		
		Pattern pattern = Pattern.compile("-?[0-9]+.?[0-9]+");
		Matcher isNum = pattern.matcher(schoolId);
		if (isNum.matches()) {
			schooluser.setSchoolId(Integer.parseInt(schoolId));
		}
		
		Matcher isNum1 = pattern.matcher(userId);
		if (isNum.matches()) {
			schooluser.setUserId(Integer.parseInt(userId));
		}
		Matcher isNum2 = pattern.matcher(username);
		if (isNum.matches()) {
			schooluser.setUserId(Integer.parseInt(username));
		}
		int result = service.updateSchoolUser(schooluser);
		
		BackendData data = new BackendData();
		
		if(result!=0){
			data.setMsg("用户修改成功");
			data.setCode(0); 
		}else{
			data.setMsg("用户修改失败");
			data.setCode(99); 
		}
		
		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/deleteschooluser", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<BackendData> deleteUsers(HttpServletRequest request) {
		
		String id = request.getParameter("id");
		
		int result = service.deleteSchoolUser(Integer.parseInt(id));
		
		BackendData data = new BackendData();
		
		if(result!=0){
			data.setMsg("用户删除成功");
			data.setCode(0); 
		}else{
			data.setMsg("用户删除失败");
			data.setCode(99); 
		}
		
		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}
		
	
	@RequestMapping(value = "/schoolMajorUser", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<BackendData> schoolMajorUser(){
		
		ArrayList usersList = (ArrayList) service.getRolenameSchoolMajor();
		logger.info("successfully get the roles list");
		BackendData data = new BackendData();
		data.setMsg("");
		data.setCode(0); 
		data.setData(usersList);
		data.setCount(usersList.size());

		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/addMajorUser", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<BackendData> addMajorUser(HttpServletRequest request) {
		
		TblSchoolMajor majoruser = new TblSchoolMajor();
		
		String majorId = request.getParameter("majorId");
		String userId = request.getParameter("userId");

		if(majorId != null  && !majorId.equals("")) {
		    
			if(userId != null && !userId.equals("") ) {
				majoruser.setMajorId(Integer.parseInt(majorId));
				majoruser.setUserId(Integer.parseInt(userId));
				int num = service.updateMajorUser(majoruser);
				if(num==0) {
					service.createMajorUser(majoruser);
				}
			}else {
				service.deleteMajorUser(Integer.parseInt(majorId));
			}
		} 
		 
		BackendData data = new BackendData();
		data.setMsg("用户添加成功");
		data.setCode(0); 
		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}
}
