package com.philip.edu.eval.usertask;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.philip.edu.eval.bean.MajorCollectionStatus;
import com.philip.edu.eval.bean.MajorStatus;
import com.philip.edu.eval.bean.SchoolTask;
import com.philip.edu.eval.bean.UserTask;
import com.philip.edu.eval.mapper.ColMapper;

@org.springframework.stereotype.Service
public class UserTaskServiceImpl implements UserTaskService {

	@Autowired
	private ColMapper collectionDAO;

	public List<UserTask> getUserTaskList(int user_id) {
		// TODO Auto-generated method stub

		return collectionDAO.getUserTaskList(user_id);
	}

	public List<UserTask> getUserTaskByTaskID(int user_id, int task_id) {
		// TODO Auto-generated method stub

		return collectionDAO.getUserTaskByTaskID(user_id, task_id);
	}

	public List<MajorStatus> getMajorStatusByTask(int user_id, int task_id) {
		return collectionDAO.getMajorStatusByTask(user_id, task_id);
	}

	public List<MajorCollectionStatus> getMajorCollectionStatus(int task_id, int school_id, int major_id) {
		return collectionDAO.getMajorCollectionStatus(task_id, school_id, major_id);
	}

	public List<SchoolTask> getSchoolTaskList(int user_id) {
		// TODO Auto-generated method stub
		return collectionDAO.selectSchoolTaskList(user_id);
	}

}
