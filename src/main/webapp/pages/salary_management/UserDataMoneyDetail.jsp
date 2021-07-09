<%@ page import="com.xxx.service.Department.DepartmentService" %>
<%@ page import="com.xxx.service.Department.DepartmentServiceImpl" %>
<%@ page import="com.xxx.pojo.Department" %>
<%@ page import="java.util.List" %>
<%@ page import="com.xxx.service.Position.PositionService" %>
<%@ page import="com.xxx.pojo.Position" %>
<%@ page import="com.xxx.service.Position.PositionServiceImpl" %>
<%@ page import="com.xxx.service.WorkTitle.WorkTitleServiceImpl" %>
<%@ page import="com.xxx.service.WorkTitle.WorkTitleService" %>
<%@ page import="com.xxx.pojo.WorkTitle" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>查看员工工资信息情况</title>
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
            text-align: center; /* label文字 */
            width: 130px;
            margin-left: 10px;
            z-index: -1;
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
        // （用户编号，员工手机号，员工姓名，部门名称）
        // （岗位名称，岗位工资，职称名称，职称工资）
        // （工龄，工龄基础工资）

        // UserDataMoney
        // userId , userPhone , userName , departmentName ,
        // positionName , positionMoney , workTitleName , workTitleMoney
        // workAge , baseAgeMoney

        String userId = request.getParameter("userId");
        String userName = request.getParameter("userName");
        String userPhone = request.getParameter("userPhone");
        String departmentName = request.getParameter("departmentName");
        String positionName = request.getParameter("positionName");
        String positionMoney = request.getParameter("positionMoney");
        String workTitleName = request.getParameter("workTitleName");
        String workTitleMoney = request.getParameter("workTitleMoney");
        String workAge = request.getParameter("workAge");
        String baseAgeMoney = request.getParameter("baseAgeMoney");
        String userPhoto = request.getParameter("userPhoto");

        System.out.println("UserDataMoneyDetail.jsp => " + userId + "," + userPhone + ","
                + userName + "," + departmentName + ","
                + positionName + "," + positionMoney + ","
                + workTitleName + "," + workTitleMoney + ","
                + workAge + "," + baseAgeMoney + "," + userPhoto);

        // 当前查看列，部门名称
        request.setAttribute("departmentName",departmentName);
        // 当前查看列，岗位名称
        request.setAttribute("positionName",positionName);
        // 当前查看列，职称名称
        request.setAttribute("workTitleName",workTitleName);

        // 预查询出所有部门
        DepartmentService departmentService = new DepartmentServiceImpl();
        List<Department> departmentList = departmentService.selectAll();
        // 预查询出所有岗位
        PositionService positionService = new PositionServiceImpl();
        List<Position> positionList = positionService.getAllPosition();
        // 预查询出所有职称
        WorkTitleService workTitleService = new WorkTitleServiceImpl();
        List<WorkTitle> workTitleList = workTitleService.getAllWorkTitle();

    %>

</head>

<body>

<%--标题，刷新按钮 部分--%>
<div class="x-nav" style="margin-top: 10px;margin-left: 10px;">
                <span class="layui-breadcrumb" style="visibility: visible">
                    <span class="spanTitle"><a>首页</a> </span>
        <%--            <span lay-separator>/</span>--%>
                    <span class="spanTitle"><a>员工管理</a> </span>
        <%--            <span lay-separator>/</span>--%>
                    <span class="spanTitle"><a>查看员工信息</a> </span>
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

<%--        <div class="layui-inline">--%>
<%--            &lt;%&ndash;头像显示&ndash;%&gt;--%>
<%--            <img src="${pageContext.request.contextPath}/resources/images/head/666.jpg" class="myHeadImg">--%>
<%--        </div>--%>


        <div class="layui-form-item">
            <%--。。。。。左边。。。--%>
            <div class="layui-inline">

                <%--员工编号--%>
                <div class="layui-form-item divlabel1" >
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

                <%--员工姓名--%>
                <div class="layui-form-item divlabel1" >
                    <div class="layui-inline">
                        <label class="layui-form-label labelText" >
                            <span style="font-size: 18px;">员工姓名：</span>
                        </label>
                        <div class="layui-input-inline ">
                            <input  type="text" name="userName" lay-verify="required"
                                    autocomplete="off" class="layui-input" value="<%=userName%>" readonly>
                        </div>
                    </div>
                </div>

                <%--员工手机号--%>
                <div class="layui-form-item divlabel1" >
                    <div class="layui-inline">
                        <label class="layui-form-label labelText" >
                            <span style="font-size: 18px;">员工手机号：</span>
                        </label>
                        <div class="layui-input-inline ">
                            <input  type="text" name="userPhone" lay-verify="required" style="width: 230px;"
                                    autocomplete="off" class="layui-input" value="<%=userPhone%>" readonly>
                        </div>
                    </div>
                </div>

                <%--部门名称--%>
                <div class="layui-form-item divlabel1" >
                    <div class="layui-inline">
                        <label class="layui-form-label labelText" >
                            <span style="font-size: 18px;">部门名称：</span>
                        </label>
                        <%--使用 select 框显示，不使用文本输入框显示 --%>
                        <%--<div class="layui-input-inline ">
                            <input  type="text" name="departmentName" lay-verify="required" style="width: 230px;"
                                    autocomplete="off" class="layui-input" value="<%=departmentName%>" readonly>
                        </div>--%>
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

            <%--。。。。。右边头像。。。--%>
            <div class="layui-inline">
                <%--头像显示， 当找不到对应头像时，显示默认头像 default.png --%>
                <img src="<%=userPhoto%>"
                     class="myHeadImg" onerror="this.src='${pageContext.request.contextPath}/resources/images/head/default.png'">
            </div>

        </div>

        <%--岗位名称，岗位工资--%>
        <div class="layui-form-item divlabel1" >
            <%--岗位名称--%>
            <div class="layui-inline">
                <label class="layui-form-label labelText" >
                    <span style="font-size: 18px;">岗位名称：</span>
                </label>
                <div class="layui-input-inline ">
<%--                    <input  type="text" name="positionName" lay-verify="required" style="width: 230px;"--%>
<%--                            autocomplete="off" class="layui-input" value="<%=positionName%>" readonly>--%>
                    <select name="positionNameSelect" lay-verify="required" style="width: 260px;" >
                        <c:forEach items="<%=positionList%>" var="position">
                            <option value="${position.positionId}"
                                <%--相等时选中--%>
                                    <c:if test="${ position.positionName ==  positionName  }"> selected </c:if>
                                    <c:if test="${ position.positionName != positionName }"> disabled </c:if>
                            >${position.positionName}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <%--岗位工资--%>
            <div class="layui-inline">
                <label class="layui-form-label labelText" >
                    <span style="font-size: 18px;">岗位工资：</span>
                </label>
                <div class="layui-input-inline ">
                    <input  type="text" name="positionMoney" lay-verify="required|number"
                            autocomplete="off" class="layui-input" value="<%=positionMoney%>" readonly>
                </div>
                <label class="layui-form-label divlable3" >
                    <span style="font-size: 18px;">元/月</span>
                </label>
            </div>
        </div>

        <%--职称名称，职称工资--%>
        <div class="layui-form-item divlabel1" >
            <%--职称名称--%>
            <div class="layui-inline">
                <label class="layui-form-label labelText" >
                    <span style="font-size: 18px;">职称名称：</span>
                </label>
                <div class="layui-input-inline ">
<%--                    <input  type="text" name="workTitleName" lay-verify="required" style="width: 230px;"--%>
<%--                            autocomplete="off" class="layui-input" value="<%=workTitleName%>" readonly>--%>
                    <select name="workTitleNameSelect" lay-verify="required" style="width: 260px;" >
                        <c:forEach items="<%=workTitleList%>" var="workTitle">
                            <option value="${workTitle.workTitleId}"
                                <%--相等时选中--%>
                                    <c:if test="${ workTitle.workTitleName ==  workTitleName  }"> selected </c:if>
                                    <c:if test="${ workTitle.workTitleName != workTitleName }"> disabled </c:if>
                            >${workTitle.workTitleName}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <%--职称工资--%>
            <div class="layui-inline">
                <label class="layui-form-label labelText" >
                    <span style="font-size: 18px;">职称工资：</span>
                </label>
                <div class="layui-input-inline ">
                    <input  type="text" name="workTitleMoney" lay-verify="required|number"
                            autocomplete="off" class="layui-input" value="<%=workTitleMoney%>" readonly>
                </div>
                <label class="layui-form-label divlable3" >
                    <span style="font-size: 18px;">元/月</span>
                </label>
            </div>
        </div>

        <%--工龄，基础工龄工资--%>
        <div class="layui-form-item divlabel1" >
            <%--工龄--%>
            <div class="layui-inline" style="margin-left: -10px;">
                <label class="layui-form-label labelText" >
                    <span style="font-size: 18px;">工龄：</span>
                </label>
                <div class="layui-input-inline ">
                    <input  type="text" name="workAge" lay-verify="required|number"
                            autocomplete="off" class="layui-input" value="<%=workAge%>" readonly>
                </div>
                <label class="layui-form-label divlable3" >
                    <span style="font-size: 18px;">年</span>
                </label>
            </div>
            <%--基础工龄工资--%>
            <div class="layui-inline " style="margin-left: -20px;">
                <label class="layui-form-label labelText" >
                    <span style="font-size: 18px;">基础工龄工资：</span>
                </label>
                <div class="layui-input-inline ">
                    <input  type="text" name="baseAgeMoney" lay-verify="required|number"
                            autocomplete="off" class="layui-input" value="<%=baseAgeMoney%>" readonly>
                </div>
                <label class="layui-form-label divlable3" >
                    <span style="font-size: 18px;">元/年</span>
                </label>
            </div>
        </div>


        <%--选择框 test (!)--%>
        <%--部门名称--%>
        <%--<div class="layui-form-item divlabel1" >
            <div class="layui-inline">
                <label class="layui-form-label labelText" >
                    <span style="font-size: 18px;">部门名称：</span>
                </label>
                <div class="layui-input-inline ">
                    <select name="departmentNameSelect" lay-verify="required" style="width: 260px;" >
                        <c:forEach items="<%=departmentList%>" var="department">
                            <option value="${department.departmentId}"
                                    &lt;%&ndash;相等时选中&ndash;%&gt;
                                <c:if test="${ department.departmentName ==  departmentName  }"> selected </c:if>
                            >${department.departmentName}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
        </div>--%>


        <%--搜索选择框--%>
<%--        <div class="layui-inline">--%>
<%--            <label class="layui-form-label">搜索选择框</label>--%>
<%--            <div class="layui-input-inline">--%>
<%--                <select name="modules" lay-verify="required" lay-search="">--%>
<%--                    <option value="">直接选择或搜索选择</option>--%>
<%--                    <option value="1">layer</option>--%>
<%--                    <option value="2">form</option>--%>
<%--                    <option value="3">layim</option>--%>
<%--                    <option value="4">element</option>--%>
<%--                    <option value="5">laytpl</option>--%>
<%--                    <option value="6">upload</option>--%>
<%--                    <option value="7">laydate</option>--%>
<%--                    <option value="8">laypage</option>--%>
<%--                    <option value="9">flow</option>--%>
<%--                    <option value="10">util</option>--%>
<%--                    <option value="11">code</option>--%>
<%--                    <option value="12">tree</option>--%>
<%--                    <option value="13">layedit</option>--%>
<%--                    <option value="14">nav</option>--%>
<%--                    <option value="15">tab</option>--%>
<%--                    <option value="16">table</option>--%>
<%--                    <option value="17">select</option>--%>
<%--                    <option value="18">checkbox</option>--%>
<%--                    <option value="19">switch</option>--%>
<%--                    <option value="20">radio</option>--%>
<%--                </select>--%>
<%--            </div>--%>
<%--        </div>--%>

        <%--只查看，无功能按钮--%>
        <%--提交重置按钮--%>
        <%--<div class="layui-form-item divlabel1" >
            <div class="layui-input-block" style="margin-left: 260px;">
                <button class="layui-btn " lay-submit lay-filter="myForm"
                        style="font-size: 18px; width: 97px;!important; margin-right: 50px;">保存</button>
                <button type="reset" class="layui-btn layui-btn-primary"
                        style="font-size: 18px; width: 97px;!important;">重置</button>
            </div>
        </div>--%>

    </form>

</div>

<script>
    /*只查看，无表单提交功能*/
    /*//Demo
    layui.use('form', function(){
        var form = layui.form;
        var layer = layui.layer;

        //监听提交
        form.on('submit(myForm)', function(data){
            // layer.msg(JSON.stringify(data.field));
            let workAge = BigInt(data.field.workAge);
            if (workAge < 0 || workAge > 1000) {
                layer.msg("请输入正确的工龄！");
                return false;
            }

            $.ajax({
                url: "${pageContext.request.contextPath}/saveWorkAgeMoney",
                data: {     // 表单数据
                    workAge : data.field.workAge,
                    baseAgeMoney : data.field.baseAgeMoney,
                },
                dataType: 'json',
                success: function (result) {
                    if (result.check.toString() === "exists") {
                        layer.msg("该工龄已存在，保存失败！");

                    } else if (result.check.toString() === "false") {

                        layer.msg("保存失败！");

                    } else if (result.check.toString() === "true") {


                        layer.open({
                            shade:0,
                            type:0,
                            icon:6,
                            content:'保存成功！',
                            anim:3,
                            button:'确定',
                            yes() {
                                // 关闭当前弹出层
                                var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                                parent.layer.close(index); //再执行关闭
                                // 父页面更新
                                window.parent.location.replace(window.parent.location.href)
                            }
                        });
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    layer.msg("保存失败！");
                    console.log(XMLHttpRequest.status);
                    console.log(XMLHttpRequest.readyState);
                    console.log(textStatus);
                }
            });

            return false; // 表单提交
        });
    });*/
</script>



</body>
</html>


