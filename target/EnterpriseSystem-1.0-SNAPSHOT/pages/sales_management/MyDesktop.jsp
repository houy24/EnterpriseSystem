<%--
  Created by IntelliJ IDEA.
  User: 86150
  Date: 2021/6/7
  Time: 10:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/layui/css/layui.css?time=<%=Math.random()%>">
    <script src="${pageContext.request.contextPath}/resources/layui/layui.js?time=<%=Math.random()%>"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js?time=<%=Math.random()%>"></script>
    <script src="${pageContext.request.contextPath}/resources/js/clock.js?time=<%=Math.random()%>"></script>
    <script>
        let startTime;
        let now = new Date();
        layui.use(['laydate', 'clock'], function () {
            let laydate = layui.laydate;
            laydate.render({
                elem: '#modifyDate',// input里时间的Id
                type: 'time',
                format: 'yyyy-MM-dd HH:mm:ss',
                value: new Date(),
                done: function (value, date) {
                }
            });

            laydate.render({
                elem: '#test-n1'
                ,position: 'static'
                ,calendar: true
            });
        });
    </script>
</head>
<body>
<div class="" style="padding: 30px">
    <h1>欢迎使用销售管理系统</h1>
    <br/>
    <input type="text" id="nowTime" class="layui-input" />
    <br/>
    <div class="site-demo-laydate">
        <div class="layui-inline" id="test-n1"></div>
    </div>
    <script type="text/javascript">
        var newDate = '';
        getLangDate();
        function dateFilter(date){ //值小于10时，在前面补0
            if(date < 10){
                return "0"+date;
            }
            return date;
        }

        function getLangDate(){
            var dateObj = new Date(); //表示当前系统时间的Date对象
            var year = dateObj.getFullYear(); //当前系统时间的完整年份值
            var month = dateObj.getMonth()+1; //当前系统时间的月份值
            var date = dateObj.getDate(); //当前系统时间的月份中的日
            var day = dateObj.getDay(); //当前系统时间中的星期值
            var weeks = ["星期日","星期一","星期二","星期三","星期四","星期五","星期六"];
            var week = weeks[day]; //根据星期值，从数组中获取对应的星期字符串
            var hour = dateObj.getHours(); //当前系统时间的小时值
            var minute = dateObj.getMinutes(); //当前系统时间的分钟值
            var second = dateObj.getSeconds(); //当前系统时间的秒钟值
            var timeValue = "" +((hour >= 12) ? (hour >= 18) ? "晚上" : "下午" : "上午" ); //当前时间属于上午、晚上还是下午
            newDate = dateFilter(year)+"-"+dateFilter(month)+"-"+dateFilter(date)+" "+dateFilter(hour)+":"+dateFilter(minute)+":"+dateFilter(second);
            $("#nowTime").val("当前时间： "+newDate+"　"+week+"    "+timeValue);
            setTimeout(getLangDate,1000);
        }
    </script>
</div>
</body>
</html>