package com.flyfiref.dsscm.dao;

import com.flyfiref.dsscm.pojo.ProductCategory;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;


public interface ProductCategoryMapper {
	/**
	 * 根据ID查询商品分类
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public ProductCategory findById(@Param("id")Integer id) throws SQLException;

	/**
	 * 查找ID下的子标签
	 * @param id
	 * @throws SQLException
	 */
	public List<Integer> findChildren(@Param("id")Integer id) throws SQLException;
	/**
	 * 根据 获取 商品分类
	 * @return
	 * @throws SQLException
	 */
	public List<ProductCategory> findProductCategories();
	
//	public List<ProductCategory> getProductCategories(@Param("name")String name,
//			@Param("parentId")Long parentId,@Param("type")Integer type) throws SQLException;
	/**
	 * 查询商品分类的根节点
	 * @return
	 * @throws SQLException
	 */
	public List<ProductCategory> getRootCategories(@Param("parentId")Integer parentId) throws SQLException;
	/**
	 * 删除商品分类
	 * @throws SQLException
	 */
	public int delete(@Param("id")Integer parentId) throws SQLException;

	/**
	 * 批量删除商品分类
	 * @param ids
	 * @throws SQLException
	 */
	public int deleteList(@Param("ids")List<Integer> ids) throws SQLException;
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
