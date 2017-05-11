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
<section id="mzbGongyct_view_section" data-transition="flip">
    <header>
        <nav class="left">
            <a href="#" data-icon="previous" data-target="back">返回</a>
        </nav>
        <h1 class="title">公示情况</h1>
    </header>
    <article class="active" >
        <form:form id="inputForm" modelAttribute="mzbGongyct" action="${ctx}/mzb/mzbGongyct/save" method="post" class="input-group">
            <div class="input-row">
                <label >标题：</label>
                <form:input path="title" cols="29" style="overflow:auto"/>
            </div>


           <div class="input-row">
                <label >申请内容：</label>
                <div class="controls">
                    <form:textarea path="summary" rows="7" cols="29" style="overflow:auto"></form:textarea>
                </div>
            </div>
            <div class="input-row">
                <label >金额：</label>
                <div class="controls">
                    <form:input readonly="true" path="cash" htmlEscape="false" maxlength="255" />
                </div>
            </div>
            <div class="input-row">
                <label >申请时间：</label>
                <div class="controls">
                    <input readonly="true" name="dengjRq" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
                           value="<fmt:formatDate value="${mzbGongyct.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                           onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
                </div>
            </div>
            <div class="input-row">
                <label>附件:</label>
                <form:hidden id="files" path="files" htmlEscape="false" maxlength="255" class="input-xlarge"/>
                <sys:ckfinder input="files" type="files" uploadPath="/mzb/mzbGongyct" selectMultiple="true" readonly="true" />
            </div>
            <c:if test="${!empty mzbGongyct.remarks}">
                <div class="input-row">
                    <label >审批意见：</label>
                    <div class="controls">
                        <form:textarea path="remarks" htmlEscape="false" rows="6" maxlength="255" class="input-xxlarge "  cols="29" readonly="true"/>
                    </div>
                </div>
            </c:if>
        </form:form>

        <%----%>
       <%--<form:form id="inputForm" modelAttribute="mzbGongyct"  method="post" class="input-group">--%>
            <%--<div class="input-row">--%>
                <%--<label>年度:</label>--%>
                <%--<input type="text" value="<fmt:formatDate value="${mzbYearcheck.createDate}" pattern="yyyy年"/>" readonly>--%>
            <%--</div>--%>
            <%--<div class="input-row">--%>
                <%--<label>创建人:</label>--%>
                <%--<input type="text" value="${mzbYearcheck.createBy.name}" readonly>--%>
            <%--</div>--%>
            <%--<div class="input-row">--%>
                <%--<label>所属机构:</label>--%>
                <%--<input type="text" value="${mzbYearcheck.createBy.office.name}" readonly>--%>
            <%--</div>--%>
            <%--<div class="input-row">--%>
                <%--<label>创建时间:</label>--%>
                <%--<input type="text" value="<fmt:formatDate value="${mzbYearcheck.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>" readonly>--%>
            <%--</div>--%>
            <%--<div class="input-row">--%>
                <%--<label>附件:</label>--%>
                    <%--<form:hidden id="files" path="files" htmlEscape="false" maxlength="255" class="input-xlarge"/>--%>
                    <%--<sys:ckfinder input="files" type="files" uploadPath="/mzb/mzbYearcheck" selectMultiple="true" readonly="true" />--%>
            <%--</div>--%>
        <%--</form:form>--%>
       </article>
</section>