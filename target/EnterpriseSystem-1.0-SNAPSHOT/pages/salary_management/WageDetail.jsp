<%@ page import="com.xxx.service.Department.DepartmentService" %>
<%@ page import="com.xxx.service.Department.DepartmentServiceImpl" %>
<%@ page import="java.util.List" %>
<%@ page import="com.xxx.pojo.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>查看员工工资</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui/css/layui.css?time=<%=Math.random()%>">
    <script src="${pageContext.request.contextPath}/resources/layui/layui.js?time=<%=Math.random()%>"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js?time=<%=Math.random()%>"></script>
    <script>
        layui.use('element', function(){
            var element = layui.element;
        });
    </script>

    <style>
        .divlabel1 {

            margin-top: 30px;
            margin-left: 0px;
        }
        .divlable3 {
            width: 50px;
            margin-left: -31px;
            z-index: -1;
            text-align: center;
        }
        input {
            font-size: 18px;!important;
            width: 200px;
            margin-left: -20px;
        }
        .labelText {
            text-align: left; /* label文字 */
            width: 130px;
            margin-left: 50px;
            z-index: -1;
        }
        div.layui-inline {
            margin-top: -10px;
        }
        .myDivItem {
            margin-bottom: 20px;
        }

        .x-nav{     /*弹出层，缩小版*/
            padding: 0 15px;
            position: relative;
            z-index: 99;
            border-bottom: 1px solid #e5e5e5;
            line-height: 40px;
            height: 40px;
            overflow: hidden;
            background: #fff;
        }
        .spanTitle {
            font-size: 20px;
        }
        .aFlush {
            width: 77px;
            margin-top: -2px;
            float: right;
            margin-right: 18px;
        }
        .myHeadImg {
            margin-left: 150px;
            margin-top: -25px;
            width: 210px;
            height: 220px;
        }

    </style>

    <%
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

        String wageProvideTime = request.getParameter("wageProvideTime");
        String userName = request.getParameter("userName");

        String userId = request.getParameter("userId");
        String departmentName = request.getParameter("departmentName");

        String shouldWage = request.getParameter("shouldWage");
        String realyWage = request.getParameter("realyWage");

        String positionMoney = request.getParameter("positionMoney");
        String workResultMoney = request.getParameter("workResultMoney");
        String workTitleMoney = request.getParameter("workTitleMoney");

        String workAgeMoney = request.getParameter("workAgeMoney");
        String workOverTimeMoney = request.getParameter("workOverTimeMoney");
        String fullTimeMoney = request.getParameter("fullTimeMoney");

        String eatAllowance = request.getParameter("eatAllowance");
        String carAllowance = request.getParameter("carAllowance");
        String travelAllowance = request.getParameter("travelAllowance");
        String houseAllowance = request.getParameter("houseAllowance");

        String lateMoney = request.getParameter("lateMoney");
        String outEarlyMoney = request.getParameter("outEarlyMoney");
        String absentMoney = request.getParameter("absentMoney");

        String oldEnsure = request.getParameter("oldEnsure");
        String medicalEnsure = request.getParameter("medicalEnsure");
        String lostJobEnsure = request.getParameter("lostJobEnsure");
        String workHurtEnsure = request.getParameter("workHurtEnsure");
        String birthEnsure = request.getParameter("birthEnsure");
        String houseFundEnsure = request.getParameter("houseFundEnsure");

        String oneSelfTax = request.getParameter("oneSelfTax");

        System.out.println("WageDetail.jsp => " + wageProvideTime + "," + userName + "," + userId + ","
                + departmentName + "," + shouldWage + "," + realyWage + "," + positionMoney
                + "," + workResultMoney + "," + workTitleMoney + "," + workAgeMoney
                + "," + workOverTimeMoney + "," + fullTimeMoney + "," + eatAllowance
                + "," + carAllowance + "," + travelAllowance + "," + houseAllowance
                + "," + lateMoney + "," + outEarlyMoney + "," + absentMoney
                + "," + oldEnsure + "," + medicalEnsure + "," + lostJobEnsure
                + "," + workHurtEnsure + "," + birthEnsure + "," + houseFundEnsure
                + "," + oneSelfTax );

        // 当前查看列，部门名称
        request.setAttribute("departmentName",departmentName);

        // 预查询出所有部门
        DepartmentService departmentService = new DepartmentServiceImpl();
        List<Department> departmentList = departmentService.selectAll();
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
                    <span class="spanTitle"><a>查看工资信息</a> </span>
                </span>

    <a class="layui-btn layui-btn-small layui-btn-normal aFlush"

       href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="height:10px;font-size: 20px;!important;">&#xe669;</i>
    </a>

</div>


<%--表单部分--%>
<div style="margin-left: 50px;margin-top: 20px;">
    <form class="layui-form" >

        <input hidden name="userId" value="<%=userId%>" disabled />

        <%--时间（月份）--%>
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 0px;">
            <legend>时间</legend>
        </fieldset>
        <div class="myDivItem">
            <div class="layui-form-item divlabel1" >
                <%--月份--%>
                <div class="layui-inline">
                    <label class="layui-form-label labelText" >
                        <span style="font-size: 18px;">月份：</span>
                    </label>
                    <div class="layui-input-inline ">
                        <input  type="text" name="wageProvideTime" lay-verify="required"
                                autocomplete="off" class="layui-input" value="<%=wageProvideTime%>" readonly>
                    </div>
                </div>
            </div>
        </div>

        <%--员工信息（员工姓名，员工编号  员工部门）--%>
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 0px;">
            <legend>员工信息</legend>
        </fieldset>
        <div class="myDivItem">
            <div class="layui-form-item divlabel1" >
                <%--员工姓名--%>
                <div class="layui-inline">
                    <label class="layui-form-label labelText" >
                        <span style="font-size: 18px;">员工姓名：</span>
                    </label>
                    <div class="layui-input-inline ">
                        <input  type="text" name="userName" lay-verify="required"
                                autocomplete="off" class="layui-input" value="<%=userName%>" readonly>
                    </div>
                </div>
                <%--员工编号--%>
                <div class="layui-inline">
                    <label class="layui-form-label labelText" >
                        <span style="font-size: 18px;">员工编号：</span>
                    </label>
                    <div class="layui-input-inline ">
                        <input  type="text" name="userId" lay-verify="required"
                                autocomplete="off" class="layui-input" value="<%=userId%>" readonly>
                    </div>
                </div>
            </div>
            <%--员工部门--%>
            <div class="layui-form-item divlabel1" >
                <div class="layui-inline">
                    <label class="layui-form-label labelText" >
                        <span style="font-size: 18px;">员工部门：</span>
                    </label>
                    <div class="layui-input-inline ">
                        <select name="departmentNameSelect" lay-verify="required" style="width: 260px;" >
                            <c:forEach items="<%=departmentList%>" var="department">
                                <option value="${department.departmentId}"
                                    <%--相等时选中--%>
                                        <c:if test="${ department.departmentName ==  departmentName  }"> selected </c:if>
                                        <c:if test="${ department.departmentName != departmentName }"> disabled </c:if>
                                >${department.departmentName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>
        </div>


        <%--工资情况（应发工资  实发工资）--%>
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 0px;">
            <legend>工资情况</legend>
        </fieldset>
        <div class="myDivItem">
            <div class="layui-form-item divlabel1" >
                <%--应发工资--%>
                <div class="layui-inline">
                    <label class="layui-form-label labelText" >
                        <span style="font-size: 18px;">应发工资：</span>
                    </label>
                    <div class="layui-input-inline ">
                        <input  type="text" name="shouldWage" lay-verify="required"
                                autocomplete="off" class="layui-input" value="<%=shouldWage%>" readonly>
                    </div>
                </div>
                <%--实发工资--%>
                <div class="layui-inline">
                    <label class="layui-form-label labelText" >
                        <span style="font-size: 18px;">实发工资：</span>
                    </label>
                    <div class="layui-input-inline ">
                        <input  type="text" name="realyWage" lay-verify="required" style="color: red;"
                                autocomplete="off" class="layui-input" value="<%=realyWage%>" readonly>
                    </div>
                </div>
            </div>
        </div>

        <%--基本工资（岗位工资  绩效工资  职称工资）--%>
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 0px;">
            <legend>基本工资</legend>
        </fieldset>
        <div class="myDivItem">
            <div class="layui-form-item divlabel1" >
                <%--岗位工资--%>
                <div class="layui-inline">
                    <label class="layui-form-label labelText" >
                        <span style="font-size: 18px;">岗位工资：</span>
                    </label>
                    <div class="layui-input-inline ">
                        <input  type="text" name="positionMoney" lay-verify="required"
                                autocomplete="off" class="layui-input" value="<%=positionMoney%>" readonly>
                    </div>
                </div>
                <%--绩效工资--%>
                <div class="layui-inline">
                    <label class="layui-form-label labelText" >
                        <span style="font-size: 18px;">绩效工资：</span>
                    </label>
                    <div class="layui-input-inline ">
                        <input  type="text" name="workResultMoney" lay-verify="required"
                                autocomplete="off" class="layui-input" value="<%=workResultMoney%>" readonly>
                    </div>
                </div>
            </div>
            <div class="layui-form-item divlabel1" >
                <%--职称工资--%>
                <div class="layui-inline">
                    <label class="layui-form-label labelText" >
                        <span style="font-size: 18px;">职称工资：</span>
                    </label>
                    <div class="layui-input-inline ">
                        <input  type="text" name="workTitleMoney" lay-verify="required"
                                autocomplete="off" class="layui-input" value="<%=workTitleMoney%>" readonly>
                    </div>
                </div>
            </div>
        </div>

        <%--奖金（工龄工资 加班工资  全勤工资）--%>
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 0px;">
            <legend>奖金</legend>
        </fieldset>
        <div class="myDivItem">
            <div class="layui-form-item divlabel1" >
                <%--工龄工资--%>
                <div class="layui-inline">
                    <label class="layui-form-label labelText" >
                        <span style="font-size: 18px;">工龄工资：</span>
                    </label>
                    <div class="layui-input-inline ">
                        <input  type="text" name="workAgeMoney" lay-verify="required"
                                autocomplete="off" class="layui-input" value="<%=workAgeMoney%>" readonly>
                    </div>
                </div>
                <%--加班工资--%>
                <div class="layui-inline">
                    <label class="layui-form-label labelText" >
                        <span style="font-size: 18px;">加班工资：</span>
                    </label>
                    <div class="layui-input-inline ">
                        <input  type="text" name="workOverTimeMoney" lay-verify="required"
                                autocomplete="off" class="layui-input" value="<%=workOverTimeMoney%>" readonly>
                    </div>
                </div>
            </div>
            <div class="layui-form-item divlabel1" >
                <%--全勤工资--%>
                <div class="layui-inline">
                    <label class="layui-form-label labelText" >
                        <span style="font-size: 18px;">全勤工资：</span>
                    </label>
                    <div class="layui-input-inline ">
                        <input  type="text" name="fullTimeMoney" lay-verify="required"
                                autocomplete="off" class="layui-input" value="<%=fullTimeMoney%>" readonly>
                    </div>
                </div>
            </div>
        </div>


        <%--补贴（餐饮补贴  交通补贴  出差补贴  住房补贴）--%>
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 0px;">
            <legend>补贴</legend>
        </fieldset>
        <div class="myDivItem">
            <div class="layui-form-item divlabel1" >
                <%--餐饮补贴--%>
                <div class="layui-inline">
                    <label class="layui-form-label labelText" >
                        <span style="font-size: 18px;">餐饮补贴：</span>
                    </label>
                    <div class="layui-input-inline ">
                        <input  type="text" name="eatAllowance" lay-verify="required"
                                autocomplete="off" class="layui-input" value="<%=eatAllowance%>" readonly>
                    </div>
                </div>
                <%--交通补贴--%>
                <div class="layui-inline">
                    <label class="layui-form-label labelText" >
                        <span style="font-size: 18px;">交通补贴：</span>
                    </label>
                    <div class="layui-input-inline ">
                        <input  type="text" name="carAllowance" lay-verify="required"
                                autocomplete="off" class="layui-input" value="<%=carAllowance%>" readonly>
                    </div>
                </div>
            </div>
            <div class="layui-form-item divlabel1" >
                <%--出差补贴--%>
                <div class="layui-inline">
                    <label class="layui-form-label labelText" >
                        <span style="font-size: 18px;">出差补贴：</span>
                    </label>
                    <div class="layui-input-inline ">
                        <input  type="text" name="travelAllowance" lay-verify="required"
                                autocomplete="off" class="layui-input" value="<%=travelAllowance%>" readonly>
                    </div>
                </div>
                <%--住房补贴--%>
                <div class="layui-inline">
                    <label class="layui-form-label labelText" >
                        <span style="font-size: 18px;">住房补贴：</span>
                    </label>
                    <div class="layui-input-inline ">
                        <input  type="text" name="travelAllowance" lay-verify="required"
                                autocomplete="off" class="layui-input" value="<%=houseAllowance%>" readonly>
                    </div>
                </div>
            </div>
        </div>

        <%--缺勤罚金（迟到罚金  早退罚金  缺勤罚金）--%>
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 0px;">
            <legend>缺勤罚金</legend>
        </fieldset>
        <div class="myDivItem">
            <div class="layui-form-item divlabel1" >
                <%--迟到罚金--%>
                <div class="layui-inline">
                    <label class="layui-form-label labelText" >
                        <span style="font-size: 18px;">迟到罚金：</span>
                    </label>
                    <div class="layui-input-inline ">
                        <input  type="text" name="lateMoney" lay-verify="required"
                                autocomplete="off" class="layui-input" value="<%=lateMoney%>" readonly>
                    </div>
                </div>
                <%--早退罚金--%>
                <div class="layui-inline">
                    <label class="layui-form-label labelText" >
                        <span style="font-size: 18px;">早退罚金：</span>
                    </label>
                    <div class="layui-input-inline ">
                        <input  type="text" name="workOverTimeMoney" lay-verify="required"
                                autocomplete="off" class="layui-input" value="<%=outEarlyMoney%>" readonly>
                    </div>
                </div>
            </div>
            <div class="layui-form-item divlabel1" >
                <%--缺勤罚金--%>
                <div class="layui-inline">
                    <label class="layui-form-label labelText" >
                        <span style="font-size: 18px;">缺勤罚金：</span>
                    </label>
                    <div class="layui-input-inline ">
                        <input  type="text" name="absentMoney" lay-verify="required"
                                autocomplete="off" class="layui-input" value="<%=absentMoney%>" readonly>
                    </div>
                </div>
            </div>
        </div>


        <%--五险一金（养老保险  医疗保险  失业保险  工伤保险  生育保险  住房公积金）--%>
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 0px;">
            <legend>五险一金</legend>
        </fieldset>
        <div class="myDivItem">
            <div class="layui-form-item divlabel1" >
                <%--养老保险--%>
                <div class="layui-inline">
                    <label class="layui-form-label labelText" >
                        <span style="font-size: 18px;">养老保险：</span>
                    </label>
                    <div class="layui-input-inline ">
                        <input  type="text" name="oldEnsure" lay-verify="required"
                                autocomplete="off" class="layui-input" value="<%=oldEnsure%>" readonly>
                    </div>
                </div>
                <%--医疗保险--%>
                <div class="layui-inline">
                    <label class="layui-form-label labelText" >
                        <span style="font-size: 18px;">医疗保险：</span>
                    </label>
                    <div class="layui-input-inline ">
                        <input  type="text" name="medicalEnsure" lay-verify="required"
                                autocomplete="off" class="layui-input" value="<%=medicalEnsure%>" readonly>
                    </div>
                </div>
            </div>
            <div class="layui-form-item divlabel1" >
                <%--失业保险--%>
                <div class="layui-inline">
                    <label class="layui-form-label labelText" >
                        <span style="font-size: 18px;">失业保险：</span>
                    </label>
                    <div class="layui-input-inline ">
                        <input  type="text" name="lostJobEnsure" lay-verify="required"
                                autocomplete="off" class="layui-input" value="<%=lostJobEnsure%>" readonly>
                    </div>
                </div>
                <%--工伤保险--%>
                <div class="layui-inline">
                    <label class="layui-form-label labelText" >
                        <span style="font-size: 18px;">工伤保险：</span>
                    </label>
                    <div class="layui-input-inline ">
                        <input  type="text" name="workHurtEnsure" lay-verify="required"
                                autocomplete="off" class="layui-input" value="<%=workHurtEnsure%>" readonly>
                    </div>
                </div>
            </div>
            <div class="layui-form-item divlabel1" >
                <%--生育保险--%>
                <div class="layui-inline">
                    <label class="layui-form-label labelText" >
                        <span style="font-size: 18px;">生育保险：</span>
                    </label>
                    <div class="layui-input-inline ">
                        <input  type="text" name="birthEnsure" lay-verify="required"
                                autocomplete="off" class="layui-input" value="<%=birthEnsure%>" readonly>
                    </div>
                </div>
                <%--住房公积金--%>
                <div class="layui-inline">
                    <label class="layui-form-label labelText" >
                        <span style="font-size: 18px;">住房公积金：</span>
                    </label>
                    <div class="layui-input-inline ">
                        <input  type="text" name="houseFundEnsure" lay-verify="required"
                                autocomplete="off" class="layui-input" value="<%=houseFundEnsure%>" readonly>
                    </div>
                </div>
            </div>
        </div>

        <%--纳税（个人所得税）--%>
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 0px;">
            <legend>纳税</legend>
        </fieldset>
        <div class="myDivItem">
            <div class="layui-form-item divlabel1" >
                <%--个人所得税--%>
                <div class="layui-inline">
                    <label class="layui-form-label labelText" >
                        <span style="font-size: 18px;">个人所得税：</span>
                    </label>
                    <div class="layui-input-inline ">
                        <input  type="text" name="oneSelfTax" lay-verify="required"
                                autocomplete="off" class="layui-input" value="<%=oneSelfTax%>" readonly>
                    </div>
                </div>
            </div>
        </div>


    </form>

</div>




</body>
</html>


