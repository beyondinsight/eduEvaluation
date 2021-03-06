package com.philip.edu.eval.usertask;

import java.util.ArrayList;
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
import com.philip.edu.eval.bean.BackendData;
import com.philip.edu.eval.bean.CollectionTask;
import com.philip.edu.eval.bean.MajorCollectionStatus;
import com.philip.edu.eval.bean.MajorStatus;
import com.philip.edu.eval.bean.UserTask;
import com.philip.edu.eval.collection.ColTaskController;
import com.philip.edu.eval.collection.ColTaskService;
import com.philip.edu.eval.user_role.UserService;
import com.philip.edu.eval.users.UsersService;
import com.philip.edu.eval.util.EvalConstants;
import com.philip.edu.eval.util.PageUtil;
import com.philip.edu.eval.util.SecurityUtil;
import com.philip.edu.eval.bean.TblUsers;

@RestController
@EnableWebMvc
@RequestMapping(value = "/userTask")
public class UserTaskController {

	private static final Logger logger = Logger.getLogger(UserTaskController.class);

	@Autowired
	private UserTaskService userTaskService;
	@Autowired
	private UsersService usersService;

	@RequestMapping(value = "/userTaskList", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<BackendData> getUsetTaskList(HttpServletRequest request) {
		BackendData data = new BackendData();

		// get token:
		String token = request.getParameter("token");

		if (token == null || "".equals(token)) {
			data.setMsg("您的令牌已过期！");
			data.setCode(10);
			// BackendData data = new BackendData();

			return new ResponseEntity<BackendData>(data, HttpStatus.OK);
		}

		// check:
		Map<String, Claim> claims = SecurityUtil.verifyToken(token);
		Claim user_name_claim = claims.get("username");

		if (null == user_name_claim || StringUtils.isEmpty(user_name_claim.asString())) {
			data.setMsg("您的用户验证信息不正确！");
			data.setCode(20);
			// BackendData data = new BackendData();

			return new ResponseEntity<BackendData>(data, HttpStatus.OK);
		}

		// get user id:
		String username = user_name_claim.asString();
		int user_id = ((TblUsers) usersService.getUsers(username).get(0)).getId();

		ArrayList lUserTask = (ArrayList) userTaskService.getUserTaskList(user_id);

		logger.info("successfully get collection task list");

		data.setMsg("");
		data.setCode(0);
		data.setData(lUserTask);
		data.setCount(lUserTask.size());
		// BackendData data = new BackendData();

		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}

	@RequestMapping(value = "/schoolTaskList", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<BackendData> getSchoolTaskList(HttpServletRequest request) {
		BackendData data = new BackendData();

		// get token:
		String token = request.getParameter("token");

		if (token == null || "".equals(token)) {

			data.setMsg("您的令牌已过期！");
			data.setCode(10);
			// BackendData data = new BackendData();

			return new ResponseEntity<BackendData>(data, HttpStatus.OK);
		}

		// check:
		Map<String, Claim> claims = SecurityUtil.verifyToken(token);
		Claim user_name_claim = claims.get("username");

		if (null == user_name_claim || StringUtils.isEmpty(user_name_claim.asString())) {
			data.setMsg("您的用户验证信息不正确！");
			data.setCode(20);
			// BackendData data = new BackendData();

			return new ResponseEntity<BackendData>(data, HttpStatus.OK);
		}

		// get user id:
		String username = user_name_claim.asString();
		TblUsers user = usersService.getUsers(username).get(0);
		int user_id = user.getId();
		int role_id = user.getRoleId();
		ArrayList lUserTask = null;
		
		if(role_id == EvalConstants.ROLE_GOVERNMENT_MANAGEMENT){
			lUserTask = (ArrayList) userTaskService.getAllTaskList();
		} else if(role_id == EvalConstants.ROLE_SCHOOL_MANAGEMENT){
			lUserTask = (ArrayList) userTaskService.getSchoolTaskList(user_id);
		}
	
		logger.info("successfully get school task list");

		data.setMsg("");
		data.setCode(0);
		data.setData(lUserTask);
		data.setCount(lUserTask.size());
		// BackendData data = new BackendData();

		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}

	@RequestMapping(value = "/searchTaskList", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<BackendData> searchTaskList(HttpServletRequest request) {
		BackendData data = new BackendData();

		String sTask_id = request.getParameter("task_id");
		String sSchool_id = request.getParameter("school_id");
		String sMajor_id = request.getParameter("major_id");
		String sProcess_status = request.getParameter("process_status");
		
		PageUtil pu = new PageUtil();
		int page = 0;
		int limit = 0;
		page = Integer.parseInt(request.getParameter("page"));
		limit = Integer.parseInt(request.getParameter("limit"));
		
		
		int task_id = 0;
		int school_id = 0;
		int major_id = 0;
		char process_status;

		if (sTask_id != null && !"".equals(sTask_id))
			task_id = Integer.parseInt(sTask_id);
		if (sSchool_id != null && !"".equals(sSchool_id))
			school_id = Integer.parseInt(sSchool_id);
		if (sMajor_id != null && !"".equals(sMajor_id))
			major_id = Integer.parseInt(sMajor_id);
		if (sProcess_status != null && !"".equals(sProcess_status))
			process_status = sProcess_status.charAt(0);
		else
			process_status = '-';
 
		ArrayList taskList = (ArrayList) userTaskService.searchTaskList(task_id, school_id, major_id, process_status);

 		logger.info("successfully get major task list");   

		data.setMsg("已获取所有专业的任务情况");
		data.setCode(0);

		if(request.getParameter("page") == null || request.getParameter("limit") == null) {
 
			data.setData(taskList);
			data.setCount(taskList.size());
			
			return new ResponseEntity<BackendData>(data, HttpStatus.OK);
		}
		ArrayList pagelist = pu.batchList(taskList, page, limit);
		data.setData(pagelist);
		data.setCount(taskList.size());
		// BackendData data = new BackendData();

		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}

	@RequestMapping(value = "/userTaskByTaskID", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<BackendData> getUserTaskByTaskID(HttpServletRequest request) {

		String task_id = (String) request.getParameter("task_id");

		// get token:
		String token = request.getParameter("token");

		// check:
		Map<String, Claim> claims = SecurityUtil.verifyToken(token);
		Claim user_name_claim = claims.get("username");

		BackendData data = new BackendData();
		if (null == user_name_claim || StringUtils.isEmpty(user_name_claim.asString())) {
			data.setMsg("您的用户验证信息不正确！");
			data.setCode(10);
			// BackendData data = new BackendData();

			return new ResponseEntity<BackendData>(data, HttpStatus.OK);
		}

		// get user id:
		String username = user_name_claim.asString();
		int user_id = ((TblUsers) usersService.getUsers(username).get(0)).getId();

		ArrayList lUserTask = (ArrayList) userTaskService.getUserTaskByTaskID(user_id,
				(new Integer(task_id)).intValue());

		logger.info("successfully get collection task by task id: " + task_id);

		data.setMsg("");
		data.setCode(0);
		data.setData(lUserTask);
		data.setCount(lUserTask.size());
		// BackendData data = new BackendData();

		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}

	@RequestMapping(value = "/majorStatus", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<BackendData> getMajorStatusByTask(HttpServletRequest request) {

		String task_id = (String) request.getParameter("task_id");

		// get token:
		String token = request.getParameter("token");

		// check:
		Map<String, Claim> claims = SecurityUtil.verifyToken(token);
		Claim user_name_claim = claims.get("username");

		BackendData data = new BackendData();
		if (null == user_name_claim || StringUtils.isEmpty(user_name_claim.asString())) {
			data.setMsg("您的用户验证信息不正确！");
			data.setCode(10);
			// BackendData data = new BackendData();

			return new ResponseEntity<BackendData>(data, HttpStatus.OK);
		}

		// get user id:
		String username = user_name_claim.asString();
		int user_id = ((TblUsers) usersService.getUsers(username).get(0)).getId();

		ArrayList<MajorStatus> lMajorStatus = (ArrayList) userTaskService.getMajorStatusByTask(user_id,
				(new Integer(task_id)).intValue());

		logger.info("successfully get major status by task id: " + task_id);

		data.setMsg("");
		data.setCode(0);
		data.setData(lMajorStatus);
		data.setCount(lMajorStatus.size());
		// BackendData data = new BackendData();

		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}

	@RequestMapping(value = "/majorCollectionStatus", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<BackendData> getMajorCollectionStatus(HttpServletRequest request) {

		String task_id = (String) request.getParameter("task_id");
		String school_id = (String) request.getParameter("school_id");
		String major_id = (String) request.getParameter("major_id");

		ArrayList lMajorCollectionStatus = (ArrayList) userTaskService.getMajorCollectionStatus(
				(new Integer(task_id)).intValue(), (new Integer(school_id)).intValue(),
				(new Integer(major_id)).intValue());

		ArrayList tempList = new ArrayList();
		if (lMajorCollectionStatus != null && lMajorCollectionStatus.size() != 0) {
			MajorCollectionStatus performance = (MajorCollectionStatus) lMajorCollectionStatus.get(0);
			MajorCollectionStatus basic = (MajorCollectionStatus) lMajorCollectionStatus.get(1);
			tempList.add(basic);
			tempList.add(performance); 
			tempList.add(lMajorCollectionStatus.get(2));
		}

		logger.info("successfully get major status by task id: " + task_id);
 
		BackendData data = new BackendData();
		data.setMsg("");
		data.setCode(0);
		data.setData(tempList);
		data.setCount(tempList.size());
		// BackendData data = new BackendData();

		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}

	@RequestMapping(value = "/updateMajorStatus", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<BackendData> updateMajorStatus(HttpServletRequest request) {
		int collection_school_id = Integer.parseInt(request.getParameter("collection_school_id"));
		int major_id = Integer.parseInt(request.getParameter("major_id"));

		logger.info(
				"Try to update major status. collection_school_id " + collection_school_id + " major_id" + major_id);
		int returnCode = 1;
		String msg = "提交失败";

		if (userTaskService.updateMajorStatus(collection_school_id, major_id) == 1) {
			logger.info("successfully update major status. collection_school_id " + collection_school_id + " major_id"
					+ major_id);
			msg = "已成功提交";
			returnCode = 0;
		}

		BackendData data = new BackendData();
		data.setMsg(msg);
		data.setCode(returnCode);

		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}

}
