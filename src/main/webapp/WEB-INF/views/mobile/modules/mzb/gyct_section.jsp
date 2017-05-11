<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<section id="gyct_section">
    <header>
        <nav class="left">
            <a href="#" data-icon="previous" data-target="back">返回</a>
        </nav>
        <h1 class="title">公益创投</h1>
    </header>
    <footer>
        <a  href="#gyct1"  data-target="article" class="active" data-icon="info-2" >待审核</a>
        <a  href="#gyct2"  data-target="article" data-icon="cancel-circle" >审核不通过</a>
        <a  href="#gyct3"  data-target="article"  data-icon="checkmark-circle" >审核通过</a>
    </footer>
    <article data-scroll="true" class="active" id="gyct1">
        <ul class="list inset">
            <li class="divider grid middle" >
                未审核列表
                <div id="content"></div>
            </li>
            <c:forEach items="${page.list}" var="mzbYearcheck" >
                <li  data-selected="selected">
                    <a href="#mzbGongyct_view_section?id=${mzbYearcheck.id}"  data-target="section" >
                        <div class="tag">${mzbYearcheck.createBy.office.name}</div>
                        <strong > ${mzbYearcheck.title}</strong>
                        <p>${fns:abbr(mzbYearcheck.summary,50)}</p>
                        <p>
                            <span class="label">提交时间:<fmt:formatDate value="${mzbYearcheck.createDate}" pattern="yyyy年MM月dd日"/> </span>
                        </p>
                    </a>
                </li>
            </c:forEach>
        </ul>
    </article>
    <article  id="gyct2" >
        <ul class="list inset" id="gunpass">

        </ul>
    </article>
    <article  id="gyct3" >
        <ul class="list inset" id="gpass">

        </ul>
    </article>


    <script type="text/javascript">
	$('body').delegate('#gyct_section','pageinit',function(){
        var unpass="<li class='divider grid middle'>未通过列表</li>"
        var pass="<li class='divider grid middle'>通过列表</li>"
        $.getJSON(
                "mzb/mzbGongyct/getListJson",
                {status:"1"},
                function(json){
                    for(var i=0;i<json.length;i++){
                        var obj = json[i];
                        unpass +=  template('gyctpass',obj);
                    }
                    $("#gunpass").html(unpass);
                } );


        $.getJSON(
                "mzb/mzbGongyct/getListJson",
                {status:"2"},
                function(json){
                    for(var i=0;i<json.length;i++){

                        var obj = json[i];
                        pass +=  template('gyctpass',obj);
                    }
                    $("#gpass").html(pass);
                } );
	});


	$('body').delegate('#gyct_section','pageshow',function(){
        var hash = J.Util.parseHash(location.hash);
        console.log(hash.param);
	});
	</script>

    <script type="text/html" id="gyctpass">
        <li  data-selected="selected">
            <a href="#mzbGongyct_view_section?id={{id}}"  data-target="section" >
                <div class="tag">{{officeName}}</div>
                <strong > {{title}}</strong>
                <p>{{summary}}</p>
                <p>
                    <span class="label">提交时间:{{createDate}}</span>
                </p>
            </a>
        </li>

    </script>


</section>