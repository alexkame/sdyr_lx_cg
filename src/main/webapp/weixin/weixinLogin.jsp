<%@ page import="com.thinkgem.jeesite.weixin.utils.OauthUtil" %>
<%@ page import="com.thinkgem.jeesite.common.utils.StringUtils" %>
<%@ page import="com.thinkgem.jeesite.modules.sys.dao.UserDao" %>
<%@ page import="com.thinkgem.jeesite.common.utils.SpringContextHolder" %>
<%@ page import="com.thinkgem.jeesite.modules.sys.entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%><!DOCTYPE >
<%
	//判断从微信端还是app端进来的,有则是微信,无则是app
	String code = request.getParameter("code");
	String  openid = new OauthUtil().getOpenid(code);
	//user中的openid
	String useropenid = "";
	UserDao userDao = SpringContextHolder.getBean(UserDao.class);
	User user = userDao.getByOpenid(openid);//UserUtils.getByOpenid(openid);//UserUtils.getByLoginName(principal.getLoginName());
	if (user == null) {
		user = new User();
		useropenid = "1";
	}

%>
<html>
<head>
	<meta charset="utf-8">
	<title>民政百事通</title>
	<meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<Meta http-equiv="Content-Type" Content="application/vnd.openxmlformats-officedocument.wordprocessingml.document">
	<link rel="stylesheet" href="${ctxStatic}/jingle/css/Jingle.css">
	<link rel="stylesheet" href="${ctxStatic}/jingle/css/app.css">
</head>
<body>
<div id="aside_container">
</div>
<div id="section_container">
	<section id="login_section" class="active">
		<header>
			<h1 class="title">${fns:getConfig('productName')}</h1>
			<!-- <nav class="right">
                <a data-target="section" data-icon="info" href="#about_section"></a>
            </nav> -->
		</header>
		<article data-scroll="true" id="login_article">
			<div class="indented">
				<form id="loginForm" action="${ctx}/login" method="post">
					<div>&nbsp;</div>
					<input type="hidden" id="openid" name="openid" value="<%=openid%>"/>
					<div class="input-group">
						<div class="input-row">
							<label for="username">账号</label>
							<input list="pasta" type="text" name="username" id="username" placeholder="请填写登录账号">
							<datalist id="pasta">
								<option>Bavette</option>
								<option>Cannelloni</option>
								<option>Fiorentine</option>
								<option>Gnocchi</option>
								<option>Pappardelle</option>
								<option>Penne lisce</option>
								<option>Pici</option>
								<option>Rigatoni</option>
								<option>Spaghetti</option>
								<option>Tagliatelle</option>
							</datalist>
						</div>
						<div class="input-row">
							<label for="password">密码</label>
							<input type="password" name="password" id="password" placeholder="请填写登录密码">
						</div>
					</div>
					<div class="input-group" id="validateCodeDiv" style="display:none;">
						<div class="input-row">
							<label class="input-label mid" for="validateCode">验证码</label>
							<sys:validateCode name="validateCode" inputCssStyle="margin-bottom:0;"
											  imageCssStyle="padding-top:7px;"/>
						</div>
					</div>
					<div>&nbsp;</div>
					<input type="hidden" name="mobileLogin" value="true">
					<button id="btn" class="submit block" data-icon="key">登录</button>
				</form>
			</div>
			<br><br>
			<c:if test="<%=StringUtils.isNotBlank(useropenid)%>">
				<ul class="list inset">
					<li class="divider grid middle" >
						<div style="text-align:center;font-size:20px;color:#3498DB;font-weight:600;margin:5px 0;">欢迎您第一次使用!</div>
					</li>

					<li data-selected="selected">
							<div style="padding:10px 20px;">
								<div style="text-align:center;font-size:20px;color:#3498DB;font-weight:600;margin:5px 0;">温馨提示</div>
								<div>1、初始登记账号是社会组织单位全称，密码123456。</div>
								<div>2、第一次登陆时需完成手机号码注册绑定，以便今后消息推送。</div>
								</div>
						</li>
				</ul>
			</c:if>
		</article>
	</section>
</div>
<!--<script type="text/javascript" src="${ctxStatic}/jingle/js/lib/cordova.js"></script>-->
<!-- lib -->
<script type="text/javascript" src="${ctxStatic}/jingle/js/lib/zepto.js"></script>
<script type="text/javascript" src="${ctxStatic}/jingle/js/lib/iscroll.js"></script>
<%--<script type="text/javascript" src="${ctxStatic}/jingle/js/lib/template.min.js"></script>--%>
<script type="text/javascript" src="${ctxStatic}/jingle/js/lib/template-simple.js"></script>
<script type="text/javascript" src="${ctxStatic}/jingle/js/lib/Jingle.debug.js"></script>
<script type="text/javascript" src="${ctxStatic}/jingle/js/lib/zepto.touch2mouse.js"></script>
<script type="text/javascript" src="${ctxStatic}/jingle/js/lib/JChart.debug.js"></script>
<%--<script type="text/javascript" src="${ctxStatic}/jingle/js/lib/json2.js"></script>--%>
<!--- app --->
<script type="text/javascript">var ctx = '${ctx}';</script>
<script type="text/javascript" src="${ctxStatic}/jingle/js/app/app.js"></script>
<!--<script src="http://192.168.2.153:8080/target/target-script-min.js#anonymous"></script>-->
<script type="text/javascript">
	var sessionid = '${not empty fns:getPrincipal() ? fns:getPrincipal().sessionid : ""}';
	var openid='<%=useropenid%>';
	$('body').delegate('#login_section','pageinit',function(){
//		if(openid =='1'){
//			J.popup({
//				tplId : 'tpl_popup_login',
//				pos : 'bottom'
//			})
//		}



		$("#loginForm").submit(function(){
			if ($('#username').val() == ''){
				J.showToast('请填写账号', 'info');
			}else if ($('#password').val() == ''){
				J.showToast('请填写密码', 'info');
			}else if ($('#validateCodeDiv').is(':visible') && $('#validateCode').val() == ''){
				J.showToast('请填写验证码', 'info');
			}else{
				var loginForm = $("#loginForm");
				$.post(loginForm.attr('action'), loginForm.serializeArray(), function(data){
					if (data && data.sessionid){
						sessionid = data.sessionid;
						J.showToast('登录成功！', 'success');
						J.Router.goTo('#index_section?index');
					}else{
						J.showToast(data.message, 'error');
						if (data.shiroLoginFailure == 'org.apache.shiro.authc.AuthenticationException'){
							$('#validateCodeDiv').show();
						}
						$('#validateCodeDiv a').click();
					}
					//console.log(data);
				});
			}
			return false;
		});
	});
	$('body').delegate('#login_section','pageshow',function(){
		if (sessionid != ''){
			var targetHash = location.hash;
			if (targetHash == '#login_section'){
				//J.showToast('你已经登录！', 'success');
				J.Router.goTo('#index_section?index');
			}
		}else{
			$('#login_article').addClass('active');
		}
	});
</script>
<%--<script type="text/html" id="tpl_popup_login">--%>
	<%--<div style="padding:10px 20px;">--%>
		<%--<div style="text-align:center;font-size:20px;color:#3498DB;font-weight:600;margin:5px 0;">温馨提示</div>--%>
		<%--<div>1.初始登录账号和密码分别为您的姓名字母全拼和‘123456’。</div>--%>
		<%--<div>2.第一次登录将自动绑定您的信息，方便与您有关的消息针对性推送。</div>--%>
	<%--</div>--%>
<%--</script>--%>
</body>
</html>