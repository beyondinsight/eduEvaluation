package com.philip.edu.eval.collection;

import java.util.List;

import com.philip.edu.eval.bean.ColTaskSchool;
import com.philip.edu.eval.bean.Major;
import com.philip.edu.eval.bean.School;
import com.philip.edu.eval.bean.TblMajor;

public interface ColTaskService {
	//Task:
	public List<ColTaskSchool> getTaskSchoolList(int task_id);
}
