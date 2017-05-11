<%@ page import="com.thinkgem.jeesite.common.utils.StringUtils" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<section id="mzbJnpx_test_section" data-transition="flip">
    <header>
        <nav class="left">
            <a href="#" data-icon="previous" data-target="back">返回</a>
        </nav>
        <h1 class="title">${mzbJnpxMain.title}</h1>
<shiro:lacksPermission name="mzb:mzbJnpxMain:check">
        <nav class="right">
            <a href="#" class="button" data-icon="previous" id="mzbJnpx_prev"></a>
            <a href="#" class="button" data-icon="next" id="mzbJnpx_next"></a>
        </nav>
    <nav class="header-secondary">得分: <font size="5pt" color="#add8e6" id="score">0</font>分(合格成绩>=80分)
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="button" onclick="javascript:location.reload()" style="position: absolute;right: 10px;top:0px">重新答题</a>
    </nav>
    </shiro:lacksPermission>
    </header>
    <article class="active" >
    <shiro:hasPermission name="mzb:mzbJnpxMain:check">
        <c:if test="${mzbJnpxMain.mzbJnpxUserList.size()>0}">

        <div style="padding:10px 20px; overflow:auto">
            <div class="control-group">
                <div class="controls" style="width:100%" >
                    <table  border='1'cellspacing="0" cellpadding="0" style="width:100%" align="center">
                        <thead>
                        <tr>
                            <th>接受人</th>
                            <th>接受部门</th>
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
                                        ${fns:getDictLabel(mzbJnpxUser.isDone, 'yes_no', '')}
                                </td>
                                <td>
                                    ${mzbJnpxUser.doneDate}
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
        <shiro:lacksPermission name="mzb:mzbJnpxMain:check">
            <div id="allCount" hidden>${mzbJnpxMain.mzbJnpxTestList.size()}</div>
            <div id="userId" hidden>${fns:getUser().id}</div>
            <div id="mainId" hidden>${mzbJnpxMain.id}</div>
            <div id="mzbJnpx_test" class="slider">
            <div>
                <c:forEach items="${mzbJnpxTestList}" var="mzbJnpxTest" varStatus="abc" >
                    <div style="text-align: center;height: 100%;">
                        <ul class="list">
                            <li hidden >checkbox_${abc.index + 1}${mzbJnpxTest.answer}</li>
                            <li class="divider">问题${abc.index + 1}</li>
                            <li style="text-align: left;">
                                ${mzbJnpxTest.question}<br><br>
                            </li>
                            <li class="divider" onclick="setreadOnly()">测试</li>
                            <li  style="text-align: left;padding:20px">

                                <c:if test="${mzbJnpxTest.test1 != '' and mzbJnpxTest.test1 !=null}">
                                    <input   style="text-align: left;padding:10px"  name="checkbox_${abc.index + 1}" id="checkbox_${abc.index + 1}1"  type="radio"  /><label for="checkbox_${abc.index + 1}1">&nbsp;&nbsp;<span>1</span>.&nbsp;&nbsp;${mzbJnpxTest.test1}</label><font id="a_checkbox_${abc.index + 1}1" size='6pt' color='#7fffd4'></font><br><br>
                                </c:if>
                                <c:if test="${mzbJnpxTest.test2 != '' and mzbJnpxTest.test2 !=null}">
                               <input  style="text-align: left;padding:10px"  name="checkbox_${abc.index + 1}" id="checkbox_${abc.index + 1}2"  type="radio"  /><label for="checkbox_${abc.index + 1}2">&nbsp;&nbsp;<span>2</span>.&nbsp;&nbsp;${mzbJnpxTest.test2}</label><font id="a_checkbox_${abc.index + 1}2" size='6pt' color='#7fffd4'></font><br><br>
                                </c:if>
                                <c:if test="${mzbJnpxTest.test3 != '' and mzbJnpxTest.test3 !=null}">
                                 <input  style="text-align: left;padding:10px"  name="checkbox_${abc.index + 1}" id="checkbox_${abc.index + 1}3"  type="radio"  /><label for="checkbox_${abc.index + 1}3">&nbsp;&nbsp;<span>3</span>.&nbsp;&nbsp;${mzbJnpxTest.test3}</label><font id="a_checkbox_${abc.index + 1}3" size='6pt' color='#7fffd4'></font><br><br>
                                </c:if>
                                <c:if test="${mzbJnpxTest.test4 != '' and mzbJnpxTest.test4 !=null}">
                                   <input  style="text-align: left;padding:10px"  name="checkbox_${abc.index + 1}" id="checkbox_${abc.index + 1}4"  type="radio"  /><label for="checkbox_${abc.index + 1}4">&nbsp;&nbsp;<span>4</span>.&nbsp;&nbsp;${mzbJnpxTest.test4}</label><font id="a_checkbox_${abc.index + 1}4" size='6pt' color='#7fffd4'></font><br>
                                </c:if>
                                <br>
                                <c:if test="${isDone == '1'}">

                                    <a href="#" class="button block nephritis " >
                                        答案为:${mzbJnpxTest.answer}
                                    </a>
                                     </c:if>
                                <%--<c:if test="${isDone != '1'}">--%>
                                    <%--<br><br>--%>
                                    <%--成 绩: 分--%>
                                <%--</c:if>--%>
                            </li>
                            </ul>
                    </div>
                </c:forEach>
            </div>

        </div>
        </shiro:lacksPermission>
    </article>


    <script type="text/javascript">
        $('body').delegate('#mzbJnpx_test_section','pageinit',function(){
            var isdone = ${isDone};
            if(isdone == '1'){
                $("input[type=radio]").attr("disabled","true");
            }



            var allNum = $('#allCount').html();
            var userId = $('#userId').html();
            var mainId = $('#mainId').html();
            var rightNum =0; //正确的题数
            var score = 0; //分数
            var answerNum = 0;//回答的题数

            var slider;
            $('#mzbJnpx_test_section article').on('articleshow',function(){
                slider = new J.Slider({
                    selector : '#mzbJnpx_test',
                    onBeforeSlide : function(){
                        return true;
                    },
                    onAfterSlide : function(i){

                    }
                });
            });
            $('#mzbJnpx_prev').tap(function(){slider.prev()});
            $('#mzbJnpx_next').tap(function(){slider.next()});

            //新建一个新的对象

            $("input[type=radio]").click(function(){
                answerNum++;

                var thisId = $(this).attr("id");
                var answer = $(this).closest("ul").children(":first-child").html();
                if( thisId == answer)
                {
                    rightNum ++;


                    $(this).closest("div").toggleClass("show");
                    $(this).attr("disabled","true");
                    $(this).siblings().attr("disabled","true");

                    $('#a_'+thisId).html("√");

                    $("#score").html(Math.round(100*(rightNum/allNum)));
                    if(allNum == answerNum) {
                        $.getJSON(
                                "mzb/mzbJnpxMain/through",
                                {userId:userId,mainId:mainId,score:Math.round(100*(rightNum/allNum))},
                                function(json){
                                    var message1 = json.message1;
                                    J.showToast(message1,"info",2000);
                                } );
                        return;
                    }
                    J.showToast('回答正确,准备下一题...',"success",1500);
                    setTimeout(function(){slider.next()}, 1500);
                }else{

                    $(this).attr("disabled","true");
                    $(this).siblings().attr("disabled","true");
                    $('#a_'+thisId).html("×");
                    if(allNum == answerNum) {
                        $.getJSON(
                                "mzb/mzbJnpxMain/through",
                                {userId:userId,mainId:mainId,score:Math.round(100*(rightNum/allNum))},
                                function(json){
                                    var message1 = json.message1;
                                    J.showToast(message1,"info",2000);
                                } );
                       // J.confirm('提示','您的得分是' + Math.round(100*(rightNum/allNum)) + ',未通过测试,是否重新测试！',function(){location.reload();},function(){});
                        return;
                    }
                    J.showToast('回答错误,准备下一题...','error',1500);
                    setTimeout(function(){slider.next()}, 1500);
                }
            })

        });


        $('body').delegate('#mzbJnpx_test_section','pageshow',function(){
            var hash = J.Util.parseHash(location.hash);

            console.log(hash.param);
        });

    </script>


</section>

