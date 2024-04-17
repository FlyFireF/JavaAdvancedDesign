<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>
<div class="content">
	<div class="container-fluid">
		<nav class="pull-left">
			<strong>你现在所在的位置是:</strong>&nbsp;&nbsp;&nbsp; <span>订单管理页面</span><br>
			<br>
		</nav>
	</div>
	<div class="container-fluid">
		<div class="row">

			<div class="col-md-12">
				<div class="card card-plain">
					<div class="content">
						<form class="form-inline" method="post"
							action="${pageContext.request.contextPath }/sys/bill/list.html">
							<div class="row">
								<div class="col-md-3">
									<div class="form-group">
										<label>商品名称： </label><br> <input type="text"
											class="form-control border-input" name="queryProductName"
											value="${queryProductName }">
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>供应商： &nbsp;&nbsp;</label> <br> <select
											name="queryProviderId" class="form-control border-input">
											<c:if test="${providerList != null }">
												<option value="">--请选择--</option>
												<c:forEach var="provider" items="${providerList}">
													<option
														<c:if test="${provider.id == queryProviderId }">selected="selected"</c:if>
														value="${provider.id}">${provider.proName}</option>
												</c:forEach>
											</c:if>
										</select>
									</div>
								</div>
								<div class="col-md-2">
									<div class="form-group">
										<label for="exampleInputEmail1">是否付款： &nbsp;&nbsp;</label><br>
										<select name="queryIsPayment"
											class="form-control border-input">
											<option value="">--请选择--</option>
											<option value="1" ${queryIsPayment== 1 ? "selected=\"selected\"":"" }>未付款</option>
											<option value="2" ${queryIsPayment== 2 ? "selected=\"selected\"":"" }>已付款</option>
										</select>
									</div>
								</div>
								<input type="hidden" name="pageIndex" value="1" />
								<div class="col-md-2">
									<div class="text-center">
										<button type="submit" class="btn btn-danger btn-fill btn-wd">搜&nbsp;&nbsp;索</button>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</div>
								</div>
								<div class="col-md-2">
									<div class="text-center">
										<a class="btn btn-danger btn-fill btn-wd"
											href="${pageContext.request.contextPath }/sys/bill/add.html">添加订单</a>
									</div>
								</div>
							</div>
							<div class="clearfix"></div>
						</form>
					</div>
					<div class="content table-responsive table-full-width">
						<table class="table table-hover">
							<thead>
								<th width="10%">订单编码</th>
								<th width="20%">商品名称</th>
								<th width="20%">供应商</th>
								<th width="10%">订单金额</th>
								<th width="10%">是否付款</th>
								<th width="10%">创建时间</th>
								<th width="20%">操作</th>
							</thead>
							<tbody>
								<c:forEach var="bill" items="${pi.list}" varStatus="status">
									<tr>
										<td><span>${bill.billCode }</span>
										</td>
										<td><span>${bill.productName }</span>
										</td>
										<td><span>${bill.providerName}</span>
										</td>
										<td class="text-right"><span>${bill.totalPrice}</span>
										</td>
										<td class="text-center"><span> <c:if
													test="${bill.isPayment == 1}">未付款</c:if> <c:if
													test="${bill.isPayment == 2}">已付款</c:if> </span>
										</td>
										<td><span> <fmt:formatDate
													value="${bill.creationDate}" pattern="yyyy-MM-dd" /> </span>
										</td>
										<td><span><a class="viewBill btn btn-info btn-xs"
												href="javascript:;" billid=${bill.id }
												billcc=${bill.billCode }>查看</a>&nbsp;&nbsp; <a
												class="modifyBill btn btn-warning btn-xs"
												href="javascript:;" billid=${bill.id }
												billcc=${bill.billCode }>编辑</a>&nbsp;&nbsp; <a
												class="deleteBill btn btn-success btn-xs"
												href="javascript:;" billid=${bill.id }
												billcc=${bill.billCode }>删除</a> </span>
										</td>
									</tr>
								</c:forEach>

							</tbody>
						</table>
					</div>
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
	$(".sidebar-wrapper .nav li:eq(2)").addClass("active");
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/statics/js/billlist.js"></script>