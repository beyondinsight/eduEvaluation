package com.philip.edu.eval.role;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.philip.edu.eval.bean.TblRoles;
import com.philip.edu.eval.bean.TblUserRole;
import com.philip.edu.eval.bean.TblUsers;
import com.philip.edu.eval.mapper.RolesMapper;


@org.springframework.stereotype.Service("roles_service")
public  class RolesImpl implements RolesService {
	
	@Autowired
	private RolesMapper dao; 

	

	public List<TblRoles> getRolenameRoles() {
		// TODO Auto-generated method stub
		return this.dao.getRolenameRoles();
	}





	public int createRoles(TblRoles roles) {
		// TODO Auto-generated method stub
		return dao.createRoles(roles);
	}



	public int updateRoles(TblRoles roles) {
		// TODO Auto-generated method stub
		return dao.updateRoles(roles);
	}



	public int deleteRoles(int id) {
		// TODO Auto-generated method stub
		return dao.deleteRoles(id);
	}





	public List<TblRoles> getRolesUsersCount() {
		// TODO Auto-generated method stub
		return dao.getRolesUsersCount();
	}





	public List<TblUserRole> getRolenameUserrole() {
		// TODO Auto-generated method stub
		return null;
	}

	public int saveChosenUser(TblUserRole users) {
		// TODO Auto-generated method stub
		return dao.saveChosenUser(users);
	}

	public int updateChosenUser(TblUserRole users) {
		// TODO Auto-generated method stub
		return dao.updateChosenUser(users);
	}





	public int deleteChosenUser(int[] userId) {
		// TODO Auto-generated method stub
		return dao.deleteChosenUser(userId);
	}





	



	
}