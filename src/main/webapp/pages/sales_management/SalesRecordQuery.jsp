<%@ page import="com.alibaba.fastjson.JSONObject" %>
<%--
  Created by IntelliJ IDEA.
  User: 86150
  Date: 2021/6/7
  Time: 15:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/layui/css/layui.css?time=<%=Math.random()%>">
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js?time=<%=Math.random()%>"></script>
    <script src="${pageContext.request.contextPath}/resources/layui/layui.js?time=<%=Math.random()%>"></script>

    <script>
        layui.use(['layer', 'laydate', 'table', 'element'], function () {
            let laydate = layui.laydate
                , table = layui.table //表格
            //第一个实例
            table.render({
                elem: '#dome'
                , height: 480
                , id: 'testReload'
                , url: '${pageContext.request.contextPath}/SaleRecordGetAll' //数据接口
                , cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
                , skin: 'line'  //表格风格 line （行边框风格）row （列边框风格）nob （无边框风格）
                , even: true    //隔行换色
                , page: {
                    layout: ['count', 'prev', 'page', 'next', 'limit', 'refresh', 'skip']
                }
                , limits: [5, 10, 15, 20]  //每页条数的选择项，默认：[10,20,30,40,50,60,70,80,90]。
                , limit: 10 //每页默认显示的数量
                // ,method:'POST'  //提交方式
                , cols: [[ //表头
                    {field: 'saleRecordId', title: '销售记录编号', width: 160, fixed: 'left'}
                    , {field: 'userName', title: '用户编号', width: 100}
                    , {field: 'saleTaskId', title: '销售任务编号', width: 160}
                    , {field: 'productName', title: '产品编号', width: 160}
                    , {field: 'saleNumber', title: '销售数量', width: 110, sort: true}
                    , {field: 'saleOneMoney', title: '销售单价成交价', width: 160, sort: true}
                    , {field: 'saleSumMoney', title: '销售总金额', width: 130, sort: true}
                    , {
                        field: 'saleFinishTime',
                        title: '销售时间',
                        templet: "<div>{{layui.util.toDateString(d.saleFinishTime, 'yyyy年-MM月-dd日 HH:mm:ss')}}</div>",
                        width: 210,
                        sort: true
                    }
                    , {field: 'saleRecordState', title: '销售记录状态', width: 130}
                ]]
                , done: function (res) {
                    $('th').css({ 'background-color': '#009688', 'color': 'black', 'font-weight': '500' });
                    $('.layui-table-total.layui-table tbody tr').css({ 'background-color': '#ffffb4' });

                    let that = this.elem.next(); res.data.forEach(function (item, index) {
                        //console.log(item.empName);item表示每列显示的数据
                        if (index % 2 === 0) {
                            that.find(".layui-table-box tbody tr[data-index='" + index + "']").css('background-color', '#ecedff').addClass('addclass');
                        } else {
                            that.find(".layui-table-box tbody tr[data-index='" + index + "']").css('background-color', '#E0EEF6').addClass('addclass');
                        }
                    })
                }
            });

            //常规用法
            laydate.render({
                elem: '#date1'
            });//常规用法
            laydate.render({
                elem: '#date2'
            });

            let active = {
                reload: function () {
                    let demoReload1 = $('#date1');
                    let demoReload2 = $('#date2');
                    layer.msg(demoReload1.val() + "," + demoReload2.val());
                    let startTime = demoReload1.val();
                    let endTime = demoReload2.val();
                    //执行重载
                    table.reload('testReload', {
                        url: '${pageContext.request.contextPath}/getSaleRecordByTime' //数据接口
                        , page: {
                            curr: 1 //重新从第 1 页开始
                        }
                        , where: {
                            startTime: startTime,
                            endTime: endTime
                        }
                    });
                }
            };

            $('.demoTable .btn').on('click', function () {
                let type = $(this).data('type');
                active[type] ? active[type].call(this) : '';
            });

        });
    </script>
</head>
<body>

<blockquote class="layui-elem-quote layui-text" style="font-size: 28px">
    个人销售记录查询
</blockquote>
<div class="layui-form-item">
    <div class="demoTable">
        <label class="layui-form-label">日期范围</label>
        <div class="layui-inline" id="test6">
            <div class="layui-input-inline">
                <input type="text" autocomplete="off" id="date1" class="layui-input" placeholder="开始日期">
            </div>
            <div class="layui-form-mid">-</div>
            <div class="layui-input-inline">
                <input type="text" autocomplete="off" id="date2" class="layui-input" placeholder="结束日期">
            </div>
        </div>
        <button class="layui-btn-xs layui-btn btn" data-type="reload" style="width: 80px;height: 38px; padding: 0 0 0 0">查询</button>
    </div>
</div>
<table id="dome" lay-filter="test" class="layui-table-body"></table>
</body>
</html>
