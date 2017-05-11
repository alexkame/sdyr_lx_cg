<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>公益创投管理</title>
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
		<li><a href="${ctx}/mzb/mzbGongyct/list?status=0">公益创投列表</a></li>
		<li class="active"><a href="${ctx}/mzb/mzbGongyct/form?id=${mzbGongyct.id}">公益创投<shiro:hasPermission name="mzb:mzbGongyct:edit">${not empty mzbGongyct.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="mzb:mzbGongyct:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="mzbGongyct" action="${ctx}/mzb/mzbGongyct/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="status" value="0"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">标题：</label>
			<div class="controls">
				<form:input path="title" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">申请内容：</label>
			<div class="controls">
				<form:textarea path="summary" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">申请金额：</label>
			<div class="controls">
				<form:input path="cash" htmlEscape="false" maxlength="255" class="input-xlarge  number required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">附件：</label>
			<div class="controls">
				<form:hidden id="files" path="files" htmlEscape="false" maxlength="2000" class="input-xlarge"/>
				<sys:ckfinder input="files" type="files" uploadPath="/mzb/mzbGongyct" selectMultiple="true"/>
			</div>
		</div>
		<c:if test="${!empty mzbGongyct.remarks}">
			<div class="control-group">
				<label class="control-label">审批意见：</label>
				<div class="controls">
					<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge " readonly="true"/>
				</div>
			</div>
		</c:if>
		<div class="form-actions">
			<shiro:hasPermission name="mzb:mzbGongyct:edit"><td>
					<shiro:hasPermission name="mzb:mzbGongyct:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
					<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
			</td></shiro:hasPermission>

		</div>
	</form:form>
</body>
</html>