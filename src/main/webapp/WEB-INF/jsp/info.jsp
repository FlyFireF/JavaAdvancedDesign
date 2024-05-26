<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jsp/common/head.jsp" %>
<div class="content">
    <div class="container-fluid">
        <nav class="pull-left">
            <strong>你现在所在的位置是:</strong>&nbsp;&nbsp;&nbsp; <span>信息管理页面</span><br>
            <br>
        </nav>
    </div>
    <div class="container-fluid">
        <div class="content">
            <form class="form-inline" method="post"
                  action="${pageContext.request.contextPath }/sys/info/list.html">
                <input type="hidden" name="pageIndex" value="1"/>
                <div class="row">
                    <div class="col-md-12">
                        <div class="card card-plain">
                            <div class="content table-responsive table-full-width">
                                <table class="table table-hover">
                                    <thead>
                                    <th width="10%">信息代码</th>
                                    <th width="40%">信息内容</th>
                                    <th width="20%">发布者</th>
                                    <th width="10%">发布时间</th>
                                    <th width="20%">操作</th>

                                    </thead>
                                    <tbody>

                                    <c:forEach var="info" items="${pi.list}" varStatus="status">
                                        <tr>
                                            <td><span>${info.infoCode }</span>
                                            </td>
                                            <td><span>${info.content }</span>
                                            </td>
                                            <td><span>${info.providerName}</span>
                                            </td>
                                            <td><span> <fmt:formatDate
                                                    value="${info.creationDate}" pattern="yyyy-MM-dd"/> </span>
                                            </td>
                                        <td><span><a class="viewNews btn btn-info btn-xs"
                                                     href="javascript:" infoid=${info.id }
                                        >查看</a>&nbsp;&nbsp; <a
                                                class="deleteNews btn btn-success btn-xs"
                                                href="javascript:" infoid=${info.id }
                                                        infocc=${info.infoCode}>删除</a> </span>
                                        </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <c:import url="rollpage.jsp">
                            <c:param name="pi" value="${pi}"/>
                        </c:import>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/jsp/common/foot.jsp" %>
<script type="text/javascript">
    $(".sidebar-wrapper .nav li:eq(8)").addClass("active");
</script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/statics/js/infolist.js"></script>
