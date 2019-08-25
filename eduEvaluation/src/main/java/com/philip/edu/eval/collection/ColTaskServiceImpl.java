package com.philip.edu.eval.collection;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.philip.edu.eval.bean.ColTaskSchool;
import com.philip.edu.eval.bean.Major;
import com.philip.edu.eval.bean.School;
import com.philip.edu.eval.bean.TblMajor;
import com.philip.edu.eval.mapper.ColMapper;
import com.philip.edu.eval.mapper.DictMapper;

@org.springframework.stereotype.Service("colTask_service")
public class ColTaskServiceImpl implements ColTaskService {
	
	@Autowired
	private ColMapper dao;

	public List<ColTaskSchool> getTaskSchoolList(int task_id) {
		// TODO Auto-generated method stub
		return dao.getTaskSchoolList();
	} 

}
