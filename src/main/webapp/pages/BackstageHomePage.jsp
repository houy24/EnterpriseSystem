
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
    <script>
        layui.use('element', function(){
            var element = layui.element;
        });
    </script>

</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">销售管理子系统-后台主页</div>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="">个人信息</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item">
                <a href="" onclick="exit()">退出</a>
            </li>
        </ul>
    </div>
    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <ul class="layui-nav layui-nav-tree" lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed">
                    <a href="javascript:;">人事管理子系统</a>
                    <dl class="layui-nav-child">
                        <dd class="layui-this"><a href="javascript:;">新增客户信息</a></dd>
                        <dd><a href="javascript:;">查询客户信息</a></dd>
                        <dd><a href="javascript:;">查询客户信息</a></dd>
                        <dd><a href="javascript:;">查询客户信息</a></dd>
                        <dd><a href="javascript:;">查询客户信息</a></dd>
                        <dd><a href="javascript:;">查询客户信息</a></dd>
                    </dl>
                </li>

                <li class="layui-nav-item layui-nav-itemed">
                    <a href="javascript:;">财务管理子系统</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">新增客户信息</a></dd>
                        <dd><a href="javascript:;">查询客户信息</a></dd>
                        <dd><a href="javascript:;">查询客户信息</a></dd>
                        <dd><a href="javascript:;">查询客户信息</a></dd>
                        <dd><a href="javascript:;">查询客户信息</a></dd>
                        <dd><a href="javascript:;">查询客户信息</a></dd>
                    </dl>
                </li>

                <li class="layui-nav-item layui-nav-itemed">
                    <a href="javascript:;">销售管理子系统</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">新增客户信息</a></dd>
                        <dd><a href="javascript:;">查询客户信息</a></dd>
                        <dd><a href="javascript:;">查询客户信息</a></dd>
                        <dd><a href="javascript:;">查询客户信息</a></dd>
                        <dd><a href="javascript:;">查询客户信息</a></dd>
                        <dd><a href="javascript:;">查询客户信息</a></dd>
                        <dd><a href="javascript:;">查询客户信息</a></dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>
    <div class="layui-body">
        <div style="padding: 15px;">
            内容主体区域
        </div>
        <div class="index">
            <h1>欢迎使用生产型企业信息化管理系统后台</h1>
            <h1>详细功能请查看左方的功能栏</h1>
        </div>
    </div>
</div>
</body>
</html>