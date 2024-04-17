<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>
<div class="content">
	<div class="container-fluid">
		<nav class="pull-left">
			<strong>你现在所在的位置是:</strong>&nbsp;&nbsp;&nbsp; <span>销售订单管理页面</span><br>
			<br>
		</nav>
	</div>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div>
					<div class="content">
						<form class="form-inline" method="post"
							action="${pageContext.request.contextPath}/sys/order/list.html">
							<div class="row">
								<div class="col-md-4">
									<div class="form-group">
										<label for="queryname">收货人姓名：</label> <input name="queryname"
											type="text" value="${queryname}"
											class="form-control border-input">
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label for="queryUserRole">订单状态</label> <select
											class="form-control border-input" name="status">
											<option value="0">- - - 请选择 - - -</option>
											<c:forEach items="${statuslist}" begin="1" var="keyword"
												varStatus="id">
												<option
													<c:if  test="${id.index == status}">selected="selected"</c:if>
													value="${id.index}">${keyword}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<input type="hidden" name="pageIndex" value="1" />
								<div class="col-md-2">
									<div class="text-center">
										<button type="submit" class="btn btn-primary btn-fill btn-wd">搜&nbsp;&nbsp;&nbsp;&nbsp;索</button>
									</div>
								</div>
							</div>
							<div class="clearfix"></div>
						</form>
					</div>
					<c:forEach items="${oolist}" var="order">
						<hr />
						<div class="content table-responsive table-full-width card">
							<div class="content">
								<div class="col-md-8">
									<h5>
										<span class="category">订单号&nbsp;&nbsp;</span>${order.serialNumber}
									</h5>
								</div>
								<div class="col-md-4">
									<h5>
										<span class="category">订单状态&nbsp;&nbsp;</span>
										<c:forEach items="${statuslist}" begin="1" var="keyword"
											varStatus="id">
											<c:if test="${id.index == order.status}">${keyword}</c:if>
										</c:forEach>
									</h5>
								</div>
								<div class="col-md-4">
									<p>
										<span class="category">顾客姓名&nbsp;&nbsp;</span>${order.userName}
									</p>
								</div>
								<div class="col-md-4">
									<p>
										<span class="category">联系电话&nbsp;&nbsp;</span>${order.customerPhone}
									</p>
								</div>
								<div class="col-md-4">
									<p>
										<span class="category">创建时间&nbsp;&nbsp;</span>
										<fmt:formatDate value="${order.creationDate}"
											pattern="yyyy年MM月dd日 HH:mm:ss" />
									</p>
								</div>

							</div>

							<table class="table table-striped card">
								<thead>
									<tr class="text-center">
										<th width="30%">商品名称</th>
										<th width="20%">商品图片</th>
										<th width="20%">价格</th>
										<th width="10%">数量</th>
										<th width="20%">小计价格</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${order.products}" var="p">
										<tr>
											<td>${p.name}</td>
											<td>${p.fileName}</td>
											<td class="text-right">￥ ${p.price}</td>
											<td class="text-right">${p.stock}</td>
											<td class="text-right">￥ ${p.price*p.stock}</td>
										</tr>
									</c:forEach>

								</tbody>
							</table>

							<div class="row content">
								<div class="col-md-4">
									<p>
										<span class="category">付款方式&nbsp;&nbsp;</span>
										<c:if test="${order.payType==1}">在线支付</c:if>
										<c:if test="${order.payType==2}">货到付款</c:if>
									</p>
								</div>
								<div class="col-md-4">
									<p>
										<span class="category">商品数量&nbsp;&nbsp;</span>${order.proCount}
									</p>
								</div>
								<div class="col-md-4">
									<p>
										<span class="category">总消费&nbsp;&nbsp;</span>${order.cost}
									</p>
								</div>


							</div>
						</div>
					</c:forEach>
				</div>
				<c:import url="rollpage.jsp">
					<c:param name="pi" value="${pi}" />
				</c:import>
			</div>
		</div>
	</div>
</div>

<%@include file="/WEB-INF/jsp/common/foot.jsp"%>
<script type="text/javascript">
	$(".sidebar-wrapper .nav li:eq(3)").addClass("active");
</script>
<%-- <script type="text/javascript"
	src="${pageContext.request.contextPath }/statics/js/orderlist.js"></script> --%>
