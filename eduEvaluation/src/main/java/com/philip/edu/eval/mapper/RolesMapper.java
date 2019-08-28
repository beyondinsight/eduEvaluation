package com.philip.edu.eval.mapper;

import java.util.List;

import com.philip.edu.eval.bean.TblRoles;
import com.philip.edu.eval.bean.TblUserRole;



public interface RolesMapper {
   
	//tbl_roles
	List<TblRoles> getRolenameRoles();
    int deleteByPrimaryKey(Integer id);
    int updateByPrimaryKeySelective(TblRoles record);
    int updateByPrimaryKey(TblRoles record);  
    int selectRolename(TblRoles record);  
    public int createRoles(TblRoles roles);
   	public int updateRoles(TblRoles roles);
   	public int deleteRoles(int id);
   	
    
    
   	//tbl_userrole
    List<TblUserRole> getRolenameUserrole();
    int deleteByPrimaryKey(Long id);
    int insert(TblUserRole record);
    int insertSelective(TblUserRole record);
    TblUserRole selectByPrimaryKey(Long id);
    int updateByPrimaryKeySelective(TblUserRole record);
    int updateByPrimaryKey(TblUserRole record);    
    public int createuserrole(TblUserRole users);
	public int updateuserrole(TblUserRole users);
	public int deleteuserrole(int id);    

}