package com.flyfiref.dsscm.controller;

import com.alibaba.fastjson.JSONArray;
import com.flyfiref.dsscm.pojo.Product;
import com.flyfiref.dsscm.pojo.ProductCategory;
import com.flyfiref.dsscm.pojo.User;
import com.flyfiref.dsscm.service.ProductCategoryService;
import com.flyfiref.dsscm.service.ProductService;
import com.flyfiref.dsscm.tools.Constants;
import com.github.pagehelper.PageInfo;
import com.mysql.cj.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sys/product")
public class ProductController {
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductCategoryService productCategoryService;

	@RequestMapping(value = "/list.html")
	public String getProductList(
			Model model,
			@RequestParam(value = "queryname", required = false) String queryName,
			@RequestParam(value = "categoryLevel1Id", required = false) Long categoryLevel1Id,
			@RequestParam(value = "pageIndex", required = false) Integer pageIndex) {
		PageInfo<Product> ppi = null;
		List<ProductCategory> pcList = null;
		// 设置页面容量
		int pageSize = Constants.pageSize;
		// 页码为空默认分第一页
		if (null == pageIndex) {
			pageIndex = 1;
		}
		if (queryName == null) {
			queryName = "";
		}
		try {
			ppi = productService.getProducts(categoryLevel1Id, null, queryName,
					pageIndex, pageSize);
			pcList = productCategoryService.getRootCategories(0);
		} catch (Exception e) {
			e.printStackTrace();
		}

		model.addAttribute("pi", ppi);
		model.addAttribute("pcList", pcList);
		model.addAttribute("queryName", queryName);
		model.addAttribute("categoryLevel1Id", categoryLevel1Id);
		return "productlist";
	}
	@RequestMapping("/update.html")
	@ResponseBody
	public Map getupdateList(@RequestParam(value = "queryname", required = false) String queryName,
							 @RequestParam(value = "categoryLevel1Id", required = false) Long categoryLevel1Id,
							 @RequestParam(value = "pageIndex", required = false) Integer pageIndex) {
		PageInfo<Product> ppi = null;
		List<ProductCategory> pcList = null;
		// 设置页面容量
		int pageSize = Constants.pageSize;
		// 页码为空默认分第一页
		if (null == pageIndex) {
			pageIndex = 1;
		}
		if (queryName == null) {
			queryName = "";
		}
		try {
			ppi = productService.getProducts(categoryLevel1Id, null, queryName,
					pageIndex, pageSize);
			pcList = productCategoryService.getRootCategories(0);
		} catch (Exception e) {
			e.printStackTrace();
		}


		// 将用户列表转换成HTML格式的字符串返回给前端
		String productListHtml = convertProductListToHtml(ppi, pcList, queryName, categoryLevel1Id);
		Map<String,String> map = new HashMap<String,String>();
		map.put("productListHtml", productListHtml);
		return map;
	}
	private String getCategoryName(Long categoryId) {
		String categoryName = "";
		try {
			// 执行联表查询，根据分类ID查询对应的分类名称
			ProductCategory productCategory = productCategoryService.findById(Math.toIntExact(categoryId));
			if (productCategory != null) {
				categoryName = productCategory.getName();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return categoryName;
	}


	// 将用户列表转换成HTML格式的方法
	private String convertProductListToHtml(PageInfo<Product> ppi, List<ProductCategory> pcList, String queryname, Long categoryLevel1Id) {
		StringBuilder htmlBuilder = new StringBuilder();

		// 在这里添加HTML头部，例如表格的开始标签和表头
		htmlBuilder.append("<table class='table table-striped'>");
		htmlBuilder.append("<thead>");
		htmlBuilder.append("<tr>");
		htmlBuilder.append("<th>商品编码</th>");
		htmlBuilder.append("<th>商品名称</th>");
		htmlBuilder.append("<th>单价</th>");
		htmlBuilder.append("<th>摆放位置</th>");
		htmlBuilder.append("<th>数量</th>");
		htmlBuilder.append("<th>一级分类名称</th>");
		htmlBuilder.append("<th>二级分类名称</th>");
		htmlBuilder.append("<th>三级分类名称</th>");
		htmlBuilder.append("</tr>");
		htmlBuilder.append("</thead>");
		htmlBuilder.append("<tbody>");

		// 遍历用户列表数据，将每个用户的信息添加到HTML中
		for (Product product : ppi.getList()) {
			htmlBuilder.append("<tr>");
			htmlBuilder.append("<td>").append(product.getId()).append("</td>");
			htmlBuilder.append("<td>").append(product.getName()).append("</td>");
			htmlBuilder.append("<td>").append(product.getPrice()).append("</td>");
			htmlBuilder.append("<td>").append(product.getPlacement()).append("</td>");
			htmlBuilder.append("<td>").append(product.getStock()).append("</td>");
			// 添加一级分类名称
			htmlBuilder.append("<td>").append(getCategoryName(product.getCategoryLevel1Id())).append("</td>");
			// 添加二级分类名称
			htmlBuilder.append("<td>").append(getCategoryName(product.getCategoryLevel2Id())).append("</td>");
			// 添加三级分类名称
			htmlBuilder.append("<td>").append(getCategoryName(product.getCategoryLevel3Id())).append("</td>");
			htmlBuilder.append("</tr>");
		}

		// 在这里添加HTML尾部，例如表格的结束标签
		htmlBuilder.append("</tbody>");
		htmlBuilder.append("</table>");

		return htmlBuilder.toString();
	}
	@RequestMapping("/productcategorylist.html")
	public String productcategorylist(Model model) {

		List<ProductCategory> ppclist = null;
		ppclist = productCategoryService.findProductCategories();

		model.addAttribute("ppclist", ppclist);
		return "productcategorylist";
	}

//	@RequestMapping("/productcategory.html")
//	public String getProductCategoryList(
//			Model model,
//			@RequestParam(value = "queryname", required = false) String queryName,
//			@RequestParam(value = "categoryLevel1Id", required = false) Long categoryLevel1Id,
//			@RequestParam(value = "type", required = false) Integer type,
//			@RequestParam(value = "pageIndex", required = false) Integer pageIndex) {
//		// 设置页面容量
//		int pageSize = Constants.pageSize;
//		// 页码为空默认分第一页
//		if (null == pageIndex) {
//			pageIndex = 1;
//		}
//		if (queryName == null) {
//			queryName = "";
//		}
//		PageInfo<ProductCategory> ppi = null;
//		List<ProductCategory> pc1List = null;
//		try {
//			ppi = productCategoryService.getProductCategories(queryName,
//					categoryLevel1Id, pageIndex, pageSize, type);
//			pc1List = productCategoryService.getRootCategories(0L);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		model.addAttribute("pi", ppi);
//		model.addAttribute("pc1List", pc1List);
//		model.addAttribute("queryName", queryName);
//		model.addAttribute("type", type);
//		model.addAttribute("categoryLevel1Id", categoryLevel1Id);
//		return "productcategorylistpage";
//	}

	@RequestMapping("/add.html")
	public String addproduct() {

		return "productadd";
	}

	@RequestMapping(value = "/addsave.html", method = RequestMethod.POST)
	public String doaddproduct(
			Product product,
			HttpSession session,
			HttpServletRequest request,
			@RequestParam(value = "attachs", required = false) MultipartFile attach) {
		String picPath = null;
		String errorInfo = null;
		boolean flag = true;
		String path = request.getSession().getServletContext()
				.getRealPath("statics" + File.separator + "uploadfiles");
		if (!attach.isEmpty()) {
			errorInfo = "uploadFileError";
			String oldFileName = attach.getOriginalFilename();// 原文件名
			String prefix = FilenameUtils.getExtension(oldFileName);// 原文件后缀
			int filesize = 500000;
			if (attach.getSize() > filesize) {// 上传大小不得超过 500k
				request.setAttribute(errorInfo, " * 上传大小不得超过 500k");
				flag = false;
			} else if (prefix.equalsIgnoreCase("jpg")
					|| prefix.equalsIgnoreCase("png")
					|| prefix.equalsIgnoreCase("jpeg")
					|| prefix.equalsIgnoreCase("pneg")) {// 上传图片格式不正确
				String fileName = System.currentTimeMillis()
						+ RandomUtils.nextInt(1000000) + "_product.jpg";
				File targetFile = new File(path, fileName);
				if (!targetFile.exists()) {
					targetFile.mkdirs();
				}
				// zhr:00005 删除了一部分对filrname的修改，原先的代码图讲filename存为绝对路径，实际上只需要文件名
				// 保存
				try {
					attach.transferTo(targetFile);
				} catch (Exception e) {
					e.printStackTrace();
					request.setAttribute(errorInfo, " * 上传失败！");
					flag = false;
				}
				picPath = path + File.separator + fileName;
			} else {
				request.setAttribute(errorInfo, " * 上传图片格式不正确");
				flag = false;
			}
		}

		if (flag) {
			product.setCreatedBy(((User) session
					.getAttribute(Constants.USER_SESSION)).getId());
			product.setCreationDate(new Date());
			product.setFileName(picPath);
			try {
				if (productService.save(product) == 1) {
					return "redirect:/sys/product/list.html";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "productadd";
	}

	@RequestMapping(value = "/delproduct.json", method = RequestMethod.GET)
	@ResponseBody
	public Object deluser(@RequestParam("id") String id) {
		System.out.println(id);
		HashMap<String, String> resultMap = new HashMap<String, String>();
		if (StringUtils.isNullOrEmpty(id)) {
			resultMap.put("delResult", "notexist");
		} else {
			try {
				if (productService.delete(Long.parseLong(id)) == 1)
					resultMap.put("delResult", "true");
				else
					resultMap.put("delResult", "false");
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return JSONArray.toJSONString(resultMap);
	}
	
	//查看
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") String id, Model model,
			HttpServletRequest request) {
		Product product = new Product();
		try {
			product = productService.findById(Long.parseLong(id));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (product.getFileName() != null
				&& !"".equals(product.getFileName())) {
			String[] paths = product.getFileName().split(
					"\\" + File.separator);
			product.setFileName(request.getContextPath()
					+ "/statics/uploadfiles/" + paths[paths.length - 1]);
		}

		model.addAttribute(product);
		return "productview";
	}

	@RequestMapping("/pclist.json")
	@ResponseBody
	// zhr: Long 改 Integer，因为数据库内的类型是int，用long类型不匹配会报错
	public Object getcategoryLevel(@RequestParam("cid") Integer cid) {
		List<ProductCategory> list = null;
		try {
			list = productCategoryService.getRootCategories(cid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("------" + JSONArray.toJSONString(list));
		return JSONArray.toJSONString(list);
	}
	
	//显示修改页面
	@RequestMapping(value = "/modify/{id}", method = RequestMethod.GET)
	public String getproductById(@PathVariable("id") String id, Model model,
			HttpServletRequest request) {
		Product product = new Product();
		try {
			product = productService.findById(Long.parseLong(id));
			if (product.getFileName() != null
					&& !"".equals(product.getFileName())) {
				String[] paths = product.getFileName().split(
						"\\" + File.separator);
				product.setFileName(request.getContextPath()
						+ "/statics/uploadfiles/" + paths[paths.length - 1]);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute(product);
		return "productmodify";
	}
	
	//处理修改操作
	@RequestMapping(value = "/modifysave.html", method = RequestMethod.POST)
	public String modifyproductSave(
			Product product,
			HttpSession session,
			HttpServletRequest request,
			@RequestParam(value = "attachs", required = false) MultipartFile[] attachs) {
		String fileName = null;
		String errorInfo = null;
		boolean flag = true;
		String path = request.getSession().getServletContext()
				.getRealPath("statics" + File.separator + "uploadfiles");
		if (attachs != null) {
			for (int i = 0; i < attachs.length; i++) {
				MultipartFile attach = attachs[i];
				if (!attach.isEmpty()) {
					if (i == 0) {
						errorInfo = "uploadFileError";
					} else if (i == 1) {
						errorInfo = "uploadOcError";
					}
					String oldFileName = attach.getOriginalFilename();// 原文件名
					String prefix = FilenameUtils.getExtension(oldFileName);// 原文件后缀
					int filesize = 500000;
					if (attach.getSize() > filesize) {// 上传大小不得超过 500k
						request.setAttribute(errorInfo, " * 上传大小不得超过 500k");
						flag = false;
					} else if (prefix.equalsIgnoreCase("jpg")
							|| prefix.equalsIgnoreCase("png")
							|| prefix.equalsIgnoreCase("jpeg")
							|| prefix.equalsIgnoreCase("pneg")) {// 上传图片格式不正确
						fileName = System.currentTimeMillis()
								+ RandomUtils.nextInt(1000000)
								+ "_Personal.jpg";
						File targetFile = new File(path, fileName);
						if (!targetFile.exists()) {
							targetFile.mkdirs();
						}
						// zhr: 删除了一部分对filename的修改，原先的代码试图将filename存为绝对路径，实际上只需要文件名
						// 保存
						try {
							attach.transferTo(targetFile);
						} catch (Exception e) {
							e.printStackTrace();
							request.setAttribute(errorInfo, " * 上传失败！");
							flag = false;
						}
					} else {
						request.setAttribute(errorInfo, " * 上传图片格式不正确");
						flag = false;
					}
				}
			}
		}
		if (flag) {
			product.setModifyBy(((User) session
					.getAttribute(Constants.USER_SESSION)).getId());
			product.setModifyDate(new Date());
			product.setFileName(fileName);
			try {
				if (productService.update(product)==1) {
					return "redirect:/sys/product/list.html";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "productmodify";
	}
	
	
	@RequestMapping(value = "/productcategory/delproduct.json", method = RequestMethod.GET)
	@ResponseBody
	public Object delproductcategory(@RequestParam("id") String id) {
		HashMap<String, String> resultMap = new HashMap<String, String>();
		if (StringUtils.isNullOrEmpty(id)) {
			resultMap.put("delResult", "notexist");
		} else {
			try {
				if (productCategoryService.delete(Integer.parseInt(id)) >= 1)
					resultMap.put("delResult", "true");
				else
					resultMap.put("delResult", "false");
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println(resultMap);
		return JSONArray.toJSONString(resultMap);
	}
	
	@RequestMapping(value = "/doaddpc", method = RequestMethod.POST)
	@ResponseBody
	public Object doaddpc(@RequestParam("id") String id,
			@RequestParam("name") String name,
			@RequestParam("parentId") String parentId,
			@RequestParam("type") Integer type) {
		ProductCategory productCategory = new ProductCategory();
		productCategory.setId(Long.parseLong(id));
		productCategory.setName(name);
		productCategory.setParentId(Long.parseLong(parentId));
		productCategory.setType(type);
		
		HashMap<String, String> resultMap = new HashMap<String, String>();
			try {
				if (productCategoryService.save(productCategory) == 1)
					resultMap.put("addResult", "true");
				else
					resultMap.put("addResult", "false");
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return JSONArray.toJSONString(resultMap);
	}
	
	/*//显示修改页面
	@RequestMapping(value = "/productcategory/modify/{id}", method = RequestMethod.GET)
	public String getproductcategoryById(@PathVariable String id, Model model,
			HttpServletRequest request) {
		ProductCategory productcategory = new ProductCategory();
		try {
			productcategory = productCategoryService.findById(Long.parseLong(id));
		}  catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute(productcategory);
		return "productcategorymodify";
	}
	
	//处理修改操作
	@RequestMapping(value = "/modifysave.html", method = RequestMethod.POST)
	public String modifyproductcategorySave( ProductCategory productcategory,
			HttpSession session, HttpServletRequest request ) {
			try {
				if (productCategoryService.update(productcategory)==1) {
					return "redirect:/sys/product/productcategory.html";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		return "productcategorymodify";
	}*/
}
