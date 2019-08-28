package com.philip.edu.eval.collection;

import java.util.List;

import com.philip.edu.eval.bean.ColTaskMajor;
import com.philip.edu.eval.bean.ColTaskSchool;
import com.philip.edu.eval.bean.CollectionTask;
import com.philip.edu.eval.bean.Major;
import com.philip.edu.eval.bean.School;
import com.philip.edu.eval.bean.TblMajor;

public interface ColTaskService {
	//Task:
	public List<ColTaskSchool> getTaskSchoolList(int task_id);
	public int createColTask(CollectionTask task, List<ColTaskSchool> schools, List<ColTaskMajor> majors);
	public List<CollectionTask> getColTaskList();
	public int countTaskSchool(int task_id);
	public int countTaskMajor(int task_id);
	public int updateStatus(int task_id, char status);
	public int batchDeleteTasks(int[] ids);
}
