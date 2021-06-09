<%--
  Created by IntelliJ IDEA.
  User: 86150
  Date: 2021/6/7
  Time: 10:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/layui/css/layui.css?time=<%=Math.random()%>">
    <script src="${pageContext.request.contextPath}/resources/layui/layui.js?time=<%=Math.random()%>"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js?time=<%=Math.random()%>"></script>
    <script>
        layui.use(['util', 'laydate', 'layer'], function(){
            var util = layui.util
                ,laydate = layui.laydate
                ,$ = layui.$
                ,layer = layui.layer;

            let thisTimer, setCountdown = function(y, M, d, H, m, s){
                let endTime = new Date(y, M||0, d||1, H||0, m||0, s||0) //结束日期
                    ,serverTime = new Date(); //假设为当前服务器时间，这里采用的是本地时间，实际使用一般是取服务端的

                clearTimeout(thisTimer);
                util.countdown(endTime, serverTime, function(date, serverTime, timer){
                    let str = date[0] + '天' + date[1] + '时' +  date[2] + '分' + date[3] + '秒';
                    lay('#test2').html(str);
                    thisTimer = timer;
                });
            };
            setCountdown(2099,1,1);
        });
    </script>

</head>
<body>
<div class="" style="padding: 30px">
    <h1>欢迎使用销售管理系统</h1>
    <br/>
    <blockquote class="layui-elem-quote" style="margin-top: 10px;">
        <div id="test2"></div>
    </blockquote>
</div>
</body>
</html>
