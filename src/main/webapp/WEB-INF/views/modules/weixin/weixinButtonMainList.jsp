<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>菜单管理</title>
	<meta name="decorator" content="default"/>

</head>
<body>
	<%--<ul class="nav nav-tabs">--%>
		<%--<li class="active"><a href="${ctx}/weixin/weixinButtonMain?remarks=${weixinButtonMain.remarks}">菜单列表</a></li>--%>
		<%--<shiro:hasPermission name="weixin:weixinButtonMain:edit"><li><a href="${ctx}/weixin/weixinButtonMain/form?remarks=${weixinButtonMain.remarks}">菜单添加</a></li></shiro:hasPermission>--%>
	<%--</ul>--%>
	<%--<div class="breadcrumb form-search">--%>
		<%--<a class="btn btn-primary" href="${ctx}/weixin/weixinButtonMain/createMenu?count=${page.count}&remarks=${weixinButtonMain.remarks}" onclick="return confirmx('确认要发布该菜单吗？', this.href)">发布菜单</a>--%>
	<%--</div>--%>
	<%--<sys:message content="${message}"/>--%>
	<%--<table id="contentTable" class="table table-striped table-bordered table-condensed">--%>
		<%--<thead>--%>
			<%--<tr>--%>
				<%--<th>名称</th>--%>
				<%--<th>类型</th>--%>
				<%--<th>键值</th>--%>
				<%--<th>跳转路径</th>--%>
				<%--<shiro:hasPermission name="weixin:weixinButtonMain:edit"><th>操作</th></shiro:hasPermission>--%>
			<%--</tr>--%>
		<%--</thead>--%>
		<%--<tbody>--%>
		<%--<c:forEach items="${page.list}" var="weixinButtonMain">--%>
			<%--<tr>--%>
				<%--<td><a href="${ctx}/weixin/weixinButtonMain/form?id=${weixinButtonMain.id}&remarks=${weixinButtonMain.remarks}">--%>
					<%--${weixinButtonMain.name}--%>
				<%--</a></td>--%>
				<%--<td>--%>
					<%--${weixinButtonMain.type}--%>
				<%--</td>--%>
				<%--<td>--%>
					<%--${weixinButtonMain.key}--%>
				<%--</td>--%>
				<%--<td>--%>
					<%--${weixinButtonMain.url}--%>
				<%--</td>--%>
				<%--<shiro:hasPermission name="weixin:weixinButtonMain:edit"><td>--%>
    				<%--<a href="${ctx}/weixin/weixinButtonMain/form?id=${weixinButtonMain.id}&remarks=${weixinButtonMain.remarks}">修改</a>--%>
					<%--<a href="${ctx}/weixin/weixinButtonMain/delete?id=${weixinButtonMain.id}" onclick="return confirmx('确认要删除该菜单吗？', this.href)">删除</a>--%>
				<%--</td></shiro:hasPermission>--%>
			<%--</tr>--%>
		<%--</c:forEach>--%>
		<%--</tbody>--%>
	<%--</table>--%>
	<%--<div class="pagination">${page}</div>--%>
sfasdfasdfasd
</body>
</html>