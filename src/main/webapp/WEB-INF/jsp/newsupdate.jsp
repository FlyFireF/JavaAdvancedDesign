<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>
<div class="content">
    <div class="container-fluid">
        <nav class="pull-left">
            <strong>你现在所在的位置是:</strong>&nbsp;&nbsp;&nbsp; <span> 新闻管理页面 >>
				新闻修改页面</span><br> <br>
        </nav>
    </div>
    <div class="container-fluid">
        <div class="row">
            <div class="card">
                <div class="content">
                    <form id="newsForm" name="newsForm" method="post"
                          action="${pageContext.request.contextPath }/sys/news/modifysave.html">
                        <div class="row">
                            <input type="hidden" name="id" value="${news.id }">
                            <!--div的class 为error是验证错误，ok是验证成功-->
                            <div class="col-md-6">
                                <label for="creationDate">创建时间：</label> <input type="text"
                                                                               name="creationDate" id="creationDate" class="form-control border-input"
                                                                               value="${news.creationDate }" readonly="readonly">
                            </div>
                            <div class="col-md-6">
                                <label for="title">新闻标题：</label> <input type="text"
                                                                                  name="title" id="title"
                                                                                  class="form-control border-input" value="${news.title }">
                                <font color="red"></font>
                            </div>
                            <div class="col-md-4">
                                <label for="content">新闻内容：</label> <input type="text"
                                                                                  name="content" id="content"
                                                                                  class="form-control border-input" value="${news.content }">
                                <font color="red"></font>
                            </div>
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
                        </div>
                    </form>
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