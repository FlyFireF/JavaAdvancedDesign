<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="common/head.jsp"%>
<div class="content">
	<div class="container-fluid">
		<nav class="pull-left">
			<strong>你现在所在的位置是:</strong>&nbsp;&nbsp;&nbsp; <span>用户管理页面</span><br>
			<br>
		</nav>
	</div>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div class="card">
					<div class="content">
						<form class="form-inline" method="post"
							action="${pageContext.request.contextPath }/sys/user/list.html">
							<div class="row">
								<div class="col-md-3">
									<div class="form-group">
										<label>用户名 </label> <input type="text" name="queryname"
											class="form-control border-input" value="${queryUserName }"
											placeholder="请输入搜索用户名......">
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label for="queryUserRole">用 户 权 限 &nbsp;&nbsp;</label> <select
											class="form-control border-input" name="queryUserRole">
											<option value="0">- - - 请选择 - - -</option>
											<c:forEach var="role" items="${roleList}">
												<option
													<c:if test="${role.id == queryUserRole }">selected="selected"</c:if>
													value="${role.id}">${role.roleName}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<input type="hidden" name="pageIndex" value="1" />
								<div class="col-md-3">
									<div class="text-center">
										<button type="submit" class="btn btn-primary btn-fill btn-wd">搜&nbsp;&nbsp;&nbsp;&nbsp;索</button>
									</div>
								</div>
								<div class="col-md-3">
									<div class="text-center">
										<a class="btn btn-danger btn-fill btn-wd"
											href="${pageContext.request.contextPath}/sys/user/add.html">添加用户</a>
									</div>
								</div>
							</div>
							<div class="clearfix"></div>
						</form>
					</div>
					<div class="content table-responsive table-full-width">
						<table class="table table-striped">
							<thead>
								<th width="12%">用户编码</th>
								<th width="13%">用户名称</th>
								<th width="10%">性别</th>
								<th width="10%">年龄</th>
								<th width="15%">电话</th>
								<th width="15%">用户角色</th>
								<th width="25%">操作</th>
							</thead>
							<tbody>
								<c:forEach var="user" items="${pi.list}" varStatus="status">
									<tr>
										<td><span>${user.userCode }</span>
										</td>
										<td><span>${user.userName }</span>
										</td>
										<td><span> <c:if test="${user.gender==2}">男</c:if>
												<c:if test="${user.gender==1}">女</c:if> </span>
										</td>
										<td><span><c:if test="${null != user.age}">${user.age}</c:if></span>
										</td>
										<td><span>${user.phone}</span>
										</td>
										<td><span>${user.userRoleName}</span>
										</td>
										<td><span><a class="viewUser btn btn-info btn-xs"
												href="javascript:;" userid=${user.id}
												username=${user.userName} >查看</a>
										</span>&nbsp;&nbsp; <span><a
												class="modifyUser btn btn-warning btn-xs"
												href="javascript:;" userid=${user.id}
												username=${user.userName }>编辑</a>
										</span>&nbsp;&nbsp; <span><a
												class="deleteUser btn btn-success btn-xs"
												href="javascript:;" userid=${user.id}
												username=${user.userName}>删除</a>
										</span></td>
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
	</div>
</div>
<%@ include file="common/foot.jsp"%>
<script type="text/javascript">
	$(".sidebar-wrapper .nav li:eq(5)").addClass("active");
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/statics/js/userlist.js"></script>