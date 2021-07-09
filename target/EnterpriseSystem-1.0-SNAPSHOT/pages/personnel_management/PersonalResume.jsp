<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>生产型企业信息化管理系统-后台主页</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui/css/layui.css?time=<%=Math.random()%>">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/PersonalResume.css?time=<%=Math.random()%>">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/PersonalMessage.css?time=<%=Math.random()%>">
    <script src="${pageContext.request.contextPath}/resources/layui/layui.js?time=<%=Math.random()%>"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js?time=<%=Math.random()%>"></script>
    <script src="${pageContext.request.contextPath}/resources/js/PersonalResume.js?time=<%=Math.random()%>"></script>
    <script src="${pageContext.request.contextPath}/resources/js/PersonalMessage.js?time=<%=Math.random()%>"></script>

    <script>
        layui.use('element', function(){
            var element = layui.element;
        });
        var upload = function (c, d) {
            var $c = document.querySelector(c),
                $d = document.querySelector(d),
                file = $c.files[0],
                reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onload = function (e) {
                $d.setAttribute("src", e.target.result);
            };
        };
    </script>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">销售管理子系统-后台主页</div>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="${userData.userPhoto}" class="layui-nav-img">
                </a>
                <dl class="layui-nav-child">
                    <dd onclick="Popup()"><a href="javascript:void(0)">个人信息</a></dd>
                    <dd onclick="changePassword()"><a href="javascript:void(0)">修改密码</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item">
                <a href="${pageContext.request.contextPath}/pages/homepage.jsp" >退出</a>
            </li>
        </ul>
    </div>
    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <ul class="layui-nav layui-nav-tree" lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed">
                    <a href="javascript:;">查询功能</a>
                    <dl class="layui-nav-child">
                        <dd class="layui-this" onclick="queryResume()"><a href="javascript:;">查询个人简历</a></dd>
                        <dd><a href="${pageContext.request.contextPath}/PersonalSalesJup">查询销售记录</a></dd>
                        <dd><a href="${pageContext.request.contextPath}/PersonalRecordJup">查询个人考勤记录</a></dd>
                        <c:if test="${userData.userType == 'manager'}">
                        <dd><a href="${pageContext.request.contextPath}/PersonalInformationJup">查询员工信息</a></dd>
                        <dd><a href="${pageContext.request.contextPath}/PersonalAttendanceJup">查询员工考勤信息</a></dd>
                        </c:if>
                    </dl>
                </li>
                <c:if test="${userData.userType == 'manager'}">
                <li class="layui-nav-item">
                    <a href="javascript:;">录入人员管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="${pageContext.request.contextPath}/pages/personnel_management/BackPersonalEnter_employee.jsp">录入新员工</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">人员信息管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="${pageContext.request.contextPath}/Personnel_informationJup">人员信息删改查</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">部门信息管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="${pageContext.request.contextPath}/PersonalDepartment_informationJup">增删改查部门信息</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">员工考勤管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="${pageContext.request.contextPath}/PersonalAttendance_RecordJup">增删改查员工考勤记录</a></dd>
                    </dl>
                </li>
                </c:if>
                <li class="layui-nav-item">
                    <a href="javascript:;">签到管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="${pageContext.request.contextPath}/pages/personnel_management/BackPersonalSign_in.jsp">签到签退</a></dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>
    <div>
        <div class="vehicle" id="vehicle"></div>
        <div class="vehiclePopup" id="vehiclePopup">
            <div class="close">
                <a href="javascript:void(0)" onclick="closeBox()" id="close_pop"></a>
            </div>
            <p class="H1_title">个人信息</p>
            <form action="${pageContext.request.contextPath}/PersonalImg" enctype="multipart/form-data" method="post">
                <img src="" class="HeadMessage" id="userImg" ><br>
                <input type="file" name="UserImg" id="Img" style="width: 150px;margin-left: 100px" onchange="upload('#Img','#userImg')"><br>
                <input type="submit" class="layui-btn" style="width: 150px;margin-left: 100px;margin-top:5px" value="确认">
            </form>
            <div class="UserName">
                <input type="text" value="用户姓名：" class="HeadInput">
                <input type="text" id="userName" class="ContextInput">
            </div>
            <div class="UserSex">
                <input type="text" value="用户性别：" class="HeadInput">
                <input type="text" id="userSex" value="" class="ContextInput">
            </div>
            <div class="UserEmail">
                <input type="text" value="邮箱：" class="HeadInput">
                <input type="text" id="userEmail" value="" class="ContextInput">
            </div>
            <div class="UserAge">
                <input type="text" value="年龄：" class="HeadInput">
                <input type="text" id="userAge" value="" class="ContextInput">
            </div>
            <div class="UserType">
                <input type="text" value="职务：" class="HeadInput">
                <input type="text" id="userType" value="" class="ContextInput">
            </div>
            <div class="UserDepartmentId">
                <input type="text" value="部门编号：" class="HeadInput">
                <input type="text" id="departmentId" value="" class="ContextInput">
            </div>
            <div class="UserPositionId">
                <input type="text" value="职位编号：" class="HeadInput">
                <input type="text" id="positionId" value="" class="ContextInput">
            </div>
            <div class="UserWorkTitleId">
                <input type="text" value="职称编号：" class="HeadInput">
                <input type="text" id="workTitleId" value="" class="ContextInput">
            </div>
            <div class="UserWorkAge">
                <input type="text" value="工龄：" class="HeadInput">
                <input type="text" id="workAge" value="" class="ContextInput">
            </div>
        </div>
    </div>
    <div>
        <div class="vehicle" id="changePassword"></div>
        <div class="vehiclePopup" id="changePasswordPopup">
            <div class="close">
                <a href="javascript:void(0)" onclick="changePasswordCloseBox()" id="changePasswordClose_pop"></a>
            </div>
            <p class="H1_title">修改密码</p>
            <div class="oldPassword">
                <input type="text" value="原密码：" class="HeadInput">
                <input type="password" id="oldPassword" placeholder="原密码" class="ContextInput">
            </div>
            <div class="newPassword">
                <input type="text" value="新密码：" class="HeadInput">
                <input type="password" id="newPassword1" placeholder="新密码" class="ContextInput">
            </div>
            <div class="newPassword">
                <input type="text" value="新密码：" class="HeadInput">
                <input type="password" id="newPassword2" placeholder="新密码" class="ContextInput">
            </div>
            <div class="btn">
                <button id="confirm" onclick="Sure()" class="layui-btn">确认提交</button>
            </div>
        </div>
    </div>
    <div class="layui-body">
        <div class="index">
            <h1 class="resume">个人简历</h1>
            <div class="resumneName">
                <input type="text" value="用户姓名：" class="inputTable">
                <input type="text" value="${personalResume.userName}" class="inputContent">
            </div>
            <div class="resumeSex">
                <input type="text" value="性别：" class="inputTable">
                <input type="text" value="${personalResume.userSex}" class="inputContent">
            </div>
            <div class="resumeAge">
                <input type="text" value="年龄：" class="inputTable">
                <input type="text" value="${personalResume.userAge}" class="inputContent">
            </div>
            <div class="resumePhone">
                <input type="text" value="手机号：" class="inputTable">
                <input type="text" value="${personalResume.userPhone}" class="inputContent">
            </div>
            <div class="resumeEmail">
                <input type="text" value="邮箱：" class="inputTable">
                <input type="text" value="${personalResume.userEmail}" class="inputContent">
            </div>
            <div class="resumeEducation">
                <input type="text" value="教育经历：" class="inputTable">
                <input type="text" value="${personalResume.educationExperience}" class="inputContent">
            </div>
            <div class="resumejobTarget">
                <input type="text" value="求职意向：" class="inputTable">
                <input type="text" value="${personalResume.jobTarget}" class="inputContent">
            </div>
            <div class="resumeWork">
                <input type="text" value="工作经历：" class="inputTable">
                <input type="text" value="${personalResume.workExperience}" class="inputContent">
            </div>
            <div class="resumeAwards">
                <input type="text" value="获奖情况：" class="inputTable">
                <textarea class="areaContent">${personalResume.awards}</textarea>
            </div>
            <br>
            <div class="resumeSkills">
                <input type="text" value="个人技能：" class="inputTable">
                <textarea class="areaContent">${personalResume.skills}</textarea>
            </div>
            <br>
            <div class="resumeThink">
                <input type="text" value="自我评价：" class="inputTable">
                <textarea class="areaContent">${personalResume.selfThink}</textarea>
            </div>
        </div>
    </div>
</div>
</body>
</html>