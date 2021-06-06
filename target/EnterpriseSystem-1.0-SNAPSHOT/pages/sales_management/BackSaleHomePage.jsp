
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>生产型企业信息化管理系统-后台主页</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui/css/layui.css?time=<%=Math.random()%>">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/BackHomePage.css?time=<%=Math.random()%>">
    <script src="${pageContext.request.contextPath}/resources/layui/layui.js?time=<%=Math.random()%>"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js?time=<%=Math.random()%>"></script>
    <script src="${pageContext.request.contextPath}/resources/js/menuComponents.js?time=<%=Math.random()%>"></script>
    <script>
        //JavaScript代码区域
        layui.use(['element','dropdown', 'jquery'], function () {
            let dropdown = layui.dropdown;
            let element = layui.element;
            let $ = layui.jquery;

            dropdown.render({
                elem: '.home' //可绑定在任意元素中，此处以上述按钮为例
                , align: 'left'
                , trigger: 'contextmenu'
                , data: [{
                    title: '关闭当前'
                    , id: 100
                },{type: '-'},{
                    title: '关闭其它'
                    , id: 101
                },{type: '-'},{
                    title: '关闭所有'
                    , id: 102
                }]
                ,click: function(data, othis){
                    console.log(data); //得到当前所点击的菜单项对应的数据
                    console.log(othis); //得到当前所点击的菜单项 DOM 对象
                    console.log(this.elem); //得到当前组件绑定的原始 DOM 对象
                    close(data.title);
                }
            });
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
            <ul class="layui-nav layui-nav-tree" lay-filter="test" >
                <li class="layui-nav-item">
                    <a href="javascript:;">销售记录</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">销售记录查询</a></dd>
                        <dd><a href="javascript:;">添加销售记录</a></dd>
                        <dd><a href="javascript:;">销售记录管理</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">库存管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">库存查询</a></dd>
                        <dd><a href="javascript:;">库存管理</a></dd>
                        <dd><a href="javascript:;">添加库存</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">销售任务</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">销售任务查询</a></dd>
                        <dd class="layui-nav-item">
                            <a href="javascript:;">销售任务管理</a>
                            <dl class="layui-nav-child">
                                <dd><a href="javascript:;">分配销售任务</a></dd>
                                <dd><a href="javascript:;">删除销售任务</a></dd>
                                <dd><a href="javascript:;">更新销售任务</a></dd>
                            </dl>
                        </dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">销售排行</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">销售月排行</a></dd>
                        <dd><a href="javascript:;">销售年排行</a></dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>
    <div class="layui-body">
        <div class="layui-tab tab" lay-filter="xbs_tab" lay-allowclose="false">
            <ul class="layui-tab-title">
                <li class="home layui-this">我的桌面</li>
                <li class="home"  id="home">第二个页面</li>
            </ul>
        </div>
    </div>
    <div class="page-content-bg"></div>
</div>
</div>
</body>
</html>