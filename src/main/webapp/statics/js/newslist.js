// var newsObj;

function deleteNews(obj){
    $.ajax({
        type:"GET",
        url:path+"/sys/news/del.json",
        data:{id:obj.attr("newsid")},
        dataType:"json",
        success:function(data){
            if(data.valueOf() > 0){//删除成功：移除删除行
                obj.parents("tr").remove();
            }else{//删除失败
                alert("对不起，删除订单【"+obj.attr("newscc")+"】失败");
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
        window.location.href=path+"/sys/news/view/"+ obj.attr("newsid");
    });

    $(".modifyNews").on("click",function(){
        var obj = $(this);
        window.location.href=path+"/sys/news/modify/"+ obj.attr("newsid");
    });

    $(".deleteNews").click(function(){
        var newsObj = $(this);
        var del = confirm("你确定要删除新闻【"+newsObj.attr("newscc")+"】吗？");
        if (del)  {
            deleteNews(newsObj);
        } else {
            alert("你取消删除！");
        }
    });

});