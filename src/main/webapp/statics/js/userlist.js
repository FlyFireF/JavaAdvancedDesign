var userObj;

//用户管理页面上点击删除按钮弹出删除框(userlist.jsp)
function deleteUser(obj){
	$.ajax({
		type:"GET",
		url:path+"/sys/user/deluser.json",
		data:{id:obj.attr("userid")},
		dataType:"json",
		success:function(data){
			if(data.delResult == "true"){//删除成功：移除删除行
				obj.parents("tr").remove();
			}else if(data.delResult == "false"){//删除失败
				alert("对不起，删除用户【"+obj.attr("username")+"】失败");
			}else if(data.delResult == "notexist"){
				alert("对不起，用户【"+obj.attr("username")+"】不存在");
			}
		},
		error:function(data){
			alert("对不起，删除失败");
		}
	});
}


$(function(){
	//通过jquery的class选择器（数组）
	//对每个class为viewUser的元素进行动作绑定（click）
	$(".viewUser").on("click",function(){
		//将被绑定的元素（a）转换成jquery对象，可以使用jquery方法
		var obj = $(this);
		window.location.href=path+"/sys/user/view/"+ obj.attr("userid");
	});
	
	$(".modifyUser").on("click",function(){
		var obj = $(this);
		window.location.href=path+"/sys/user/modify/"+ obj.attr("userid");
	});

	$(".deleteUser").on("click",function(){
		userObj = $(this);
		var del = confirm("你确定要删除用户【"+userObj.attr("username")+"】吗？");
		if (del)  {
			deleteUser(userObj);
	    } else {
	       alert("你取消删除！");
	    }
	});
	
});