function sign_in(){
    $.ajax({
        url:"/PersonalSign_in",
        dataType:"json",
        success:function (value){
            if (value.check === "false"){
                alert("今日已签到！")
            }else {
                alert("签到成功！")
            }
        }
    })
}
function sign_out(){
    $.ajax({
        url:"/PersonalSign_out",
        dataType:"json",
        success:function (value){
            if (value.check === "false"){
                alert("今日还未签到！")
            }else {
                if (value.upd === "true"){
                    alert("今日已签退！")
                }else {
                    alert("签退成功！")
                }
            }
        }
    })
}