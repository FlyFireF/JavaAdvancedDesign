<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>
<div class="content">
	<div class="container-fluid">
		<nav class="pull-left">
			<strong>你现在所在的位置是:</strong>&nbsp;&nbsp;&nbsp; <span> 订单管理页面 >>
				订单添加页面</span><br> <br>
		</nav>
	</div>
	<div class="container-fluid">
		<div class="row">
			<div class="card">
					<div class="content">
				<form id="billForm" name="billForm" method="post"
					action="${pageContext.request.contextPath }/sys/bill/modifysave.html">
					<div class="row">
					<input type="hidden" name="id" value="${bill.id }">
					<!--div的class 为error是验证错误，ok是验证成功-->
							<div class="col-md-6">
								<label for="billCode">订单编码：</label> <input type="text"
									name="billCode" id="billCode" class="form-control border-input"
									value="${bill.billCode }" readonly="readonly">
							</div>
							<div class="col-md-6">
						<label for="productName">商品名称：</label> <input type="text"
							name="productName" id="productName"
							class="form-control border-input" value="${bill.productName }">
						<font color="red"></font>
					</div>
					<div class="col-md-4">
						<label for="productUnit">商品单位：</label> <input type="text"
							name="productUnit" id="productUnit"
							class="form-control border-input" value="${bill.productUnit }">
						<font color="red"></font>
					</div>
					<div class="col-md-4">
						<label for="productCount">商品数量：</label> <input type="text"
							name="productCount" id="productCount"
							class="form-control border-input" value="${bill.productCount }">
						<font color="red"></font>
					</div>
					<div class="col-md-4">
						<label for="totalPrice">总金额：</label> <input type="text"
							name="totalPrice" id="totalPrice"
							class="form-control border-input" value="${bill.totalPrice }">
						<font color="red"></font>
					</div>
					<div class="col-md-6">
						<label for="providerId">供应商：</label> 
						<input type="hidden" value="${bill.providerId }" id="pid" /> 
							<select name="providerId" id="providerId" class="form-control border-input">
						    </select> 
						    <font color="red"></font>
					</div>
					<div class="col-md-6">
						<label>是否付款：</label>
						<select name="isPayment" id="isPayment" class="form-control border-input">
								<option value="">--请选择--</option>
								<option value="1" ${bill.isPayment==1 ? "selected=\"selected\"":"" }>未付款</option>
								<option value="2" ${bill.isPayment==2 ? "selected=\"selected\"":"" }>已付款</option>
						</select>
						<br>
					</div>
					<div class="col-md-6 col-offset-md-3">
						<input type="button" name="save" id="save" value="保  存"
							class="btn btn-info btn-fill btn-wd" /> 
					    <input type="button" id="back" name="back" value="返  回"
							class="btn btn-success btn-fill btn-wd" />
					</div>
					</div>
				</form>
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
	src="${pageContext.request.contextPath }/statics/js/billmodify.js"></script>