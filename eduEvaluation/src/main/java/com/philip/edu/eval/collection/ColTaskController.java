package com.philip.edu.eval.collection;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

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
import com.philip.edu.eval.bean.BasicForm;
import com.philip.edu.eval.bean.CapitalProgressForm;
import com.philip.edu.eval.bean.ChosenMajor;
import com.philip.edu.eval.bean.ColTaskMajor;
import com.philip.edu.eval.bean.ColTaskSchool;
import com.philip.edu.eval.bean.CollectionTask;
import com.philip.edu.eval.bean.Material;
import com.philip.edu.eval.bean.MetricsAdd;
import com.philip.edu.eval.bean.MetricsDetail;
import com.philip.edu.eval.bean.PerformanceAdd;
import com.philip.edu.eval.bean.PerformanceForm;
import com.philip.edu.eval.bean.School;
import com.philip.edu.eval.bean.TblMajor;
import com.philip.edu.eval.dictionary.DictService;
import com.philip.edu.eval.util.EvalConstants;
import com.philip.edu.eval.util.PropertiesUtil;
import com.philip.edu.test.bean.HelloBean;

@RestController
@EnableWebMvc
@RequestMapping(value = "/collection")
public class ColTaskController {

	private static final Logger logger = Logger.getLogger(ColTaskController.class);
	private Properties propConfig = PropertiesUtil.getProperty("config");

	private int template_form_performance_id = Integer.parseInt(propConfig.getProperty("template_performance_form_id"));

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
    
		taskCol.setUse_metrics_system(this.template_form_performance_id);
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
					int major_id = obj.getInt("major_id");
					ColTaskMajor major = new ColTaskMajor();
					major.setSchool_id(school_id);
					major.setMajor_id(major_id);
					majors.add(major);
				} 
			} 
		}
		logger.info("get collection task major information from page.");

		int result = service.createColTask(taskCol, schools, majors, propConfig);
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
	
	@RequestMapping(value = "/editTask", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<BackendData> editTask(HttpServletRequest request) {

		logger.info("entering edit task method.");
		CollectionTask taskCol = new CollectionTask();
		String task_id = request.getParameter("task_id");
		String task_name = request.getParameter("name");
		String task_year = request.getParameter("year");
		String start_date = request.getParameter("startDate");
		String end_date = request.getParameter("endDate");
		String weight1 = request.getParameter("weighting1");
		String weight2 = request.getParameter("weighting2");
		String weight3 = request.getParameter("weighting3");

		taskCol.setId(Integer.parseInt(task_id));
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
    
		taskCol.setUse_metrics_system(this.template_form_performance_id);
		logger.info("get collection task basic information from page.");

		int result = service.updateColTask(taskCol);
		logger.info("the update method successfully executed.");

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
		
		BackendData data = new BackendData();
		
		/*List<MetricsDetail>  aa =    service.getMetricsList(template_form_performance_id);
		List<String> dd = new ArrayList<String>();
		for(MetricsDetail  c :  aa) {
			
			dd.add(c.getMetrics_code());
			
		}
		
		//System.out.print("0======"+aa);
		

		
		//System.out.print("000000"+metrics_code);
		
		String contain;
		
		if(dd.contains(metrics_code)) {
			contain="0";
		}else{
			contain="1";

			data.setMsg(contain);
			data.setCode(10);

			return new ResponseEntity<BackendData>(data, HttpStatus.OK);
		}*/
		

		String level = request.getParameter("level");
		String unit = request.getParameter("unit");
		String name = request.getParameter("name");
		String memo = request.getParameter("memo");
		String metrics_code = request.getParameter("metrics_code");
		MetricsDetail metrics = new MetricsDetail();
		metrics.setMetrics_name(name);
		int metrics_system_id = ((Integer.parseInt((String) propConfig.get("template_performance_form_id"))));
		metrics.setM_system_id(metrics_system_id);
		metrics.setLevel(Integer.parseInt(level));
		metrics.setUnit(unit);
		metrics.setDescription(memo);
		metrics.setMetrics_code(metrics_code);
		// decode:
		if (Integer.parseInt(level) == 2 && metrics_code.contains(".")) {
			String[] temp = metrics_code.split("\\.");
			metrics.setPid(Integer.parseInt(temp[0]));
			metrics.setOrder(Integer.parseInt(temp[1]));
		} else if (Integer.parseInt(level) == 1 && !metrics_code.contains(".")) {
			metrics.setPid(0);
			metrics.setOrder(Integer.parseInt(metrics_code));
		}
		logger.info("get all the fields from page.");

		// Materials:
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
		int metrics_system_id = (Integer.parseInt((String) propConfig.get("template_performance_form_id")));
		metrics.setM_system_id(metrics_system_id);
		metrics.setLevel(Integer.parseInt(level));
		metrics.setUnit(unit);
		metrics.setDescription(memo);
		metrics.setMetrics_code(metrics_code);
		// decode:
		if (Integer.parseInt(level) == 2 && metrics_code.contains(".")) {
			String[] temp = metrics_code.split("\\.");
			metrics.setPid(Integer.parseInt(temp[0]));
			metrics.setOrder(Integer.parseInt(temp[1]));
		} else if (Integer.parseInt(level) == 1 && !metrics_code.contains(".")) {
			metrics.setPid(0);
			metrics.setOrder(Integer.parseInt(metrics_code));
		}
		logger.info("get all the fields from page.");

		// Materials:
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

	@RequestMapping(value = "/getMetrics", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<BackendData> getMetrics(HttpServletRequest request) {

		int metrics_system_id = (Integer.parseInt((String) propConfig.get("template_performance_form_id")));
		ArrayList metricsList = (ArrayList) service.getMetricsList(metrics_system_id);
		// level 1 and 2:
		ArrayList level1List = new ArrayList();
		ArrayList level2List = new ArrayList();
		ArrayList newList = new ArrayList();
		int i = 0;

		for (i = 0; i < metricsList.size(); i++) {
			MetricsDetail metrics = (MetricsDetail) metricsList.get(i);
			if (metrics.getLevel() == 1) {
				MetricsAdd metricsAdd = new MetricsAdd();
				metricsAdd.setParentMetrics(metrics);
				level1List.add(metricsAdd);
			} else {
				break;
			}
		}
		   
		int index = 0;
		for (int j = i; j<metricsList.size(); j++){
			MetricsDetail metrics = (MetricsDetail) metricsList.get(j);
			MetricsAdd metricsParent = (MetricsAdd) level1List.get(index);
			MetricsDetail mParent = metricsParent.getParentMetrics();
			if(metrics.getPid() == mParent.getOrder()){
				ArrayList children = metricsParent.getChildrenMetrics();
				children.add(metrics);
			} else if(metrics.getPid() > mParent.getOrder()){
				index++; 
				j--;
			}
		}
		
		for (int k=0; k<level1List.size(); k++){
			MetricsAdd metricsA = (MetricsAdd)level1List.get(k);
			MetricsDetail metricsP = metricsA.getParentMetrics();
			ArrayList metricsC = metricsA.getChildrenMetrics();
			if(metricsC.size()==0){
				metricsP.setLevel1_name(metricsP.getMetrics_name());
				metricsP.setPid(metricsP.getOrder());
				metricsP.setMetrics_code("");
				metricsP.setMetrics_name("");
				newList.add(metricsP);
			} else {
				for(int l=0; l<metricsC.size(); l++){
					MetricsDetail metrics = (MetricsDetail)metricsC.get(l);
					metrics.setLevel1_name(metricsP.getMetrics_name());
					metrics.setMaterial_num("要求" + service.countMaterials(metrics.getId()) + "项");
					newList.add(metrics); 
				}
			}
		}

		/*int index = 0;
		int[] parent = new int[30];
		MetricsDetail temp = null;
		MetricsDetail metricsParent = (MetricsDetail) level1List.get(0);
		for (int j = i; j < metricsList.size(); j++) {
			MetricsDetail metrics = (MetricsDetail) metricsList.get(j);
			if (metrics.getPid() == metricsParent.getOrder()) {
				metrics.setLevel1_name(metricsParent.getMetrics_name());
				metrics.setMaterial_num("要求" + service.countMaterials(metrics.getId()) + "项");
				newList.add(metrics);
				parent[index]++;

			} else if (metrics.getPid() < metricsParent.getOrder()) {
				metricsParent.setLevel1_name(metricsParent.getMetrics_name());
				metricsParent.setPid(metricsParent.getOrder());
				metricsParent.setMetrics_code("");
				metricsParent.setMetrics_name("");
				newList.add(metricsParent);
				if (index < level1List.size())
					metricsParent = (MetricsDetail) level1List.get(index++);
			} else if (metrics.getPid() > metricsParent.getOrder()){
				metricsParent = (MetricsDetail) level1List.get(index++); 
				metrics.setLevel1_name(metricsParent.getMetrics_name());
				metrics.setMaterial_num("要求" + service.countMaterials(metrics.getId()) + "项");
				newList.add(metrics);
				parent[index]++;
			}
		}
		for(int k=index; k<level1List.size(); k++){
			metricsParent = (MetricsDetail) level1List.get(k);
			metricsParent.setLevel1_name(metricsParent.getMetrics_name());
			metricsParent.setPid(metricsParent.getOrder());
			metricsParent.setMetrics_code("");
			metricsParent.setMetrics_name("");
			newList.add(metricsParent);
		}*/
		

		logger.info("successfully get metrics list");

		BackendData data = new BackendData();
		data.setMsg("成功获取全部指标");
		data.setCode(0);
		data.setData(newList);
		data.setCount(newList.size());
		// BackendData data = new BackendData();

		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}

	@RequestMapping(value = "/getMaterials", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<BackendData> getMaterials(HttpServletRequest request) {

		// int metrics_system_id = (Integer.parseInt((String)
		// propConfig.get("template_capital_progress_form_id")));
		String sMetrics_id = request.getParameter("metrics_id");
		logger.info("successfully get material list");
		List<Material> list = service.getMaterials(Integer.parseInt(sMetrics_id));

		BackendData data = new BackendData();
		data.setMsg("成功获取支撑材料");
		data.setCode(0);
		data.setData((ArrayList) list);
		data.setCount(list.size());
		// BackendData data = new BackendData();

		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}

	@RequestMapping(value = "/deleteMetrics", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<BackendData> deleteMetrics(HttpServletRequest request) {

		String sId = request.getParameter("id");

		int result = service.deleteMetrics(Integer.parseInt(sId));
		logger.info("successfully delete metrics");

		BackendData data = new BackendData();
		data.setMsg("成功删除指标");
		data.setCode(1);
		// BackendData data = new BackendData();

		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}

	@RequestMapping(value = "/getPerformanceForm", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<BackendData> getPerformanceForm(HttpServletRequest request) {

		String collection_major_id = request.getParameter("collection_major_id");

		ArrayList performanceForm = (ArrayList) service.getPerformanceForm(Integer.parseInt(collection_major_id),
				this.template_form_performance_id);
		service.selectPerformanceMaterialsNum(performanceForm);
		
		ArrayList level1List = new ArrayList();
		ArrayList level2List = new ArrayList();
		ArrayList newList = new ArrayList();
		int i = 0;

		for (i = 0; i < performanceForm.size(); i++) {
			PerformanceForm metrics = (PerformanceForm) performanceForm.get(i);
			if (metrics.getMetrics_level() == 1) {
				PerformanceAdd metricsAdd = new PerformanceAdd();
				metricsAdd.setParent(metrics);
				level1List.add(metricsAdd);
			} else {
				break;
			}
		}
		
		int index = 0;
		for (int j = i; j<performanceForm.size(); j++){
			PerformanceForm metrics = (PerformanceForm) performanceForm.get(j);
			PerformanceAdd metricsParent = (PerformanceAdd) level1List.get(index);
			PerformanceForm mParent = metricsParent.getParent();
			if(metrics.getMetrics_pid() == mParent.getMetrics_order()){
				ArrayList children = metricsParent.getChildren();
				children.add(metrics);
			} else if(metrics.getMetrics_pid() > mParent.getMetrics_order()){
				index++; 
				j--;
			}
		}
		
		for (int k=0; k<level1List.size(); k++){
			PerformanceAdd metricsA = (PerformanceAdd)level1List.get(k);
			PerformanceForm metricsP = metricsA.getParent();
			ArrayList metricsC = metricsA.getChildren();
			if(metricsC.size()==0){
				metricsP.setLevel1_name(metricsP.getMetrics_name());
				metricsP.setMetrics_pid(metricsP.getMetrics_order());
				//metricsP.setMetrics_code("");
				metricsP.setMetrics_name("");
				newList.add(metricsP);
			} else {
				for(int l=0; l<metricsC.size(); l++){
					PerformanceForm metrics = (PerformanceForm)metricsC.get(l);
					metrics.setLevel1_name(metricsP.getMetrics_name());
					metrics.setMaterial_num("要求" + service.countMaterials(metrics.getId()) + "项");
					newList.add(metrics);
				}
			}
		}
	
		
		logger.info("successfully get performance form list"); 

		BackendData data = new BackendData();
		data.setMsg("成功获取业绩表格");
		data.setCode(0);
		data.setData(newList);
		data.setCount(newList.size());
		// BackendData data = new BackendData();

		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}

	@RequestMapping(value = "/getRelateMaterials", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<BackendData> getRelateMaterials(HttpServletRequest request) {

		String pf_id = request.getParameter("form_performance_id");
		// String metrics_id = request.getParameter("metrics_id");
		String metrics_id = null;

		ArrayList materials = (ArrayList) service.getRelateMaterials(Integer.parseInt(pf_id), 0);
		logger.info("successfully get materials list");

		BackendData data = new BackendData();
		data.setMsg("成功获取材料列表");
		data.setCode(0);
		data.setData(materials);
		data.setCount(materials.size());
		// BackendData data = new BackendData();

		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}

	@RequestMapping(value = "/getCapitalProgress", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<BackendData> getCapitalProgress(HttpServletRequest request) {

		String collection_major_id = request.getParameter("collection_major_id");

		ArrayList cpf = (ArrayList) service.selectCapitalProgress(Integer.parseInt(collection_major_id));
		CapitalProgressForm cpform = (CapitalProgressForm) cpf.get(0);

		int setNum = service.selectCapitalProgressMaterialsNumAndPerformanceId(cpform, propConfig);
		logger.info("successfully get capitalProgress form");

		BackendData data = new BackendData();
		data.setMsg("成功获取资金支出表格");
		data.setCode(0);
		data.setData(cpf);
		data.setCount(cpf.size());
		// BackendData data = new BackendData();

		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getCapitalProgressDetail", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<BackendData> getCapitalProgressDetail(HttpServletRequest request) {

		String collection_major_id = request.getParameter("collection_major_id");

		ArrayList cpf = (ArrayList) service.selectCapitalProgress(Integer.parseInt(collection_major_id));
		CapitalProgressForm cpform = (CapitalProgressForm) cpf.get(0);

		ArrayList newList = service.setCapitalProgressMaterialsNumAndPerformanceId(cpform, propConfig);

		logger.info("successfully get capitalProgress form");

		BackendData data = new BackendData();
		data.setMsg("成功获取资金支出表格");
		data.setCode(0);
		data.setData(newList);
		data.setCount(newList.size());
		// BackendData data = new BackendData();

		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}

	@RequestMapping(value = "/getBasicForm", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<BackendData> getBasicForm(HttpServletRequest request) {

		String collection_major_id = request.getParameter("collection_major_id");

		ArrayList pfs = service.setBasicForm(Integer.parseInt(collection_major_id), propConfig);

		BackendData data = new BackendData(); 
		data.setMsg("成功基本信息表格");
		data.setCode(0);
		data.setData(pfs);
		data.setCount(pfs.size());
		// BackendData data = new BackendData();

		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}

	@RequestMapping(value = "/editPerformanceItem", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity editPerformanceItem(HttpServletRequest request) {

		String performance_id = request.getParameter("performance_id");
		logger.info("performance_id:" + performance_id);
		String current_value = request.getParameter("current_value");
		String target_value = request.getParameter("target_value");
		String actual_value = request.getParameter("actual_value");
		String score = request.getParameter("score");
		String self_evaluation = request.getParameter("self_evaluation");
		String self_introduction = request.getParameter("self_introduction");

		PerformanceForm pf = new PerformanceForm();
		pf.setId(Integer.parseInt(performance_id));
		if (current_value != null && !"".equals(current_value))
			pf.setCurrent_value(Double.parseDouble(current_value));
		if (target_value != null && !"".equals(target_value))
			pf.setTarget_value(Double.parseDouble(target_value));
		if (actual_value != null && !"".equals(actual_value))
			pf.setActual_value(Double.parseDouble(actual_value));
		if (score != null && !"".equals(score))
			pf.setScore(Double.parseDouble(score));
		if (self_evaluation != null && !"".equals(self_evaluation))
			pf.setSelf_evaluate(Double.parseDouble(self_evaluation));
		pf.setSelf_introduction(self_introduction);
		pf.setUpdate_time(new Date());
		int result = service.updatePerformanceForm(pf);

		// update all the status:
		service.updatePerformanceStatus(EvalConstants.FORM_STATUS_INPUTING_INFORMATIN,
				Integer.parseInt(performance_id));
		
		//service.updateTaskStatus(id, process_status)
		service.updateTaskStatus(service.getCollectionIdByPerformance(Integer.parseInt(performance_id)), EvalConstants.PROCESS_STATUS_INPUTING_INFORMATION);

		logger.info("update performance form success");

		BackendData data = new BackendData();
		// logger.info("result:" + result);
		if (result != 0) {
			data.setMsg("修改表格成功!");
			data.setCode(1);
		} else {
			data.setMsg("修改表格失败!");
			data.setCode(99);
		}

		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}

	@RequestMapping(value = "/editCapitalProgressItem", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity editCapitalProgressItem(HttpServletRequest request) {

		String performance_id = request.getParameter("form_performance_id");
		logger.info("performance_id:" + performance_id);
		String actural_value = request.getParameter("actural_value");
		String edit_type = request.getParameter("edit_type");

		ArrayList al = service.getCapitalProgess(Integer.parseInt(performance_id));
		CapitalProgressForm cpf1 = (CapitalProgressForm) al.get(0);
		CapitalProgressForm cpf = new CapitalProgressForm();
		cpf.setId(cpf1.getId());
		cpf.setCollection_major_id(cpf1.getCollection_major_id());

		if (edit_type != null & !"".equals(edit_type) && actural_value != null && !"0".equals(actural_value)) {
			if ("rda".equals(edit_type)) {
				cpf.setRegion_disbursement_amount(Double.parseDouble(actural_value));
			} else if ("rpha".equals(edit_type)) {
				cpf.setRegion_paid_hardware_amount(Double.parseDouble(actural_value));
			} else if ("rpia".equals(edit_type)) {
				cpf.setRegion_paid_internal_amount(Double.parseDouble(actural_value));
			} else if ("cda".equals(edit_type)) {
				cpf.setCentral_disbursment_amount(Double.parseDouble(actural_value));
			} else if ("cpia".equals(edit_type)) {
				cpf.setCentral_paid_internal_amount(Double.parseDouble(actural_value));
			} else if ("cpha".equals(edit_type)) {
				cpf.setCentral_paid_hardware_amount(Double.parseDouble(actural_value));
			} else if ("sft".equals(edit_type)) {
				cpf.setSchool_funding_total(Double.parseDouble(actural_value));
			} else if ("sfh".equals(edit_type)) {
				cpf.setSchool_funding_hardware(Double.parseDouble(actural_value));
			} else if ("sfi".equals(edit_type)) {
				cpf.setSchool_funding_internal(Double.parseDouble(actural_value));
			}
			cpf.setUpdate_time(new Date());
			cpf.setProcess_status(EvalConstants.FORM_STATUS_INPUTING_INFORMATIN);
		}

		int result = service.updateCapitalProgressForm(cpf);
		
		service.updateTaskStatus(service.getCollectionIdByPerformance(Integer.parseInt(performance_id)), EvalConstants.PROCESS_STATUS_INPUTING_INFORMATION);

		logger.info("update capital progress ");

		BackendData data = new BackendData();
		// logger.info("result:" + result);
		if (result != 0) {
			data.setMsg("修改表格成功!");
			data.setCode(1);
			data.setData(al);
		} else {
			data.setMsg("修改表格失败!");
			data.setCode(99);
		}

		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}

	@RequestMapping(value = "/addMaterial", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<BackendData> addMaterial(HttpServletRequest request) {

		String metrics_id = request.getParameter("metrics_id");
		// logger.info("metrics_id:" + metric);
		String material_name = request.getParameter("material_name");
		String memo = request.getParameter("memo");

		Material material = new Material();
		if (metrics_id != null && !"".equals(metrics_id))
			material.setMetrics_id(Integer.parseInt(metrics_id));
		if (material_name != null && !"".equals(material_name))
			material.setMaterial_name(material_name);
		if (memo != null && !"".equals(memo))
			material.setMemo(memo);

		ArrayList materials = new ArrayList();
		materials.add(material);
		int result = service.insertMaterials(materials);

		logger.info("successfully insert material.");

		BackendData data = new BackendData();
		data.setMsg("成功添加材料");
		data.setCode(0);
		// BackendData data = new BackendData();

		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}

	@RequestMapping(value = "/chosenSchool", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<BackendData> getChosenSchool(HttpServletRequest request) {

		String task_id = request.getParameter("task_id");
		logger.info("task_id:" + task_id);

		ArrayList chosenSchools = (ArrayList) service.getChosenSchool(Integer.parseInt(task_id));

		logger.info("successfully get chosen school list");

		BackendData data = new BackendData();
		data.setMsg("成功获取选中学校");
		data.setCode(0);
		data.setData(chosenSchools);
		data.setCount(chosenSchools.size());
		// BackendData data = new BackendData();

		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}

	@RequestMapping(value = "/changeSchool", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<BackendData> changeSchool(HttpServletRequest request) {

		String task_id = request.getParameter("task_id");
		String[] school_ids = request.getParameterValues("chose_id");

		logger.info("task_id:" + task_id);
		int[] chose_id = new int[school_ids.length];
		for (int i = 0; i < school_ids.length; i++) {
			chose_id[i] = Integer.parseInt(school_ids[i]);
		}

		// ArrayList chosenSchools =
		// (ArrayList)service.getChosenSchool(Integer.parseInt(task_id));
		int result = service.changeSchool(Integer.parseInt(task_id), chose_id, propConfig);

		logger.info("successfully change school list");

		BackendData data = new BackendData();
		if (result != 0) {
			data.setMsg("成功改变选中学校");
			data.setCode(0);
			// data.setData(chosenSchools);
			// data.setCount(chosenSchools.size());
			// BackendData data = new BackendData();
		} else {
			data.setMsg("修改学校失败");
			data.setCode(99);
		}

		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}

	@RequestMapping(value = "/chosenMajor", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<BackendData> getChosenMajor(HttpServletRequest request) {

		String collection_school_id = request.getParameter("collection_school_id");

		ArrayList chosenMajors = (ArrayList) service.getChosenMajor(Integer.parseInt(collection_school_id));

		logger.info("collection school id:" + collection_school_id);
		logger.info("successfully get chosen major list");

		BackendData data = new BackendData();
		data.setMsg("成功获取选中专业");
		data.setCode(0);
		data.setData(chosenMajors);
		data.setCount(chosenMajors.size());
		// BackendData data = new BackendData();

		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}

	@RequestMapping(value = "/chosenMajorTran", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<BackendData> getChosenMajorTran(HttpServletRequest request) {

		String collection_school_id = request.getParameter("collection_school_id");

		ArrayList chosenMajors = (ArrayList) service.getChosenMajor(Integer.parseInt(collection_school_id));

		logger.info("collection school id:" + collection_school_id);
		logger.info("successfully get chosen major list");

		ArrayList temp = new ArrayList();
		for (int i = 0; i < chosenMajors.size(); i++) {
			ColTaskMajor major = (ColTaskMajor) chosenMajors.get(i);
			temp.add(new Integer(major.getMajor_id()));
		}

		BackendData data = new BackendData();
		data.setMsg("成功获取选中专业");
		data.setCode(0);
		data.setData(temp);
		data.setCount(temp.size());
		// BackendData data = new BackendData();

		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}

	@RequestMapping(value = "/changeMajor", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<BackendData> changeMajor(HttpServletRequest request) {

		String task_id = request.getParameter("collection_school_id");
		String[] major_ids = request.getParameterValues("ids");

		logger.info("collectio_school_id:" + task_id);
		int[] chose_id = new int[major_ids.length];
		for (int i = 0; i < major_ids.length; i++) {
			chose_id[i] = Integer.parseInt(major_ids[i]);
		}

		// ArrayList chosenSchools =
		// (ArrayList)service.getChosenSchool(Integer.parseInt(task_id));
		int result = service.changeMajor(Integer.parseInt(task_id), chose_id, propConfig);

		logger.info("successfully change school list");

		BackendData data = new BackendData();
		if (result != 0) {
			data.setMsg("成功改变选中学校");
			data.setCode(0);
			// data.setData(chosenSchools);
			// data.setCount(chosenSchools.size());
			// BackendData data = new BackendData();
		} else {
			data.setMsg("修改学校失败");
			data.setCode(99);
		}

		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}

	@RequestMapping(value = "/status", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<BackendData> getStatusList() {

		ArrayList statusList = (ArrayList) service.selectStatusList();

		logger.info("successfully get status list");

		BackendData data = new BackendData();
		data.setMsg("成功获取状态列表");
		data.setCode(0);
		data.setData(statusList);
		data.setCount(statusList.size());
		// BackendData data = new BackendData();

		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}

	@RequestMapping(value = "/getCapitalMetrics", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<BackendData> getCapitalTemplateList() {

		ArrayList capitals = (ArrayList) service.getCapitalMetrics(propConfig); 

		logger.info("successfully get capital list");

		BackendData data = new BackendData();
		data.setMsg("成功获取指标列表");
		data.setCode(0);
		data.setData(capitals);
		data.setCount(capitals.size());
		// BackendData data = new BackendData();

		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getBasicMetrics", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<BackendData> getBasicTemplateList() {

		ArrayList basic = (ArrayList) service.getBasicMetrics(propConfig);
		
		logger.info("successfully get basic form list");

		BackendData data = new BackendData();
		data.setMsg("成功获取指标列表");
		data.setCode(0);
		data.setData(basic);
		data.setCount(basic.size());
		// BackendData data = new BackendData();

		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}

	@RequestMapping(value = "/saveBasicForm", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<BackendData> saveBasicForm(HttpServletRequest request) {

		BackendData data = new BackendData();
		String collection_major_id = request.getParameter("collection_major_id");
		int result = 0;

		if (collection_major_id != null && !"".equals(collection_major_id)) {
			BasicForm bf = new BasicForm();
			bf.setCollection_major_id(Integer.parseInt(collection_major_id));
			bf.setProcess_status(EvalConstants.FORM_STATUS_INPUTING_INFORMATIN);
			bf.setUpdate_time(new Date());
			result = service.updateBasicForm(bf);
			
			service.updateTaskStatus(Integer.parseInt(collection_major_id), EvalConstants.PROCESS_STATUS_INPUTING_INFORMATION);
		}
		logger.info("successfully save the basic form");

		if (result != 0) {
			data.setMsg("成功保存基本情况表");
			data.setCode(0);
		}
		// BackendData data = new BackendData();

		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}

	@RequestMapping(value = "/deleteMaterial", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<BackendData> deleteMaterial(HttpServletRequest request) {

		BackendData data = new BackendData();
		String material_id = request.getParameter("id");
		int result = 0;

		if (material_id != null && !"".equals(material_id)) {
			result = service.deleteMaterial(Integer.parseInt(material_id));
		}
		logger.info("successfully delete the material");

		if (result != 0) {
			data.setMsg("成功删除材料");
			data.setCode(0);
		}
		// BackendData data = new BackendData();

		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}

	@RequestMapping(value = "/completeBasicForm", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<BackendData> completeBasicForm(HttpServletRequest request) {

		BackendData data = new BackendData();
		String collection_major_id = request.getParameter("collection_major_id");
		int result = 0;

		if (collection_major_id != null && !"".equals(collection_major_id)) {
			BasicForm bf = new BasicForm();
			bf.setCollection_major_id(Integer.parseInt(collection_major_id));
			bf.setProcess_status(EvalConstants.FORM_STATUS_COMPLETE);
			bf.setUpdate_time(new Date());
			result = service.updateBasicForm(bf);
		}
		logger.info("successfully update the basic form");

		if (result != 0) {
			data.setMsg("验证并完成填报情况表");
			data.setCode(0);
		}
		// BackendData data = new BackendData();

		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}

	@RequestMapping(value = "/completeCapitalForm", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<BackendData> completeCapitalForm(HttpServletRequest request) {

		BackendData data = new BackendData();
		String collection_major_id = request.getParameter("collection_major_id");
		int result = 0;

		if (collection_major_id != null && !"".equals(collection_major_id)) {
			CapitalProgressForm bf = new CapitalProgressForm();
			bf.setCollection_major_id(Integer.parseInt(collection_major_id));
			bf.setProcess_status(EvalConstants.FORM_STATUS_COMPLETE);
			bf.setUpdate_time(new Date());
			result = service.updateCapitalFormStatus(bf);
		}
		logger.info("successfully update the basic form");

		if (result != 0) {
			data.setMsg("验证并完成填报情况表");
			data.setCode(0);
		}
		// BackendData data = new BackendData();

		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}

	@RequestMapping(value = "/completePerformanceForm", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<BackendData> completePerformanceForm(HttpServletRequest request) {

		BackendData data = new BackendData();
		String collection_major_id = request.getParameter("collection_major_id");
		int result = 0;

		if (collection_major_id != null && !"".equals(collection_major_id)) {
			result = service.updatePerformanceFormStatus(EvalConstants.FORM_STATUS_COMPLETE,
					Integer.parseInt(collection_major_id));
		}
		logger.info("successfully update the performance form");

		if (result != 0) {
			data.setMsg("验证并完成业绩完成表");
			data.setCode(0);
		}
		// BackendData data = new BackendData();

		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/auditReport", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<BackendData> auditReport(HttpServletRequest request) {

		int result = 0;
		BackendData data = new BackendData();
		String collection_major_id = request.getParameter("collection_major_id");
		String role = request.getParameter("role");
		String operation = request.getParameter("operation");		
		
		if("SCHOOL".equals(role)){
			if("APPROVE".equals(operation)){
				result = service.updateTaskStatus(Integer.parseInt(collection_major_id),EvalConstants.PROCESS_STATUS_GOVERNMENT_VERIFY);
			} else if("REJECT".equals(operation)) {
				result = service.updateTaskStatus(Integer.parseInt(collection_major_id), EvalConstants.PROCESS_STATUS_SCHOOL_REJECT);
				
				BasicForm bf = new BasicForm();
				bf.setCollection_major_id(Integer.parseInt(collection_major_id));
				bf.setProcess_status(EvalConstants.FORM_STATUS_INPUTING_INFORMATIN);
				bf.setUpdate_time(new Date());
				service.updateBasicForm(bf);
				
				PerformanceForm pf = new PerformanceForm();
				service.updatePerformanceFormStatus(EvalConstants.FORM_STATUS_INPUTING_INFORMATIN, Integer.parseInt(collection_major_id));
				
				CapitalProgressForm cpf = new CapitalProgressForm();
				cpf.setCollection_major_id(Integer.parseInt(collection_major_id));
				cpf.setProcess_status(EvalConstants.FORM_STATUS_INPUTING_INFORMATIN);
				cpf.setUpdate_time(new Date());
				service.updateCapitalFormStatus(cpf);
			}
		} else if("GOVERNMENT".equals(role)){
			if("APPROVE".equals(operation)){
				result = service.updateTaskStatus(Integer.parseInt(collection_major_id), EvalConstants.PROCESS_STATUS_GORVERNMENT_APPROVE);
			} else if("REJECT".equals(operation)) {
				result = service.updateTaskStatus(Integer.parseInt(collection_major_id), EvalConstants.PROCESS_STATUS_GOVERNMENT_REJECT);
				
				BasicForm bf = new BasicForm();
				bf.setCollection_major_id(Integer.parseInt(collection_major_id));
				bf.setProcess_status(EvalConstants.FORM_STATUS_INPUTING_INFORMATIN);
				bf.setUpdate_time(new Date());
				service.updateBasicForm(bf);
				
				PerformanceForm pf = new PerformanceForm();
				service.updatePerformanceFormStatus(EvalConstants.FORM_STATUS_INPUTING_INFORMATIN, Integer.parseInt(collection_major_id));
				
				CapitalProgressForm cpf = new CapitalProgressForm();
				cpf.setCollection_major_id(Integer.parseInt(collection_major_id));
				cpf.setProcess_status(EvalConstants.FORM_STATUS_INPUTING_INFORMATIN);
				cpf.setUpdate_time(new Date());
				service.updateCapitalFormStatus(cpf);
			}
		}
		
		if (result != 0) {
			data.setMsg("成功完成审批");
			data.setCode(1);
		} else {
			data.setMsg("审批失败");
			data.setCode(99);
		}
		
		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}
}
