package com.philip.edu.eval.dictionary;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONArray;
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
import com.philip.edu.eval.bean.BackendDataJSON;
import com.philip.edu.eval.bean.School;
import com.philip.edu.eval.bean.ShuttleBoxInfo;
import com.philip.edu.eval.bean.TblMajor;
@RestController
@EnableWebMvc
@RequestMapping(value = "/dictionary")
public class DictController {
	
	private static final Logger logger = Logger.getLogger(DictController.class);
	
	@Autowired
	private DictService service;
	
	@RequestMapping(value = "/school", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<BackendData> school(){
		//ApplicationContext context = request.getAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		//ApplicationContext context = ApplicationContextRegister.getApplicationContext();
		//HelloService service = (HelloService)context.getBean("service_test");
		//String password = service.getPwByUserNm(name);
		logger.info("Entering [school] method.");
		
		ArrayList schoolList = (ArrayList) service.getSchoolList();
		logger.info("successfully get the school list");
		
		BackendData data = new BackendData();
		data.setMsg("");
		data.setCode(0); 
		data.setData(schoolList);
		data.setCount(schoolList.size());
		//BackendData data = new BackendData();
		
		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/choseSchool", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<BackendData> choseSchool(){
		logger.info("Entering [choseSchool] method.");
		
		ArrayList schoolList = (ArrayList) service.getSchoolList();
		logger.info("successfully get the school list");
		
		ArrayList choseSchool = new ArrayList();
		for(int i=0; i<schoolList.size(); i++){
			School school = (School)schoolList.get(i);
			ShuttleBoxInfo info = new ShuttleBoxInfo();
			info.setValue(""+school.getId());
			info.setTitle(school.getSchool_code() + "--" + school.getSchool_name());
			choseSchool.add(info);
		}
		
		BackendData data = new BackendData();
		data.setMsg("");
		data.setCode(0); 
		data.setData(choseSchool);
		data.setCount(choseSchool.size());
		//BackendData data = new BackendData();
		
		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/schoolById", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<BackendData1> schoolById(HttpServletRequest request){		
		String sId = request.getParameter("id");
		
		ArrayList schoolList = (ArrayList) service.getSchoolById(Integer.parseInt(sId));
		
		BackendData1 data = new BackendData1();
		data.setMsg("");
		data.setCode(0); 
		data.setData(schoolList);
		//BackendData data = new BackendData();
		
		return new ResponseEntity<BackendData1>(data, HttpStatus.OK);
	}
	
	@RequestMapping(value="/addSchool", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity addSchool(HttpServletRequest request) {
		
		logger.info("entering addSchool method.");
		School school = new School();
		String school_name = request.getParameter("school_name");
		String school_code = request.getParameter("school_code");
		String city = request.getParameter("city");
		String type = request.getParameter("type");
		String memo = request.getParameter("memo");
		school.setSchool_name(school_name);
		school.setSchool_code(school_code);
		school.setCity(city);
		school.setType(type);
		school.setMemo(memo);
		school.setCreate_time(new Date());
		school.setUpdate_time(new Date());
		logger.info("get all the information from page.");
		
		int result = service.createSchool(school);
		logger.info("the create method successfully executed.");
		
		JSONObject object = new JSONObject();
		if(result!=0){
			object.put("code", 1);
			object.put("msg", "成功添加院校");
		}else{
			object.put("code", 99);
			object.put("msg", "添加院校失败");
		}
		
		logger.info("return the message.");
		
		return new ResponseEntity(object, HttpStatus.OK);
	}
	
	@RequestMapping(value="/updateSchool", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity updateSchool(HttpServletRequest request) {
		
		School school = new School();
		String id = request.getParameter("id");
		String school_name = request.getParameter("school_name");
		String school_code = request.getParameter("school_code");
		String city = request.getParameter("city");
		String type = request.getParameter("type");
		String memo = request.getParameter("memo");
		
		school.setId(Integer.parseInt(id));
		school.setSchool_name(school_name);
		school.setSchool_code(school_code);
		school.setCity(city);
		school.setType(type);
		school.setMemo(memo);
		school.setUpdate_time(new Date());
		
		int result = service.updateSchool(school);
		JSONObject object = new JSONObject();
		if(result!=0){
			object.put("code", 0);
			object.put("msg", "成功修改院校");
		}else{
			object.put("code", 99);
			object.put("msg", "修改院校失败");
		}
		
		return new ResponseEntity(object, HttpStatus.OK);
	}
	
	@RequestMapping(value="/deleteSchool", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity deleteSchool(HttpServletRequest request) {
		
		String id = request.getParameter("id");
		
		int result = service.deleteSchool(Integer.parseInt(id));
		JSONObject object = new JSONObject();
		if(result!=0){
			object.put("code", 0);
			object.put("msg", "成功删除院校");
		}else{
			object.put("code", 99);
			object.put("msg", "删除院校失败");
		}
		
		return new ResponseEntity(object, HttpStatus.OK);
	}
	
	@RequestMapping(value="/deleteSchools", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity deleteSchools(HttpServletRequest request) {
		
		String[] sIds = request.getParameterValues("id");
		int[] ids = new int[sIds.length];
		for(int i=0; i<sIds.length; i++){
			ids[i] = Integer.parseInt(sIds[i]);
		}
		
		int result = service.batchDeleteSchool(ids);
		JSONObject object = new JSONObject();
		if(result!=0){
			object.put("code", 1);
			object.put("msg", "成功删除院校");
		}else{
			object.put("code", 99);
			object.put("msg", "删除院校失败");
		}
		
		return new ResponseEntity(object, HttpStatus.OK);
	}
	
	@RequestMapping(value="/getCity", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity getCity(HttpServletRequest request) {
		
		//String search = request.getParameter("keywords");
		
		List<String> cityList = service.getCity();
		
		//logger.info("get city list");
		BackendData1 data = new BackendData1();
		data.setMsg("搜索到信息");
		data.setCode(0); 
		data.setData((ArrayList)cityList);
		
		return new ResponseEntity<BackendData1>(data, HttpStatus.OK);
	}
	
	@RequestMapping(value="/getType", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity getType(HttpServletRequest request) {
		
		//String search = request.getParameter("keywords");
		
		List<String> typeList = service.getType();
		
		BackendData1 data = new BackendData1();
		data.setMsg("搜索到信息");
		data.setCode(0); 
		data.setData((ArrayList)typeList);
		
		return new ResponseEntity<BackendData1>(data, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/major", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<BackendData> major(){
		
		ArrayList majorList = (ArrayList) service.getMajorList();
		logger.info("successfully get the major list");
		BackendData data = new BackendData();
		data.setMsg("");
		data.setCode(0); 
		data.setData(majorList);
		data.setCount(majorList.size());
		//BackendData data = new BackendData();
		
		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/choseMajor", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<BackendData> choseMajor(){
		
		ArrayList majorList = (ArrayList) service.getMajorList();
		logger.info("successfully get the prepare to chose major list");
		
		ArrayList choseMajor = new ArrayList();
		for(int i=0; i<majorList.size(); i++){
			TblMajor major = (TblMajor)majorList.get(i);
			ShuttleBoxInfo info = new ShuttleBoxInfo();
			info.setValue(major.getId().toString());
			info.setTitle(major.getMajorCode() + "--" + major.getMajorName());
			choseMajor.add(info);
		}
		
		BackendData data = new BackendData();
		data.setMsg("");
		data.setCode(0); 
		data.setData(choseMajor);
		data.setCount(choseMajor.size());
		//BackendData data = new BackendData();
		
		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}
	
	@RequestMapping(value="/addMajor", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity addMajor(HttpServletRequest request) {
		
		TblMajor major = new TblMajor();
		String majorName = request.getParameter("majorName");
		String majorCode = request.getParameter("majorCode");
		String majorClass = request.getParameter("majorClass");
		String mainLecture = request.getParameter("mainLecture");
		String isFirstClass = request.getParameter("isFirstClass");
		String memo = request.getParameter("memo");
		major.setMajorName(majorName);
		major.setMajorCode(majorCode);
		major.setMajorClass(majorClass);
		major.setMainLecture(mainLecture);
		major.setIsFirstClass(isFirstClass);
		major.setMemo(memo);
		major.setCreateTime(new Date());
		major.setUpdateTime(new Date());
		
		int result = service.createMajor(major);
		JSONObject object = new JSONObject();
		if(result!=0){
			object.put("code", 1);
			object.put("msg", "专业添加成功");
		}else{
			object.put("code", 99);
			object.put("msg", "专业添加失败");
		}
		
		return new ResponseEntity(object, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/updateMajor", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity updateMajor(HttpServletRequest request) {
		
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
		
		int result = service.updateMajor(major);
		JSONObject object = new JSONObject();
		if(result!=0){
			object.put("code", 0);
			object.put("msg", "专业修改成功");
		}else{
			object.put("code", 99);
			object.put("msg", "专业修改失败");
		}
		
		return new ResponseEntity(object, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/deleteMajor", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity deleteMajor(HttpServletRequest request) {
		
		String id = request.getParameter("id");
		
		int result = service.deleteMajor(Integer.parseInt(id));
		JSONObject object = new JSONObject();
		if(result!=0){
			object.put("code", 0);
			object.put("msg", "专业删除成功");
		}else{
			object.put("code", 99);
			object.put("msg", "专业删除失败");
		}
		
		return new ResponseEntity(object, HttpStatus.OK);
	}
	
	@RequestMapping(value="/deleteMajors", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity deleteMajors(HttpServletRequest request) {
		
		String[] sIds = request.getParameterValues("id");
		int[] ids = new int[sIds.length];
		for(int i=0; i<sIds.length; i++){
			ids[i] = Integer.parseInt(sIds[i]);
		}
		
		int result = service.batchDeleteMajor(ids);
		JSONObject object = new JSONObject();
		if(result!=0){
			object.put("code", 1);
			object.put("msg", "专业删除成功");
		}else{
			object.put("code", 99);
			object.put("msg", "专业删除失败");
		}
		
		return new ResponseEntity(object, HttpStatus.OK);
	}
	
	@RequestMapping(value="/chosenMajor", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<BackendData> chosenMajor(HttpServletRequest request){
		
		String sSchool = request.getParameter("school_id");
		
		ArrayList majorList = (ArrayList) service.getChosenMajor(Integer.parseInt(sSchool));
		
		logger.info("successfully get chosen majors list");
		
		BackendData data = new BackendData();
		data.setMsg("");
		data.setCode(0); 
		data.setData(majorList);
		data.setCount(majorList.size());
		//BackendData data = new BackendData();
		
		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}
	
	@RequestMapping(value="/chosenMajorInfo", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<BackendData> chosenMajorInfo(HttpServletRequest request){
		
		String sSchool = request.getParameter("school_id");
		
		ArrayList majorList = (ArrayList) service.getChosenMajorInfo(Integer.parseInt(sSchool));
		
		logger.info("successfully get chosen majors with info list");
		
		BackendData data = new BackendData();
		data.setMsg("");
		data.setCode(0); 
		data.setData(majorList);
		data.setCount(majorList.size());
		//BackendData data = new BackendData();
		
		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}
	
	@RequestMapping(value="/saveChosenMajor", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<BackendData> saveChosenMajor(HttpServletRequest request){
		
		String sSchool = request.getParameter("school_id");
		String[] sMajor = request.getParameterValues("majors");
		
		int school_id = Integer.parseInt(sSchool);
		int[] majors = new int[sMajor.length];
		for(int i=0; i<majors.length; i++){
			majors[i] = Integer.parseInt(sMajor[i]);
		}
		
		int n = service.saveChosenMajor(school_id, majors);
		logger.info("successfully save chosen majors list");
		
		BackendData data = new BackendData();
		data.setMsg("");
		data.setCode(0); 
		//data.setData(majorList);
		//data.setCount(majorList.size());
		//BackendData data = new BackendData();
		
		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}
}
