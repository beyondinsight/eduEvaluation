package com.philip.edu.test.service;

import javax.annotation.Resource;
import com.philip.edu.test.mapper.Dao;
import com.philip.edu.test.service.HelloService;

@org.springframework.stereotype.Service("service_test")
public class HelloServiceImpl implements HelloService {
	
	//@Resource
	//private Dao dao;
	
	public String getPwByUserNm(String name) {
		// TODO Auto-generated method stub
		return null;//this.dao.getPwByUserNm(name);
	}
	
}
