package com.flyfiref.dsscm.controller;

import com.flyfiref.dsscm.pojo.Provider;
import com.flyfiref.dsscm.pojo.User;
import com.flyfiref.dsscm.service.ProviderService;
import com.flyfiref.dsscm.tools.Constants;
import com.alibaba.fastjson.JSONArray;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sys/provider")
public class ProviderController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(ProviderController.class);

	@Autowired
	private ProviderService providerService;

	@RequestMapping(value = "/list.html")
	public String getProviderList(
			Model model,
			@RequestParam(value = "queryProCode", required = false) String queryProCode,
			@RequestParam(value = "queryProName", required = false) String queryProName,
			@RequestParam(value = "pageIndex", required = false) String pageIndex) {
		logger.info("getProviderList ---- > queryProCode: " + queryProCode);
		logger.info("getProviderList ---- > queryProName: " + queryProName);
		logger.info("getProviderList ---- > pageIndex: " + pageIndex);
		PageInfo<Provider> pi = null;
		// 设置页面容量
		int pageSize = Constants.pageSize;
		// 当前页码
		int currentPageNo = 1;

		if (queryProCode == null) {
			queryProCode = "";
		}
		if (queryProName == null) {
			queryProName = "";
		}
		if (pageIndex != null) {
			try {
				currentPageNo = Integer.valueOf(pageIndex);
			} catch (NumberFormatException e) {
				return "redirect:/sys/provider/syserror.html";
			}
		}
		try {
			pi = providerService.getProviderList(queryProName,queryProCode,currentPageNo,pageSize);
			for (Provider p : pi.getList()) {
				System.out.println("---p ----"+p);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("pi", pi);
		model.addAttribute("queryProCode", queryProCode);
		model.addAttribute("queryProName", queryProName);
		model.addAttribute("currentPageNo", currentPageNo);
		return "providerlist";
	}
	@RequestMapping("/update.html")
	@ResponseBody
	public Object getupdateList(@RequestParam(value = "queryProCode", required = false) String queryProCode,
								@RequestParam(value = "queryProName", required = false) String queryProName,
								@RequestParam(value = "pageIndex", required = false) String pageIndex) {
		logger.info("getProviderList ---- > queryProCode: " + queryProCode);
		logger.info("getProviderList ---- > queryProName: " + queryProName);
		logger.info("getProviderList ---- > pageIndex: " + pageIndex);
		PageInfo<Provider> pi = null;
		List<Provider> providerList = null;
		// 设置页面容量
		int pageSize = Constants.pageSize;
		// 当前页码
		int currentPageNo = 1;

		if (queryProCode == null) {
			queryProCode = "";
		}
		if (queryProName == null) {
			queryProName = "";
		}
		if (pageIndex != null) {
			try {
				currentPageNo = Integer.valueOf(pageIndex);
			} catch (NumberFormatException e) {
				return "redirect:/sys/provider/syserror.html";
			}
		}
		try {
			pi = providerService.getProviderList(queryProName,queryProCode,currentPageNo,pageSize);
			providerList = providerService.getProviderList();
			for (Provider p : pi.getList()) {
				System.out.println("---p ----"+p);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 将用户列表转换成HTML格式的字符串返回给前端
		String providerListHtml = convertProviderListToHtml(pi, providerList,queryProCode, queryProName);
		Map<String,String> map = new HashMap<String,String>();
		map.put("providerListHtml", providerListHtml);
		return map;
	}

	// 将用户列表转换成HTML格式的方法
	private String convertProviderListToHtml(PageInfo<Provider> pi, List<Provider> providerList, String queryProCode, String queryProName) {
		StringBuilder htmlBuilder = new StringBuilder();

		// 在这里添加HTML头部，例如表格的开始标签和表头
		htmlBuilder.append("<table class='table table-striped'>");
		htmlBuilder.append("<thead>");
		htmlBuilder.append("<tr>");
		htmlBuilder.append("<th>供应商编码</th>");
		htmlBuilder.append("<th>供应商名称</th>");
		htmlBuilder.append("<th>联系人</th>");
		htmlBuilder.append("<th>联系电话</th>");
		htmlBuilder.append("<th>传真</th>");
		htmlBuilder.append("<th>创建时间</th>");
		htmlBuilder.append("</tr>");
		htmlBuilder.append("</thead>");
		htmlBuilder.append("<tbody>");

		// 遍历用户列表数据，将每个用户的信息添加到HTML中
		for (Provider provider : pi.getList()) {
			htmlBuilder.append("<tr>");
			htmlBuilder.append("<td>").append(provider.getProCode()).append("</td>");
			htmlBuilder.append("<td>").append(provider.getProName()).append("</td>");
			htmlBuilder.append("<td>").append(provider.getProContact()).append("</td>");
			htmlBuilder.append("<td>").append(provider.getProPhone()).append("</td>");
			htmlBuilder.append("<td>").append(provider.getProFax()).append("</td>");
			htmlBuilder.append("<td>").append(provider.getCreationDate()).append("</td>");
			htmlBuilder.append("</tr>");
		}

		// 在这里添加HTML尾部，例如表格的结束标签
		htmlBuilder.append("</tbody>");
		htmlBuilder.append("</table>");

		return htmlBuilder.toString();
	}

	@RequestMapping(value = "/syserror.html")
	public String sysError() {
		return "syserror";
	}

	@RequestMapping(value = "/add.html", method = RequestMethod.GET)
	public String add(@ModelAttribute("provider") Provider provider) {
		return "provideradd";
	}

	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") String id, Model model,
			HttpServletRequest request) {
		logger.debug("view id===================== " + id);
		Provider provider = new Provider();
		try {
			provider = providerService.getProviderById(Integer.parseInt(id));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (provider.getCompanyLicPicPath() != null
				&& !"".equals(provider.getCompanyLicPicPath())) {
			String[] paths = provider.getCompanyLicPicPath().split(
					"\\" + File.separator);
			logger.debug("view companyLicPicPath paths[paths.length-1]============ "
					+ paths[paths.length - 1]);
			provider.setCompanyLicPicPath(request.getContextPath()
					+ "/statics/uploadfiles/" + paths[paths.length - 1]);
		}
		if (provider.getOrgCodePicPath() != null
				&& !"".equals(provider.getOrgCodePicPath())) {
			String[] paths = provider.getOrgCodePicPath().split(
					"\\" + File.separator);
			logger.debug("view orgCodePicPath paths[paths.length-1]============ "
					+ paths[paths.length - 1]);
			provider.setOrgCodePicPath(request.getContextPath()
					+ "/statics/uploadfiles/" + paths[paths.length - 1]);
		}

		model.addAttribute(provider);
		return "providerview";
	}

	@RequestMapping(value = "/modify/{id}", method = RequestMethod.GET)
	public String getProviderById(@PathVariable("id") String id, Model model,
			HttpServletRequest request) {
		logger.debug("getProviderById id===================== " + id);
		Provider provider = new Provider();
		try {
			provider = providerService.getProviderById(Integer.parseInt(id));
			if (provider.getCompanyLicPicPath() != null
					&& !"".equals(provider.getCompanyLicPicPath())) {
				String[] paths = provider.getCompanyLicPicPath().split(
						"\\" + File.separator);
				logger.debug("view companyLicPicPath paths[paths.length-1]============ "
						+ paths[paths.length - 1]);
				provider.setCompanyLicPicPath(request.getContextPath()
						+ "/statics/uploadfiles/" + paths[paths.length - 1]);
			}
			if (provider.getOrgCodePicPath() != null
					&& !"".equals(provider.getOrgCodePicPath())) {
				String[] paths = provider.getOrgCodePicPath().split(
						"\\" + File.separator);
				logger.debug("view orgCodePicPath paths[paths.length-1]============ "
						+ paths[paths.length - 1]);
				provider.setOrgCodePicPath(request.getContextPath()
						+ "/statics/uploadfiles/" + paths[paths.length - 1]);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute(provider);
		return "providermodify";
	}

	@RequestMapping(value = "/modifysave.html", method = RequestMethod.POST)
	public String modifyProviderSave(
			Provider provider,
			HttpSession session,
			HttpServletRequest request,
			@RequestParam(value = "attachs", required = false) MultipartFile[] attachs) {
		logger.debug("modifyProviderSave id===================== "
				+ provider.getId());
		String companyLicPicPath = null;
		String orgCodePicPath = null;
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
						errorInfo = "uploadOcError";
					}
					String oldFileName = attach.getOriginalFilename();// 原文件名
					String prefix = FilenameUtils.getExtension(oldFileName);// 原文件后缀
					int filesize = 500000;
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
						// zhr: 和product modify修改逻辑相同
						if (i == 0) {
							companyLicPicPath = fileName;
						} else if (i == 1) {
							orgCodePicPath = fileName;
						}
						logger.debug("companyLicPicPath: " + companyLicPicPath);
						logger.debug("orgCodePicPath: " + orgCodePicPath);

					} else {
						request.setAttribute(errorInfo, " * 上传图片格式不正确");
						flag = false;
					}
				}
			}
		}
		if (flag) {
			provider.setModifyBy(((User) session
					.getAttribute(Constants.USER_SESSION)).getId());
			provider.setModifyDate(new Date());
			provider.setCompanyLicPicPath(companyLicPicPath);
			provider.setOrgCodePicPath(orgCodePicPath);
			try {
				if (providerService.modify(provider)) {
					return "redirect:/sys/provider/list.html";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "providermodify";
	}

	@RequestMapping(value = "/addsave.html", method = RequestMethod.POST)
	public String addProviderSave(
			Provider provider,
			HttpSession session,
			HttpServletRequest request,
			@RequestParam(value = "attachs", required = false) MultipartFile[] attachs) {
		String companyLicPicPath = null;
		String orgCodePicPath = null;
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
					errorInfo = "uploadOcError";
				}
				String oldFileName = attach.getOriginalFilename();// 原文件名
				String prefix = FilenameUtils.getExtension(oldFileName);// 原文件后缀
				int filesize = 500000;
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
						companyLicPicPath = path + File.separator + fileName;
					} else if (i == 1) {
						orgCodePicPath = path + File.separator + fileName;
					}
					logger.debug("companyLicPicPath: " + companyLicPicPath);
					logger.debug("orgCodePicPath: " + orgCodePicPath);

				} else {
					request.setAttribute(errorInfo, " * 上传图片格式不正确");
					flag = false;
				}
			}
		}
		if (flag) {
			provider.setCreatedBy(((User) session
					.getAttribute(Constants.USER_SESSION)).getId());
			provider.setCreationDate(new Date());
			provider.setCompanyLicPicPath(companyLicPicPath);
			provider.setOrgCodePicPath(orgCodePicPath);
			try {
				if (providerService.add(provider)) {
					return "redirect:/sys/provider/list.html";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "provideradd";
	}

	@RequestMapping(value = "/del.json", method = RequestMethod.POST)
	@ResponseBody
	public Object delProviderById(@RequestParam(value = "proid") String id) {
		logger.debug("delProviderById proid ===================== " + id);
		HashMap<String, String> resultMap = new HashMap<String, String>();
		if (!StringUtils.isNullOrEmpty(id)) {
			boolean flag = false;
			try {
				flag = providerService.smbmsdeleteProviderById(Integer
						.parseInt(id));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (flag) {// 删除成功
				resultMap.put("delResult", "true");
			} else {// 删除失败
				resultMap.put("delResult", "false");
			}
		} else {
			resultMap.put("delResult", "notexit");
		}
		return JSONArray.toJSONString(resultMap);
	}
}
