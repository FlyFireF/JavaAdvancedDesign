<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>

<div class="content">
	<div class="container-fluid">
		<nav class="pull-left">
			<strong>你现在所在的位置是:</strong>&nbsp;&nbsp;&nbsp; <span>供应商管理页面 >>
				供应商添加页面</span><br> <br>
		</nav>
	</div>
	<div class="container-fluid">
		<div class="content">
			<div class="container-fluid">
				<div class="row">
					<div>
						<div class="card">
							<div class="content">
								<form id="providerForm" name="providerForm" method="post"
									action="${pageContext.request.contextPath }/sys/provider/addsave.html"
									enctype="multipart/form-data">
									<!--div的class 为error是验证错误，ok是验证成功-->
									<div class="col-md-6">
										<label for="proCode">供应商编码：</label> <input type="text"
											name="proCode" id="proCode" class="form-control border-input"
											value="">
										<!-- 放置提示信息 -->
										<font color="red"></font>
									</div>
									<div class="col-md-6">
										<label for="proName">供应商名称：</label> <input type="text"
											name="proName" id="proName" class="form-control border-input"
											value=""> <font color="red"></font>
									</div>
									<div class="col-md-6">
										<label for="proContact">联系人：</label> <input type="text"
											name="proContact" id="proContact"
											class="form-control border-input" value=""> <font
											color="red"></font>

									</div>
									<div class="col-md-6">
										<label for="proPhone">联系电话：</label> <input type="text"
											name="proPhone" id="proPhone"
											class="form-control border-input" value=""> <font
											color="red"></font>
									</div>
									<div class="col-md-6">
										<label for="proAddress">联系地址：</label> <input type="text"
											name="proAddress" id="proAddress"
											class="form-control border-input" value="">
									</div>
									<div class="col-md-6">
										<label for="proFax">传真：</label> <input type="text"
											name="proFax" id="proFax" class="form-control border-input"
											value="">
									</div>
									<div class="col-md-12">
										<label for="proDesc">描述：</label> <input type="text"
											name="proDesc" id="proDesc" class="form-control border-input"
											value="">
									</div>
									<div class="col-md-6">
										<input type="hidden" id="errorinfo" value="${uploadFileError}" />
										<label for="a_companyLicPicPath">企业营业执照：</label> <input
											type="file" name="attachs" id="a_companyLicPicPath" /> <font
											color="red"></font>
									</div>
									<div class="col-md-6">
										<input type="hidden" id="errorinfo_oc"
											value="${uploadOcError}" /> <label for="a_orgCodePicPath">组织机构代码证：</label>
										<input type="file" name="attachs" id="a_orgCodePicPath" /> <font
											color="red"></font>
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
	$(".sidebar-wrapper .nav li:eq(4)").addClass("active");
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/statics/js/provideradd.js"></script>
