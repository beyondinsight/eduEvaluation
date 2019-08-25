package com.philip.edu.eval.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.philip.edu.eval.bean.ColTaskSchool;
import com.philip.edu.eval.bean.Major;
import com.philip.edu.eval.bean.School;
import com.philip.edu.eval.bean.TblMajor;

@Repository
public interface ColMapper {
	
	//Task:
	public List<ColTaskSchool> getTaskSchoolList();
}
