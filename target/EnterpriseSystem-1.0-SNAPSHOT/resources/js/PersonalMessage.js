//点击弹出个人信息弹框
function Popup(){
    var popBox = document.getElementById("vehiclePopup");
    var popLayer = document.getElementById("vehicle");
    popBox.style.display = "block";
    popLayer.style.display = "block";
    $.ajax({
        type: "get",
        url: "/PersonalMessage",
        dataType:"json",
        success:function (data){
            console.log(data.userPhoto);
            document.getElementById("userImg").src= data.userPhoto;
            $("#userName").val(data.userName);
            $("#userSex").val(data.userSex);
            $("#userEmail").val(data.userEmail);
            $("#userAge").val(data.userAge);
            $("#userType").val(data.userType);
            $("#departmentId").val(data.departmentId);
            $("#positionId").val(data.positionId);
            $("#workTitleId").val(data.workTitleId);
            $("#workAge").val(data.workAge);
        }
    })
}

/*点击关闭按钮*/
function closeBox() {
    var popBox = document.getElementById("vehiclePopup");
    var popLayer = document.getElementById("vehicle");
    popBox.style.display = "none";
    popLayer.style.display = "none";
}
//点击弹出修改密码弹框
function changePassword(){
    var popBox = document.getElementById("changePasswordPopup");
    var popLayer = document.getElementById("changePassword");
    popBox.style.display = "block";
    popLayer.style.display = "block";
}
/*点击关闭按钮*/
function changePasswordCloseBox() {
    var popBox = document.getElementById("changePasswordPopup");
    var popLayer = document.getElementById("changePassword");
    popBox.style.display = "none";
    popLayer.style.display = "none";
}
/*点击关闭弹出详细信息*/
function detailedCloseBox(){
    var popBox = document.getElementById("detailedPopup");
    var popLayer = document.getElementById("detailed");
    popBox.style.display = "none";
    popLayer.style.display = "none";
}
/*点击关闭弹出修改详细信息*/
function changeCloseBox(){
    var popBox = document.getElementById("changePopup");
    var popLayer = document.getElementById("change");
    popBox.style.display = "none";
    popLayer.style.display = "none";
}
function Sure(){

    var oldPassword = $('#oldPassword').val();
    var newPassword1 = $('#newPassword1').val();
    var newPassword2 = $('#newPassword2').val();

    $.ajax({
        url: "/PersonalPassword",
        data:{
            old : oldPassword,
            new1 : newPassword1,
            new2 : newPassword2
        },
        dataTypes : "json",
        success: function (value){
            if (value.check == false){
                cleanPasswordTips();
                $('.oldPassword').append("<p style='color: red;'>请输入正确的原密码!</p>");
            }else {
                if (value.checks == false){
                    cleanPasswordTips();
                    $('.newPassword1').append("<p style='color: red;'>请输入相同的新密码!</p>");
                }else {
                    layui.use('layer', function() {
                        var layer = layui.layer ;

                        let sure = layer.msg('<span style="font-size:20px;align-content: center;align-items: center;">修改成功!</span>',{time: 1000,area: ['170px','50px']},function () {
                            // window.location = "/pages/BackstageHomePage.jsp";
                            window.location = "/pages/Login.jsp";
                        });
                    });
                }
            }
        }
    })
}
function updateMessage(){
    var userId = $('#changeId').val();
    var userName = $('#changeName').val();
    var userPhone = $('#changeUserPhone').val();
    var userSex = $('#changeSex').val();
    var userEmail = $('#changeEmail').val();
    var userAge = $('#changeAge').val();
    var userType = $('#changeType').val();
    var departmentId = $('#changeDepartmentId').val();
    var positionId = $('#changePositionId').val();
    var workTitleId = $('#changeWorkTitleId').val();
    var workAge = $('#changeWorkAge').val();
    var userPhotoJ = document.getElementById("changeImg").src
    var userPhotoR = "../../resources/images/"+userPhotoJ.substring(userPhotoJ.lastIndexOf("/")+1);
    $.ajax({
        url: "/Personal_informationChange",
        data: {
            userId:userId,
            userName:userName,
            userPhone:userPhone,
            userSex:userSex,
            userEmail:userEmail,
            userAge:userAge,
            userType:userType,
            departmentId:departmentId,
            positionId:positionId,
            workTitleId:workTitleId,
            workAge:workAge,
            userPhoto:userPhotoR
        },
        dataType: "json",
        success: function (value){
            window.location ="/pages/personnel_management/BackPersonnel_information.jsp"
            if (value.check === "true"){
                alert("修改成功！")
            }
        }
    })
}
function updateDepartment(){
    var departmentId = $('#departmentUp').val();
    var departmentName = $('#departmentNameUp').val();
    var departmentDesrciption = $('#departmentDesrciptionUp').val();
    $.ajax({
        url: "/PersonalDepartment_informationChange",
        data:{
            departmentId:departmentId,
            departmentName:departmentName,
            departmentDesrciption:departmentDesrciption
        },
        dataType:"json",
        success:function (value){
            window.location ="/pages/personnel_management/BackPersonalDepartment_information.jsp"
            if (value.check === "true"){
                alert("修改成功！")
            }
        }
    })
}
function openDepartment(){
    var popBox = document.getElementById("addChangePopup");
    var popLayer = document.getElementById("addChange");
    popBox.style.display = "block";
    popLayer.style.display = "block";
}
function addDepartment(){

    var departmentName = $('#addDepartmentName').val();
    var departmentDesrciption = $('#addDepartmentDesrciption').val();

    $.ajax({
        url:"/PersonalDepartment_informationAdd",
        data:{
            departmentName:departmentName,
            departmentDesrciption:departmentDesrciption
        },
        dataType:"json",
        success:function (value){
            if (value.check === "true"){
                alert("添加成功！")
            }
            window.location ="/pages/personnel_management/BackPersonalDepartment_information.jsp"
        }
    })
}
function AddChangeCloseBox(){
    var popBox = document.getElementById("addChangePopup");
    var popLayer = document.getElementById("addChange");
    popBox.style.display = "none";
    popLayer.style.display = "none";
}
function updateRecord(){
    var signInId = $('#signInId').val();
    var userId = $('#userIdUp').val();
    var userName = $('#signInuserNameUp').val();
    var signInTime = $('#signInTimeUp').val();
    var signOutTime = $('#signOutTimeUp').val();
    $.ajax({
        url: "/PersonalAttendance_RecordChange",
        data:{
            signInId:signInId,
            userId:userId,
            userName:userName,
            signInTime:signInTime,
            signOutTime:signOutTime
        },
        dataType:"json",
        success:function (value){
            window.location ="/pages/personnel_management/BackPersonalAttendance_Record.jsp"
            if (value.check === "true"){
                alert("修改成功！")
            }
        },
        error:function (value){
          alert("失败！")
        }

    })
}
function addSign(){
    var userId = $('#AddUserId').val();
    var userName = $('#AddSignInuserName').val();
    var signInTime = $('#AddSignInTime').val();
    var signOutTime = $('#AddSignOutTime').val();
    $.ajax({
        url: "/PersonalAttendance_RecordAdd",
        data:{
            userId:userId,
            userName:userName,
            signInTime:signInTime,
            signOutTime:signOutTime
        },
        dataType:"json",
        success:function (value){
            window.location ="/pages/personnel_management/BackPersonalAttendance_Record.jsp"
            if (value.check === "true"){
                alert("添加成功！")
            }
        },
        error:function (value){
            alert("失败！")
        }

    })

}