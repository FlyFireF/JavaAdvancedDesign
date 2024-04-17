package com.flyfiref.dsscm.dao;

import com.flyfiref.dsscm.pojo.Product;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;


public interface ProductMapper {
	/**
	 * 根据ID查询商品
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public Product findById(@Param("id")Long id) throws SQLException;
	/**
	 * 根据分类,名称查询商品
	 * @return
	 * @throws SQLException
	 */
	public List<Product> getProducts(@Param("t1id")Long t1id,
			@Param("t2id")Long t2id,@Param("name")String name) throws SQLException;
	/**
	 * 删除一款商品
	 * @param id
	 * @throws SQLException
	 */
	public int delete(@Param("id")Long id) throws SQLException;
	/**
	 * 保存一款商品
	 * @param product
	 * @return
	 * @throws SQLException
	 */
	public int save(Product product) throws SQLException;
	/**
	 * 更新一款商品
	 * @param product
	 * @return
	 * @throws SQLException
	 */
	public int update(Product product) throws SQLException;
	
}
