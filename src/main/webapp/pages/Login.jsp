
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>生产型企业信息管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/Login.css?time=<%=Math.random()%>" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui/css/layui.css?time=<%=Math.random()%>">
    <script src="${pageContext.request.contextPath}/resources/layui/layui.js?time=<%=Math.random()%>"></script>
    <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/js/jquery.min.js?time=<%=Math.random()%>"></script>
    <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/js/Login.js?time=<%=Math.random()%>"></script>
</head>
<body>
<div class="login_box">
    <h1 class="login_box_title">用户登录</h1>
    <form name="form">
        <div class="login_box_account">
            <input type="text" class="account_input" id="account_input" placeholder="请输入11位的手机号" autocomplete="on" oninput="cleanAccountTips()"
                   value="17779686045">
        </div>
        <div class="login_box_password">
            <input type="password" class="password_input" id="password_input" placeholder="请输入3-16位的密码" autocomplete="off" oninput="cleanPasswordTips()"
                    value="123456">
        </div>
        <div class="login_box_others">
            <input type="checkbox" class="remember_choose" id="remember_choose">
            <p>记住我</p>
            <a href="/ChangePas" class="forget" target="_self">忘记密码？</a>
        </div>
    </form>
    <div class="login_box_click">
        <input type="button" value="登陆" class="login_button" onclick="login()"/>
        <a href="javascript:;" class="register" target="_self" style="">新用户注册</a>
    </div>
</div>
</body>
</html>
