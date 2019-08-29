package com.philip.edu.eval.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.philip.edu.eval.bean.BasicForm;
import com.philip.edu.eval.bean.CapitalProgressForm;
import com.philip.edu.eval.bean.ColTaskMajor;
import com.philip.edu.eval.bean.ColTaskSchool;
import com.philip.edu.eval.bean.CollectionTask;
import com.philip.edu.eval.bean.Major;
import com.philip.edu.eval.bean.Material;
import com.philip.edu.eval.bean.MetricsDetail;
import com.philip.edu.eval.bean.School;
import com.philip.edu.eval.bean.TblMajor;

@Repository
public interface ColMapper {
	
	//Task:
	public List<ColTaskSchool> getTaskSchoolList();
	public int insertColTask(CollectionTask task);
	public int insertColSchool(@Param("taskSchools")List<ColTaskSchool> taskSchools);
	public int[] getColTaskSchoolId(@Param("task_id")int task_id, @Param("school_id")int school_id);
	public int insertTaskMajors(@Param("taskMajors")List<ColTaskMajor> taskMajors);
	public int insertTaskMajor(ColTaskMajor major);
	public int deleteTaskOldSchools(@Param("collection_school_ids")int[] collection_school_ids);
	public int insertBasicForm(BasicForm bf);
	public int insertCapitalProgressForm(CapitalProgressForm cpf);
	public List<CollectionTask> getColTaskList();
	public int countTaskSchool(int task_id);
	public int countTaskMajor(int task_id);
	public int updateStatus(@Param("task_id")int task_id, @Param("status")char status);
	public int batchDeleteTasks(int[] ids);
	public int insertMetrics(MetricsDetail metrics);
	public int insertMaterials(List<Material> materials);
	public List<MetricsDetail> selectMetricsList(int metrics_system);
	public int countMaterials(int metrics_id);
	public int updateMetrics(MetricsDetail metrics);
	public int deleteMetrics(int metrics_id);
}
