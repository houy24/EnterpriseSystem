<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>员工收入统计</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui/css/layui.css?time=<%=Math.random()%>">
    <script src="${pageContext.request.contextPath}/resources/layui/layui.js?time=<%=Math.random()%>"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js?time=<%=Math.random()%>"></script>
    <script src="${pageContext.request.contextPath}/resources/js/echarts.min.js"></script>
    <script>
        layui.use('element', function(){
            var element = layui.element;
        });
    </script>

    <style>

        .x-nav{     /*正常版*/
            padding: 0 20px;
            position: relative;
            z-index: 99;
            border-bottom: 1px solid #e5e5e5;
            line-height: 50px;
            height: 50px;
            overflow: hidden;
            background: #fff;
        }
        .spanTitle {
            font-size: 24px;
        }
        .aFlush {
            width:90px;
            margin-top:3px;
            float:right;
            margin-right: 10px;
        }


    </style>

</head>

<body>

<%--标题，刷新按钮 部分--%>
<div class="x-nav" style="margin-top: 10px;margin-left: 10px;">
            <span class="layui-breadcrumb" style="visibility: visible">
                <span class="spanTitle"><a>首页</a> </span>
    <%--            <span lay-separator>/</span>--%>
                <span class="spanTitle"><a>工资统计</a> </span>
    <%--            <span lay-separator>/</span>--%>
                <span class="spanTitle"><a>员工收入统计</a> </span>
            </span>

    <a class="layui-btn layui-btn-small layui-btn-normal aFlush"
       href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="height:10px;font-size: 20px;!important;">&#xe669;</i>
    </a>
</div>

<%--员工搜索--%>
<%--搜索，操作栏--%>

    <%--管理员，正常搜索--%>
    <c:if test="${userAccount.userType == 'manager'}">
        <div class="layui-row" style="margin-left: 50px;margin-top: 15px;">
            <div class="layui-form">
                <span style="font-size: 18px;">员工查询：</span>
                <div class="layui-inline">
                    <input class="layui-input" style="font-size: 18px;width: 220px;"
                           name="userPhoneSearch" id="userPhoneSearch" autocomplete="off" placeholder="请输入员工手机号">
                </div>
                <button type="button" class="layui-btn" lay-submit style="font-size: 16px;margin-left: 20px;" data-type="reload"
                        lay-filter="data-search-btn">搜索</button>
            </div>

        </div>
    </c:if>



<div id="container" style="height: 87%"></div>

<script type="text/javascript">

    var dom = document.getElementById("container");
    // 解决模糊问题
    var myChart = echarts.init(dom,'', {devicePixelRatio:2});
    // var myChart = echarts.init(dom,'', { renderer : 'svg' });

    // 默认统计当前用户
    $.get('${pageContext.request.contextPath}/getUserYearAndMonthWage', function (data) { // ajax 获取 json 数据
        console.log(data);

        myChart.setOption(option = {
            title: {
                text: '员工工资收入统计',
                subtext: '员工：' + data.userName, // 员工名称 ，回显
                left: 'center',
                textStyle: {    // 修改标题字体大小
                    color: '#333333',
                    fontWeight: 'bold',
                    fontSize: 20
                },
                subtextStyle: {    // 修改子标题字体大小
                    fontSize: 16
                },
            },
            legend: {
                type: 'scroll',
                right: 30,
                top: 20,
                bottom: 20,
                orient: 'vertical',
                textStyle: {
                    fontSize: 17,
                },
            },
            tooltip: {  //鼠标悬浮提示字体大小
                trigger: 'axis',    // 单项显示，还是多项显示，当前是多项显示
                textStyle: {
                    fontSize: 16,
                },
            },
            dataset: {
                source: [
                    // 提供数据
                    data.product,   // test
                    data.data1,
                    data.data2,
                    data.data3,
                    data.data4,
                    data.data5,
                    data.data6,
                    data.data7,
                    data.data8,
                    data.data9,
                    data.data10,
                    data.data11,
                    data.data12,
                    data.dataSum,
                ]
            },
            xAxis: {
                type: 'category',
                axisLabel: {
                    fontSize:'16',
                },
            },
            yAxis: {
                axisLabel: {
                    fontSize:'16',
                },
            },
            // Declare several bar series, each will be mapped
            // to a column of dataset.source by default.
            series: [
                {
                    type: 'bar',
                    color: '#FC8452'
                },
                {
                    type: 'bar',
                    color: '#00ff00'
                },
                {
                    type: 'bar',
                    color: '#73C0DE'
                }
            ]

        });

    }, 'json');



    layui.use(['layer', 'element' , 'form'], function(){
        var layer = layui.layer //弹层
            ,element = layui.element //元素操作

        var form = layui.form;

        // 向世界问个好
        // layer.msg('Hello World');

        //监听提交
        form.on('submit(data-search-btn)', function(data){
            // layer.msg(JSON.stringify(data.field));

            // 向世界问个好
            //layer.msg('Hello World1');

            var userPhoneSearch = data.field.userPhoneSearch; // 年度
            console.log(userPhoneSearch);

            /*正则表达式校验输入的手机号查询*/
            if (userPhoneSearch.length !== 11 || !userPhoneSearch.match('^1[0-9]{10}$')) {
                layer.msg('请输入正确的手机号'/*,{
                        icon: 5
                    }*/);
                return false;
            }

            /* 更新图表 */
            $.ajax({
                url: "${pageContext.request.contextPath}/getUserYearAndMonthWage",
                data: {     // 表单数据
                    userPhoneSearch : data.field.userPhoneSearch,
                },
                dataType: 'json',
                success: function (data) {

                    if (data.check.toString() === "false") {

                        layer.msg("查询失败！");

                    } else if (data.check.toString() === "true") {

                        layer.msg('查询成功',{icon : 6, time : 800});
                        console.log(data);

                        myChart.setOption(option = {
                            title: {
                                text: '员工工资收入统计',
                                subtext: '员工：' + data.userName, // 员工名称 ，回显
                                left: 'center',
                                textStyle: {    // 修改标题字体大小
                                    color: '#333333',
                                    fontWeight: 'bold',
                                    fontSize: 20
                                },
                                subtextStyle: {    // 修改子标题字体大小
                                    fontSize: 16
                                },
                            },
                            legend: {
                                type: 'scroll',
                                right: 30,
                                top: 20,
                                bottom: 20,
                                orient: 'vertical',
                                textStyle: {
                                    fontSize: 17,
                                },
                            },
                            tooltip: {  //鼠标悬浮提示字体大小
                                trigger: 'axis',    // 单项显示，还是多项显示，当前是多项显示
                                textStyle: {
                                    fontSize: 16,
                                },
                            },
                            dataset: {
                                source: [
                                    // 提供数据
                                    data.product,   // test
                                    data.data1,
                                    data.data2,
                                    data.data3,
                                    data.data4,
                                    data.data5,
                                    data.data6,
                                    data.data7,
                                    data.data8,
                                    data.data9,
                                    data.data10,
                                    data.data11,
                                    data.data12,
                                    data.dataSum,
                                ]
                            },
                            xAxis: {
                                type: 'category',
                                axisLabel: {
                                    fontSize:'16',
                                },
                            },
                            yAxis: {
                                axisLabel: {
                                    fontSize:'16',
                                },
                            },
                            // Declare several bar series, each will be mapped
                            // to a column of dataset.source by default.
                            series: [
                                {
                                    type: 'bar',
                                    color: '#FC8452'
                                },
                                {
                                    type: 'bar',
                                    color: '#00ff00'
                                },
                                {
                                    type: 'bar',
                                    color: '#73C0DE'
                                }
                            ]

                        });


                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    layer.msg("查询失败！");
                    console.log(XMLHttpRequest.status);
                    console.log(XMLHttpRequest.readyState);
                    console.log(textStatus);
                }
            });

            return false; // 表单提交
        });


    });

</script>

</body>
</html>
