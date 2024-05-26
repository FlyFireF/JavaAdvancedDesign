package com.flyfiref.dsscm.controller;

import com.flyfiref.dsscm.pojo.*;
import com.flyfiref.dsscm.service.OrderDetailService;
import com.flyfiref.dsscm.service.OrderService;
import com.flyfiref.dsscm.tools.Constants;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.mysql.cj.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sys/order")
public class OrderController extends BaseController{
	private Logger logger = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderDetailService orderDetailService;

	@RequestMapping(value = "/list.html")
	public String getOrderList(
			Model model,
			@RequestParam(value = "queryname", required = false) String queryUserName,
			@RequestParam(value = "status", required = false) Integer status,
			@RequestParam(value = "pageIndex", required = false) Integer pageIndex) {
		logger.info("getOrderList ---- > queryUserName: " + queryUserName);
		logger.info("getOrderList ---- > status: " + status);
		logger.info("getOrderList ---- > pageIndex: " + pageIndex);
		PageInfo<Order> pi = null;
		List<Order> oolist= null;
		// 设置页面容量
		int pageSize = Constants.pageSize;
		// 页码为空默认分第一页
		if (null == pageIndex) {
			pageIndex = 1;
		}

		try {
			List<Order> olist = orderService.getOrders(0L, queryUserName, status);
			 pi = new PageInfo();
			 oolist = new ArrayList<Order>();
			 System.out.println("size "+olist.size());
			 System.out.println("from = "+(pageIndex-1)*pageSize);
			 int pages = olist.size()%pageSize==0 ? olist.size()/pageSize : olist.size()/pageSize+1;
			 int end = pageIndex<pages ? pageIndex*pageSize : (pageIndex-1)*pageSize + olist.size()%5;

			 for(int i=(pageIndex-1)*pageSize; i<end ; i++){
				 System.out.println("========="+i);
				 oolist.add(olist.get(i));
			 }
			 pi.setPageNum(pageIndex);
			 pi.setPrePage(pageIndex-1);
			 pi.setNextPage(pageIndex+1);
			 pi.setPageSize(pageSize);
			 pi.setLastPage(pages);
			 pi.setPages(pages);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[] statuslist = {"请选择","待审核","审核通过","配货","卖家已发货","已收货"};
		model.addAttribute("oolist", oolist);
		model.addAttribute("pi", pi);
		model.addAttribute("queryname", queryUserName);
		model.addAttribute("status", status);
		model.addAttribute("statuslist", statuslist);
		return "orderlist";
	}
	@RequestMapping("/update.html")
	@ResponseBody
	public Map getupdateList(@RequestParam(value = "queryname", required = false) String queryUserName,
							 @RequestParam(value = "status", required = false) Integer status,
							 @RequestParam(value = "pageIndex", required = false) Integer pageIndex) {
		logger.info("getOrderList ---- > queryUserName: " + queryUserName);
		logger.info("getOrderList ---- > status: " + status);
		logger.info("getOrderList ---- > pageIndex: " + pageIndex);
		PageInfo<Order> pi = null;
		List<Order> oolist = null;

		// 设置页面容量
		int pageSize = Constants.pageSize;
		// 页码为空默认分第一页
		if (null == pageIndex) {
			pageIndex = 1;
		}

			try {
				List<Order> olist = orderService.getOrders(0L, queryUserName, status);
				pi = new PageInfo();
				oolist = new ArrayList<Order>();
				System.out.println("size " + olist.size());
				System.out.println("from = " + (pageIndex - 1) * pageSize);
				int pages = olist.size() % pageSize == 0 ? olist.size() / pageSize : olist.size() / pageSize + 1;
				int end = pageIndex < pages ? pageIndex * pageSize : (pageIndex - 1) * pageSize + olist.size() % 5;

				for (int i = (pageIndex - 1) * pageSize; i < end; i++) {
					System.out.println("=========" + i);
					oolist.add(olist.get(i));
				}
				pi.setPageNum(pageIndex);
				pi.setPrePage(pageIndex - 1);
				pi.setNextPage(pageIndex + 1);
				pi.setPageSize(pageSize);
				pi.setLastPage(pages);
				pi.setPages(pages);
			} catch (Exception e) {
				e.printStackTrace();
			}

			// 将用户列表转换成HTML格式的字符串返回给前端
			String orderListHtml = convertOrderListToHtml(pi, oolist, queryUserName, status);
			Map<String, String> map = new HashMap<String, String>();
			map.put("orderListHtml", orderListHtml);
			return map;
		}



	// 将用户列表转换成HTML格式的方法
	private String convertOrderListToHtml(PageInfo<Order> pi, List<Order> oolist, String queryUserName, Integer status) {
		StringBuilder htmlBuilder = new StringBuilder();

		// 在这里添加HTML头部，例如表格的开始标签和表头
		htmlBuilder.append("<table class='table table-striped'>");
		htmlBuilder.append("<thead>");
		htmlBuilder.append("<tr>");
		htmlBuilder.append("<th>订单号</th>");
		htmlBuilder.append("<th>订单状态</th>");
		htmlBuilder.append("<th>顾客姓名</th>");
		htmlBuilder.append("<th>联系电话</th>");
		htmlBuilder.append("<th>创建时间</th>");
		htmlBuilder.append("<th>商品名称</th>");
		htmlBuilder.append("<th>商品图片</th>");
		htmlBuilder.append("<th>价格</th>");
		htmlBuilder.append("<th>数量</th>");
		htmlBuilder.append("<th>小计价格</th>");
		htmlBuilder.append("<th>付款方式</th>");
		htmlBuilder.append("<th>商品数量</th>");
		htmlBuilder.append("<th>总消费</th>");
		htmlBuilder.append("</tr>");
		htmlBuilder.append("</thead>");
		htmlBuilder.append("<tbody>");

		// 遍历用户列表数据，将每个用户的信息添加到HTML中
		for (Order order : pi.getList()) {
			htmlBuilder.append("<tr>");
			htmlBuilder.append("<td>").append(order.getSerialNumber()).append("</td>");
			htmlBuilder.append("<td>").append(setStatus(order)).append("</td>");
			htmlBuilder.append("<td>").append(order.getUserName()).append("</td>");
			htmlBuilder.append("<td>").append(order.getCustomerPhone()).append("</td>");
			htmlBuilder.append("<td>").append(order.getCreationDate()).append("</td>");
//			htmlBuilder.append("<td>").append(order.mingcheng1()).append("</td>");
//			htmlBuilder.append("<td>").append(setGender(tupian)).append("</td>");
//			htmlBuilder.append("<td>").append(setGender(jiage)).append("</td>");
//			htmlBuilder.append("<td>").append(getOrderDetil(order.getquantity())).append("</td>");
//			//htmlBuilder.append("<td>").append(getOrderDetil(order.getcost())).append("</td>");
			htmlBuilder.append("<td>").append(setPayType(order)).append("</td>");
			htmlBuilder.append("<td>").append(order.getProCount()).append("</td>");
			htmlBuilder.append("<td>").append(order.getCost()).append("</td>");

			htmlBuilder.append("</tr>");
		}

		// 在这里添加HTML尾部，例如表格的结束标签
		htmlBuilder.append("</tbody>");
		htmlBuilder.append("</table>");

		return htmlBuilder.toString();
	}


	private String setStatus(Order order) {
		if (order.getStatus() == 1) {
			return "待审核";
		}
		if (order.getStatus() == 2) {
			return "审核通过";
		}
		if (order.getStatus() == 3) {
			return "配货";
		}
		if (order.getStatus() == 4) {
			return "卖家已发货";
		}
		if (order.getStatus() == 5 ){
			return "已收货";
		}
		return "未知状态";
	}
	private String setPayType(Order order) {
		if (order.getPayType() == 1) {
			return "在线支付";
		}
		if (order.getPayType() == 2) {
			return "货到付款";
		}
		return "未知状态";
	}



	@RequestMapping(value = "/delorder.json", method = RequestMethod.GET)
	@ResponseBody
	public Object deluser(@RequestParam("id") String id) {
		HashMap<String, String> resultMap = new HashMap<String, String>();
		if (StringUtils.isNullOrEmpty(id)) {
			resultMap.put("delResult", "notexist");
		} else {
			try {
				if (orderService.deleteOrder(Integer.parseInt(id)))
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

}//	private String getOrderDetil(Long orderid) {
//		String quantity = "";
//		String cost = "";
//		try {
//			// 执行联表查询，根据分类ID查询对应的分类名称
//			OrderDetail orderDetail = OrderDetailService.findById(orderid);
//			if (orderDetail != null) {
//				quantity = orderDetail.getQuantity();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return quantity;
//	}
