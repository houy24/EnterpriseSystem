
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>日常事务工资项管理</title>
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
            margin-top: 40px;
            margin-left: 60px;
        }
        .divlabel2 {
            margin-left: 40px;
        }
        .divlable3 {
            width: 100px;
            margin-left: -70px;
            z-index: -1;
        }
        input {
            font-size: 18px;!important;width: 170px;
        }
        .x-nav{
            padding: 0 20px;
            position: relative;
            z-index: 99;
            border-bottom: 1px solid #e5e5e5;
            line-height: 50px;
            height: 50px;
            overflow: hidden;
            background: #fff;
        }

    </style>

</head>



<body>


<%--<h1>日常事务工资项管理</h1>--%>

    <div class="x-nav" style="margin-top: 10px;margin-left: 10px;">
                <span class="layui-breadcrumb" style="visibility: visible">
                    <span style="font-size: 24px;"><a>首页</a> </span>
        <%--            <span lay-separator>/</span>--%>
                    <span style="font-size: 24px;"><a>日常工资项管理</a> </span>
        <%--            <span lay-separator>/</span>--%>
                    <span style="font-size: 24px;"><a>工资项</a> </span>
                </span>

        <a class="layui-btn layui-btn-small layui-btn-normal" style="width:90px;margin-top:3px;float:right;margin-right: 10px;"
           href="javascript:location.replace(location.href);" title="刷新">
            <i class="layui-icon" style="height:10px;font-size: 20px;!important;">&#xe669;</i>
        </a>

    </div>



    <div style="margin-left: 100px;margin-top: 20px;">
        <form class="layui-form" >

            <input hidden name="routineItemId" value="default" disabled  />

            <div class="layui-form-item divlabel1" >
                <div class="layui-inline">
                    <label class="layui-form-label" style="width: 100px;">
                        <span style="font-size: 18px;">迟到罚金：</span>
                    </label>
                    <div class="layui-input-inline ">
                        <input  type="text" name="latePenalty" lay-verify="required|number"
                                autocomplete="off" class="layui-input" value="${routineItem.latePenalty}">
                    </div>
                    <label class="layui-form-label divlable3" >
                        <span style="font-size: 18px;">元/次</span>
                    </label>
                </div>

                <div class="layui-inline divlabel2" >
                    <label class="layui-form-label" style="width: 100px;">
                        <span style="font-size: 18px;">早退罚金：</span>
                    </label>
                    <div class="layui-input-inline">
                        <input  type="text" name="outEarlyPenalty" lay-verify="required|number"
                                autocomplete="off" class="layui-input" value="${routineItem.outEarlyPenalty}">
                    </div>
                    <label class="layui-form-label divlable3" >
                        <span style="font-size: 18px;">元/次</span>
                    </label>
                </div>
            </div>

            <div class="layui-form-item divlabel1" >
                <div class="layui-inline">
                    <label class="layui-form-label" style="width: 100px;">
                        <span style="font-size: 18px;">缺勤罚金：</span>
                    </label>
                    <div class="layui-input-inline ">
                        <input  type="text" name="absentPenalty" lay-verify="required|number"
                                autocomplete="off" class="layui-input" value="${routineItem.absentPenalty}">
                    </div>
                    <label class="layui-form-label divlable3" >
                        <span style="font-size: 18px;">元/次</span>
                    </label>
                </div>

                <div class="layui-inline divlabel2" >
                    <label class="layui-form-label" style="width: 100px;">
                        <span style="font-size: 18px;">全勤奖金：</span>
                    </label>
                    <div class="layui-input-inline">
                        <input  type="text" name="fullTimeAllowance" lay-verify="required|number"
                                autocomplete="off" class="layui-input" value="${routineItem.fullTimeAllowance}">
                    </div>
                    <label class="layui-form-label divlable3" >
                        <span style="font-size: 18px;">元/月</span>
                    </label>
                </div>
            </div>

            <div class="layui-form-item divlabel1" >
                <div class="layui-inline">
                    <label class="layui-form-label" style="width: 100px;">
                        <span style="font-size: 18px;">加班奖金：</span>
                    </label>
                    <div class="layui-input-inline ">
                        <input  type="text" name="workOvertimeAllowance" lay-verify="required|number"
                                autocomplete="off" class="layui-input" value="${routineItem.workOvertimeAllowance}">
                    </div>
                    <label class="layui-form-label divlable3" >
                        <span style="font-size: 18px;">元/天</span>
                    </label>
                </div>

                <div class="layui-inline divlabel2" >
                    <label class="layui-form-label" style="width: 100px;">
                        <span style="font-size: 18px;">出差补贴：</span>
                    </label>
                    <div class="layui-input-inline">
                        <input  type="text" name="travelAllowance" lay-verify="required|number"
                                autocomplete="off" class="layui-input" value="${routineItem.travelAllowance}">
                    </div>
                    <label class="layui-form-label divlable3" >
                        <span style="font-size: 18px;">元/天</span>
                    </label>
                </div>
            </div>

            <div class="layui-form-item divlabel1" >
                <div class="layui-inline">
                    <label class="layui-form-label" style="width: 100px;">
                        <span style="font-size: 18px;">餐饮补贴：</span>
                    </label>
                    <div class="layui-input-inline ">
                        <input  type="text" name="eatAllowance" lay-verify="required|number"
                                autocomplete="off" class="layui-input" value="${routineItem.eatAllowance}">
                    </div>
                    <label class="layui-form-label divlable3" >
                        <span style="font-size: 18px;">元/月</span>
                    </label>
                </div>

                <div class="layui-inline divlabel2" >
                    <label class="layui-form-label" style="width: 100px;">
                        <span style="font-size: 18px;">交通补贴：</span>
                    </label>
                    <div class="layui-input-inline">
                        <input  type="text" name="carAllowance" lay-verify="required|number"
                                autocomplete="off" class="layui-input" value="${routineItem.carAllowance}">
                    </div>
                    <label class="layui-form-label divlable3" >
                        <span style="font-size: 18px;">元/月</span>
                    </label>
                </div>
            </div>

            <div class="layui-form-item divlabel1" >
                <div class="layui-inline">
                    <label class="layui-form-label" style="width: 100px;">
                        <span style="font-size: 18px;">住房补贴：</span>
                    </label>
                    <div class="layui-input-inline ">
                        <input  type="text" name="houseAllowance" lay-verify="required|number"
                                autocomplete="off" class="layui-input" value="${routineItem.houseAllowance}">
                    </div>
                    <label class="layui-form-label divlable3" >
                        <span style="font-size: 18px;">元/月</span>
                    </label>
                </div>

            </div>

            <%--提交重置按钮--%>
            <div class="layui-form-item divlabel1" >
                <div class="layui-input-block" style="margin-left: 300px;">
                    <button class="layui-btn " lay-submit lay-filter="myForm"
                            style="font-size: 18px; width: 100px;!important; margin-right: 70px;">保存</button>
                    <button type="reset" class="layui-btn layui-btn-primary"
                            style="font-size: 18px; width: 100px;!important;">重置</button>
                </div>
            </div>


        </form>

    </div>

<script>
    //Demo
    layui.use('form', function(){
        var form = layui.form;
        var layer = layui.layer;

        //监听提交
        form.on('submit(myForm)', function(data){
            // layer.msg(JSON.stringify(data.field));

            $.ajax({
                url: "${pageContext.request.contextPath}/saveRoutineItem",
                data: {
                     routineItemId : data.field.routineItemId,
                    latePenalty : data.field.latePenalty,
                    outEarlyPenalty : data.field.outEarlyPenalty,
                    absentPenalty : data.field.absentPenalty,
                    fullTimeAllowance : data.field.fullTimeAllowance,
                    workOvertimeAllowance : data.field.workOvertimeAllowance,
                    travelAllowance : data.field.travelAllowance,
                    eatAllowance : data.field.eatAllowance,
                    carAllowance : data.field.carAllowance,
                    houseAllowance : data.field.houseAllowance,
                },
                dataType: 'json',
                success: function (result) {
                    if (result.check.toString() === "false") {
                        layer.msg("保存失败！");
                    } else if (result.check.toString() === "true") {
                        layer.msg("保存成功！");
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
    });
</script>



</body>
</html>


