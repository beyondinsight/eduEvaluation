package com.philip.edu.eval.mapper;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.philip.edu.eval.bean.CollectionTask;

@Repository
public interface StatisticMapper {
	public int countSchool();
	
	public int countMajor();
} 
