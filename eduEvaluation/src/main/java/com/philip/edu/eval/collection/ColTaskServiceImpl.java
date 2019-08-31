package com.philip.edu.eval.collection;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;

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

				// 6.insert form 3:
				CapitalProgressForm cpf = new CapitalProgressForm();
				cpf.setCollection_major_id(taskMajor.getId());
				cpf.setCreate_time(new Date());
				cpf.setUpdate_time(new Date());
				cpf.setProcess_status(EvalConstants.PROCESS_STATUS_NOT_INPUT);
				dao.insertCapitalProgressForm(cpf);
			}*/
			// dao.insertTaskMajors(defaultMajorTasks);

			// 3.2 update school majors:
			if (majors != null && majors.size()!=0) {
				HashMap choseSchool = new HashMap();
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
				dao.deleteTaskOldSchools(school_id2);

				for (int i = 0; i < majors.size(); i++) {
					ColTaskMajor inputMajor = (ColTaskMajor) majors.get(i);
					int m = dao.insertTaskMajor(inputMajor);

					// 4.insert form 1:
					BasicForm bf = new BasicForm();
					bf.setCollection_major_id(inputMajor.getId());
					bf.setCreate_time(new Date());
					bf.setUpdate_time(new Date());
					bf.setProcess_status(EvalConstants.PROCESS_STATUS_NOT_INPUT);
					dao.insertBasicForm(bf);

					// 5.insert form 2:
						//5.1 insert forms;
						PerformanceForm pf = new PerformanceForm();
						pf.setCollection_major_id(inputMajor.getId());
						pf.setM_system_id(EvalConstants.DEFAULT_METRICS_SYSTEM_ID);
						pf.setProcess_status(EvalConstants.PROCESS_STATUS_NOT_INPUT);
						
						ArrayList metrics = (ArrayList)dao.selectMetricsList(EvalConstants.DEFAULT_METRICS_SYSTEM_ID);
						for(int j=0; j<metrics.size(); j++){
							MetricsDetail metric = (MetricsDetail)metrics.get(j);
							pf.setCreate_time(new Date());
							pf.setUpdate_time(new Date());
							pf.setMetrics_id(metric.getId());
							pf.setUnit(metric.getUnit());
							dao.insertPerformanceForm(pf);
							 
							//5.2 insert materials:
							List<Material> materials = dao.getMaterialMetrics(metric.getId());
							for(int k=0; k<materials.size(); k++){
								Material material = (Material)materials.get(k);	
								material.setCreate_time(new Date());
								material.setUpdate_time(new Date());
								material.setIs_required(EvalConstants.MATERIAL_IS_REQUIRED);
								
								material.setForm_performance_id(pf.getId());
								dao.insertRelateMaterial(material);
							} 
						}
						
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

	public List<PerformanceForm> getPerformanceForm(int collection_major_id) {
		// TODO Auto-generated method stub
		return dao.getPerformanceForm(collection_major_id);
	}

	public List<Material> getRelateMaterials(int pf_id, int metrics_id) {
		// TODO Auto-generated method stub
		return dao.getRelateMaterials(pf_id, metrics_id);
	}

	public List<CapitalProgressForm> selectCapitalProgress(int collection_major_id) {
		// TODO Auto-generated method stub
		return dao.selectCapitalProgress(collection_major_id);
	}

	public int selectCapitalProgressMaterialsNum(CapitalProgressForm cpf, Properties prop){
		int result = 0;
		
		Integer rda_id = (Integer)prop.get("metrics_region_disbursement_amount");
		Integer rpha_id = (Integer)prop.get("metrics_region_paid_hardware_amount");
		Integer rpia_id = (Integer)prop.get("metrics_region_paid_internal_amount");
		Integer cda_id = (Integer)prop.get("metrics_central_disbursement_amount");
		Integer cpha_id = (Integer)prop.get("metrics_central_paid_hardware_amount");
		Integer cpia_id = (Integer)prop.get("metrics_central_paid_internal_amount");
		Integer sft_id = (Integer)prop.get("metrics_school_funding_total");
		Integer sfh_id = (Integer)prop.get("metrics_school_funding_hardware");
		Integer sfi_id = (Integer)prop.get("metrics_school_funding_internal");
		//region_disbursement_amount:
		ArrayList al = dao.getCapitalProgressItemId(cpf.getCollection_major_id(), rda_id);
		int pf_id = ((Integer)al.get(0)).intValue();
		String num = "要求" + dao.countRequiredMaterial(pf_id) + "项,已提交" + dao.countUploadedMaterial(pf_id) + "项";
		cpf.setRda_material_num(num);
		
		//region_paid_hardware_amount:
		al = dao.getCapitalProgressItemId(cpf.getCollection_major_id(), rpha_id);
		pf_id = ((Integer)al.get(0)).intValue();
		num = "要求" + dao.countRequiredMaterial(pf_id) + "项,已提交" + dao.countUploadedMaterial(pf_id) + "项";
		cpf.setRpha_material_num(num);
		
		//region_paid_internal_amount:
		al = dao.getCapitalProgressItemId(cpf.getCollection_major_id(), rpia_id);
		pf_id = ((Integer)al.get(0)).intValue();
		num = "要求" + dao.countRequiredMaterial(pf_id) + "项,已提交" + dao.countUploadedMaterial(pf_id) + "项";
		cpf.setRpia_material_num(num);
		
		//central_disbursement_amount:
		al = dao.getCapitalProgressItemId(cpf.getCollection_major_id(), cda_id);
		pf_id = ((Integer)al.get(0)).intValue();
		num = "要求" + dao.countRequiredMaterial(pf_id) + "项,已提交" + dao.countUploadedMaterial(pf_id) + "项";
		cpf.setCda_material_num(num);
		
		//central_paid_hardware_amount:
		al = dao.getCapitalProgressItemId(cpf.getCollection_major_id(), cpha_id);
		pf_id = ((Integer)al.get(0)).intValue();
		num = "要求" + dao.countRequiredMaterial(pf_id) + "项,已提交" + dao.countUploadedMaterial(pf_id) + "项";
		cpf.setCpha_material_num(num);
		
		//central_paid_internal_amount:
		al = dao.getCapitalProgressItemId(cpf.getCollection_major_id(), cpia_id);
		pf_id = ((Integer)al.get(0)).intValue();
		num = "要求" + dao.countRequiredMaterial(pf_id) + "项,已提交" + dao.countUploadedMaterial(pf_id) + "项";
		cpf.setCpia_material_num(num);
		
		//school_funding_total:
		al = dao.getCapitalProgressItemId(cpf.getCollection_major_id(), sft_id);
		pf_id = ((Integer)al.get(0)).intValue();
		num = "要求" + dao.countRequiredMaterial(pf_id) + "项,已提交" + dao.countUploadedMaterial(pf_id) + "项";
		cpf.setSft_material_num(num);
		
		//school_funding_hardware:
		al = dao.getCapitalProgressItemId(cpf.getCollection_major_id(), sfh_id);
		pf_id = ((Integer)al.get(0)).intValue();
		num = "要求" + dao.countRequiredMaterial(pf_id) + "项,已提交" + dao.countUploadedMaterial(pf_id) + "项";
		cpf.setSfh_material_num(num);
		
		//school_funding_internal:
		al = dao.getCapitalProgressItemId(cpf.getCollection_major_id(), sfi_id);
		pf_id = ((Integer)al.get(0)).intValue();
		num = "要求" + dao.countRequiredMaterial(pf_id) + "项,已提交" + dao.countUploadedMaterial(pf_id) + "项";
		cpf.setSfi_material_num(num);
		
		result = 1;
		
		return result;
	}
}
