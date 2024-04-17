<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>
<div class="content">
	<div class="container-fluid">
		<nav class="pull-left">
			<strong>你现在所在的位置是:</strong>&nbsp;&nbsp;&nbsp; <span>密码修改页面</span><br>
			<br>
		</nav>
	</div>

	<div class="card container-fluid">
		<div class="header">
			<h4 class="title">
				用户密码修改 <small> Edit User PassWord</small>
			</h4>
		</div>
		<div class="content">
			<form id="userForm" name="userForm" method="post" class="form"
				action="${pageContext.request.contextPath }/sys/user/pwdsave.html">
				<!--div的class 为error是验证错误，ok是验证成功-->
				<div class="row info">${message}</div>
				<div class="row">
					<div class="form-group">
						<div class="col-sm-2">
							<label for="oldPassword">旧密码：</label>
						</div>
						<div class="col-sm-10">
							<input type="password" name="oldpassword" id="oldpassword"
								value="" class="form-control border-input"> 
							<font color="red"></font>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group">
						<div class="col-sm-2">
							<label for="newPassword">新密码：</label>
						</div>
						<div class="col-sm-10">
							<input type="password" name="newpassword" id="newpassword"
								value="" class="form-control border-input"> 
							<font color="red"></font>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group">
						<div class="col-sm-2">
							<label for="reNewPassword">确认新密码：</label>
						</div>
						<div class="col-sm-10">
							<input type="password" name="rnewpassword" id="rnewpassword"
								value="" class="form-control border-input"> <font
								color="red"></font>
						</div>
					</div>
				</div>
				<div class="row providerAddBtn">
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<input type="button" name="save" id="save" value="保存"
								class="btn btn-info btn-fill btn-wd">
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<%@include file="/WEB-INF/jsp/common/foot.jsp"%>
<script type="text/javascript">
	$(".sidebar-wrapper .nav li:eq(5)").addClass("active");
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/statics/js/pwdmodify.js"></script>