<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="common/head.jsp"%>
<div class="content">
	<div class="container-fluid">
		<nav class="pull-left">
			<strong>你现在所在的位置是:</strong>&nbsp;&nbsp;&nbsp; <span>商品管理页面</span><br>
			<br>
		</nav>
	</div>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div class="card">
					<div class="content">
						<form class="form-inline" method="post"
							action="${pageContext.request.contextPath }/sys/product/list.html">
							<div class="row">
								<div class="col-md-3">
									<div class="form-group">
										<label>商品名 </label> <input type="text" name="queryname"
											class="form-control border-input" value="${queryName }"
											placeholder="请输入搜索商品名......">
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label for="categoryLevel1Id">商品类别 &nbsp;&nbsp;</label> <select
											class="form-control border-input" name="categoryLevel1Id"
											id="categoryLevel1Id">
											<option value="0">- - - 请选择 - - -</option>
											<c:forEach var="pc" items="${pcList}">
												<option
													<c:if test="${pc.id == categoryLevel1Id }">selected="selected"</c:if>
													value="${pc.id}">${pc.name}</option>
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
								<div class="col-md-2">
									<div class="text-center">
										<a class="btn btn-danger btn-fill btn-wd"
											href="${pageContext.request.contextPath}/sys/product/add.html">添加商品</a>
									</div>
								</div>
								<div class="col-md-2">
									<div class="text-center">
										<a class="btn btn-danger btn-fill btn-wd"
											href="${pageContext.request.contextPath}/sys/product/productcategorylist.html">查看商品分类</a>
									</div>
								</div>
							</div>
							<div class="clearfix"></div>
						</form>
					</div>
					<div class="content table-responsive table-full-width">
						<table class="table table-striped">
							<thead>
								<th width="10%">商品编码</th>
								<th width="15%">商品名称</th>
								<th width="8%">单价</th>
								<th width="8%">摆放位置</th>
								<th width="8%">数量</th>
								<th width="12%">一级分类名称</th>
								<th width="12%">二级分类名称</th>
								<th width="12%">三级分类名称</th>
								<th width="25%">操作</th>
							</thead>
							<tbody>
								<c:forEach var="product" items="${pi.list}" varStatus="status">
									<tr>
										<td><span>${product.id}</span>
										</td>
										<td><span>${product.name}</span>
										</td>
										<td class="text-right"><span>￥ ${product.price}</span>
										</td>
										<td><span>${product.placement}</span>
										</td>
										<td><span>${product.stock}</span>
										</td>
										<td><span>${product.pc1name}</span>
										</td>
										<td><span>${product.pc2name}</span>
										</td>
										<td><span>${product.pc3name}</span>
										</td>
										<td><span><a
												class="viewproduct btn btn-info btn-xs" href="javascript:;"
												pid=${product.id } pname=${product.name} >查看</a></span>&nbsp;&nbsp; 
											<span><a class="modifyproduct btn btn-warning btn-xs"
												href="javascript:;" pid=${product.id } pname=${product.name}>编辑</a>
											</span>&nbsp;&nbsp; <span><a class="deleteproduct btn btn-success btn-xs"
												href="javascript:;" pid=${product.id } pname=${product.name}>删除</a></span>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			<c:import url="rollpage.jsp">
	          	<c:param name="pi" value="${pi}"/>
          	</c:import>
			</div>
		</div>
	</div>
</div>
<%@ include file="common/foot.jsp"%>
<script type="text/javascript">
	$(".sidebar-wrapper .nav li:eq(1)").addClass("active");
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/statics/js/productlist.js"></script>