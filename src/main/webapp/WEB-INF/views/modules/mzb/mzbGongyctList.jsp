<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>公益创投管理</title>
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
		<li class="active"><a href="${ctx}/mzb/mzbGongyct/list?status=${status}">公益创投列表</a></li>
		<shiro:hasPermission name="mzb:mzbGongyct:edit">
			<c:if test="${status=='0'.toString()}">
				<li><a href="${ctx}/mzb/mzbGongyct/form">公益创投添加</a></li>
			</c:if>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="mzbGongyct" action="${ctx}/mzb/mzbGongyct/?status=${status}" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>创建时间：</label>
				<input name="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${mzbGongyct.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${mzbGongyct.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>标题</th>
				<th>申请内容</th>
				<th>提交人</th>
				<th>组织</th>
				<th>申请金额</th>
				<th>申请时间</th>
				<th>状态</th>
				<shiro:hasPermission name="mzb:mzbGongyct:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="mzbGongyct">
			<tr>
				<td>
					<a href="${ctx}/mzb/mzbGongyct/view?id=${mzbGongyct.id}">
						${mzbGongyct.title}
					</a>

				</td>
				<td>
						${mzbGongyct.summary}
				</td>
				<td>
						${mzbGongyct.createBy.name}
				</td>
				<td>
						${mzbGongyct.createBy.office.name}
				</td>

				<td>
					${mzbGongyct.cash}
				</td>
				<td>
					<fmt:formatDate value="${mzbGongyct.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<c:choose>
						<c:when test="${mzbGongyct.status == '1'.toString()}">
							审批不通过
						</c:when>
						<c:when test="${mzbGongyct.status == '2'.toString()}">
							审批通过
						</c:when>
						<c:otherwise>
							未审批
						</c:otherwise>
					</c:choose>
				</td>
				<shiro:hasPermission name="mzb:mzbGongyct:edit"><td>
					<shiro:hasPermission name="mzb:mzbGongyct:check">
						<c:choose>
							<c:when test="${mzbGongyct.status == '1'.toString()}">
								等待修改
							</c:when>
							<c:when test="${mzbGongyct.status == '2'.toString()}">
								<a href="${ctx}/mzb/mzbGongyct/view?id=${mzbGongyct.id}">查看</a>
							</c:when>
							<c:otherwise>
								<a href="${ctx}/mzb/mzbGongyct/checkform?id=${mzbGongyct.id}">审批</a>
							</c:otherwise>
						</c:choose>
					</shiro:hasPermission>
					<shiro:lacksPermission name="mzb:mzbGongyct:check">
						<c:choose>
							<c:when test="${mzbGongyct.status == '2'.toString()}">
								<a href="${ctx}/mzb/mzbGongyct/view?id=${mzbGongyct.id}">查看</a>
							</c:when>
							<c:otherwise >
								<a href="${ctx}/mzb/mzbGongyct/form?id=${mzbGongyct.id}">修改</a>
								<a href="${ctx}/mzb/mzbGongyct/delete?id=${mzbGongyct.id}" onclick="return confirmx('确认要删除该公益创投吗？', this.href)">删除</a>
							</c:otherwise>
						</c:choose>
					</shiro:lacksPermission>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>