<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<section id="njsb_section">
    <header>
        <nav class="left">
            <a href="#" data-icon="previous" data-target="back">返回</a>
        </nav>
        <h1 class="title">年报公示</h1>
    </header>
    <footer>
        <a  href="#baseinfo"  data-target="article" data-icon="signup" >基本信息</a>
        <a  href="#thisyear"  data-target="article" class="active" data-icon="radio-checked">本年公示</a>
        <a  href="#otheryear"  data-target="article" data-icon="crop">往年公示</a>
    </footer>
    <article data-scroll="true"  id="baseinfo">
        <ul class="list inset" id="commList">

        </ul>
    </article>
    <article id="thisyear" class="active">

        <ul class="list inset">
            <li class="divider grid middle" >
                社会组织
            </li>
            <c:forEach items="${page.list}" var="mzbYearcheck" >
                <li  data-selected="selected">
                    <a href="#mzbYearcheck_view_section?id=${mzbYearcheck.id}"  data-target="section" >
                        <div class="tag"><fmt:formatDate value="${mzbYearcheck.createDate}" pattern="yyyy年"/> </div>
                        <strong > ${mzbYearcheck.createBy.name}</strong>
                    </a>
                </li>
            </c:forEach>
            </ul>
    </article>
    <article data-scroll="true" id="otheryear" >
        <ul class="list inset" id="yearcheckList">

        </ul>
    </article>


    <script type="text/javascript">
	$('body').delegate('#njsb_section','pageinit',function(){
        var lihtml="<li class='divider grid middle'>社会组织</li>"
        var yearCheckHtml="<li class='divider grid middle'>社会组织</li>"
        $.getJSON( 
                "mzb/mzbUnitinfo/getListJson", 
                function(json){ 
                    for(var i=0;i<json.length;i++){ 
                        var obj = json[i]; 
                        lihtml +=  "<li  data-selected='selected'><a href='#mzbUnitinfo_view_section?id=" + obj.id + "'  data-target='section' > <strong>" + obj.name + " </strong> </a> </li>"; 
                    } 
                    $("#commList").html(lihtml); 
                } );


        $.getJSON(
                "mzb/mzbYearcheck/getListJson",
                function(json){
                    for(var i=0;i<json.length;i++){
                        var obj = json[i];
                        var timestamp = obj.createDate;
                        var d = new Date(timestamp);    //根据时间戳生成的时间对象
                        var date = d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + (d.getDate()) + " " ;

                        yearCheckHtml +=  "<li  data-selected='selected'><a href='#mzbYearcheck_view_section?id=" + obj.id + "'  data-target='section' ><div class='tag'>"+ date+ " </div> <strong> " + obj.name  + " </strong> </a> </li>";
                    }
                    $("#yearcheckList").html(yearCheckHtml);
                } );
	});


	$('body').delegate('#njsb_section','pageshow',function(){
        var hash = J.Util.parseHash(location.hash);
        console.log(hash.param);
	});
	</script>



</section>