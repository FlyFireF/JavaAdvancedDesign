<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>

<div class="content">
	<div class="container-fluid">
		<nav class="pull-left">
			<strong>你现在所在的位置是:</strong>&nbsp;&nbsp;&nbsp; <span>订单管理页面 >>
				订单添加页面</span><br> <br>
		</nav>
	</div>
	<div class="container-fluid">
		<div class="content">
			<div class="container-fluid">
				<div class="row">
					<div>
						<div class="card">
							<div class="content">

								<form id="billForm" name="billForm" method="post"
									action="${pageContext.request.contextPath }/sys/bill/addsave.html">
									<!--div的class 为error是验证错误，ok是验证成功-->
									<div class="row">
										<div class="form-group">
											<div class="col-sm-2">
												<label for="billCode">订单编码：</label>
											</div>
											<div class="col-sm-10">
												<input type="text" name="billCode" class="text form-control border-input"
													id="billCode" value="" >
												<!-- 放置提示信息 -->
												<font color="red"></font>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<div class="col-sm-2">
												<label for="productName">商品名称：</label>
											</div>
											<div class="col-sm-10">
												<input type="text" name="productName" id="productName"
													value="" class="form-control border-input"> <font color="red"></font>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<div class="col-sm-2">
												<label for="productUnit">商品单位：</label>
											</div>
											<div class="col-sm-10">
												<input type="text" name="productUnit" id="productUnit"
													value="" class="form-control border-input"> <font color="red"></font>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<div class="col-sm-2">
												<label for="productCount">商品数量：</label>
											</div>
											<div class="col-sm-10">
												<input type="text" name="productCount" id="productCount"
													value="" class="form-control border-input"> <font color="red"></font>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<div class="col-sm-2">
												<label for="totalPrice">总金额：</label>
											</div>
											<div class="col-sm-10">
												<input type="text" name="totalPrice" id="totalPrice"
													value="" class="form-control border-input"> <font color="red"></font>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<div class="col-sm-2">
												<label>供应商：</label>
											</div>
											<div class="col-sm-10">
												<select name="providerId" id="providerId" class="form-control border-input">
												</select> <font color="red"></font>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<div class="col-sm-2">
												<label>是否付款：</label>
											</div>
											<div class="col-sm-10">
													<select name="isPayment" id="isPayment" class="form-control border-input">
								<option value="">--请选择--</option>
								<option value="1">未付款</option>
								<option value="2">已付款</option>
						</select>
											</div>
										</div>
									</div>

									<div class="row">
										<div class="form-group">
											<div class="col-sm-offset-2 col-sm-10">
												<input type="button" name="add" id="add"  value="保存"
													class="btn btn-info btn-fill btn-wd" /> <input
													type="button" id="back" name="back" value="返回"
													class="btn btn-success btn-fill btn-wd">
											</div>
										</div>
									</div>
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
	$(".sidebar-wrapper .nav li:eq(2)").addClass("active");
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/statics/js/billadd.js"></script>