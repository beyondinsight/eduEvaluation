package com.philip.edu.eval.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.auth0.jwt.interfaces.Claim;
import com.philip.edu.eval.bean.BackendData;
import com.philip.edu.eval.collection.ColTaskController;
import com.philip.edu.eval.util.SecurityUtil;

@Component
public class PermissionCheckInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger logger = Logger.getLogger(PermissionCheckInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object hander) throws Exception {
		// get token:
		String token = request.getParameter("token");

		if (token == null || "".equals(token)) {
			logger.info("进行了token的校验。");
			
			return false;
		}

		// check:
		Map<String, Claim> claims = SecurityUtil.verifyToken(token);
		Claim user_name_claim = claims.get("username");

		if (null == user_name_claim || StringUtils.isEmpty(user_name_claim.asString())) {
			
			return false;
		}

		return true;
	}
}
