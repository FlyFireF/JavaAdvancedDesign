<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>
<div class="content">
	<div class="container-fluid">
		<nav class="pull-left">
			<strong>你现在所在的位置是:</strong>&nbsp;&nbsp;&nbsp; <span> 供应商管理页面
				>> 信息查看</span><br> <br>
		</nav>
	</div>
	<div class="container-fluid">
		<div class="row">
			<div class="card">
				<div class="content">
					<p>
						<strong>供应商编码：</strong><span>${provider.proCode }</span>
					</p>
					<p>
						<strong>供应商名称：</strong><span>${provider.proName }</span>
					</p>
					<p>
						<strong>联&nbsp;&nbsp;&nbsp;&nbsp;系&nbsp;&nbsp;&nbsp;&nbsp;人：</strong><span>${provider.proContact }</span>
					</p>
					<p>
						<strong>联 系 &nbsp;&nbsp;电 话：</strong><span>${provider.proPhone }</span>
					</p>
					<p>
						<strong>传&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;真：</strong><span>${provider.proFax }</span>
					</p>
					<p>
						<strong>描&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;述：</strong><span>${provider.proDesc}</span>
					</p>
					<p>
						<strong>企业营业执照：</strong><span> <c:choose>
								<c:when test="${provider.companyLicPicPath == null || provider.companyLicPicPath == ''}">暂无</c:when>
								<c:otherwise>
									<img src="${provider.companyLicPicPath}" width="200px"/>
								</c:otherwise>
							</c:choose> </span>
					</p>

					<p>
						<strong>组织机构代码证：</strong><span> <c:choose>
								<c:when test="${provider.orgCodePicPath == null || provider.orgCodePicPath == ''}">暂无</c:when>
								<c:otherwise>
									<img src="${provider.orgCodePicPath}"  width="200px"/>
								</c:otherwise>
							</c:choose> </span>
					</p>

					<div>
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
	$(".sidebar-wrapper .nav li:eq(4)").addClass("active");
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/statics/js/providerview.js"></script>
