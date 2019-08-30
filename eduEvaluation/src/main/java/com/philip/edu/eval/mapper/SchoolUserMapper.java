package com.philip.edu.eval.mapper;


import java.util.List;

import com.philip.edu.eval.bean.TblRoles;
import com.philip.edu.eval.bean.TblSchoolMajor;
import com.philip.edu.eval.bean.TblSchoolUser;

public interface SchoolUserMapper {

	//school major user
	public int createMajorUser(TblSchoolMajor tsu);
	public int updateMajorUser(TblSchoolMajor tsu);
	public int deleteMajorUser(int id);  
	public List<TblSchoolMajor> getRolenameSchoolMajor();
    
	//school user
    public List<TblSchoolUser> getNameSchoolUser();
    public int createSchoolUser(TblSchoolUser tsu);
	public int updateSchoolUser(TblSchoolUser tsu);
	public int deleteSchoolUser(int id);

}