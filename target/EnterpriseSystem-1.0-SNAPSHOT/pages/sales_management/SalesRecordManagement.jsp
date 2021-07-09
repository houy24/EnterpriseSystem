<%--
  Created by IntelliJ IDEA.
  User: 86150
  Date: 2021/6/8
  Time: 21:21
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

    <script>
        layui.use(['layer', 'table', 'dropdown', 'element'], function () {
            let layer = layui.layer //弹层
                , table = layui.table //表格
            let dropdown = layui.dropdown;
            //第一个实例
            table.render({
                elem: '#dome'
                , height: 563
                , url: '${pageContext.request.contextPath}/getAllSalesRecord' //数据接口
                , cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
                , skin: 'line'  //表格风格 line （行边框风格）row （列边框风格）nob （无边框风格）
                , even: true    //隔行换色
                , page: {
                    layout: ['count', 'prev', 'page', 'next', 'limit', 'refresh', 'skip']
                }
                , toolbar: 'default' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
                , limits: [5, 10, 15, 20]  //每页条数的选择项，默认：[10,20,30,40,50,60,70,80,90]。
                , limit: 10 //每页默认显示的数量
                ,totalRow: true
                // ,method:'POST'  //提交方式
                , cols: [[ //表头
                    {field: 'saleRecordId', title: '销售记录编号', fixed: 'left', totalRowText: '合计'}
                    , {field: 'userName', title: '用户姓名', width: 100}
                    , {field: 'userId', title: '用户编号', hide:true}
                    , {field: 'saleTaskId', title: '销售任务编号', hide:true}
                    , {field: 'productName', title: '产品名称'}
                    , {field: 'productId', title: '产品编号', hide:true}
                    , {field: 'saleNumber', title: '销售数量', sort: true, totalRow: true}
                    , {field: 'saleOneMoney', title: '销售单价成交价', sort: true, totalRow: true}
                    , {field: 'saleSumMoney', title: '销售总金额', sort: true, totalRow: true}
                    , {
                        field: 'saleFinishTime',
                        title: '销售时间',
                        templet: "<div>{{layui.util.toDateString(d.saleFinishTime, 'yyyy年-MM月-dd日 HH:mm:ss')}}</div>",
                        width: 210,
                        sort: true
                    }
                    , {field: 'saleRecordState', title: '销售记录状态', width: 130}
                    , {fixed: 'right', title: '操作', width: 150, align:'center', toolbar: '#barDemo'}

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

            //监听行工具事件
            table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
                var data = obj.data //获得当前行数据
                    ,layEvent = obj.event; //获得 lay-event 对应的值
                if(layEvent === 'detail'){
                    layer.open({
                        type: 2,
                        title: "更新库存数据",
                        closeBtn: 1,
                        shade: [0],
                        area: ['640px', '480px'],
                        offset: 'auto',
                        anim: 2,
                        content: ['/pages/sales_management/MsgForSaleRecord.jsp', 'yes'], //iframe的url，no代表不显示滚动条
                        success: function(layero, index){
                            let body = layer.getChildFrame('body', index);
                            let iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
                            console.log(body.html()) //得到iframe页的body内容
                            body.find('#saleRecordId').val(data.saleRecordId);
                            body.find('#userId').val(data.userId);
                            body.find('#productId').val(data.productId);
                            body.find('#saleTaskId').val(data.saleTaskId);
                            body.find('#saleNumber').val(data.saleNumber);
                            body.find('#saleOneMoney').val(data.saleOneMoney);
                            body.find('#saleSumMoney').val(data.saleSumMoney);

                            let d1 = new Date(data.saleFinishTime);
                            let batchTime = d1.getFullYear() + '-' + (d1.getMonth() + 1) + '-' + d1.getDate() + ' ' + d1.getHours() + ':' + d1.getMinutes() + ':' + d1.getSeconds();

                            body.find('#saleFinishTime').val(batchTime);
                            body.find('#saleRecordState').val(data.saleRecordState);
                            iframeWin.layui.form.render('checkbox');
                        }
                    });
                } else if(layEvent === 'more'){
                    //下拉菜单
                    dropdown.render({
                        elem: this //触发事件的 DOM 对象
                        ,show: true //外部事件触发即显示
                        ,data: [{
                            title: '编辑'
                            ,id: 'edit'
                        },{
                            title: '删除'
                            ,id: 'del'
                        }]
                        ,click: function(menudata){
                            if(menudata.id === 'del'){
                                layer.confirm('真的删除行么', function(index){
                                    obj.del(); //删除对应行（tr）的DOM结构
                                    layer.close(index);

                                    //向服务端发送删除指令
                                    $.ajax({
                                        url:'${pageContext.request.contextPath}/deleteBySaleTaskId',
                                        data:{
                                            saleRecordId: data.saleRecordId
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
                            } else if(menudata.id === 'edit'){
                                layer.open({
                                    type: 2,
                                    title: "更新库存数据",
                                    closeBtn: 1,
                                    shade: [0],
                                    area: ['640px', '480px'],
                                    offset: 'auto',
                                    anim: 2,
                                    content: ['/pages/sales_management/MsgForSaleRecordManagement.jsp', 'yes'], //iframe的url，no代表不显示滚动条
                                    success: function(layero, index){
                                        let body = layer.getChildFrame('body', index);
                                        let iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
                                        console.log(body.html()) //得到iframe页的body内容
                                        body.find('#saleRecordId').val(data.saleRecordId);
                                        body.find('#userId').val(data.userId);
                                        body.find('#productId').val(data.productId);
                                        body.find('#saleTaskId').val(data.saleTaskId);
                                        body.find('#saleNumber').val(data.saleNumber);
                                        body.find('#saleOneMoney').val(data.saleOneMoney);
                                        body.find('#saleSumMoney').val(data.saleSumMoney);
                                        let d1 = new Date(data.saleFinishTime);
                                        let batchTime = d1.getFullYear() + '-' + (d1.getMonth() + 1) + '-' + d1.getDate() + ' ' + d1.getHours() + ':' + d1.getMinutes() + ':' + d1.getSeconds();

                                        body.find('#saleFinishTime').val(batchTime);
                                        body.find('#saleRecordState').val(data.saleRecordState);
                                        iframeWin.layui.form.render('checkbox');
                                    }
                                });
                            }
                        }
                        ,align: 'right' //右对齐弹出（v2.6.8 新增）
                        ,style: 'box-shadow: 1px 1px 10px rgb(0 0 0 / 12%);' //设置额外样式
                    })
                }
            });
        });
    </script>
</head>
<body>

<blockquote class="layui-elem-quote layui-text" style="font-size: 28px">
    销售记录管理
</blockquote>
<div>
    <table id="dome" lay-filter="test" class="layui-table-body"></table>
    <script type="text/html" id="barDemo">
        <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
        <a class="layui-btn layui-btn-xs" lay-event="more">更多 <i class="layui-icon layui-icon-down"></i></a>
    </script>

</div>
</body>
</html>