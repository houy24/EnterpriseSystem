$(document).ready(function() {
    layui.use('element', function () {
        var element = layui.element;

    });
})



function login(){

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