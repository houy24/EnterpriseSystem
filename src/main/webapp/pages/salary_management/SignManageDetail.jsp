<%@ page import="com.xxx.service.Department.DepartmentService" %>
<%@ page import="com.xxx.service.Department.DepartmentServiceImpl" %>
<%@ page import="com.xxx.pojo.Department" %>
<%@ page import="java.util.List" %>
<%@ page import="com.xxx.service.Position.PositionService" %>
<%@ page import="com.xxx.pojo.Position" %>
<%@ page import="com.xxx.service.Position.PositionServiceImpl" %>
<%@ page import="com.xxx.service.WorkTitle.WorkTitleServiceImpl" %>
<%@ page import="com.xxx.service.WorkTitle.WorkTitleService" %>
<%@ page import="com.xxx.pojo.WorkTitle" %>
<%@ page import="com.xxx.service.UserData.UserDataService" %>
<%@ page import="com.xxx.service.UserData.UserDataServiceImpl" %>
<%@ page import="com.xxx.pojo.UserData" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>考勤信息查看</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui/css/layui.css?time=<%=Math.random()%>">
    <script src="${pageContext.request.contextPath}/resources/layui/layui.js?time=<%=Math.random()%>"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js?time=<%=Math.random()%>"></script>
    <script>
        layui.use('element', function(){
            var element = layui.element;
        });
    </script>

    <style>
        .divlabel1 {

            margin-top: 30px;
            margin-left: 0px;
        }
        .divlable3 {
            width: 50px;
            margin-left: -31px;
            z-index: -1;
            text-align: center;
        }
        input {
            font-size: 18px;!important;
            width: 200px;
            margin-left: -20px;
        }
        .labelText {
            text-align: center; /* label文字 */
            width: 130px;
            margin-left: 10px;
            z-index: -1;
        }

        .x-nav{     /*弹出层，缩小版*/
            padding: 0 15px;
            position: relative;
            z-index: 99;
            border-bottom: 1px solid #e5e5e5;
            line-height: 40px;
            height: 40px;
            overflow: hidden;
            background: #fff;
        }
        .spanTitle {
            font-size: 20px;
        }
        .aFlush {
            width: 77px;
            margin-top: -2px;
            float: right;
            margin-right: 18px;
        }
        .myHeadImg {
            margin-left: 150px;
            margin-top: -25px;
            width: 210px;
            height: 220px;
        }

    </style>

    <%
        // 显示考勤信息，主要和工资有关
        // （日期<年-月-日>，员工编号，员工姓名，员工部门）
        // （考勤情况<迟到/早退/缺勤/出差/平常加班/周末加班>，工资情况（加200/减...））

        // SignManage
        // signDayTime , userId , userName , departmentName ,
        // signStatus , signMoneyStatus

        String signDayTime = request.getParameter("signDayTime");
        String userId = request.getParameter("userId");
        String userName = request.getParameter("userName");
        String departmentName = request.getParameter("departmentName");
        String signStatus = request.getParameter("signStatus");
        String signMoneyStatus = request.getParameter("signMoneyStatus");

        System.out.println("SignManageDetail.jsp => " + signDayTime + "," + userId + ","
                + userName + "," + departmentName + ","
                + signStatus + "," + signMoneyStatus  );

        // 当前查看列，部门名称
        request.setAttribute("departmentName",departmentName);

        // 预查询出所有部门
        DepartmentService departmentService = new DepartmentServiceImpl();
        List<Department> departmentList = departmentService.selectAll();

        UserDataService userDataService = new UserDataServiceImpl();
        UserData userData = userDataService.selectByUserId(userId);

    %>

</head>

<body>

<%--标题，刷新按钮 部分--%>
<div class="x-nav" style="margin-top: 10px;margin-left: 10px;">
                <span class="layui-breadcrumb" style="visibility: visible">
                    <span class="spanTitle"><a>首页</a> </span>
        <%--            <span lay-separator>/</span>--%>
                    <span class="spanTitle"><a>考勤管理</a> </span>
        <%--            <span lay-separator>/</span>--%>
                    <span class="spanTitle"><a>查看考勤信息</a> </span>
                </span>

    <a class="layui-btn layui-btn-small layui-btn-normal aFlush"

       href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="height:10px;font-size: 20px;!important;">&#xe669;</i>
    </a>

</div>


<%--表单部分--%>
<div style="margin-left: 50px;margin-top: 20px;">
    <form class="layui-form" >

        <input hidden name="userId" value="<%=userId%>" disabled />


        <div class="layui-form-item">
            <%--。。。。。左边。。。--%>
            <div class="layui-inline">

                <%--日期--%>
                <div class="layui-form-item divlabel1" >
                    <div class="layui-inline">
                        <label class="layui-form-label labelText" >
                            <span style="font-size: 18px;">日期：</span>
                        </label>
                        <div class="layui-input-inline ">
                            <input  type="text" name="signDayTime" lay-verify="required" style="width: 230px;"
                                    autocomplete="off" class="layui-input" value="<%=signDayTime%>" readonly>
                        </div>
                    </div>
                </div>

                <%--员工编号--%>
                <div class="layui-form-item divlabel1" >
                    <div class="layui-inline">
                        <label class="layui-form-label labelText" >
                            <span style="font-size: 18px;">员工编号：</span>
                        </label>
                        <div class="layui-input-inline ">
                            <input  type="text" name="userId" lay-verify="required"
                                    autocomplete="off" class="layui-input" value="<%=userId%>" readonly>
                        </div>
                    </div>
                </div>

                <%--员工姓名--%>
                <div class="layui-form-item divlabel1" >
                    <div class="layui-inline">
                        <label class="layui-form-label labelText" >
                            <span style="font-size: 18px;">员工姓名：</span>
                        </label>
                        <div class="layui-input-inline ">
                            <input  type="text" name="userName" lay-verify="required"
                                    autocomplete="off" class="layui-input" value="<%=userName%>" readonly>
                        </div>
                    </div>
                </div>

                <%--部门名称--%>
                <div class="layui-form-item divlabel1" >
                    <div class="layui-inline">
                        <label class="layui-form-label labelText" >
                            <span style="font-size: 18px;">部门名称：</span>
                        </label>
                        <%--使用 select 框显示，不使用文本输入框显示 --%>
                        <%--<div class="layui-input-inline ">
                            <input  type="text" name="departmentName" lay-verify="required" style="width: 230px;"
                                    autocomplete="off" class="layui-input" value="<%=departmentName%>" readonly>
                        </div>--%>
                        <div class="layui-input-inline ">
                            <select name="departmentNameSelect" lay-verify="required" style="width: 260px;" >
                                <c:forEach items="<%=departmentList%>" var="department">
                                    <option value="${department.departmentId}"
                                        <%--相等时选中--%>
                                            <c:if test="${ department.departmentName ==  departmentName  }"> selected </c:if>
                                            <c:if test="${ department.departmentName != departmentName }"> disabled </c:if>
                                    >${department.departmentName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>

            </div>

            <%--。。。。。右边头像。。。--%>
            <div class="layui-inline">
                <%--头像显示， 当找不到对应头像时，显示默认头像 default.png --%>
                <img src="${pageContext.request.contextPath}/<%=userData.getUserPhoto()%>"
                     class="myHeadImg" onerror="this.src='${pageContext.request.contextPath}/resources/images/head/default.png'">
            </div>

        </div>

        <%--考勤情况--%>
        <div class="layui-form-item divlabel1" >
            <%--考勤情况--%>
            <div class="layui-inline">
                <label class="layui-form-label labelText" >
                    <span style="font-size: 18px;">考勤情况：</span>
                </label>
                <div class="layui-input-inline ">
                    <input  type="text" name="signStatus" lay-verify="required" style="color: red;"
                            autocomplete="off" class="layui-input" value="<%=signStatus%>" readonly>
                </div>
            </div>
        </div>

        <%--工资情况--%>
        <div class="layui-form-item divlabel1" >

            <%--工资情况--%>
            <div class="layui-inline">
                <label class="layui-form-label labelText" >
                    <span style="font-size: 18px;">工资情况：</span>
                </label>
                <div class="layui-input-inline ">
                    <input  type="text" name="signMoneyStatus" lay-verify="required" style="color: red;"
                            autocomplete="off" class="layui-input" value="<%=signMoneyStatus%>" readonly>
                </div>
            </div>


        </div>

    </form>

</div>




</body>
</html>


