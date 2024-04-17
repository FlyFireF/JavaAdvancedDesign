package com.flyfiref.dsscm.controller;

import com.flyfiref.dsscm.pojo.Product;
import com.flyfiref.dsscm.pojo.ProductCategory;
import com.flyfiref.dsscm.pojo.User;
import com.flyfiref.dsscm.service.ProductCategoryService;
import com.flyfiref.dsscm.service.ProductService;
import com.flyfiref.dsscm.tools.Constants;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.mysql.cj.util.StringUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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
			pcList = productCategoryService.getRootCategories(0L);
		} catch (Exception e) {
			e.printStackTrace();
		}

		model.addAttribute("pi", ppi);
		model.addAttribute("pcList", pcList);
		model.addAttribute("queryName", queryName);
		model.addAttribute("categoryLevel1Id", categoryLevel1Id);
		return "productlist";
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
	public Object getcategoryLevel(@RequestParam("cid") Long cid) {
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
						// 保存
						try {
							attach.transferTo(targetFile);
						} catch (Exception e) {
							e.printStackTrace();
							request.setAttribute(errorInfo, " * 上传失败！");
							flag = false;
						}
						if (i == 0) {
							fileName = path + File.separator
									+ fileName;
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
				if (productCategoryService.delete(Long.parseLong(id)) == 1)
					resultMap.put("delResult", "true");
				else
					resultMap.put("delResult", "false");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
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
