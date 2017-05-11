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
<section id="mzbUnitinfo_view_section" data-transition="flip">
    <header>
        <nav class="left">
            <a href="#" data-icon="previous" data-target="back">返回</a>
        </nav>
        <h1 class="title">公示情况</h1>
    </header>
    <article class="active" >
        <form:form id="inputForm" modelAttribute="mzbUnitinfo" action="${ctx}/mzb/mzbUnitinfo/save" method="post" class="input-group">
            <div class="input-row">
                <label >单位名称：</label>
                <div >
                    <input type="text" value="${mzbUnitinfo.danwMc}" readonly>
                </div>
            </div>
           <div class="input-row">
                <label >登记证号：</label>
                <div class="controls">
                    <form:input readonly="true" path="dengjzh" htmlEscape="false" maxlength="255" />
                </div>
            </div>
            <div class="input-row">
                <label >登记日期：</label>
                <div class="controls">
                    <input readonly="true" name="dengjRq" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
                           value="<fmt:formatDate value="${mzbUnitinfo.dengjRq}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                           onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
                </div>
            </div>
            <div class="input-row">
                <label >开办资金：</label>
                <div class="controls">
                    <form:input readonly="true" path="kaibZj" htmlEscape="false" />
                </div>
            </div>
            <div class="input-row">
                <label >住所：</label>
                <div class="controls">
                    <form:textarea readonly="true" path="zhus" htmlEscape="false" rows="4" maxlength="255" />
                </div>
            </div>
            <div class="input-row">
                <label >单位电话：</label>
                <div class="controls">
                    <form:input readonly="true" path="danwDh" htmlEscape="false" maxlength="255" />
                </div>
            </div>
            <div class="input-row">
                <label >法定代表人：</label>
                <div class="controls">
                    <form:input readonly="true" path="fadDbr" htmlEscape="false" maxlength="255" />
                </div>
            </div>
            <div class="input-row">
                <label >业务主管单位：</label>
                <div class="controls">
                    <form:input readonly="true" path="yewzgDw" htmlEscape="false" maxlength="255" />
                </div>
            </div>
            <div class="input-row">
                <label >宗旨：</label>
                <div class="controls">
                    <form:textarea readonly="true" path="zhongz" htmlEscape="false" rows="4" maxlength="255" />
                </div>
            </div>
            <div class="input-row">
                <label >业务范围：</label>
                <div class="controls">
                    <form:textarea readonly="true" path="yewFw" htmlEscape="false" rows="4" maxlength="255" />
                </div>
            </div>
            <div class="input-row">
                <label >统一社会信用代码：</label>
                <div class="controls">
                    <form:input readonly="true" path="tongyDm" htmlEscape="false" maxlength="255" />
                </div>
            </div>
        </form:form>


        <%----%>
       <%--<form:form id="inputForm" modelAttribute="mzbUnitinfo"  method="post" class="input-group">--%>
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