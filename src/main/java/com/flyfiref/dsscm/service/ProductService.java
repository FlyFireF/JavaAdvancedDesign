package com.flyfiref.dsscm.service;

import com.flyfiref.dsscm.pojo.Order;
import com.flyfiref.dsscm.pojo.Product;
import com.github.pagehelper.PageInfo;

import java.sql.SQLException;
import java.util.List;


public interface ProductService {
	/**
	 * 根据ID查询商品
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public Product findById(Long id) throws SQLException;
	/**
	 * 根据分类,名称查询商品
	 * @return
	 * @throws SQLException
	 */
	public PageInfo<Product> getProducts(Long t1id,
			Long t2id,String name,Integer currentPageNo, 
			Integer pageSize) throws SQLException;
	/**
	 * 删除一款商品
	 * @param id
	 * @throws SQLException
	 */
	public int delete(Long id) throws SQLException;
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

	/**
	 * 通过条件查询-商品表记录数
	 * @return
	 * @throws Exception
	 */
	public int getProductCount() throws Exception;

	/**
	 * 获取订单及其下属的详情记录
	 * @return
	 * @throws SQLException
	 */
	public List<Product> getProductList()  throws SQLException;
}
