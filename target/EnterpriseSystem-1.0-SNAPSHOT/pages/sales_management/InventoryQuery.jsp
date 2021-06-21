<%--
  Created by IntelliJ IDEA.
  User: 86150
  Date: 2021/6/10
  Time: 8:16
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
                        templet: "<div>{{layui.util.toDateString(d.sbj_start, 'yyyy年-MM月-dd日 HH:mm:ss')}}</div>",
                        width: 210,
                        sort: true,
                        edit: 'text'
                    }
                    , {field: 'productionAddress', title: '生产地址', width: 170, edit: 'text'}
                    , {field: 'oneInPrice', title: '单进价', width: 110, sort: true, edit: 'text'}
                    , {field: 'totalInPrice', title: '入厂总进价', width: 130, sort: true, totalRow: true, edit: 'text'}
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

            let $ = layui.$, active = {
                reload: function () {
                    let demoReload1 = $('#demoReload1');
                    let demoReload2 = $('#demoReload2');
                    layer.msg(demoReload1.val() + "," + demoReload2.val());
                    let productType = demoReload1.val();
                    let productName = demoReload2.val();
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
    库存查询
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
</div>
</body>
</html>
