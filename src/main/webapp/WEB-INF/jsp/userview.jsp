<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>
<div class="content">
	<div class="container-fluid">
		<nav class="pull-left">
			<strong>你现在所在的位置是:</strong>&nbsp;&nbsp;&nbsp; <span> 用户管理页面 >>
				用户信息查看页面</span><br> <br>
		</nav>
	</div>

	<div class="row">
		<div class="col-lg-4 col-md-5">
			<div class="card card-user">
				<div class="image">
					<img
						src="${pageContext.request.contextPath }/statics/img/background.jpg"
						alt="..." />
				</div>
				<div class="content">
					<div class="author">
						<c:choose>
							<c:when test="${user.imgPath ==null || user.imgPath==''}">
								<img class="avatar border-white"
									src="${pageContext.request.contextPath }/statics/img/faces/face-1.jpg" />
							</c:when>
							<c:otherwise>
								<img class="avatar border-white" src="${user.imgPath}" />
							</c:otherwise>
						</c:choose>
						<h4 class="title">
							${user.userName }<br /> <a href="#"><small>@${user.userCode}</small>
							</a>
						</h4>
					</div>
					<p class="description text-center">
						${user.userDesc}
					</p>
				</div>
				<hr>
				<div class="text-center">
					<div class="row">
						<div class="col-md-3 col-md-offset-1">
							<h5>
								<c:if test="${user.gender == 2 }">男</c:if>
								<c:if test="${user.gender == 1 }">女</c:if>
								<br /> <small>性别</small>
							</h5>
						</div>
						<div class="col-md-3">
							<h5>
								${user.age}<br /> <small>年龄</small>
							</h5>
						</div>
						<div class="col-md-4">
							<h5>
								${user.userRoleName}<br /> <small>用户角色</small>
							</h5>
						</div>
					</div>
				</div>
			</div>

		</div>
		<div class="col-lg-8 col-md-7">
			<div class="card">
				<div class="header">
					<h4 class="title">
						用户信息 <small> User Information</small>
					</h4>
				</div>
				<div class="content">
					<p>
						<strong>用户编号：</strong><span>${user.userCode }</span>
					</p>
					<p>
						<strong>用户名称：</strong><span>${user.userName }</span>
					</p>
					<p>
						<strong>用户性别：</strong> <span> <c:if
								test="${user.gender == 2 }">男</c:if> <c:if
								test="${user.gender == 1 }">女</c:if> </span>
					</p>
					<p>
						<strong>出生日期：</strong><span><fmt:formatDate
								value="${user.birthday }" pattern="yyyy-MM-dd" /> </span>
					</p>
					<p>
						<strong>用户电话：</strong><span>${user.phone }</span>
					</p>
					<p>
						<strong>用户邮箱：</strong><span>${user.email}</span>
					</p>
					<p>
						<strong>用户地址：</strong><span>${user.address }</span>
					</p>
					<p>
						<strong>用户角色：</strong><span>${user.userRoleName}</span>
					</p>
					<p>
						<strong>用户简介：</strong><span>${user.userDesc}</span>
					</p>
					<p>
						<strong>个人证件照：</strong><span> <c:choose>
								<c:when test="${user.imgPath == null || user.imgPath == ''}"> 暂无 </c:when>
								<c:otherwise>
									<img src="${user.imgPath}" width="240px" />
								</c:otherwise>
							</c:choose> </span>
					</p>
					<div class="providerAddBtn">
						<input type="button" id="back" name="back" value="返回"
							class="btn btn-success btn-fill btn-wd">
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
	src="${pageContext.request.contextPath }/statics/js/userview.js"></script>