<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>
<div class="content">
	<div class="container-fluid">
		<nav class="pull-left">
			<strong>你现在所在的位置是:</strong>&nbsp;&nbsp;&nbsp; <span> 商品管理页面
				>> 信息查看</span><br> <br>
		</nav>
	</div>
	<div class="container-fluid">
		<div class="row">
			<div class="card">
				<div class="content">
					<p>
						<strong>商品编码：</strong><span>${product.id }</span>
					</p>
					<p>
						<strong>商品名称：</strong><span>${product.name }</span>
					</p>
					<p>
						<strong>商品类别：</strong><span>${product.pc1name} ${product.pc2name} ${product.pc3name}</span>
					</p>
					<p>
						<strong>价&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;格：</strong><span>${product.price }</span>
					</p>
					<p>
						<strong>数&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;量：</strong><span>${product.stock }</span>
					</p>
					<p>
						<strong>摆放位置：</strong><span>${product.placement }</span>
					</p>
					<p>
						<strong>描&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;述：</strong><span>${product.description}</span>
					</p>
					<p>
						<strong>图&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;片：</strong><span> <c:choose>
								<c:when test="${product.fileName == null || product.fileName == ''}">暂无</c:when>
								<c:otherwise>
									<img src="${product.fileName}" width="200px"/>
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
	$(".sidebar-wrapper .nav li:eq(1)").addClass("active");
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/statics/js/productview.js"></script>
