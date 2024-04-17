<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="common/head.jsp"%>
<div class="content">

	<div class="container-fluid">
		<div class="row">
			<div class="col-md-8">
				<nav class="pull-left">
					<strong>你现在所在的位置是:</strong>&nbsp;&nbsp;&nbsp; <span>商品分类列表页面</span><br>
					<br>
				</nav>
			</div>
			<div class="col-md-2">
				<div class="text-center ">
					<a class="btn btn-danger btn-fill btn-wd"
						href="${pageContext.request.contextPath}/sys/product/addproductcategory.html">添加一二级商品分类</a>
				</div>
				<br>
			</div>
			<div class="col-md-2">
				<div class="text-center ">
					<a class="btn btn-success btn-fill btn-wd"
						href="javascript:history.go(-1)">返回</a>
				</div>
				<br>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<div class="card">
					<div class="content">
						<!-- Nav tabs -->
						<ul class="nav nav-tabs" role="tablist">
							<c:forEach items="${ppclist}" var="pc1">
								<li role="presentation"
									<c:if test="${pc1.id==1}"> class="active" </c:if>><a
									href="#ppc${pc1.id}" aria-controls="ppc${pc1.id}" role="tab"
									data-toggle="tab">${pc1.name}</a>
								</li>
							</c:forEach>
						</ul>
						<!-- Tab panes -->
						<div class="tab-content" id="ppclist">
							<c:forEach items="${ppclist}" var="p1">
								<div role="tabpanel" class="tab-pane content" id="ppc${p1.id}">
									<div class="row">
										<c:forEach items="${p1.productCategories}" var="p2">
											<div class="col-xs-6 col-md-3">
												<ul class="list-group">
													<li class="list-group-item active">
														<button type="button" class="close addmodal" aria-label="Close"
															pid="${p2.id}" pname="${p2.name}" ppid="${p2.parentId}" ppname="${p1.name}"
													data-toggle="modal" data-target="#addpp">
													<span class="glyphicon glyphicon-plus" aria-hidden="true"></span></button>
														${p2.id} | ${p2.name}</li>
													<c:forEach items="${p2.productCategories}" var="p3">
														<li class="list-group-item">
															<button type="button" class="close delete"
																aria-label="Close" pid="${p3.id}" pname="${p3.name}"  
																ppid="${p3.parentId}" ppname="${p2.name}">
														<span aria-hidden="true">&times;</span></button> 
														${p3.id} | ${p3.name}</li>
													</c:forEach>
												</ul>
											</div>
										</c:forEach>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>
</div>

<%@ include file="common/foot.jsp"%>
<!-- Modal -->
<div class="modal fade" id="addpp" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">添加商品类别</h4>
			</div>
			<div class="modal-body">
			   <div class="row">
			       <div class="col-md-3">
						一级商品类别名称
					</div>
					 <div class="col-md-8">
					    <input type="hidden" name="pp1id" id="pp1id" value="">
						<input class="form-control border-input" type="text" value="" readonly="readonly" />
					</div>
			   </div><br>
			   <div class="row">
			       <div class="col-md-3">
						二级商品类别名称
					</div>
					 <div class="col-md-8">
					    <input type="hidden" name="pp2id" id="pp2id" value="">
						<input class="form-control border-input" type="text" value="" readonly="readonly" />
					</div>
			   </div><br>
			   <div class="row">
			       <div class="col-md-3">
						<p>子级商品编号</p>
					</div>
					 <div class="col-md-8">
						<input class="form-control border-input" type="text" id="ppcode" placeholder="请输入商品类别编码" />
					</div>
			   </div>
			   <br>
			   <div class="row">
			       <div class="col-md-3">
						<p>子级商品类别名称</p>
					</div>
					 <div class="col-md-8">
						<input class="form-control border-input" type="text" id="ppname" placeholder="请输入商品类别名称" />
					</div>
			   </div><br>
			   
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				<button type="button" class="btn btn-primary add">添加</button>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	$(".sidebar-wrapper .nav li:eq(1)").addClass("active");
	$("#ppclist div:eq(0)").addClass("active");
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/statics/js/productcategorylist.js"></script>