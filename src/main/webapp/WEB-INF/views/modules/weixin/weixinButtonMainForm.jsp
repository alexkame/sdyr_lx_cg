<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>菜单管理</title>
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
		function addRow(list, idx, tpl, row){
			$(list).append(Mustache.render(tpl, {
				idx: idx, delBtn: true, row: row
			}));
			$(list+idx).find("select").each(function(){
				$(this).val($(this).attr("data-value"));
			});
			$(list+idx).find("input[type='checkbox'], input[type='radio']").each(function(){
				var ss = $(this).attr("data-value").split(',');
				for (var i=0; i<ss.length; i++){
					if($(this).val() == ss[i]){
						$(this).attr("checked","checked");
					}
				}
			});
		}
		function delRow(obj, prefix){
			var id = $(prefix+"_id");
			var delFlag = $(prefix+"_delFlag");
			if (id.val() == ""){
				$(obj).parent().parent().remove();
			}else if(delFlag.val() == "0"){
				delFlag.val("1");
				$(obj).html("&divide;").attr("title", "撤销删除");
				$(obj).parent().parent().addClass("error");
			}else if(delFlag.val() == "1"){
				delFlag.val("0");
				$(obj).html("&times;").attr("title", "删除");
				$(obj).parent().parent().removeClass("error");
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/weixin/weixinButtonMain?remarks=${weixinButtonMain.remarks}">菜单列表</a></li>
		<li class="active"><a href="${ctx}/weixin/weixinButtonMain/form?id=${weixinButtonMain.id}">菜单<shiro:hasPermission name="weixin:weixinButtonMain:edit">${not empty weixinButtonMain.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="weixin:weixinButtonMain:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="weixinButtonMain" action="${ctx}/weixin/weixinButtonMain/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="20" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">类型：</label>
			<div class="controls">
				<form:radiobuttons path="type" items="${fns:getDictList('menu_type')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
			</div>
		</div>
		<div class="control-group" style="display:none">
			<label class="control-label">标记</label>
			<div class="controls">
				<form:input path="remarks" htmlEscape="false" maxlength="20" class="input-xlarge " />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">键值：</label>
			<div class="controls">
				<form:input path="key" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">跳转路径：</label>
			<div class="controls">
				<form:textarea path="url" htmlEscape="false" maxlength="2000" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">媒体id：</label>
			<div class="controls">
				<form:input path="mediaId" htmlEscape="false" maxlength="2000" class="input-xlarge "/>
			</div>
		</div>
			<div class="control-group">
				<label class="control-label">业务数据子表：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>类型</th>
								<th>名称</th>
								<th>键值</th>
								<th>跳转路径</th>
								<th>媒体id</th>
								<shiro:hasPermission name="weixin:weixinButtonMain:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="weixinButtonSubList">
						</tbody>
						<shiro:hasPermission name="weixin:weixinButtonMain:edit"><tfoot>
							<tr><td colspan="7"><a href="javascript:" onclick="addRow('#weixinButtonSubList', weixinButtonSubRowIdx, weixinButtonSubTpl);weixinButtonSubRowIdx = weixinButtonSubRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="weixinButtonSubTpl">//<!--
						<tr id="weixinButtonSubList{{idx}}">
							<td class="hide">
								<input id="weixinButtonSubList{{idx}}_id" name="weixinButtonSubList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="weixinButtonSubList{{idx}}_delFlag" name="weixinButtonSubList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<c:forEach items="${fns:getDictList('menu_type')}" var="dict" varStatus="dictStatus">
									<span><input id="weixinButtonSubList{{idx}}_type${dictStatus.index}" name="weixinButtonSubList[{{idx}}].type" type="radio" value="${dict.value}" data-value="{{row.type}}"><label for="weixinButtonSubList{{idx}}_type${dictStatus.index}">${dict.label}</label></span>
								</c:forEach>
							</td>
							<td>
								<input id="weixinButtonSubList{{idx}}_name" name="weixinButtonSubList[{{idx}}].name" type="text" value="{{row.name}}" maxlength="20" class="input-small required"/>
							</td>
							<td>
								<input id="weixinButtonSubList{{idx}}_key" name="weixinButtonSubList[{{idx}}].key" type="text" value="{{row.key}}" maxlength="20" class="input-small "/>
							</td>
							<td>
								<input id="weixinButtonSubList{{idx}}_url" name="weixinButtonSubList[{{idx}}].url" type="text" value="{{row.url}}" maxlength="200" class="input-small "/>
							</td>
							<td>
								<input id="weixinButtonSubList{{idx}}_mediaId" name="weixinButtonSubList[{{idx}}].mediaId" type="text" value="{{row.mediaId}}" maxlength="200" class="input-small "/>
							</td>
							<shiro:hasPermission name="weixin:weixinButtonMain:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#weixinButtonSubList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var weixinButtonSubRowIdx = 0, weixinButtonSubTpl = $("#weixinButtonSubTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(weixinButtonMain.weixinButtonSubList)};
							for (var i=0; i<data.length; i++){
								addRow('#weixinButtonSubList', weixinButtonSubRowIdx, weixinButtonSubTpl, data[i]);
								weixinButtonSubRowIdx = weixinButtonSubRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
		<div class="form-actions">
			<shiro:hasPermission name="weixin:weixinButtonMain:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>