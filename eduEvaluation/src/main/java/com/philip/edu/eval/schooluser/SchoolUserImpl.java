package com.philip.edu.eval.schooluser;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.philip.edu.eval.bean.TblRoles;
import com.philip.edu.eval.bean.TblSchoolMajor;
import com.philip.edu.eval.bean.TblSchoolUser;
import com.philip.edu.eval.bean.TblUsers;
import com.philip.edu.eval.mapper.RolesMapper;
import com.philip.edu.eval.mapper.SchoolUserMapper;


@org.springframework.stereotype.Service("school_user_service")
public  class SchoolUserImpl implements SchoolUserService {
	
	@Autowired
	private SchoolUserMapper dao; 

	

	public List<TblSchoolUser> getNameSchoolUser(int roleId) {
		// TODO Auto-generated method stub
		return this.dao.getNameSchoolUser(roleId);
	}

 
	public int createRoleUser(TblSchoolUser users) {
		// TODO Auto-generated method stub
		return dao.createRoleUser(users);
	}

 
	public int updateRoleUser(TblSchoolUser users) {
		// TODO Auto-generated method stub
		return dao.updateRoleUser(users);
	}


	public int deleteSchoolUser(int id) {
		// TODO Auto-generated method stub
		return dao.deleteSchoolUser(id);
	}


	public List<TblSchoolMajor> getRolenameSchoolMajor(int roleId) {
		// TODO Auto-generated method stub
		return dao.getRolenameSchoolMajor(roleId);
	}


	public int createMajorUser(TblSchoolMajor tsu) {
		// TODO Auto-generated method stub
		return dao.createMajorUser(tsu);
	}


	public int updateMajorUser(TblSchoolMajor tsu) {
		// TODO Auto-generated method stub
		return dao.updateMajorUser(tsu);
	}


	public int deleteMajorUser(int id) {
		// TODO Auto-generated method stub
		return dao.deleteMajorUser(id);
	}
	
	public TblSchoolUser getSchoolRolesUsers(TblSchoolUser users) {
		// TODO Auto-generated method stub
		return dao.getSchoolRolesUsers(users);
	}


	public TblSchoolMajor getMajorRolesUsers(TblSchoolMajor tsm) {
		// TODO Auto-generated method stub
		return dao.getMajorRolesUsers(tsm);
	}


	
}