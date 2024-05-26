package com.flyfiref.dsscm.dao;

import com.flyfiref.dsscm.pojo.News;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

public interface NewsMapper {
	/**
	 * 删除新闻
	 * @param id
	 * @throws SQLException
	 */
	public int delete(@Param("id")Long id) throws SQLException;
	
	/**
	 * 更新新闻
	 * @param news
	 * @throws SQLException
	 */
	public int update(News news) throws SQLException;
	/**
	 * 保存新闻
	 * @param news
	 * @throws SQLException
	 */
	public int add(News news) throws SQLException;
	/**
	 * 获取本页要显示的新闻
	 * @return
	 * @throws SQLException
	 */
	public List<News> getAllNews() throws SQLException;

	/**
	 * 获取新闻共有多少条记录
	 * @return
	 * @throws SQLException
	 */
	public long getNewsRowCount()  throws SQLException ;

	public News getNewsById(Long id) throws  SQLException;

}
