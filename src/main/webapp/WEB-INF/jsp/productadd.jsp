<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>

<div class="content">
	<div class="container-fluid">
		<nav class="pull-left">
			<strong>你现在所在的位置是:</strong>&nbsp;&nbsp;&nbsp; <span>商品管理页面 >>
				商品添加页面</span><br> <br>
		</nav>
	</div>
	<div class="container-fluid">
		<div class="content">
			<div class="container-fluid">
				<div class="row">
					<div>
						<div class="card">
							<div class="content">
								<form id="productForm" name="productForm" method="post"
									action="${pageContext.request.contextPath }/sys/product/addsave.html"
									enctype="multipart/form-data">
									<!--div的class 为error是验证错误，ok是验证成功-->
									<div class="row">
										<div class="col-md-6">
											<label for="name">商品名称：</label> <input type="text"
												name="name" id="pname" class="form-control border-input"
												value=""> <font color="red"></font>
										</div>
										<div class="col-md-3">
											<label for="price">价格：</label>
											<div class="input-group border-input">
												<span class="input-group-addon border-input">￥</span><input type="text"
													name="price" id="price" value=""
													class="form-control border-input">
											</div>
											<font color="red"></font>
										</div>
										<div class="col-md-3">
											<label for="stock">数量：</label> <input type="number"
												name="stock" id="stock" value=""
												class="form-control border-input"> <font color="red"></font>
										</div>
									</div>
									<div class="row">
										<div class="col-md-6">
											<label for="placement">摆放位置：</label> <input type="text"
												name="placement" id="placement" value=""
												class="form-control border-input"> <font color="red"></font>
										</div>
										<div class="col-md-6">
											<label for="attachs">图片：</label> <input type="file"
												name="attachs" id="attachs" value=""
												class="form-control border-input">
										</div>
									</div>

									<div class="row">
										<div class="form-group">
											<div class="col-sm-2">
												<label>商品类别：</label>
											</div>
											<div class="col-sm-3">
												<select name="categoryLevel1Id" id="categoryLevel1Id"
													class="form-control border-input">
												</select> <font color="red"></font>
											</div>
											<div class="col-sm-3">
												<select name="categoryLevel2Id" id="categoryLevel2Id"
													class="form-control border-input">
												</select> <font color="red"></font>
											</div>
											<div class="col-sm-3">
												<select name="categoryLevel3Id" id="categoryLevel3Id"
													class="form-control border-input">
												</select> <font color="red"></font>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-12">
											<label for="proDesc">描述：</label>
											<textarea name="description" id="description" rows="5"
												class="form-control border-input"></textarea>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<div class="col-sm-12">
												<input type="button" name="add" id="add" value="保存"
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
	$(".sidebar-wrapper .nav li:eq(1)").addClass("active");
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/statics/js/productadd.js"></script>
