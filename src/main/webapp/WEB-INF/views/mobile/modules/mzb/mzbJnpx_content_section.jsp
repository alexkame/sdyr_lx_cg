<%@ page import="com.thinkgem.jeesite.common.utils.StringUtils" %>
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
<section id="mzbJnpx_content_section" data-transition="flip">
    <header>
        <nav class="left">
            <a href="#" data-icon="previous" data-target="back">返回</a>
        </nav>
        <h1 class="title">${mzbJnpxMain.title}</h1>
    </header>
    <article class="active" >
        <form:form id="inputForm" modelAttribute="mzbJnpxMain" action="${ctx}/mzb/mzbJnpxMain/save" method="post" class="input-group">
            <div class="input-row">
            </div>
           <html style="padding:20px">
               <%--<%=StringUtils.substringBeforeLast()%>--%>
               ${mzbJnpxMain.content}
           </html>

            <div class="input-row">
                <label>附件:</label>
                <form:hidden id="files" path="files" htmlEscape="false" maxlength="255" class="input-xlarge"/>
                <sys:ckfinder input="files" type="files" uploadPath="/mzb/mzbJnpxMain" selectMultiple="true" readonly="true" />
            </div>
            <a href="#mzbJnpx_test_section?id=${mzbJnpxMain.id}"  data-target="section" class="button block peter-river" >
                <shiro:lacksPermission name="mzb:mzbJnpxMain:check">
                    开始测试
                </shiro:lacksPermission>
                <shiro:hasPermission name="mzb:mzbJnpxMain:check">
                    查看完成情况
                </shiro:hasPermission>
            </a>
        </form:form>

        <%----%>
       <%--<form:form id="inputForm" modelAttribute="mzbJnpxMain"  method="post" class="input-group">--%>
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