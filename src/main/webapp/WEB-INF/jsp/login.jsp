<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=emulateIE7" />
<link rel="stylesheet"type="text/css"  href="${pageContext.request.contextPath }/statics/css/style.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/statics/css/login.css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/jquery-1.10.2.js"></script>
<title>百货中心供应链管理系统</title>
</head>

<body>
<div id="container">
    <div id="bd">
    	<div id="main">
    		<form action="${pageContext.request.contextPath }/dologin.html" method="post">
	        	<div class="login-box">
	                <div id="logo"></div>
	                <h1></h1>
	                <div class="input username" id="username">
	                    <label for="userCode">用户名</label>
	                    <span></span>
	                    <input type="text" id="userCode" name="userCode" required/>
	                </div>
	                <div class="input psw" id="userPasswordInput">
	                    <%--@declare id="password"--%><label for="password">密&nbsp;&nbsp;&nbsp;&nbsp;码</label>
	                    <span></span>
	                    <input type="password" id="userPassword" name="userPassword" required/>
	                </div>
	                <div id="btn" class="loginButton">
	                    <input type="submit" class="button" value="登录"  />
	                </div>
	            </div>
            </form>
        </div>
        <div id="ft">Copyright &copy; 2020. 百货中心供应链管理系统.</div>
    </div>
   
</div>


<script type="text/javascript">
	var height = $(window).height() > 445 ? $(window).height() : 445;
	$("#container").height(height);
	var bdheight = ($(window).height() - $('#bd').height()) / 2 - 20;
	$('#bd').css('padding-top', bdheight);
	$(window).resize(function(e) {
        var height = $(window).height() > 445 ? $(window).height() : 445;
		$("#container").height(height);
		var bdheight = ($(window).height() - $('#bd').height()) / 2 - 20;
		$('#bd').css('padding-top', bdheight);
    });
	$('select').select();

</script>
</body>
</html>
