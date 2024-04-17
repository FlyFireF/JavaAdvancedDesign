var productcategoryObj;

//商品管理页面上点击删除按钮弹出删除框
function deleteproduct(obj){
	$.ajax({
		type:"GET",
		url:path+"/sys/product/productcategory/delproduct.json",
		data:{id:obj.attr("pid")},
		dataType:"json",
		success:function(data){
			if(data.delResult == "true"){//删除成功：移除删除行
				alert("删除商品类别成功");
				obj.parents("li").remove();
			}else if(data.delResult == "false"){//删除失败
				alert("对不起，删除商品【"+obj.attr("pname")+"】失败");
			}else if(data.delResult == "notexist"){
				alert("对不起，商品【"+obj.attr("pname")+"】不存在");
			}
		},
		error:function(data){
			alert("对不起，删除失败");
		}
	});
}

$(function(){
	//通过jquery的class选择器（数组）
	$(".modify").on("click",function(){
		var obj = $(this);
		window.location.href=path+"/sys/product/productcategory/modify/"+ obj.attr("pid");
	});
	
	$(".addmodal").on("click",function(){
		var obj = $(this);
		//alert(obj.attr("pid")+ " -- " + obj.attr("pname")+ " -- " +obj.attr("ppid")+" --- "+ obj.attr("ppname"));
		$("#pp1id").val(obj.attr("ppid"));
		$("#pp1id").next().val(obj.attr("ppname"));
		$("#pp2id").val(obj.attr("pid"));
		$("#pp2id").next().val(obj.attr("pname"));
	});
	
	$(".add").on("click",function(){
		//alert("add ======  "+$("#pp2id").val()+ " -- " + $("#ppcode").val()+ " -- " +$("#ppname").val());
		$.ajax({
			type:"POST",
			url:path+"/sys/product/doaddpc",
			data:{id:$("#ppcode").val(),name:$("#ppname").val(), parentId:$("#pp2id").val(), type:3},
			success:function(data){
				if(data.addResult == "true"){//添加成功
					alert("添加商品类别成功");
					location.href=path+"/sys/product/productcategorylist.html";
				}else if(data.addResult == "false"){//删除失败
					alert("对不起，添加商品【"+$("#ppname").val()+"】失败");
				}else{
					alert("对不起，操作异常");
				}
			},
			error:function(data){
				alert("对不起，操作异常");
			}
		});
	});
	
	$(".delete").on("click",function(){
		productcategoryObj = $(this);
		var del = confirm("你确定要删除商品【"+productcategoryObj.attr("pname")+"】吗？");
		if (del)  {
			deleteproduct(productcategoryObj);
	    } else {
	       alert("你取消删除！");
	    }
	});
});