<%@ page import="com.thinkgem.jeesite.common.utils.SpringContextHolder" %>
<%@ page import="com.thinkgem.jeesite.modules.sys.dao.UserDao" %>
<%@ page import="com.thinkgem.jeesite.modules.sys.entity.User" %>
<%@ page import="com.thinkgem.jeesite.weixin.utils.OauthUtil" %>
<%@ page import="com.thinkgem.jeesite.common.utils.StringUtils" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%
	//判断从微信端还是app端进来的,有则是微信,无则是app
	//String code = request.getParameter("code");
	String  openid = "o_yVPxJWRsJgrK7LZM7-e6zLtVPw";//new OauthUtil().getOpenid(code);
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

	<section id="password_reset_section" class="active">
		<article class="active" id="password_reset">
			<div class="grid middle" style="height: 500px;">

				<div style="margin: auto;;padding: 5px;">
					<center>
						<c:if test="<%=StringUtils.isBlank(useropenid)%>">
							<font size="5pt"><%=user.getName()%></font><br><br><br>
							<button id="resetPw" onclick="resetPw('<%=openid%>')" data-icon="key">密码重置</button>
						</c:if>
						<c:if test="<%=StringUtils.isNotBlank(useropenid)%>">
							<font>抱歉,您非社会组织工作人员,无法使用该功能.</font>
						</c:if>
					</center>
				</div>
			</div>
		</article>
	</section>
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

	function resetPw(openid){
		J.confirm('确定重置?','重置完的密码为:123456',
				function(){
					$.post("/a/sys/user/resetPwd",{"openid":openid},function(data) {
						J.showToast(data.message);
					});
				},
				function(){
				}
		);


	}
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