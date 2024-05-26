
function deleteInfo(obj){
    $.ajax({
        type:"GET",
        url:path+"/sys/info/del.json",
        data:{id:obj.attr("infoid")},
        dataType:"json",
        success:function(data){
            if(data.valueOf() > 0){//删除成功：移除删除行
                obj.parents("tr").remove();
            }else{//删除失败
                alert("对不起，删除订单【"+obj.attr("infocc")+"】失败");
            }
        },
        error:function(data){
            alert("对不起，删除失败");
        }
    });
}
$(function(){
    $(".viewNews").on("click",function(){
        //将被绑定的元素（a）转换成jquery对象，可以使用jquery方法
        var obj = $(this);
        window.location.href=path+"/sys/info/view/"+ obj.attr("infoid");
    });

    $(".deleteNews").click(function(){
        var obj = $(this);
        var del = confirm("你确定要删除新闻【"+obj.attr("infocc")+"】吗？");
        if (del)  {
            deleteInfo(obj);
        } else {
            alert("你取消删除！");
        }
    });

});