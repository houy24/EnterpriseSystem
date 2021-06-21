<%--
  Created by IntelliJ IDEA.
  User: 86150
  Date: 2021/6/9
  Time: 22:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>库存管理</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/layui/css/layui.css?time=<%=Math.random()%>">
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js?time=<%=Math.random()%>"></script>
    <script src="${pageContext.request.contextPath}/resources/layui/layui.js?time=<%=Math.random()%>"></script>

    <script>
        layui.use(['layer', 'util', 'table', 'dropdown', 'element'], function () {
            let layer = layui.layer //弹层
                , table = layui.table
                , util = layui.util;
            let element = layui.element;
            let dropdown = layui.dropdown;
            //第一个实例
            table.render({
                elem: '#test'
                , height: 523
                , url: '${pageContext.request.contextPath}/getAllInventory' //数据接口
                , cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
                , skin: 'line'  //表格风格 line （行边框风格）row （列边框风格）nob （无边框风格）
                , even: true    //隔行换色
                , page: {
                    layout: ['count', 'prev', 'page', 'next', 'limit', 'refresh', 'skip']
                }
                , toolbar: ['filter']
                // , defaultToolbar : ['filter']
                , limits: [5, 10, 15, 20]  //每页条数的选择项，默认：[10,20,30,40,50,60,70,80,90]。
                , limit: 10 //每页默认显示的数量
                , totalRow: true
                , id: 'testReload'
                // ,method:'POST'  //提交方式
                , cols: [[ //表头
                    {field: 'productWarehouseId', title: '库存编号', width: 200, fixed: 'left', totalRowText: '合计'}
                    , {field: 'productId', title: '产品编号', width: 130, edit: 'text'}
                    , {field: 'productTypeId', title: '产品类别编号', width: 160, edit: 'text'}
                    , {field: 'productTypeName', title: '产品类型', width: 110, edit: 'text'}
                    , {field: 'productCommission', title: '产品提成', width: 110, sort: true, edit: 'text'}
                    , {field: 'productName', title: '产品名称', width: 100, edit: 'text'}
                    , {field: 'productNumber', title: '产品数量', width: 110, sort: true, edit: 'text'}
                    , {
                        field: 'InWareHouseTime',
                        title: '入厂日期',
                        templet: "<div>{{layui.util.toDateString(d.InWareHouseTime, 'yyyy年-MM月-dd日 HH:mm:ss')}}</div>",
                        width: 210,
                        sort: true,
                        edit: 'text'
                    }
                    , {field: 'productionAddress', title: '生产地址', width: 170, edit: 'text'}
                    , {field: 'oneInPrice', title: '单进价', width: 110, sort: true, edit: 'text'}
                    , {field: 'totalInPrice', title: '入厂总进价', width: 130, sort: true, totalRow: true, edit: 'text'}
                    , {fixed: 'right', title: '操作', width: 150, align: 'center', toolbar: '#barDemo'}
                ]]
                , done: function (res, curr, count) {
                    $('th').css({'background-color': '#009688', 'color': 'black', 'font-weight': '500'});
                    $('layui-table-total.layui-table tbody tr').css({'color': 'red'});
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

            //监听单元格编辑
            table.on('edit(test)', function (obj) {
                // 单元格编辑之前的值
                let oldText = $(this).prev().text();
                let value = obj.value //得到修改后的值
                    , data = obj.data //得到所在行所有键值
                    , field = obj.field; //得到字段
                layer.msg('[ID: ' + data.productWarehouseId + '] ' + field + ' 字段 ' + oldText + ' 更改值为：' + util.escape(value));
                //数据库操作
            });

            //监听行工具事件
            table.on('tool(test)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
                let data = obj.data //获得当前行数据
                    , layEvent = obj.event; //获得 lay-event 对应的值
                if (layEvent === 'detail') {
                    layer.open({
                        type: 2,
                        title: "库存数据",
                        closeBtn: 1,
                        shade: [0],
                        area: ['640px', '480px'],
                        offset: 'auto',
                        anim: 2,
                        content: ['/pages/sales_management/MsgForAssignSalesTasks.html', 'no'], //iframe的url，no代表不显示滚动条
                        success: function(layero, index){
                            let body = layer.getChildFrame('body', index);
                            let iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
                            console.log(body.html()) //得到iframe页的body内容
                            body.find('#productWarehouseId').val(data.productWarehouseId);
                            body.find('#productId').val(data.productId);
                            body.find('#productName').val(data.productName);
                            body.find('#productTypeName').val(data.productTypeName);
                            body.find('#productNumber').val(data.productNumber);
                            body.find('#oneInPrice').val(data.oneInPrice);
                            body.find('#totalInPrice').val(data.totalInPrice);
                            let d1 = new Date(data.InWareHouseTime);
                            let batchTime = d1.getFullYear() + '-' + (d1.getMonth() + 1) + '-' + d1.getDate() + ' ' + d1.getHours() + ':' + d1.getMinutes() + ':' + d1.getSeconds();
                            body.find('#InWareHouseTime').val(batchTime);
                            body.find('#productionAddress').val(data.productionAddress);
                        }
                    });

                } else if (layEvent === 'more') {
                    //下拉菜单
                    dropdown.render({
                        elem: this //触发事件的 DOM 对象
                        , show: true //外部事件触发即显示
                        , data: [{
                            title: '编辑'
                            , id: 'edit'
                        }, {
                            title: '删除'
                            , id: 'del'
                        }]
                        , click: function (menudata) {
                            if (menudata.id === 'del') {
                                layer.confirm('真的删除行么', function (index) {
                                    obj.del(); //删除对应行（tr）的DOM结构
                                    layer.close(index);
                                    layer.msg('删除操作，当前行 ID:' + data.productWarehouseId);
                                });
                                //向服务端发送删除指令
                                $.ajax({
                                    url:'/deleteOneProductWarehouse',
                                    data:{
                                        productWarehouseId: data.productWarehouseId
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
                            } else if (menudata.id === 'edit') {
                                layer.open({
                                    type: 2,
                                    title: "更新库存数据",
                                    closeBtn: 1,
                                    shade: [0],
                                    area: ['640px', '480px'],
                                    offset: 'auto',
                                    anim: 2,
                                    content: ['/pages/sales_management/MsgForInventoryManagement.html', 'no'], //iframe的url，no代表不显示滚动条
                                    success: function(layero, index){
                                        let body = layer.getChildFrame('body', index);
                                        let iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
                                        console.log(body.html()) //得到iframe页的body内容
                                        body.find('#productWarehouseId').val(data.productWarehouseId);
                                        body.find('#productId').val(data.productId);
                                        body.find('#productName').val(data.productName);
                                        body.find('#productTypeName').val(data.productTypeName);
                                        body.find('#productNumber').val(data.productNumber);
                                        body.find('#oneInPrice').val(data.oneInPrice);
                                        body.find('#totalInPrice').val(data.totalInPrice);
                                        let d1 = new Date(data.InWareHouseTime);
                                        let batchTime = d1.getFullYear() + '-' + (d1.getMonth() + 1) + '-' + d1.getDate() + ' ' + d1.getHours() + ':' + d1.getMinutes() + ':' + d1.getSeconds();
                                        body.find('#InWareHouseTime').val(batchTime);
                                        body.find('#productionAddress').val(data.productionAddress);

                                        iframeWin.layui.form.render('checkbox');
                                    }
                                });

                            }
                        }
                        , align: 'right' //右对齐弹出（v2.6.8 新增）
                        , style: 'box-shadow: 1px 1px 10px rgb(0 0 0 / 12%);' //设置额外样式
                    })
                }
            });

            let $ = layui.$, active = {
                reload: function () {
                    let demoReload1 = $('#demoReload1');
                    let demoReload2 = $('#demoReload2');
                    layer.msg(demoReload1.val() + "," + demoReload2.val());
                    let productType = demoReload1.val();
                    let productName = demoReload2.val();
                    // let json = [];
                    // json['productType'] = productType;
                    // json['productName'] = productName;
                    //执行重载
                    table.reload('testReload', {
                        url: '${pageContext.request.contextPath}/getInventoryByParameter' //数据接口
                        , page: {
                            curr: 1 //重新从第 1 页开始
                        }
                        , where: {
                            productType: productType,
                            productName: productName
                        }
                    });
                }
            };

            $('.demoTable .btn1').on('click', function () {
                let type = $(this).data('type');
                active[type] ? active[type].call(this) : '';
            });
            $('.demoTable .btn2').on('click', function () {
                let type = $(this).data('type');
                active[type] ? active[type].call(this) : '';
            });
        });
    </script>
</head>
<body>

<blockquote class="layui-elem-quote layui-text" style="font-size: 28px">
    库存管理
</blockquote>
<div>
    <div class="demoTable">
        <label style="width: 120px;">商品类型：</label>
        <div class="layui-inline" style="width: 120px;">
            <input class="layui-input" name="id" id="demoReload1" autocomplete="off">
        </div>
        <button class="layui-btn btn1" data-type="reload">搜索</button>
        <label style="margin-left: 30px;width: 120px;">商品名称：</label>
        <div class="layui-inline" style="width: 120px;">
            <input class="layui-input" name="id" id="demoReload2" autocomplete="off">
        </div>
        <button class="layui-btn btn2" data-type="reload">搜索</button>
    </div>

    <table id="test" lay-filter="test" class="layui-table-body"></table>
    <script type="text/html" id="barDemo">
        <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
        <a class="layui-btn layui-btn-xs" lay-event="more">更多 <i class="layui-icon layui-icon-down"></i></a>
    </script>

</div>
</body>
</html>
