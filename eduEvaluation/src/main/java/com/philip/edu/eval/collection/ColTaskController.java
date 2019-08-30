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
import com.philip.edu.eval.bean.Material;
import com.philip.edu.eval.bean.MetricsDetail;
import com.philip.edu.eval.bean.School;
import com.philip.edu.eval.bean.TblMajor;
import com.philip.edu.eval.dictionary.DictService;
import com.philip.edu.eval.util.EvalConstants;
import com.philip.edu.test.bean.HelloBean;

@RestController
@EnableWebMvc
@RequestMapping(value = "/collection")
public class ColTaskController {

	private static final Logger logger = Logger.getLogger(ColTaskController.class);

	@Autowired
	private ColTaskService service;

	@RequestMapping(value = "/createTask", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<BackendData> saveTask(HttpServletRequest request) {

		logger.info("entering save task method.");
		CollectionTask taskCol = new CollectionTask();
		String task_name = request.getParameter("name");
		String task_year = request.getParameter("year");
		String start_date = request.getParameter("startDate");
		String end_date = request.getParameter("endDate");
		String weight1 = request.getParameter("weighting1");
		String weight2 = request.getParameter("weighting2");
		String weight3 = request.getParameter("weighting3");

		taskCol.setTask_name(task_name);
		taskCol.setTask_year(task_year);
		taskCol.setStart_time(start_date);
		taskCol.setEnd_time(end_date);
		taskCol.setCreate_time(new Date());
		taskCol.setUpdate_time(new Date());
		taskCol.setForm_basic_weight(Integer.parseInt(weight1));
		taskCol.setForm_performance_weight(Integer.parseInt(weight2));
		taskCol.setForm_capitalprogress_weight(Integer.parseInt(weight3));
		taskCol.setStatus(EvalConstants.COLLECTION_STATUS_INACTIVE);

		taskCol.setUse_metrics_system(EvalConstants.DEFAULT_METRICS_SYSTEM_ID);
		logger.info("get collection task basic information from page.");

		String sSchools = request.getParameter("schoolChose");
		List<ColTaskSchool> schools = null;
		if (sSchools != null && !"".equals(sSchools)) {
			schools = new ArrayList();
			JSONArray objSchools = new JSONArray(sSchools);
			for (int i = 0; i < objSchools.length(); i++) {
				ColTaskSchool school = new ColTaskSchool();
				JSONObject obj = objSchools.getJSONObject(i);
				school.setSchool_id(Integer.parseInt(obj.getString("id")));
				school.setSchool_code(obj.getString("school_code"));
				school.setSchool_name(obj.getString("school_name"));
				schools.add(school);
			}
		}
		logger.info("get collection task school information from page.");

		String sMajors = request.getParameter("majorChose");
		List<ColTaskMajor> majors = null;
		if (sMajors != null && !sMajors.equals("")) {
			majors = new ArrayList();
			JSONObject objMajors = new JSONObject(sMajors);
			Iterator it = objMajors.keys();
			while (it.hasNext()) {
				String key = (String) it.next();
				int school_id = Integer.parseInt(key);
				JSONArray value = objMajors.getJSONArray(key);
				for (int i = 0; i < value.length(); i++) {
					JSONObject obj = value.getJSONObject(i);
					int major_id = obj.getInt("id");
					ColTaskMajor major = new ColTaskMajor();
					major.setSchool_id(school_id);
					major.setMajor_id(major_id);
					majors.add(major);
				}
			}
		}
		logger.info("get collection task major information from page.");

		int result = service.createColTask(taskCol, schools, majors);
		logger.info("the create method successfully executed.");

		JSONObject object = new JSONObject();
		if (result != 0) {
			object.put("code", 1);
			object.put("msg", "成功创建填报任务");
		} else {
			object.put("code", 99);
			object.put("msg", "创建填报任务失败");
		}

		logger.info("return the message.");

		return new ResponseEntity(object, HttpStatus.OK);
	}

	@RequestMapping(value = "/colTask", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<BackendData> getColTask() {

		ArrayList taskList = (ArrayList) service.getColTaskList();
		for (int i = 0; i < taskList.size(); i++) {
			CollectionTask task = (CollectionTask) taskList.get(i);
			task.setForms("共3个");
			int count_school = service.countTaskSchool(task.getId());
			int count_major = service.countTaskMajor(task.getId());
			task.setSchool_major("共" + count_school + "个学校，共" + count_major + "个专业");
			switch (task.getStatus()) {
			case EvalConstants.COLLECTION_STATUS_ACTIVE:
				task.setStatus_info(EvalConstants.COLLECTION_STATUS_ACTIVE_DISPLAY);
				break;
			case EvalConstants.COLLECTION_STATUS_INACTIVE:
				task.setStatus_info(EvalConstants.COLLECTION_STATUS_INACTIVE_DISPLAY);
				break;
			case EvalConstants.COLLECTION_STATUS_STOP:
				task.setStatus_info(EvalConstants.COLLECTION_STATUS_STOP_DISPLAY);
				break;
			}
			task.setStart_time(task.getStart_time().substring(0, 10));
			task.setEnd_time(task.getEnd_time().substring(0, 10));
		}
		logger.info("successfully get collection task list");

		BackendData data = new BackendData();
		data.setMsg("");
		data.setCode(0);
		data.setData(taskList);
		data.setCount(taskList.size());
		// BackendData data = new BackendData();

		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}

	@RequestMapping(value = "/startTask", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<BackendData> startTask(HttpServletRequest request) {

		String status = (String) request.getParameter("status");
		String sTask_id = (String) request.getParameter("id");

		int result = service.updateStatus(Integer.parseInt(sTask_id), EvalConstants.COLLECTION_STATUS_ACTIVE);
		logger.info("successfully start task");

		BackendData data = new BackendData();
		if (result != 0) {
			data.setMsg("启用成功!");
			data.setCode(1);
		} else {
			data.setMsg("启用失败!");
			data.setCode(99);
		}

		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}

	@RequestMapping(value = "/stopTask", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<BackendData> stopTask(HttpServletRequest request) {

		String status = (String) request.getParameter("status");
		String sTask_id = (String) request.getParameter("id");

		int result = service.updateStatus(Integer.parseInt(sTask_id), EvalConstants.COLLECTION_STATUS_STOP);
		logger.info("successfully stop task list");

		BackendData data = new BackendData();
		logger.info("result:" + result);
		if (result != 0) {
			data.setMsg("启用成功!");
			data.setCode(1);
		} else {
			data.setMsg("启用失败!");
			data.setCode(99);
		}

		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}

	@RequestMapping(value = "/deleteTasks", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity deleteTasks(HttpServletRequest request) {

		String[] sIds = request.getParameterValues("id");
		int[] ids = new int[sIds.length];
		for (int i = 0; i < sIds.length; i++) {
			ids[i] = Integer.parseInt(sIds[i]);
		}

		int result = service.batchDeleteTasks(ids);
		BackendData data = new BackendData();
		// logger.info("result:" + result);
		if (result != 0) {
			data.setMsg("删除任务成功!");
			data.setCode(1);
		} else {
			data.setMsg("删除任务失败!");
			data.setCode(99);
		}

		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}

	@RequestMapping(value = "/createMetrics", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity createMetrics(HttpServletRequest request) {

		String level = request.getParameter("level");
		String metrics_code = request.getParameter("metrics_code");
		String unit = request.getParameter("unit");
		String name = request.getParameter("name");
		String memo = request.getParameter("memo");
		MetricsDetail metrics = new MetricsDetail();
		metrics.setMetrics_name(name);
		metrics.setM_system_id(EvalConstants.DEFAULT_METRICS_SYSTEM_ID);
		metrics.setLevel(Integer.parseInt(level));
		metrics.setUnit(unit);
		metrics.setDescription(memo);
		metrics.setMetrics_code(metrics_code);
		//decode:
		if(Integer.parseInt(level) == 2 && metrics_code.contains(".")){
			String[] temp = metrics_code.split(".");
			metrics.setPid(Integer.parseInt(temp[0]));
			metrics.setOrder(Integer.parseInt(temp[1]));
		} else if(Integer.parseInt(level) == 1 && !metrics_code.contains(".")) {
			metrics.setPid(0);
			metrics.setOrder(Integer.parseInt(metrics_code));
		}
		logger.info("get all the fields from page.");
		
		//Materials:
		String sMaterials = request.getParameter("materials");
		List<Material> materials = new ArrayList();
		if (sMaterials != null && !"".equals(sMaterials)) {
			JSONObject object = new JSONObject(sMaterials);
			Iterator iKeys = object.keys();
			while (iKeys.hasNext()) {
				String material = (String) iKeys.next();
				String memo1 = (String) object.get(material);
				Material m = new Material();
				m.setMaterial_name(material);
				m.setMemo(memo1);
				materials.add(m);
			}
		}
		logger.info("get all the materials from page.");
		
		int result = service.createMetrics(metrics, materials);
		
		BackendData data = new BackendData();
		// logger.info("result:" + result);
		if (result != 0) {
			data.setMsg("创建指标成功!");
			data.setCode(1);
		} else {
			data.setMsg("创建指标失败!");
			data.setCode(99);
		}

		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/updateMetrics", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity updateMetrics(HttpServletRequest request) {

		String level = request.getParameter("level");
		String metrics_code = request.getParameter("metrics_code");
		String unit = request.getParameter("unit");
		String name = request.getParameter("name");
		String memo = request.getParameter("memo");
		String id = request.getParameter("id");
		MetricsDetail metrics = new MetricsDetail();
		metrics.setId(Integer.parseInt(id));
		metrics.setMetrics_name(name);
		metrics.setM_system_id(EvalConstants.DEFAULT_METRICS_SYSTEM_ID);
		metrics.setLevel(Integer.parseInt(level));
		metrics.setUnit(unit);
		metrics.setDescription(memo);
		metrics.setMetrics_code(metrics_code);  
		//decode:
		if(Integer.parseInt(level) == 2 && metrics_code.contains(".")){
			String[] temp = metrics_code.split(".");
			metrics.setPid(Integer.parseInt(temp[0]));
			metrics.setOrder(Integer.parseInt(temp[1]));
		} else if(Integer.parseInt(level) == 1 && !metrics_code.contains(".")) {
			metrics.setPid(0);
			metrics.setOrder(Integer.parseInt(metrics_code));
		}
		logger.info("get all the fields from page.");
		
		//Materials:
		String sMaterials = request.getParameter("materials");
		List<Material> materials = new ArrayList();
		if (sMaterials != null && !"".equals(sMaterials)) {
			JSONObject object = new JSONObject(sMaterials);
			Iterator iKeys = object.keys();
			while (iKeys.hasNext()) {
				String material = (String) iKeys.next();
				String memo1 = (String) object.get(material);
				Material m = new Material();
				m.setMaterial_name(material);
				m.setMemo(memo1);
				materials.add(m);
			}
		}
		logger.info("get all the materials from page.");
		
		int result = service.updateMetrics(metrics);
		
		BackendData data = new BackendData();
		// logger.info("result:" + result);
		if (result != 0) {
			data.setMsg("创建指标成功!");
			data.setCode(1);
		} else {
			data.setMsg("创建指标失败!");
			data.setCode(99);
		}

		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}
	
	@RequestMapping(value="/getMetrics", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<BackendData> chosenMajor(HttpServletRequest request){
		
		ArrayList metricsList = (ArrayList) service.getMetricsList(EvalConstants.DEFAULT_METRICS_SYSTEM_ID);
		for(int i=0; i<metricsList.size(); i++){
			MetricsDetail detail = (MetricsDetail)metricsList.get(i);
			detail.setLevel1_name(detail.getMetrics_name());
			detail.setMaterial_num("要求" + service.countMaterials(detail.getId()) + "项");
		}
		logger.info("successfully get metrics list");
		
		BackendData data = new BackendData();
		data.setMsg("成功获取全部指标");
		data.setCode(0); 
		data.setData(metricsList);
		data.setCount(metricsList.size());
		//BackendData data = new BackendData();
		
		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}
	
	@RequestMapping(value="/deleteMetrics", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<BackendData> deleteMetrics(HttpServletRequest request){
		
		String sId = request.getParameter("id");
		
		int result = service.deleteMetrics(Integer.parseInt(sId));
		logger.info("successfully delete metrics");
		
		BackendData data = new BackendData();
		data.setMsg("成功删除指标");
		data.setCode(1); 
		//BackendData data = new BackendData();
		
		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}
	 
	@RequestMapping(value="/getPerformanceForm", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<BackendData> getPerformanceForm(HttpServletRequest request){
		
		String collection_major_id = request.getParameter("collection_major_id");
		
		ArrayList performanceForm = (ArrayList)service.getPerformanceForm(Integer.parseInt(collection_major_id));
		logger.info("successfully get performance form list");
		 
		BackendData data = new BackendData();
		data.setMsg("成功获取业绩表格"); 
		data.setCode(0); 
		data.setData(performanceForm);
		data.setCount(performanceForm.size());
		//BackendData data = new BackendData();
		
		return new ResponseEntity<BackendData>(data, HttpStatus.OK); 
	}
	
	@RequestMapping(value="/getRelateMaterials", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<BackendData> getRelateMaterials(HttpServletRequest request){
		
		String metrics_id = request.getParameter("metrics_id");
		
		ArrayList materials = (ArrayList)service.getRelateMaterials(Integer.parseInt(metrics_id));
		logger.info("successfully get materials list");
		 
		BackendData data = new BackendData();
		data.setMsg("成功获取材料列表");  
		data.setCode(0); 
		data.setData(materials);
		data.setCount(materials.size());
		//BackendData data = new BackendData();
		
		return new ResponseEntity<BackendData>(data, HttpStatus.OK); 
	}
}
