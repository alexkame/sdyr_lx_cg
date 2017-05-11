<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>基本信息管理</title>
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
		<li><a href="${ctx}/mzb/mzbUnitinfo/">基本信息列表</a></li>
		<li class="active"><a href="${ctx}/mzb/mzbUnitinfo/form?id=${mzbUnitinfo.id}">提交申请</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="mzbUnitinfo" action="${ctx}/mzb/mzbUnitinfo/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">提交申请：</label>
			<div class="controls">
				<form:textarea  path="remarks" htmlEscape="false" rows="4" maxlength="255" readonly="${mzbUnitinfo.isself eq '1'?'true':'false'}" class="input-xxlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
		<div class="form-actions">
			<shiro:hasPermission name="mzb:mzbUnitinfo:check">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="同意修改"/>
			</shiro:hasPermission>
			<shiro:lacksPermission name="mzb:mzbUnitinfo:check">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="提 交"/>
			</shiro:lacksPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>