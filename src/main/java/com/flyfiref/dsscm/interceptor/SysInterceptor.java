package com.flyfiref.dsscm.interceptor;

import com.flyfiref.dsscm.pojo.User;
import com.flyfiref.dsscm.tools.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SysInterceptor implements HandlerInterceptor {
	private Logger logger = LoggerFactory.getLogger(SysInterceptor.class);

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		logger.debug("SysInterceptor preHandle ==========================");
		HttpSession session = request.getSession();

		User user = (User) session.getAttribute(Constants.USER_SESSION);

		if (null == user) {
			response.sendRedirect(request.getContextPath() + "/login.html");
			return false;
		}
		return true;
	}
}
