<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>学习园地管理</title>
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
		<li><a href="${ctx}/mzb/mzbJnpxMain/">学习园地列表</a></li>
		<li class="active"><a href="${ctx}/mzb/mzbJnpxMain/form?id=${mzbJnpxMain.id}">学习园地<shiro:hasPermission name="mzb:mzbJnpxMain:edit">${not empty mzbJnpxMain.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="mzb:mzbJnpxMain:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="mzbJnpxMain" action="${ctx}/mzb/mzbJnpxMain/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<%--<div class="control-group">--%>
			<%--<label class="control-label">类别：</label>--%>
			<%--<div class="controls">--%>
				<%--<form:select path="status" class="input-xlarge required ">--%>
					<%--<form:option value="" label=""/>--%>
					<%--<form:options items="${fns:getDictList('jnpx_type')}" itemLabel="label" itemValue="value"  htmlEscape="false"/>--%>
				<%--</form:select>--%>
			<%--</div>--%>
		<%--</div>--%>
		<div class="control-group">
			<label class="control-label">标题：</label>
			<div class="controls">
				<form:input path="title" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">正文:</label>
			<div class="controls">
				<form:textarea path="content" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge required"/>
				<sys:ckeditor replace="content" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">附件：</label>
			<div class="controls">
				<form:hidden id="files" path="files" htmlEscape="false" maxlength="255" class="input-xlarge"/>
				<sys:ckfinder input="files" type="files" uploadPath="/mzb/mzbJnpxMain" selectMultiple="true"/>
			</div>
		</div>
		<shiro:hasPermission name="mzb:mzbJnpxMain:edit">


			<div class="control-group">
				<label class="control-label">测试题：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>问题</th>
								<th>答案</th>
								<th>选项一</th>
								<th>选项二</th>
								<th>选项三</th>
								<th>选项四</th>
								<shiro:hasPermission name="mzb:mzbJnpxMain:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="mzbJnpxTestList">
						</tbody>
						<shiro:hasPermission name="mzb:mzbJnpxMain:edit"><tfoot>
							<tr><td colspan="8"><a href="javascript:" onclick="addRow('#mzbJnpxTestList', mzbJnpxTestRowIdx, mzbJnpxTestTpl);mzbJnpxTestRowIdx = mzbJnpxTestRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="mzbJnpxTestTpl">//<!--
						<tr id="mzbJnpxTestList{{idx}}">
							<td class="hide">
								<input id="mzbJnpxTestList{{idx}}_id" name="mzbJnpxTestList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="mzbJnpxTestList{{idx}}_delFlag" name="mzbJnpxTestList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<textarea id="mzbJnpxTestList{{idx}}_question" name="mzbJnpxTestList[{{idx}}].question"  rows="4" maxlength="255" class="input ">{{row.question}}</textarea>
							</td>
							<td>
								<select id="mzbJnpxTestList{{idx}}_answer" name="mzbJnpxTestList[{{idx}}].answer" data-value="{{row.answer}}" class="input-small required ">
									<option value=""></option>
									<c:forEach items="${fns:getDictList('answer_type')}" var="dict">
										<option value="${dict.value}">${dict.label}</option>
									</c:forEach>
								</select>
							</td>
							<td>
								<textarea id="mzbJnpxTestList{{idx}}_test1" name="mzbJnpxTestList[{{idx}}].test1" rows="4" maxlength="255" class="input-small ">{{row.test1}}</textarea>
							</td>
							<td>
								<textarea id="mzbJnpxTestList{{idx}}_test2" name="mzbJnpxTestList[{{idx}}].test2" rows="4" maxlength="255" class="input-small ">{{row.test2}}</textarea>
							</td>
							<td>
								<textarea id="mzbJnpxTestList{{idx}}_test3" name="mzbJnpxTestList[{{idx}}].test3" rows="4" maxlength="255" class="input-small ">{{row.test3}}</textarea>
							</td>
							<td>
								<textarea id="mzbJnpxTestList{{idx}}_test4" name="mzbJnpxTestList[{{idx}}].test4" rows="4" maxlength="255" class="input-small ">{{row.test4}}</textarea>
							</td>
							<shiro:hasPermission name="mzb:mzbJnpxMain:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#mzbJnpxTestList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var mzbJnpxTestRowIdx = 0, mzbJnpxTestTpl = $("#mzbJnpxTestTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(mzbJnpxMain.mzbJnpxTestList)};
							for (var i=0; i<data.length; i++){
								addRow('#mzbJnpxTestList', mzbJnpxTestRowIdx, mzbJnpxTestTpl, data[i]);
								mzbJnpxTestRowIdx = mzbJnpxTestRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
		<div class="control-group">
			<label class="control-label">接受人：</label>
			<div class="controls">
				<sys:treeselect id="mzbJnpxUser" name="mzbJnpxUserIds" value="${mzbJnpxMain.mzbJnpxUserIds}" labelName="getMzbJnpxUserNames" labelValue="${mzbJnpxMain.mzbJnpxUserNames}"
								title="用户" url="/sys/office/treeData?type=3" cssClass="input-xxlarge required" notAllowSelectParent="true" checked="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<c:if test="${mzbJnpxMain.mzbJnpxUserList.size()>0}">


			<div class="control-group">
				<label class="control-label">完成情况：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
						<tr>
							<th>接受人</th>
							<th>接受部门</th>
							<th>分数</th>
							<th>是否完成</th>
							<th>完成时间</th>
						</tr>
						</thead>
						<tbody>
						<c:forEach items="${mzbJnpxMain.mzbJnpxUserList}" var="mzbJnpxUser">
							<tr>
								<td>
										${mzbJnpxUser.user.name}
								</td>
								<td>
										${mzbJnpxUser.user.office.name}
								</td>
								<td>
										${mzbJnpxUser.test1}
								</td>
								<td>
										${fns:getDictLabel(mzbJnpxUser.isDone, 'yes_no', '')}
								</td>
								<td>
									${mzbJnpxUser.doneDate}
									<%--<fmt:formatDate value="${mzbJnpxUser.doneDate}" pattern="yyyy-MM-dd HH:mm:ss"/>--%>
								</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
					<%--已查阅：${oaNotify.readNum} &nbsp; 未查阅：${oaNotify.unReadNum} &nbsp; 总共：${oaNotify.readNum + oaNotify.unReadNum}--%>
				</div>
			</div>
		</c:if>
		</shiro:hasPermission>
		<div class="form-actions">
			<shiro:hasPermission name="mzb:mzbJnpxMain:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>