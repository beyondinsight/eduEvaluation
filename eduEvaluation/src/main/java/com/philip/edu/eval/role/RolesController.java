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
import java.util.Properties;
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
import com.philip.edu.eval.bean.ShuttleBoxInfo;
import com.philip.edu.eval.bean.TblMajor;
import com.philip.edu.eval.bean.TblRoles;
import com.philip.edu.eval.bean.TblUserRole;
import com.philip.edu.eval.bean.TblUsers;
import com.philip.edu.eval.users.UsersService;
import com.philip.edu.eval.util.PropertiesUtil;


@Controller
@RequestMapping(value = "/role")
public class RolesController {
	
	private static final Logger logger = Logger.getLogger(RolesController.class);
	private Properties propConfig = PropertiesUtil.getProperty("config");
	@Autowired
	private RolesService service;
	@Autowired
	private UsersService user_service;

	
	@RequestMapping(value = "/roles", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<BackendData> roles(){
		
		ArrayList rolesList = (ArrayList) service.getRolenameRoles();
		logger.info("successfully get the roles list");
		BackendData data = new BackendData();
		data.setMsg("");
		data.setCode(0); 
		data.setData(rolesList);
		data.setCount(rolesList.size());
		//BackendData data = new BackendData();
		
		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}
	
	/**
	 * 权限拥有人数
	 * @return
	 */
	@RequestMapping(value = "/roles_usersCount", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<BackendData> roles_usersCount(){
		
		ArrayList roles_usersListCount = (ArrayList) service.getRolesUsersCount();
		logger.info("successfully get the roles list");
		BackendData data = new BackendData();
		data.setMsg("");
		data.setCode(0); 
		data.setData(roles_usersListCount);
		data.setCount(roles_usersListCount.size());

		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/upload_roles_usersCount", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<BackendData> upload_roles_usersCount(){
		
		int id = Integer.parseInt(propConfig.getProperty("upload_roleid"));
		ArrayList roles_usersListCount = (ArrayList) service.getUploadRolesUsersCount(id);
		logger.info("successfully get the roles list");
		BackendData data = new BackendData();
		data.setMsg("");
		data.setCode(0); 
		data.setData(roles_usersListCount);
		data.setCount(roles_usersListCount.size());

		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}
		 
	
	/**
	 * 权限拥有人
	 * @return
	 */
	@RequestMapping(value = "/choseUser", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<BackendData> choseUser(){
	
		ArrayList usersList = (ArrayList) user_service.getUsersList();				 
	 		
		ArrayList choseUser = new ArrayList();
		for(int i=0; i<usersList.size(); i++){
			TblUsers user = (TblUsers)usersList.get(i);
			ShuttleBoxInfo info = new ShuttleBoxInfo();
			info.setValue(user.getId().toString());
			info.setTitle(user.getChineseName());
			choseUser.add(info);
		}
		
		logger.info("successfully get the choseuser list");
		BackendData data = new BackendData();
		data.setMsg("");
		data.setCode(0); 
		data.setData(choseUser);
		data.setCount(choseUser.size());
		//BackendData data = new BackendData();
		
		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/chosenUser", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<BackendData> chosenUser(HttpServletRequest request){
		
		String id = request.getParameter("roleId");		 
		if(id==null || id.equals("")) {		
			id="0";		 
		} 
		List<TblUsers> usersList = user_service.getRolesUsers(Integer.parseInt(id));
		ArrayList<Integer> chosenUser = new ArrayList<Integer>();
		for(TblUsers u: usersList) {		
			chosenUser.add(u.getId());
		}
		
		logger.info("successfully get the choseuser list");
		BackendData data = new BackendData();
		data.setMsg("");
		data.setCode(0); 
		data.setData(chosenUser);
		data.setCount(chosenUser.size());
		//BackendData data = new BackendData();
		
		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/saveChosenUser", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<BackendData> saveChosenUser(HttpServletRequest request){
		

		String sroleId = request.getParameter("roleId");
		String[] suserId = request.getParameterValues("userId");
		
		int roleId = Integer.parseInt(sroleId);
		List<TblUsers> usersList = user_service.getRolesUsers(roleId);
		List<Integer> userid_all = new ArrayList<Integer>();
		for(TblUsers turs : usersList){
			userid_all.add(turs.getId());
		}

		if(suserId!=null &&  !suserId.equals("") ) {
			for(String su: suserId) {
				int userId = Integer.parseInt(su);
				TblUserRole  tur = new TblUserRole();  
				tur.setRoleId(roleId);
				tur.setUserId(userId);
				int n = service.updateChosenUser(tur);
				if(n==0){
					service.saveChosenUser(tur);
				}
				userid_all.remove((Object)userId);//必须是object 类型

			}
		}
		int[] usid = new int[userid_all.size()];
		for(int i=0; i<userid_all.size(); i++){
			usid[i] =  userid_all.get(i);
		}
		if(userid_all.size()>0) {
			service.deleteChosenUser(usid);
		 }
		
		
		logger.info("successfully save chosen User list");
		
		BackendData data = new BackendData();
		data.setMsg("");
		data.setCode(0); 
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
