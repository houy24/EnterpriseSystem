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
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/resources/js/time.js?time=<%=Math.random()%>"></script>
</head>
<body>
<div class="" style="padding: 30px">
    <h1>欢迎使用销售管理系统</h1>
    <br/>
    <section class="loadTime">
        <span id="time"></span>
    </section>
</div>
</body>
</html>
