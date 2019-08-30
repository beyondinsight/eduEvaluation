package com.philip.edu.eval.mapper;

import java.util.List;

import com.philip.edu.eval.bean.TblRoles;
import com.philip.edu.eval.bean.TblUserRole;
import com.philip.edu.eval.bean.TblUsers;



public interface RolesMapper {
   
	//tbl_roles
	List<TblRoles> getRolenameRoles();
	List<TblRoles> getRolesUsersCount();
	List<TblRoles> getUploadRolesUsersCount(int id);
    int deleteByPrimaryKey(int id);  
    int createRoles(TblRoles roles);
   	int updateRoles(TblRoles roles);
   	int deleteRoles(int id);
   	
    
    
   	//tbl_userrole
   	int saveChosenUser(TblUserRole users);
    int updateChosenUser(TblUserRole users);
    int deleteChosenUser(int[] userId);

}