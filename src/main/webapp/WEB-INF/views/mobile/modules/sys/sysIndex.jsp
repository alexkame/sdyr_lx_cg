<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<section id="index_section">
	<header>
        <h1 class="title">您好,${fns:getUser().name}</h1>
        <nav class="right">
            <a data-icon="arrow-down-left-2" href="#" id="btnLogout">退出</a>
        </nav>
    </header>
    <footer>
        <a  href="#njsb_section?model=njsb" data-target="section" data-icon="signup" >年检公示</a>
        <a  href="#fcsq_section?status=0" data-target="section" data-icon="radio-checked">扶持申请</a>
        <a  href="#gyct_section?status=0" data-target="section" data-icon="crop">公益创投</a>
        <a  href="#jnpx_section" data-target="section" data-icon="pencil">学习园地</a>
        <shiro:hasPermission name="oa:oaNotify:edit"><a  href="#dqyj_section?model=dqyj" data-target="section"   data-icon="cogs">到期预警</a></shiro:hasPermission>
    </footer>
    <article class="active" data-scroll="true">
        <div style="padding: 10px 0 20px;">
        <ul class="list inset">
                <li class="divider grid middle" data-icon="bell">
                    <div id="titleTag" class="tag" style="top:5px;background-color:red">
                         <span id="notifyNum">
                         </span>
                    </div>
                    通知公告
                </li>
            <%--1.社会组织--%>
            <%
                //用来判断
                int i = 0;
            %>
            <shiro:lacksPermission name="oa:oaNotify:edit">
                <c:set var="nowDate1" value="<%=System.currentTimeMillis()%>"></c:set>
                <c:forEach items="${page.list}" var="oaNotify" >
                    <c:if test="${nowDate1-oaNotify.endTime.time < 0 }">
                        <%
                          i++;
                        %>
                        <li data-icon="next" data-selected="selected">
                            <a href="#oaNotify_view_section?id=${oaNotify.id}"  data-target="section" >
                                <div class="tag">${fns:getDictLabel(oaNotify.type, 'oa_notify_type', '')} </div>
                                <strong data-icon="checkbox-${oaNotify.readFlag=='0' ? 'un':''}checked"> ${fns:abbr(oaNotify.title,50)}</strong>
                                <p>${fns:abbr(oaNotify.content,50)}</p>
                                <p>
                                    <span class="label">起:<fmt:formatDate value="${oaNotify.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
                                    <span class="label">止:<fmt:formatDate value="${oaNotify.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
                                </p>
                            </a>
                        </li>
                    </c:if>

                </c:forEach>

            </shiro:lacksPermission>
            <%--2.民政局工作人员--%>
            <shiro:hasPermission name="oa:oaNotify:edit">
                <c:set var="nowDate" value="<%=System.currentTimeMillis()%>"></c:set>
                <c:forEach items="${page.list}" var="oaNotify" >
                    <%--判断公告是否已经过期--%>
                    <c:if test="${nowDate - oaNotify.endTime.time < 0  }">
                        <%
                            i++;
                        %>
                        <li data-icon="next" data-selected="selected">
                            <a href="#oaNotify_view_section?id=${oaNotify.id}" data-target="section" >
                                <div class="tag">${fns:getDictLabel(oaNotify.type, 'oa_notify_type', '')}(${oaNotify.readNum} / ${oaNotify.readNum + oaNotify.unReadNum})</div>
                                <strong>${fns:abbr(oaNotify.title,50)}</strong>
                                <p>${fns:abbr(oaNotify.content,50)}</p>
                                <p>
                                    <span class="label">起:<fmt:formatDate value="${oaNotify.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
                                    <span class="label">止:<fmt:formatDate value="${oaNotify.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
                                </p>
                            </a>
                        </li>
                    </c:if>
                </c:forEach>
            </shiro:hasPermission>
            <%
                if(i == 0){
            %>
            <li  data-selected="selected">
                    暂无通告
            </li>
            <%
                }
            %>
        </ul>
        </div>
    </article>
    <script type="text/javascript">
   		$('#btnLogout').tap(function(){
   			J.confirm('确认提示','确认要退出吗？',function(){
   				$.get("${ctx}/logout", function(){
   					sessionid = '';
   					J.showToast('退出成功！', 'success');
   					J.Router.goTo('#login_section');
   				});
   			});
   		});
        $(document).ready(function() {
        // 获取通知数目  <c:set var="oaNotifyRemindInterval" value="${fns:getConfig('oa.notify.remind.interval')}"/>
        function getNotifyNum(){
            $.get("${ctx}/oa/oaNotify/self/count?updateSession=0&t="+new Date().getTime(),function(data){
                var num = parseFloat(data);
                if (num > 0){
                    $("#notifyNum").show().html("未阅:" + num + "条");
                    $("#titleTag").show();
                }else{
                    $("#notifyNum").hide();
                    $("#titleTag").hide();
                }
            });
        }
        getNotifyNum(); //<c:if test="${oaNotifyRemindInterval ne '' && oaNotifyRemindInterval ne '0'}">
        setInterval(getNotifyNum, ${oaNotifyRemindInterval}); //</c:if>
        });
    </script>
</section>