package com.philip.edu.eval.statistic;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.philip.edu.eval.bean.MajorCollectionStatus;
import com.philip.edu.eval.bean.MajorStatus;
import com.philip.edu.eval.bean.MajorTask;
import com.philip.edu.eval.bean.SchoolTask;
import com.philip.edu.eval.bean.UserTask;

public interface StatisticService {

	public int countSchool();

	public int countMajor();
}
