package com.flyfiref.dsscm.controller;

import com.flyfiref.dsscm.pojo.Role;
import com.flyfiref.dsscm.pojo.User;
import com.flyfiref.dsscm.service.RoleService;
import com.flyfiref.dsscm.service.UserService;
import com.flyfiref.dsscm.tools.Constants;
import com.alibaba.fastjson.JSONArray;
import com.flyfiref.dsscm.tools.RsaUtil;
import com.github.pagehelper.PageInfo;
import com.mysql.cj.util.StringUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.io.File;
import java.util.*;

@Controller
@RequestMapping("/sys/user")
public class UserController extends BaseController {
	private Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "/list.html")
	public String getUserList(
			Model model,
			@RequestParam(value = "queryname", required = false) String queryUserName,
			@RequestParam(value = "queryUserRole", required = false) Integer queryUserRole,
			@RequestParam(value = "pageIndex", required = false) Integer pageIndex,
			HttpSession session) {
		logger.info("getUserList ---- > queryUserName: " + queryUserName);
		logger.info("getUserList ---- > queryUserRole: " + queryUserRole);
		logger.info("getUserList ---- > pageIndex: " + pageIndex);
		PageInfo<User> upi = null;
		List<Role> roleList = null;
		// 设置页面容量
		int pageSize = Constants.pageSize;
		// 页码为空默认分第一页
		if (null == pageIndex) {
			pageIndex = 1;
		}
		if (queryUserName == null) {
			queryUserName = "";
		}
		try {
			upi = userService.getUserList(queryUserName, queryUserRole,
					pageIndex, pageSize);
			roleList = roleService.getRoleList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		model.addAttribute("pi", upi);
		model.addAttribute("roleList", roleList);
		model.addAttribute("queryUserName", queryUserName);
		model.addAttribute("queryUserRole", queryUserRole);
		return "userlist";
	}
//	@RequestMapping("/update.html")
//	@ResponseBody
//	public Map getupdateList(@RequestParam(value = "queryname", required = false) String queryUserName,
//							 @RequestParam(value = "queryUserRole", required = false) Integer queryUserRole,
//							 @RequestParam(value = "pageIndex", required = false) Integer pageIndex) {
//		logger.info("getUserList ---- > queryUserName: " + queryUserName);
//		logger.info("getUserList ---- > queryUserRole: " + queryUserRole);
//		logger.info("getUserList ---- > pageIndex: " + pageIndex);
//		PageInfo<User> upi = null;
//		List<Role> roleList = null;
//		// 设置页面容量
//		int pageSize = Constants.pageSize;
//		// 页码为空默认分第一页
//		if (null == pageIndex) {
//			pageIndex = 1;
//		}
//		if (queryUserName == null) {
//			queryUserName = "";
//		}
//		try {
//			upi = userService.getUserList(queryUserName, queryUserRole,
//					pageIndex, pageSize);
//			roleList = roleService.getRoleList();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		// 将用户列表转换成HTML格式的字符串返回给前端
//		String userListHtml = convertUserListToHtml(upi, roleList, queryUserName, queryUserRole);
//		Map<String,String> map = new HashMap<String,String>();
//		map.put("userListHtml", userListHtml);
//		return map;
//	}
//
//	// 将用户列表转换成HTML格式的方法
//	private String convertUserListToHtml(PageInfo<User> upi, List<Role> roleList, String queryUserName, Integer queryUserRole) {
//		StringBuilder htmlBuilder = new StringBuilder();
//
//		// 在这里添加HTML头部，例如表格的开始标签和表头
//		htmlBuilder.append("<table class='table table-striped'>");
//		htmlBuilder.append("<thead>");
//		htmlBuilder.append("<tr>");
//		htmlBuilder.append("<th>用户编码</th>");
//		htmlBuilder.append("<th>用户名</th>");
//		htmlBuilder.append("<th>性别</th>");
//		htmlBuilder.append("<th>年龄</th>");
//		htmlBuilder.append("<th>电话</th>");
//		htmlBuilder.append("<th>角色</th>");
//		htmlBuilder.append("</tr>");
//		htmlBuilder.append("</thead>");
//		htmlBuilder.append("<tbody>");
//
//		// 遍历用户列表数据，将每个用户的信息添加到HTML中
//		for (User user : upi.getList()) {
//			htmlBuilder.append("<tr>");
//			htmlBuilder.append("<td>").append(user.getUserCode()).append("</td>");
//			htmlBuilder.append("<td>").append(user.getUserName()).append("</td>");
//			htmlBuilder.append("<td>").append(setGender(user)).append("</td>");
//			htmlBuilder.append("<td>").append(user.getAge()).append("</td>");
//			htmlBuilder.append("<td>").append(user.getPhone()).append("</td>");
//			htmlBuilder.append("<td>").append(setUserRole(user)).append("</td>");
////			String roleName = getRoleName(user.getId(), roleList);
////			htmlBuilder.append("<td>").append(roleName).append("</td>");
//			htmlBuilder.append("</tr>");
//		}
//
//		// 在这里添加HTML尾部，例如表格的结束标签
//		htmlBuilder.append("</tbody>");
//		htmlBuilder.append("</table>");
//
//		return htmlBuilder.toString();
//	}
//
//
//	private String setGender(User user) {
//		if (user.getGender() == 2) {
//			return "男";
//		}
//		if (user.getGender() == 1) {
//			return "女";
//		}
//		return "未知性别";
//	}
//	private String setUserRole(User user) {
//		if (user.getUserRole() == 2) {
//			return "经理";
//		}
//		if (user.getUserRole() == 1) {
//			return "系统管理者";
//		}
//		if (user.getUserRole() == 3) {
//			return "普通员工";
//		}
//		if (user.getUserRole() == 4) {
//			return "人事部员工";
//		}
//		if (user.getUserRole() == 5) {
//			return "采购部员工";
//		}
//		if (user.getUserRole() == 6) {
//			return "物资部员工";
//		}
//		if (user.getUserRole() == 7) {
//			return "销售部员工";
//		}
//		return "未知角色";
//	}
	@RequestMapping(value = "/add.html", method = RequestMethod.GET)
	public String addUser(Model model,@ModelAttribute("user") User user) {
		List<Role> roleList = null;
		try {
			roleList = roleService.getRoleList();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		model.addAttribute("roleList", roleList);
		return "useradd";
	}

	// 文件上传
	@RequestMapping(value = "/addsave.html", method = RequestMethod.POST)
	public String addUserSave(
			User user,
			HttpSession session,
			HttpServletRequest request,
			@RequestParam(value = "attachs", required = false) MultipartFile[] attachs) throws Exception {
		System.out.println("--------------进入添加用户方法---------");
		String idPicPath = null;
		String workPicPath = null;
		String errorInfo = null;
		boolean flag = true;
		String path = request.getSession().getServletContext()
				.getRealPath("statics" + File.separator + "uploadfiles");
		logger.info("uploadFile path ============== > " + path);
		for (int i = 0; i < attachs.length; i++) {
			MultipartFile attach = attachs[i];
			if (!attach.isEmpty()) {
				if (i == 0) {
					errorInfo = "uploadFileError";
				} else if (i == 1) {
					errorInfo = "uploadWpError";
				}
				String oldFileName = attach.getOriginalFilename();// 原文件名
				logger.info("uploadFile oldFileName ============== > "
						+ oldFileName);
				String prefix = FilenameUtils.getExtension(oldFileName);// 原文件后缀
				logger.debug("uploadFile prefix============> " + prefix);
				int filesize = 500000;
				logger.debug("uploadFile size============> " + attach.getSize());
				if (attach.getSize() > filesize) {// 上传大小不得超过 500k
					request.setAttribute(errorInfo, " * 上传大小不得超过 500k");
					flag = false;
				} else if (prefix.equalsIgnoreCase("jpg")
						|| prefix.equalsIgnoreCase("png")
						|| prefix.equalsIgnoreCase("jpeg")
						|| prefix.equalsIgnoreCase("pneg")) {// 上传图片格式不正确
					String fileName = System.currentTimeMillis()
							+ RandomUtils.nextInt(1000000) + "_Personal.jpg";
					logger.debug("new fileName======== " + attach.getName());
					File targetFile = new File(path, fileName);
					if (!targetFile.exists()) {
						targetFile.mkdirs();
					}
					// 保存
					try {
						attach.transferTo(targetFile);
					} catch (Exception e) {
						e.printStackTrace();
						request.setAttribute(errorInfo, " * 上传失败！");
						flag = false;
					}
					if (i == 0) {
						idPicPath = path + File.separator + fileName;
					} else if (i == 1) {
						workPicPath = path + File.separator + fileName;
					}
					logger.debug("idPicPath: " + idPicPath);
					logger.debug("workPicPath: " + workPicPath);

				} else {
					request.setAttribute(errorInfo, " * 上传图片格式不正确");
					flag = false;
				}
			}
		}
		if (flag) {
			user.setCreatedBy(((User) session
					.getAttribute(Constants.USER_SESSION)).getId());
			user.setUserPassword(RsaUtil.encryptByPublicKey(user.getUserPassword(),Constants.PUB_KEY));
			user.setCreationDate(new Date());
			user.setImgPath(idPicPath);
			try {
				if (userService.add(user)) {
					return "redirect:/sys/user/list.html";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "useradd";
	}

	@RequestMapping(value = "/modify/{id}", method = RequestMethod.GET)
	public String getUserById(@PathVariable("id") String id, Model model,
			HttpServletRequest request) {
		User user = new User();
		try {
			user = userService.getUserById(Integer.parseInt(id));
			if (user.getImgPath() != null && !"".equals(user.getImgPath())) {
				String[] paths = user.getImgPath().split("\\" + File.separator);
				logger.debug("view picPath paths[paths.length-1]============ "
						+ paths[paths.length - 1]);
				user.setImgPath(request.getContextPath()
						+ "/statics/uploadfiles/" + paths[paths.length - 1]);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute(user);
		return "usermodify";
	}

	@RequestMapping(value = "/modifysave.html", method = RequestMethod.POST)
	public String modifyUserSave(
			User user,
			HttpSession session,
			HttpServletRequest request,
			@RequestParam(value = "attachs", required = false) MultipartFile[] attachs) throws Exception {
		logger.debug("modifyUserSave id===================== " + user.getId());
		String idPicPath = null;
		String errorInfo = null;
		boolean flag = true;
		String path = request.getSession().getServletContext()
				.getRealPath("statics" + File.separator + "uploadfiles");
		logger.info("uploadFile path ============== > " + path);
		if (attachs != null) {
			for (int i = 0; i < attachs.length; i++) {
				MultipartFile attach = attachs[i];
				if (!attach.isEmpty()) {
					if (i == 0) {
						errorInfo = "uploadFileError";
					} else if (i == 1) {
						errorInfo = "uploadWpError";
					}
					String oldFileName = attach.getOriginalFilename();// 原文件名
					logger.info("uploadFile oldFileName ============== > "
							+ oldFileName);
					String prefix = FilenameUtils.getExtension(oldFileName);// 原文件后缀
					logger.debug("uploadFile prefix============> " + prefix);
					int filesize = 500000;
					logger.debug("uploadFile size============> "
							+ attach.getSize());
					if (attach.getSize() > filesize) {// 上传大小不得超过 500k
						request.setAttribute(errorInfo, " * 上传大小不得超过 500k");
						flag = false;
					} else if (prefix.equalsIgnoreCase("jpg")
							|| prefix.equalsIgnoreCase("png")
							|| prefix.equalsIgnoreCase("jpeg")
							|| prefix.equalsIgnoreCase("pneg")) {// 上传图片格式不正确
						String fileName = System.currentTimeMillis()
								+ RandomUtils.nextInt(1000000)
								+ "_Personal.jpg";
						logger.debug("new fileName======== " + attach.getName());
						File targetFile = new File(path, fileName);
						if (!targetFile.exists()) {
							targetFile.mkdirs();
						}
						// 保存
						try {
							attach.transferTo(targetFile);
						} catch (Exception e) {
							e.printStackTrace();
							request.setAttribute(errorInfo, " * 上传失败！");
							flag = false;
						}
						if (i == 0) {
							// zhr: 和product modify修改逻辑相同
							idPicPath = fileName;
						}
						logger.debug("idPicPath: " + idPicPath);

					} else {
						request.setAttribute(errorInfo, " * 上传图片格式不正确");
						flag = false;
					}
				}
			}
		}
		if (flag) {
			user.setModifyBy(((User) session
					.getAttribute(Constants.USER_SESSION)).getId());
			user.setModifyDate(new Date());
			user.setImgPath(idPicPath);
			try {
				if (userService.modify(user)) {
					return "redirect:/sys/user/list.html";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "usermodify";
	}

	@RequestMapping(value = "/ucexist.json")
	@ResponseBody
	public Object userCodeIsExit(@RequestParam("userCode") String userCode) {
		logger.debug("userCodeIsExit userCode===================== " + userCode);
		HashMap<String, String> resultMap = new HashMap<String, String>();
		if (StringUtils.isNullOrEmpty(userCode)) {
			resultMap.put("userCode", "exist");
		} else {
			User user = null;
			try {
				user = userService.selectUserCodeExist(userCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (null != user)
				resultMap.put("userCode", "exist");
			else
				resultMap.put("userCode", "noexist");
		}
		return JSONArray.toJSONString(resultMap);
	}

	@RequestMapping(value = "/rolelist.json", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public List<Role> getRoleList() {
		List<Role> roleList = null;
		try {
			roleList = roleService.getRoleList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.debug("roleList size: " + roleList.size());
		return roleList;
	}

	@RequestMapping(value = "/deluser.json", method = RequestMethod.GET)
	@ResponseBody
	public Object deluser(@RequestParam("id") String id) {
		HashMap<String, String> resultMap = new HashMap<String, String>();
		if (StringUtils.isNullOrEmpty(id)) {
			resultMap.put("delResult", "notexist");
		} else {
			try {
				if (userService.deleteUserById(Integer.parseInt(id)))
					resultMap.put("delResult", "true");
				else
					resultMap.put("delResult", "false");
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return JSONArray.toJSONString(resultMap);
	}

	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") String id, Model model,
			HttpServletRequest request) {
		logger.debug("view id===================== " + id);
		User currentUser=(User)request.getSession().getAttribute(Constants.USER_SESSION);
		User user = new User();
		try {
			user = userService.getUserById(Integer.parseInt(id));
			if(currentUser.getUserRole()==1|| Objects.equals(currentUser.getUserRole(), user.getUserRole())){
				if (user.getImgPath() != null && !"".equals(user.getImgPath())) {
					String[] paths = user.getImgPath().split("\\" + File.separator);
					logger.debug("view picPath paths[paths.length-1]============ "
							+ paths[paths.length - 1]);
					user.setImgPath(request.getContextPath()
							+ "/statics/uploadfiles/" + paths[paths.length - 1]);
				}
			}else{
				return "roleError";
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute(user);
		return "userview";
	}

	@RequestMapping(value = "/pwdmodify.html", method = RequestMethod.GET)
	public String pwdModify(HttpSession session) {
		if (session.getAttribute(Constants.USER_SESSION) == null) {
			return "redirect:/user/login.html";
		}
		return "pwdmodify";
	}

	@RequestMapping(value = "/pwdmodify.json", method = RequestMethod.POST)
	@ResponseBody
	public Object getPwdByUserId(@RequestParam("oldpassword") String oldpassword,
			HttpSession session) throws Exception {
		logger.debug("getPwdByUserId oldpassword ===================== "
				+ oldpassword);
		HashMap<String, String> resultMap = new HashMap<String, String>();
		if (null == session.getAttribute(Constants.USER_SESSION)) {// session过期
			resultMap.put("result", "sessionerror");
		} else if (StringUtils.isNullOrEmpty(oldpassword)) {// 旧密码输入为空
			resultMap.put("result", "error");
		} else {
			String sessionPwd = ((User) session
					.getAttribute(Constants.USER_SESSION)).getUserPassword();
			if (oldpassword.equals(RsaUtil.decryptByPrivateKey(sessionPwd,Constants.PRI_KEY))) {
				resultMap.put("result", "true");
			} else {// 旧密码输入不正确
				resultMap.put("result", "false");
			}
		}
		return JSONArray.toJSONString(resultMap);
	}

	@RequestMapping(value = "/pwdsave.html")
	public String pwdSave(
			@RequestParam(value = "newpassword") String newPassword,
			HttpSession session, HttpServletRequest request) {
		boolean flag = false;
		Object o = session.getAttribute(Constants.USER_SESSION);
		if (o != null && !StringUtils.isNullOrEmpty(newPassword)) {
			try {
				flag = userService.updatePwd(((User) o).getId(), RsaUtil.encryptByPublicKey(newPassword,Constants.PUB_KEY));
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (flag) {
				request.setAttribute(Constants.SYS_MESSAGE,"修改密码成功,请退出并使用新密码重新登录！");
				session.removeAttribute(Constants.USER_SESSION);// session注销
				return "login";
			} else {
				request.setAttribute(Constants.SYS_MESSAGE, "修改密码失败！");
			}
		} else {
			request.setAttribute(Constants.SYS_MESSAGE, "修改密码失败！");
		}
		return "pwdmodify";
	}

}
