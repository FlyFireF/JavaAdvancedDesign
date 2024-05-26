package com.flyfiref.dsscm.controller;

import com.flyfiref.dsscm.pojo.Bill;
import com.flyfiref.dsscm.pojo.Info;
import com.flyfiref.dsscm.pojo.News;
import com.flyfiref.dsscm.pojo.Provider;
import com.flyfiref.dsscm.service.InfoService;
import com.flyfiref.dsscm.service.ProviderService;
import com.flyfiref.dsscm.tools.Constants;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/sys/info")
public class InfoController {
    private Logger logger = LoggerFactory.getLogger(InfoController.class);

    @Autowired
    private InfoService infoService;

    @RequestMapping(value="/list.html")
    public String getInfoList(
            Model model,
            @RequestParam(value = "pageIndex", required = false) String pageIndex) {

        logger.info("getInfoList ---- > pageIndex: " + pageIndex);
        System.out.println("getInfoList ---- > pageIndex: " + pageIndex);

        PageInfo<Info> bpi = null;
        // 设置页面容量
        int pageSize = Constants.pageSize;
        // 当前页码
        int currentPageNo = 1;


        if (pageIndex != null) {
            try {
                currentPageNo = Integer.valueOf(pageIndex);
            } catch (NumberFormatException e) {
                return "redirect:/syserror.html";
            }
        }

        try {
            bpi = infoService.getInfoList(currentPageNo,pageSize);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(bpi);
        model.addAttribute("pi", bpi);

        return "info";
    }

    @RequestMapping(value = "/view/{id}")
    public String view(@PathVariable("id") String id, Model model){
        logger.debug("view id===================== " + id);
        System.out.println("view id===================== " + id);
        Info info = new Info();
        try {
            info = infoService.getInfoById(Integer.parseInt(id));
            System.out.println(info);
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        model.addAttribute(info);
        return "infoview";
    }

    @RequestMapping(value = "/del.json")
    @ResponseBody
    public int del(@RequestParam("id") String id) throws Exception {
        System.out.println("==================" + id);
        return infoService.deleteInfoById(Integer.parseInt(id));
    }
}
