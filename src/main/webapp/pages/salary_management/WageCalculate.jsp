
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>工资结算</title>
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
            width: 300px;
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


<div class="x-nav" style="margin-top: 10px;margin-left: 10px;">
                <span class="layui-breadcrumb" style="visibility: visible">
                    <span style="font-size: 24px;"><a>首页</a> </span>
        <%--            <span lay-separator>/</span>--%>
                    <span style="font-size: 24px;"><a>工资管理</a> </span>
        <%--            <span lay-separator>/</span>--%>
                    <span style="font-size: 24px;"><a>工资结算</a> </span>
                </span>

    <a class="layui-btn layui-btn-small layui-btn-normal" style="width:90px;margin-top:3px;float:right;margin-right: 10px;"
       href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="height:10px;font-size: 20px;!important;">&#xe669;</i>
    </a>

</div>



<div style="margin-left: 50px;margin-top: 20px;">

    <form class="layui-form" >

        <div class="layui-form-item" style="margin-top: 30px;margin-left: 0px;" >
            <div class="layui-inline">
                <span style="font-size: 20px;">按月份，结算所有员工工资</span>
            </div>
        </div>

        <div class="layui-form-item divlabel1" >
            <div class="layui-inline">
                <label class="layui-form-label" style="width: 100px;">
                    <span style="font-size: 18px;">选择月份：</span>
                </label>
                <div class="layui-inline">
                    <input class="layui-input" style="font-size: 18px;width: 200px;" lay-verify="required"
                           name="timeMonthSearch1" id="timeMonthSearch1" autocomplete="off" placeholder="yyyy-MM">
                </div>
            </div>
        </div>

        <%--提交重置按钮--%>
        <div class="layui-form-item divlabel1" >
            <div class="layui-input-block" style="margin-left: 300px;">
                <button class="layui-btn " lay-submit lay-filter="myFormAllUser"
                        style="font-size: 18px; width: 100px;!important; margin-right: 70px;margin-left: -200px;">一键结算</button>
                <button type="reset" class="layui-btn layui-btn-primary"
                        style="font-size: 18px; width: 100px;!important;">重置</button>
            </div>
        </div>

    </form>

    <hr>
    <hr>
    <hr>

    <form class="layui-form" >

        <div class="layui-form-item" style="margin-top: 30px;margin-left: 0px;" >
            <div class="layui-inline">
                <span style="font-size: 20px;">按月份，结算指定员工工资</span>
            </div>
        </div>

        <div class="layui-form-item divlabel1" >
            <div class="layui-inline">
                <label class="layui-form-label" style="width: 100px;">
                    <span style="font-size: 18px;">选择月份：</span>
                </label>
                <div class="layui-inline">
                    <input class="layui-input" style="font-size: 18px;width: 200px;" lay-verify="required"
                           name="timeMonthSearch2" id="timeMonthSearch2" autocomplete="off" placeholder="yyyy-MM">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label " style="width: 200px;margin-left: -50px;">
                    <span style="font-size: 18px;">员工手机号：</span>
                    <%--提示，可能员工不存在--%>
                </label>
                <div class="layui-input-inline ">
                    <input  type="text" name="userPhone" lay-verify="required|number"
                            <%--autocomplete="off"--%> class="layui-input" value="">
                </div>
            </div>
        </div>

        <%--提交重置按钮--%>
        <div class="layui-form-item divlabel1" >
            <div class="layui-input-block" style="margin-left: 300px;">
                <button class="layui-btn " lay-submit lay-filter="myFormOneUser"
                        style="font-size: 18px; width: 100px;!important; margin-right: 70px;margin-left: -200px;">一键结算</button>
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

        //监听提交 1
        form.on('submit(myFormAllUser)', function(data){
            // layer.msg(JSON.stringify(data.field));

            var timeMonthSearch1 = $('#timeMonthSearch1');
            /*正则表达式校验输入的月份查询*/
            if (timeMonthSearch1.val()!== '' && !timeMonthSearch1.val().match('\\d{4}-\\d{2}')) {
                layer.msg('请输入正确的月份'/*,{
                        icon: 5
                    }*/);
                return false;
            }

            $.ajax({
                url: "${pageContext.request.contextPath}/calculateWageAllUser",
                data: {
                    // 根据月份，结算，全部员工工资
                    timeMonthSearch1 : data.field.timeMonthSearch1,
                },
                dataType: 'json',
                success: function (result) {
                    if (result.check.toString() === "false") {
                        layer.msg("工资结算失败！");
                        // open
                        // 工资结算失败
                    } else if (result.check.toString() === "true") {

                        // open
                        // 工资结算成功
                        layer.open({
                            shade:0,
                            type:0,
                            icon:6,
                            content:'工资结算成功！',
                            anim:3,
                            button:'确定',
                            yes() {
                                // 关闭当前弹出层
                                var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                                parent.layer.close(index); //再执行关闭
                                layer.closeAll(); // 关闭弹窗
                            }
                        });
                    } else if (result.check.toString() === "errorMonthSearch") {
                        // 不是结算本月和之前的工资，日期不正确
                        layer.open({
                            shade:0,
                            type:0,
                            icon:5,
                            content:'请输入正确的结算时间！',
                            anim:3,
                            button:'确定',
                            yes() {
                                // 关闭当前弹出层
                                var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                                parent.layer.close(index); //再执行关闭
                                layer.closeAll(); // 关闭弹窗
                            }
                        });
                    } else if (result.check.toString() === "exists") {
                        // 已经结算，是否重新结算
                        // url : calculateWageAllUserAgain

                        // test 确认取消框
                        layer.confirm('工资已经结算，是否重新结算？', {
                            btn: ['确认','取消'] //按钮
                        }, function(){  // 确认函数
                            // layer.msg('确认');

                            // ajax 套娃

                            $.ajax({
                                url: "${pageContext.request.contextPath}/calculateWageAllUserAgain",
                                data: {
                                    // 根据月份和员工手机号，结算，员工工资
                                    timeMonthSearch1 : data.field.timeMonthSearch1,
                                },
                                dataType: 'json',
                                success: function (result) {
                                    if (result.check.toString() === "false") {
                                        layer.msg("工资结算失败！");
                                        // open
                                        // 工资结算失败
                                    } else if (result.check.toString() === "true") {
                                        // layer.msg("工资结算成功！");
                                        // open
                                        // 工资结算成功
                                        layer.open({
                                            shade:0,
                                            type:0,
                                            icon:6,
                                            content:'工资结算成功！',
                                            anim:3,
                                            button:'确定',
                                            yes() {
                                                // 关闭当前弹出层
                                                var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                                                parent.layer.close(index); //再执行关闭
                                                layer.closeAll(); // 关闭弹窗
                                            }
                                        });
                                    }
                                },
                                error: function (XMLHttpRequest, textStatus, errorThrown) {
                                    layer.msg("工资结算失败！");
                                    console.log(XMLHttpRequest.status);
                                    console.log(XMLHttpRequest.readyState);
                                    console.log(textStatus);
                                }
                            });

                        }, function(){ // 取消函数
                            // layer.msg('取消');
                        });
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    layer.msg("工资结算失败！");
                    console.log(XMLHttpRequest.status);
                    console.log(XMLHttpRequest.readyState);
                    console.log(textStatus);
                }
            });

            return false; // 表单1提交
        });


        //监听提交 2
        form.on('submit(myFormOneUser)', function(data){
            // layer.msg(JSON.stringify(data.field));

            var timeMonthSearch2 = $('#timeMonthSearch2');
            /*正则表达式校验输入的月份查询*/
            if (timeMonthSearch2.val()!== '' && !timeMonthSearch2.val().match('\\d{4}-\\d{2}')) {
                layer.msg('请输入正确的月份'/*,{
                        icon: 5
                    }*/);
                return false;
            }

            $.ajax({
                url: "${pageContext.request.contextPath}/calculateWageOneUser",
                data: {
                    // 根据月份和员工手机号，结算，员工工资
                    timeMonthSearch2 : data.field.timeMonthSearch2,
                    userPhone : data.field.userPhone,
                },
                dataType: 'json',
                success: function (result) {
                    if (result.check.toString() === "userNotExists") {

                        // 员工不存在
                        layer.open({
                            shade:0,
                            type:0,
                            icon:5,
                            content:'员工不存在！',
                            anim:3,
                            button:'确定',
                            yes() {
                                // 关闭当前弹出层
                                var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                                parent.layer.close(index); //再执行关闭
                                layer.closeAll(); // 关闭弹窗
                                // 父页面更新
                                // window.parent.location.replace(window.parent.location.href)
                            }
                        });
                    } else if (result.check.toString() === "errorMonthSearch") {
                        // 不是结算本月和之前的工资，日期不正确
                        layer.open({
                            shade:0,
                            type:0,
                            icon:5,
                            content:'请输入正确的结算时间！',
                            anim:3,
                            button:'确定',
                            yes() {
                                // 关闭当前弹出层
                                var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                                parent.layer.close(index); //再执行关闭
                                layer.closeAll(); // 关闭弹窗
                            }
                        });
                    } else if (result.check.toString() === "false") {
                        layer.msg("工资结算失败！");
                        // open
                        // 工资结算失败
                    } else if (result.check.toString() === "true") {
                        layer.msg("工资结算成功！");
                        // open
                        // 工资结算成功
                    } else if (result.check.toString() === "exists") {
                        // 已经结算，是否重新结算
                        // url : calculateWageOneUserAgain

                        // test 确认取消框
                        layer.confirm('工资已经结算，是否重新结算？', {
                            btn: ['确认','取消'] //按钮
                        }, function(){  // 确认函数
                            // layer.msg('确认');

                            // ajax 套娃

                            $.ajax({
                                url: "${pageContext.request.contextPath}/calculateWageOneUserAgain",
                                data: {
                                    // 根据月份和员工手机号，结算，员工工资
                                    timeMonthSearch2 : data.field.timeMonthSearch2,
                                    userPhone : data.field.userPhone,
                                },
                                dataType: 'json',
                                success: function (result) {
                                    if (result.check.toString() === "false") {
                                        layer.msg("工资结算失败！");
                                        // open
                                        // 工资结算失败
                                    } else if (result.check.toString() === "true") {
                                        // layer.msg("工资结算成功！");
                                        // open
                                        // 工资结算成功
                                        layer.open({
                                            shade:0,
                                            type:0,
                                            icon:6,
                                            content:'工资结算成功！',
                                            anim:3,
                                            button:'确定',
                                            yes() {
                                                // 关闭当前弹出层
                                                var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                                                parent.layer.close(index); //再执行关闭
                                                layer.closeAll(); // 关闭弹窗
                                            }
                                        });
                                    }
                                },
                                error: function (XMLHttpRequest, textStatus, errorThrown) {
                                    layer.msg("工资结算失败！");
                                    console.log(XMLHttpRequest.status);
                                    console.log(XMLHttpRequest.readyState);
                                    console.log(textStatus);
                                }
                            });




                        }, function(){ // 取消函数
                            // layer.msg('取消');
                        });



                        // // 工资已经结算，是否重新结算
                        // layer.open({
                        //     shade:0,
                        //     type:0,
                        //     icon:5,
                        //     content:'工资已经结算，是否重新结算？',
                        //     anim:3,
                        //     // .... 确定，取消
                        //     button:'确定',
                        //     yes() {
                        //         // 关闭当前弹出层
                        //         var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                        //         parent.layer.close(index); //再执行关闭
                        //         layer.closeAll(); // 关闭弹窗
                        //         // 父页面更新
                        //         // window.parent.location.replace(window.parent.location.href)
                        //     }
                        // });
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    layer.msg("工资结算失败！");
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


