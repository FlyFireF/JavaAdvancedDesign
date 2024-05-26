package com.flyfiref.dsscm.controller;

import com.flyfiref.dsscm.pojo.*;
import com.flyfiref.dsscm.service.InfoService;
import com.flyfiref.dsscm.service.NewsService;
import com.flyfiref.dsscm.service.ProviderService;
import com.flyfiref.dsscm.tools.Constants;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/sys/news")
public class NewsController {
    private Logger logger = LoggerFactory.getLogger(InfoController.class);

    @Autowired
    private NewsService newsService;

    @RequestMapping(value="/list.html")
    public String getNewsList(
            Model model,
            @RequestParam(value = "pageIndex", required = false) String pageIndex) {

        logger.info("getInfoList ---- > pageIndex: " + pageIndex);
        System.out.println("getInfoList ---- > pageIndex: " + pageIndex);

        PageInfo<News> bpi = null;
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
            bpi = newsService.getAllNews(currentPageNo, pageSize);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
//        System.out.println(bpi.getList().size());
        model.addAttribute("pi", bpi);

        return "news";
    }
    @RequestMapping(value = "/add.html", method = RequestMethod.GET)
    public String addNews(@ModelAttribute("news") News news) {
        return "newsadd";
    }

    @RequestMapping(value = "/addsave.html", method = RequestMethod.POST)
    public String addNewsSave(News news, HttpSession session) {
        news.setModifyBy(((User) session.getAttribute(Constants.USER_SESSION))
                .getId());
        news.setCreationDate(new Date());
        try {
            if (newsService.add(news)) {
                return "redirect:/sys/news/list.html";
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "newsadd";
    }

    @RequestMapping(value = "/view/{id}")
    public String view(@PathVariable("id") String id, Model model){
        logger.debug("view id===================== " + id);
        System.out.println("view id===================== " + id);
        News news = new News();
        try {
            news = newsService.getNewsById((long) Integer.parseInt(id));
            System.out.println(news);
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        model.addAttribute(news);
        return "newsview";
    }

    @RequestMapping(value = "/modify/{id}", method = RequestMethod.GET)
    public String getNewsById(@PathVariable("id") String id, Model model,
                              HttpServletRequest request) {
        News news = new News();
        try {
            news = newsService.getNewsById(Long.parseLong(id));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute(news);
        return "newsupdate";
    }

    @RequestMapping(value = "/modifysave.html", method = RequestMethod.POST)
    public String modifyNewsSave(News news, HttpSession session) {
        System.out.println("modifysave");
        news.setModifyBy(((User) session.getAttribute(Constants.USER_SESSION))
                .getId());
        news.setModifyDate(new Date());
        try {
            if (newsService.update(news)) {
                return "redirect:/sys/news/list.html";
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "newsupdate";
    }

    @RequestMapping(value = "/del.json")
    @ResponseBody
    public int del(@RequestParam("id") String id) throws SQLException {
        System.out.println("==================" + id);
        return newsService.deleteNewsById(Long.parseLong((id)));
    }
}
