<%--
  Created by IntelliJ IDEA.
  User: 86150
  Date: 2021/6/18
  Time: 14:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/layui/css/layui.css?time=<%=Math.random()%>">
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js?time=<%=Math.random()%>"></script>
    <script src="${pageContext.request.contextPath}/resources/layui/layui.js?time=<%=Math.random()%>"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/resources/js/menuComponents.js?time=<%=Math.random()%>"></script>

    <script>
        layui.use(['layer', 'laypage', 'laydate', 'table', 'element'], function () {
            let layer = layui.layer //弹层
                , table = layui.table //表格
            //第一个实例
            table.render({
                elem: '#dome'
                , height: 480
                , url: '${pageContext.request.contextPath}/getAllSaleTask' //数据接口
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
                    {field: 'ID', width: 80, title: '序号', fixed: 'left', type: 'numbers'}
                    , {field: 'saleTaskId', title: '销售任务编号', hide: true,}
                    , {field: 'userId', title: '用户编号', hide: true,}
                    , {field: 'userName', title: '用户姓名', hide: true,}
                    , {field: 'productId', title: '产品编号', hide: true,}
                    , {field: 'productName', title: '产品名称'}
                    , {field: 'productTypeId', title: '产品类别编号', hide: true,}
                    , {field: 'productTypeName', title: '产品类型'}
                    , {field: 'productCommission', title: '产品提成', sort: true}
                    , {field: 'productWarehouseId', title: '产品库存编号', hide: true,}
                    , {field: 'productPreSumNumber', title: '销售任务总量', sort: true, width: 130}
                    , {field: 'productNumber', title: '还需销售数量', sort: true, width: 130}
                    , {field: 'oneInPrice', title: '单件进价', sort: true}
                    , {field: 'oneLowestPrice', title: '单件最低成交价', sort: true, width: 150}
                    , {field: 'sumLowestPrice', title: '总最低成交价', sort: true, width: 130}
                    , {
                        field: 'taskStartTime',
                        title: '开始时间',
                        templet: "<div>{{layui.util.toDateString(d.taskStartTime, 'yyyy年-MM月-dd日 HH:mm:ss')}}</div>",
                        sort: true
                    }
                    , {
                        field: 'latestFinishTime',
                        title: '最晚成交时间',
                        templet: "<div>{{layui.util.toDateString(d.latestFinishTime, 'yyyy年-MM月-dd日 HH:mm:ss')}}</div>",
                        sort: true
                        , width: 130
                    }
                    , {field: 'finishState', title: '完成状态'}
                    , {fixed: 'right', title: '操作', width: 200, align: 'center', toolbar: '#barDemo'}
                ]]
                , done: function (res) {
                    $('th').css({'background-color': '#009688', 'color': 'black', 'font-weight': '500'});
                    $('.layui-table-total.layui-table tbody tr').css({'background-color': '#ffffb4'});

                    let that = this.elem.next();
                    res.data.forEach(function (item, index) {
                        //console.log(item.empName);item表示每列显示的数据
                        if (index % 2 === 0) {
                            that.find(".layui-table-box tbody tr[data-index='" + index + "']").css('background-color', '#ecedff').addClass('addclass');
                        } else {
                            that.find(".layui-table-box tbody tr[data-index='" + index + "']").css('background-color', '#E0EEF6').addClass('addclass');
                        }
                    })
                }
            });

            //监听行工具事件
            table.on('tool(test)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
                let data = obj.data //获得当前行数据
                    , layEvent = obj.event; //获得 lay-event 对应的值
                if (layEvent === 'detail') {
                    layer.open({
                        type: 2,
                        title: "更新库存数据",
                        closeBtn: 1,
                        shade: [0],
                        area: ['640px', '480px'],
                        offset: 'auto',
                        anim: 2,
                        content: ['/pages/sales_management/MsgForSaleTask.jsp', 'yes'], //iframe的url，no代表不显示滚动条
                        success: function(layero, index){
                            let body = layer.getChildFrame('body', index);
                            let iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
                            console.log(body.html()) //得到iframe页的body内容
                            body.find('#saleTaskId').val(data.saleTaskId);
                            body.find('#userId').val(data.userId);
                            body.find('#productId').val(data.productId);
                            body.find('#productWarehouseId').val(data.productWarehouseId);
                            body.find('#productPreSumNumber').val(data.productPreSumNumber);
                            body.find('#productNumber').val(data.productNumber);
                            body.find('#oneLowestPrice').val(data.oneLowestPrice);
                            body.find('#sumLowestPrice').val(data.sumLowestPrice);

                            let d1 = new Date(data.taskStartTime);
                            let batchTime = d1.getFullYear() + '-' + (d1.getMonth() + 1) + '-' + d1.getDate() + ' ' + d1.getHours() + ':' + d1.getMinutes() + ':' + d1.getSeconds();
                            let d2 = new Date(data.latestFinishTime);
                            let lastTime =  d2.getFullYear() + '-' + (d2.getMonth() + 1) + '-' + d2.getDate() + ' ' + d2.getHours() + ':' + d2.getMinutes() + ':' + d2.getSeconds();
                            body.find('#taskStartTime').val(batchTime);
                            body.find('#latestFinishTime').val(lastTime);
                            body.find('#finishState').val(data.finishState);
                            iframeWin.layui.form.render('checkbox');
                        }
                    });
                } else if (layEvent === 'delete') {
                    layer.confirm('真的删除行么', function (index) {
                        obj.del(); //删除对应行（tr）的DOM结构
                        layer.close(index);
                        layer.msg('删除操作，当前行 ID:' + data.saleTaskId);

                        //向服务端发送删除指令
                        $.ajax({
                            url:'/deleteBySaleTaskId',
                            data:{
                                saleTaskId: data.saleTaskId
                            },
                            dataType: 'json'
                            ,success:function (res) {
                                console.log(res);
                                if (res.flag){
                                    layer.msg("删除成功");
                                    table.reload();
                                }
                                else {
                                    layer.msg("删除失败");
                                }
                            }
                        })
                    });
                }
            });

        });
    </script>
</head>
<body>

<blockquote class="layui-elem-quote layui-text" style="font-size: 28px">
    销售任务管理
</blockquote>

<table id="dome" lay-filter="test" class="layui-table-body"></table>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看详情</a>
    <a class="layui-btn layui-btn-xs" lay-event="delete">删除记录</a>
</script>
</body>
</html>
