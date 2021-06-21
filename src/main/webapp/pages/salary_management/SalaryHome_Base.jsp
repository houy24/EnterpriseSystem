
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

    <script>
        layui.use('element', function(){
            var element = layui.element;
        });
    </script>
</head>
<body class="layui-layout-body">

    <div style="margin-left: 30px;">
        <div style="padding: 15px;margin-bottom: 5px;margin-left: -10px;margin-top: 10px;">
            <blockquote class="layui-elem-quote">
                <h1>您好，管理员 <span style="color: #40AFFE;">${userData.userName}</span> ！</h1>
            </blockquote>
        </div>
        <div class="index">
            <h2 style="margin-bottom: 20px;">欢迎使用生产型企业信息化管理系统后台-工资管理系统</h2>
<%--            <h2>当前时间: 2021-6-4 16:50:33</h2>--%>
            <!--时间-->
            <h2>
                <section class="loadTime">
                    当前时间:&nbsp;&nbsp;<span id="time" style="color: red;">2015年1月1日 11:11  星期一</span>
                </section>
            </h2>


        </div>
    </div>

</body>

<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/js/time.js"></script>

</html>