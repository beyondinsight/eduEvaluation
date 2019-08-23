package com.philip.edu.eval.dictionary;

import java.util.List;

import com.philip.edu.eval.bean.Major;
import com.philip.edu.eval.bean.School;

public interface DictService {
	//School:
	public School getSchoolById(int school_id);
	public int createSchool(School school);
	public int updateSchool(School school);
	public int deleteSchool(int school_id);
	public List<School> getSchoolList();
	public int batchDeleteSchool(int ids[]);
	public List<String> searchCity(String search);
	
	//Major:
	public Major getMajorById(int major_id);
	public boolean createMajor(Major major);
	public boolean updateMajor(Major major);
	public boolean deleteMajor(Major major);
	public List<Major> getMajorList();
	
}
