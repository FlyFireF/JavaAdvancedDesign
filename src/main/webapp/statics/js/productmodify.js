var pname = null;
var price = null;
var stock = null;
var placement = null;
var cl1 = null;
var cl2 = null;
var cl3 = null;
var saveBtn = null;
var backBtn = null;

$(function() {
	pname = $("#pname");
	price = $("#price");
	stock = $("#stock");
	placement = $("#placement");
	cl1 = $("#categoryLevel1Id");
	cl2 = $("#categoryLevel2Id");
	cl3 = $("#categoryLevel3Id");
	saveBtn = $("#save");
	backBtn = $("#back");
	// 初始化的时候，要把所有的提示信息变为：* 以提示必填项，更灵活，不要写在页面上
	pname.next().html("*");
	price.next().html("*");
	stock.next().html("*");
	placement.next().html("*");
	cl1.next().html("*");
	cl2.next().html("*");
	cl3.next().html("*");

	$.ajax({
		type : "get",// 请求类型
		url : path + "/sys/product/pclist.json",// 请求的url
		data : {
			cid : "0"
		},// 请求参数
		dataType : "json",// ajax接口（请求url）返回的数据类型
		success : function(data) {// data：返回数据（json对象）
			if (data != null) {
				cl1.html("");
				var options = "<option value=\"\">请选择</option>";
				var ck1 = $("#ct1").val();
				for ( var i = 0; i < data.length; i++) {
					if (ck1 != null && ck1 != undefined && data[i].id == ck1) {
						options += "<option selected=\"selected\" value=\""
								+ data[i].id + "\" >" + data[i].name
								+ "</option>";
					} else {
						options += "<option value=\"" + data[i].id + "\">"
								+ data[i].name + "</option>";
					}
				}
				cl1.html(options);
				cl2.html("<option selected=\"selected\" value=\""
						+ $("#ct2").val() + "\">" + $("#ct2").attr("vv")
						+ "</option>");
				cl3.html("<option selected=\"selected\" value=\""
						+ $("#ct3").val() + "\">" + $("#ct3").attr("vv")
						+ "</option>");
			}
		},
		error : function(data) {// 当访问时候，404，500 等非200的错误状态码
			validateTip(userRole.next(), {
				"color" : "red"
			}, imgNo + " 获取商品类别列表error", false);
		}
	});

	cl1.change(function() {
		$.ajax({
			type : "get",// 请求类型
			url : path + "/sys/product/pclist.json",// 请求的url
			data : {
				cid : cl1.val()
			},// 请求参数
			dataType : "json",// ajax接口（请求url）返回的数据类型
			success : function(data) {// data：返回数据（json对象）
				if (data != null) {
					cl2.html("");
					var options = "<option value=\"\">请选择</option>";
					for ( var i = 0; i < data.length; i++) {
						options += "<option value=\"" + data[i].id + "\">"
								+ data[i].name + "</option>";
					}
					cl2.html(options);
					cl3.html("");
				}
			},
			error : function(data) {// 当访问时候，404，500 等非200的错误状态码
				validateTip(userRole.next(), {
					"color" : "red"
				}, imgNo + " 获取商品类别列表error", false);
			}
		});
	});

	cl2.change(function() {
		$.ajax({
			type : "get",// 请求类型
			url : path + "/sys/product/pclist.json",// 请求的url
			data : {
				cid : cl2.val()
			},// 请求参数
			dataType : "json",// ajax接口（请求url）返回的数据类型
			success : function(data) {// data：返回数据（json对象）
				if (data != null) {
					cl3.html("");
					var options = "<option value=\"\">请选择</option>";
					for ( var i = 0; i < data.length; i++) {
						options += "<option value=\"" + data[i].id + "\">"
								+ data[i].name + "</option>";
					}
					cl3.html(options);
				}
			},
			error : function(data) {// 当访问时候，404，500 等非200的错误状态码
				validateTip(userRole.next(), {
					"color" : "red"
				}, imgNo + " 获取商品类别列表error", false);
			}
		});
	});
	/*
	 * 验证 失焦\获焦 jquery的方法传递
	 */
	pname.on("blur", function() {
		if (pname.val() != null && pname.val() != "") {
			validateTip(pname.next(), {
				"color" : "green"
			}, imgYes, true);
		} else {
			validateTip(pname.next(), {
				"color" : "red"
			}, imgNo + " 商品名不能为空，请重新输入", false);
		}
	}).on("focus", function() {
		// 显示友情提示
		validateTip(pname.next(), {
			"color" : "#666666"
		}, "* 请输入商品名", false);
	}).focus();

	price.on("focus", function() {
		validateTip(price.parent().next(), {
			"color" : "#666666"
		}, "* 请输入单价", false);
	}).on("blur", function() {
		if (price.val() != null && price.val() != "") {
			validateTip(price.parent().next(), {
				"color" : "green"
			}, imgYes, true);
		} else {
			validateTip(price.parent().next(), {
				"color" : "red"
			}, imgNo + " 单价不能为空，请重新输入", false);
		}

	});

	stock.on("focus", function() {
		validateTip(stock.next(), {
			"color" : "#666666"
		}, "* 请输入数量", false);
	}).on("blur", function() {
		if (stock.val() != null && stock.val() != "") {
			validateTip(stock.next(), {
				"color" : "green"
			}, imgYes, true);
		} else {
			validateTip(stock.next(), {
				"color" : "red"
			}, imgNo + " 数量不能为空，请重新输入", false);
		}

	});

	placement.on("focus", function() {
		validateTip(placement.next(), {
			"color" : "#666666"
		}, "* 请输入摆放位置", false);
	}).on("blur", function() {
		if (placement.val() != null && placement.val() != "") {
			validateTip(placement.next(), {
				"color" : "green"
			}, imgYes, true);
		} else {
			validateTip(placement.next(), {
				"color" : "red"
			}, imgNo + " 摆放位置不能为空，请重新输入", false);
		}
	});
	cl1.on("focus", function() {
		validateTip(cl1.next(), {
			"color" : "#666666"
		}, "* 请选择一级分类", false);
	}).on("blur", function() {
		if (cl1.val() != null && cl1.val() != "") {
			validateTip(cl1.next(), {
				"color" : "green"
			}, imgYes, true);
		} else {
			validateTip(cl1.next(), {
				"color" : "red"
			}, imgNo + " 一级分类不能为空，请重新选择", false);
		}
	});

	cl2.on("focus", function() {
		validateTip(cl2.next(), {
			"color" : "#666666"
		}, "* 请选择二级分类", false);
	}).on("blur", function() {
		if (cl2.val() != null && cl2.val() != "") {
			validateTip(cl2.next(), {
				"color" : "green"
			}, imgYes, true);
		} else {
			validateTip(cl2.next(), {
				"color" : "red"
			}, imgNo + " 二级分类不能为空，请重新选择", false);
		}
	});

	cl3.on("focus", function() {
		validateTip(cl3.next(), {
			"color" : "#666666"
		}, "* 请选择三级分类", false);
	}).on("blur", function() {
		if (cl3.val() != null && cl3.val() != "") {
			validateTip(cl3.next(), {
				"color" : "green"
			}, imgYes, true);
		} else {
			validateTip(cl3.next(), {
				"color" : "red"
			}, imgNo + " 三级分类不能为空，请重新选择", false);
		}
	});
	saveBtn.bind("click", function() {
		if (pname.attr("validateStatus") != "true") {
			name.blur();
		}/*
			 * else if(price.attr("validateStatus") != "true"){ price.blur(); }
			 */
		else if (stock.attr("validateStatus") != "true") {
			stock.blur();
		} else if (placement.attr("validateStatus") != "true") {
			placement.blur();
		} else if (cl1.attr("validateStatus") != "true") {
			cl1.blur();
		} else if (cl2.attr("validateStatus") != "true") {
			cl2.blur();
		} else if (cl3.attr("validateStatus") != "true") {
			cl3.blur();
		} else {
			if (confirm("是否确认提交数据")) {
				$("#productForm").submit();
			}
		}
	});

	backBtn.on("click", function() {
		if (referer != undefined && null != referer && "" != referer
				&& "null" != referer && referer.length > 4) {
			window.location.href = referer;
		} else {
			history.back(-1);
		}
	});
});