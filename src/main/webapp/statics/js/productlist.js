var productObj;

//商品管理页面上点击删除按钮弹出删除框(productlist.jsp)
function deleteproduct(obj){
	$.ajax({
		type:"GET",
		url:path+"/sys/product/delproduct.json",
		data:{id:obj.attr("pid")},
		dataType:"json",
		success:function(data){
			if(data.delResult == "true"){//删除成功：移除删除行
				obj.parents("tr").remove();
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
	//对每个class为viewproduct的元素进行动作绑定（click）
	$(".viewproduct").on("click",function(){
		//将被绑定的元素（a）转换成jquery对象，可以使用jquery方法
		var obj = $(this);
		window.location.href=path+"/sys/product/view/"+ obj.attr("pid");
	});
	
	$(".modifyproduct").on("click",function(){
		var obj = $(this);
		window.location.href=path+"/sys/product/modify/"+ obj.attr("pid");
	});

	$(".deleteproduct").on("click",function(){
		productObj = $(this);
		var del = confirm("你确定要删除商品【"+productObj.attr("pname")+"】吗？");
		if (del)  {
			deleteproduct(productObj);
	    } else {
	       alert("你取消删除！");
	    }
	});
	
});