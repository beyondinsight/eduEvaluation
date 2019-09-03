package com.philip.edu.eval.users;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.philip.edu.eval.bean.TblUsers;

public interface UsersService {
	//Users:  
	public List<TblUsers> getUsersList();
	public List<TblUsers> getUsers(String userName);
	public int createUsers(TblUsers users);
	public int updateUsers(TblUsers users);
	public int deleteUsers(int id);
	public int batchDeleteUsers(int ids[]);
	public int createUsersList(List<TblUsers> usersList);
	public boolean exsitsUser(String username);
	public boolean checkPassword(@Param("username")String username, @Param("password")String password);
	
	//User Role	
	public int createUserRole(TblUsers users);
	public int updateUserRole(TblUsers users);
    public List<TblUsers> getRolesUsers(int id);
    
    //User School   
    public int createUserSchool(TblUsers users);
    public int updateUserSchool(TblUsers users);
    
 
}
