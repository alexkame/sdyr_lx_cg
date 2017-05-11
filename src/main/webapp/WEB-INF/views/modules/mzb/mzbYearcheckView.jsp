<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>年检申报管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/mzb/mzbYearcheck/">年检申报列表</a></li>
		<li class="active"><a href="${ctx}/mzb/mzbYearcheck/form?id=${mzbYearcheck.id}">年检申报审批<shiro:lacksPermission name="mzb:mzbYearcheck:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="mzbYearcheck" action="${ctx}/mzb/mzbYearcheck/checked" method="post" class="form-horizontal">
		<form:hidden path="id"/><form:hidden path="status"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">附件：</label>
			<div class="controls">
				<form:hidden id="files" path="files" htmlEscape="false" maxlength="2000" class="input-xlarge"/>
				<sys:ckfinder input="files" type="files" uploadPath="/mzb/mzbYearcheck" selectMultiple="true" readonly="true"/>
			</div>
		</div>
		<%--<div class="control-group">--%>
			<%--<label class="control-label">审批意见:</label>--%>
			<%--<div class="controls">--%>
				<%--<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>--%>
			<%--</div>--%>
		<%--</div>--%>
		<div class="form-actions">
			<%--<input id="btnSubmit" class="btn btn-primary" type="submit" onclick="$('#status').val('3');" value="通过"/>&nbsp;--%>
			<%--<input id="btnSubmit" class="btn btn-danger" type="submit" onclick="$('#status').val('2');" value="不通过"/>&nbsp;--%>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>