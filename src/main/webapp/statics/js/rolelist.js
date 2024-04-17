var roleObj;

function deleteRole(obj){
	$.ajax({
		type:"GET",
		url:path+"/sys/role/delrole.json",
		data:{id:obj.attr("roleid")},
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
	
	$(".modifyRole").on("click",function(){
		var obj = $(this);
		window.location.href=path+"/sys/role/modify/"+ obj.attr("roleid");
	});

	$(".deleteRole").on("click",function(){
		roleObj = $(this);
		var del = confirm("你确定要删除角色【"+roleObj.attr("rolename")+"】吗？");
		if (del)  {
			deleteRole(roleObj);
	    } else {
	       alert("你取消删除！");
	    }
	});
	
});