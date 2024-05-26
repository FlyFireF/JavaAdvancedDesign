package com.flyfiref.dsscm.service;

import com.flyfiref.dsscm.pojo.ProductCategory;

import java.sql.SQLException;
import java.util.List;


public interface ProductCategoryService {
	/**
	 * 根据ID查询商品分类
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public ProductCategory findById(Integer id) throws SQLException;
	
	//查询全部商品分类
	public List<ProductCategory> findProductCategories();
	/**
	 * 根据父ID获取子商品分类
	 * @param parentId
	 * @return
	 * @throws SQLException
	 */
//	public PageInfo<ProductCategory> getProductCategories(String name,Long parentId,Integer currentPageNo, 
//			Integer pageSize,Integer type) throws SQLException;
	/**
	 * 查询商品分类的根节点
	 * @return
	 * @throws SQLException
	 */
	public List<ProductCategory> getRootCategories(Integer parentId) throws SQLException;
	/**
	 * 删除商品分类
	 * @throws SQLException
	 */
	public int delete( Integer id) throws SQLException;
	/**
	 * 新增商品分类
	 */
	public int save(ProductCategory productCategory) throws SQLException;
	/**
	 * 更新商品分类
	 * @param productCategory
	 * @throws SQLException
	 */
	public int update(ProductCategory productCategory) throws SQLException;
}
