var productcategoryObj;
var currentSelectedCategory;

//商品管理页面上点击删除按钮弹出删除框
function deleteproduct(obj) {
    $.ajax({
        type: "GET",
        url: path + "/sys/product/productcategory/delproduct.json",
        data: {id: obj.attr("pid")},
        dataType: "json",
        success: function (data) {
            if (data.delResult == "true") {//删除成功：移除删除行
                alert("删除商品类别成功");
                // zhr: 删除数据后同步删除页面上的元素和子标签对应的元素
                obj.parents("li").remove();
                $('li[pid=' + obj.attr("pid") + ']').remove();
                var childsp = $('[ppid=' + obj.attr("pid") + ']').parent();
                console.log(childsp);
                childsp.remove()
                // zhr: 删除一级标签后，刷新页面
                if (obj.attr('ppid') == 0) {
                    history.go(0);
                }

            } else if (data.delResult == "false") {//删除失败
                alert("对不起，删除商品【" + obj.attr("pname") + "】失败");
            } else if (data.delResult == "notexist") {
                alert("对不起，商品【" + obj.attr("pname") + "】不存在");
            }
        },
        error: function (data) {
            alert("对不起，删除失败");
        }
    });
}

$(function () {
    //通过jquery的class选择器（数组）
    $(".modify").on("click", function () {
        var obj = $(this);
        window.location.href = path + "/sys/product/productcategory/modify/" + obj.attr("pid");
    });

    $(".add").on("click", function () {
        //alert("add ======  "+$("#pp2id").val()+ " -- " + $("#ppcode").val()+ " -- " +$("#ppname").val());
        $.ajax({
            type: "POST",
            url: path + "/sys/product/doaddpc",
            data: {id: $("#ppcode").val(), name: $("#ppname").val(), parentId: $("#pp2id").val(), type: 3},
            success: function (data) {
                if (data.addResult == "true") {//添加成功
                    alert("添加商品类别成功");
                    location.href = path + "/sys/product/productcategorylist.html";
                } else if (data.addResult == "false") {//删除失败
                    alert("对不起，添加商品【" + $("#ppname").val() + "】失败");
                } else {
                    alert("对不起，操作异常");
                }
            },
            error: function (data) {
                alert("对不起，操作异常");
            }
        });
    });

    $(".addp1").on("click", function () {
        //alert("add ======  "+$("#pp2id").val()+ " -- " + $("#ppcode").val()+ " -- " +$("#ppname").val());
        var postData = {
            id: $("#p1CategoryId").val(),
            name: $("#p1CategoryName").val(),
            parentId: 0, // 一级类别没有父类别
            type: 1 // 标记为一级类别
        };

        $.ajax({
            type: "POST",
            url: path + "/sys/product/doaddpc",
            data: postData,
            success: function (data) {
                if (data.addResult == "true") {//添加成功
                    alert("添加一级商品类别成功");
                    location.href = path + "/sys/product/productcategorylist.html";
                } else if (data.addResult == "false") {//删除失败
                    alert("对不起，添加一级商品类别【" + $("#ppname").val() + "】失败");
                } else {
                    alert("对不起，操作异常");
                }
            },
            error: function (data) {
                alert("对不起，操作异常");
            }
        });
    });

    $(".addp2").on("click", function () {
        //alert("add ======  "+$("#pp2id").val()+ " -- " + $("#ppcode").val()+ " -- " +$("#ppname").val());
        var postData = {
            id: $("#p2CategoryId").val(),
            name: $("#p2CategoryName").val(),
            parentId: $("#parentId").val(), // 一级类别没有父类别
            type: 2 // 标记为一级类别
        };

        $.ajax({
            type: "POST",
            url: path + "/sys/product/doaddpc",
            data: postData,
            success: function (data) {
                if (data.addResult == "true") {//添加成功
                    alert("添加一级商品类别成功");
                    location.href = path + "/sys/product/productcategorylist.html";
                } else if (data.addResult == "false") {//删除失败
                    alert("对不起，添加一级商品类别【" + $("#ppname").val() + "】失败");
                } else {
                    alert("对不起，操作异常");
                }
            },
            error: function (data) {
                alert("对不起，操作异常");
            }
        });
    });

    $(".delete").on("click", function () {
        productcategoryObj = $(this);
        var del = confirm("你确定要删除商品【" + productcategoryObj.attr("pname") + "】吗？");
        if (del) {
            deleteproduct(productcategoryObj);
        } else {
            alert("你取消删除！");
        }
    });

    $(".addmodal").on("click", function () {
        var obj = $(this);
        $(".pp1id").val(obj.attr("ppid"));
        $(".pp1name").val(obj.attr("ppname"));
        $("#pp2id").val(obj.attr("pid"));
        $("#pp2id").next().val(obj.attr("pname"));
    });

    $(".pmenu button").on("click", function () {
        var action = $(this).text();
        var obj = $(this).closest('.dropdown').find('#dropdownMenuButton');
        if (action === '添加') {
            $(".pp1id").val(obj.attr("ppid"));
            $(".pp1name").val(obj.attr("ppname"));
            $("#pp2id").val(obj.attr("pid"));
            $("#pp2id").next().val(obj.attr("pname"));
            $("#addpp").modal("show");
        } else if (action === '删除') {
            var del = confirm("你确定要删除二级商品标签【" + obj.attr("pname") + "】吗？");
            if (del) {
                deleteproduct(obj);
            } else {
                alert("你取消删除！");
            }
        }
    });

    $("#deleteP1Category").on("click", function () {
        var currentPC1 = $($('li[role="presentation"].active'));
        var del = confirm("你确定要删除商品【" + currentPC1.attr("pname") + "】吗？");
        if (del) {
            deleteproduct(currentPC1);
        } else {
            alert("你取消删除！");
        }
    });
});