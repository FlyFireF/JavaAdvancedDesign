package com.flyfiref.dsscm.controller;

import com.flyfiref.dsscm.pojo.Order;
import com.flyfiref.dsscm.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import com.flyfiref.dsscm.service.OrderService;
import com.flyfiref.dsscm.service.ProductService;
import com.flyfiref.dsscm.service.ProviderService;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    private Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProviderService providerService;

    @RequestMapping(value = "/sys/main.html")
    public String getCounts(HttpServletRequest request){
        int providerCount = 0;
        int productCount = 0;
        int orderCount = 0;
        float totalCost = 0.00f;
        try {
            providerCount = providerService.getproviderCount();
            productCount = productService.getproductCount();
            orderCount = orderService.getorderCount();
            List<Order> olist = orderService.getOrder();
            for (Order order : olist) {
                totalCost += order.getCost();
            }
            logger.info("Provider count: " + providerCount);
            logger.info("Product count: " + productCount);
            logger.info("Order count: " + orderCount);
            logger.info("Total cost: " + totalCost);

        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("providerCount", providerCount);
        request.setAttribute("productCount", productCount);
        request.setAttribute("orderCount", orderCount);
        request.setAttribute("totalCost", totalCost);
        return "index";
    }
}