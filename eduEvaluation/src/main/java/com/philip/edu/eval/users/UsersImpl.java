package com.philip.edu.eval.users;

import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import com.philip.edu.eval.bean.TblUsers;
import com.philip.edu.eval.mapper.UsersMapper;
import com.philip.edu.eval.util.Code;
//import com.philip.edu.eval.util.PasswordUtil;
import com.philip.edu.eval.util.PropertiesUtil;

@org.springframework.stereotype.Service("users_service")
public class UsersImpl implements UsersService {
	
	@Autowired
	private UsersMapper dao; 
	private Properties propConfig = PropertiesUtil.getProperty("config");

	public TblUsers getUsersById(int school_id) {
		// TODO Auto-generated method stub
		return null;
	}

	public int createUsers(TblUsers user) {
		// TODO Auto-generated method stub
		return dao.createUsers(user);
	}

	public int updateUsers(TblUsers user) {
		// TODO Auto-generated method stub
		return dao.updateUsers(user);
	}

	public int deleteUsers(int id) {
		// TODO Auto-generated method stub
		return dao.deleteUsers(id);
	}

	public List<TblUsers> getUsersList() {
		// TODO Auto-generated method stub
		return this.dao.getUsersList();
	}
	
	public int batchDeleteUsers(int ids[]){
		return dao.batchDeleteUsers(ids);
	}

	public int createUsersList(List<TblUsers> usersList) {
		// TODO Auto-generated method stub
		return dao.createUsersList(usersList);
	}

	public List<TblUsers> getUsers(String userName) {
		// TODO Auto-generated method stub
		return dao.getUsers(userName);
	}

	public int updateUserRole(TblUsers users) {
		// TODO Auto-generated method stub
		return dao.updateUserRole(users);
	}

	public int createUserRole(TblUsers users) {
		// TODO Auto-generated method stub
		return dao.createUserRole(users);
	}
	
	public List<TblUsers> getRolesUsers(int id) {
		// TODO Auto-generated method stub
		return dao.getRolesUsers(id);
	}
	
}
