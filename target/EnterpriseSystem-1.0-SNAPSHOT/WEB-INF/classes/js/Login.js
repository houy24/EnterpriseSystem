$(document).ready(function() {
    layui.use('element', function () {
        var element = layui.element;
    });
})



function login(){

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
            if(value.check.toString() === "false"){
                cleanPasswordTips();
                $('.login_box_password').append("<p style='color: red;'>请输入正确的手机号和密码</p>");
            }
            else {

                // window.location = "/pages/BackstageHomePage.jsp";
                window.location = "/pages/homepage.jsp";
                layui.msg("登录成功！");
            }
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