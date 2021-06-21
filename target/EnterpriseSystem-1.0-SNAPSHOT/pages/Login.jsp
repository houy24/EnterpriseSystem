
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>生产型企业信息化管理系统</title>
    <link rel="stylesheet" href="/resources/css/Login.css?time=<%=Math.random()%>" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui/css/layui.css?time=<%=Math.random()%>">
    <script src="${pageContext.request.contextPath}/resources/layui/layui.js?time=<%=Math.random()%>"></script>
    <script type="text/javascript" charset="utf-8" src="/resources/js/jquery.min.js?time=<%=Math.random()%>"></script>
<%--    <script type="text/javascript" charset="utf-8" src="/resources/js/Login.js"></script>--%>
</head>
<body>

<div class="login_box">
<%--    <a href="/Index" target="_self" class="login_box_return">--%>
<%--        <img src="/resources/image/fanhui.png">返回主界面--%>
<%--    </a>--%>
    <h1 class="login_box_title">生产型企业信息化管理系统</h1>

    <form name="form">
        <div class="login_box_account">
            <input type="text" class="account_input" id="account_input" placeholder="请输入11位的手机号" autocomplete="on" oninput="cleanAccountTips()"
                   value="17779686045">
        </div>
        <div class="login_box_password">
            <input type="password" class="password_input" id="password_input" placeholder="请输入8-16位的密码" autocomplete="off" oninput="cleanPasswordTips()"
                    value="123456">
        </div>
        <div class="login_box_others">
            <input type="checkbox" class="remember_choose" id="remember_choose">
            <p>记住我</p>
            <a href="/ChangePas" class="forget" target="_self">忘记密码？</a>
        </div>
    </form>
    <div class="login_box_click">
        <input type="button" value="登陆" id="login_button" class="login_button" onclick="login()"/>
        <a href="javascript:0;" class="register" target="_self" style="">新用户注册</a>
    </div>
</div>
</body>
</html>

<script>
    $(document).ready(function() {
        layui.use('element', function () {
            var element = layui.element;

        });
    })

    function login() {

        var loading = layer.load(0, {
            shade: false,
            time: 2*1000,
            icon: 2   // 图标 0,1,2
        });

        var account = $('#account_input').val();
        var password = $('#password_input').val();
        var remember = $('#remember_choose').prop("checked");
        if(account === ""){
            cleanAccountTips();
            $('#account_input').css("border", "1px solid red");
            $('.login_box_account').append("<p style='color: red;'>手机号不能为空</p>");
            return;
        }
        if(account.length != 11 || !judgeIsNum(account)){
            cleanAccountTips();
            $('#account_input').css("border", "1px solid red");
            $('.login_box_account').append("<p style='color: red;'>手机号应该为11位的纯数字组合</p>");
            return;
        }
        if(password === ""){
            cleanPasswordTips();
            $('#password_input').css("border", "1px solid red");
            $('.login_box_password').append("<p style='color: red;'>密码不能为空</p>");
            return;
        }
        if(password.length < 3 || password.length > 16){
            cleanPasswordTips();
            $('#password_input').css("border", "1px solid red");
            $('.login_box_password').append("<p style='color: red;'>密码长度应在3-16位之间</p>");
            return;
        }
        $.ajax({
            url: "/userAccountLogin",
            data: {
                userPhone : account,
                userPassword : password,
                remember : remember
            },
            dataType: "json",
            success: function(value){
                layer.close(loading);
                if(value.check.toString() === "false"){
                    cleanPasswordTips();
                    $('.login_box_password').append("<p style='color: #ff0000;'>请输入正确的手机号和密码</p>");
                }
                else {
                    layui.use('layer', function() {
                        var layer = layui.layer ;

                        let sure = layer.msg('<span style="font-size:20px;align-content: center;align-items: center;">登录成功!</span>',{time: 1000,area: ['170px','50px']},function () {
                            // window.location = "/pages/BackstageHomePage.jsp";
                            window.location = "/pages/homepage.jsp";
                        });
                        layer.style(sure,{
                            // width: 0

                        });
                    });
                }
            },
            error(result) {
                layer.close(loading);
                console.log(result);
            }
        });
    }

    function cleanAccountTips(){
        $('#account_input').css("border", "1px solid #fff");
        $('.login_box_account p').remove();
    }

    function cleanPasswordTips(){
        $('#password_input').css("border", "1px solid #fff");
        $('.login_box_password p').remove();
    }

    function judgeIsNum (str){
        var pattern=/^\d+$/g;
        var result= str.match(pattern);
        if (result==null){
            return false;
        }else{
            return true;
        }
    }
</script>
