package com.flyfiref.dsscm.controller;

import com.flyfiref.dsscm.pojo.Bill;
import com.flyfiref.dsscm.pojo.Provider;
import com.flyfiref.dsscm.pojo.User;
import com.flyfiref.dsscm.service.BillService;
import com.flyfiref.dsscm.service.ProviderService;
import com.flyfiref.dsscm.tools.Constants;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.mysql.cj.util.StringUtils;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sys/bill")
public class BillController extends BaseController {
	private Logger logger = LoggerFactory.getLogger(BillController.class);

	@Autowired
	private BillService billService;
	@Autowired
	private ProviderService providerService;

	@RequestMapping(value = "/list.html")
	public String getBillList(
			Model model,
			@RequestParam(value = "queryProductName", required = false) String queryProductName,
			@RequestParam(value = "queryProviderId", required = false) String queryProviderId,
			@RequestParam(value = "queryIsPayment", required = false) String queryIsPayment,
			@RequestParam(value = "pageIndex", required = false) String pageIndex) {
		logger.info("getBillList ---- > queryProductName: " + queryProductName);
		logger.info("getUserList ---- > queryProviderId: " + queryProviderId);
		logger.info("getUserList ---- > queryIsPayment: " + queryIsPayment);
		logger.info("getUserList ---- > pageIndex: " + pageIndex);
		Integer _queryProviderId = null;
		Integer _queryIsPayment = null;
		PageInfo<Bill> bpi = null;
		List<Provider> providerList = null;
		// 设置页面容量
		int pageSize = Constants.pageSize;
		// 当前页码
		int currentPageNo = 1;

		if (queryProductName == null) {
			queryProductName = "";
		}
		if (queryProviderId != null && !queryProviderId.equals("")) {
			_queryProviderId = Integer.parseInt(queryProviderId);
		}
		if (queryIsPayment != null && !queryIsPayment.equals("")) {
			_queryIsPayment = Integer.parseInt(queryIsPayment);
		}

		if (pageIndex != null) {
			try {
				currentPageNo = Integer.valueOf(pageIndex);
			} catch (NumberFormatException e) {
				return "redirect:/syserror.html";
			}
		}
		
		try {
			bpi = billService.getBillList(queryProductName,
					_queryProviderId, _queryIsPayment,currentPageNo,pageSize);
			providerList = providerService.getProviderList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("pi", bpi);
		model.addAttribute("providerList", providerList);
		model.addAttribute("queryProductName", queryProductName);
		model.addAttribute("queryProviderId", queryProviderId);
		model.addAttribute("queryIsPayment", queryIsPayment);
		return "billlist";
	}

	@RequestMapping("/update.html")
	@ResponseBody
	public Map getupdateList(@RequestParam(value = "queryProductName", required = false) String queryProductName,
								@RequestParam(value = "queryProviderId", required = false) String queryProviderId,
								@RequestParam(value = "queryIsPayment", required = false) String queryIsPayment,
								@RequestParam(value = "pageIndex", required = false) String pageIndex,
							 HttpServletResponse response) throws IOException {
		logger.info("getBillList ---- > queryProductName: " + queryProductName);
		logger.info("getUserList ---- > queryProviderId: " + queryProviderId);
		logger.info("getUserList ---- > queryIsPayment: " + queryIsPayment);
		logger.info("getUserList ---- > pageIndex: " + pageIndex);
		Integer _queryProviderId = null;
		Integer _queryIsPayment = null;
		PageInfo<Bill> bpi = null;
		List<Provider> providerList = null;
		// 设置页面容量
		int pageSize = Constants.pageSize;
		// 当前页码
		int currentPageNo = 1;

		if (queryProductName == null) {
			queryProductName = "";
		}
		if (queryProviderId != null && !queryProviderId.equals("")) {
			_queryProviderId = Integer.parseInt(queryProviderId);
		}
		if (queryIsPayment != null && !queryIsPayment.equals("")) {
			_queryIsPayment = Integer.parseInt(queryIsPayment);
		}

		if (pageIndex != null) {
			try {
				currentPageNo = Integer.valueOf(pageIndex);
			} catch (NumberFormatException e) {
				response.sendRedirect("/syserror.html");
			}
		}

		try {
			bpi = billService.getBillList(queryProductName,
					_queryProviderId, _queryIsPayment,currentPageNo,pageSize);
			providerList = providerService.getProviderList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 将用户列表转换成HTML格式的字符串返回给前端
		String billListHtml = convertBillListToHtml(bpi, providerList, queryProductName, queryProviderId,queryIsPayment);
		Map<String,String> map = new HashMap<String,String>();
		map.put("billListHtml", billListHtml);
		return map;
	}

	private String convertBillListToHtml(PageInfo<Bill> bpi, List<Provider> providerList, String queryProductName,
										 String queryProviderId, String queryIsPayment) {
		StringBuilder htmlBuilder = new StringBuilder();

		// 在这里添加HTML头部，例如表格的开始标签和表头
		htmlBuilder.append("<table class='table table-striped'>");
		htmlBuilder.append("<thead>");
		htmlBuilder.append("<tr>");
		htmlBuilder.append("<th>订单编码</th>");
		htmlBuilder.append("<th>商品名称</th>");
		htmlBuilder.append("<th>供应商</th>");
		htmlBuilder.append("<th>订单金额</th>");
		htmlBuilder.append("<th>是否付款</th>");
		htmlBuilder.append("<th>创建时间</th>");
		htmlBuilder.append("</tr>");
		htmlBuilder.append("</thead>");
		htmlBuilder.append("<tbody>");

		// 遍历用户列表数据，将每个用户的信息添加到HTML中
		for (Bill bill : bpi.getList()) {
			htmlBuilder.append("<tr>");
			htmlBuilder.append("<td>").append(bill.getBillCode()).append("</td>");
			htmlBuilder.append("<td>").append(bill.getProductName()).append("</td>");
			htmlBuilder.append("<td>").append(setProviderId(bill)).append("</td>");
			htmlBuilder.append("<td>").append(bill.getProductCount()).append("</td>");
			htmlBuilder.append("<td>").append(setIsPayment(bill)).append("</td>");
			htmlBuilder.append("<td>").append(bill.getCreationDate()).append("</td>");
			htmlBuilder.append("</tr>");
		}

		// 在这里添加HTML尾部，例如表格的结束标签
		htmlBuilder.append("</tbody>");
		htmlBuilder.append("</table>");

		return htmlBuilder.toString();
	}


	private String setIsPayment(Bill bill) {
		if (bill.getIsPayment() == 2) {
			return "已付款";
		}
		if (bill.getIsPayment() == 1) {
			return "未付款";
		}
		return "付款未知";
	}
	private String setProviderId(Bill bill) {
		if (bill.getProviderId() == 1) {
			return "北京三木堂商贸有限公司";
		}
		if (bill.getProviderId() == 2) {
			return "石家庄帅益食品贸易有限公司";
		}
		if (bill.getProviderId() == 3) {
			return "深圳市泰香米业有限公司";
		}
		if (bill.getProviderId() == 4) {
			return "深圳市喜来客商贸有限公司";
		}
		if (bill.getProviderId() == 5) {
			return "兴化佳美调味品厂";
		}
		if (bill.getProviderId() == 6) {
			return "北京纳福尔食用油有限公司";
		}
		if (bill.getProviderId() == 7) {
			return "北京国粮食用油有限公司";
		}
		if (bill.getProviderId() == 8) {
			return "慈溪市广和绿色食品厂";
		}
		if (bill.getProviderId() == 9) {
			return "优百商贸有限公司";
		}
		if (bill.getProviderId() == 10) {
			return "南京火头军信息技术有限公司";
		}
		if (bill.getProviderId() == 11) {
			return "广州市白云区美星五金制品厂";
		}
		if (bill.getProviderId() == 12) {
			return "北京隆盛日化科技";
		}
		if (bill.getProviderId() == 13) {
			return "山东豪克华光联合发展有限公司";
		}
		if (bill.getProviderId() == 14) {
			return "无锡喜源坤商行";
		}
		if (bill.getProviderId() == 15) {
			return "乐摆日用品厂";
		}
		return "未知公司";
	}


	@RequestMapping(value = "/add.html", method = RequestMethod.GET)
	public String addBill(@ModelAttribute("bill") Bill bill) {
		return "billadd";
	}

	@RequestMapping(value = "/addsave.html", method = RequestMethod.POST)
	public String addBillSave(Bill bill, HttpSession session) {
		bill.setModifyBy(((User) session.getAttribute(Constants.USER_SESSION))
				.getId());
		bill.setCreationDate(new Date());
		try {
			if (billService.add(bill)) {
				return "redirect:/sys/bill/list.html";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "billadd";
	}

	@RequestMapping(value = "/modify/{id}", method = RequestMethod.GET)
	public String getBillById(@PathVariable("id") String id, Model model,
			HttpServletRequest request) {
		Bill bill = new Bill();
		try {
			bill = billService.getBillById(Integer.parseInt(id));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute(bill);
		return "billmodify";
	}

	@RequestMapping(value = "/modifysave.html", method = RequestMethod.POST)
	public String modifyBillSave(Bill bill, HttpSession session) {
		bill.setModifyBy(((User) session.getAttribute(Constants.USER_SESSION))
				.getId());
		bill.setModifyDate(new Date());
		try {
			if (billService.modify(bill)) {
				return "redirect:/sys/bill/list.html";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "billmodify";
	}

	@RequestMapping(value = "/providerlist.json", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public List<Provider> getProviderList() {
		List<Provider> providerList = null;
		try {
			providerList = providerService.getProviderList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.debug("providerList size: " + providerList.size());
		return providerList;
	}

	@RequestMapping(value = "/delbill.json", method = RequestMethod.GET)
	@ResponseBody
	public Object delBill(@RequestParam("id") String id) {
		HashMap<String, String> resultMap = new HashMap<String, String>();
		if (StringUtils.isNullOrEmpty(id)) {
			resultMap.put("delResult", "notexist");
		} else {
			try {
				if (billService.deleteBillById(Integer.parseInt(id)))
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

	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") String id, Model model,
			HttpServletRequest request) {
		logger.debug("view id===================== " + id);
		Bill bill = new Bill();
		try {
			bill = billService.getBillById(Integer.parseInt(id));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute(bill);
		return "billview";
	}

}
