package com.philip.edu.eval.collection;

import java.util.List;
import java.util.Properties;

import com.philip.edu.eval.bean.CapitalProgressForm;
import com.philip.edu.eval.bean.ColTaskMajor;
import com.philip.edu.eval.bean.ColTaskSchool;
import com.philip.edu.eval.bean.CollectionTask;
import com.philip.edu.eval.bean.Major;
import com.philip.edu.eval.bean.Material;
import com.philip.edu.eval.bean.MetricsDetail;
import com.philip.edu.eval.bean.PerformanceForm;
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
	public List<PerformanceForm> getPerformanceForm(int collection_major_id);
	public List<Material> getRelateMaterials(int pf_id, int metrics_id);
	public List<CapitalProgressForm> selectCapitalProgress(int collection_major_id);
	public int selectCapitalProgressMaterialsNum(CapitalProgressForm cpf, Properties prop);
	public int updatePerformanceForm(PerformanceForm pf);
}
