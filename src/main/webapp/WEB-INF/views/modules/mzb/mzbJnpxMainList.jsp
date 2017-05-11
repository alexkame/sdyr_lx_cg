<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>学习园地管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/mzb/mzbJnpxMain/">学习园地列表</a></li>
		<shiro:hasPermission name="mzb:mzbJnpxMain:edit"><li><a href="${ctx}/mzb/mzbJnpxMain/form">学习园地添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="mzbJnpxMain" action="${ctx}/mzb/mzbJnpxMain/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<%--<li><label>创建时间：</label>--%>
				<%--<input name="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"--%>
					   <%--value="<fmt:formatDate value="${mzbYearcheck.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"--%>
					   <%--onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> ---%>
				<%--<input name="endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"--%>
					   <%--value="<fmt:formatDate value="${mzbYearcheck.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"--%>
					   <%--onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>--%>
			<%--</li>--%>
			<%--<li><label>类别：</label>--%>
				<%--<form:select path="status" class="input-medium">--%>
					<%--<form:option value="" label=""/>--%>
					<%--<form:options items="${fns:getDictList('jnpx_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>--%>
				<%--</form:select>--%>
			<%--</li>--%>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>标题</th>
				<%--<th>类别</th>--%>
				<th>创建时间</th>
				<shiro:hasPermission name="mzb:mzbJnpxMain:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="mzbJnpxMain">
			<tr>
				<td><a href="${ctx}/mzb/mzbJnpxMain/form?id=${mzbJnpxMain.id}">
					${mzbJnpxMain.title}
				</a></td>
				<%--<td>--%>
						<%--${fns:getDictLabel(mzbJnpxMain.status, 'jnpx_type', '')}--%>
				<%--</td>--%>
				<td>
					<fmt:formatDate value="${mzbJnpxMain.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="mzb:mzbJnpxMain:edit"><td>
    				<a href="${ctx}/mzb/mzbJnpxMain/form?id=${mzbJnpxMain.id}">修改</a>
					<a href="${ctx}/mzb/mzbJnpxMain/delete?id=${mzbJnpxMain.id}" onclick="return confirmx('确认要删除该学习园地吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>