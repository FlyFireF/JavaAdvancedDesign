<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>

<div class="content">
	<div class="container-fluid">
		<nav class="pull-left">
			<strong>你现在所在的位置是:</strong>&nbsp;&nbsp;&nbsp; <span>用户管理页面 >>
				用户添加页面</span><br> <br>
		</nav>
	</div>
	<div class="container-fluid">
		<div class="content">
			<div class="container-fluid">
				<div class="row">
					<div>
						<div class="card">
							<div class="header">
								<h4 class="title">
									添加用户信息 <small> Add User Infomation</small>
								</h4>
							</div>
							<div class="content">
								<form id="userForm" name="userForm" method="post"
									action="${pageContext.request.contextPath }/sys/user/addsave.html"
									enctype="multipart/form-data">

									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
												<label>用户名 <small> UserName</small> </label> <input
													type="text" class="form-control border-input"
													placeholder="请输入用户名" name="userName" id="userName"><font
													color="red"></font>
											</div>
										</div>
										<div class="col-md-5">
											<div class="form-group">
												<label for="exampleInputEmail1">用户编码 <small>
														UserCode</small> </label> <input type="text"
													class="form-control border-input" placeholder="请输入用户编码"
													name="userCode" id="userCode"><font color="red"></font>
											</div>
										</div>
										<div class="col-md-3">
											<div class="form-group">
												<label>性别 <small> Gender</small> </label> <select
													class="form-control border-input" name="gender" id="gender">
													<option value="">- - - 请选择 - - -</option>
													<option value="2">男</option>
													<option value="1">女</option>
												</select>
											</div>
										</div>
									</div>

									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label for="exampleInputEmail1">邮箱地址 <small>
														Email Address</small> </label> <input type="email"
													class="form-control border-input" placeholder="Email"
													name="email" id="email">
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label>手机号码 <small> Phone Number</small> </label> <input
													type="text" class="form-control border-input"
													placeholder="Phone" name="phone" id="phone"><font
													color="red"></font>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label>用户密码 <small>Password</small> </label> <input
													type="password" class="form-control border-input"
													placeholder="请输入6~20位密码" name="userPassword"
													id="userPassword"><font color="red"></font>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label>确认密码 <small> Confirm password</small> </label> <input
													type="password" class="form-control border-input"
													placeholder="请输入确认密码" name="ruserPassword"
													id="ruserPassword"><font color="red"></font>
											</div>
										</div>
									</div>

									<div class="row">
										<div class="col-md-12">
											<div class="form-group">
												<label>地 址 <small> Address</small> </label> <input
													type="text" class="form-control border-input"
													placeholder="Home Address" name="address" id="address">
											</div>
										</div>
									</div>

									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
												<label>出生日期 <small> Birthday</small> </label> <input
													type="date" class="form-control border-input" id="birthday"
													name="birthday"><font color="red"></font>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label>用户角色 <small> UserRole</small> </label> <select
													class="form-control border-input" name="userRole"
													id="userRole">
												</select><font color="red"></font>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label>用户照片 <small> Photograph</small> </label> <input
													type="file" class="form-control border-input"
													placeholder="请选择上传照片" name="attachs" id="a_idPicPath">
											</div>
										</div>
									</div>

									<div class="row">
										<div class="col-md-12">
											<div class="form-group">
												<label>个人简介</label>
												<textarea rows="5" class="form-control border-input"
													placeholder="Here can be your description" name="userDesc"
													id="userDesc"></textarea>
											</div>
										</div>
									</div>
									<div class="text-center">
										<button id="add" class="btn btn-info btn-fill btn-wd">新增用户</button>
										<button id="back" class="btn btn-success btn-fill btn-wd">返回</button>
									</div>
									<div class="clearfix"></div>
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
	$(".sidebar-wrapper .nav li:eq(5)").addClass("active");
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/statics/js/useradd.js"></script>
