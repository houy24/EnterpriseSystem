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
    <title>工资管理</title>
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
                <span class="spanTitle"><a>工资管理</a> </span>
    <%--            <span lay-separator>/</span>--%>
                <span class="spanTitle"><a>工资查询</a> </span>
            </span>

    <a class="layui-btn layui-btn-small layui-btn-normal aFlush"
       href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="height:10px;font-size: 20px;!important;">&#xe669;</i>
    </a>
</div>

<%--搜索，操作栏--%>
<script type="text/html" id="toolbarDemo">  <%--工具栏--%>

<div class="layui-row" style="">  <%--margin-left: 20px;margin-top: 20px;--%>
    <%--搜索，  搜索员工姓名，员工部门，月份--%>
    <div class="layui-form">

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

        <%--月份搜索--%>
        <span style="font-size: 18px;">月份：</span>

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
    <%--工资表格--%>
    <table class="layui-hide" id="WageAllList"  lay-filter="demo"></table>

    <%--操作栏--%>
    <script type="text/html" id="barDemo" >
        <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail" style="font-size: 16px;width: 68px;height: 44px;line-height: 27px;">查看</a>

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
            elem: '#WageAllList'
            ,height: 540 //设置表的高度
            ,url: '${pageContext.request.contextPath}/getAllWageAll' //数据接口  //（！）
            ,title: '工资表'
            ,page: true //开启分页
            ,limits: [5,10,15,20]
            ,limit: 5

            ,id: 'testReload'

            ,toolbar: '#toolbarDemo' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档 (?)

            ,cols: [[ //表头

                //                   |-----员工信息-----|     	        |-----工资情况-----|
                //月份   员工姓名      员工编号  员工部门     	             应发工资  实发工资
                //wageProvideTime   userName       userId  departmentName     shouldWage  realyWage

                //|----------基本工资----------|  				   |-------------奖金-----------|
                //岗位工资  绩效工资  职称工资 				           工龄工资 加班工资  全勤工资
                //positionMoney  workResultMoney workTitleMoney  	workAgeMoney workOverTimeMoney  fullTimeMoney

                //|---------------------补贴----------------|   			 |------ ----缺勤罚金----------|
                //餐饮补贴  交通补贴  出差补贴  住房补贴     			     迟到罚金  早退罚金  缺勤罚金
                //eatAllowance carAllowance	travelAllowance houseAllowance 	lateMoney outEarlyMoney absentMoney

                //|------------------------------五险一金--------------------------|
                //养老保险  医疗保险  失业保险  工伤保险  生育保险  住房公积金
                //oldEnsure medicalEnsure lostJobEnsure workHurtEnsure birthEnsure houseFundEnsure

                //|----纳税----|
                //个人所得税
                //oneSelfTax

                {type:'checkbox'},  //复选框

                {field: 'wageProvideTime', width: 116, align:'center',  title: '月份', sort: true,
                    style: 'font-size:18px;'}
                ,{field: 'userName',width: 116, align:'center', title: '姓名',
                    style: 'font-size:18px;'}

                //  员工信息
                ,{field: 'userId',width: 122, align:'center',title: '编号',
                    style: 'font-size:18px;'}
                ,{field: 'departmentName',width: 113, align:'center',title: '部门',
                    style: 'font-size:18px;'}

                // 工资情况
                ,{field: 'shouldWage',width: 146, align:'center',title: '应发工资',
                    style: 'font-size:18px;'}
                ,{field: 'realyWage',width: 131, align:'center',title: '实发工资',
                    style: 'font-size:18px;'}

                // 基本工资
                ,{field: 'positionMoney',width: 109, align:'center',title: '岗位',
                    style: 'font-size:18px;'}
                ,{field: 'workResultMoney',width: 100, align:'center',title: '绩效',
                    style: 'font-size:18px;'}
                ,{field: 'workTitleMoney',width: 90, align:'center',title: '职称',
                    style: 'font-size:18px;'}

                // 奖金
                ,{field: 'workAgeMoney',width: 90, align:'center',title: '工龄',
                    style: 'font-size:18px;'}
                ,{field: 'workOverTimeMoney',width: 90, align:'center',title: '加班',
                    style: 'font-size:18px;'}
                ,{field: 'fullTimeMoney',width: 90, align:'center',title: '全勤',
                    style: 'font-size:18px;'}

                // 补贴
                ,{field: 'eatAllowance',width: 90, align:'center',title: '餐饮',
                    style: 'font-size:18px;'}
                ,{field: 'carAllowance',width: 90, align:'center',title: '交通',
                    style: 'font-size:18px;'}
                ,{field: 'travelAllowance',width: 90, align:'center',title: '出差',
                    style: 'font-size:18px;'}
                ,{field: 'houseAllowance',width: 90, align:'center',title: '住房',
                    style: 'font-size:18px;'}

                // 罚金
                ,{field: 'lateMoney',width: 90, align:'center',title: '迟到',
                    style: 'font-size:18px;'}
                ,{field: 'outEarlyMoney',width: 90, align:'center',title: '早退',
                    style: 'font-size:18px;'}
                ,{field: 'absentMoney',width: 90, align:'center',title: '缺勤',
                    style: 'font-size:18px;'}

                // 五险一金
                ,{field: 'oldEnsure',width: 104, align:'center',title: '养老',
                    style: 'font-size:18px;'}
                ,{field: 'medicalEnsure',width: 104, align:'center',title: '医疗',
                    style: 'font-size:18px;'}
                ,{field: 'lostJobEnsure',width: 104, align:'center',title: '失业',
                    style: 'font-size:18px;'}
                ,{field: 'workHurtEnsure',width: 90, align:'center',title: '工伤',
                    style: 'font-size:18px;'}
                ,{field: 'birthEnsure',width: 90, align:'center',title: '生育',
                    style: 'font-size:18px;'}
                ,{field: 'houseFundEnsure',width: 128, align:'center',title: '住房公积金',
                    style: 'font-size:18px;'}
                // 纳税
                ,{field: 'oneSelfTax',width: 128, align:'center',title: '个人所得税',
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

        });

        //监听行工具事件
        table.on('tool(demo)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data //获得当前行数据
                ,layEvent = obj.event; //获得 lay-event 对应的值

            // UserDataMoney
            // 只有查看功能
            if (layEvent === 'detail') {
                //layer.msg('查看 => ' + data);

                var index = layer.open({
                    title: '查看工资详情',
                    type: 2,
                    shade: 0.2,
                    maxmin:true,
                    shadeClose: true,
                    // area: ['75%', '80%'],
                    area: ['75%', '80%'],
                    offset: '9%',

                    // wageAll
                    //月份   员工姓名
                    // 员工编号  员工部门 （员工信息）      应发工资  实发工资 （工资情况）
                    //岗位工资  绩效工资  职称工资 	（基本工资）	 工龄工资 加班工资  全勤工资  （奖金）
                    //餐饮补贴  交通补贴  出差补贴  住房补贴   （补贴）  迟到罚金  早退罚金  缺勤罚金   （缺勤罚金）
                    //养老保险  医疗保险  失业保险  工伤保险  生育保险  住房公积金   （五险一金）
                    //个人所得税   （纳税）

                    //wageProvideTime   userName       userId  departmentName     shouldWage  realyWage
                    //positionMoney  workResultMoney workTitleMoney  	workAgeMoney workOverTimeMoney  fullTimeMoney
                    //eatAllowance carAllowance	travelAllowance houseAllowance 	lateMoney outEarlyMoney absentMoney
                    //oldEnsure medicalEnsure lostJobEnsure workHurtEnsure birthEnsure houseFundEnsure
                    //oneSelfTax


                    //加载编辑页面的弹出层
                    content: 'WageDetail.jsp?' + 'wageProvideTime=' + data.wageProvideTime
                        + '&' +'userName=' + data.userName + '&' + 'userId=' + data.userId
                        + '&' +'departmentName=' + data.departmentName + '&' + 'shouldWage=' + data.shouldWage
                        + '&' + 'realyWage=' + data.realyWage + '&' + 'positionMoney=' + data.positionMoney
                        + '&' + 'workResultMoney=' + data.workResultMoney + '&' + 'workTitleMoney=' + data.workTitleMoney
                        + '&' + 'workAgeMoney=' + data.workAgeMoney + '&' + 'workOverTimeMoney=' + data.workOverTimeMoney
                        + '&' + 'fullTimeMoney=' + data.fullTimeMoney + '&' + 'eatAllowance=' + data.eatAllowance
                        + '&' + 'carAllowance=' + data.carAllowance + '&' + 'travelAllowance=' + data.travelAllowance
                        + '&' + 'houseAllowance=' + data.houseAllowance + '&' + 'lateMoney=' + data.lateMoney
                        + '&' + 'outEarlyMoney=' + data.outEarlyMoney + '&' + 'absentMoney=' + data.absentMoney
                        + '&' + 'oldEnsure=' + data.oldEnsure + '&' + 'medicalEnsure=' + data.medicalEnsure
                        + '&' + 'lostJobEnsure=' + data.lostJobEnsure + '&' + 'workHurtEnsure=' + data.workHurtEnsure
                        + '&' + 'birthEnsure=' + data.birthEnsure + '&' + 'houseFundEnsure=' + data.houseFundEnsure
                        + '&' + 'fullTimeMoney=' + data.fullTimeMoney + '&' + 'eatAllowance=' + data.eatAllowance
                        + '&' + 'oneSelfTax=' + data.oneSelfTax  ,

                    btn: '关闭',
                    skin: 'editLayer',   // 自定义样式
                    yes:function(){
                        tableIns.reload("demo",{
                            url:'${pageContext.request.contextPath}/getAllWageAll'
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


