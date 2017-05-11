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
		<li><a href="${ctx}/mzb/mzbYearcheck/uncheck">年检申报列表</a></li>
		<li class="active"><a href="${ctx}/mzb/mzbYearcheck/form?id=${mzbYearcheck.id}">年检申报<shiro:hasPermission name="mzb:mzbYearcheck:edit">${not empty mzbYearcheck.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="mzb:mzbYearcheck:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="mzbYearcheck" action="${ctx}/mzb/mzbYearcheck/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>


		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">附件：</label>
			<div class="controls">
				<form:hidden id="files" path="files" htmlEscape="false" maxlength="2000" class="input-xlarge"/>
				<sys:ckfinder input="files" type="files" uploadPath="/mzb/mzbYearcheck" selectMultiple="true" readonly="${mzbYearcheck.status == '3' ? 'true' : 'false'}"/>
			</div>
		</div>

		<div class="form-actions">
			<c:if test="${mzbYearcheck.status != '3'}"><shiro:hasPermission name="mzb:mzbYearcheck:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission></c:if>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>