
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

            // 菜单栏伸缩
            $('.layui-nav-tree').on('click','li ',function(){
                console.log(this.className);
                 let className = this.className;

                 if ( className.indexOf('layui-nav-itemed') === -1) {
                    $(this).removeClass('layui-nav-itemed').siblings('li').removeClass('layui-nav-itemed');
                     console.log('remove');
                 } else {
                     $(this).addClass('layui-nav-itemed').siblings('li').removeClass('layui-nav-itemed');
                     console.log('add');
                 }
                console.log('11');
            });

        });
    </script>

    <style>
        .layui-layout-admin .layui-body {
            position: absolute;
            top: 60px;
            padding-bottom: 0px;
        }
    </style>

</head>
<body class="layui-layout-body" >
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo" onclick="window.location.href='${pageContext.request.contextPath}/pages/salary_management/BackSalaryHomePage.jsp'">工资管理子系统-后台主页</div>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:0;">
                    <img src="${userData.userPhoto}" class="layui-nav-img" alt="图片无法显示">
                </a>
            </li>
            <li class="layui-nav-item">
                <a href="${pageContext.request.contextPath}/pages/homepage.jsp" <%--onclick="exit()"--%>>退出</a>
            </li>
        </ul>
    </div>
    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <ul class="layui-nav layui-nav-tree" lay-filter="test">

                <%--工资管理--%>
                <li class="layui-nav-item "  id="test">  <%-- layui-nav-itemed 展开--%>
                    <a href="javascript:;">工资管理</a>
                    <dl class="layui-nav-child">
                        <%--工资结算，管理员功能--%>
                        <c:if test="${userAccount.userType == 'manager'}">
                            <dd class=""><a href="${pageContext.request.contextPath}/pages/salary_management/WageCalculate.jsp"
                                            target="main_self_frame">工资结算</a></dd>
                        </c:if>
                        <%--员工只能看到自己的，管理员可以查询所有员工工资--%>
                        <dd><a href="${pageContext.request.contextPath}/pages/salary_management/WageView.jsp"
                               target="main_self_frame">工资查询</a></dd>
                    </dl>
                </li>

                <%--员工管理--%>
                <li class="layui-nav-item ">  <%-- layui-nav-itemed 展开--%>
                    <a href="javascript:;">员工管理</a>
                    <dl class="layui-nav-child">
                        <dd class=""><a href="${pageContext.request.contextPath}/pages/salary_management/UserDataMoneyView.jsp"
                                        target="main_self_frame">
                                        <c:if test="${userAccount.userType == 'manager'}">员工列表</c:if>
                                        <c:if test="${userAccount.userType == 'employee'}">员工信息</c:if>
                                     </a></dd>
                    </dl>
                </li>

                <%--基本工资管理--%>
                <li class="layui-nav-item ">  <%-- layui-nav-itemed 展开--%>
                    <a href="javascript:;">基本工资管理</a>

                    <%--岗位管理，管理员功能--%>
                    <c:if test="${userAccount.userType == 'manager'}">
                        <dl class="layui-nav-child">
                            <%--岗位管理--%>
                            <li class="layui-nav-item ">
                                <a href="javascript:;">岗位管理</a>
                                <dl class="layui-nav-child">
                                    <dd class=""><a href="${pageContext.request.contextPath}/pages/salary_management/PositionView.jsp"
                                                    target="main_self_frame">岗位列表</a></dd>
                                    <dd><a href="${pageContext.request.contextPath}/pages/salary_management/PositionQuery.jsp"
                                           target="main_self_frame" >查询员工岗位</a></dd>
                                </dl>
                            </li>
                        </dl>
                    </c:if>

                    <dl class="layui-nav-child">
                        <%--绩效管理--%>
                        <dd class=""><a href="${pageContext.request.contextPath}/pages/salary_management/GradeManageView.jsp"
                                        target="main_self_frame">绩效管理</a></dd>
                    </dl>

                    <%--职称奖金管理，管理员功能--%>
                    <c:if test="${userAccount.userType == 'manager'}">
                        <dl class="layui-nav-child">
                            <%--职称奖金管理--%>
                            <dd class=""><a href="${pageContext.request.contextPath}/pages/salary_management/WorkTitleView.jsp"
                                            target="main_self_frame">职称管理</a></dd>
                        </dl>
                    </c:if>

                </li>

                <%--税率管理--%>
                <li class="layui-nav-item ">
                    <a href="javascript:;">税率管理</a>
                    <dl class="layui-nav-child">
                        <dd class=""><a href="${pageContext.request.contextPath}/pages/salary_management/TaxRateView.jsp"
                                        target="main_self_frame">税率列表</a></dd>
                        <dd class=""><a href="${pageContext.request.contextPath}/pages/salary_management/TaxRateCalculate.jsp"
                                        target="main_self_frame">个人所得税计算</a></dd>
                    </dl>
                </li>

                <%--日常工资项管理，管理员功能--%>
                <c:if test="${userAccount.userType == 'manager'}">
                    <%--日常工资项管理--%>
                    <li class="layui-nav-item ">  <%-- layui-nav-itemed 展开--%>
                        <a href="javascript:;">日常工资项管理</a>
                        <dl class="layui-nav-child">
                            <%--                        <dd class=""><a href="${pageContext.request.contextPath}/pages/salary_management/RoutineItemView.jsp"--%>
                            <%--                                        target="main_self_frame">工资项</a></dd>--%>
                            <dd class=""><a href="${pageContext.request.contextPath}/ToRoutineItemView"
                                            target="main_self_frame">工资项</a></dd>
                        </dl>
                    </li>
                </c:if>

                <%-- 其他管理 --%>
                <li class="layui-nav-item ">
                    <a href="javascript:;">其他管理</a>

                    <%--工龄工资管理，管理员功能--%>
                    <c:if test="${userAccount.userType == 'manager'}">
                        <dl class="layui-nav-child">
                            <%--工龄工资管理--%>
                            <li class="layui-nav-item ">  <%-- layui-nav-itemed --%>
                                <a href="javascript:;">工龄工资管理</a>
                                <dl class="layui-nav-child">
                                    <dd class=""><a href="${pageContext.request.contextPath}/pages/salary_management/WorkAgeMoneyView.jsp"
                                                    target="main_self_frame">工龄列表</a></dd>
                                    <dd><a href="${pageContext.request.contextPath}/pages/salary_management/WorkAgeMoneyQuery.jsp"
                                           target="main_self_frame">查询员工工龄</a></dd>
                                </dl>
                            </li>
                        </dl>
                    </c:if>

                    <dl class="layui-nav-child">
                        <%--考勤工资管理--%>
                        <dd class=""><a href="${pageContext.request.contextPath}/pages/salary_management/SignManageView.jsp"
                                        target="main_self_frame">考勤工资管理</a></dd>
                    </dl>

                </li>

                <%--工资统计--%>
                <li class="layui-nav-item layui-nav-itemed ">
                    <a href="javascript:;">工资统计</a>
                    <dl class="layui-nav-child">
                        <dd class=""><a href="${pageContext.request.contextPath}/pages/salary_management/EmployeeIncomeTotal.jsp"
                                        target="main_self_frame">员工收入统计</a></dd>
                        <%--部门收入统计，管理员功能--%>
                        <c:if test="${userAccount.userType == 'manager'}">
                            <dd class=""><a href="${pageContext.request.contextPath}/pages/salary_management/DepartmentIncomeTotal.jsp"
                                        target="main_self_frame">部门收入统计</a></dd>
                        </c:if>
                    </dl>
                </li>

                <%--退出--%>
                <li class="layui-nav-item">
                    <a href="${pageContext.request.contextPath}/pages/homepage.jsp">退出</a>
                </li>

            </ul>
        </div>
    </div>
    <div class="layui-body" <%--style="height: 700px;"--%>>
        <%--<div style="padding: 15px;">
            内容主体区域
        </div>
        <div class="index">
            <h1>欢迎使用生产型企业信息化管理系统后台</h1>
            <h1>详细功能请查看左方的功能栏</h1>
        </div>--%>
        <iframe src="${pageContext.request.contextPath}/pages/salary_management/SalaryHome_Base.jsp"
                name="main_self_frame" frameborder="0" class="layadmin-iframe"
                style=" height:100%; width:100%" ></iframe>



    </div>
</div>
</body>
</html>