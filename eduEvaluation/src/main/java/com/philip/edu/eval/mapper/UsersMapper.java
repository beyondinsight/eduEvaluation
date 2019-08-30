package com.philip.edu.eval.mapper;

import java.util.List;

import com.philip.edu.eval.bean.TblUsers;

public interface UsersMapper {
	
		//Users:
		public List<TblUsers> getUsersList();
		public List<TblUsers> getUsers(String  userName);
		public int createUsers(TblUsers users);
		public int updateUsers(TblUsers users);
		public int deleteUsers(int id);
		public int batchDeleteUsers(int ids[]);
		public int createUsersList(List<TblUsers> Users);
		
		//User Role
		public int createUserRole(TblUsers users);
		public int updateUserRole(TblUsers users);
		public List<TblUsers> getRolesUsers(int id);
}