<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>

<div class="content">
	<div class="container-fluid">
		<nav class="pull-left">
			<strong>你现在所在的位置是:</strong>&nbsp;&nbsp;&nbsp; <span>用户管理页面 >>
				用户修改页面</span><br> <br>
		</nav>
	</div>
	<div class="container-fluid">
		<div class="row">
			<div>
				<div class="card">
					<div class="header">
						<h4 class="title">
							修改用户信息 <small> Edit User Infomation</small>
						</h4>
					</div>
					<div class="content">
						<form id="userForm" name="userForm" method="post"
							action="${pageContext.request.contextPath }/sys/user/modifysave.html"
							enctype="multipart/form-data">
							<input type="hidden" name="id" value="${user.id }"/>
							<div class="row">
								<div class="col-md-4">
									<div class="form-group">
										<label for="userName">用户名 <small> UserName</small> </label> <input
											type="text" class="form-control border-input" name="userName"
											id="userName" value="${user.userName }"><font color="red"></font>
									</div>
								</div>
								<div class="col-md-5">
									<div class="form-group">
										<label for="userCode">用户编码 <small> UserCode</small> </label> <input
											type="text" class="form-control border-input"
											value="${user.userCode }" readonly="readonly">
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>性别 <small> Gender</small> </label> <select
											name="gender" id="gender" class="form-control border-input">
											<c:choose>
												<c:when test="${user.gender == 1 }">
													<option value="1" selected="selected">女</option>
													<option value="2">男</option>
												</c:when>
												<c:otherwise>
													<option value="1">女</option>
													<option value="2" selected="selected">男</option>
												</c:otherwise>
											</c:choose>
										</select>
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label for="exampleInputEmail1">邮箱地址 <small>
												Email Address</small> </label> <input type="email"
											class="form-control border-input" name="email" id="email"
											value="${user.email }">
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label>手机号码 <small> Phone Number</small> </label> <input
											type="text" class="form-control border-input" name="phone"
											id="phone" value="${user.phone }"><font color="red"></font>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
										<label>地 址 <small> Address</small> </label> <input type="text"
											class="form-control border-input" name="address" id="address"
											value="${user.address }">
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label>出生日期 <small> Birthday</small> </label> <input
											type="date" class="form-control border-input" id="birthday"
											name="birthday"
											value="<fmt:formatDate value="${user.birthday }" pattern="yyyy-MM-dd"/>"><font color="red"></font>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label>用户角色 <small> UserRole</small> </label>
										<!-- 列出所有的角色分类 -->
										<input type="hidden" value="${user.userRole }" id="rid" /> <select
											class="form-control border-input" name="userRole"
											id="userRole">
										</select><font color="red"></font>
									</div>
								</div>

							</div>
							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
										<label>用户照片 <small> Photograph</small> </label>
										<c:choose>
											<c:when test="${user.imgPath == null || user.imgPath == ''}">
												<input type="hidden" id="errorinfo"
													value="${uploadFileError}" />
												<input type="file" name="attachs" id="m_imgPath" />
												<font color="red"></font>
											</c:when>
											<c:otherwise>
												<img src="${user.imgPath}" width="300px"/>
											</c:otherwise>
										</c:choose>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
										<label>个人简介</label>
										<textarea rows="5" class="form-control border-input"
											name="userDesc">${user.userDesc}</textarea>
									</div>
								</div>
							</div>
							<div class="text-center">
								<input type="button" name="save" id="save" value="修 改 用 户"
									class="btn btn-info btn-fill btn-wd" /> <input type="button"
									id="back" name="back" value="返  回"
									class="btn btn-success btn-fill btn-wd" />
							</div>
							<div class="clearfix"></div>
						</form>
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
	src="${pageContext.request.contextPath }/statics/js/usermodify.js"></script>
