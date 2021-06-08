<%--
  Created by IntelliJ IDEA.
  User: 86150
  Date: 2021/6/8
  Time: 21:21
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

    <script>
        layui.use(['layer', 'table', 'dropdown', 'element'], function () {
            let layer = layui.layer //弹层
                , table = layui.table //表格
            let element = layui.element;
            let dropdown = layui.dropdown;
            //第一个实例
            table.render({
                elem: '#dome'
                , height: 523
                , url: '${pageContext.request.contextPath}/getAllSalesRecord' //数据接口
                , cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
                , skin: 'line'  //表格风格 line （行边框风格）row （列边框风格）nob （无边框风格）
                , even: true    //隔行换色
                , page: true    //开启分页
                , toolbar: 'default' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
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
                        templet: "<div>{{layui.util.toDateString(d.sbj_start, 'yyyy年-MM月-dd日 HH:mm:ss')}}</div>",
                        width: 210,
                        sort: true
                    }
                    , {field: 'saleRecordState', title: '销售记录状态', width: 130}
                    , {fixed: 'right', title: '操作', width: 150, align:'center', toolbar: '#barDemo'}
                ]]
                , done: function (res, curr, count) {
                    $('th').css({ 'background-color': '#009688', 'color': 'black', 'font-weight': '500' });
                    $('layui-table-total.layui-table tbody tr').css({ 'color': 'red' });
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
                    layer.msg('查看操作');
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
                                    layer.msg('删除操作，当前行 ID:'+ data.saleRecordId);
                                });
                            } else if(menudata.id === 'edit'){
                                layer.msg('编辑操作，当前行 ID:'+ data.saleRecordId);
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
    个人销售记录查询
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