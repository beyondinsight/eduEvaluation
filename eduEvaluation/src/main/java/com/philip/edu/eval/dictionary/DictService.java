package com.philip.edu.eval.dictionary;

import java.util.List;

import com.philip.edu.eval.bean.Major;
import com.philip.edu.eval.bean.School;

public interface DictService {
	//School:
	public School getSchoolById(int school_id);
	public int createSchool(School school);
	public int updateSchool(School school);
	public boolean deleteSchool(int school_id);
	public List<School> getSchoolList();
	
	//Major:
	public Major getMajorById(int major_id);
	public boolean createMajor(Major major);
	public boolean updateMajor(Major major);
	public boolean deleteMajor(Major major);
	public List<Major> getMajorList();
	
}
