package com.philip.edu.eval.statistic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.philip.edu.eval.mapper.StatisticMapper;

@org.springframework.stereotype.Service
public class StatisticServiceImpl implements StatisticService {

	@Autowired
	private StatisticMapper statisticDAO;

	public int countSchool() {
		// TODO Auto-generated method stub
		return statisticDAO.countSchool();
	}

	public int countMajor() {
		// TODO Auto-generated method stub
		return statisticDAO.countMajor();
	}

	
}
