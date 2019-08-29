package com.philip.edu.eval.schooluser;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.philip.edu.eval.bean.TblRoles;
import com.philip.edu.eval.bean.TblSchoolMajor;
import com.philip.edu.eval.bean.TblSchoolUser;
import com.philip.edu.eval.mapper.RolesMapper;
import com.philip.edu.eval.mapper.SchoolUserMapper;


@org.springframework.stereotype.Service("school_user_service")
public  class SchoolUserImpl implements SchoolUserService {
	
	@Autowired
	private SchoolUserMapper dao; 

	

	public List<TblSchoolUser> getNameSchoolUser() {
		// TODO Auto-generated method stub
		return this.dao.getNameSchoolUser();
	}

 
	public int createSchoolUser(TblSchoolUser users) {
		// TODO Auto-generated method stub
		return dao.createSchoolUser(users);
	}

 
	public int updateSchoolUser(TblSchoolUser users) {
		// TODO Auto-generated method stub
		return dao.updateSchoolUser(users);
	}


	public int deleteSchoolUser(int id) {
		// TODO Auto-generated method stub
		return dao.deleteSchoolUser(id);
	}



	
}