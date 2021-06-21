
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>税率管理</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui/css/layui.css?time=<%=Math.random()%>">
    <script src="${pageContext.request.contextPath}/resources/layui/layui.js?time=<%=Math.random()%>"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js?time=<%=Math.random()%>"></script>
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


        .layui-laypage a,
        .layui-laypage span {

            font-size: 18px;
        }
        /*分页栏设置*/
        #layui-table-page1 {
            margin-left: 350px;
        }
        .layui-laypage-limits {
            font-size: 17px;
        }
        .lay-ignore {
            height:30px;
            margin-top:-4px;
        } /*?*/

        /*设置表格单元格线样式*/
        /*.layui-table td, .layui-table th, .layui-table-col-set, .layui-table-fixed-r, .layui-table-grid-down, .layui-table-header, .layui-table-page, .layui-table-tips-main, .layui-table-tool, .layui-table-total, .layui-table-view, .layui-table[lay-skin=line], .layui-table[lay-skin=row] {*/
        /*    border-color: #bbb; !*#bbb*!*/
        /*}*/

        .editLayer .layui-layer-btn0 {  /*按钮的class是通过浏览器的查看元素功能知道的*/
            font-size: 16px;
            width: 50px;
            height: 32px;
            line-height: 32px;
        }
        .layui-table-tool-self { /*三个工具，打印，生成文件。。。*/
            position: absolute;
            right: 40px;
            top: 16px;
        }

    </style>

</head>


<body>

<%--标题，刷新按钮 部分--%>
<div class="x-nav" style="margin-top: 10px;margin-left: 10px;">
            <span class="layui-breadcrumb" style="visibility: visible">
                <span class="spanTitle"><a>首页</a> </span>
    <%--            <span lay-separator>/</span>--%>
                <span class="spanTitle"><a>税率管理</a> </span>
    <%--            <span lay-separator>/</span>--%>
                <span class="spanTitle"><a>税率列表</a> </span>
            </span>

    <a class="layui-btn layui-btn-small layui-btn-normal aFlush"
       href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="height:10px;font-size: 20px;!important;">&#xe669;</i>
    </a>
</div>

<%--搜索，添加，操作栏--%>
<script type="text/html" id="toolbarDemo">  <%--工具栏--%>

<div class="layui-row" style="">  <%--margin-left: 20px;margin-top: 20px;--%>
    <%--搜索、添加职称按钮--%>
    <div class="layui-form">
        <span style="font-size: 18px;">工资金额：</span>
        <div class="layui-inline">
            <input class="layui-input" style="font-size: 18px;"
                   name="search" id="demoReload" autocomplete="off">
        </div>
        <button type="button" class="layui-btn" data-type="reload" style="font-size: 16px;"
                lay-filter="data-search-btn">搜索</button>

        <div class="layui-inline" style="float: right;margin-right: 100px;" >
            <button class="layui-btn"  lay-event="add"
                    style="font-size: 18px; margin-top: 0px; padding: 2px 10px 4px 10px;line-height: 32px;margin-right: -37px;" onclick="">
                <i class="layui-icon" style="font-size: 16px;">&#xe61f;</i>
                <span style="font-size: 16px;">添加</span>
            </button>
        </div>
    </div>
</div>

</script>


<%--表格，编辑删除操作 部分--%>
<div style="margin-left: 20px;margin-top: 20px;">
    <%--职称表格--%>
    <table class="layui-hide" id="TaxRateList"  lay-filter="demo"></table>

    <%--操作栏--%>
    <script type="text/html" id="barDemo" >
        <%--            <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>--%>
        <a class="layui-btn layui-btn-xs" lay-event="edit" style="font-size: 16px;width: 68px;height: 44px;line-height: 27px;">编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del" style="font-size: 16px;width: 68px;height: 44px;line-height: 27px;">删除</a>
    </script>

</div>


<script>

    layui.use(['layer', 'table', 'element'], function(){
        var layer = layui.layer //弹层
            ,table = layui.table //表格
            ,element = layui.element //元素操作

        // 向世界问个好
        // layer.msg('Hello World');

        //执行一个 table 实例
        var tableIns = table.render({
            elem: '#TaxRateList'
            ,height: 540 //设置表的高度
            ,url: '${pageContext.request.contextPath}/getAllTaxRate' //数据接口 （？） 获得所有税率信息
            ,title: '税率表'
            ,page: true //开启分页
            ,limits: [5,10,15,20]
            ,limit: 5

            ,id: 'testReload'

            ,toolbar: '#toolbarDemo' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档 (?)

            ,cols: [[ //表头

                // 税率表（税率编号，当前工资，适用税率，速算扣除数）
                // tax_rate（taxRateId,money,rate,quicklyReduce）

                {type:'checkbox'},  //复选框

                {field: 'taxRateId', width: 217, align:'center',  title: 'ID',
                    style: 'font-size:18px;'} //职称编号
                ,{field: 'money',width: 253, align:'center', title: '工资', sort: true, // 根据工资排序
                    style: 'font-size:18px;'}
                ,{field: 'rate',width: 169, align:'center',title: '适用税率',
                    style: 'font-size:18px;'}
                ,{field: 'quicklyReduce',width: 332, align:'center',title: '速算扣除数',
                    style: 'font-size:18px;'}

                ,{title: '操作',/*fixed: 'right',*/ width: 265, align:'center', toolbar: '#barDemo',
                    style: 'font-size:18px;'} //常驻右边工具栏

            ]],

            done: function () {
                /*表头样式*/
                $('th').css({'background-color': '#F2F2F2', /*'color': '#fff',*/'font-weight':'bold','font-size':'18px'});

            }

        });

        /*搜索功能*/
        var active = {
            reload: function(){
                var demoReload = $('#demoReload');

                //执行重载
                table.reload('testReload', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    ,where: {
                        searchMoney: demoReload.val() // 根据一个工资，查找对应税率
                    }
                });
            }
        };


        $('body').on('click','.layui-btn', function(){
            console.log('123');
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });


        //头工具栏事件
        table.on('toolbar(demo)', function(obj){
            var layEvent = obj.event; //获得 lay-event 对应的值

            if (layEvent === 'add') { // 增加
                // layer.msg('add！');

                var index = layer.open({
                    title: '增加税率信息',
                    type: 2,
                    shade: 0.2,
                    maxmin:true,
                    shadeClose: true,
                    area: ['71%', '76%'],
                    offset: '9%',
                    //加载编辑页面的弹出层
                    content: 'TaxRateAdd.jsp',
                    btn: '关闭',
                    skin: 'editLayer',   // 自定义样式
                    yes:function(){     // 点关闭按钮的话，刷新页面
                        tableIns.reload("demo",{
                            url:'${pageContext.request.contextPath}/getAllTaxRate'
                        });
                        layer.closeAll();
                    },
                    btnAlign: 'c', //按钮居中
                });
                return false;

            }
        });

        //监听行工具事件
        table.on('tool(demo)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data //获得当前行数据
                ,layEvent = obj.event; //获得 lay-event 对应的值

            if(layEvent === 'del')
            {
                layer.confirm('确定要删除么', function(index){

                    //向服务端发送删除指令
                    $.ajax({
                        url: "${pageContext.request.contextPath}/deleteTaxRate", // (!)
                        data: {  // 数据
                            taxRateId : data.taxRateId, // 税率编号
                        },
                        dataType: 'json',
                        success: function (result) {
                            if (result.check.toString() === "false") {
                                layer.msg("删除失败！");
                            } else if (result.check.toString() === "true") {
                                layer.msg("删除成功！");
                                obj.del(); //删除对应行（tr）的DOM结构
                                tableIns.reload("demo",{
                                    url:'${pageContext.request.contextPath}/getAllTaxRate'
                                });
                                layer.close(index);
                            }
                        },
                        error: function (XMLHttpRequest, textStatus, errorThrown) {
                            layer.msg("删除失败！");
                            console.log(XMLHttpRequest.status);
                            console.log(XMLHttpRequest.readyState);
                            console.log(textStatus);
                        }

                    });

                    // ...
                });
            } else if(layEvent === 'edit'){

                var index = layer.open({
                    title: '编辑税率信息',
                    type: 2,
                    shade: 0.2,
                    maxmin:true,
                    shadeClose: true,
                    area: ['71%', '76%'],
                    offset: '9%',
                    //加载编辑页面的弹出层
                    content: 'TaxRateEdit.jsp?' + 'taxRateId=' + data.taxRateId   // (?)
                        + '&' +'money=' + data.money + '&' + 'rate=' + data.rate
                        + '&' + 'quicklyReduce=' + data.quicklyReduce,
                    btn: '关闭',
                    skin: 'editLayer',   // 自定义样式
                    yes:function(){
                        tableIns.reload("demo",{
                            url:'${pageContext.request.contextPath}/getAllTaxRate'
                        });
                        layer.closeAll();
                    },
                    btnAlign: 'c', //按钮居中
                });
                return false;
            }

        });

    });

</script>






</body>
</html>



