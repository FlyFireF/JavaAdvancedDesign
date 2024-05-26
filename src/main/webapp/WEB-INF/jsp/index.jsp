<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="common/head.jsp" %>
<div class="content">
					<div class="container-fluid">
						<div class="row">
							<div class="col-lg-3 col-sm-6">
								<div class="card">
									<div class="content">
										<div class="row">
											<div class="col-xs-5">
												<div class="icon-big icon-warning text-center">
													<i class="ti-server"></i>
												</div>
											</div>
											<div class="col-xs-7">
												<div class="numbers">
													<p>商品种类</p>
													${productCount}样
												</div>
											</div>
										</div>
										<div class="footer">
											<hr />
											<div class="stats">
												<i class="ti-reload"></i> Updated now
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="col-lg-3 col-sm-6">
								<div class="card">
									<div class="content">
										<div class="row">
											<div class="col-xs-5">
												<div class="icon-big icon-success text-center">
													<i class="ti-wallet"></i>
												</div>
											</div>
											<div class="col-xs-7">
												<div class="numbers">
													<p>供应商数</p>
													${providerCount}个
												</div>
											</div>
										</div>
										<div class="footer">
											<hr />
											<div class="stats">
												<i class="ti-calendar"></i> Last day
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="col-lg-3 col-sm-6">
								<div class="card">
									<div class="content">
										<div class="row">
											<div class="col-xs-5">
												<div class="icon-big icon-danger text-center">
													<i class="ti-shopping-cart"></i>
												</div>
											</div>
											<div class="col-xs-7">
												<div class="numbers">
													<p>订单数</p>
													${orderCount}单
												</div>
											</div>
										</div>
										<div class="footer">
											<hr />
											<div class="stats">
												<i class="ti-timer"></i> In the last hour
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="col-lg-3 col-sm-6">
								<div class="card">
									<div class="content">
										<div class="row">
											<div class="col-xs-5">
												<div class="icon-big icon-info text-center">
													<i class="ti-stats-up"></i>
												</div>
											</div>
											<div class="col-xs-7">
												<div class="numbers">
													<p>销售额</p>
													${totalCost}元
												</div>
											</div>
										</div>
										<div class="footer">
											<hr />
											<div class="stats">
												<i class="ti-reload"></i> Updated now
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="row">

							<div class="col-md-12">
								<div class="card">
									<div class="header">
										<h4 class="title">年度销售额变化</h4>
										<p class="category">Annual Sales Revenue Change</p>
									</div>
									<div class="content">
										<div id="chartActivity" class="ct-chart"></div>
										<div class="footer">
											<div class="chart-legend">
												<font color="#5f9ea0">销售额</font>
											</div>
											<hr>
											<div class="stats">
												<i class="ti-reload"></i> Updated now
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6">
								<div class="card">
									<div class="header">
										<h4 class="title">各商品种类销售额</h4>
										<p class="category">Sales Revenue of Various Product Categories</p>
									</div>
									<div class="content">
										<div id="chartViews" class="ct-chart ct-perfect-fourth"></div>

										<div class="footer">
											<hr>
											<%--<div class="stats">
												<i class="ti-timer"></i> Campaign sent 2 days ago
											</div>--%>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="card ">
									<div class="header">
										<h4 class="title">各商品种类销售额占比</h4>
										<p class="category">Sales Revenue Share of Various Product Categories</p>
									</div>
									<div class="content">
										<div id="chartPreferences" class="ct-chart"></div>

										<div class="footer">
											<hr>
											<%--<div class="stats">
												<i class="ti-check"></i> Data information certified
											</div>--%>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
<%@ include file="common/foot.jsp" %>
<script type="text/javascript">
	$(".sidebar-wrapper .nav li:eq(0)").addClass("active");
	$(document).ready(function() {
		$.notify({
			icon: 'ti-gift',
			message: "<br>欢迎 <b>${userSession.userName }</b> 访问 百货中心供应链管理系统.<br><br>"
		}, {
			type: 'success',
			timer: 1000,
			delay: 1000
		});
	});
</script>
