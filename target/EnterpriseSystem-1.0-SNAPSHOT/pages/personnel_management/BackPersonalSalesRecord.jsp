<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>生产型企业信息化管理系统-后台主页</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui/css/layui.css?time=<%=Math.random()%>">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/PersonalSales.css?time=<%=Math.random()%>">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/PersonalMessage.css?time=<%=Math.random()%>">
    <script src="${pageContext.request.contextPath}/resources/layui/layui.js?time=<%=Math.random()%>"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js?time=<%=Math.random()%>"></script>
    <script src="${pageContext.request.contextPath}/resources/js/PersonalMessage.js?time=<%=Math.random()%>"></script>
    <script>
        layui.use('element', function(){
            var element = layui.element;
        });
        layui.use('table', function(){
            var table = layui.table;

            tables = table.render({
                elem: '#test'
                ,id:'test'
                ,url:'${pageContext.request.contextPath}/PersonalSalesRecord'
                ,page:true
                ,limit: 10
                ,toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
                ,title: '用户数据表'
                ,cols: [[
                    {field:'saleTaskId', title:'销售任务编号', width:150, fixed: 'left', align: 'center'}
                    ,{field:'productId', title:'产品编号', width:150, fixed: 'left', align: 'center'}
                    ,{field:'saleNumber', title:'销售数量', width:150, fixed: 'left', align: 'center'}
                    ,{field:'saleOneMoney', title:'销售单件成交价', width:150, fixed: 'left', align: 'center'}
                    ,{field:'saleSumMoney', title:'销售总价', width:180, fixed: 'left', align: 'center'}
                    ,{field:'saleFinishTime', title:'销售时间', width:290, fixed: 'left', align: 'center',
                        templet: "<div>{{layui.util.toDateString(d.saleFinishTime, 'yyyy-MM-dd HH:mm:ss')}}</div>",}
                    ,{field:'saleRecordState', title:'状态', width:180, fixed: 'left', align: 'center'}
                ]]

            });

            //头工具栏事件
            table.on('toolbar(test)', function(obj){
                var checkStatus = table.checkStatus(obj.config.id);
                switch(obj.event){
                    case 'checkPhone':
                        var userPhone =$("#Phone").val();
                        checkByPhone(userPhone);
                        break;
                    case 'checkName':
                        var userName =$("#Name").val();
                        checkByName(userName);
                        break;

                    //自定义头工具栏右侧图标 - 提示
                    case 'LAYTABLE_TIPS':
                        layer.alert('这是工具栏右侧自定义的一个图标按钮');
                        break;
                };
            });

        });
        function checkByPhone(MaxNumber) {
            tables.reload({
                url: '${pageContext.request.contextPath}/PersonalSaleMaxNumber'
                , where: {
                    MaxNumber: MaxNumber
                }
                , page: {
                    curr: 1
                }
            });
        }
        function checkByName(saleFinishTime) {
            tables.reload({
                url: '${pageContext.request.contextPath}/PersonalSalesFinishTime'
                , where: {
                    saleFinishTime: saleFinishTime
                }
                , page: {
                    curr: 1
                }
            });
        }
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
                        <dd><a href="${pageContext.request.contextPath}/PersonalResumeS">查询个人简历</a></dd>
                        <dd class="layui-this"><a href="${pageContext.request.contextPath}/PersonalSalesJup">查询销售记录</a></dd>
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
        <table class="layui-hide" id="test" lay-filter="test"></table>

        <script type="text/html" id="toolbarDemo">
            <div class="layui-btn-container">
                <input type="text" class="Find Phoneinput" id="Phone" name="Phone" placeholder="查询数量最大值">
                <button class="layui-btn layui-btn-sm" lay-event="checkPhone">查询</button>
                <input type="text" class="Find Phoneinput" id="Name" name="Name" placeholder="例如2021-6-21">
                <button class="layui-btn layui-btn-sm" lay-event="checkName">查询</button>
            </div>
        </script>
    </div>
    </div>
</body>
</html>