<%@ page import="com.thinkgem.jeesite.common.utils.DateUtils" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.thinkgem.jeesite.modules.sys.utils.DictUtils" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>年检公示管理</title>
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
	<c:set var="nowDate" value="<%=System.currentTimeMillis()%>"></c:set>
	<%--<c:set var="thisyearStr" value="<%=DictUtils.getDictLabel(\"年检结束时间\", \"yearcheck\", \"\")%>"></c:set>--%>
	<c:set var="startStr" value="<%=DateUtils.getYear() + DictUtils.getDictValue(\"年检开始时间\", \"yearcheck\", \"\")%>"></c:set>
	<c:set var="endStr" value="<%=DateUtils.getYear() + DictUtils.getDictValue(\"年检结束时间\", \"yearcheck\", \"\")%>"></c:set>
	<c:set var="starttime" value="${fns:parseDate(startStr)}"></c:set>
	<c:set var="endtime" value="${fns:parseDate(endStr)}"></c:set>
	<%--<c:set var="starttime" value="${fns:parseDate(fns:getDate('yyyy') +'-' + fns:getDictValue('年检开始时间', 'yearcheck', ''))}"></c:set>--%>
	<%--<c:set var="endtime" value="${fns:parseDate(fns:getDate('yyyy') +'-'+ fns:getDictValue('年检结束时间', 'yearcheck', ''))}"></c:set>--%>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/mzb/mzbYearcheck/uncheck">年检公示列表</a></li>
		<shiro:lacksPermission name="mzb:mzbYearcheck:admin">
			<c:if test="${page.list.size() < 1 and !isOld}">
				<c:if test="${nowDate - starttime.time >0 and nowDate - endtime.time <0}">
					 <li><a href="${ctx}/mzb/mzbYearcheck/form">年检公示添加</a></li>
				</c:if>
			</c:if>
		</shiro:lacksPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="mzbYearcheck" action="${ctx}/mzb/mzbYearcheck/${isOld?'check':'uncheck'}" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>归属用户：</label>
				<sys:treeselect id="user" name="user.id" value="${mzbYearcheck.user.id}" labelName="user.name" labelValue="${mzbYearcheck.user.name}"
								title="用户" url="/sys/office/treeData?type=3" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>创建时间：</label>
				<input name="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${mzbYearcheck.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${mzbYearcheck.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>

	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>年度</th>
				<th>创建人</th>
				<th>所属机构</th>
				<%--<th>状态</th>--%>
				<th>创建时间</th>
				<%--<th>审批意见</th>--%>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="mzbYearcheck">
			<tr>
				<td>
					<fmt:formatDate value="${mzbYearcheck.createDate}" pattern="yyyy年"/>
				</td>
				<td>
						${mzbYearcheck.createBy.name}
				</td>
				<td>
						${mzbYearcheck.createBy.office.name}
				</td>
				<%--<td>--%>
						<%--${fns:getDictLabel(mzbYearcheck.status, 'check_flag', '')}--%>
				<%--</td>--%>
				<td>
					<fmt:formatDate value="${mzbYearcheck.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<%--<td>--%>
					<%--${mzbYearcheck.remarks}--%>
				<%--</td>--%>
				<td>
					<%--<c:if test="${mzbYearcheck.status == '2'}"><a href="${ctx}/mzb/mzbYearcheck/form?id=${mzbYearcheck.id}">修改</a></c:if>--%>
					<a href="${ctx}/mzb/mzbYearcheck/view?id=${mzbYearcheck.id}">查看</a>
					<%--<c:if test="${mzbYearcheck.status != '3'}"><shiro:lacksRole name="d"><a href="${ctx}/mzb/mzbYearcheck/view?id=${mzbYearcheck.id}">审批</a></shiro:lacksRole></c:if>--%>
					<c:if test="${nowDate - starttime.time >0 and nowDate - endtime.time <0 and !isOld}"><a href="${ctx}/mzb/mzbYearcheck/delete?id=${mzbYearcheck.id}" onclick="return confirmx('确认要删除该年检申报吗？', this.href)">删除</a></c:if>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<c:if test="${nowDate - starttime.time <0 and !isOld}">
		<sys:message content="今年年报提交时间为${startStr}到${endStr},目前尚未开始" type="info"></sys:message>
	</c:if>

	<c:if test="${nowDate - starttime.time >0 and nowDate - endtime.time <0 and !isOld and page.list.size() < 1}">
		<sys:message content="本年度年报提交开始啦" type="info"></sys:message>
	</c:if>
	<c:if test="${ nowDate - endtime.time >0 and !isOld}">
		<sys:message content="今年年报提交时间为${startStr}到${endStr},目前已经结束" type="info"></sys:message>
	</c:if>
	<div class="pagination">${page}</div>
</body>
</html>