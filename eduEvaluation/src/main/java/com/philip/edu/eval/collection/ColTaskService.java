package com.philip.edu.eval.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.annotations.Param;

import com.philip.edu.eval.bean.BasicForm;
import com.philip.edu.eval.bean.CapitalProgressForm;
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

public interface ColTaskService {
	//Task:
	public List<ColTaskSchool> getTaskSchoolList(int task_id);
	public int createColTask(CollectionTask task, List<ColTaskSchool> schools, List<ColTaskMajor> majors, Properties prop);
	public List<CollectionTask> getColTaskList();
	public int countTaskSchool(int task_id);
	public int countTaskMajor(int task_id);
	public int updateStatus(int task_id, char status);
	public int batchDeleteTasks(int[] ids);
	public int createMetrics(MetricsDetail metrics, List<Material> materials);
	public List<MetricsDetail> getMetricsList(int metrics_system);
	public int countMaterials(int metrics_id);
	public int updateMetrics(MetricsDetail metrics); 
	public int deleteMetrics(int metrics_id); 
	public List<PerformanceForm> getPerformanceForm(int collection_major_id, int for_template);
	public List<Material> getRelateMaterials(int pf_id, int metrics_id);
	public List<CapitalProgressForm> selectCapitalProgress(int collection_major_id);
	public int selectCapitalProgressMaterialsNumAndPerformanceId(CapitalProgressForm cpf, Properties prop);
	public int selectPerformanceMaterialsNum(ArrayList performanceForm);
	public int updatePerformanceForm(PerformanceForm pf);
	public List<ColTaskSchool> getChosenSchool(int task_id);
	public List<ColTaskMajor> getChosenMajor(int collection_school_id);
	public int deleteTaskSchool(int task_id);
	public int insertTaskSchool(ColTaskSchool colTaskSchool);
	public int changeSchool(int task_id, int[] chose_id, Properties prop);
	public int changeMajor(int parseInt, int[] chose_id, Properties propConfig);
	public ArrayList setBasicForm(int collection_major_id, Properties prop);
	public int updatePerformanceStatus(char process_status, int collection_major_id);
	public ArrayList getCapitalProgess(int performance_form_id);
	public int updateCapitalProgressForm(CapitalProgressForm cpf);
	public List<ProcessStatus> selectStatusList();
	public int updateBasicForm(BasicForm bf);
	public List<MetricsDetail> getCapitalMetrics(Properties prop);
	public List<Material> getMaterials(int metrics_id);
	public int insertMaterials(List<Material> materials);
	public int deleteMaterial(int id);
	public int updatePerformanceFormStatus(char status, int collection_major_id);
	public int updateCapitalFormStatus(CapitalProgressForm cpf);
}
