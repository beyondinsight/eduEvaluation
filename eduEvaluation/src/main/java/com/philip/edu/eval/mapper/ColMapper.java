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
}
