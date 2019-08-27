package com.philip.edu.eval.collection;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
import com.philip.edu.eval.bean.ColTaskMajor;
import com.philip.edu.eval.bean.ColTaskSchool;
import com.philip.edu.eval.bean.CollectionTask;
import com.philip.edu.eval.bean.School;
import com.philip.edu.eval.bean.TblMajor;
import com.philip.edu.eval.dictionary.DictService;
import com.philip.edu.eval.util.EvalConstants;
import com.philip.edu.test.bean.HelloBean;
import com.philip.edu.test.service.HelloService;

@RestController
@EnableWebMvc
@RequestMapping(value = "/collection")
public class ColTaskController {
	
	private static final Logger logger = Logger.getLogger(ColTaskController.class);
	
	@Autowired
	private ColTaskService service;
	
	@RequestMapping(value = "/createTask", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<BackendData> saveTask(HttpServletRequest request){
		
		logger.info("entering save task method.");
		CollectionTask taskCol = new CollectionTask();
		String task_name = request.getParameter("name");
		String task_year = request.getParameter("year");
		String start_date = request.getParameter("startDate");
		String end_date = request.getParameter("endDate");
		
		taskCol.setTask_name(task_name);
		taskCol.setTask_year(task_year);
		taskCol.setStart_time(start_date);
		taskCol.setEnd_time(end_date);
		taskCol.setCreate_time(new Date());
		taskCol.setUpdate_time(new Date());
		taskCol.setUse_metrics_system(EvalConstants.DEFAULT_METRICS_SYSTEM_ID);
		logger.info("get collection task basic information from page.");
		
		String sSchools = request.getParameter("schoolChose");
		List<ColTaskSchool> schools = new ArrayList();
		JSONArray objSchools = new JSONArray(sSchools);
		for(int i=0; i<objSchools.length(); i++){
			ColTaskSchool school = new ColTaskSchool();
			JSONObject obj = objSchools.getJSONObject(i);
			school.setSchool_id(Integer.parseInt(obj.getString("id")));
			school.setSchool_code(obj.getString("school_code"));
			school.setSchool_name(obj.getString("school_name"));
			schools.add(school);
		}
		logger.info("get collection task school information from page.");
		
		String sMajors = request.getParameter("majorChose");
		List<ColTaskMajor> majors = new ArrayList();
		JSONObject objMajors = new JSONObject(sMajors);
		Iterator it = objMajors.keys();
		while(it.hasNext()){
			String key = (String)it.next();
			int school_id = Integer.parseInt(key);
			JSONArray value = objMajors.getJSONArray(key);
			for(int i=0; i<value.length(); i++){
				JSONObject obj = value.getJSONObject(i);
				String major_id = obj.getString("id");
				ColTaskMajor major = new ColTaskMajor();
				major.setSchool_id(school_id);
				major.setMajor_id(Integer.parseInt(major_id));
				majors.add(major);
			}
		}
		logger.info("get collection task major information from page.");
		
		int result = service.createColTask(taskCol, schools, majors);
		logger.info("the create method successfully executed.");
		
		JSONObject object = new JSONObject();
		if(result!=0){
			object.put("code", 1);
			object.put("msg", "成功创建填报任务");
		}else{
			object.put("code", 99);
			object.put("msg", "创建填报任务失败"); 
		}
		
		logger.info("return the message.");
		
		return new ResponseEntity(object, HttpStatus.OK);
	}
	
}
