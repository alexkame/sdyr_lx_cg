<!doctype html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html lang="en" class="login-content" data-ng-app="materialAdmin">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="Generator" content="EditPlus®">
	<meta name="Author" content="">
	<meta name="Keywords" content="">
	<meta name="Description" content="">
	<title>Material Admin</title>
	<link href="/test/Azrael_login/css/material-design-iconic-font/css/material-design-iconic-font.min.css" rel="stylesheet" type="text/css">
	<!-- CSS -->
	<link href="/test/Azrael_login/css/app.min.1.css" rel="stylesheet" type="text/css">
	<style type="text/css">
		html,body,table{background-color:#f5f5f5;width:100%;text-align:center;}.form-signin-heading{font-family:Helvetica, Georgia, Arial, sans-serif, 黑体;font-size:36px;margin-bottom:20px;color:#0663a2;}
		.form-signin{position:relative;text-align:left;width:300px;padding:25px 29px 29px;margin:0 auto 20px;background-color:#fff;border:1px solid #e5e5e5;
			-webkit-border-radius:5px;-moz-border-radius:5px;border-radius:5px;-webkit-box-shadow:0 1px 2px rgba(0,0,0,.05);-moz-box-shadow:0 1px 2px rgba(0,0,0,.05);box-shadow:0 1px 2px rgba(0,0,0,.05);}
		.form-signin .checkbox{margin-bottom:10px;color:#0663a2;} .form-signin .input-label{font-size:16px;line-height:23px;color:#999;}
		.form-signin .input-block-level{font-size:16px;height:auto;margin-bottom:15px;padding:7px;*width:283px;*padding-bottom:0;_padding:7px 7px 9px 7px;}
		.form-signin .btn.btn-large{font-size:16px;} .form-signin #themeSwitch{position:absolute;right:15px;bottom:10px;}
		.form-signin div.validateCode {padding-bottom:15px;} .mid{vertical-align:middle;}
		.header{height:80px;padding-top:20px;} .alert{position:relative;width:300px;margin:0 auto;*padding-bottom:0px;}
		label.error{background:none;width:270px;font-weight:normal;color:inherit;margin:0;}
	</style>
	<script src="${ctxStatic}/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>
	<script>
		function doSubmit(){
			$("#loginForm").submit();
		}
		$(document).ready(function() {
			$("#loginForm").validate({
				messages: {
					username: {required: "请填写用户名."},password: {required: "请填写密码."},
				},
				errorLabelContainer: "#messageBox",
				errorPlacement: function(error, element) {
					error.appendTo($("#loginError").parent());
				}
			});
		});
		// 如果在框架或在对话框中，则弹出提示并跳转到首页
		if(self.frameElement && self.frameElement.tagName == "IFRAME" || $('#left').length > 0 || $('.jbox').length > 0){
			alert('未登录或登录超时。请重新登录，谢谢！');
			top.location = "${ctx}";
		}
	</script>
</head>
<body class="login-content" data-ng-controller="loginCtrl as lctrl">
<%--<form id="loginForm" action="${ctx}/login" method="post">--%>
<div class="header">
	<div id="messageBox" class="alert alert-error ${empty message ? 'hide' : ''}"><button data-dismiss="alert" class="close">×</button>
		<label id="loginError" class="error">${message}</label>
	</div>
</div>
<center><div style="text-shadow: 2px 2px 0 gray;font: bold 55px/100% '微软雅黑';position: absolute;left: 50%;width: 980px;margin-left: -490px;">海沧民政百事通</div></center>
<form class="lc-block" id="loginForm" class="form-signin" action="${ctx}/login" method="post" data-ng-class="{'toggled':lctrl.login === 1}">
	<div class="input-group m-b-20">
    		<span class="input-group-addon">
    			<i class="zmdi zmdi-account"></i>
    		</span>
		<div class="fg-line">
			<input type="text" id="username" placeholder="用户名" name="username" class="form-control" value="${username}">
		</div>
	</div>

	<div class="input-group m-b-20">
    		<span class="input-group-addon">
    			<i class="zmdi zmdi-male"></i>
    		</span>
		<div class="fg-line">
			<input type="password" id="password" name="password" class="form-control" placeholder="密码">
		</div>
	</div>

	<div class="clearfix"></div>

	<a href="" class="btn btn-login btn-danger btn-float">
		<i onclick="doSubmit()" class="zmdi zmdi-arrow-forward"></i>
	</a>

</form>
</body>

<script src="http://libs.baidu.com/jquery/2.1.1/jquery.min.js"></script>
<!-- Angular -->
<script src="/test/Azrael_login/js/bower_components/angular/angular.min.js"></script>
<script src="/test/Azrael_login/js/bower_components/angular-resource/angular-resource.min.js"></script>
<script src="/test/Azrael_login/js/bower_components/angular-animate/angular-animate.min.js"></script>


<!-- Angular Modules -->
<script src="/test/Azrael_login/js/bower_components/angular-ui-router/release/angular-ui-router.min.js"></script>
<script src="/test/Azrael_login/js/bower_components/angular-loading-bar/src/loading-bar.js"></script>
<script src="/test/Azrael_login/js/bower_components/oclazyload/dist/ocLazyLoad.min.js"></script>
<script src="/test/Azrael_login/js/bower_components/angular-bootstrap/ui-bootstrap-tpls.min.js"></script>

<!-- Common js -->
<script src="/test/Azrael_login/js/bower_components/angular-nouislider/src/nouislider.min.js"></script>
<script src="/test/Azrael_login/js/bower_components/ng-table/dist/ng-table.min.js"></script>

<!-- Placeholder for IE9 -->
<!--[if IE 9 ]>
<script src="/test/Azrael_login/js/bower_components/jquery-placeholder/jquery.placeholder.min.js"></script>
<![endif]-->
<!-- App level -->
<script src="/test/Azrael_login/js/app.js"></script>
<script src="/test/Azrael_login/js/controllers/main.js"></script>
<script src="/test/Azrael_login/js/controllers/ui-bootstrap.js"></script>


<!-- Template Modules -->
<script src="/test/Azrael_login/js/modules/form.js"></script>
<script src="${ctxStatic}/flash/zoom.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/jquery-validation/1.11.0/jquery.validate.min.js" type="text/javascript"></script>

</html>