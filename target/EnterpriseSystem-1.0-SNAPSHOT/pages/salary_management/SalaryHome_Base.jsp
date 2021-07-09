
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>生产型企业信息化管理系统-后台主页</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui/css/layui.css?time=<%=Math.random()%>">
    <script src="${pageContext.request.contextPath}/resources/layui/layui.js?time=<%=Math.random()%>"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js?time=<%=Math.random()%>"></script>
    <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/js/Login.js?time=<%=Math.random()%>"></script>
    <script src="${pageContext.request.contextPath}/resources/js/clock.js?time=<%=Math.random()%>"></script>

    <script>
        layui.use('element', function(){
            var element = layui.element;
        });

        let startTime;
        let now = new Date();
        layui.use(['laydate', 'clock'], function () {
            let laydate = layui.laydate;
            laydate.render({
                elem: '#modifyDate',// input里时间的Id
                type: 'time',
                format: 'yyyy-MM-dd HH:mm:ss',
                value: new Date(),
                done: function (value, date) {
                }
            });

            laydate.render({
                elem: '#test-n1'
                ,position: 'static'
                ,calendar: true
            });
        });

    </script>
</head>
<body class="layui-layout-body">

    <div style="margin-left: 30px;">
        <div style="padding: 15px;margin-bottom: 5px;margin-left: -10px;margin-top: 10px;">
            <blockquote class="layui-elem-quote">
                <h1>您好，<c:if test="${userAccount.userType == 'manager'}">管理员</c:if><c:if test="${userAccount.userType == 'employee'}">员工</c:if>
                    <%--显示当前用户身份--%>

                    <span style="color: #40AFFE;">${userData.userName}</span> ！</h1>

            </blockquote>
        </div>
        <div class="index">
            <h1 style="margin-bottom: 20px;">欢迎使用工资管理系统</h1>
<%--            <h2>当前时间: 2021-6-4 16:50:33</h2>--%>
            <!--时间-->
            <h2>
                <section class="loadTime">
                    当前时间:&nbsp;&nbsp;<span id="time" style="color: red;">2015年1月1日 11:11  星期一</span>
                </section>
            </h2>


        </div>
    </div>

    <div style="margin-left: 30px;margin-top: 30px;font-size: 18px;">
        <div class="layui-inline" id="test-n1"></div>
    </div>

</body>

<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/js/time.js"></script>

</html>