<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>生产型企业信息化管理系统-后台主页</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/layui/css/layui.css?time=<%=Math.random()%>">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/BackHomePage.css?time=<%=Math.random()%>">
    <script src="${pageContext.request.contextPath}/resources/layui/layui.js?time=<%=Math.random()%>"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js?time=<%=Math.random()%>"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/resources/js/time.js?time=<%=Math.random()%>"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/resources/js/menuComponents.js?time=<%=Math.random()%>"></script>


</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">销售管理子系统-后台主页</div>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:0;">
                    <img src="${userData.userPhoto}" class="layui-nav-img" alt="图片无法显示">
                </a>
            </li>
            <li class="layui-nav-item">
                <a href="${pageContext.request.contextPath}/pages/homepage.jsp" onclick="exit()">退出</a>
            </li>
        </ul>
    </div>
    <div class="layui-side layui-bg-black">
        <ul class="layui-nav layui-nav-tree " lay-filter="test">
            <li class="layui-nav-item">
                <a href="javascript:0;">销售记录</a>
                <dl class="layui-nav-child">
                    <dd><a data-url="SalesRecordQuery" data-title="个人销售记录查询"  data-id="sales-1"  class="lis">个人销售记录查询</a></dd>
                    <dd><a data-url="AddSalesRecord" data-title="添加销售记录"  data-id="sales-2"  class="lis">添加销售记录</a></dd>
                    <c:if test="${userData.userType.equals('manager')}">
                        <dd><a data-url="SalesRecordManagement" data-title="销售记录管理"  data-id="sales-3"  class="lis">销售记录管理</a></dd>
                    </c:if>
                </dl>
            </li>
            <li class="layui-nav-item">
                <a href="javascript:0;">库存管理</a>
                <dl class="layui-nav-child">
                    <dd><a data-url="InventoryQuery" data-title="库存查询"  data-id="sles-4"  class="lis">库存查询</a></dd>
                    <c:if test="${userData.userType.equals('manager')}">
                    <dd><a data-url="InventoryManagement" data-title="库存管理"  data-id="sales-5"  class="lis">库存管理</a></dd>
                    <dd><a data-url="AddInventory" data-title="添加库存"  data-id="sales-6"  class="lis">添加库存</a></dd>
                    </c:if>
                </dl>
            </li>
            <li class="layui-nav-item">
                <a href="javascript:0;">销售任务</a>
                <dl class="layui-nav-child">
                    <dd><a data-url="SalesTaskQuery" data-title="个人销售任务查询"  data-id="sales-7"  class="lis">个人销售任务查询</a></dd>
                    <c:if test="${userData.userType.equals('manager')}">
                    <dd class="layui-nav-item">
                        <a href="javascript:0;">销售任务管理</a>
                        <dl class="layui-nav-child">
                            <dd><a data-url="AssignSalesTasks" data-title="分配销售任务" data-id="sales-8" class="lis">分配销售任务</a></dd>
                            <dd><a data-url="SaleTaskManagement" data-title="销售任务管理" data-id="sales-9" class="lis">销售任务管理</a></dd>
                        </dl>
                    </dd>
                    </c:if>
                </dl>
            </li>
            <li class="layui-nav-item">
                <a href="javascript:0;">销售排行</a>
                <dl class="layui-nav-child">
                    <dd><a data-url="MonthlySalesRanking" data-title="月销售额排行" data-id="sales-10" class="lis">月销售额排行</a></dd>
                    <dd><a data-url="AnnualSalesRanking" data-title="年销售额排行" data-id="sales-11" class="lis">年销售额排行</a></dd>
                </dl>
            </li>
        </ul>
    </div>
    <div class="layui-body">
        <div class="layui-tab" lay-filter="demo" lay-allowclose="true" style="margin-left: 20px;">
            <ul class="layui-tab-title">
                <li class="home">
                    <i class="layui-icon">&#xe68e;</i>我的桌面</li></ul>
            <ul class="rightmenu" style="display: none;position: absolute;">
                <li data-type="closethis"><span style="font-size: 18px">关闭当前</span></li>
                <li data-type="closeall"><span style="font-size: 18px">关闭所有</span></li>
            </ul>
            <div class="layui-tab-content">
                <div class="layui-tab-item layui-show">
                    <iframe src='MyDesktop.jsp' class="x-iframe"></iframe>
                </div>
            </div>
            <div id="tab_show"></div>
        </div>
    </div>
    <div class="page-content-bg"></div>
</div>
</body>
</html>