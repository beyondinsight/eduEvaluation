package com.philip.edu.eval.dictionary;

import java.util.ArrayList;
import java.util.List;

import com.philip.edu.eval.bean.Major;
import com.philip.edu.eval.bean.School;
import com.philip.edu.eval.bean.TblMajor;

public interface DictService {
	//School:
	public List<School> getSchoolById(int school_id);
	public int createSchool(School school);
	public int updateSchool(School school);
	public int deleteSchool(int school_id);
	public List<School> getSchoolList();
	public int batchDeleteSchool(int ids[]);
	public List<String> getCity();
	public List<String> getType();
	

	//Major:
	public Major getMajorById(int major_id);
	public int createMajor(TblMajor major);
	public int updateMajor(TblMajor major);
	public int deleteMajor(int school_id);
	public int batchDeleteMajor(int ids[]);
	public List<TblMajor> getMajorList();
	
}
