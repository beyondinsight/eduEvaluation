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
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
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

import com.auth0.jwt.interfaces.Claim;
import com.philip.edu.eval.bean.BackendData;
import com.philip.edu.eval.bean.TblSchoolMajor;
import com.philip.edu.eval.bean.TblSchoolUser;
import com.philip.edu.eval.bean.TblUserRole;
import com.philip.edu.eval.bean.TblUsers;
import com.philip.edu.eval.role.RolesService;
import com.philip.edu.eval.users.UsersService;
import com.philip.edu.eval.util.SecurityUtil;


@Controller
@RequestMapping(value = "/schooluser")
public class SchoolUserController {
	
	private static final Logger logger = Logger.getLogger(SchoolUserController.class);
	
	@Autowired
	private SchoolUserService service;
	@Autowired
	private UsersService user_service;
	@Autowired
	private RolesService role_service;

	
	@RequestMapping(value = "/schoolUser", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<BackendData> schoolUser(HttpServletRequest request){
		
		BackendData data = new BackendData();
		String roleId = request.getParameter("roleId");
		if(roleId==null || roleId.equals("")) {
			data.setMsg("未查询到信息");
			data.setCode(1);
			return new ResponseEntity<BackendData>(data, HttpStatus.OK);
		}
		ArrayList usersList = (ArrayList) service.getNameSchoolUser(Integer.parseInt(roleId));
		logger.info("successfully get the roles list");
		
		data.setMsg("");
		data.setCode(0); 
		data.setData(usersList);
		data.setCount(usersList.size());

		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}
	

	@RequestMapping(value="/addSchoolUser", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<BackendData> addSchoolUser(HttpServletRequest request) {
		
	
		
		String roleId = request.getParameter("roleId");
		String schoolId = request.getParameter("schoolId");
		
		String[] sIds = request.getParameterValues("userId");
		

		if(roleId != null && schoolId != null && !schoolId.equals("") && !roleId.equals("")) {
			TblSchoolUser user = new TblSchoolUser();
			user.setSchoolId(Integer.parseInt(schoolId));
			user.setRoleId(Integer.parseInt(roleId));
			TblSchoolUser sur = service.getSchoolRolesUsers(user);
			if(sur != null && sur.getUserId() != null) {
				String[] userids =  sur.getUserId().split(",");
				int[] uids = new int[userids.length];
				for(int i=0; i<userids.length; i++){
					uids[i] = Integer.parseInt(userids[i]);
				}
				 
				role_service.deleteChosenUser(uids);
			}
			if(sIds != null && !sIds.equals("") ) {
				
				int[] ids = new int[sIds.length];
				for(int i=0; i<sIds.length; i++){
					ids[i] = Integer.parseInt(sIds[i]);
				}
				
				for(int a : ids) {
					TblUsers users = new TblUsers();
					users.setId(a);
					users.setRoleId(Integer.parseInt(roleId));
					int num = user_service.updateUserRole(users);
					if(num==0) {
						user_service.createUserRole(users);		
					}
				}				 
			}
			
		} 
		

 
		BackendData data = new BackendData();
		data.setMsg("用户添加成功");
		data.setCode(0); 
		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}
	
	
	/*@RequestMapping(value="/updateschooluser", method = RequestMethod.POST, produces = "application/json")
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
			schooluser.setUserId(userId);
		}
		Matcher isNum2 = pattern.matcher(username);
		if (isNum.matches()) {
			schooluser.setUserId(username);
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
	}*/
	
	
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
	public ResponseEntity<BackendData> schoolMajorUser(HttpServletRequest request){
		
		
		BackendData data = new BackendData();
		// get token:
		String token = request.getParameter("token");
		String roleId = request.getParameter("roleId");
		
		if(roleId==null || roleId.equals("")) {
			data.setMsg("未查询到信息");
			data.setCode(1);
			return new ResponseEntity<BackendData>(data, HttpStatus.OK);
		}
		
		if (token == null || "".equals(token)) {
			data.setMsg("您的令牌已过期！");
			data.setCode(10);
			// BackendData data = new BackendData();

			return new ResponseEntity<BackendData>(data, HttpStatus.OK);
		}

		// check:
		Map<String, Claim> claims = SecurityUtil.verifyToken(token);
		Claim user_name_claim = claims.get("username");

		if (null == user_name_claim || StringUtils.isEmpty(user_name_claim.asString())) {
			data.setMsg("您的用户验证信息不正确！");
			data.setCode(20);
			// BackendData data = new BackendData();

			return new ResponseEntity<BackendData>(data, HttpStatus.OK);
		}

		// get user id:
		String username = user_name_claim.asString();
		TblUsers user_mes = (TblUsers) user_service.getUsers(username).get(0);
		
		int schoolId=0;
		
		if(user_mes.getSchoolId() != null) {
			schoolId =  user_mes.getSchoolId();
		}
		ArrayList usersList  = new ArrayList();
		
	    if(user_mes.getRoleId() != null  &&  user_mes.getRoleId() ==1) {
	    	usersList = (ArrayList) service.getRolenameSchoolMajor(Integer.parseInt(roleId));
	    }else {	     
	    	TblSchoolMajor tsu = new TblSchoolMajor();
	    	tsu.setRoleId(Integer.parseInt(roleId));
	    	tsu.setSchoolId(schoolId);
    		usersList = (ArrayList) service.getRolenameSchoolMajorBySchool(tsu);			 
	    }
 
		logger.info("successfully get the roles list");
		
		data.setMsg("");
		data.setCode(0); 
		data.setData(usersList);
		data.setCount(usersList.size());

		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}
	
	
	/*@RequestMapping(value="/addMajorUser", method = RequestMethod.POST, produces = "application/json")
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
	}*/
	
	@RequestMapping(value = "/chosenSchoolUser", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<BackendData> chosenSchoolUser(HttpServletRequest request){
		BackendData data = new BackendData();
		String roleId = request.getParameter("roleId");
		String schoolId = request.getParameter("schoolId");
		ArrayList<Integer> chosenUser = new ArrayList<Integer>();
		if(roleId==null || schoolId==null) {
			data.setMsg("");
			data.setCode(0); 
			data.setData(chosenUser);
			data.setCount(chosenUser.size());
			return new ResponseEntity<BackendData>(data, HttpStatus.OK);
		}
		
		TblSchoolUser user = new TblSchoolUser();
		user.setSchoolId(Integer.parseInt(schoolId));
		user.setRoleId(Integer.parseInt(roleId));
		
		TblSchoolUser sur = service.getSchoolRolesUsers(user);
		if(sur == null || sur.getUserId() == null) {
			return new ResponseEntity<BackendData>(data, HttpStatus.OK);
		}
		String[] userids =  sur.getUserId().split(",");
		
		for(int i=0; i<userids.length; i++){
			chosenUser.add(Integer.parseInt(userids[i]));
		}
		
		logger.info("successfully get the choseuser list");

		data.setMsg("");
		data.setCode(0); 
		data.setData(chosenUser);
		data.setCount(chosenUser.size());
		//BackendData data = new BackendData();
		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/chosenMajorUser", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<BackendData> chosenMajorUser(HttpServletRequest request){
		BackendData data = new BackendData();
		String roleId = request.getParameter("roleId");
		String schoolId = request.getParameter("schoolId");
		String majorId = request.getParameter("majorId");

		ArrayList<Integer> chosenUser = new ArrayList<Integer>();
		if(roleId==null || schoolId==null || majorId == null) {
			data.setMsg("");
			data.setCode(0); 
			data.setData(chosenUser);
			data.setCount(chosenUser.size());
			return new ResponseEntity<BackendData>(data, HttpStatus.OK);
		}
		TblSchoolMajor user = new TblSchoolMajor();
		user.setSchoolId(Integer.parseInt(schoolId));
		user.setRoleId(Integer.parseInt(roleId));
		user.setMajorId(Integer.parseInt(majorId));
		
		TblSchoolMajor sur = service.getMajorRolesUsers(user);
		if(sur == null || sur.getUserId() == null) {
			return new ResponseEntity<BackendData>(data, HttpStatus.OK);
		}
		String[] userids =  sur.getUserId().split(",");
		
		for(int i=0; i<userids.length; i++){
			chosenUser.add(Integer.parseInt(userids[i]));
		}
		
		logger.info("successfully get the choseuser list");

		data.setMsg("");
		data.setCode(0); 
		data.setData(chosenUser);
		data.setCount(chosenUser.size());
	
		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}
	
	@RequestMapping(value="/addMajorUser", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<BackendData> addMajorUser(HttpServletRequest request) {
		
	
		
		String roleId = request.getParameter("roleId");
		String schoolId = request.getParameter("schoolId");
		String majorId = request.getParameter("majorId");
		String[] sIds = request.getParameterValues("userId");
		 
		

		if(roleId != null && schoolId != null && !schoolId.equals("") && !roleId.equals("")) {
			
	 
			//TblSchoolUser user = new TblSchoolUser();
			TblSchoolMajor user = new TblSchoolMajor();
			user.setSchoolId(Integer.parseInt(schoolId));
			user.setRoleId(Integer.parseInt(roleId));
			user.setMajorId(Integer.parseInt(majorId));
			TblSchoolMajor sur = service.getMajorRolesUsers(user);
			if(sur  != null  && sur.getUserId() != null) {			
			
					String[] userids =  sur.getUserId().split(",");
					int[] uids = new int[userids.length];
					for(int i=0; i<userids.length; i++){
						uids[i] = Integer.parseInt(userids[i]);
					}
					 
					role_service.deleteChosenUser(uids);
			}
			if(sIds != null && !sIds.equals("") ) {

				int[] ids = new int[sIds.length];
				for(int i=0; i<sIds.length; i++){
					ids[i] = Integer.parseInt(sIds[i]);
				}
				 
				for(int a : ids) {
					TblUsers users = new TblUsers();
					users.setId(a);
					users.setRoleId(Integer.parseInt(roleId));
					
					//users.setMajor(major);
					int num = user_service.updateUserRole(users);
					if(num==0) {
						user_service.createUserRole(users);		
					}
					user.setId(a);
					int num2 = service.updateMajorUser(user);
					if(num2==0) {
						service.createMajorUser(user);
					}
				}				 
			}
			
		} 
		

 
		BackendData data = new BackendData();
		data.setMsg("用户添加成功");
		data.setCode(0); 
		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}
}
