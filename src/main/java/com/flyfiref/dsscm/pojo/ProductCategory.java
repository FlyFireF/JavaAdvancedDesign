package com.flyfiref.dsscm.pojo;

import java.io.Serializable;
import java.util.List;

public class ProductCategory implements Serializable {
	private Long id;// ID
	private String name;// 名称
	private Long parentId;// 父级ID
	private int type;// 级别(1:一级 2：二级 3：三级)',
	private String iconClass;// 图标
	
	private String pc1name;// 一级分类名称
	private String pc2name;// 二级分类名称
	private String pc3name;// 三级分类名称

	private List<ProductCategory> productCategories;
	
	
	public List<ProductCategory> getProductCategories() {
		return productCategories;
	}

	public void setProductCategories(List<ProductCategory> productCategories) {
		this.productCategories = productCategories;
	}

	public String getPc1name() {
		return pc1name;
	}

	public void setPc1name(String pc1name) {
		this.pc1name = pc1name;
	}

	public String getPc2name() {
		return pc2name;
	}

	public void setPc2name(String pc2name) {
		this.pc2name = pc2name;
	}

	public String getPc3name() {
		return pc3name;
	}

	public void setPc3name(String pc3name) {
		this.pc3name = pc3name;
	}

	public ProductCategory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductCategory(Long id, String name, Long parentId, int type,
			String iconClass) {
		super();
		this.id = id;
		this.name = name;
		this.parentId = parentId;
		this.type = type;
		this.iconClass = iconClass;
	}

	@Override
	public String toString() {
		return "ProductCategory [id=" + id + ", name=" + name + ", parentId="
				+ parentId + ", type=" + type + ", iconClass=" + iconClass
				+ "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getIconClass() {
		return iconClass;
	}

	public void setIconClass(String iconClass) {
		this.iconClass = iconClass;
	}

}
