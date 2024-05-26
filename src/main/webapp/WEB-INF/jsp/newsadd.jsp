<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>

<div class="content">
    <div class="container-fluid">
        <nav class="pull-left">
            <strong>你现在所在的位置是:</strong>&nbsp;&nbsp;&nbsp; <span>新闻管理界面 >>
				新闻添加页面</span><br> <br>
        </nav>
    </div>
    <div class="container-fluid">
        <div class="content">
            <div class="container-fluid">
                <div class="row">
                    <div>
                        <div class="card">
                            <div class="content">

                                <form id="newsForm" name="newsForm" method="post"
                                      action="${pageContext.request.contextPath }/sys/news/addsave.html">
                                    <!--div的class 为error是验证错误，ok是验证成功-->
                                    <div class="row">
                                        <div class="form-group">
                                            <div class="col-sm-2">
                                                <label for="title">新闻标题：</label>
                                            </div>
                                            <div class="col-sm-10">
                                                <input type="text" name="title" class="text form-control border-input"
                                                       id="title" value="" >
                                                <!-- 放置提示信息 -->
                                                <font color="red"></font>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="form-group">
                                            <div class="col-sm-2">
                                                <label for="content">新闻内容：</label>
                                            </div>
                                            <div class="col-sm-10">
                                                <input type="text" name="content" id="content"
                                                       value="" class="form-control border-input"> <font color="red"></font>
                                            </div>
                                        </div>
                                    </div>
<%--                                    <div class="row">--%>
<%--                                        <div class="form-group">--%>
<%--                                            <div class="col-sm-2">--%>
<%--                                                <label for="createdBy">发布者：<label>--%>
<%--                                            </div>--%>
<%--                                            <div class="col-sm-10">--%>
<%--                                                <input type="text" name="createdBy" id="createdBy"--%>
<%--                                                       value="" class="form-control border-input"> <font color="red"></font>--%>
<%--                                            </div>--%>
<%--                                        </div>--%>
<%--                                    </div>--%>

                                    <div class="row">
                                        <div class="form-group">
                                            <div class="col-sm-offset-2 col-sm-10">
                                                <input type="button" name="add" id="add"  value="保存"
                                                       class="btn btn-info btn-fill btn-wd" /> <input
                                                    type="button" id="back" name="back" value="返回"
                                                    class="btn btn-success btn-fill btn-wd">
                                            </div>
                                        </div>
                                    </div>

                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/jsp/common/foot.jsp"%>
<script type="text/javascript">
    $(".sidebar-wrapper .nav li:eq(7)").addClass("active");
</script>
<script type="text/javascript"
        src="${pageContext.request.contextPath }/statics/js/newsadd.js"></script>