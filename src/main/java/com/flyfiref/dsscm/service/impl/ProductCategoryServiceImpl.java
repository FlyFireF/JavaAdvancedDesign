package com.flyfiref.dsscm.service.impl;

import com.flyfiref.dsscm.dao.ProductCategoryMapper;
import com.flyfiref.dsscm.pojo.ProductCategory;
import com.flyfiref.dsscm.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service("productCategoryService")
public class ProductCategoryServiceImpl implements ProductCategoryService{
	@Autowired
	private ProductCategoryMapper productCategoryMapper;
	
	@Override
	public ProductCategory findById(Integer id) throws SQLException {
		return productCategoryMapper.findById(id);
	}

//	@Override
//	public PageInfo<ProductCategory> getProductCategories(String name,Long parentId,Integer currentPageNo, 
//			Integer pageSize,Integer type) throws SQLException {
//		// 开启分页
//		PageHelper.startPage(currentPageNo, pageSize);
//		List<ProductCategory> list =productCategoryMapper.getProductCategories(name,parentId,type);
//		PageInfo<ProductCategory> pi = new PageInfo<ProductCategory>(list);
//		return pi;
//	}

	@Override
	public List<ProductCategory> getRootCategories(Integer parentId) throws SQLException {
		return productCategoryMapper.getRootCategories(parentId);
	}

	@Override
	@Transactional
	public int delete(Integer id) throws SQLException {
		// zhr: 在删除标签时会递归删除其下的所有子标签
		ProductCategory pc = productCategoryMapper.findById(id);
		if (pc.getType() == 3) {
            return productCategoryMapper.delete(id);
        } else if (pc.getType() == 2) {
			return productCategoryMapper.delete(id) +
					productCategoryMapper.deleteList(productCategoryMapper.findChildren(id));
		} else {
			int res = productCategoryMapper.delete(id);
			List<Integer> pc2List = productCategoryMapper.findChildren(id);
			if (pc2List.size() > 0) {
				res += productCategoryMapper.deleteList(pc2List);
			}
            for (Integer pc2 : pc2List) {
                List<Integer> pc3List = productCategoryMapper.findChildren(pc2);
                if (pc3List.size() > 0) {
                    res += productCategoryMapper.deleteList(pc3List);
                }
            }
			return res;
		}
	}

	@Override
	public int save(ProductCategory productCategory) throws SQLException {
		return productCategoryMapper.save(productCategory);
	}

	@Override
	public int update(ProductCategory productCategory) throws SQLException {
		return productCategoryMapper.update(productCategory);
	}

	@Override
	public List<ProductCategory> findProductCategories() {
		return productCategoryMapper.findProductCategories();
	}

}
