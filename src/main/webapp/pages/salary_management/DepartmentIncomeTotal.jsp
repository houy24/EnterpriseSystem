
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>部门收入统计</title>
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
                <span class="spanTitle"><a>部门收入统计</a> </span>
            </span>

    <a class="layui-btn layui-btn-small layui-btn-normal aFlush"
       href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="height:10px;font-size: 20px;!important;">&#xe669;</i>
    </a>
</div>

<%--年份搜索--%>
<%--搜索，操作栏--%>

<div class="layui-row" style="margin-left: 50px;margin-top: 15px;">
    <div class="layui-form">
        <span style="font-size: 18px;">年度查询：</span>
        <div class="layui-inline">
            <input class="layui-input" style="font-size: 18px;width: 200px;"
                   name="timeYearSearch" id="timeYearSearch" autocomplete="off" placeholder="yyyy">
        </div>
        <button type="button" class="layui-btn" lay-submit style="font-size: 16px;margin-left: 20px;" data-type="reload"
                lay-filter="data-search-btn">搜索</button>
    </div>

</div>



<div id="container" style="height: 87%"></div>

<script type="text/javascript">

    var dom = document.getElementById("container");
    // 解决模糊问题
    var myChart = echarts.init(dom,'', {devicePixelRatio:2});
    // var myChart = echarts.init(dom,'', { renderer : 'svg' });

    // 默认统计当前年度 2021
    $.get('${pageContext.request.contextPath}/getDepartmentYearWage', function (data) { // ajax 获取 json 数据
        console.log(data);

        myChart.setOption(option = {
            title: {
                text: '各部门工资收入统计',
                subtext: data.timeYearSearch + '年',
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
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b}：{c} &nbsp;({d}%)",
                textStyle: {
                    fontSize: 16,
                },
            },
            legend: {
                type: 'scroll',
                right: 100,
                top: 20,
                bottom: 20,
                orient: 'vertical',
                textStyle: {
                    fontSize: 17,
                },
                // left: 'right',
            },
            series : [
                {
                    name: '工资占比',
                    type: 'pie',    // 设置图表类型为饼图
                    radius: '55%',  // 饼图的半径，外半径为可视区尺寸（容器高宽中较小一项）的 55% 长度。
                    data:data.data_pie,
                    label: {    // 修改饼图字体大小
                        normal: {
                            // position: 'inner',
                            textStyle : {
                                fontWeight : 'normal',
                                fontSize : 16
                            }
                        }
                    },
                    center: ['50%', '45%'], // 饼图的中心
                }
            ],


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

            var timeYearSearch = data.field.timeYearSearch; // 年度
            console.log(timeYearSearch);

            /*正则表达式校验输入的年份查询*/
            if (timeYearSearch.length !== 4 || !timeYearSearch.match('\\d{4}')) {
                layer.msg('请输入正确的年份'/*,{
                        icon: 5
                    }*/);
                return false;
            }

            /* 更新图表 */
            $.get('${pageContext.request.contextPath}/getDepartmentYearWage?timeYearSearch=' + timeYearSearch, function (data) { // ajax 获取 json 数据
                layer.msg('查询成功',{icon : 6, time : 800});
                console.log(data);

                myChart.setOption(option = {
                    title: {
                        text: '各部门工资收入统计',
                        subtext: data.timeYearSearch + '年',
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
                    tooltip: {
                        trigger: 'item',
                        formatter: "{a} <br/>{b}：{c} &nbsp;({d}%)",
                        textStyle: {
                            fontSize: 16,
                        },
                    },
                    legend: {
                        type: 'scroll',
                        right: 100,
                        top: 20,
                        bottom: 20,
                        orient: 'vertical',
                        textStyle: {
                            fontSize: 17,
                        },
                        // left: 'right',
                    },
                    series : [
                        {
                            name: '工资占比',
                            type: 'pie',    // 设置图表类型为饼图
                            radius: '55%',  // 饼图的半径，外半径为可视区尺寸（容器高宽中较小一项）的 55% 长度。
                            data:data.data_pie,
                            label: {    // 修改饼图字体大小
                                normal: {
                                    // position: 'inner',
                                    textStyle : {
                                        fontWeight : 'normal',
                                        fontSize : 16
                                    }
                                }
                            },
                            center: ['50%', '45%'], // 饼图的中心
                        }
                    ]
                });
            }, 'json');

            return false; // 表单提交
        });


    });

</script>

</body>
</html>
