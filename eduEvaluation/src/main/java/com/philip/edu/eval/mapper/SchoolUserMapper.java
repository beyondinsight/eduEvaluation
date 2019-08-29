package com.philip.edu.eval.mapper;


import java.util.List;

import com.philip.edu.eval.bean.TblRoles;
import com.philip.edu.eval.bean.TblSchoolMajor;
import com.philip.edu.eval.bean.TblSchoolUser;

public interface SchoolUserMapper {

	public int createschoolmajor(TblSchoolMajor users);
	public int updateschoolmajor(TblSchoolMajor users);
	public int deleteschoolmajor(int id);   
    List<TblSchoolMajor> getRolenameSchoolMajor();
    
	
    public List<TblSchoolUser> getNameSchoolUser();
    public int createSchoolUser(TblSchoolUser tsu);
	public int updateSchoolUser(TblSchoolUser tsu);
	public int deleteSchoolUser(int id);

}