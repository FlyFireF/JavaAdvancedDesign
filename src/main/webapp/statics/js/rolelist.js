var roleObj;

function deleteRole(obj){
	// 调用后端接口获取该角色下的用户数量
	$.ajax({
		type:"GET",
		url:path+"/sys/role/getUserCount.json",
		data:{id:obj.attr("roleid")},
		dataType:"json",
		success:function(data){
			if(data.userCount > 0){
				alert("该角色下有用户，不能删除");
			}else{
				// 调用原来的删除角色接口
				$.ajax({
					type:"GET",
					url:path+"/sys/role/delrole.json",
					data:{id:obj.attr("roleid")},
					dataType:"json",
					success:function(data){
						if(data.delResult == "true"){//删除成功：移除删除行
							obj.parents("tr").remove();
						}else if(data.delResult == "false"){//删除失败
							("对不起，删除用户【"+obj.attr("rolename")+"】失败");
						}else if(data.delResult == "notexist"){
							("对不起，用户【"+obj.attr("rolename")+"】不存在");
						}
					},
					error:function(data){
						("对不起，删除失败");
					}
				});
			}
		},
		error:function(data){
			("获取用户数量失败");
		}
	});
}
//
// function deleteRole(obj){
// 	$.ajax({
// 		type:"GET",
// 		url:path+"/sys/role/delrole.json",
// 		data:{id:obj.attr("roleid")},
// 		dataType:"json",
// 		success:function(data){
// 			if(data.delResult == "true"){//删除成功：移除删除行
// 				obj.parents("tr").remove();
// 			}else if(data.delResult == "false"){//删除失败
// 				alert("对不起，删除用户【"+obj.attr("rolename")+"】失败");
// 			}else if(data.delResult == "notexist"){
// 				alert("对不起，用户【"+obj.attr("rolename")+"】不存在");
// 			}
// 		},
// 		error:function(data){
// 			alert("对不起，删除失败");
// 		}
// 	});
// }

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