
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>工龄工资添加</title>
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
            margin-left: 0px;
        }
        .divlable3 {
            width: 50px;
            margin-left: -11px;
        }
        input {
            font-size: 18px;!important;
            width: 200px;
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

    </style>

</head>

<body>

<%--标题，刷新按钮 部分--%>
<div class="x-nav" style="margin-top: 10px;margin-left: 10px;">
                <span class="layui-breadcrumb" style="visibility: visible">
                    <span class="spanTitle"><a>首页</a> </span>
        <%--            <span lay-separator>/</span>--%>
                    <span class="spanTitle"><a>工龄管理</a> </span>
        <%--            <span lay-separator>/</span>--%>
                    <span class="spanTitle"><a>工龄信息增加</a> </span>
                </span>

    <a class="layui-btn layui-btn-small layui-btn-normal aFlush"

       href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="height:10px;font-size: 20px;!important;">&#xe669;</i>
    </a>

</div>


<%--表单部分--%>
<div style="margin-left: 50px;margin-top: 20px;">
    <form class="layui-form" >

        <input hidden name="workTitleId" value="" disabled />

        <div class="layui-form-item divlabel1" >
            <div class="layui-inline">
                <label class="layui-form-label" style="width: 130px;">
                    <span style="font-size: 18px;">工龄：</span>
                </label>
                <div class="layui-input-inline ">
                    <input  type="text" name="workAge" lay-verify="required|number" style="width: 230px;"
                            autocomplete="off" class="layui-input" value="">
                </div>
            </div>
        </div>

        <div class="layui-form-item divlabel1" >
            <div class="layui-inline">
                <label class="layui-form-label" style="width: 130px;">
                    <span style="font-size: 18px;">基础补贴工资：</span>
                </label>
                <div class="layui-input-inline ">
                    <input  type="text" name="baseAgeMoney" lay-verify="required|number"
                            autocomplete="off" class="layui-input" value="">
                </div>
                <label class="layui-form-label divlable3" >
                    <span style="font-size: 18px;">元/年</span>
                </label>
            </div>
        </div>

        <%--提交重置按钮--%>
        <div class="layui-form-item divlabel1" >
            <div class="layui-input-block" style="margin-left: 260px;">
                <button class="layui-btn " lay-submit lay-filter="myForm"
                        style="font-size: 18px; width: 97px;!important; margin-right: 50px;">保存</button>
                <button type="reset" class="layui-btn layui-btn-primary"
                        style="font-size: 18px; width: 97px;!important;">重置</button>
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
            let workAge = BigInt(data.field.workAge);
            if (workAge < 0 || workAge > 1000) {
                layer.msg("请输入正确的工龄！");
                return false;
            }

            $.ajax({
                url: "${pageContext.request.contextPath}/addWorkAgeMoney",
                data: {     // 表单数据
                    workAge : data.field.workAge,
                    baseAgeMoney : data.field.baseAgeMoney,
                },
                dataType: 'json',
                success: function (result) {
                    if (result.check.toString() === "false") {

                        layer.msg("添加失败！");

                    } else if (result.check.toString() === "true") {

                        // layer.msg("保存成功！",{  time: 1000 } ,
                        //     function() {
                        //         // 关闭当前弹出层
                        //         var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                        //         parent.layer.close(index); //再执行关闭
                        //         // 父页面更新
                        //         window.parent.location.replace(window.parent.location.href)
                        //     }
                        // );


                        layer.open({
                            shade:0,
                            type:0,
                            icon:6,
                            content:'添加成功！',
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
                    layer.msg("添加失败！");
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


