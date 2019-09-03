package com.philip.edu.eval.usertask;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.philip.edu.eval.bean.MajorCollectionStatus;
import com.philip.edu.eval.bean.MajorStatus;
import com.philip.edu.eval.bean.UserTask;

public interface UserTaskService {

	public List<UserTask> getUserTaskList(int user_id);
	
	public List<UserTask> getUserTaskByTaskID(int user_id, int task_id);
	
	public List<MajorStatus> getMajorStatusByTask(int user_id, int task_id);
	
	public List<MajorCollectionStatus> getMajorCollectionStatus(int task_id, int school_id,  int major_id);
	
	
	
}