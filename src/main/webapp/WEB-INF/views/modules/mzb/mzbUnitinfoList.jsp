<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>基本信息管理</title>
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
		<li class="active"><a href="${ctx}/mzb/mzbUnitinfo/">基本信息列表</a></li>
		<shiro:lacksPermission name="mzb:mzbUnitinfo:check">
			<c:if test="${page.list.size() < 1}">
				<li><a href="${ctx}/mzb/mzbUnitinfo/form">基本信息添加</a></li>
			</c:if>
		</shiro:lacksPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="mzbUnitinfo" action="${ctx}/mzb/mzbUnitinfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>单位名称：</label>
				<form:input path="danwMc" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>登记证号：</label>
				<form:input path="dengjzh" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>登记日期：</label>
				<input name="dengjRq" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${mzbUnitinfo.dengjRq}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<%--<li><label>统一社会信用代码：</label>--%>
				<%--<form:input path="tongyDm" htmlEscape="false" maxlength="255" class="input-medium"/>--%>
			<%--</li>--%>
			<%--<li class="clearfix"></li>--%>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>单位名称</th>
				<th>登记证号</th>
				<th>登记日期</th>
				<th>单位电话</th>
				<th>法定代表人</th>
				<shiro:hasPermission name="mzb:mzbUnitinfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="mzbUnitinfo">
			<tr >
				<td><a href="${ctx}/mzb/mzbUnitinfo/view?id=${mzbUnitinfo.id}">
					${mzbUnitinfo.danwMc}
				</a></td>
				<td>
					${mzbUnitinfo.dengjzh}
				</td>
				<td>
					<fmt:formatDate value="${mzbUnitinfo.dengjRq}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${mzbUnitinfo.danwDh}
				</td>
				<td>
					${mzbUnitinfo.fadDbr}
				</td >
				<shiro:hasPermission name="mzb:mzbUnitinfo:edit">
					<td style="background-color:${empty mzbUnitinfo.isself?'':'#ffb49b'}">
					<c:choose>
						<c:when test="${empty mzbUnitinfo.isself}">
							<shiro:hasPermission name="mzb:mzbUnitinfo:check">
								状态正常
							</shiro:hasPermission>
							<shiro:lacksPermission name="mzb:mzbUnitinfo:check">
								<a href="${ctx}/mzb/mzbUnitinfo/apply?id=${mzbUnitinfo.id}">申请修改</a>
							</shiro:lacksPermission>
						</c:when>
						<c:when test="${mzbUnitinfo.isself=='1'}">
							<shiro:hasPermission name="mzb:mzbUnitinfo:check">
								<a href="${ctx}/mzb/mzbUnitinfo/apply?id=${mzbUnitinfo.id}">审核申请</a>
							</shiro:hasPermission>
							<shiro:lacksPermission name="mzb:mzbUnitinfo:check">
								申请中
							</shiro:lacksPermission>
						</c:when>
						<c:when test="${mzbUnitinfo.isself=='2'}">
							<shiro:hasPermission name="mzb:mzbUnitinfo:check">
								已通过审核,修改中
							</shiro:hasPermission>
							<shiro:lacksPermission name="mzb:mzbUnitinfo:check">
								<a href="${ctx}/mzb/mzbUnitinfo/form?id=${mzbUnitinfo.id}">修改</a>
							</shiro:lacksPermission>
						</c:when>
						<c:when test="${mzbUnitinfo.isself=='3'}">
							<shiro:hasPermission name="mzb:mzbUnitinfo:check">
								<a href="${ctx}/mzb/mzbUnitinfo/view?id=${mzbUnitinfo.id}">审核</a>
							</shiro:hasPermission>
							<shiro:lacksPermission name="mzb:mzbUnitinfo:check">
								审核中
							</shiro:lacksPermission>
						</c:when>
					</c:choose>
					<%--<a href="${ctx}/mzb/mzbUnitinfo/delete?id=${mzbUnitinfo.id}" onclick="return confirmx('确认要删除该基本信息吗？', this.href)">删除</a>--%>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>