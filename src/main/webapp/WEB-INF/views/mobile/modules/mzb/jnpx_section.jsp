<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<section id="jnpx_section">
    <header>
        <nav class="left">
            <a href="#" data-icon="previous" data-target="back">返回</a>
        </nav>
        <h1 class="title">学习园地</h1>
    </header>
    <footer>
        <a  href="#study_model"  data-target="article"  class="active" data-icon="signup" >学习模块</a>
        <a  href="#test_model"  data-target="article" data-icon="radio-checked">测试模块</a>
    </footer>
    <article data-scroll="true"  id="baseinfo">
        <ul class="list inset" id="commList">

        </ul>
    </article>
    <article id="study_model" class="active">
             <ul class="list inset">
                <li class="divider grid middle" >
                    学习内容
                </li>
                <c:forEach items="${page.list}" var="mzbYearcheck" >
                    <li  data-selected="selected">
                        <a href="#mzbJnpx_content_section?id=${mzbYearcheck.id}"  data-target="section" >
                            <c:if test="${mzbYearcheck.test3 == '1'.toString()}" >
                                <div class="tag" style="background-color: #34DB6F">
                                    完成测试/${mzbYearcheck.test4}分
                                </div>
                            </c:if>
                            <c:if test="${mzbYearcheck.test3 == '0'.toString()}">
                                <div class="tag" style="background-color: #F31A1A">
                                    未完成测试/${mzbYearcheck.test4 == null ? "0":mzbYearcheck.test4}分
                                </div>
                            </c:if>

                            </div>
                            <strong > ${mzbYearcheck.title}</strong>
                            <p>
                                <span class="label">创建时间:<fmt:formatDate value="${mzbYearcheck.createDate}" pattern="yyyy年MM月dd日"/> </span>
                            </p>
                        </a>
                    </li>
                </c:forEach>
            </ul>
    </article>
    <article data-scroll="true" id="test_model" >
        <ul class="list inset">
            <li class="divider grid middle" >
                测试内容
            </li>
            <c:forEach items="${page.list}" var="mzbYearcheck" >
                <li  data-selected="selected">
                    <a href="#mzbJnpx_test_section?id=${mzbYearcheck.id}"  data-target="section" >
                        <c:if test="${mzbYearcheck.test3 == '1'.toString()}">
                        <div class="tag" style="background-color: #34DB6F">
                            <%--${fns:getDictLabel(mzbYearcheck.status, 'jnpx_type', '')}--%>
                                完成测试/${mzbYearcheck.test4}分
                        </div>
                        </c:if>
                            <c:if test="${mzbYearcheck.test3 == '0'.toString()}">
                            <div class="tag" style="background-color: #F31A1A">
                                未完成测试/${mzbYearcheck.test4 == null ? "0":mzbYearcheck.test4}分
                            </div>
                        </c:if>

                        </div>
                        <strong > ${mzbYearcheck.title}</strong>
                        <p>
                            <span class="label">创建时间:<fmt:formatDate value="${mzbYearcheck.createDate}" pattern="yyyy年MM月dd日"/> </span>
                        </p>
                    </a>
                </li>
            </c:forEach>
        </ul>
    </article>


    <script type="text/javascript">
	$('body').delegate('#jnpx_section','pageinit',function(){
	});


	$('body').delegate('#jnpx_section','pageshow',function(){
        var hash = J.Util.parseHash(location.hash);
        console.log(hash.param);
	});
	</script>



</section>