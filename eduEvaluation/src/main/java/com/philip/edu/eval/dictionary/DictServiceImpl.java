package com.philip.edu.eval.dictionary;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.philip.edu.eval.bean.ChosenMajor;
import com.philip.edu.eval.bean.Major;
import com.philip.edu.eval.bean.School;
import com.philip.edu.eval.bean.TblMajor;
import com.philip.edu.eval.mapper.DictMapper;

@org.springframework.stereotype.Service("dict_service")
public class DictServiceImpl implements DictService {
	
	@Autowired
	private DictMapper dao; 

	public List<School> getSchoolById(int school_id) {
		// TODO Auto-generated method stub
		return dao.getSchoolById(school_id);
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
	
	public List<String> getCity(){
		return dao.getCity();
	}
	
	public List<String> getType(){
		return dao.getType();
	}

	public Major getMajorById(int major_id) {
		// TODO Auto-generated method stub
		return null;
	}

	public int createMajor(TblMajor major) {
		// TODO Auto-generated method stub
		return dao.createMajor(major);
	}

	public int updateMajor(TblMajor major) {
		// TODO Auto-generated method stub
		return dao.updateMajor(major);
	}

	public int deleteMajor(int id) {
		// TODO Auto-generated method stub
		return dao.deleteMajor(id);
	}

	public List<TblMajor> getMajorList() {
		// TODO Auto-generated method stub
		return dao.getMajorList();
	}

	public int batchDeleteMajor(int[] ids) {
		// TODO Auto-generated method stub
		return dao.batchDeleteMajor(ids);
	}

	public List<Integer> getChosenMajor(int school_id) {
		// TODO Auto-generated method stub
		return dao.getChosenMajor(school_id);
	}

	@Transactional("txManager")
	public int saveChosenMajor(int school_id, int[] majors) {
		// TODO Auto-generated method stub
		int n = 0;
		dao.deleteChosenMajor(school_id);
		if(majors!=null){
			n = dao.saveChosenMajor(school_id, majors);
		}
		return n;
	}

	public List<ChosenMajor> getChosenMajorInfo(int school_id) {
		// TODO Auto-generated method stub
		return dao.getChosenMajorInfo(school_id);
	}
 

}
