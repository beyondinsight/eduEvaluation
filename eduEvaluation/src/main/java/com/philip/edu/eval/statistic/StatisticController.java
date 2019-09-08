package com.philip.edu.eval.statistic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.auth0.jwt.interfaces.Claim;
import com.philip.edu.eval.collection.ColTaskController;
import com.philip.edu.eval.collection.ColTaskService;
import com.philip.edu.eval.user_role.UserService;
import com.philip.edu.eval.users.UsersService;
import com.philip.edu.eval.util.EvalConstants;
import com.philip.edu.eval.util.SecurityUtil;


@RestController
@EnableWebMvc
@RequestMapping(value = "/statistic")
public class StatisticController {

	private static final Logger logger = Logger.getLogger(StatisticController.class);

	@Autowired
	private StatisticService statisticService;

	@RequestMapping(value = "/countSchool", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity countSchool(HttpServletRequest request) {

		Map mData = new HashMap();
		mData.put("school_no", statisticService.countSchool());

		return new ResponseEntity(mData, HttpStatus.OK);
	}

	@RequestMapping(value = "/countMajor", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity countMajor(HttpServletRequest request) {

		Map mData = new HashMap();
		mData.put("major_no", statisticService.countMajor());

		return new ResponseEntity(mData, HttpStatus.OK);
	}
}
