var title = null;
var content = null;
// var createdBy = null;
var addBtn = null;
var backBtn = null;



$(function(){
    title = $("#title");
    content = $("#content");
    // createdBy = $("#createdBy");
    addBtn = $("#add");
    backBtn = $("#back");
    //初始化的时候，要把所有的提示信息变为：* 以提示必填项，更灵活，不要写在页面上
    title.next().html("*");
    content.next().html("*");
    // createdBy.next().html("*");

    /*
     * 验证
     * 失焦\获焦
     * jquery的方法传递
     */
    title.on("blur",function(){
        if(title.val() != null && title.val() !== ""){
            validateTip(title.next(),{"color":"green"},imgYes,true);
        }else{
            validateTip(title.next(),{"color":"red"},imgNo+" 标题不能为空，请重新输入",false);
        }
    }).on("focus",function(){
        //显示友情提示
        validateTip(title.next(),{"color":"#666666"},"* 请输入新闻标题",false);
    }).focus();

    content.on("focus",function(){
        validateTip(content.next(),{"color":"#666666"},"* 请输入新闻内容",false);
    }).on("blur",function(){
        if(content.val() != null && content.val() !== ""){
            validateTip(content.next(),{"color":"green"},imgYes,true);
        }else{
            validateTip(content.next(),{"color":"red"},imgNo+" 新闻内容不能为空，请重新输入",false);
        }

    });

    // createdBy.on("focus",function(){
    //     validateTip(createdBy.next(),{"color":"#666666"},"* 请输入发布者",false);
    // }).on("blur",function(){
    //     if(createdBy.val() != null && createdBy.val() !== ""){
    //         validateTip(createdBy.next(),{"color":"green"},imgYes,true);
    //     }else{
    //         validateTip(createdBy.next(),{"color":"red"},imgNo+" 发布者不能为空，请重新输入",false);
    //     }
    //
    // });


    addBtn.on("click",function(){
        if(title.attr("validateStatus") !== "true"){
            title.blur();
        }else if(content.attr("validateStatus") !== "true"){
            content.blur();
        }
        // else if(createdBy.attr("validateStatus") !== "true"){
        //     createdBy.blur();
        // }
        else{
            if(confirm("是否确认提交数据")){
                $("#newsForm").submit();
            }
        }
    });

    backBtn.on("click",function(){
        if(referer !== undefined && "" !== referer && "null" !== referer
            && referer.length > 4){
            window.location.href = referer;
        }else{
            history.back(-1);
        }
    });
});