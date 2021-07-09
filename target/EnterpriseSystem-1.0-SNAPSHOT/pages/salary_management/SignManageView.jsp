<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.xxx.service.Department.DepartmentService" %>
<%@ page import="com.xxx.service.Department.DepartmentServiceImpl" %>
<%@ page import="com.xxx.pojo.Department" %>
<%@ page import="java.util.List" %>
<%@ page import="com.xxx.service.Position.PositionService" %>
<%@ page import="com.xxx.service.Position.PositionServiceImpl" %>
<%@ page import="com.xxx.pojo.Position" %>
<%@ page import="com.xxx.service.WorkTitle.WorkTitleService" %>
<%@ page import="com.xxx.service.WorkTitle.WorkTitleServiceImpl" %>
<%@ page import="com.xxx.pojo.WorkTitle" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>考勤管理</title>
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

    <%
        DepartmentService departmentService = new DepartmentServiceImpl();
        List<Department> departmentList = departmentService.selectAll(); // 预查询出所有部门
    %>

</head>



<body>

<%--标题，刷新按钮 部分--%>
<div class="x-nav" style="margin-top: 10px;margin-left: 10px;">
            <span class="layui-breadcrumb" style="visibility: visible">
                <span class="spanTitle"><a>首页</a> </span>
    <%--            <span lay-separator>/</span>--%>
                <span class="spanTitle"><a>考勤管理</a> </span>
    <%--            <span lay-separator>/</span>--%>
                <span class="spanTitle"><a>考勤列表</a> </span>
            </span>

    <a class="layui-btn layui-btn-small layui-btn-normal aFlush"
       href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="height:10px;font-size: 20px;!important;">&#xe669;</i>
    </a>
</div>

<%--搜索，操作栏--%>
<script type="text/html" id="toolbarDemo">  <%--工具栏--%>

<div class="layui-row" style="">  <%--margin-left: 20px;margin-top: 20px;--%>
    <%--搜索，  搜索员工姓名，员工部门，考勤月份--%>
    <div class="layui-form">

    <%--管理员，正常搜索--%>
    <c:if test="${userAccount.userType == 'manager'}">
        <%--员工姓名搜索--%>
        <span style="font-size: 18px;">员工姓名：</span>
        <div class="layui-inline">
            <input class="layui-input" style="font-size: 18px;"
                   name="search" id="demoReload" autocomplete="off">
        </div>

        <%--员工部门搜索--%>
        <span style="font-size: 18px;">部门：</span>
        <%--搜索选择框--%>
        <div class="layui-inline">
            <select name="departmentSearch" id="departmentSearch" lay-verify="required" lay-search="">
                <option value="">选择或搜索</option>
                <c:forEach items="<%=departmentList%>" var="departmentItem">
                    <option value="${departmentItem.departmentId}">${departmentItem.departmentName}</option>
                </c:forEach>
            </select>
        </div>
    </c:if>

        <%--考勤月份搜索--%>
        <span style="font-size: 18px;">考勤月份：</span>

<%--        &lt;%&ndash;年月选择器&ndash;%&gt;--%>
<%--        <div class="layui-input-inline">--%>
<%--            <input type="text" class="layui-input" name="timeMonthSearch" id="timeMonthSearch" placeholder="yyyy-MM">--%>
<%--        </div>--%>

        <div class="layui-inline">
            <input class="layui-input" style="font-size: 18px;width: 200px;"
                   name="timeMonthSearch" id="timeMonthSearch" autocomplete="off" placeholder="yyyy-MM">
        </div>

        <button type="button" class="layui-btn" data-type="reload" style="font-size: 16px;"
                lay-filter="data-search-btn">搜索</button>

    </div>
</div>

</script>


<%--表格，查看操作 部分--%>
<div style="margin-left: 20px;margin-top: 20px;">
    <%--考勤表格--%>
    <table class="layui-hide" id="SignManageList"  lay-filter="demo"></table>

    <%--操作栏--%>
    <script type="text/html" id="barDemo" >
        <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail" style="font-size: 16px;width: 68px;height: 44px;line-height: 27px;">查看</a>

        <%--无编辑删除功能--%>
        <%--        <a class="layui-btn layui-btn-xs" lay-event="edit" style="font-size: 16px;width: 68px;height: 44px;line-height: 27px;">编辑</a>--%>
        <%--        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del" style="font-size: 16px;width: 68px;height: 44px;line-height: 27px;">删除</a>--%>
    </script>

</div>


<script >

    layui.use(['layer', 'table', 'element'], function(){
        var layer = layui.layer //弹层
            ,table = layui.table //表格
            ,element = layui.element //元素操作

        // 向世界问个好
        // layer.msg('Hello World');

        //执行一个 table 实例
        var tableIns = table.render({
            elem: '#SignManageList'
            ,height: 540 //设置表的高度
            ,url: '${pageContext.request.contextPath}/getAllSignManage' //数据接口  //（！）
            ,title: '考勤表'
            ,page: true //开启分页
            ,limits: [5,10,15,20]
            ,limit: 5

            ,id: 'testReload'

            ,toolbar: '#toolbarDemo' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档 (?)

            ,cols: [[ //表头

                // 显示考勤信息，主要和工资有关
                // （日期<年-月-日>，员工编号，员工姓名，员工部门）
                // （考勤情况<迟到/早退/缺勤/出差/平常加班/周末加班>，工资情况（加200/减...））

                // SignManage
                // signDayTime , userId , userName , departmentName ,
                // signStatus , signMoneyStatus

                {type:'checkbox'},  //复选框

                {field: 'signDayTime', width: 169, align:'center',  title: '日期', sort: true,
                    style: 'font-size:18px;'}  // 用户编号
                ,{field: 'userId',width: 167, align:'center', title: '员工编号',
                    style: 'font-size:18px;'}
                ,{field: 'userName',width: 151, align:'center',title: '员工姓名',
                    style: 'font-size:18px;'}
                ,{field: 'departmentName',width: 184, align:'center',title: '员工部门',
                    style: 'font-size:18px;'}
                ,{field: 'signStatus',width: 191, align:'center',title: '考勤情况',
                    style: 'font-size:18px;'}
                ,{field: 'signMoneyStatus' ,width: 210, align:'center',title: '工资情况',
                    style: 'font-size:18px;'}

                ,{title: '操作',fixed: 'right', width: 215, align:'center', toolbar: '#barDemo',
                    style: 'font-size:18px;'} //常驻右边工具栏

            ]],

            done: function () {
                /*表头样式*/
                $('th').css({'background-color': '#F2F2F2', /*'color': '#fff',*/'font-weight':'bold','font-size':'18px'});

            }

        });

        // //年月选择器
        // laydate.render({
        //     elem: '#timeMonthSearch'
        //     ,type: 'month'
        //
        // });

        /*搜索功能*/
        var active = {
            reload: function(){
                var demoReload = $('#demoReload');
                var departmentSearch = $('#departmentSearch');
                var timeMonthSearch = $('#timeMonthSearch');

                /*正则表达式校验输入的月份查询*/
                if (timeMonthSearch.val()!== '' && !timeMonthSearch.val().match('\\d{4}-\\d{2}')) {
                    layer.msg('请输入正确的月份'/*,{
                        icon: 5
                    }*/);
                    return false;
                }

                //执行重载
                table.reload('testReload', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    ,where: {
                        userName: demoReload.val(), // 员工姓名
                        departmentIdSearch: departmentSearch.val(),   // 部门搜索
                        timeMonthSearch: timeMonthSearch.val()    // 考勤月份搜索
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

            // 无增加功能
            <%--if (layEvent === 'add') { // 增加--%>
            <%--    // layer.msg('add！');--%>

            <%--    var index = layer.open({--%>
            <%--        title: '增加职称信息',--%>
            <%--        type: 2,--%>
            <%--        shade: 0.2,--%>
            <%--        maxmin:true,--%>
            <%--        shadeClose: true,--%>
            <%--        area: ['70%', '80%'],--%>
            <%--        offset: '9%',--%>
            <%--        //加载编辑页面的弹出层--%>
            <%--        content: 'WorkTitleAdd.jsp',--%>
            <%--        btn: '关闭',--%>
            <%--        skin: 'editLayer',   // 自定义样式--%>
            <%--        yes:function(){     // 点关闭按钮的话，刷新页面--%>
            <%--            tableIns.reload("demo",{--%>
            <%--                url:'${pageContext.request.contextPath}/getAllWorkTitle'--%>
            <%--            });--%>
            <%--            layer.closeAll();--%>
            <%--        },--%>
            <%--        btnAlign: 'c', //按钮居中--%>
            <%--    });--%>
            <%--    return false;--%>

            <%--}--%>
        });

        //监听行工具事件
        table.on('tool(demo)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data //获得当前行数据
                ,layEvent = obj.event; //获得 lay-event 对应的值

            console.log("!!!!");
            console.log(data.signStatus + ',' + data.signMoneyStatus);

            // UserDataMoney
            // 只有查看功能
            if (layEvent === 'detail') {
                //layer.msg('查看 => ' + data);

                var index = layer.open({
                    title: '查看考勤信息',
                    type: 2,
                    shade: 0.2,
                    maxmin:true,
                    shadeClose: true,
                    // area: ['75%', '80%'],
                    area: ['75%', '80%'],
                    offset: '9%',

                    // 显示考勤信息，主要和工资有关
                    // （日期<年-月-日>，员工编号，员工姓名，员工部门）
                    // （考勤情况<迟到/早退/缺勤/出差/平常加班/周末加班>，工资情况（加200/减...））

                    // SignManage
                    // signDayTime , userId , userName , departmentName ,
                    // signStatus , signMoneyStatus

                    //加载编辑页面的弹出层        （？）
                    content: 'SignManageDetail.jsp?' + 'signDayTime=' + data.signDayTime
                        + '&' +'userId=' + data.userId + '&' + 'userName=' + data.userName
                        + '&' +'departmentName=' + data.departmentName + '&' + 'signStatus=' + data.signStatus
                        + '&' + 'signMoneyStatus=' + data.signMoneyStatus ,

                    btn: '关闭',
                    skin: 'editLayer',   // 自定义样式
                    yes:function(){
                        tableIns.reload("demo",{
                            url:'${pageContext.request.contextPath}/getAllSignManage'
                        });
                        layer.closeAll();
                    },
                    btnAlign: 'c', //按钮居中
                });
                return false;


            }

            /* 无删除和编辑功能 */
            <%--if(layEvent === 'del')--%>
            <%--{--%>
            <%--    layer.confirm('确定要删除么', function(index){--%>

            <%--        //向服务端发送删除指令--%>
            <%--        $.ajax({--%>
            <%--            url: "${pageContext.request.contextPath}/deleteWorkTitle",--%>
            <%--            data: {  // 数据--%>
            <%--                workTitleId : data.workTitleId,--%>
            <%--            },--%>
            <%--            dataType: 'json',--%>
            <%--            success: function (result) {--%>
            <%--                if (result.check.toString() === "exists") {--%>
            <%--                    layer.msg("该职称已被使用，删除失败！");--%>
            <%--                } else if (result.check.toString() === "false") {--%>
            <%--                    layer.msg("删除失败！");--%>
            <%--                } else if (result.check.toString() === "true") {--%>
            <%--                    layer.msg("删除成功！");--%>
            <%--                    obj.del(); //删除对应行（tr）的DOM结构--%>
            <%--                    layer.close(index);--%>
            <%--                }--%>
            <%--            },--%>
            <%--            error: function (XMLHttpRequest, textStatus, errorThrown) {--%>
            <%--                layer.msg("删除失败！");--%>
            <%--                console.log(XMLHttpRequest.status);--%>
            <%--                console.log(XMLHttpRequest.readyState);--%>
            <%--                console.log(textStatus);--%>
            <%--            }--%>

            <%--        });--%>

            <%--        // ...--%>
            <%--    });--%>
            <%--} else if(layEvent === 'edit'){--%>

            <%--    var index = layer.open({--%>
            <%--        title: '编辑职称信息',--%>
            <%--        type: 2,--%>
            <%--        shade: 0.2,--%>
            <%--        maxmin:true,--%>
            <%--        shadeClose: true,--%>
            <%--        area: ['70%', '80%'],--%>
            <%--        offset: '9%',--%>
            <%--        //加载编辑页面的弹出层--%>
            <%--        content: 'WorkTitleEdit.jsp?' + 'workTitleId=' + data.workTitleId--%>
            <%--            + '&' +'workTitleName=' + data.workTitleName--%>
            <%--            + '&' + 'workTitleMoney=' + data.workTitleMoney,--%>
            <%--        btn: '关闭',--%>
            <%--        skin: 'editLayer',   // 自定义样式--%>
            <%--        yes:function(){--%>
            <%--            tableIns.reload("demo",{--%>
            <%--                url:'${pageContext.request.contextPath}/getAllWorkTitle'--%>
            <%--            });--%>
            <%--            layer.closeAll();--%>
            <%--        },--%>
            <%--        btnAlign: 'c', //按钮居中--%>
            <%--    });--%>
            <%--    return false;--%>
            <%--}--%>

        });

    });

</script>






</body>
</html>


