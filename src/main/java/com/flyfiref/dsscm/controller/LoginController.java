package com.flyfiref.dsscm.controller;

import com.flyfiref.dsscm.pojo.User;
import com.flyfiref.dsscm.service.UserService;
import com.flyfiref.dsscm.tools.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	private Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login.html")
	public String login() {
		logger.debug("LoginController welcome 百货中心供应链管理系统==================");
		return "login";
	}

	@RequestMapping(value = "/dologin.html", method = RequestMethod.POST)
	public String doLogin(@RequestParam("userCode") String userCode,
			@RequestParam("userPassword") String userPassword, HttpServletRequest request,
			HttpSession session) throws Exception {
		logger.debug("doLogin====================================");
		// 调用service方法，进行用户匹配
		User user = userService.login(userCode, userPassword);
		if (null != user) {// 登录成功
			// 放入session
			session.setAttribute(Constants.USER_SESSION, user);
			// 页面跳转（frame.jsp）
			return "redirect:/sys/main.html";
		} else {
			// 页面跳转（login.jsp）带出提示信息--转发
			request.setAttribute("error", "用户名或密码不正确");
			return "login";
		}
	}

	@RequestMapping(value = "/logout.html")
	public String logout(HttpSession session) {
		// 清除session
		session.removeAttribute(Constants.USER_SESSION);
		return "login";
	}

	@RequestMapping(value = "/sys/main.html")
	public String main() {
		return "index";
	}

	@RequestMapping(value = "/syserror.html")
	public String sysError() {
		return "syserror";
	}
}
