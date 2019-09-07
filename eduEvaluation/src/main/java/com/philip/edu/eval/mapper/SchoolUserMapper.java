package com.philip.edu.eval.mapper;


import java.util.List;

import com.philip.edu.eval.bean.TblRoles;
import com.philip.edu.eval.bean.TblSchoolMajor;
import com.philip.edu.eval.bean.TblSchoolUser;
import com.philip.edu.eval.bean.TblUsers;

public interface SchoolUserMapper {

	//school major user
	public int createMajorUser(TblSchoolMajor tsu);
	public int updateMajorUser(TblSchoolMajor tsu);
	public int deleteMajorUser(int id);  
	public List<TblSchoolMajor> getRolenameSchoolMajor(int roleId);
	public List<TblSchoolMajor> getRolenameSchoolMajorBySchool(TblSchoolMajor tsu);
	public TblSchoolMajor getMajorRolesUsers(TblSchoolMajor tsm);
	
	//school user
    public List<TblSchoolUser> getNameSchoolUser(int roleId);
    public int createRoleUser(TblSchoolUser tsu);
	public int updateRoleUser(TblSchoolUser tsu);
	public int deleteSchoolUser(int id);
	
	//School Role User
    public TblSchoolUser getSchoolRolesUsers(TblSchoolUser  tsu);

}