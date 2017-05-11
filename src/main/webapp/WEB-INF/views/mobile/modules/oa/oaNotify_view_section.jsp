<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<style>
    a{
        color: #208A98;
    }

    th,td{
        text-align:center;
    }
</style>
<section id="oaNotify_view_section" data-transition="flip">
    <header>
        <nav class="left">
            <a href="#" data-icon="previous" data-target="back">Back</a>
        </nav>
        <h1 class="title">用户列表</h1>
    </header>
    <article class="active" >
       <form:form id="inputForm" modelAttribute="oaNotify" action="${ctx}/oa/oaNotify/save" method="post" class="input-group">
            <div class="input-row">
                <label>类型:</label>
                <input type="text" value="${fns:getDictLabel(oaNotify.type, 'oa_notify_type', '')}" readonly>
            </div>
            <div class="input-row">
                <label>标题:</label>
                <input type="text" value="${oaNotify.title}" readonly>
            </div>
            <div class="input-row">
                <label>内容:</label>
                <textarea  rows="7" cols="29" style="overflow:auto">${oaNotify.content}</textarea>
            </div>
            <div class="input-row">
                <label>开始时间:</label>
                <input type="text" value="<fmt:formatDate value="${oaNotify.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" readonly>
            </div>
            <div class="input-row">
                <label>结束时间:</label>
                <input type="text" value="<fmt:formatDate value="${oaNotify.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" readonly>
            </div>
            <div class="input-row">
                <label>附件:</label>
                <c:if test="${oaNotify.files != null}">

                </c:if>
                <c:choose>
                    <c:when test="${oaNotify.files != null}">
                        <form:hidden id="files" path="files" htmlEscape="false" maxlength="255" class="input-xlarge"/>
                        <sys:ckfinder input="files" type="files" uploadPath="/oa/notify" selectMultiple="true" readonly="true" />
                    </c:when>
                    <c:otherwise>
                        无
                    </c:otherwise>
                </c:choose>
            </div>
        </form:form>
        <br><br>

        <c:if test="${oaNotify.status eq '1'}">
            <shiro:hasPermission name="oa:oaNotify:edit">
                <a href="#" class="button block wet-asphalt" id="btn_oaNotify_look">委员查阅情况</a>
            </shiro:hasPermission>
        </c:if>
    </article>

    <script type="text/html" id="popup_oaNotify_look">
        <div style="padding:10px 20px; overflow:auto">
            <div class="control-group">
                <div class="controls" style="width:100%" >
                    <table  border='1'cellspacing="0" cellpadding="0" style="width:100%" align="center">
                        <thead>
                        <tr >
                            <th width="25%">接受人</th>
                            <th width="25%">接受部门</th>
                            <th width="25%">阅读状态</th>
                            <th width="25%">阅读时间</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${oaNotify.oaNotifyRecordList}" var="oaNotifyRecord">
                            <tr>
                                <td>
                                        ${oaNotifyRecord.user.name}
                                </td>
                                <td>
                                        ${oaNotifyRecord.user.office.name}
                                </td>
                                <td>
                                        ${fns:getDictLabel(oaNotifyRecord.readFlag, 'oa_notify_read', '')}
                                </td>
                                <td>
                                    <fmt:formatDate value="${oaNotifyRecord.readDate}" pattern="yyyy-MM-dd"/>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    已查阅：${oaNotify.readNum} &nbsp; 未查阅：${oaNotify.unReadNum} &nbsp; 总共：${oaNotify.readNum + oaNotify.unReadNum}
                </div>
            </div>
        </div>
    </script>
    <script type="text/javascript" src="${ctxStatic}/jingle/js/lib/template.min.js"></script>
</section>