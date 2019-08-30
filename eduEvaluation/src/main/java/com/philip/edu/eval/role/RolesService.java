package com.philip.edu.eval.role;


import java.util.ArrayList;
import java.util.List;


import com.philip.edu.eval.bean.TblRoles;
import com.philip.edu.eval.bean.TblUserRole;
import com.philip.edu.eval.bean.TblUsers;



public interface RolesService {
	
    public List<TblRoles> getRolenameRoles();
    public List<TblRoles> getRolesUsersCount();
    public List<TblRoles> getUploadRolesUsersCount(int id);
    public int createRoles(TblRoles roles);
	public int updateRoles(TblRoles roles);
	public int deleteRoles(int id);


	
	//tbl_userrole
    List<TblUserRole> getRolenameUserrole();
    int saveChosenUser(TblUserRole users);
    int updateChosenUser(TblUserRole users);
    int deleteChosenUser(int[] userId);
    /*int createuserrole(TblUserRole users);
	int updateuserrole(TblUserRole users);
	int deleteuserrole(int id);
	*/

	
}
