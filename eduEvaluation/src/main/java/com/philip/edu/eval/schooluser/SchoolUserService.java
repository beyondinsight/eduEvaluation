package com.philip.edu.eval.schooluser;


import java.util.ArrayList;
import java.util.List;


import com.philip.edu.eval.bean.TblRoles;
import com.philip.edu.eval.bean.TblSchoolMajor;
import com.philip.edu.eval.bean.TblSchoolUser;



public interface SchoolUserService {
	
    public List<TblSchoolUser> getNameSchoolUser();

    public int createSchoolUser(TblSchoolUser tsu);
	public int updateSchoolUser(TblSchoolUser tsu);
	public int deleteSchoolUser(int id);






	
}
