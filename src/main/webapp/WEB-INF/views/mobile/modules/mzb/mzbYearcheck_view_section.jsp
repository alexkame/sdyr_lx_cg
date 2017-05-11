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
<section id="mzbYearcheck_view_section" data-transition="flip">
    <header>
        <nav class="left">
            <a href="#" data-icon="previous" data-target="back">返回</a>
        </nav>
        <h1 class="title">公示情况</h1>
    </header>
    <article class="active" >
       <form:form id="inputForm" modelAttribute="mzbYearcheck"  method="post" class="input-group">
            <div class="input-row">
                <label>年度:</label>
                <input type="text" value="<fmt:formatDate value="${mzbYearcheck.createDate}" pattern="yyyy年"/>" readonly>
            </div>
            <div class="input-row">
                <label>创建人:</label>
                <input type="text" value="${mzbYearcheck.createBy.name}" readonly>
            </div>
            <div class="input-row">
                <label>所属机构:</label>
                <input type="text" value="${mzbYearcheck.createBy.office.name}" readonly>
            </div>
            <div class="input-row">
                <label>创建时间:</label>
                <input type="text" value="<fmt:formatDate value="${mzbYearcheck.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>" readonly>
            </div>
            <div class="input-row">
                <label>附件:</label>
                    <form:hidden id="files" path="files" htmlEscape="false" maxlength="255" class="input-xlarge"/>
                    <sys:ckfinder input="files" type="files" uploadPath="mzb/mzbYearcheck" selectMultiple="true" readonly="true" />
            </div>
        </form:form>
       </article>
</section>