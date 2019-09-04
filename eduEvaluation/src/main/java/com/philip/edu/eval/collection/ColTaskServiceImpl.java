package com.philip.edu.eval.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.philip.edu.eval.bean.BasicForm;
import com.philip.edu.eval.bean.BasicFormInfo;
import com.philip.edu.eval.bean.CapitalProgressForm;
import com.philip.edu.eval.bean.ChosenMajor;
import com.philip.edu.eval.bean.ColTaskMajor;
import com.philip.edu.eval.bean.ColTaskSchool;
import com.philip.edu.eval.bean.CollectionTask;
import com.philip.edu.eval.bean.Major;
import com.philip.edu.eval.bean.Material;
import com.philip.edu.eval.bean.MetricsDetail;
import com.philip.edu.eval.bean.PerformanceForm;
import com.philip.edu.eval.bean.ProcessStatus;
import com.philip.edu.eval.bean.School;
import com.philip.edu.eval.bean.TblMajor;
import com.philip.edu.eval.mapper.ColMapper;
import com.philip.edu.eval.mapper.DictMapper;
import com.philip.edu.eval.util.EvalConstants;

@org.springframework.stereotype.Service("colTask_service")
public class ColTaskServiceImpl implements ColTaskService {

	private static final Logger logger = Logger.getLogger(ColTaskServiceImpl.class);
	
	@Autowired
	private ColMapper dao;
	@Autowired
	private DictMapper dictDao;

	private int METRICS_MAJOR_BASIC_ID;
	private int METRICS_SELF_EVAL_ID;

	private int metrics_region_disbursement_amount_id;
	private int metrics_region_paid_hardware_amount_id;
	private int metrics_region_paid_internal_amount_id;
	private int metrics_central_disbursement_amount_id;
	private int metrics_central_paid_hardware_amount_id;
	private int metrics_central_paid_internal_amount_id;
	private int metrics_school_funding_total_id;
	private int metrics_school_funding_hardware_id;
	private int metrics_school_funding_internal_id;
	private int template_basic_form_id;
	private int template_performance_form_id;
	private int template_capital_progress_form_id;

	public void use_properties(Properties prop) {
		template_basic_form_id = Integer.parseInt((String) prop.get("template_basic_form_id"));
		template_performance_form_id = Integer.parseInt((String) prop.get("template_performance_form_id"));
		template_capital_progress_form_id = Integer.parseInt((String) prop.get("template_capital_progress_form_id"));

		METRICS_MAJOR_BASIC_ID = Integer.parseInt((String) prop.get("metrics_major_basic_id"));
		METRICS_SELF_EVAL_ID = Integer.parseInt((String) prop.get("metrics_self_eval_id"));

		metrics_region_disbursement_amount_id = (Integer
				.parseInt((String) prop.get("metrics_region_disbursement_amount_id")));
		metrics_region_paid_hardware_amount_id = (Integer
				.parseInt((String) prop.get("metrics_region_paid_hardware_amount_id")));
		metrics_region_paid_internal_amount_id = (Integer
				.parseInt((String) prop.get("metrics_region_paid_internal_amount_id")));
		metrics_central_disbursement_amount_id = (Integer
				.parseInt((String) prop.get("metrics_central_disbursement_amount_id")));
		metrics_central_paid_hardware_amount_id = (Integer
				.parseInt((String) prop.get("metrics_central_paid_hardware_amount_id")));
		metrics_central_paid_internal_amount_id = (Integer
				.parseInt((String) prop.get("metrics_central_paid_internal_amount_id")));
		metrics_school_funding_total_id = (Integer.parseInt((String) prop.get("metrics_school_funding_total_id")));
		metrics_school_funding_hardware_id = (Integer
				.parseInt((String) prop.get("metrics_school_funding_hardware_id")));
		metrics_school_funding_internal_id = (Integer
				.parseInt((String) prop.get("metrics_school_funding_internal_id")));
	}

	public List<ColTaskSchool> getTaskSchoolList(int task_id) {
		// TODO Auto-generated method stub
		return dao.getTaskSchoolList();
	}

	@Transactional
	public int createColTask(CollectionTask task, List<ColTaskSchool> schools, List<ColTaskMajor> majors,
			Properties prop) {
		// TODO Auto-generated method stub
		int result = 0;

		this.use_properties(prop);

		int school_ids[] = new int[schools.size()];
		// 1.insert task:
		int n = dao.insertColTask(task);

		if (schools != null && schools.size() != 0) {
			// 2.insert task-school:
			for (int i = 0; i < schools.size(); i++) {
				ColTaskSchool school = (ColTaskSchool) schools.get(i);
				school.setTask_id(task.getId());
				school_ids[i] = school.getSchool_id();
				school.setProcess_status(EvalConstants.PROCESS_STATUS_NOT_INPUT);
			}
			dao.insertColSchool(schools);
			logger.info("sucessfully insert schools.");

			// 3.insert task-major:
			// 3.1 default school majors:
			/*
			 * List<ColTaskMajor> defaultMajorTasks = new ArrayList();
			 * List<ChosenMajor> defaultMajors = (ArrayList)
			 * dictDao.getChosenMajorSchools(school_ids); for (int i = 0; i <
			 * defaultMajors.size(); i++) { ColTaskMajor taskMajor = new
			 * ColTaskMajor(); ChosenMajor major = (ChosenMajor)
			 * defaultMajors.get(i); taskMajor.setMajor_id(major.getMajor_id());
			 * int[] colTaskSchoolId = dao.getColTaskSchoolId(task.getId(),
			 * major.getSchool_id());
			 * taskMajor.setCollection_school_id(colTaskSchoolId[0]);
			 * taskMajor.setProcess_status(EvalConstants.
			 * PROCESS_STATUS_NOT_INPUT); taskMajor.setCreate_time(new Date());
			 * taskMajor.setUpdate_time(new Date()); int m =
			 * dao.insertTaskMajor(taskMajor); //
			 * defaultMajorTasks.add(taskMajor);
			 * 
			 * // 4.insert form 1: BasicForm bf = new BasicForm();
			 * bf.setCollection_major_id(taskMajor.getId());
			 * bf.setCreate_time(new Date()); bf.setUpdate_time(new Date());
			 * bf.setProcess_status(EvalConstants.PROCESS_STATUS_NOT_INPUT);
			 * dao.insertBasicForm(bf);
			 * 
			 * // 5.insert form 2:  
			 * 
			 * // 6.insert form 3: CapitalProgressForm cpf = new
			 * CapitalProgressForm();
			 * cpf.setCollection_major_id(taskMajor.getId());
			 * cpf.setCreate_time(new Date()); cpf.setUpdate_time(new Date());
			 * cpf.setProcess_status(EvalConstants.PROCESS_STATUS_NOT_INPUT);
			 * dao.insertCapitalProgressForm(cpf); }
			 */
			// dao.insertTaskMajors(defaultMajorTasks);

			// 3.2 update school majors:
			if (majors != null && majors.size() != 0) {
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
				logger.info("successfully delete nonchose Schools");

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

					PerformanceForm pf1 = new PerformanceForm();
					pf1.setCollection_major_id(inputMajor.getId());
					pf1.setM_system_id(this.template_basic_form_id);
					pf1.setProcess_status(EvalConstants.PROCESS_STATUS_NOT_INPUT);
					pf1.setCreate_time(new Date());
					pf1.setUpdate_time(new Date());
					pf1.setFor_template(this.template_basic_form_id);
					pf1.setMetrics_id(this.METRICS_MAJOR_BASIC_ID);
					dao.insertPerformanceForm(pf1);
					List<Material> materials = dao.getMaterialMetrics(this.METRICS_MAJOR_BASIC_ID);
					for (int k = 0; k < materials.size(); k++) {
						Material material = (Material) materials.get(k);
						material.setCreate_time(new Date());
						material.setUpdate_time(new Date());
						material.setIs_required(EvalConstants.MATERIAL_IS_REQUIRED);

						material.setForm_performance_id(pf1.getId());
						dao.insertRelateMaterial(material);
						logger.info("successfully insert one material");
					}
					pf1.setMetrics_id(this.METRICS_SELF_EVAL_ID);
					dao.insertPerformanceForm(pf1);
					materials = dao.getMaterialMetrics(this.METRICS_SELF_EVAL_ID);
					for (int k = 0; k < materials.size(); k++) {
						Material material = (Material) materials.get(k);
						material.setCreate_time(new Date());
						material.setUpdate_time(new Date());
						material.setIs_required(EvalConstants.MATERIAL_IS_REQUIRED);

						material.setForm_performance_id(pf1.getId());
						dao.insertRelateMaterial(material);
						logger.info("successfully insert one material");
					}
					logger.info("successfully insert basic form");

					// 5.insert form 2:
					// 5.1 insert forms;
					PerformanceForm pf = new PerformanceForm();
					pf.setCollection_major_id(inputMajor.getId());
					pf.setM_system_id(this.template_performance_form_id);
					pf.setProcess_status(EvalConstants.PROCESS_STATUS_NOT_INPUT);
					pf.setFor_template(this.template_performance_form_id);

					ArrayList metrics = (ArrayList) dao.selectMetricsList(this.template_performance_form_id);
					for (int j = 0; j < metrics.size(); j++) {
						MetricsDetail metric = (MetricsDetail) metrics.get(j);
						pf.setCreate_time(new Date());
						pf.setUpdate_time(new Date());
						pf.setMetrics_id(metric.getId());
						pf.setUnit(metric.getUnit());
						dao.insertPerformanceForm(pf);
						logger.info("successfully insert one performance form");

						// 5.2 insert materials:
						materials = dao.getMaterialMetrics(metric.getId());
						for (int k = 0; k < materials.size(); k++) {
							Material material = (Material) materials.get(k);
							material.setCreate_time(new Date());
							material.setUpdate_time(new Date());
							material.setIs_required(EvalConstants.MATERIAL_IS_REQUIRED);

							material.setForm_performance_id(pf.getId());
							dao.insertRelateMaterial(material);
							logger.info("successfully insert one material");
						}
					}

					// 6.insert form 3:
					CapitalProgressForm cpf = new CapitalProgressForm();
					cpf.setCollection_major_id(inputMajor.getId());
					cpf.setCreate_time(new Date());
					cpf.setUpdate_time(new Date());
					cpf.setProcess_status(EvalConstants.PROCESS_STATUS_NOT_INPUT);
					dao.insertCapitalProgressForm(cpf);

					PerformanceForm pf2 = new PerformanceForm();
					pf2.setCollection_major_id(inputMajor.getId());
					pf2.setM_system_id(this.template_capital_progress_form_id);
					pf2.setProcess_status(EvalConstants.PROCESS_STATUS_NOT_INPUT);
					pf2.setFor_template(this.template_capital_progress_form_id);
					pf2.setCreate_time(new Date());
					pf2.setUpdate_time(new Date());
					pf2.setMetrics_id(this.metrics_central_disbursement_amount_id);
					dao.insertPerformanceForm(pf2);
					this.insertMaterials(pf2.getId(), this.template_capital_progress_form_id,
							this.metrics_central_disbursement_amount_id);
					pf2.setMetrics_id(this.metrics_central_paid_hardware_amount_id);
					dao.insertPerformanceForm(pf2);
					this.insertMaterials(pf2.getId(), this.template_capital_progress_form_id,
							this.metrics_central_paid_hardware_amount_id);
					pf2.setMetrics_id(this.metrics_central_paid_internal_amount_id);
					dao.insertPerformanceForm(pf2);
					this.insertMaterials(pf2.getId(), this.template_capital_progress_form_id,
							this.metrics_central_paid_internal_amount_id);
					pf2.setMetrics_id(this.metrics_region_disbursement_amount_id);
					dao.insertPerformanceForm(pf2);
					this.insertMaterials(pf2.getId(), this.template_capital_progress_form_id,
							this.metrics_region_disbursement_amount_id);
					pf2.setMetrics_id(this.metrics_region_paid_hardware_amount_id);
					dao.insertPerformanceForm(pf2);
					this.insertMaterials(pf2.getId(), this.template_capital_progress_form_id,
							this.metrics_region_paid_hardware_amount_id);
					pf2.setMetrics_id(this.metrics_region_paid_internal_amount_id);
					dao.insertPerformanceForm(pf2);
					this.insertMaterials(pf2.getId(), this.template_capital_progress_form_id,
							this.metrics_region_paid_internal_amount_id);
					pf2.setMetrics_id(this.metrics_school_funding_total_id);
					dao.insertPerformanceForm(pf2);
					this.insertMaterials(pf2.getId(), this.template_capital_progress_form_id,
							this.metrics_school_funding_total_id);
					pf2.setMetrics_id(this.metrics_school_funding_hardware_id);
					dao.insertPerformanceForm(pf2);
					this.insertMaterials(pf2.getId(), this.template_capital_progress_form_id,
							this.metrics_school_funding_hardware_id);
					pf2.setMetrics_id(this.metrics_school_funding_internal_id);
					dao.insertPerformanceForm(pf2);
					this.insertMaterials(pf2.getId(), this.template_capital_progress_form_id,
							this.metrics_school_funding_internal_id);
					logger.info("successfully insert capital progress.");
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
		for (int i = 0; i < materials.size(); i++) {
			Material material = (Material) materials.get(i);
			material.setMetrics_id(metrics.getId());
		}
		if (materials.size() != 0)
			result = dao.insertMaterials(materials);
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

	public List<PerformanceForm> getPerformanceForm(int collection_major_id, int for_template) {
		// TODO Auto-generated method stub
		return dao.getPerformanceForm(collection_major_id, for_template);
	}

	public List<Material> getRelateMaterials(int pf_id, int metrics_id) {
		// TODO Auto-generated method stub
		return dao.getRelateMaterials(pf_id, metrics_id);
	}

	public List<CapitalProgressForm> selectCapitalProgress(int collection_major_id) {
		// TODO Auto-generated method stub
		return dao.selectCapitalProgress(collection_major_id);
	}

	public int selectPerformanceMaterialsNum(ArrayList performanceForm) {
		for (int i = 0; i < performanceForm.size(); i++) {
			PerformanceForm pf = (PerformanceForm) performanceForm.get(i);
			int pf_id = pf.getId();
			String num = "要求" + dao.countRequiredMaterial(pf_id) + "项,已提交" + dao.countUploadedMaterial(pf_id) + "项";
			pf.setMaterial_num(num);
		}
		return performanceForm.size();
	}

	public int selectCapitalProgressMaterialsNumAndPerformanceId(CapitalProgressForm cpf, Properties prop) {
		int result = 0;

		this.use_properties(prop);
		// region_disbursement_amount:
		int test = cpf.getCollection_major_id();
		ArrayList al = dao.getCapitalProgressItemId(cpf.getCollection_major_id(), this.metrics_region_disbursement_amount_id);
		int pf_id = ((Integer) al.get(0)).intValue();
		String num = "要求" + dao.countRequiredMaterial(pf_id) + "项,已提交" + dao.countUploadedMaterial(pf_id) + "项";
		cpf.setRda_material_num(num);
		cpf.setRda_pf_id(pf_id);	

		// region_paid_hardware_amount:
		al = dao.getCapitalProgressItemId(cpf.getCollection_major_id(), this.metrics_region_paid_hardware_amount_id);
		pf_id = ((Integer) al.get(0)).intValue();
		num = "要求" + dao.countRequiredMaterial(pf_id) + "项,已提交" + dao.countUploadedMaterial(pf_id) + "项";
		cpf.setRpha_material_num(num);
		cpf.setRpha_pf_id(pf_id);

		// region_paid_internal_amount:
		al = dao.getCapitalProgressItemId(cpf.getCollection_major_id(), this.metrics_region_paid_internal_amount_id);
		pf_id = ((Integer) al.get(0)).intValue();
		num = "要求" + dao.countRequiredMaterial(pf_id) + "项,已提交" + dao.countUploadedMaterial(pf_id) + "项";
		cpf.setRpia_material_num(num);
		cpf.setRpia_pf_id(pf_id);
		
		// central_disbursement_amount:
		al = dao.getCapitalProgressItemId(cpf.getCollection_major_id(), this.metrics_central_disbursement_amount_id);
		pf_id = ((Integer) al.get(0)).intValue();
		num = "要求" + dao.countRequiredMaterial(pf_id) + "项,已提交" + dao.countUploadedMaterial(pf_id) + "项";
		cpf.setCda_material_num(num);
		cpf.setCda_pf_id(pf_id);

		// central_paid_hardware_amount:
		al = dao.getCapitalProgressItemId(cpf.getCollection_major_id(), this.metrics_central_paid_hardware_amount_id);
		pf_id = ((Integer) al.get(0)).intValue();
		num = "要求" + dao.countRequiredMaterial(pf_id) + "项,已提交" + dao.countUploadedMaterial(pf_id) + "项";
		cpf.setCpha_material_num(num);
		cpf.setCpha_pf_id(pf_id);

		// central_paid_internal_amount:
		al = dao.getCapitalProgressItemId(cpf.getCollection_major_id(), this.metrics_central_paid_internal_amount_id);
		pf_id = ((Integer) al.get(0)).intValue();
		num = "要求" + dao.countRequiredMaterial(pf_id) + "项,已提交" + dao.countUploadedMaterial(pf_id) + "项";
		cpf.setCpia_material_num(num);
		cpf.setCpia_pf_id(pf_id);

		// school_funding_total:
		al = dao.getCapitalProgressItemId(cpf.getCollection_major_id(), this.metrics_school_funding_total_id);
		pf_id = ((Integer) al.get(0)).intValue();
		num = "要求" + dao.countRequiredMaterial(pf_id) + "项,已提交" + dao.countUploadedMaterial(pf_id) + "项";
		cpf.setSft_material_num(num);
		cpf.setSft_pf_id(pf_id);

		// school_funding_hardware:
		al = dao.getCapitalProgressItemId(cpf.getCollection_major_id(), this.metrics_school_funding_hardware_id);
		pf_id = ((Integer) al.get(0)).intValue();
		num = "要求" + dao.countRequiredMaterial(pf_id) + "项,已提交" + dao.countUploadedMaterial(pf_id) + "项";
		cpf.setSfh_material_num(num);
		cpf.setSfh_pf_id(pf_id);

		// school_funding_internal:
		al = dao.getCapitalProgressItemId(cpf.getCollection_major_id(), this.metrics_school_funding_internal_id);
		pf_id = ((Integer) al.get(0)).intValue();
		num = "要求" + dao.countRequiredMaterial(pf_id) + "项,已提交" + dao.countUploadedMaterial(pf_id) + "项";
		cpf.setSfi_material_num(num);
		cpf.setSfi_pf_id(pf_id);

		result = 1;

		return result;
	}

	private int insertMaterials(int performance_id, int template_id, int metrics_id) {
		// 5.2 insert materials:
		List<Material> materials = dao.getMaterialMetrics(metrics_id);
		for (int k = 0; k < materials.size(); k++) {
			Material material = (Material) materials.get(k);
			material.setCreate_time(new Date());
			material.setUpdate_time(new Date());
			material.setIs_required(EvalConstants.MATERIAL_IS_REQUIRED);

			material.setForm_performance_id(performance_id);
			dao.insertRelateMaterial(material);
		}

		return materials.size();
	}

	public int updatePerformanceForm(PerformanceForm pf) {
		// TODO Auto-generated method stub
		return dao.updatePerformanceForm(pf);
	}

	public List<ColTaskSchool> getChosenSchool(int task_id) {
		// TODO Auto-generated method stub
		return dao.getChosenSchools(task_id);
	}

	public List<ColTaskMajor> getChosenMajor(int collection_school_id) {
		// TODO Auto-generated method stub
		return dao.getChosenMajors(collection_school_id);
	}

	public int changeSchool(int task_id, int[] chose_id, Properties prop) {
		int result = 0;
		this.use_properties(prop);

		// TODO Auto-generated method stub
		if (chose_id != null && chose_id.length != 0) {
			int[] deleteSchool = new int[100];
			Integer[] tempSchoolId = new Integer[100];
			int[] addSchool = new int[100];
			Integer[] chose_id_I = new Integer[chose_id.length];
			int index = 0;

			for(int xx=0; xx < chose_id.length; xx++){
				chose_id_I[xx] = new Integer(chose_id[xx]);
			}
			// 1.some school deleted:
			List<ColTaskSchool> t_schools = dao.getChosenSchools(task_id);
			for (int x = 0; x < t_schools.size(); x++) {
				ColTaskSchool task_school = (ColTaskSchool) t_schools.get(x);
				tempSchoolId[x] = new Integer(task_school.getSchool_id());
				if (!Arrays.asList(chose_id_I).contains(new Integer(task_school.getSchool_id())))
					deleteSchool[index++] = task_school.getSchool_id();
			}
			dao.deleteTaskSchools(deleteSchool);
 
			// 2.some school added: ,others keep as before; 
			for (int i = 0; i < chose_id_I.length; i++) {
				if (!Arrays.asList(tempSchoolId).contains(chose_id_I[i])) {
					ArrayList schools = new ArrayList();
					ColTaskSchool school = new ColTaskSchool();
					school.setTask_id(task_id);
					school.setSchool_id(chose_id[i]);
					school.setProcess_status(EvalConstants.PROCESS_STATUS_NOT_INPUT);
					dao.insertTaskSchool(school);
					int[] taskSchools = new int[1];
					taskSchools[0] = chose_id[i]; 

					List<ChosenMajor> defaultMajors = (ArrayList) dictDao.getChosenMajorSchools(taskSchools);
					for (int j = 0; j < defaultMajors.size(); j++) {
						ColTaskMajor taskMajor = new ColTaskMajor();
						ChosenMajor major = (ChosenMajor) defaultMajors.get(j);
						taskMajor.setMajor_id(major.getMajor_id());
						taskMajor.setCollection_school_id(school.getId());
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

						PerformanceForm pf1 = new PerformanceForm();
						pf1.setCollection_major_id(taskMajor.getId());
						pf1.setM_system_id(this.template_basic_form_id);
						pf1.setProcess_status(EvalConstants.PROCESS_STATUS_NOT_INPUT);
						pf1.setCreate_time(new Date());
						pf1.setUpdate_time(new Date());
						pf1.setMetrics_id(this.METRICS_MAJOR_BASIC_ID);
						dao.insertPerformanceForm(pf1);
						List<Material> materials = dao.getMaterialMetrics(this.METRICS_MAJOR_BASIC_ID);
						for (int k = 0; k < materials.size(); k++) {
							Material material = (Material) materials.get(k);
							material.setCreate_time(new Date());
							material.setUpdate_time(new Date());
							material.setIs_required(EvalConstants.MATERIAL_IS_REQUIRED);

							material.setForm_performance_id(pf1.getId());
							dao.insertRelateMaterial(material);
							logger.info("successfully insert one material");
						}
						pf1.setMetrics_id(this.METRICS_SELF_EVAL_ID);
						dao.insertPerformanceForm(pf1);
						materials = dao.getMaterialMetrics(this.METRICS_SELF_EVAL_ID);
						for (int k = 0; k < materials.size(); k++) {
							Material material = (Material) materials.get(k);
							material.setCreate_time(new Date());
							material.setUpdate_time(new Date());
							material.setIs_required(EvalConstants.MATERIAL_IS_REQUIRED);

							material.setForm_performance_id(pf1.getId());
							dao.insertRelateMaterial(material);
							logger.info("successfully insert one material");
						}

						// 5.insert form 2:
						// 5.1 insert forms;
						PerformanceForm pf = new PerformanceForm();
						pf.setCollection_major_id(taskMajor.getId());
						pf.setM_system_id(this.template_performance_form_id);
						pf.setProcess_status(EvalConstants.PROCESS_STATUS_NOT_INPUT);

						ArrayList metrics = (ArrayList) dao.selectMetricsList(this.template_performance_form_id);
						for (int k = 0; k < metrics.size(); k++) {
							MetricsDetail metric = (MetricsDetail) metrics.get(k);
							pf.setCreate_time(new Date());
							pf.setUpdate_time(new Date());
							pf.setMetrics_id(metric.getId());
							pf.setUnit(metric.getUnit());
							dao.insertPerformanceForm(pf);

							// 5.2 insert materials:
							materials = dao.getMaterialMetrics(metric.getId());
							for (int l = 0; l < materials.size(); l++) {
								Material material = (Material) materials.get(l);
								material.setCreate_time(new Date());
								material.setUpdate_time(new Date());
								material.setIs_required(EvalConstants.MATERIAL_IS_REQUIRED);

								material.setForm_performance_id(pf.getId());
								dao.insertRelateMaterial(material);
							}
						}

						// 6.insert form 3:
						CapitalProgressForm cpf = new CapitalProgressForm();
						cpf.setCollection_major_id(taskMajor.getId());
						cpf.setCreate_time(new Date());
						cpf.setUpdate_time(new Date());
						cpf.setProcess_status(EvalConstants.PROCESS_STATUS_NOT_INPUT);
						dao.insertCapitalProgressForm(cpf);

						PerformanceForm pf2 = new PerformanceForm();
						pf2.setCollection_major_id(taskMajor.getId());
						pf2.setM_system_id(this.template_capital_progress_form_id);
						pf2.setProcess_status(EvalConstants.PROCESS_STATUS_NOT_INPUT);
						pf2.setCreate_time(new Date());
						pf2.setUpdate_time(new Date());
						pf2.setMetrics_id(this.metrics_central_disbursement_amount_id);
						dao.insertPerformanceForm(pf2);
						this.insertMaterials(pf2.getId(), this.template_capital_progress_form_id,
								this.metrics_central_disbursement_amount_id);
						pf2.setMetrics_id(this.metrics_central_paid_hardware_amount_id);
						dao.insertPerformanceForm(pf2);
						this.insertMaterials(pf2.getId(), this.template_capital_progress_form_id,
								this.metrics_central_paid_hardware_amount_id);
						pf2.setMetrics_id(this.metrics_central_paid_internal_amount_id);
						dao.insertPerformanceForm(pf2);
						this.insertMaterials(pf2.getId(), this.template_capital_progress_form_id,
								this.metrics_central_paid_internal_amount_id);
						pf2.setMetrics_id(this.metrics_region_disbursement_amount_id);
						dao.insertPerformanceForm(pf2);
						this.insertMaterials(pf2.getId(), this.template_capital_progress_form_id,
								this.metrics_region_disbursement_amount_id);
						pf2.setMetrics_id(this.metrics_region_paid_hardware_amount_id);
						dao.insertPerformanceForm(pf2);
						this.insertMaterials(pf2.getId(), this.template_capital_progress_form_id,
								this.metrics_region_paid_hardware_amount_id);
						pf2.setMetrics_id(this.metrics_region_paid_internal_amount_id);
						dao.insertPerformanceForm(pf2);
						this.insertMaterials(pf2.getId(), this.template_capital_progress_form_id,
								this.metrics_region_paid_internal_amount_id);
						pf2.setMetrics_id(this.metrics_school_funding_total_id);
						dao.insertPerformanceForm(pf2);
						this.insertMaterials(pf2.getId(), this.template_capital_progress_form_id,
								this.metrics_school_funding_total_id);
						pf2.setMetrics_id(this.metrics_school_funding_hardware_id);
						dao.insertPerformanceForm(pf2);
						this.insertMaterials(pf2.getId(), this.template_capital_progress_form_id,
								this.metrics_school_funding_hardware_id);
						pf2.setMetrics_id(this.metrics_school_funding_internal_id);
						dao.insertPerformanceForm(pf2);
						this.insertMaterials(pf2.getId(), this.template_capital_progress_form_id,
								this.metrics_school_funding_internal_id);
					}
				}
			}

		}

		result = 1;

		return result;
	}

	public int deleteTaskSchool(int task_id) {
		// TODO Auto-generated method stub
		return dao.deleteTaskSchool(task_id);
	}

	public int insertTaskSchool(ColTaskSchool colTaskSchool) {
		// TODO Auto-generated method stub
		return dao.insertTaskSchool(colTaskSchool);
	}

	public int changeSchool(int task_id, int chose_id) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int changeMajor(int collection_school_id, int[] chose_id, Properties prop) {
		// TODO Auto-generated method stub
		// 1.delete old majors:
		int result = 0;
		this.use_properties(prop);
		
		dao.deleteTaskMajors(collection_school_id);
		// 2.add new majors:
		for (int j = 0; j < chose_id.length; j++) {
			ColTaskMajor taskMajor = new ColTaskMajor();
			taskMajor.setMajor_id(chose_id[j]);
			taskMajor.setCollection_school_id(collection_school_id);
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

			PerformanceForm pf1 = new PerformanceForm();
			pf1.setCollection_major_id(taskMajor.getId());
			pf1.setM_system_id(this.template_basic_form_id);
			pf1.setProcess_status(EvalConstants.PROCESS_STATUS_NOT_INPUT);
			pf1.setCreate_time(new Date());
			pf1.setUpdate_time(new Date());
			pf1.setMetrics_id(this.METRICS_MAJOR_BASIC_ID);
			dao.insertPerformanceForm(pf1);
			pf1.setMetrics_id(this.METRICS_SELF_EVAL_ID);
			dao.insertPerformanceForm(pf1);

			// 5.insert form 2:
			// 5.1 insert forms;
			PerformanceForm pf = new PerformanceForm();
			pf.setCollection_major_id(taskMajor.getId());
			pf.setM_system_id(this.template_performance_form_id);
			pf.setProcess_status(EvalConstants.PROCESS_STATUS_NOT_INPUT);

			ArrayList metrics = (ArrayList) dao.selectMetricsList(this.template_performance_form_id);
			for (int k = 0; k < metrics.size(); k++) {
				MetricsDetail metric = (MetricsDetail) metrics.get(k);
				pf.setCreate_time(new Date());
				pf.setUpdate_time(new Date());
				pf.setMetrics_id(metric.getId());
				pf.setUnit(metric.getUnit());
				dao.insertPerformanceForm(pf);

				// 5.2 insert materials:
				List<Material> materials = dao.getMaterialMetrics(metric.getId());
				for (int l = 0; l < materials.size(); l++) {
					Material material = (Material) materials.get(l);
					material.setCreate_time(new Date());
					material.setUpdate_time(new Date());
					material.setIs_required(EvalConstants.MATERIAL_IS_REQUIRED);

					material.setForm_performance_id(pf.getId());
					dao.insertRelateMaterial(material);
				}
			}

			// 6.insert form 3:
			CapitalProgressForm cpf = new CapitalProgressForm();
			cpf.setCollection_major_id(taskMajor.getId());
			cpf.setCreate_time(new Date());
			cpf.setUpdate_time(new Date());
			cpf.setProcess_status(EvalConstants.PROCESS_STATUS_NOT_INPUT);
			dao.insertCapitalProgressForm(cpf);

			PerformanceForm pf2 = new PerformanceForm();
			pf2.setCollection_major_id(taskMajor.getId());
			pf2.setM_system_id(this.template_capital_progress_form_id);
			pf2.setProcess_status(EvalConstants.PROCESS_STATUS_NOT_INPUT);
			pf2.setCreate_time(new Date());
			pf2.setUpdate_time(new Date());
			pf2.setMetrics_id(this.metrics_central_disbursement_amount_id);
			dao.insertPerformanceForm(pf2);
			this.insertMaterials(pf2.getId(), this.template_capital_progress_form_id,
					this.metrics_central_disbursement_amount_id);
			pf2.setMetrics_id(this.metrics_central_paid_hardware_amount_id);
			dao.insertPerformanceForm(pf2);
			this.insertMaterials(pf2.getId(), this.template_capital_progress_form_id,
					this.metrics_central_paid_hardware_amount_id);
			pf2.setMetrics_id(this.metrics_central_paid_internal_amount_id);
			dao.insertPerformanceForm(pf2);
			this.insertMaterials(pf2.getId(), this.template_capital_progress_form_id,
					this.metrics_central_paid_internal_amount_id);
			pf2.setMetrics_id(this.metrics_region_disbursement_amount_id);
			dao.insertPerformanceForm(pf2);
			this.insertMaterials(pf2.getId(), this.template_capital_progress_form_id,
					this.metrics_region_disbursement_amount_id);
			pf2.setMetrics_id(this.metrics_region_paid_hardware_amount_id);
			dao.insertPerformanceForm(pf2);
			this.insertMaterials(pf2.getId(), this.template_capital_progress_form_id,
					this.metrics_region_paid_hardware_amount_id);
			pf2.setMetrics_id(this.metrics_region_paid_internal_amount_id);
			dao.insertPerformanceForm(pf2);
			this.insertMaterials(pf2.getId(), this.template_capital_progress_form_id,
					this.metrics_region_paid_internal_amount_id);
			pf2.setMetrics_id(this.metrics_school_funding_total_id);
			dao.insertPerformanceForm(pf2);
			this.insertMaterials(pf2.getId(), this.template_capital_progress_form_id,
					this.metrics_school_funding_total_id);
			pf2.setMetrics_id(this.metrics_school_funding_hardware_id);
			dao.insertPerformanceForm(pf2);
			this.insertMaterials(pf2.getId(), this.template_capital_progress_form_id,
					this.metrics_school_funding_hardware_id);
			pf2.setMetrics_id(this.metrics_school_funding_internal_id);
			dao.insertPerformanceForm(pf2);
			this.insertMaterials(pf2.getId(), this.template_capital_progress_form_id,
					this.metrics_school_funding_internal_id);
		}

		result = 1;
		return result;
	}

	public ArrayList setBasicForm(int collection_major_id, Properties prop) {
		// TODO Auto-generated method stub
		//return dao.getBasicFormItemId(collection_major_id);
		this.use_properties(prop);
		ArrayList result = new ArrayList();
		
		ArrayList al = dao.getBasicFormItemId(collection_major_id, this.METRICS_MAJOR_BASIC_ID);
		BasicFormInfo info = new BasicFormInfo();
		int pf_id;
		info.setItem_name("basic_info");
		pf_id = ((Integer)al.get(0)).intValue();
		info.setPerformance_id(pf_id);
		String materials_num = "要求1项,已提交" + dao.countUploadedMaterial(pf_id) + "项";
		info.setMaterials_num(materials_num);
		result.add(info);
		
		al = dao.getBasicFormItemId(collection_major_id, this.METRICS_SELF_EVAL_ID);
		info = new BasicFormInfo();
		info.setItem_name("self_eval");
		pf_id = ((Integer)al.get(0)).intValue();
		info.setPerformance_id(pf_id);
		materials_num = "要求1项,已提交" + dao.countUploadedMaterial(pf_id) + "项";
		info.setMaterials_num(materials_num);
		result.add(info);
		
		return result;
	}

	public int updatePerformanceStatus(char process_status, int performance_id) {
		// TODO Auto-generated method stub
		ArrayList al = dao.selectPerformanceItem(performance_id);
		    
		return dao.updatePerformanceFormStatus(process_status, ((PerformanceForm)al.get(0)).getCollection_major_id());
	}

	public ArrayList getCapitalProgess(int performance_form_id) {
		// TODO Auto-generated method stub
		return dao.getCapitalProgress(performance_form_id);
	}

	public int updateCapitalProgressForm(CapitalProgressForm cpf) {
		// TODO Auto-generated method stub
		return dao.updateCapitalProgressForm(cpf);
	}

	public List<ProcessStatus> selectStatusList() {
		// TODO Auto-generated method stub
		return dao.selectStatusList();
	}
}
