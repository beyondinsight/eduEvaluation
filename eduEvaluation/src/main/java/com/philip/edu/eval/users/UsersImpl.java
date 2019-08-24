package com.philip.edu.eval.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.philip.edu.eval.bean.TblUsers;
import com.philip.edu.eval.mapper.UsersMapper;

@org.springframework.stereotype.Service("users_service")
public class UsersImpl implements UsersService {
	
	@Autowired
	private UsersMapper dao; 

	public TblUsers getUsersById(int school_id) {
		// TODO Auto-generated method stub
		return null;
	}

	public int createUsers(TblUsers school) {
		// TODO Auto-generated method stub
		return dao.createUsers(school);
	}

	public int updateUsers(TblUsers school) {
		// TODO Auto-generated method stub
		return dao.updateUsers(school);
	}

	public int deleteUsers(int school_id) {
		// TODO Auto-generated method stub
		return dao.deleteUsers(school_id);
	}

	public List<TblUsers> getUsersList() {
		// TODO Auto-generated method stub
		return this.dao.getUsersList();
	}
	
	public int batchDeleteUsers(int ids[]){
		return dao.batchDeleteUsers(ids);
	}
	
}
