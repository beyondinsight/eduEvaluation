package com.philip.edu.eval.collection;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.philip.edu.eval.bean.BasicForm;
import com.philip.edu.eval.bean.CapitalProgressForm;
import com.philip.edu.eval.bean.ChosenMajor;
import com.philip.edu.eval.bean.ColTaskMajor;
import com.philip.edu.eval.bean.ColTaskSchool;
import com.philip.edu.eval.bean.CollectionTask;
import com.philip.edu.eval.bean.Major;
import com.philip.edu.eval.bean.Material;
import com.philip.edu.eval.bean.MetricsDetail;
import com.philip.edu.eval.bean.PerformanceForm;
import com.philip.edu.eval.bean.School;
import com.philip.edu.eval.bean.TblMajor;
import com.philip.edu.eval.mapper.ColMapper;
import com.philip.edu.eval.mapper.DictMapper;
import com.philip.edu.eval.util.EvalConstants;

@org.springframework.stereotype.Service("colTask_service")
public class ColTaskServiceImpl implements ColTaskService {

	@Autowired
	private ColMapper dao;
	@Autowired
	private DictMapper dictDao;

	public List<ColTaskSchool> getTaskSchoolList(int task_id) {
		// TODO Auto-generated method stub
		return dao.getTaskSchoolList();
	}

	@Transactional
	public int createColTask(CollectionTask task, List<ColTaskSchool> schools, List<ColTaskMajor> majors) {
		// TODO Auto-generated method stub
		int result = 0;
		int school_ids[] = new int[schools.size()];
		// 1.insert task:
		int n = dao.insertColTask(task);

		if (schools != null && schools.size()!=0) {
			// 2.insert task-school:
			for (int i = 0; i < schools.size(); i++) {
				ColTaskSchool school = (ColTaskSchool) schools.get(i);
				school.setTask_id(task.getId());
				school_ids[i] = school.getSchool_id();
			}
			dao.insertColSchool(schools);

			// 3.insert task-major:
			// 3.1 default school majors:
			/*List<ColTaskMajor> defaultMajorTasks = new ArrayList();
			List<ChosenMajor> defaultMajors = (ArrayList) dictDao.getChosenMajorSchools(school_ids);
			for (int i = 0; i < defaultMajors.size(); i++) {
				ColTaskMajor taskMajor = new ColTaskMajor();
				ChosenMajor major = (ChosenMajor) defaultMajors.get(i);
				taskMajor.setMajor_id(major.getMajor_id());
				int[] colTaskSchoolId = dao.getColTaskSchoolId(task.getId(), major.getSchool_id());
				taskMajor.setCollection_school_id(colTaskSchoolId[0]);
				taskMajor.setProcess_status(EvalConstants.PROCESS_STATUS_NOT_INPUT);
				taskMajor.setCreate_time(new Date());
				taskMajor.setUpdate_time(new Date());
				int m = dao.insertTaskMajor(taskMajor);
				// defaultMajorTasks.add(taskMajor);

				// 4.insert form 1:
				BasicForm bf = new BasicForm();
				bf.setCollection_major_id(taskMajor.getId());
				bf.setCreate_time(new Date());
				bf.setUpdate_time(new Date());
				bf.setProcess_status(EvalConstants.PROCESS_STATUS_NOT_INPUT);
				dao.insertBasicForm(bf);

				// 5.insert form 2:
				PerformanceForm pf = new PerformanceForm();
				pf.setCollection_major_id(taskMajor.getId());
				pf.setCreate_time(new Date());
				pf.setUpdate_time(new Date());
				//pf.setOp_user(op_user);
				pf.setProcess_status(EvalConstants.PROCESS_STATUS_NOT_INPUT);
				dao.insertPerformanceForm(pf);
				
				// 6.insert form 3:
				CapitalProgressForm cpf = new CapitalProgressForm();
				cpf.setCollection_major_id(taskMajor.getId());
				cpf.setCreate_time(new Date());
				cpf.setUpdate_time(new Date());
				cpf.setProcess_status(EvalConstants.PROCESS_STATUS_NOT_INPUT);
				dao.insertCapitalProgressForm(cpf);
			}
			// dao.insertTaskMajors(defaultMajorTasks);*/

			// 3.2 update school majors:
			if (majors != null && majors.size()!=0) {
				/*HashMap choseSchool = new HashMap();
				for (int i = 0; i < majors.size(); i++) {
					ColTaskMajor inputMajor = (ColTaskMajor) majors.get(i);
					choseSchool.put(inputMajor.getSchool_id(), null);
					int[] colTaskNewSchoolId = dao.getColTaskSchoolId(task.getId(), inputMajor.getSchool_id());
					inputMajor.setCollection_school_id(colTaskNewSchoolId[0]);
					inputMajor.setProcess_status(EvalConstants.PROCESS_STATUS_NOT_INPUT);
					inputMajor.setCreate_time(new Date());
					inputMajor.setUpdate_time(new Date());
				}
				int[] school_id2 = new int[choseSchool.size()];
				Iterator iterator = choseSchool.entrySet().iterator();
				int index = 0;
				while (iterator.hasNext()) {
					Entry entrySchool = (Entry) iterator.next();
					int tempId = ((Integer) entrySchool.getKey()).intValue();
					int[] tempId2 = dao.getColTaskSchoolId(task.getId(), tempId);
					school_id2[index++] = tempId2[0];
				}
				dao.deleteTaskOldSchools(school_id2);*/

				for (int i = 0; i < majors.size(); i++) {
					ColTaskMajor inputMajor = (ColTaskMajor) majors.get(i);
					inputMajor.setProcess_status(EvalConstants.PROCESS_STATUS_NOT_INPUT);
					inputMajor.setCreate_time(new Date());
					inputMajor.setUpdate_time(new Date());
					int[] colTaskNewSchoolId = dao.getColTaskSchoolId(task.getId(), inputMajor.getSchool_id());
					inputMajor.setCollection_school_id(colTaskNewSchoolId[0]);
					int m = dao.insertTaskMajor(inputMajor);

					// 4.insert form 1:
					BasicForm bf = new BasicForm();
					bf.setCollection_major_id(inputMajor.getId());
					bf.setCreate_time(new Date());
					bf.setUpdate_time(new Date());
					bf.setProcess_status(EvalConstants.PROCESS_STATUS_NOT_INPUT);
					dao.insertBasicForm(bf);

					// 5.insert form 2:  
					PerformanceForm pf = new PerformanceForm();
					pf.setCollection_major_id(inputMajor.getId());
					pf.setCreate_time(new Date());
					pf.setUpdate_time(new Date());
					//pf.setOp_user(op_user);
					pf.setProcess_status(EvalConstants.PROCESS_STATUS_NOT_INPUT);
					pf.setM_system_id(EvalConstants.DEFAULT_METRICS_SYSTEM_ID);
					dao.insertPerformanceForm(pf);
					
					// 6.insert form 3:
					CapitalProgressForm cpf = new CapitalProgressForm();
					cpf.setCollection_major_id(inputMajor.getId());
					cpf.setCreate_time(new Date());
					cpf.setUpdate_time(new Date());
					cpf.setProcess_status(EvalConstants.PROCESS_STATUS_NOT_INPUT);
					dao.insertCapitalProgressForm(cpf);
				}
			}
		}

		result = 1;

		return result;
	}

	public List<CollectionTask> getColTaskList() {
		// TODO Auto-generated method stub
		return dao.getColTaskList();
	}

	public int countTaskSchool(int task_id) {
		// TODO Auto-generated method stub
		return dao.countTaskSchool(task_id);
	}

	public int countTaskMajor(int task_id) {
		// TODO Auto-generated method stub
		return dao.countTaskMajor(task_id);
	}

	public int updateStatus(int task_id, char status) {
		// TODO Auto-generated method stub
		return dao.updateStatus(task_id, status);
	}

	public int batchDeleteTasks(int[] ids) {
		// TODO Auto-generated method stub
		return dao.batchDeleteTasks(ids);
	}

	public int createMetrics(MetricsDetail metrics, List<Material> materials) {
		// TODO Auto-generated method stub
		int result = dao.insertMetrics(metrics);
		for(int i=0; i<materials.size(); i++){
			Material material = (Material)materials.get(i);
			material.setMetrics_id(metrics.getId());
		}
		if(materials.size()!=0)result = dao.insertMaterials(materials);
		return result;
	}

	public List<MetricsDetail> getMetricsList(int metrics_system) {
		// TODO Auto-generated method stub
		return dao.selectMetricsList(metrics_system);
	}

	public int countMaterials(int metrics_id) {
		// TODO Auto-generated method stub
		return dao.countMaterials(metrics_id);
	}

	public int updateMetrics(MetricsDetail metrics) {
		// TODO Auto-generated method stub
		return dao.updateMetrics(metrics);
	}

	public int deleteMetrics(int metrics_id) {
		// TODO Auto-generated method stub
		return dao.deleteMetrics(metrics_id);
	}

	public int insertMaterial(Material material) {
		// TODO Auto-generated method stub
		return dao.insertMaterial(material);
	}

	public List<Material> getMaterials(int metrics_id) {
		// TODO Auto-generated method stub
		return dao.selectMaterials(metrics_id);
	}

	public int deleteMaterial(int id) {
		// TODO Auto-generated method stub
		return dao.deleteMaterial(id);
	}

}
