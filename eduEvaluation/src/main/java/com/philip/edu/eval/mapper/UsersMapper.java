package com.philip.edu.eval.mapper;

import java.util.List;

import com.philip.edu.eval.bean.TblUsers;

public interface UsersMapper {
	
		//Users:
		public List<TblUsers> getUsersList();
		public int createUsers(TblUsers users);
		public int updateUsers(TblUsers users);
		public int deleteUsers(int id);
		public int batchDeleteUsers(int ids[]);
		public int exsitsUser(String username);
		public List<TblUsers> selectUser(String username);
    
}