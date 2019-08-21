package com.philip.edu.eval.dictionary;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.philip.edu.eval.bean.BackendData;
import com.philip.edu.eval.bean.School;
import com.philip.edu.eval.util.EvalConstants;
import com.philip.edu.test.bean.HelloBean;
import com.philip.edu.test.service.HelloService;

@RestController
@EnableWebMvc
public class DictController {
	
	@Autowired
	private DictService service;
	
	@RequestMapping(value = "/school", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<BackendData> school(){
		//ApplicationContext context = request.getAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		//ApplicationContext context = ApplicationContextRegister.getApplicationContext();
		//HelloService service = (HelloService)context.getBean("service_test");
		//String password = service.getPwByUserNm(name);
		
		ArrayList schoolList = (ArrayList) service.getSchoolList();
		
		//process the data:
		for(int i=0; i<schoolList.size(); i++){
			School school = (School) schoolList.get(i);
			school.setBuild_year_s(school.getBuild_year() + "年");
			switch(school.getType()){
			case '1':
				school.setType_s(EvalConstants.SCHOOL_TYPE_1);
				break;
			case '2':
				school.setType_s(EvalConstants.SCHOOL_TYPE_2);
				break;
			}
		}
		
		BackendData data = new BackendData();
		data.setMsg("");
		data.setCode(0); 
		data.setData(schoolList);
		data.setCount(schoolList.size());
		//BackendData data = new BackendData();
		
		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}
	
	@RequestMapping(value="/addSchool", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity addSchool(HttpServletRequest request) {
		
		School school = new School();
		String school_name = request.getParameter("school_name");
		String school_code = request.getParameter("school_code");
		String build_year = request.getParameter("build_year");
		String type = request.getParameter("type");
		String properties = request.getParameter("properties");
		String memo = request.getParameter("memo");
		school.setSchool_name(school_name);
		school.setSchool_code(school_code);
		school.setBuild_year(build_year);
		if(type!=null&&!"".equals(type))school.setType(type.charAt(0));
		school.setProperties(properties);
		school.setMemo(memo);
		school.setCreate_time(new Date());
		school.setUpdate_time(new Date());
		
		int result = service.createSchool(school);
		JSONObject object = new JSONObject();
		if(result!=0){
			object.put("code", 0);
			object.put("msg", "添加学校成功！");
		}else{
			object.put("code", -1);
			object.put("msg", "添加学校失败！");
		}
		
		return new ResponseEntity(object, HttpStatus.OK);
	}
	
	@RequestMapping(value="/updateSchool", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity updateSchool(HttpServletRequest request) {
		
		School school = new School();
		String id = request.getParameter("id");
		String school_name = request.getParameter("school_name");
		String school_code = request.getParameter("school_code");
		String build_year = request.getParameter("build_year");
		String type = request.getParameter("type");
		String properties = request.getParameter("properties");
		String memo = request.getParameter("memo");
		
		school.setId(Integer.parseInt(id));
		school.setSchool_name(school_name);
		school.setSchool_code(school_code);
		school.setBuild_year(build_year);
		if(type!=null&&!"".equals(type))school.setType(type.charAt(0));
		school.setProperties(properties);
		school.setMemo(memo);
		school.setUpdate_time(new Date());
		
		int result = service.updateSchool(school);
		JSONObject object = new JSONObject();
		if(result!=0){
			object.put("code", 0);
			object.put("msg", "修改学校成功！");
		}else{
			object.put("code", -1);
			object.put("msg", "修改学校失败！");
		}
		
		return new ResponseEntity(object, HttpStatus.OK);
	}
}
