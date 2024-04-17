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
