package com.philip.edu.eval.role;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.philip.edu.eval.bean.TblRoles;
import com.philip.edu.eval.mapper.RolesMapper;


@org.springframework.stereotype.Service("roles_service")
public  class RolesImpl implements RolesService {
	
	@Autowired
	private RolesMapper dao; 

	

	public List<TblRoles> getRolenameRoles() {
		// TODO Auto-generated method stub
		return this.dao.getRolenameRoles();
	}





	@Override
	public int createRoles(TblRoles roles) {
		// TODO Auto-generated method stub
		return dao.createRoles(roles);
	}



	@Override
	public int updateRoles(TblRoles roles) {
		// TODO Auto-generated method stub
		return dao.updateRoles(roles);
	}



	@Override
	public int deleteRoles(int id) {
		// TODO Auto-generated method stub
		return dao.deleteRoles(id);
	}



	
}