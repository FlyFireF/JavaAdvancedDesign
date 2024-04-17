package com.flyfiref.dsscm.service.impl;

import com.flyfiref.dsscm.dao.ProductMapper;
import com.flyfiref.dsscm.pojo.Product;
import com.flyfiref.dsscm.service.ProductService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service("productService")
public class ProductServiceImpl implements  ProductService{
	@Autowired
	private ProductMapper productMapper;

	@Override
	public Product findById(Long id) throws SQLException {
		return productMapper.findById(id);
	}

	@Override
	public PageInfo<Product> getProducts(Long t1id, Long t2id, String name,Integer currentPageNo, Integer pageSize)
			throws SQLException {
		// 开启分页
		PageHelper.startPage(currentPageNo, pageSize);
		List<Product> list = productMapper.getProducts(t1id, t2id, name);
		PageInfo<Product> pi = new PageInfo<Product>(list);
		return pi;
	}

	@Override
	public int delete(Long id) throws SQLException {
		return productMapper.delete(id);
	}

	@Override
	public int save(Product product) throws SQLException {
		return productMapper.save(product);
	}

	@Override
	public int update(Product product) throws SQLException {
		return productMapper.update(product);
	}
	
}
