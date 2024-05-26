<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>
<div class="content">
    <div class="container-fluid">
        <nav class="pull-left">
            <strong>你现在所在的位置是:</strong>&nbsp;&nbsp;&nbsp; <span> 新闻管理界面 >>
				信息查看</span><br> <br>
        </nav>
    </div>
    <div class="container-fluid">
        <div class="row">
            <div class="card">
                <div class="content">
                    <p>
                        <strong>信息编号：</strong><span>${info.infoCode }</span>
                    </p>
                    <p>
                        <strong>信息内容：</strong><span>${info.content }</span>
                    </p>
                    <p>
                        <strong>发布者：</strong><span>${info.providerName }</span>
                    </p>
                    <p>
                        <strong>时间：</strong><span>${info.creationDate }</span>
                    </p>
                    <div >
                        <input type="button" id="back" name="back" value="返回" class="btn btn-success btn-fill btn-wd">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/jsp/common/foot.jsp"%>
<script type="text/javascript">
    $(".sidebar-wrapper .nav li:eq(8)").addClass("active");
</script>
<script type="text/javascript"
        src="${pageContext.request.contextPath }/statics/js/newsview.js"></script>
