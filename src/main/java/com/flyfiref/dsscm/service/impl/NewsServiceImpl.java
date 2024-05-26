package com.flyfiref.dsscm.service.impl;

import com.flyfiref.dsscm.dao.NewsMapper;
import com.flyfiref.dsscm.pojo.Bill;
import com.flyfiref.dsscm.pojo.Info;
import com.flyfiref.dsscm.pojo.News;
import com.flyfiref.dsscm.service.NewsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service("newsService")
public class NewsServiceImpl implements NewsService {
    @Autowired
    NewsMapper newsMapper;

    @Override
    public PageInfo<News> getAllNews(Integer currentPageNo,
                                     Integer pageSize) throws SQLException{
        PageHelper.startPage(currentPageNo, pageSize);
        List<News> list = newsMapper.getAllNews();
        PageInfo<News> pi = new PageInfo<>(list);
        return pi;
    }
    @Override
    public News getNewsById(Long id) throws SQLException {
        return newsMapper.getNewsById(id);
    }

    @Override
    public int deleteNewsById(Long id) throws SQLException{
        return newsMapper.delete(id);
    }
    @Override
    public boolean add(News news) throws SQLException {
        // TODO Auto-generated method stub
        boolean flag = false;
        if (newsMapper.add(news) > 0)
            flag = true;
        return flag;
    }

    @Override
    public boolean update(News news) throws SQLException {
        // TODO Auto-generated method stub
        boolean flag = false;
        if (newsMapper.update(news) > 0)
            flag = true;
        return flag;
    }
}
