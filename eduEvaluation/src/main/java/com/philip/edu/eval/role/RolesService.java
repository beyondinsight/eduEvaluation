package com.philip.edu.eval.role;


import java.util.ArrayList;
import java.util.List;


import com.philip.edu.eval.bean.TblRoles;
import com.philip.edu.eval.bean.TblUsers;



public interface RolesService {
	
    public List<TblRoles> getRolenameRoles();
    public int createRoles(TblRoles roles);
	public int updateRoles(TblRoles roles);
	public int deleteRoles(int id);






	
}
