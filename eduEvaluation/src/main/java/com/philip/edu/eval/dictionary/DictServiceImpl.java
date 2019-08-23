package com.philip.edu.eval.dictionary;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.philip.edu.eval.bean.Major;
import com.philip.edu.eval.bean.School;
import com.philip.edu.eval.mapper.DictMapper;

@org.springframework.stereotype.Service("dict_service")
public class DictServiceImpl implements DictService {
	
	@Autowired
	private DictMapper dao; 

	public School getSchoolById(int school_id) {
		// TODO Auto-generated method stub
		return null;
	}

	public int createSchool(School school) {
		// TODO Auto-generated method stub
		return dao.createSchool(school);
	}

	public int updateSchool(School school) {
		// TODO Auto-generated method stub
		return dao.updateSchool(school);
	}

	public int deleteSchool(int school_id) {
		// TODO Auto-generated method stub
		return dao.deleteSchool(school_id);
	}

	public List<School> getSchoolList() {
		// TODO Auto-generated method stub
		return this.dao.getSchoolList();
	}
	
	public int batchDeleteSchool(int ids[]){
		return dao.batchDeleteSchool(ids);
	}
	
	public List<String> searchCity(String search){
		return dao.searchCity(search);
	}

	public Major getMajorById(int major_id) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean createMajor(Major major) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean updateMajor(Major major) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteMajor(Major major) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Major> getMajorList() {
		// TODO Auto-generated method stub
		return null;
	}

}
