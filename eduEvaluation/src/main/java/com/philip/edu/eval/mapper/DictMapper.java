package com.philip.edu.eval.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.philip.edu.eval.bean.School;

@Repository
public interface DictMapper {
	public List<School> getSchoolList();
	public int createSchool(School school);
	public int updateSchool(School school);
}
