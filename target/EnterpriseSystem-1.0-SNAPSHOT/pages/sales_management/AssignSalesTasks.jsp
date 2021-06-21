<%--
  Created by IntelliJ IDEA.
  User: 86150
  Date: 2021/6/11
  Time: 10:04
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
        layui.use(['layer', 'laydate', 'form', 'util', 'table', 'dropdown', 'element'], function () {
            let laydate = layui.laydate;
            let layer = layui.layer //弹层
                , table = layui.table
                , util = layui.util
                , form = layui.form;
            let element = layui.element;
            let dropdown = layui.dropdown;
            //第一个实例
            table.render({
                elem: '#test'
                , height: 523
                , url: '${pageContext.request.contextPath}/GetRemnantInventory' //数据接口
                , cellMinWidth: 100 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
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
                    {field: 'ID', width: 80, title: '序号', fixed: 'left', type: 'numbers'}
                    , {field: 'productWarehouseId', title: '库存编号', hide: true, fixed: 'left', totalRowText: '合计'}
                    , {field: 'productId', title: '产品编号', hide: true, edit: 'text'}
                    , {field: 'productTypeId', title: '产品类别编号', hide: true, edit: 'text'}
                    , {field: 'productTypeName', title: '产品类型', edit: 'text'}
                    , {field: 'productCommission', title: '产品提成', sort: true, edit: 'text'}
                    , {field: 'productName', title: '产品名称', edit: 'text'}
                    , {field: 'productNumber', title: '产品数量', sort: true, edit: 'text'}
                    , {
                        field: 'InWareHouseTime',
                        title: '入厂日期',
                        templet: "<div>{{layui.util.toDateString(d.InWareHouseTime, 'yyyy年-MM月-dd日 HH:mm:ss')}}</div>",
                        sort: true,
                        edit: 'text'
                    }
                    , {field: 'productionAddress', title: '生产地址', edit: 'text'}
                    , {field: 'oneInPrice', title: '单进价', sort: true, edit: 'text'}
                    , {field: 'totalInPrice', title: '入厂总进价', sort: true, totalRow: true, edit: 'text'}
                    , {fixed: 'right', title: '操作', width: 200, align: 'center', toolbar: '#barDemo'}
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
                console.log(data);
                console.log(layEvent)
                if (layEvent === 'detail') {
                    layer.open({
                        type: 2,
                        title: "未分配库存数据",
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

                } else if (layEvent === 'distribute') {
                    $("#productName").text(data.productName);
                    $("#productId").val(data.productId);
                    $("#oneInPrice").val(data.oneInPrice);
                    $("#productWarehouseId").val(data.productWarehouseId);
                    //检查项目添加到下拉框中
                    $.ajax({
                        url: '/getAllUserData',
                        dataType: 'json',
                        type: 'get',
                        success: function (data) {
                            console.log(data)
                            $.each(data, function (index, item) {
                                $('#user').append(new Option(item.userName, item.userId));// 下拉菜单里添加元素
                            });
                            layui.form.render("select");
                        }
                    })
                    $(".popup-view").css("display", "block");
                }
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
                        url: '${pageContext.request.contextPath}/getRemnantInventoryByParameter' //数据接口
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

            $(".close-pop").on('click', function () {
                $(".popup-view").css("display", "none");
            })

            //常规用法
            laydate.render({
                elem: '#date1'
            });//常规用法
            laydate.render({
                elem: '#date2'
            });

            //监听提交
            form.on('submit(demo2)', function (data) {
                console.log(data);
                let json = data.field;
                json['productName'] = $("#productName").text();
                $.ajax({
                    url:"/addOneSaleTask",
                    type:"post",
                    data:JSON.stringify(json),
                    dataType:"json",
                    contentType:"json/application",
                    success:function (data) {
                        console.log(data);
                        if (data.flag === true){
                            layer.msg("分配销售任务成功！");
                            $(".popup-view").css("display", "none");
                        }
                        else {
                            layer.msg("分配销售任务失败！");
                        }
                    },
                    error: function () {
                        console.log("error")
                    }
                })
                return false;
            });
        });
    </script>
    <style>
        .popup-view {
            z-index: 999999;
            width: 800px;
            height: 640px;
            transform: translateX(50%) translateY(-90%);
            background-color: #fff;
            position: relative;
            background-size: cover;
            display: none;
            box-shadow: 1px 1px 10px rgb(0 0 0);
        }
    </style>
</head>
<body>

<blockquote class="layui-elem-quote layui-text" style="font-size: 28px">
    销售任务分配
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
        <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看详情</a>
        <a class="layui-btn layui-btn-xs" data-method="offset" data-type="auto" lay-event="distribute">分配任务</a>
    </script>

    <div class="popup-view" id="distributeView">
        <form class="layui-form" action="">
            <div style="padding: 30px 30px 30px">
                <div style="height: 40px;">
                    <blockquote class="layui-elem-quote layui-text" style="font-size: 20px">分配销售任务</blockquote>
                </div>
                <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
                    <legend></legend>
                </fieldset>
                <div class="layui-form-item" style="margin-top: 20px;">
                    <div class="layui-inline">
                        <label class="layui-form-label" style="width: 60px;margin-left: -10px;">产品名：</label>
                        <label class="layui-form-label layui-inline" id="productName"
                               style="width: 60px; margin-left: -30px;"></label>
                        <input name="productId" hidden id="productId">
                        <input name="productWarehouseId" hidden id="productWarehouseId">
                        <input name="oneInPrice" hidden id="oneInPrice">
                    </div>
                </div>
                <div class="layui-form-item" style="margin-top: 20px;">
                    <div class="layui-inline">
                        <label class="layui-form-label" style="width: 45px;margin-left: -20px;">员工:</label>
                        <div class="layui-input-inline" style="width: 330px">
                            <select name="userId" id="user">
                                <option value="">请选择人员</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item" style="margin-top: 20px;">
                    <div class="layui-inline">
                        <label class="layui-form-label" style="width: 75px;margin-left: -20px;">任务数量:</label>
                        <input type="text" class="layui-input layui-input-inline" name="productNumber" lay-verify="title" autocomplete="off"
                               style="width: 300px" placeholder="请输入需销售的物品数量">
                    </div>
                </div>
                <div class="layui-form-item" style="margin-top: 20px;">
                    <div class="layui-inline">
                        <label class="layui-form-label" style="width: 135px;margin-left: -24px;">单件物品最低售价:</label>
                        <input type="text" class="layui-input layui-input-inline" name="oneLowestPrice" lay-verify="title" autocomplete="off"
                               style="width: 245px;" placeholder="请输入单件物品最低售价">
                    </div>
                </div>
                <div class="layui-form-item" style="margin-top: 20px;">
                    <div class="layui-inline">
                        <label class="layui-form-label" style="width: 115px;margin-left: -20px;">总物品最低售价:</label>
                        <input type="text" class="layui-input layui-input-inline" name="sumLowestPrice" lay-verify="title" autocomplete="off"
                               style="width: 265px" placeholder="请输入总物品最低售价">
                    </div>
                </div>
                <div class="layui-form-item layui-inline" style="margin-top: 15px;">
                    <label class="layui-form-label" style="width: 75px;margin-left: -20px;">任务期限:</label>
                    <div class="layui-inline" id="test1">
                        <div class="layui-input-inline" style="z-index:999999">
                            <input type="text" autocomplete="off" name="startTime" id="date1" class="layui-input" placeholder="开始日期">
                        </div>
                        <div class="layui-inline layui-form-mid">-</div>
                        <div class="layui-input-inline" style="z-index:999999">
                            <input type="text" autocomplete="off" name="endTime" id="date2" class="layui-input" placeholder="结束日期">
                        </div>
                    </div>
                </div>
                <div class="layui-form-item" style="margin-top: 20px;">
                    <div class="layui-input-block" style="margin-left: 60px;">
                        <button type="submit" class="layui-btn" lay-submit="" lay-filter="demo2">立即提交</button>
                        <button class="close-pop layui-btn layui-btn-primary">关闭</button>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>
