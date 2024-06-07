package com.flyfiref.dsscm.controller;

import com.flyfiref.dsscm.pojo.Order;
import com.flyfiref.dsscm.pojo.OrderDetail;
import com.flyfiref.dsscm.pojo.Product;
import com.flyfiref.dsscm.service.OrderDetailService;
import com.flyfiref.dsscm.service.OrderService;
import com.flyfiref.dsscm.service.ProductService;
import com.flyfiref.dsscm.service.ProviderService;
import com.mysql.cj.conf.ConnectionUrlParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;



@Controller
public class GraphController {
    private Logger logger = LoggerFactory.getLogger(GraphController.class);

    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderDetailService orderDetailService;

    public LocalDate toLocalDate(Date date) {
        if (date == null) {
            return null;
        }
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDate();
    }

    @RequestMapping(value = "/sys/main/linechart.json", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public Map<String, Double> lineChart() {
        Map<String, Double> monthlySalesMap = new HashMap<>();
        try {
            LocalDate currentDate = LocalDate.now();
            List<Order> olist = orderService.getOrder();
            for (int i = 0; i < 12; i++) {
                LocalDate targetDate = currentDate.minusMonths(i);
                String key = targetDate.format(DateTimeFormatter.ofPattern("yyyy.MM"));
                double salesForMonth = 0.0;
                for (Order order : olist) {
                    LocalDate creationDate = toLocalDate(order.getCreationDate());
                    if (!creationDate.isBefore(currentDate.minusMonths(11))) {
                        if (creationDate.getMonth().equals(targetDate.getMonth()) && creationDate.getYear() == targetDate.getYear()) {
                            salesForMonth += order.getCost();
                        }
                    }
                }
                monthlySalesMap.put(key, salesForMonth);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return monthlySalesMap;
    }

    @RequestMapping(value = "/sys/main/barchart.json", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map<String, Double> barChart() {

        List<String> categoryList = Arrays.asList("食品", "饮料烟酒", "副食", "粮油", "生鲜类", "日配类", "散装加工", "文体办公", "五金家电", "家居百货", "洗涤日化", "针纺服饰");

        Map<String, Double> categorySalesMap = new HashMap<>();
        for (String category : categoryList) {
            categorySalesMap.put(category, 0.0);
        }
        try {
            List<Product> plist = productService.getProductList();
            for (Product product : plist) {
                String pc1name = product.getPc1name();
                if (categoryList.contains(pc1name)) {
                    List<OrderDetail> odlist = orderDetailService.getOrderDetailListByProductId(product.getId());
                    for (OrderDetail orderDetail : odlist) {
                        double cost = orderDetail.getCost();
                        categorySalesMap.put(pc1name, categorySalesMap.get(pc1name) + cost);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categorySalesMap;
    }


    @RequestMapping(value = "/sys/main/piechart.json", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map<String, Double> pieChart() {
        Map<String, Double> categorySalesMap = new HashMap<>();

        try {
            List<Product> plist = productService.getProductList();
            for (Product product : plist) {
                String pc1name = product.getPc1name();
                categorySalesMap.put(pc1name, 0.0);
                List<OrderDetail> odlist = orderDetailService.getOrderDetailListByProductId(product.getId());
                for (OrderDetail orderDetail : odlist) {
                    double cost = orderDetail.getCost();
                    categorySalesMap.put(pc1name, categorySalesMap.get(pc1name) + cost);
                }
            }
            double totalCost = categorySalesMap.values().stream().mapToDouble(Double::doubleValue).sum();

            if (totalCost != 0.0) {
                for (Map.Entry<String, Double> entry : categorySalesMap.entrySet()) {
                    entry.setValue(entry.getValue() / totalCost * 100);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(categorySalesMap);
        return categorySalesMap;
    }
}
