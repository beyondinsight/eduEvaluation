package com.philip.edu.eval.users;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.philip.edu.eval.bean.TblUsers;

public interface UsersService {
	//Users:
	public List<TblUsers> getUsersList();
	public int createUsers(TblUsers users);
	public int updateUsers(TblUsers users);
	public int deleteUsers(int id);
	public int batchDeleteUsers(int ids[]);
	public boolean exsitsUser(String username);
	public boolean checkPassword(@Param("username")String username, @Param("password")String password);
}
