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
													1050样
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
													123个
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
													<p>今日销售额</p>
													230000
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
													<p>本月销售额</p>
													4500000
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
										<h4 class="title">今日销售情况</h4>
										<p class="category">24 Hours performance</p>
									</div>
									<div class="content">
										<div id="chartHours" class="ct-chart"></div>
										<div class="footer">
											<div class="chart-legend">
												<i class="fa fa-circle text-info"></i> Open
												<i class="fa fa-circle text-danger"></i> Click
												<i class="fa fa-circle text-warning"></i> Click Second Time
											</div>
											<hr>
											<div class="stats">
												<i class="ti-reload"></i> Updated 3 minutes ago
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
										<h4 class="title">今日各部门销售情况</h4>
										<p class="category">Sales of all departments today</p>
									</div>
									<div class="content">
										<div id="chartPreferences" class="ct-chart ct-perfect-fourth"></div>

										<div class="footer">
											<div class="chart-legend">
												<i class="fa fa-circle text-info"></i> Open
												<i class="fa fa-circle text-danger"></i> Bounce
												<i class="fa fa-circle text-warning"></i> Unsubscribe
											</div>
											<hr>
											<div class="stats">
												<i class="ti-timer"></i> Campaign sent 2 days ago
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="card ">
									<div class="header">
										<h4 class="title">本月各部门销售情况</h4>
										<p class="category">Sales of each department in this month</p>
									</div>
									<div class="content">
										<div id="chartActivity" class="ct-chart"></div>

										<div class="footer">
											<div class="chart-legend">
												<i class="fa fa-circle text-info"></i> Tesla Model S
												<i class="fa fa-circle text-warning"></i> BMW 5 Series
											</div>
											<hr>
											<div class="stats">
												<i class="ti-check"></i> Data information certified
											</div>
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
			demo.initChartist();
			$.notify({
				icon: 'ti-gift',
				message: "<br>欢迎 <b>${userSession.userName }</b> 访问 百货中心供应链管理系统.<br><br>"
			}, {
				type: 'success',
				timer: 2000
			});
		});
</script>
