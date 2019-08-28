package com.philip.edu.eval.role;

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
import com.philip.edu.eval.bean.TblRoles;
import com.philip.edu.eval.bean.TblUsers;


@Controller
@RequestMapping(value = "/role")
public class RolesController {
	
	private static final Logger logger = Logger.getLogger(RolesController.class);
	
	@Autowired
	private RolesService service;

	
	@RequestMapping(value = "/roles", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<BackendData> users(){
		
		ArrayList usersList = (ArrayList) service.getRolenameRoles();
		logger.info("successfully get the roles list");
		BackendData data = new BackendData();
		data.setMsg("");
		data.setCode(0); 
		data.setData(usersList);
		data.setCount(usersList.size());
		//BackendData data = new BackendData();
		
		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/addroles", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity addUsers(HttpServletRequest request) {
		
		TblRoles roles = new TblRoles();
		String id = request.getParameter("id");
		String roleName = request.getParameter("roleName");
		String roleCode = request.getParameter("roleCode");
		String defineYear = request.getParameter("defineYear");
		String description = request.getParameter("description");
		String roleDef = request.getParameter("roleDef");
		String pid = request.getParameter("pid");
		String status = request.getParameter("status");
		String createTime = request.getParameter("createTime");
		String updateTime = request.getParameter("updateTime");
	
		roles.setRoleName(roleName);
		roles.setCreateTime(new Date());
		roles.setUpdateTime(new Date());
		Pattern pattern = Pattern.compile("-?[0-9]+.?[0-9]+");
		roles.setRoleCode(roleCode);
		roles.setDescription(description);
		roles.setRoleDef(roleDef);
		Matcher isNum = pattern.matcher(pid);
		if (isNum.matches()) {
			roles.setPid(Integer.parseInt(pid));
		}
		
		Matcher isNum1 = pattern.matcher(defineYear);
		if (isNum1.matches()) {
			roles.setDefineYear(Integer.parseInt(defineYear));
		}
		
		roles.setStatus(status);
		
		
		
		
		int result = service.createRoles(roles);
		JSONObject object = new JSONObject();
		if(result!=0){
			object.put("code", 1);
			object.put("msg", "用户添加成功");
		}else{
			object.put("code", 99);
			object.put("msg", "用户添加失败");
		}
		
		return new ResponseEntity(object, HttpStatus.OK);
	}
	
	
	
	
	@RequestMapping(value="/updateroles", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity updateUsers(HttpServletRequest request) {
	 
		
		TblRoles roles = new TblRoles();
		String id = request.getParameter("id");

		System.out.println(id);
		
		String roleName = request.getParameter("roleName");
		String roleCode = request.getParameter("roleCode");
		String defineYear = request.getParameter("defineYear");
		String description = request.getParameter("description");
		String roleDef = request.getParameter("roleDef");
		String pid = request.getParameter("pid");
		String status = request.getParameter("status");
		String createTime = request.getParameter("createTime");
		String updateTime = request.getParameter("updateTime");
	
		roles.setRoleName(roleName);
		roles.setCreateTime(new Date());
		roles.setUpdateTime(new Date());
		Pattern pattern = Pattern.compile("-?[0-9]+.?[0-9]+");
		roles.setRoleCode(roleCode);
		roles.setDescription(description);
		roles.setRoleDef(roleDef);
		Matcher isNum = pattern.matcher(pid);
		if (isNum.matches()) {
			roles.setPid(Integer.parseInt(pid));
		}
		
		Matcher isNum1 = pattern.matcher(defineYear);
		if (isNum1.matches()) {
			roles.setDefineYear(Integer.parseInt(defineYear));
		}
		
		roles.setStatus(status);
		
		int result = service.updateRoles(roles);
		
		JSONObject object = new JSONObject();
		if(result!=0){
			object.put("code", 0);
			object.put("msg", "用户修改成功");
		}else{
			object.put("code", 99);
			object.put("msg", "用户修改失败");
		}
		
		return new ResponseEntity(object, HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value="/deleteroles", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity deleteUsers(HttpServletRequest request) {
		
		String id = request.getParameter("id");
		
		int result = service.deleteRoles(Integer.parseInt(id));
		JSONObject object = new JSONObject();
		if(result!=0){
			object.put("code", 0);
			object.put("msg", "用户删除成功");
		}else{
			object.put("code", 99);
			object.put("msg", "用户删除失败");
		}
		
		return new ResponseEntity(object, HttpStatus.OK);
	}
	
	
	
}
