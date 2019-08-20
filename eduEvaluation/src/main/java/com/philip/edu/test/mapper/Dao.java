package com.philip.edu.test.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.philip.edu.test.bean.User;

@Repository
public interface Dao {
	String getPwByUserNm(@Param("name")String name);
	
	//void insertUser(User user);
}
