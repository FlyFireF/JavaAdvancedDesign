package com.flyfiref.dsscm.controller;

import com.flyfiref.dsscm.pojo.Role;
import com.flyfiref.dsscm.pojo.User;
import com.flyfiref.dsscm.service.RoleService;
import com.flyfiref.dsscm.tools.Constants;
import com.alibaba.fastjson.JSONArray;
import com.mysql.cj.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/sys/role")
public class RoleController {

	private Logger logger = LoggerFactory.getLogger(RoleController.class);

	@Autowired
	private RoleService roleService;
	@RequestMapping(value = "/error.html")
	public String roleError(Model model) {
		model.addAttribute("msg","您无权访问！");
		return "roleError";
	}
	@RequestMapping(value = "/list.html")
	public String getRoleList(Model model) {
		List<Role> roleList = null;
		try {
			roleList = roleService.getRoleList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("roleList", roleList);
		return "rolelist";
	}

	@RequestMapping(value = "/add.html", method = RequestMethod.GET)
	public String addRole(@ModelAttribute("role") Role role) {
		return "roleadd";
	}

	@RequestMapping(value = "/addsave.html", method = RequestMethod.POST)
	public String addRoleSave(Role role, HttpSession session) {
		role.setCreatedBy(((User) session.getAttribute(Constants.USER_SESSION))
				.getId());
		role.setCreationDate(new Date());
		try {
			if (roleService.add(role)) {
				return "redirect:/sys/role/list.html";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "roleadd";
	}

	@RequestMapping(value = "/modify/{id}", method = RequestMethod.GET)
	public String getUserById(@PathVariable("id") String id, Model model,
			HttpServletRequest request) {
		Role role = new Role();
		try {
			role = roleService.getRoleById(Integer.parseInt(id));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute(role);
		return "rolemodify";
	}

	@RequestMapping(value = "/modifysave.html", method = RequestMethod.POST)
	public String modifyUserSave(Role role, HttpSession session) {
		role.setModifyBy(((User) session.getAttribute(Constants.USER_SESSION))
				.getId());
		role.setModifyDate(new Date());
		try {
			if (roleService.modify(role)) {
				return "redirect:/sys/role/list.html";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "rolemodify";
	}

	@RequestMapping(value = "/delrole.json", method = RequestMethod.GET)
	@ResponseBody
	public Object deluser(@RequestParam("id") String id) {
		HashMap<String, String> resultMap = new HashMap<String, String>();
		if (StringUtils.isNullOrEmpty(id)) {
			resultMap.put("delResult", "notexist");
		} else {
			try {
				if (roleService.deleteRoleById(Integer.parseInt(id)))
					resultMap.put("delResult", "true");
				else
					resultMap.put("delResult", "false");
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return JSONArray.toJSONString(resultMap);
	}

	@RequestMapping(value = "/rcexist.json")
	@ResponseBody
	public Object roleCodeIsExit(@RequestParam("roleCode") String roleCode) {
		HashMap<String, String> resultMap = new HashMap<String, String>();
		if (StringUtils.isNullOrEmpty(roleCode)) {
			resultMap.put("roleCode", "exist");
		} else {
			int count = 0;
			try {
				count = roleService.roleCodeIsExist(roleCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (count == 0)
				resultMap.put("roleCode", "noexist");
			else
				resultMap.put("roleCode", "exist");
		}
		return JSONArray.toJSONString(resultMap);
	}

}
