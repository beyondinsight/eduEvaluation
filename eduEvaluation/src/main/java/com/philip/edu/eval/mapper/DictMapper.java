package com.philip.edu.eval.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.philip.edu.eval.bean.Major;
import com.philip.edu.eval.bean.School;
import com.philip.edu.eval.bean.TblMajor;

@Repository
public interface DictMapper {
	
	//School:
	public List<School> getSchoolList();
	public int createSchool(School school);
	public int updateSchool(School school);
	public int deleteSchool(int school_id);
	public int batchDeleteSchool(int ids[]);
	public List<String> searchCity(@Param(value="search")String search);
	
	
	//Major:
	public Major getMajorById(int major_id);
	public int createMajor(TblMajor major);
	public int updateMajor(TblMajor major);
	public int deleteMajor(int id);
	public int batchDeleteMajor(int ids[]);
	public List<TblMajor> getMajorList();
}
