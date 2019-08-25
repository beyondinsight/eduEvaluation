package com.philip.edu.eval.collection;

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
import com.philip.edu.eval.dictionary.DictService;
import com.philip.edu.eval.util.EvalConstants;
import com.philip.edu.test.bean.HelloBean;
import com.philip.edu.test.service.HelloService;

@RestController
@EnableWebMvc
@RequestMapping(value = "/collectionTask")
public class ColTaskController {
	
	private static final Logger logger = Logger.getLogger(ColTaskController.class);
	
	@Autowired
	private ColTaskService taskService;
	
	@RequestMapping(value = "/schoolChose", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<BackendData> school(){
		
		//int task_id = 
		ArrayList schoolList = (ArrayList) taskService.getTaskSchoolList(0);
		
		BackendData data = new BackendData();
		data.setMsg("");
		data.setCode(0); 
		data.setData(schoolList);
		data.setCount(schoolList.size());
		//BackendData data = new BackendData();
		
		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}
	
}
