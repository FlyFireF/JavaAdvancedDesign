var billObj;

//订单管理页面上点击删除按钮弹出删除框(billlist.jsp)
function deleteBill(obj){
	$.ajax({
		type:"GET",
		url:path+"/sys/bill/delbill.json",
		data:{id:obj.attr("billid")},
		dataType:"json",
		success:function(data){
			if(data.delResult == "true"){//删除成功：移除删除行
				obj.parents("tr").remove();
			}else if(data.delResult == "false"){//删除失败
				alert("对不起，删除订单【"+obj.attr("billcc")+"】失败");
			}else if(data.delResult == "notexist"){
				alert("对不起，订单【"+obj.attr("billcc")+"】不存在");
			}
		},
		error:function(data){
			alert("对不起，删除失败");
		}
	});
}


$(function(){
	$(".viewBill").on("click",function(){
		//将被绑定的元素（a）转换成jquery对象，可以使用jquery方法
		var obj = $(this);
		window.location.href=path+"/sys/bill/view/"+ obj.attr("billid");
	});
	
	$(".modifyBill").on("click",function(){
		var obj = $(this);
		window.location.href=path+"/sys/bill/modify/"+ obj.attr("billid");
	});

	$(".deleteBill").click(function(){
		billObj = $(this);
		var del = confirm("你确定要删除订单【"+billObj.attr("billcc")+"】吗？");
		if (del)  {
			deleteBill(billObj);
	    } else {
	       alert("你取消删除！");
	    }
	});
	
});