<%--
  Created by IntelliJ IDEA.
  User: 86150
  Date: 2021/6/18
  Time: 15:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/layui/css/layui.css?time=<%=Math.random()%>">
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js?time=<%=Math.random()%>"></script>
    <script src="${pageContext.request.contextPath}/resources/layui/layui.js?time=<%=Math.random()%>"></script>
    <script src="${pageContext.request.contextPath}/resources/js/echarts.min.js"></script>

</head>
<body>
<div id="container" style="height: 100%"></div>

<script type="text/javascript">
    let dom = document.getElementById("container");
    let myChart = echarts.init(dom);
    let app = {};
    let option;
    let month = ['product','1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'];
    let first = ['',0,0,0,0,0,0,0,0,0,0,0,0];
    let second = ['',0,0,0,0,0,0,0,0,0,0,0,0];
    let third = ['',0,0,0,0,0,0,0,0,0,0,0,0];
    option = {
        legend: {},
        tooltip: {},
        dataset: {
            source: [
                month,
                first,
                second,
                third
            ]
        },
        xAxis: [
            {type: 'category', gridIndex: 0},
            {type: 'category', gridIndex: 1}
        ],
        yAxis: [
            {gridIndex: 0},
            {gridIndex: 1}
        ],
        grid: [
            {bottom: '55%'},
            {top: '55%'}
        ],
        series: [
            // 这几个系列会在第一个直角坐标系中，每个系列对应到 dataset 的每一行。
            {type: 'bar', seriesLayoutBy: 'row'},
            {type: 'bar', seriesLayoutBy: 'row'},
            {type: 'bar', seriesLayoutBy: 'row'},
            // 这几个系列会在第二个直角坐标系中，每个系列对应到 dataset 的每一列。
            {type: 'bar', xAxisIndex: 1, yAxisIndex: 1},
            {type: 'bar', xAxisIndex: 1, yAxisIndex: 1},
            {type: 'bar', xAxisIndex: 1, yAxisIndex: 1},
            {type: 'bar', xAxisIndex: 1, yAxisIndex: 1},
            {type: 'bar', xAxisIndex: 1, yAxisIndex: 1},
            {type: 'bar', xAxisIndex: 1, yAxisIndex: 1},
            {type: 'bar', xAxisIndex: 1, yAxisIndex: 1},
            {type: 'bar', xAxisIndex: 1, yAxisIndex: 1},
            {type: 'bar', xAxisIndex: 1, yAxisIndex: 1},
            {type: 'bar', xAxisIndex: 1, yAxisIndex: 1},
            {type: 'bar', xAxisIndex: 1, yAxisIndex: 1},
            {type: 'bar', xAxisIndex: 1, yAxisIndex: 1}
        ]
    };

    if (option && typeof option === 'object') {
        myChart.setOption(option);
    }

    $.post('/getMonthlySalesRanking',{}).done(function(data) {
        let res = eval('('+data+')');
        console.log(res);
        console.log(first)
        first = ["第一名-"+res[0].userName];
        first=first.concat(res[0].salesMoney);
        second = ["第二名-"+res[1].userName];
        second=second.concat(res[1].salesMoney);
        third = ["第三名-"+res[2].userName];
        third=third.concat(res[2].salesMoney);
        console.log(first)
        myChart.setOption(option = {
            legend: {},
            tooltip: {},
            dataset: {
                source: [
                    month,
                    first,
                    second,
                    third
                ]
            }
        });
    });
</script>
</body>
</html>
