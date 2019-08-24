package com.philip.edu.eval.users;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.philip.edu.eval.bean.BackendData;
import com.philip.edu.eval.bean.BackendData1;
import com.philip.edu.eval.bean.School;
import com.philip.edu.eval.bean.TblMajor;
import com.philip.edu.eval.bean.TblUsers;
import com.philip.edu.eval.util.EvalConstants;
import com.philip.edu.test.bean.HelloBean;
import com.philip.edu.test.service.HelloService;

@Controller
@RequestMapping(value = "/user")
public class UsersController {
	
	private static final Logger logger = Logger.getLogger(UsersController.class);
	
	@Autowired
	private UsersService service;

	
	@RequestMapping(value = "/users", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<BackendData> users(){
		
		ArrayList usersList = (ArrayList) service.getUsersList();
		logger.info("successfully get the major list");
		BackendData data = new BackendData();
		data.setMsg("");
		data.setCode(0); 
		data.setData(usersList);
		data.setCount(usersList.size());
		//BackendData data = new BackendData();
		
		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}
	
	@RequestMapping(value="/addUsers", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity addUsers(HttpServletRequest request) {
		
		TblUsers users = new TblUsers();
		String chineseName = request.getParameter("chineseName");
		String creator = request.getParameter("creator");
		String email = request.getParameter("email");
		String fixPhone = request.getParameter("fixPhone");
		String institution = request.getParameter("institution");
		String majot = request.getParameter("majot");
		String memo = request.getParameter("memo");
		String mobilePhone = request.getParameter("mobilePhone");
		String password = request.getParameter("password");
		String position = request.getParameter("position");
		String qq = request.getParameter("qq");
		String salt = request.getParameter("salt");
		String status = request.getParameter("status");
		String userName = request.getParameter("userName");
		
		users.setChineseName(chineseName);
		users.setCreateTime(new Date());
		users.setCreator(Integer.parseInt(creator));
		users.setEmail(email);
		users.setFixPhone(fixPhone);
		users.setInstitution(institution);
		users.setMajot(majot);
		users.setMemo(memo);
		users.setMobilePhone(mobilePhone);
		users.setPassword(password);
		users.setPosition(position);
		users.setQq(qq);
		users.setSalt(salt);
		users.setStatus(status);
		users.setUpdateTime(new Date());
		users.setUserName(userName);
		
		
		int result = service.createUsers(users);
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
	
	
	/*@RequestMapping(value="/updateUsers", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity updateUsers(HttpServletRequest request) {
		
		TblMajor major = new TblMajor();
		String id = request.getParameter("id");
		String majorName = request.getParameter("majorName");
		String majorCode = request.getParameter("majorCode");
		String isFirstClass = request.getParameter("isFirstClass");
		String majorClass = request.getParameter("majorClass");
		String mainLecture = request.getParameter("mainLecture");
		String memo = request.getParameter("memo");
		
		major.setId(Integer.parseInt(id));
		major.setMajorName(majorName);
		major.setMajorCode(majorCode);
		major.setMajorClass(majorClass);
		major.setIsFirstClass(isFirstClass);
		major.setMainLecture(mainLecture);
		major.setMemo(memo);

		major.setUpdateTime(new Date());
		
		int result = service.updateUsers(major);
		JSONObject object = new JSONObject();
		if(result!=0){
			object.put("code", 0);
			object.put("msg", "用户修改成功");
		}else{
			object.put("code", 99);
			object.put("msg", "用户修改失败");
		}
		
		return new ResponseEntity(object, HttpStatus.OK);
	}*/
	
	
	@RequestMapping(value="/deleteUsers", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity deleteUsers(HttpServletRequest request) {
		
		String id = request.getParameter("id");
		
		int result = service.deleteUsers(Integer.parseInt(id));
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
	
	@RequestMapping(value="/deleteUserss", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity deleteUserss(HttpServletRequest request) {
		
		String[] sIds = request.getParameterValues("id");
		int[] ids = new int[sIds.length];
		for(int i=0; i<sIds.length; i++){
			ids[i] = Integer.parseInt(sIds[i]);
		}
		
		int result = service.batchDeleteUsers(ids);
		JSONObject object = new JSONObject();
		if(result!=0){
			object.put("code", 1);
			object.put("msg", "用户删除成功");
		}else{
			object.put("code", 99);
			object.put("msg", "用户删除失败");
		}
		
		return new ResponseEntity(object, HttpStatus.OK);
	}
	
	
}
