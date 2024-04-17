<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>
<div class="content">
	<div class="container-fluid">
		<nav class="pull-left">
			<strong>你现在所在的位置是:</strong>&nbsp;&nbsp;&nbsp; <span>角色管理页面 >> 角色添加页面</span><br> <br>
		</nav>
	</div>

	<div class="card container-fluid">
		<div class="header">
			<h4 class="title">角色添加</h4>
		</div>
		<div class="content">
			<form id="roleForm" name="roleForm" method="post"
				action="${pageContext.request.contextPath }/sys/role/addsave.html">
				<div class="row">
					<div class="form-group">
						<div class="col-sm-2">
							<label for="roleCode">角色编码：</label>
						</div>
						<div class="col-sm-10">
							<input type="text" name="roleCode" id="roleCode" class="form-control border-input" >
						<font color="red"></font>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group">
						<div class="col-sm-2">
							<label for="roleName">角色名称：</label>
						</div>
						<div class="col-sm-10">
							<input type="text" name="roleName" id="roleName"
								class="form-control border-input" >
							<font color="red"></font>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<input type="button" name="save" id="save" value="保存"
								class="btn btn-info btn-fill btn-wd" /> <input type="button"
								id="back" name="back" value="返回"
								class="btn btn-success btn-fill btn-wd">
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<%@include file="/WEB-INF/jsp/common/foot.jsp" %>
<script type="text/javascript">
	$(".sidebar-wrapper .nav li:eq(6)").addClass("active");
</script>
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/roleadd.js"></script>
