package com.flyfiref.dsscm.service;


import com.flyfiref.dsscm.pojo.News;
import com.github.pagehelper.PageInfo;

import java.sql.SQLException;
import java.util.List;

public interface NewsService {
    /**
     * 获取本页要显示的新闻
     * @return
     * @throws SQLException
     */
    public PageInfo<News> getAllNews(Integer currentPageNo,
                                     Integer pageSize) throws SQLException;
    public News getNewsById(Long id) throws SQLException;
    public int deleteNewsById(Long id) throws SQLException;

    public boolean add(News news) throws SQLException;

    public boolean update(News news) throws SQLException;
}
