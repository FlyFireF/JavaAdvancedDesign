<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>
<div class="content">
	<div class="container-fluid">
		<nav class="pull-left">
			<strong>你现在所在的位置是:</strong>&nbsp;&nbsp;&nbsp; <span> 订单管理页面 >>
				信息查看</span><br> <br>
		</nav>
	</div>
	<div class="container-fluid">
	<div class="row">
			<div class="card">
				<div class="content">
					<p>
						<strong>订单编号：</strong><span>${bill.billCode }</span>
					</p>
					<p>
						<strong>商品名称：</strong><span>${bill.productName }</span>
					</p>
					<p>
						<strong>商品单位：</strong><span>${bill.productUnit }</span>
					</p>
					<p>
						<strong>商品数量：</strong><span>${bill.productCount }</span>
					</p>
					<p>
						<strong>总&nbsp;&nbsp;金&nbsp;&nbsp;额：</strong><span>${bill.totalPrice }</span>
					</p>
					<p>
						<strong>供&nbsp;&nbsp;应&nbsp;&nbsp;商：</strong><span>${bill.providerName }</span>
					</p>
					<p>
						<strong>是否付款：</strong> <span> <c:if
								test="${bill.isPayment == 1}">未付款</c:if> <c:if
								test="${bill.isPayment == 2}">已付款</c:if> </span>
					</p>
					<div >
						<input type="button" id="back" name="back" value="返回" class="btn btn-success btn-fill btn-wd">
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<%@include file="/WEB-INF/jsp/common/foot.jsp"%>
<script type="text/javascript">
	$(".sidebar-wrapper .nav li:eq(2)").addClass("active");
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/statics/js/billview.js"></script>