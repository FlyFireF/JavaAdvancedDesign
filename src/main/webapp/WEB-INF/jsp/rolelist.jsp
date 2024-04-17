<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>
<div class="content">
	<div class="container-fluid">
		<nav class="pull-left">
			<strong>你现在所在的位置是:</strong>&nbsp;&nbsp;&nbsp; <span>角色管理页面</span><br>
			<br>
		</nav>
	</div>
	<div class="container-fluid">
	    <div class="pull-right">
           		<a href="${pageContext.request.contextPath}/sys/role/add.html" class="btn btn-danger btn-fill btn-wd">添加角色</a><br><br>
        </div>
		<div class="row">
		
			<div class="col-md-12">
				<div class="card">
					<div class="content table-responsive table-full-width">
						<table class="table table-striped">
							<thead>
								<th width="10%">ID</th>
								<th width="25%">角色编码</th>
								<th width="25%">角色名称</th>
								<th width="20%">创建时间</th>
								<th width="20%">操作</th>
							</thead>
							<tbody>
								<c:forEach var="role" items="${roleList }" varStatus="status">
									<tr>
										<td><span>${role.id}</span></td>
										<td><span>${role.roleCode }</span></td>
										<td><span>${role.roleName }</span></td>
										<td><span><fmt:formatDate
													value="${role.creationDate}" pattern="yyyy-MM-dd" />
										</span></td>
										<td> 
											<a class="modifyRole btn btn-warning btn-sm" href="javascript:;" roleid=${role.id }
													rolename=${role.roleName }>修改</a>&nbsp;&nbsp; 
											<a class="deleteRole btn btn-success btn-sm" href="javascript:;"
													roleid=${role.id } rolename=${role.roleName }>删除</a>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<%@include file="/WEB-INF/jsp/common/foot.jsp" %>
<script type="text/javascript">
	$(".sidebar-wrapper .nav li:eq(6)").addClass("active");
</script>
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/rolelist.js"></script>
