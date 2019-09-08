package com.philip.edu.eval.mapper;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.philip.edu.eval.bean.BasicForm;
import com.philip.edu.eval.bean.CapitalProgressForm;
import com.philip.edu.eval.bean.ColTaskMajor;
import com.philip.edu.eval.bean.ColTaskSchool;
import com.philip.edu.eval.bean.CollectionTask;
import com.philip.edu.eval.bean.Major;
import com.philip.edu.eval.bean.MajorCollectionStatus;
import com.philip.edu.eval.bean.MajorStatus;
import com.philip.edu.eval.bean.MajorTask;
import com.philip.edu.eval.bean.Material;
import com.philip.edu.eval.bean.MetricsDetail;
import com.philip.edu.eval.bean.PerformanceForm;
import com.philip.edu.eval.bean.ProcessStatus;
import com.philip.edu.eval.bean.School;
import com.philip.edu.eval.bean.SchoolTask;
import com.philip.edu.eval.bean.TblMajor;
import com.philip.edu.eval.bean.UserTask;

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
	public List<PerformanceForm> getPerformanceForm(@Param("collection_major_id")int collection_major_id, @Param("for_template")int for_template);
	public List<Material> getRelateMaterials(@Param("pf_id")int pf_id, @Param("metrics_id")int metrics_id);
	public int insertPerformanceForm(PerformanceForm pf);
	public int insertRelateMaterial(Material material);
	public List<Material> getMaterialMetrics(int pf_id);
	public List<CapitalProgressForm> selectCapitalProgress(@Param("collection_major_id")int collection_major_id);
	public int countUploadedMaterial(int form_performance_id);
	public int countRequiredMaterial(int form_performance_id);  
	public ArrayList getCapitalProgressItemId(@Param("collection_major_id")int collection_major_id,@Param("metrics_id")int metrics_id);
	public ArrayList getBasicFormItemId(@Param("collection_major_id")int collection_major_id,@Param("metrics_id")int metrics_id);
	public int updatePerformanceForm(PerformanceForm pf);
	public List<ColTaskSchool> getChosenSchools(int task_id);
	public List<ColTaskMajor> getChosenMajors(int collection_school_id);
	public int deleteTaskSchool(int task_id);
	public int insertTaskSchool(ColTaskSchool colTaskSchool);
	public int deleteTaskSchools(int[] school_ids);
	public int deleteTaskMajors(int collection_school_id);
	public int updatePerformanceFormStatus(@Param("process_status")char process_status, @Param("collection_major_id")int collection_major_id);
	public ArrayList selectPerformanceItem(int form_performance_id);
	public ArrayList getCapitalProgress(int form_performance_id);
	public int updateCapitalProgressForm(CapitalProgressForm cpf);
	public List<ProcessStatus> selectStatusList();
	public List<SchoolTask> selectSchoolTaskList(int user_id);
	public List<MajorTask> searchTaskList(@Param("task_id")int task_id, @Param("school_id")int school_id,@Param("major_id")int major_id,@Param("process_status")char process_status);
	public int updateBasicForm(BasicForm bf);
	public int deleteMaterial(int id);
	public int updateCapitalFormStatus(CapitalProgressForm cpf);
	public int updateColTask(CollectionTask task);
	public int updateTaskStatus(@Param("id")int id, @Param("process_status")char process_status, @Param("memo")String memo);
	public int[] getCollectionIdByPerformance(int performance_id);
	public List<SchoolTask> selectAllTaskList();
	 
	//add by xiewei
	public List<UserTask> getUserTaskList(@Param("user_id") int user_id);
	
	public List<UserTask> getUserTaskByTaskID(@Param("user_id") int user_id, @Param("task_id") int task_id);
	
	public List<MajorStatus> getMajorStatusByTask(@Param("user_id") int user_id, @Param("task_id") int task_id);
	
	public List<MajorCollectionStatus> getMajorCollectionStatus(@Param("task_id") int task_id, @Param("school_id") int school_id, @Param("major_id") int major_id);
	
	public int updateMajorStatus( @Param("collection_school_id") int collection_school_id, @Param("major_id") int major_id);
} 
